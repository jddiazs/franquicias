#! /bin/bash
sudo apt-get update
sudo apt-get install git
sudo apt-get install docker
sudo apt-get install docker-ce docker-ce-cli containerd.io docker-buildx-plugin docker-compose-plugin
sudo git clone https://github.com/jddiazs/franquicias.git
cd franquicias
./gradlew build --refresh-dependencies
./gradlew bootJar
sudo service docker start
sudo docker compose up