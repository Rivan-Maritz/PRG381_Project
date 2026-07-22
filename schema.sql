DROP TABLE IF EXISTS StockIssuance CASCADE;
DROP TABLE IF EXISTS Materials CASCADE;
DROP TABLE IF EXISTS Suppliers CASCADE;
DROP TABLE IF EXISTS Cleaners CASCADE;
DROP TABLE IF EXISTS Users CASCADE;

CREATE TABLE Users (
    UserID      SERIAL PRIMARY KEY,
    Username    VARCHAR(50)  NOT NULL UNIQUE,
    Password    VARCHAR(255) NOT NULL,
    Email       VARCHAR(100) NOT NULL UNIQUE,
    Role        VARCHAR(20)  NOT NULL DEFAULT 'Storekeeper'
                 CHECK (Role IN ('Storekeeper', 'Supervisor'))
);

CREATE TABLE Cleaners (
    CleanerID   SERIAL PRIMARY KEY,
    Name        VARCHAR(100) NOT NULL,
    PhoneNumber VARCHAR(20)
);

CREATE TABLE Suppliers (
    SupplierID   SERIAL PRIMARY KEY,
    SupplierName VARCHAR(100) NOT NULL,
    Contact      VARCHAR(100),
    PhoneNumber  VARCHAR(20),
    Email        VARCHAR(100),
    Address      VARCHAR(255)
);

CREATE TABLE Materials (
    MaterialID   SERIAL PRIMARY KEY,
    SupplierID   INT NOT NULL,
    MaterialName VARCHAR(100) NOT NULL,
    Type         VARCHAR(50),
    Stock        INT NOT NULL DEFAULT 0 CHECK (Stock >= 0),
    ReorderLevel INT NOT NULL DEFAULT 10,
    DateAdded    DATE NOT NULL DEFAULT CURRENT_DATE,
    CONSTRAINT fk_material_supplier
        FOREIGN KEY (SupplierID) REFERENCES Suppliers(SupplierID)
        ON DELETE RESTRICT
);

CREATE TABLE StockIssuance (
    IssuanceID     SERIAL PRIMARY KEY,
    MaterialID     INT NOT NULL,
    CleanerID      INT NOT NULL,
    IssuedBy       INT NOT NULL,
    Quantity       INT NOT NULL CHECK (Quantity > 0),
    DateIssued     TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    RemainingStock INT NOT NULL CHECK (RemainingStock >= 0),
    CONSTRAINT fk_issuance_material
        FOREIGN KEY (MaterialID) REFERENCES Materials(MaterialID)
        ON DELETE RESTRICT,
    CONSTRAINT fk_issuance_cleaner
        FOREIGN KEY (CleanerID) REFERENCES Cleaners(CleanerID)
        ON DELETE RESTRICT,
    CONSTRAINT fk_issuance_user
        FOREIGN KEY (IssuedBy) REFERENCES Users(UserID)
        ON DELETE RESTRICT
);

CREATE INDEX idx_materials_name ON Materials(MaterialName);
CREATE INDEX idx_cleaners_name ON Cleaners(Name);
CREATE INDEX idx_issuance_date ON StockIssuance(DateIssued);

--Test Data
-- All 5 accounts below use the password: Password123
INSERT INTO Users (Username, Password, Email, Role) VALUES
('jdoe',      '$2a$12$kLGi7S1RzjUoTZ4EqYh9A.ZJoyn81qY0R2xWoNdtGSsRKi3FkN3ji', 'jdoe@university.ac.za',      'Supervisor'),
('mkhumalo',  '$2a$12$VHxr1USxPozM.r1ssARmwOeb6Ny3fBzbBLN7JRDRc7jvkxz2sdPfG', 'mkhumalo@university.ac.za',  'Storekeeper'),
('tnkosi',    '$2a$12$nOnUGjC4jwWUYp9HyputTety3GqVGmIRsb6Rsx41v.zwOQkeMuLyG', 'tnkosi@university.ac.za',    'Storekeeper'),
('rmaritz',   '$2a$12$zE58JbQEJ6lnMDyciO/ALuhO70Ejy53Fodg5aIFejeMV8UFjsfsVe', 'rmaritz@university.ac.za',   'Supervisor'),
('lventer',   '$2a$12$nLKDUHUopIAUNckE0sAyVOgcc7xJ1000WY3udYkEGKqdnoNgs2Geq', 'lventer@university.ac.za',   'Storekeeper');

