-- Inserting apartments
INSERT INTO apartments (apartment_number) VALUES
('1'), ('2'), ('3'), ('4'), ('5'), ('6'), ('7'), ('8'), ('9'), ('10'),
('11'), ('12'), ('13'), ('14'), ('15'), ('16'), ('17'), ('18'), ('19'), ('20');

-- Inserting rooms for apartments 1-16 (Bronze type)
INSERT INTO rooms (room_number, type, status, apartment_id) VALUES
('1', 'BRONZE', 'AVAILABLE', 1), ('2', 'BRONZE', 'AVAILABLE', 1), ('3', 'BRONZE', 'AVAILABLE', 1), ('4', 'BRONZE', 'AVAILABLE', 1), ('5', 'BRONZE', 'AVAILABLE', 1), ('6', 'BRONZE', 'AVAILABLE', 1),
('1', 'BRONZE', 'AVAILABLE', 2), ('2', 'BRONZE', 'AVAILABLE', 2), ('3', 'BRONZE', 'AVAILABLE', 2), ('4', 'BRONZE', 'AVAILABLE', 2), ('5', 'BRONZE', 'AVAILABLE', 2), ('6', 'BRONZE', 'AVAILABLE', 2),
('1', 'BRONZE', 'AVAILABLE', 3), ('2', 'BRONZE', 'AVAILABLE', 3), ('3', 'BRONZE', 'AVAILABLE', 3), ('4', 'BRONZE', 'AVAILABLE', 3), ('5', 'BRONZE', 'AVAILABLE', 3), ('6', 'BRONZE', 'AVAILABLE', 3),
('1', 'BRONZE', 'AVAILABLE', 4), ('2', 'BRONZE', 'AVAILABLE', 4), ('3', 'BRONZE', 'AVAILABLE', 4), ('4', 'BRONZE', 'AVAILABLE', 4), ('5', 'BRONZE', 'AVAILABLE', 4), ('6', 'BRONZE', 'AVAILABLE', 4),
('1', 'BRONZE', 'AVAILABLE', 5), ('2', 'BRONZE', 'AVAILABLE', 5), ('3', 'BRONZE', 'AVAILABLE', 5), ('4', 'BRONZE', 'AVAILABLE', 5), ('5', 'BRONZE', 'AVAILABLE', 5), ('6', 'BRONZE', 'AVAILABLE', 5),
('1', 'BRONZE', 'AVAILABLE', 6), ('2', 'BRONZE', 'AVAILABLE', 6), ('3', 'BRONZE', 'AVAILABLE', 6), ('4', 'BRONZE', 'AVAILABLE', 6), ('5', 'BRONZE', 'AVAILABLE', 6), ('6', 'BRONZE', 'AVAILABLE', 6),
('1', 'BRONZE', 'AVAILABLE', 7), ('2', 'BRONZE', 'AVAILABLE', 7), ('3', 'BRONZE', 'AVAILABLE', 7), ('4', 'BRONZE', 'AVAILABLE', 7), ('5', 'BRONZE', 'AVAILABLE', 7), ('6', 'BRONZE', 'AVAILABLE', 7),
('1', 'BRONZE', 'AVAILABLE', 8), ('2', 'BRONZE', 'AVAILABLE', 8), ('3', 'BRONZE', 'AVAILABLE', 8), ('4', 'BRONZE', 'AVAILABLE', 8), ('5', 'BRONZE', 'AVAILABLE', 8), ('6', 'BRONZE', 'AVAILABLE', 8),
('1', 'BRONZE', 'AVAILABLE', 9), ('2', 'BRONZE', 'AVAILABLE', 9), ('3', 'BRONZE', 'AVAILABLE', 9), ('4', 'BRONZE', 'AVAILABLE', 9), ('5', 'BRONZE', 'AVAILABLE', 9), ('6', 'BRONZE', 'AVAILABLE', 9),
('1', 'BRONZE', 'AVAILABLE', 10), ('2', 'BRONZE', 'AVAILABLE', 10), ('3', 'BRONZE', 'AVAILABLE', 10), ('4', 'BRONZE', 'AVAILABLE', 10), ('5', 'BRONZE', 'AVAILABLE', 10), ('6', 'BRONZE', 'AVAILABLE', 10),
('1', 'BRONZE', 'AVAILABLE', 11), ('2', 'BRONZE', 'AVAILABLE', 11), ('3', 'BRONZE', 'AVAILABLE', 11), ('4', 'BRONZE', 'AVAILABLE', 11), ('5', 'BRONZE', 'AVAILABLE', 11), ('6', 'BRONZE', 'AVAILABLE', 11),
('1', 'BRONZE', 'AVAILABLE', 12), ('2', 'BRONZE', 'AVAILABLE', 12), ('3', 'BRONZE', 'AVAILABLE', 12), ('4', 'BRONZE', 'AVAILABLE', 12), ('5', 'BRONZE', 'AVAILABLE', 12), ('6', 'BRONZE', 'AVAILABLE', 12),
('1', 'BRONZE', 'AVAILABLE', 13), ('2', 'BRONZE', 'AVAILABLE', 13), ('3', 'BRONZE', 'AVAILABLE', 13), ('4', 'BRONZE', 'AVAILABLE', 13), ('5', 'BRONZE', 'AVAILABLE', 13), ('6', 'BRONZE', 'AVAILABLE', 13),
('1', 'BRONZE', 'AVAILABLE', 14), ('2', 'BRONZE', 'AVAILABLE', 14), ('3', 'BRONZE', 'AVAILABLE', 14), ('4', 'BRONZE', 'AVAILABLE', 14), ('5', 'BRONZE', 'AVAILABLE', 14), ('6', 'BRONZE', 'AVAILABLE', 14),
('1', 'BRONZE', 'AVAILABLE', 15), ('2', 'BRONZE', 'AVAILABLE', 15), ('3', 'BRONZE', 'AVAILABLE', 15), ('4', 'BRONZE', 'AVAILABLE', 15), ('5', 'BRONZE', 'AVAILABLE', 15), ('6', 'BRONZE', 'AVAILABLE', 15),
('1', 'BRONZE', 'AVAILABLE', 16), ('2', 'BRONZE', 'AVAILABLE', 16), ('3', 'BRONZE', 'AVAILABLE', 16), ('4', 'BRONZE', 'AVAILABLE', 16), ('5', 'BRONZE', 'AVAILABLE', 16), ('6', 'BRONZE', 'AVAILABLE', 16);

