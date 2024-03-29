# safety-net-alerte-hexagonal-architecture
Application of information management of the inhabitants for the rescue services of the city.
This app uses Java to read data from a json file and send informations after processing.
This is a refactoring application code of another project, from a layer architecture to an hexagonal architecture.
***
## Getting Started
These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.
***
### Prerequisites
What things you need to install the software and how to install them.

    - Java 21.0.1  
    - Maven 3.6.3  
    - Spring 3.2.4  

### Running Application
Post installation of Java and Maven. 
Import the project in you favorite IDE.

For the moment, no DataBase configuration is required, cause the application read a JsonFile in the resources package.

### Testing Application
The application includes unit and integration tests. To run them, run the following command:

`mvn test`
## Architecture explains
They are two modules:

`domain` 
`infrastructure`

# Domain
 - This is the heart of the application.

**They are no framework, and no hard dependencies here**
This module is only pure JAVA code writing.

An Application Programming Interface package to link the controller layer of the infrastructure to the domain service.
An Service Provider Interface package to link the repository layer of the infrastructure to the domain service.
Models and Data Transfer Objects are managed in the domain.
**ALL** business treatments of the application are performing here, in the service package.

 - The test cycle.

The domain is completely autonomous for testing.
In the test package they are two subPackages:

`service`
`stub`

The service package store all test clases.
The stub package stores a fake Database and all fake repositories to mock the infrastructure repository layer. 

# Infrastructure
 - This is the environment layer of the application.

Write with Java in SpringBoot framework.
This module will inject data from Client into the domain and obtain processed data from the domain to be sent out of the application by the Controller layer.

They are three subpackages:

`configuration`
`controller`
`data_reader`

The configuration package stores configurations classes. Notament the class to scan classes annotations in the domain module to create spring beans.
The controller package stores all classes to manage endPoints, Data Transfer Objects and mapper classes, to display formated data. Implement the domain's Application Programming Interfaces.
The data_reader package stores the JsonReader, repository classes who implement the domain's Service Provider Interfaces.

The data.json is in the resources package.

 - The test cycle.

In the test package they are two subpackages:

`controller`
`data_reader`

The controller package stores all integration test classes. Tests use the real Service clases of the domain, with the domain's Stub.
The data_Reader is completely autonomous. The reader test class use the dataStub.json in the test/resources package. Repository test classes use the same file to mock data.
