# Assesment
1. Clone the project from https://github.com/amanverma94/Assesment

2. Import the project in IDE as a Maven project

3. Create a schema in Database

4. Update the database configuration in '../Assesment/src/main/resources/application.properties'

5. Build the project

6. Run the Spring boot application

7. Once server is up and running, open you browser and navigate to the url - 'http://localhost:8080/api/swagger-ui.html'. We can check all our APIs on the browser itself and won't require any other application like PostMan etc.

8. It is suggested to change the property 'spring.jpa.hibernate.ddl-auto' to 'update' from 'create' in application.properties file, so that the data won't get inserted again to the DB when staring the application again and the application can start instantly.

Note: Application may take several minutes to start as thousands of rows get inserted in application on start up (when 'spring.jpa.hibernate.ddl-auto=create')