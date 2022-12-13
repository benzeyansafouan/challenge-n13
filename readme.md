mvn clean install
docker-compose up --build
docker exec -it  challenge-n13-mongodb bash "/init.sh"