-- Inserting rooms for apartments 17-19 (Silver type)
INSERT INTO rooms (room_number, type, status, apartment_id) VALUES
('1', 'SILVER', 'AVAILABLE', 17), ('2', 'SILVER', 'AVAILABLE', 17), ('3', 'SILVER', 'AVAILABLE', 17), ('4', 'SILVER', 'AVAILABLE', 17), ('5', 'SILVER', 'AVAILABLE', 17), ('6', 'SILVER', 'AVAILABLE', 17),
('1', 'SILVER', 'AVAILABLE', 18), ('2', 'SILVER', 'AVAILABLE', 18), ('3', 'SILVER', 'AVAILABLE', 18), ('4', 'SILVER', 'AVAILABLE', 18), ('5', 'SILVER', 'AVAILABLE', 18), ('6', 'SILVER', 'AVAILABLE', 18),
('1', 'SILVER', 'AVAILABLE', 19), ('2', 'SILVER', 'AVAILABLE', 19), ('3', 'SILVER', 'AVAILABLE', 19), ('4', 'SILVER', 'AVAILABLE', 19), ('5', 'SILVER', 'AVAILABLE', 19), ('6', 'SILVER', 'AVAILABLE', 19);

-- Inserting rooms for apartment 20 (Gold type)
INSERT INTO rooms (room_number, type, status, apartment_id) VALUES
('1', 'GOLD', 'AVAILABLE', 20), ('2', 'GOLD', 'AVAILABLE', 20), ('3', 'GOLD', 'AVAILABLE', 20), ('4', 'GOLD', 'AVAILABLE', 20), ('5', 'GOLD', 'AVAILABLE', 20), ('6', 'GOLD', 'AVAILABLE', 20);
