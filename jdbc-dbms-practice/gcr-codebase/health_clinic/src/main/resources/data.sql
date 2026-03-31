INSERT INTO specialties (name, is_active) VALUES
('General Medicine', TRUE),
('Cardiology', TRUE),
('Dermatology', TRUE),
('Orthopedics', TRUE),
('Pediatrics', TRUE)
ON DUPLICATE KEY UPDATE name = VALUES(name);
