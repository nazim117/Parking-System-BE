# DEVELOPMENT SETUP

## Data base setup

1) Download an image from dockerhub:

`docker run --name parking_system -e MYSQL_DATABASE=parking_system_db_dev -e MYSQL_ROOT_PASSWORD=*YOUR_PASSWORD!* -p 3390:3306 -d mysql`

2) Insert your password in application-dev.properties

```properties
spring.datasource.url=jdbc:mysql://localhost:3390/parking_system_db_dev
spring.datasource.username=root
spring.datasource.password=*YOUR_PASSWORD!*
spring.jpa.hibernate.ddl-auto=update
```

3) change active profile to dev in app configuration