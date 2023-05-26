
build-image:
	./gradlew build
	docker build -t play-kafka:latest .
	docker run play-kafka:latest

run:
	docker run play-kafka:latest