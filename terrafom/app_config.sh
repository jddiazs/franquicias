#! /bin/bash
sudo apt-get update
sudo apt-get install git
sudo apt-get install docker-ce docker-ce-cli containerd.io docker-buildx-plugin docker-compose-plugin
sudo git clone https://github.com/jddiazs/nequi.git
sudo cd nequi
sudo ./gradlew bootJar
sudo service docker start
sudo docker compose up