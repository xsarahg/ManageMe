# eindopdracht-ManageMe


Run the application:

1. Open your terminal and go to the directory where you want the project to be in. 
2. Execute the following line in your terminal: ```git clone https://github.com/hogeschoolnovi/eindopdracht-ManageMe.git```
3. Open the cloned file and go to: src/main/java/resources/application.properties 
4. Change spring.datasource.username to your Postgres username
5. Change spring.datasource.password to your Postgres password
6. If you don't want to use the default postgres database, you can create another database in Postgres and insert the name of the database after: spring.datasource.url= jdbc:postgresql://localhost:5432/ (remove "postgres")
7. Open your terminal again and go to the project. 
8. Execute the following line in your terminal: ```mvn spring-boot run``` or ```mvn spring-boot run``` (depends on your device).
The backend will run on port 8090.
9. Execute the following line in your terminal: ```cd client```
10. Execute the following line in your terminal: ```npm install && npm start``` 
The backend will run on port 3000

Enjoy the application! 
