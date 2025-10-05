
CREATE DATABASE IF NOT EXISTS cine_db
CHARACTER SET utf8mb4 
COLLATE utf8mb4_unicode_ci;

USE cine_db; 

CREATE TABLE IF NOT EXISTS peliculas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(150) NOT NULL,
    director VARCHAR(50) NOT NULL,
    genero ENUM('Comedia','Drama','Accion','Fantasia','Musical','Romance') NOT NULL,
    anio INT NOT NULL,
    duracion INT NOT NULL,
    UNIQUE KEY uk_titulo_anio (titulo, anio)
) CHARACTER SET utf8mb4 
  COLLATE utf8mb4_unicode_ci;

INSERT INTO peliculas (titulo, director, genero, anio, duracion) VALUES
('Amelie', 'Jean-Pierre Jeunet', 'Comedia', 2001, 122),
('El Señor de los Anillos', 'Peter Jackson', 'Fantasia', 2001, 178)
ON DUPLICATE KEY UPDATE
director = VALUES(director), genero = VALUES(genero), anio = VALUES(anio), duracion = VALUES(duracion);

-- Listar
DELIMITER //
CREATE PROCEDURE sp_listar_peliculas()
BEGIN
    SELECT id, titulo, director, genero, anio, duracion FROM peliculas ORDER BY id;
END // 
DELIMITER ;

-- Agregar  
DELIMITER //
CREATE PROCEDURE sp_insertar_peliculas(
    IN p_titulo VARCHAR(150),
    IN p_director VARCHAR(50),
    IN p_genero ENUM('Comedia','Drama','Accion','Fantasia','Musical','Romance'),
    IN p_anio INT,
    IN p_duracion INT, 
    OUT p_id INT
)
BEGIN
    INSERT INTO peliculas (titulo, director, genero, anio, duracion)
    VALUES (p_titulo, p_director, p_genero, p_anio, p_duracion);
    SET p_id = LAST_INSERT_ID();
END //
DELIMITER ;   
 
-- Modificar
DELIMITER //
CREATE PROCEDURE sp_modificar_peliculas(
    IN p_id INT,
    IN p_titulo VARCHAR(150),
    IN p_director VARCHAR(50),
    IN p_genero ENUM('Comedia','Drama','Accion','Fantasia','Musical','Romance'),
    IN p_anio INT,
    IN p_duracion INT,
    OUT p_rows INT 
) 
BEGIN
    UPDATE peliculas
    SET titulo = p_titulo,
        director = p_director,
        genero = p_genero,
        anio = p_anio,
        duracion = p_duracion 
    WHERE id = p_id;
    SET p_rows = ROW_COUNT();
END //
DELIMITER ;

-- Buscar
DELIMITER //
CREATE PROCEDURE sp_obtener_titulo_por_id(
    IN p_id INT,
    OUT p_titulo VARCHAR(150)
)
BEGIN
    SELECT titulo INTO p_titulo FROM peliculas WHERE id = p_id;
END //
DELIMITER ;

-- Eliminar
DELIMITER //
CREATE PROCEDURE sp_eliminar_peliculas(
    IN p_id INT,
    OUT p_rows INT
)
BEGIN
    DELETE FROM peliculas WHERE id = p_id;
    SET p_rows = ROW_COUNT();
END //
DELIMITER ; 

