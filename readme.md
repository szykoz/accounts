SwaggerUI documentation available at: http://localhost:8080/swagger-ui/index.html

# How to run MyBank App:

// mysql  
docker run -p 3306:3306 --name accountsdb -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=accountsdb -d mysql  
docker run -p 3307:3306 --name cardsdb -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=cardsdb -d mysql  
docker run -p 3308:3306 --name loansdb -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=loansdb -d mysql  

//latest RabbitMQ 4.x  
docker run -it --rm --name rabbitmq -p 5672:5672 -p 15672:15672 rabbitmq:4-management  

Run Order:
1. Config Server
2. Eureka Server
3. Accounts/Cards/Loans Microservices
4. Gateway Server
