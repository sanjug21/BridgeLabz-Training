CREATE TABLE IF NOT EXISTS patients (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    full_name VARCHAR(100) NOT NULL,
    dob DATE NOT NULL,
    phone VARCHAR(20) NOT NULL,
    email VARCHAR(120) NOT NULL,
    address VARCHAR(255),
    blood_group VARCHAR(5),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    UNIQUE KEY uk_patients_phone (phone),
    UNIQUE KEY uk_patients_email (email)
);

CREATE TABLE IF NOT EXISTS specialties (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    is_active BOOLEAN NOT NULL DEFAULT TRUE,
    UNIQUE KEY uk_specialties_name (name)
);

CREATE TABLE IF NOT EXISTS doctors (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    full_name VARCHAR(100) NOT NULL,
    phone VARCHAR(20) NOT NULL,
    email VARCHAR(120) NOT NULL,
    consultation_fee DECIMAL(10, 2) NOT NULL,
    specialty_id BIGINT NOT NULL,
    is_active BOOLEAN NOT NULL DEFAULT TRUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    UNIQUE KEY uk_doctors_phone (phone),
    UNIQUE KEY uk_doctors_email (email),
    CONSTRAINT fk_doctors_specialty FOREIGN KEY (specialty_id) REFERENCES specialties (id)
);

CREATE TABLE IF NOT EXISTS appointments (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    patient_id BIGINT NOT NULL,
    doctor_id BIGINT NOT NULL,
    appointment_date DATE NOT NULL,
    appointment_time TIME NOT NULL,
    status VARCHAR(20) NOT NULL,
    notes VARCHAR(255),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_appointments_patient FOREIGN KEY (patient_id) REFERENCES patients (id),
    CONSTRAINT fk_appointments_doctor FOREIGN KEY (doctor_id) REFERENCES doctors (id)
);

SET @idx_exists := (
    SELECT COUNT(1)
    FROM information_schema.statistics
    WHERE table_schema = DATABASE()
      AND table_name = 'appointments'
      AND index_name = 'idx_appointments_doctor_date'
);
SET @idx_sql := IF(
    @idx_exists = 0,
    'CREATE INDEX idx_appointments_doctor_date ON appointments (doctor_id, appointment_date)',
    'SELECT 1'
);
PREPARE idx_stmt FROM @idx_sql;
EXECUTE idx_stmt;
DEALLOCATE PREPARE idx_stmt;

CREATE TABLE IF NOT EXISTS appointment_audit (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    appointment_id BIGINT NOT NULL,
    action VARCHAR(20) NOT NULL,
    reason VARCHAR(255),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_appointment_audit_appointment FOREIGN KEY (appointment_id) REFERENCES appointments (id)
);

CREATE TABLE IF NOT EXISTS visits (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    appointment_id BIGINT NOT NULL,
    patient_id BIGINT NOT NULL,
    doctor_id BIGINT NOT NULL,
    visit_date DATE NOT NULL,
    diagnosis VARCHAR(255),
    notes VARCHAR(500),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_visits_appointment FOREIGN KEY (appointment_id) REFERENCES appointments (id),
    CONSTRAINT fk_visits_patient FOREIGN KEY (patient_id) REFERENCES patients (id),
    CONSTRAINT fk_visits_doctor FOREIGN KEY (doctor_id) REFERENCES doctors (id)
);

CREATE TABLE IF NOT EXISTS prescriptions (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    visit_id BIGINT NOT NULL,
    medicine_name VARCHAR(120) NOT NULL,
    dosage VARCHAR(50),
    duration_days INT,
    instructions VARCHAR(255),
    CONSTRAINT fk_prescriptions_visit FOREIGN KEY (visit_id) REFERENCES visits (id)
);

CREATE TABLE IF NOT EXISTS bills (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    visit_id BIGINT NOT NULL,
    total_amount DECIMAL(10, 2) NOT NULL,
    payment_status VARCHAR(20) NOT NULL,
    payment_date DATE,
    payment_mode VARCHAR(30),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_bills_visit FOREIGN KEY (visit_id) REFERENCES visits (id)
);

CREATE TABLE IF NOT EXISTS payment_transactions (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    bill_id BIGINT NOT NULL,
    amount DECIMAL(10, 2) NOT NULL,
    payment_date DATE NOT NULL,
    payment_mode VARCHAR(30) NOT NULL,
    reference_no VARCHAR(60),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_payment_transactions_bill FOREIGN KEY (bill_id) REFERENCES bills (id)
);

CREATE TABLE IF NOT EXISTS audit_log (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    table_name VARCHAR(50) NOT NULL,
    action VARCHAR(10) NOT NULL,
    row_id BIGINT NOT NULL,
    changed_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

DROP TRIGGER IF EXISTS trg_patients_insert;
DROP TRIGGER IF EXISTS trg_patients_update;
DROP TRIGGER IF EXISTS trg_patients_delete;
DROP TRIGGER IF EXISTS trg_doctors_insert;
DROP TRIGGER IF EXISTS trg_doctors_update;
DROP TRIGGER IF EXISTS trg_doctors_delete;
DROP TRIGGER IF EXISTS trg_appointments_insert;
DROP TRIGGER IF EXISTS trg_appointments_update;
DROP TRIGGER IF EXISTS trg_appointments_delete;

CREATE TRIGGER trg_patients_insert
AFTER INSERT ON patients
FOR EACH ROW
INSERT INTO audit_log (table_name, action, row_id) VALUES ('patients', 'INSERT', NEW.id);

CREATE TRIGGER trg_patients_update
AFTER UPDATE ON patients
FOR EACH ROW
INSERT INTO audit_log (table_name, action, row_id) VALUES ('patients', 'UPDATE', NEW.id);

CREATE TRIGGER trg_patients_delete
AFTER DELETE ON patients
FOR EACH ROW
INSERT INTO audit_log (table_name, action, row_id) VALUES ('patients', 'DELETE', OLD.id);

CREATE TRIGGER trg_doctors_insert
AFTER INSERT ON doctors
FOR EACH ROW
INSERT INTO audit_log (table_name, action, row_id) VALUES ('doctors', 'INSERT', NEW.id);

CREATE TRIGGER trg_doctors_update
AFTER UPDATE ON doctors
FOR EACH ROW
INSERT INTO audit_log (table_name, action, row_id) VALUES ('doctors', 'UPDATE', NEW.id);

CREATE TRIGGER trg_doctors_delete
AFTER DELETE ON doctors
FOR EACH ROW
INSERT INTO audit_log (table_name, action, row_id) VALUES ('doctors', 'DELETE', OLD.id);

CREATE TRIGGER trg_appointments_insert
AFTER INSERT ON appointments
FOR EACH ROW
INSERT INTO audit_log (table_name, action, row_id) VALUES ('appointments', 'INSERT', NEW.id);

CREATE TRIGGER trg_appointments_update
AFTER UPDATE ON appointments
FOR EACH ROW
INSERT INTO audit_log (table_name, action, row_id) VALUES ('appointments', 'UPDATE', NEW.id);

CREATE TRIGGER trg_appointments_delete
AFTER DELETE ON appointments
FOR EACH ROW
INSERT INTO audit_log (table_name, action, row_id) VALUES ('appointments', 'DELETE', OLD.id);
