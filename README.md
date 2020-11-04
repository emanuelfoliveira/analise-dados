# Data Analyzer System

## About

This is a demo project. The idea was build a file processor

It was made using **Java 11**, **Maven**, **Spring Boot**, **Spring Data JPA**, **Lombok**, **Google Guava**, **Mockito**. Database is in memory **H2**.

## Configuration

#### Using Executable Jar

Then you can run the JAR file:
```
bash
$ java -jar target/analise-dados-1.0.jar
```
#### Using Docker (https://hub.docker.com/repository/docker/emanuelfoliveira/analise-dados)

```
$ docker pull emanuelfoliveira/analise-dados:1.0

$ docker images (Find the REPOSITORY emanuelfoliveira/analise-dados and copy the IMAGE_ID)

$ docker run IMAGE_ID
```

#### Using the Maven Plugin

The Spring Boot Maven plugin includes a run goal that can be used to quickly compile and run your application. 
Applications run in an exploded form, as they do in your IDE. 
The following example shows a typical Maven command to run a Spring Boot application:
 
```
bash
$ mvn spring-boot:run
``` 

#### Using Executable Jar

To create an executable jar run:

```
bash
$ mvn clean package
``` 

To run that application, use the java -jar command, as follows:

```
bash
$ java -jar target/analise-dados-1.0.jar
```

To exit the application, press **ctrl-c**.

Tests can be run by executing following command from the root of the project:

```
bash
$ mvn test
```

## Helper Tools

### H2 Database web interface

Go to the web browser and visit `http://localhost:8080/h2-console`

In field **JDBC URL** put 
```
jdbc:h2:mem:mydb
```
## Demo
## ![Execution Example](https://github.com/emanuelfoliveira/analise-dados/blob/master/mvn-run-console.png)

## Dat File Example

Input File - teste.dat
```
001ç1234567891234çPedroç50000
001ç3245678865434çPauloç40000.99
002ç2345675434544345çJose da SilvaçRural
002ç2345675433444345çEduardo PereiraçRural
003ç10ç[1-10-100,2-30-2.50,3-40-3.10]çPedro
003ç08ç[1-34-10,2-33-1.50,3-40-0.10]çPaulo
```

Output File - teste_01112020175430.done.dat
```
Quantidade de clientes no arquivo de entrada:2 
Quantidade de vendedor no arquivo de entrada:2 
ID da venda mais cara:10 
Pior Vendedor:Paulo
```
