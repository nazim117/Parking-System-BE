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

## Endpoints

- #### Get appointments paginated (<span style="color:green">GET</span>)
    *Request:*
    ```
    api/appointments/pages
    ?page=int
    &pageSize=int
    ```
    *Response:*
    ```json
    {
        "content": [
            {
            "id": 5,
            "datetime": "2024-04-11T11:16:00",
            "guest": "Danila Solovenko",
            "employee": "Cooler Solovenko",
            "guestEmail": "Sol@example.com",
            "employeeEmail": "Cool@example.com",
            "description": "smth",
            "carPlateNumber": "DEF451"
            }
        ],
        "pageable": {
            "pageNumber": 1,
            "pageSize": 1,
            "sort": {
                "empty": true,
                "sorted": false,
                "unsorted": true
            },
            "offset": 1,
            "paged": true,
            "unpaged": false
        },
        "last": false,
        "totalElements": 4,
        "totalPages": 4,
        "size": 1,
        "number": 1,
        "sort": {
            "empty": true,
            "sorted": false,
            "unsorted": true
        },
        "first": false,
        "numberOfElements": 1,
        "empty": false
    }
    ```

- #### Get appointments for a specific month (<span style="color:green">GET</span>)
    *Request:*
    ```
    api/appointments/calendar_overview
    ?year=int
    &month=int
    ```
    *Response:*
    ```json
    [
        {
            "id": 5,
            "datetime": "2024-04-11T11:16:00",
            "guest": "Danila Solovenko",
            "employee": "Cooler Solovenko",
            "guestEmail": "Sol@example.com",
            "employeeEmail": "Cool@example.com",
            "description": "smth",
            "carPlateNumber": "DEF451"
        }
    ]
    ```
  
- #### Get a specific appointment (<span style="color:green">GET</span>)
  *Request:*
    ```
    api/appointments/5
    ```
  *Response:*
    ```json
    {
        "id": 3,
        "datetime": "2024-04-11T11:15:00",
        "guest": "Eva Garcia",
        "employee": "Michael Clark",
        "guestEmail": "eva@example.com",
        "employeeEmail": "michael@example.com",
        "description": "Tire rotation",
        "carPlateNumber": "DEF456"
    }
    ```

- #### Create an appointment (<span style="color:yellow">POST</span>)
  *Request:*
    ```
    api/appointments
    ```
  *Response:*
    ```json
    6
    ```
  
- #### Edit an appointment (<span style="color:blue">PUT</span>)
  *Request:*
    ```
    api/appointments/5
    ```
  *Response:*
    ```json
    
    ```

- #### Delete an appointment (<span style="color:red">DELETE</span>)
  *Request:*
    ```
    api/appointments/5
    ```
  *Response:*
    ```json
    
    ```