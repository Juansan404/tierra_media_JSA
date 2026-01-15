-- ============================================
-- SQL para crear la base de datos Tierra Media
-- PostgreSQL
-- ============================================

-- Crear la base de datos (ejecutar como superusuario si es necesario)
-- CREATE DATABASE tierra_media;

-- Conectar a la base de datos
-- \c tierra_media

-- ============================================
-- ELIMINAR TABLAS SI EXISTEN (orden importante por FK)
-- ============================================
DROP TABLE IF EXISTS Personaje CASCADE;
DROP TABLE IF EXISTS Raza CASCADE;

-- ============================================
-- CREAR TABLA RAZA
-- ============================================
CREATE TABLE Raza (
    id BIGSERIAL PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    habilidad_especial VARCHAR(150),
    reino_origen VARCHAR(100),
    esperanza_vida INTEGER
);

-- ============================================
-- CREAR TABLA PERSONAJE
-- ============================================
CREATE TABLE Personaje (
    id BIGSERIAL PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    edad INTEGER,
    arma_principal VARCHAR(100),
    nivel_poder DOUBLE PRECISION,
    fecha_aparicion DATE,
    raza_id BIGINT NOT NULL,
    CONSTRAINT fk_personaje_raza FOREIGN KEY (raza_id) REFERENCES Raza(id)
);

-- ============================================
-- INSERTAR RAZAS
-- ============================================
INSERT INTO Raza (nombre, habilidad_especial, reino_origen, esperanza_vida) VALUES
('Hobbit', 'Sigilo y resistencia', 'La Comarca', 100),
('Elfo', 'Inmortalidad y visión aguda', 'Rivendel', 999999),
('Humano', 'Adaptabilidad y coraje', 'Gondor', 80),
('Enano', 'Fuerza y artesanía', 'Erebor', 250),
('Mago', 'Magia y sabiduría ancestral', 'Valinor', 999999);

-- ============================================
-- INSERTAR PERSONAJES
-- ============================================
-- Nota: Las fechas muy antiguas se han ajustado para compatibilidad con PostgreSQL
-- raza_id: 1=Hobbit, 2=Elfo, 3=Humano, 4=Enano, 5=Mago

INSERT INTO Personaje (nombre, edad, arma_principal, nivel_poder, fecha_aparicion, raza_id) VALUES
-- Hobbits
('Frodo Bolsón', 50, 'Dardo', 45.0, '2968-09-22', 1),
('Sam Gamyi', 38, 'Dardo', 40.0, '2980-04-06', 1),

-- Elfos
('Legolas', 2931, 'Arco élfico', 80.0, '0087-01-01', 2),
('Galadriel', 7000, 'Nenya', 95.0, '0001-01-01', 2),

-- Humanos
('Aragorn', 87, 'Andúril', 85.0, '2931-03-01', 3),
('Boromir', 41, 'Espada', 70.0, '2978-01-01', 3),

-- Enanos
('Gimli', 139, 'Hacha de batalla', 75.0, '2879-01-01', 4),

-- Magos
('Gandalf el Gris', 2019, 'Glamdring', 98.0, '1000-01-01', 5);

-- ============================================
-- CONSULTAS DE VERIFICACIÓN
-- ============================================

-- Ver todas las razas
-- SELECT * FROM Raza;

-- Ver todos los personajes con su raza
-- SELECT p.*, r.nombre as raza FROM Personaje p JOIN Raza r ON p.raza_id = r.id;

-- Top 5 más poderosos
-- SELECT nombre, nivel_poder FROM Personaje ORDER BY nivel_poder DESC LIMIT 5;
