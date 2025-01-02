CREATE TABLE weather_requests (
    id SERIAL PRIMARY KEY,
    user_id INT NOT NULL,
    postal_code VARCHAR(10) NOT NULL,
    temperature FLOAT,
    humidity FLOAT,
    weather_conditions VARCHAR(255),
    request_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users (id)
);