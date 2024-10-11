terraform {
  required_providers {
    aws = {
      source  = "hashicorp/aws"
      version = "4.0.0"
    }
  }

  required_version = "~> 1.9.7"
}

provider "aws" {
  region = var.aws_region
}

data "aws_availability_zones" "available" {
  state = "available"
}


data "aws_ami" "ubuntu" {

  most_recent = "true"


  filter {
    name   = "name"
    values = ["ubuntu/images/hvm-ssd/ubuntu-focal-20.04-amd64-server-*"]
  }


  filter {
    name   = "virtualization-type"
    values = ["hvm"]
  }
  
  
  owners = ["099720109477"]
}

resource "aws_vpc" "nequi_vpc" {
  
  cidr_block           = var.vpc_cidr_block
  
  enable_dns_hostnames = true

  
  tags = {
    Name = "nequi_vpc"
  }
}


resource "aws_internet_gateway" "nequi_igw" {
  
  vpc_id = aws_vpc.nequi_vpc.id

  
  tags = {
    Name = "nequi_igw"
  }
}


resource "aws_subnet" "nequi_public_subnet" {
  
  count             = var.subnet_count.public
  
  vpc_id            = aws_vpc.nequi_vpc.id
  
  
  cidr_block        = var.public_subnet_cidr_blocks[count.index]
  
  availability_zone = data.aws_availability_zones.available.names[count.index]

  tags = {
    Name = "nequi_public_subnet_${count.index}"
  }
}


resource "aws_subnet" "nequi_private_subnet" {
  count             = var.subnet_count.private
  vpc_id            = aws_vpc.nequi_vpc.id
  cidr_block        = var.private_subnet_cidr_blocks[count.index]
  availability_zone = data.aws_availability_zones.available.names[count.index]

  tags = {
    Name = "nequi_private_subnet_${count.index}"
  }
}


resource "aws_route_table" "nequi_public_rt" {
  
  vpc_id = aws_vpc.nequi_vpc.id
  route {
    cidr_block = "0.0.0.0/0"
    gateway_id = aws_internet_gateway.nequi_igw.id
  }
}

resource "aws_route_table_association" "public" {
  count          = var.subnet_count.public
  route_table_id = aws_route_table.nequi_public_rt.id
  subnet_id      = 	aws_subnet.nequi_public_subnet[count.index].id
}

resource "aws_route_table" "nequi_private_rt" {
  vpc_id = aws_vpc.nequi_vpc.id
}

resource "aws_route_table_association" "private" {
  count          = var.subnet_count.private
  route_table_id = aws_route_table.nequi_private_rt.id
  subnet_id      = aws_subnet.nequi_private_subnet[count.index].id
}

resource "aws_security_group" "nequi_web_sg" {
  name        = "nequi_web_sg"
  description = "Security group for web servers"
  vpc_id      = aws_vpc.nequi_vpc.id

  ingress {
    description = "Allow all traffic through HTTP"
    from_port   = "80"
    to_port     = "80"
    protocol    = "tcp"
    cidr_blocks = ["0.0.0.0/0"]
  }

  ingress {
    description = "Allow SSH from my computer"
    from_port   = "22"
    to_port     = "22"
    protocol    = "tcp"
    // This is using the variable "my_ip"
    cidr_blocks = ["${var.my_ip}/32"]
  }

  egress {
    description = "Allow all outbound traffic"
    from_port   = 0
    to_port     = 0
    protocol    = "-1"
    cidr_blocks = ["0.0.0.0/0"]
  }

  tags = {
    Name = "nequi_web_sg"
  }
}

resource "aws_security_group" "nequi_db_sg" {
  name        = "nequi_db_sg"
  description = "Security group for  databases"
  vpc_id      = aws_vpc.nequi_vpc.id

  ingress {
    description     = "Allow PG traffic from only the web sg"
    from_port       = "5432"
    to_port         = "5432"
    protocol        = "tcp"
    security_groups = [aws_security_group.nequi_web_sg.id]
  }

  tags = {
    Name = "nequi_db_sg"
  }
}

resource "aws_db_subnet_group" "nequi_db_subnet_group" {
  name        = "nequi_db_subnet_group"
  description = "DB subnet group"
  subnet_ids  = [for subnet in aws_subnet.nequi_private_subnet : subnet.id]
}

resource "aws_db_instance" "nequi_database" {
  allocated_storage      = var.settings.database.allocated_storage
  engine                 = var.settings.database.engine
  engine_version         = var.settings.database.engine_version
  instance_class         = var.settings.database.instance_class
  db_name                = var.settings.database.db_name
  username               = var.db_username
  password               = var.db_password
  db_subnet_group_name   = aws_db_subnet_group.nequi_db_subnet_group.id
  vpc_security_group_ids = [aws_security_group.nequi_db_sg.id]
  skip_final_snapshot    = var.settings.database.skip_final_snapshot
}

resource "aws_key_pair" "nequi_kp" {
  key_name   = "nequi_kp"
  public_key = file("nequi_kp.pub")
}

resource "aws_instance" "instance_web" {
  count                  = var.settings.web_app.count
  ami                    = data.aws_ami.ubuntu.id
  instance_type          = var.settings.web_app.instance_type
  subnet_id              = aws_subnet.nequi_public_subnet[count.index].id
  key_name               = aws_key_pair.nequi_kp.key_name
  vpc_security_group_ids = [aws_security_group.nequi_web_sg.id]
  user_data = "${file("app_config.sh")}"
  tags = {
    Name = "instance_web_${count.index}"
  }
}

resource "aws_eip" "instance_web_eip" {
  count    = var.settings.web_app.count
  instance = aws_instance.instance_web[count.index].id
  vpc      = true
  tags = {
    Name = "instance_web_eip_${count.index}"
  }
}
