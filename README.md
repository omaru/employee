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
#### Tooling
- gradle
- spring (test,security,rest,hateoas,data,jpa)
- commons-cli
- ibatis
- h2
- assertj
- lombok
- travis
- codecov
- codeclimate

#### Data Ingestion
To ingest data provide the param `-i` followed by the `sql` file `path`
`java -jar $PROJECTNAME.jar -i /sample/path.sql`
If no option is provided , data is populated from `sample-data.sql` 
located in  `resources` folder.
![data](https://user-images.githubusercontent.com/877539/61019260-c3dbfd00-a356-11e9-995d-313b76ea051b.gif)
#### CRUD Operations
##### Get Employees
![get_all](https://user-images.githubusercontent.com/877539/61017285-d9015d80-a34f-11e9-9c3d-331d615fb35b.gif)
##### Get Single Employee
![get_single](https://user-images.githubusercontent.com/877539/61017289-dacb2100-a34f-11e9-91e3-ef7ef22b8c45.gif)
##### Delete Employee
![delete_ok](https://user-images.githubusercontent.com/877539/61017279-d56dd680-a34f-11e9-96b3-3b537298fb1a.gif)
for this service please provide `basic auth` with username `user` and password `password`, may be changed via
`application.properties` file.
##### Create Employee
![create](https://user-images.githubusercontent.com/877539/61017273-cf77f580-a34f-11e9-9eeb-0d9c80d0f204.gif)
##### Update Employee
![update](https://user-images.githubusercontent.com/877539/61017291-dbfc4e00-a34f-11e9-931f-e670e9221b58.gif)
##### Get Department
![department_get](https://user-images.githubusercontent.com/877539/61017282-d7d03080-a34f-11e9-9c40-5333dd45227a.gif)
