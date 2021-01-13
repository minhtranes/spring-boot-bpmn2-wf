# Overview
The application use following technology stack:
* Spring Boot
* Spring Data JPA (Hikari CP)
* Activiti

# Features
The application simply expose the REST APIs for reading the process instance information. The historic data was tracked from the real process engines (at scale) in PostgresDB. The following API groups are targed to be exposed.
* Query process information
* Query process instance information
* Query process instance detail
