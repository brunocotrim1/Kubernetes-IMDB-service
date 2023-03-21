CALL mvn -f ./tittles-micro-service/pom.xml clean package
CALL mvn -f ./episode-micro-service/pom.xml clean package
CALL mvn -f ./people-micro-service/pom.xml clean package
CALL mvn -f ./principals-micro-service/pom.xml clean package
docker compose up