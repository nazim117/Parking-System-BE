CREATE TABLE appointments (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    datetime DATETIME,
    guest VARCHAR(255),
    employee VARCHAR(255),
    guest_email VARCHAR(255),
    employee_email VARCHAR(255),
    description TEXT,
    carPlateNumber VARCHAR(20)
);
