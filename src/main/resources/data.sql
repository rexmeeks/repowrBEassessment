CREATE TABLE IF NOT EXISTS customers
(
    id          INT AUTO_INCREMENT PRIMARY KEY,
    email       VARCHAR(100) NOT NULL,
    first_name  VARCHAR(50) NOT NULL,
    last_name   VARCHAR(50) NOT NULL,
    created TIMESTAMP NOT NULL,
    last_updated TIMESTAMP,
);

CREATE TABLE IF NOT EXISTS rooms
(
    id          INT AUTO_INCREMENT PRIMARY KEY,
    owner_id       INT NOT NULL,
    address   VARCHAR(100) NOT NULL,
    city       VARCHAR(50) NOT NULL,
    state  VARCHAR(2) NOT NULL,
    created TIMESTAMP NOT NULL,
    last_updated TIMESTAMP,
    CONSTRAINT fk_owner FOREIGN KEY (owner_id) REFERENCES customers (id),
);

CREATE TABLE IF NOT EXISTS bookings
(
    id          INT AUTO_INCREMENT PRIMARY KEY,
    room_id INT NOT NULL,
    cust_id INT NOT NULL,
    start_date DATE NOT NULL,
    end_date DATE NOT NULL,
    created TIMESTAMP NOT NULL,
    last_updated TIMESTAMP,
    CONSTRAINT fk_room_id FOREIGN KEY (room_id) REFERENCES rooms (id),
    CONSTRAINT fk_cust_id FOREIGN KEY (cust_id) REFERENCES customers (id),
);