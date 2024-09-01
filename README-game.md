# How it works

Please run 2 instances of this service(profile player1 and player2), which represent 2 independent units for two players.


When player1 wants to start the game they trigger POST request http://localhost:8082/game/start. This endpoint triggers player2 instance and asks in console whether player2 wants to start a game. If player types "Yes" - the game is started. For manual input player should type "M" in console, otherwise the game will be played automatically.

When player2 wants to start the game they trigger POST request http://localhost:8081/game/start. The other functionality works the same. 

For the successful game two instances should be up and running.

## Instructions

Install and configure Java 21 and Maven 3.9 or higher. Install Docker Desktop.

1. Start the docker-compose: `docker-compose up` - kafka will be available on http://localhost:8090/overview
2. Run the application for player 1: `./mvnw spring-boot:run -Dspring-boot.run.profiles=player1`
3. Run the application for player 2: `./mvnw spring-boot:run -Dspring-boot.run.profiles=player2` 
4. Run the tests: `mvn test`

## What could be improved

1. In a real case scenario I would use 3 instances: 2 clients and 1 server. 
2. More unit tests could be added.
3. Integration tests with Test Containers could be added to test Kafka messages.