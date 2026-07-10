DROP TABLE IF EXISTS StockIssuance CASCADE;
DROP TABLE IF EXISTS Materials CASCADE;
DROP TABLE IF EXISTS Suppliers CASCADE;
DROP TABLE IF EXISTS Cleaners CASCADE;

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
INSERT INTO Users (Username, Password, Email, Role) VALUES
('jdoe',      'hashed_pw_1', 'jdoe@university.ac.za',      'Supervisor'),
('mkhumalo',  'hashed_pw_2', 'mkhumalo@university.ac.za',  'Storekeeper'),
('tnkosi',    'hashed_pw_3', 'tnkosi@university.ac.za',    'Storekeeper'),
('rmaritz',   'hashed_pw_4', 'rmaritz@university.ac.za',   'Supervisor'),
('lventer',   'hashed_pw_5', 'lventer@university.ac.za',   'Storekeeper');

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

INSERT INTO Materials (SupplierID, MaterialName, Type, Stock, DateAdded) VALUES
(1,  'Multi-Surface Cleaner',   'Liquid',       50, '2026-01-10'),
(2,  'Disinfectant Spray',       'Liquid',       30, '2026-01-12'),
(3,  'Toilet Paper (Bulk Pack)', 'Paper',       200, '2026-01-15'),
(4,  'Floor Mop',                 'Equipment',    15, '2026-02-01'),
(5,  'Rubber Gloves (Box)',       'PPE',          40, '2026-02-05'),
(6,  'Glass Cleaner',             'Liquid',       25, '2026-02-10'),
(7,  'Trash Bags (Roll)',         'Consumable',   80, '2026-02-15'),
(8,  'Dustpan and Brush',         'Equipment',    12, '2026-03-01'),
(9,  'Hand Soap Refill',          'Liquid',       60, '2026-03-05'),
(10, 'Air Freshener Spray',       'Liquid',       20, '2026-03-10');

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
