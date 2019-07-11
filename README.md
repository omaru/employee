[![Code Coverage](https://codecov.io/gh/omaru/employee/branch/master/graph/badge.svg)](https://codecov.io/gh/omaru/employee)
[![Build Status](https://travis-ci.org/omaru/employee.svg?branch=master)](https://travis-ci.org/omaru/employee)
[![Maintainability](https://api.codeclimate.com/v1/badges/fc64f6229433601f17c3/maintainability)](https://codeclimate.com/github/omaru/employee/maintainability)
# Employees Rest Service
## Usage
- Development
```bash
./gradlew  bootRunDev
```
- Package
   -  jar file will be contained inside `$PROJECT_HOME/build/libs` folder.
   - once packaged execute with `java -jar $PROJECTNAME.jar`.
```bash
./gradlew  build
``` 
#### Data Ingestion
To ingest data provide the param `-i` followed by the `sql` file `path`
`java -jar $PROJECTNAME.jar -i /sample/path.sql`
If no option is provided , data is populated from `sample-data.sql` 
located in  `resources` folder.
#### CRUD Operations
##### Get Employees
`GET /employee/`
##### Get Single Employee
`GET /employee/{id}`
##### Delete Employee
`DELETE /employee/{id}`
for this service please provide `basic auth` with username `user` and password `password`, may be changed via
`application.properties` file.
##### Create Employee
`POST /employee/`
##### Update Employee
`PUT /employee/{id}`

