# Java Exercise 5

Convert the previously developmed command line app to run as a server
Re-use the database development using Spring Data, JPA and Hibernate
Implement a RestController to server a RESTful endpoint

The project is an evolution from exercise 4. The first commit is a clone of that project, with the next commit illustrating the changes made.

## Running the server

Run the server in any one of the ways listed below. Once the application is started it will keep running until Ctrl-C is pressed.

### Maven

```
mvn spring-boot:run
```

### Java on the command line

From the command line, after running the Maven `install` command;

```
mvn install
```

Then run the jar file, in the target directory, directly.
```
java -jar target\java-exercise-5-0.0.1-SNAPSHOT.jar
```

### From Eclise / STS

Right-click the application class and select Run As -> Spring Boot Application.

## Accessing the findAll plain text resource

To display the database content in plain text, execute the following curl command from the command line once the server is running;

```
curl localhost:8080/command/findAllText
```