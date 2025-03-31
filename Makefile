stop:
	sudo docker-compose down

start:
	sudo docker-compose build --no-cache
	sudo docker-compose up

mvn-build:
	mvn -f kafka-library/ clean install
	mvn -f proto/ clean install
	mvn -f matcher/ clean package
	mvn -f auth/ clean package
	mvn -f gateway-api/ clean package
	mvn -f location/ clean package
	mvn -f profile/ clean package
	mvn -f recommendation/ clean package

