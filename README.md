## Accounts microservice
This service provides accounts and transactions REST API.
This service is one part of a larger micro services example app.

### How to run
There are 3 env variables needed for the app to run:
1. DB_URL - url to a MySql DB. Example: jdbc:mysql://1.2.3.4:3306/accounts
1. DB_USER - username for the db connection
1. DB_PASSWORD - password for the db connection

##### Run the DB:
```
docker run --name devops-accounts-mysql -e MYSQL_ROOT_PASSWORD=<my-pass> -e MYSQL_DATABASE=accounts -e MYSQL_USER=<my-user> -e MYSQL_PASSWORD=<my-pass> -p 3306:3306 -d  mysql:5.7.19
```
   
##### Run the App:
```
docker run -e DB_URL='jdbc:mysql://<my-server>:3306/accounts?useUnicode=true&characterEncoding=UTF-8' -e DB_USER='<my-user>' -e DB_PASSWORD='<my-pass>' -p8080:8080 <my-private-repo>/account-service:1.0.0
```
Later on the kubernetes config to run this app will be added.

### Live api test via swagger ui
The app is deployed on heroku and can be found here: https://ocp-accounts-service.herokuapp.com/swagger-ui.html
 
  