INSERT INTO Cleaners (Name, PhoneNumber) VALUES
('Sipho Dlamini',    '0821234567'),
('Anna Botha',        '0827654321'),
('Thabo Mokoena',     '0731122334'),
('Grace Ndlovu',      '0845566778'),
('Johan Pretorius',   '0768899001'),
('Nomvula Zulu',      '0712233445'),
('Pieter van Wyk',    '0834455667'),
('Lindiwe Sithole',   '0798877665'),
('David Fourie',      '0723344556'),
('Precious Mahlangu',  '0817766554');

INSERT INTO Suppliers (SupplierName, Contact, PhoneNumber, Email, Address) VALUES
('CleanCo Supplies',        'Karen White',    '0119876543', 'sales@cleanco.co.za',        '12 Industrial Rd, Pretoria'),
('HygienePro',               'Steve Naidoo',   '0114567890', 'info@hygienepro.co.za',      '45 Commerce St, Centurion'),
('SparkleChem',               'Amanda Lee',     '0123456789', 'orders@sparklechem.co.za',   '8 Factory Ave, Midrand'),
('PureClean Distributors',    'Vusi Mahlangu',  '0126548732', 'contact@pureclean.co.za',    '77 Main Rd, Pretoria North'),
('EcoWash Solutions',         'Farhana Patel',  '0119988776', 'support@ecowash.co.za',      '3 Green St, Sunnyside'),
('ShineBright Ltd',           'Chris Botha',    '0114433221', 'sales@shinebright.co.za',    '19 Retail Cres, Hatfield'),
('MaxHygiene',                 'Nala Zondi',     '0127766554', 'info@maxhygiene.co.za',      '56 Depot Rd, Silverton'),
('CleanTech Africa',          'Ryan Govender',  '0118899001', 'sales@cleantechafrica.co.za','101 Warehouse Blvd, Rosslyn'),
('FreshLine Supplies',        'Bongani Khumalo','0123322110', 'orders@freshline.co.za',     '22 Supply Ave, Wonderboom'),
('SafeGuard Chemicals',       'Elna Coetzee',   '0119654321', 'info@safeguardchem.co.za',   '64 Industria St, Pretoria West');

-- Stock values below are the CURRENT stock, i.e. already net of the
-- StockIssuance rows further down (each material here had exactly one
-- issuance already happen against it). ReorderLevel is set explicitly so
-- 3 materials (Floor Mop, Dustpan and Brush, Air Freshener Spray) sit at
-- or below their reorder point - so the Low-Stock report/dashboard has
-- something to actually show during a demo instead of coming back empty.
INSERT INTO Materials (SupplierID, MaterialName, Type, Stock, ReorderLevel, DateAdded) VALUES
(1,  'Multi-Surface Cleaner',   'Liquid',      45,  15, '2026-01-10'),
(2,  'Disinfectant Spray',      'Liquid',      27,  15, '2026-01-12'),
(3,  'Toilet Paper (Bulk Pack)','Paper',       180, 50, '2026-01-15'),
(4,  'Floor Mop',               'Equipment',   13,  15, '2026-02-01'),
(5,  'Rubber Gloves (Box)',     'PPE',         30,  15, '2026-02-05'),
(6,  'Glass Cleaner',           'Liquid',      21,  15, '2026-02-10'),
(7,  'Trash Bags (Roll)',       'Consumable',  65,  20, '2026-02-15'),
(8,  'Dustpan and Brush',       'Equipment',   11,  12, '2026-03-01'),
(9,  'Hand Soap Refill',        'Liquid',      52,  20, '2026-03-05'),
(10, 'Air Freshener Spray',     'Liquid',      15,  15, '2026-03-10');

INSERT INTO StockIssuance (MaterialID, CleanerID, IssuedBy, Quantity, DateIssued, RemainingStock) VALUES
(1,  1,  2, 5,  '2026-06-01 08:15:00', 45),
(2,  2,  2, 3,  '2026-06-02 09:30:00', 27),
(3,  3,  3, 20, '2026-06-03 10:00:00', 180),
(4,  4,  3, 2,  '2026-06-04 11:20:00', 13),
(5,  5,  5, 10, '2026-06-05 08:45:00', 30),
(6,  6,  5, 4,  '2026-06-06 13:10:00', 21),
(7,  7,  2, 15, '2026-06-07 14:00:00', 65),
(8,  8,  3, 1,  '2026-06-08 09:50:00', 11),
(9,  9,  5, 8,  '2026-06-09 10:30:00', 52),
(10, 10, 2, 5,  '2026-06-10 15:15:00', 15);
