# metrics-service project

This project uses Quarkus, the Supersonic Subatomic Java Framework.

If you want to learn more about Quarkus, please visit its website: https://quarkus.io/ .

## Project Overview
This project allows for the creation of metrics, adding values to created metrics, and returning select statistictics on the metrics.

## Project Requirements

The project requires the following setup:
 - Java 8
 - Maven

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:
```shell script
./mvnw compile quarkus:dev
```

## Packaging and running the application

The application can be packaged using:
```shell script
./mvnw package
```
It produces the `quarkus-run.jar` file in the `target/quarkus-app/` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `target/quarkus-app/lib/` directory.

If you want to build an _über-jar_, execute the following command:
```shell script
./mvnw package -Dquarkus.package.type=uber-jar
```

The application is now runnable using `java -jar target/quarkus-app/quarkus-run.jar`.

## Creating a native executable

You can create a native executable using: 
```shell script
./mvnw package -Pnative
```

Or, if you don't have GraalVM installed, you can run the native executable build in a container using: 
```shell script
./mvnw package -Pnative -Dquarkus.native.container-build=true
```

You can then execute your native executable with: `./target/metrics-service-1.0.0-SNAPSHOT-runner`

If you want to learn more about building native executables, please consult https://quarkus.io/guides/maven-tooling.html.

## Metrics API Definition

Start up the service locally and the API Swagger definition can be found at  http://localhost:8080/q/swagger-ui/#/

## Notes to the reviewers

-- Quarkus was new to the developer of this service. It was chosen for two primary reasons.  First was it seemed to fit the requirements with apparent concern on space and time complexity and Quarkus advertises speed and minimal memory impact.  The second reason is the developer has worked with both Spring and Micronaut and took this as an opportunity to learn something new with the time spent. 

-- To fully make the most out of Quarkus with regards to space and time complexity, using a native executable is suggested.

-- Time constraints prevented the following on the demo:
 - Full unit testing.  The project was TDD on the integration level.  Time did not allow to go back and fill in unit tests.
 - Figuring out the space and time complexity.  The developer has not dealt with this since college and ran out of time to revisit and apply the concepts.
 - The developer was hoping to set the project up to test with and learn the GraalVM and making native executables, but this will have be done at a later date.
