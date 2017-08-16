## Quotes microservice
This service provides quotes and companies REST API.
This service is one part of a larger micro services example app.

### How to run
There are 3 env variables needed for the app to run:
1. DB_URL - url to a MongoDB. Example: 1.2.3.4:45283/quotes
1. DB_USER - username for the db connection
1. DB_PASSWORD - password for the db connection

##### Run the App:
```
docker run -e DB_URL='<my-server>:45283/quotes' -e DB_USER='<my-user>' -e DB_PASSWORD='<my-pass>' -p8080:8080 <my-private-repo>/quotes-service:1.0.0
```
Later on the kubernetes config to run this app will be added.

### Live api test via swagger ui
The app is deployed on heroku and can be found here: https://ocp-quotes-service.herokuapp.com/swagger-ui.html
 
  
