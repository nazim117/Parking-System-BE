CREATE TABLE if not exists employees (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    employee_name VARCHAR(255),
    employee_email VARCHAR(255)
);

CREATE TABLE if not exists appointments (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    datetime DATETIME,
    guest VARCHAR(255),
    employee_id BIGINT,
    guest_email VARCHAR(255),
    description TEXT,
    car_plate_number VARCHAR(20),
    FOREIGN KEY (employee_id) REFERENCES employees(id)
);
