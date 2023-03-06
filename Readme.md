# Correction TP AMSC

Correction du TP AMSC version Monolithique

# Bases de données
Ce projet suppose l'accès à 2 SGBD que vous pouvez lancer via ces commandes :

## MongoDB :

docker run --name mongo -p 27017:27017 -e MONGO_INITDB_ROOT_USERNAME=root -e MONGO_INITDB_ROOT_PASSWORD=root -e MONGO_INITDB_DATABASE=banque_spring -d mongo:latest

## MySQL :

docker run --name monsql -p 3306:3306  -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=banque-spring -d mysql:oracle