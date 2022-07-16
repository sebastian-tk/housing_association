
CREATE TABLE IF NOT EXISTS users (
    id INTEGER PRIMARY KEY AUTO_INCREMENT UNIQUE ,
    email VARCHAR(50) NOT NULL UNIQUE ,
    password VARCHAR(100) NOT NULL,
    role ENUM ('ADMIN','USER'),
    username VARCHAR(50) NOT NULL UNIQUE
    );

CREATE TABLE IF NOT EXISTS buildings (
    id INTEGER PRIMARY KEY AUTO_INCREMENT UNIQUE ,
    area_m2 INTEGER NOT NULL,
    name VARCHAR(100) NOT NULL UNIQUE,
    number VARCHAR(7) NOT NULL,
    number_storeys INTEGER NOT NULL CHECK (number_storeys > 0),
    flats_per_storey INTEGER NOT NULL CHECK (flats_per_storey > 0),
    staircase INTEGER NOT NULL CHECK (staircase > 0),
    street VARCHAR(100) NOT NULL,
    town VARCHAR(100) NOT NULL,
    year_construction INTEGER NOT NULL CHECK (year_construction > 0),
    zip_code VARCHAR(6) NOT NULL
    );

CREATE TABLE IF NOT EXISTS flats (
    id INTEGER PRIMARY KEY AUTO_INCREMENT UNIQUE ,
    area_m2 INTEGER NOT NULL,
    number INTEGER NOT NULL CHECK (number>0),
    storey INTEGER NOT NULL CHECK (storey>0),
    nr_staircase INTEGER NOT NULL CHECK (nr_staircase>0),
    type_use ENUM ('RENT', 'PROPERTY'),
    building_id INTEGER,
    CONSTRAINT FK_FlatBuilding FOREIGN KEY (building_id) REFERENCES buildings (id) ON DELETE CASCADE
    );

CREATE TABLE IF NOT EXISTS rates (
    id INTEGER PRIMARY KEY AUTO_INCREMENT UNIQUE ,
    administration_m2 DECIMAL (5,2) NOT NULL,
    cold_water_per DECIMAL (5,2) NOT NULL,
    exploitation_m2 DECIMAL (5,2) NOT NULL,
    rent_rent_m2 DECIMAL (5,2) NOT NULL,
    rent_property_m2 DECIMAL (5,2) NOT NULL,
    heating_m2 DECIMAL (5,2) NOT NULL,
    renovation_found_m2 DECIMAL (5,2) NOT NULL,
    rubbish_per DECIMAL (5,2) NOT NULL,
    sewage_per DECIMAL (5,2) NOT NULL,
    warm_water_per DECIMAL (5,2) NOT NULL
);

CREATE TABLE IF NOT EXISTS fees (
    id INTEGER PRIMARY KEY AUTO_INCREMENT UNIQUE ,
    administration DECIMAL(5,2) NOT NULL,
    cold_water DECIMAL(5,2) NOT NULL,
    exploitation DECIMAL(5,2) NOT NULL,
    heating DECIMAL(5,2) NOT NULL,
    renovation_found DECIMAL(5,2) NOT NULL,
    rent DECIMAL(5,2) NOT NULL,
    rubbish DECIMAL(5,2) NOT NULL,
    sewage DECIMAL(5,2) NOT NULL,
    warm_water DECIMAL(5,2) NOT NULL,
    total DECIMAL(7,2) NOT NULL
);

CREATE TABLE IF NOT EXISTS contracts (
    id INTEGER PRIMARY KEY AUTO_INCREMENT UNIQUE ,
    amount_people INTEGER NOT NULL CHECK (amount_people>0),
    finish_time DATETIME,
    start_time DATETIME,
    type ENUM('LEASE_AGREEMENT','OWNERSHIP_AGREEMENT'),
    flat_id INTEGER,
    user_id INTEGER,
    fee_id INTEGER,

    CONSTRAINT FK_ContractFlat FOREIGN KEY (flat_id) REFERENCES flats (id) ON DELETE SET NULL,
    CONSTRAINT FK_ContractUser FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE SET NULL,
    CONSTRAINT FK_ContractFee FOREIGN KEY (fee_id) REFERENCES fees (id) ON DELETE SET NULL
);