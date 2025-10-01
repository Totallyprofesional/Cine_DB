
CREATE DATABASE IF NOT EXISTS cine_db 
CHARACTER SET utf8mb4 
COLLATE utf8mb4_unicode_ci;

USE cine_db;
 
CREATE TABLE IF NOT EXISTS cartelera (
    id INT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(150) NOT NULL,
    director VARCHAR(50) NOT NULL,
    anio INT NOT NULL,
    duracion INT NOT NULL,
    genero ENUM('Comedia', 'Drama', 'Accion', 'Fantasia', 'Musical', 'Romance') NOT NULL,
    UNIQUE KEY uk_titulo_anio (titulo, anio)
) CHARACTER SET utf8mb4 
  COLLATE utf8mb4_unicode_ci; 

INSERT INTO cartelera (titulo, director, anio, duracion, genero) VALUES
('Amelie', 'Jean-Pierre Jeunet', 2001, 122, 'Comedia'),
('El Señor de los Anillos', 'Peter Jackson', 2001, 178, 'Fantasia')
ON DUPLICATE KEY UPDATE
director=VALUES(director), anio=VALUES(anio), duracion=VALUES(duracion), genero=VALUES(genero);

-- Mostrar Lista
DELIMITER // 
CREATE PROCEDURE sp_listar_cartelera()
BEGIN
  SELECT id, titulo, director, anio, duracion, genero FROM cartelera ORDER BY id;
END //
DELIMITER ; 

-- Agregar
DELIMITER //
CREATE PROCEDURE sp_insertar_pelicula(
  IN p_titulo VARCHAR(150),
  IN p_director VARCHAR(50), 
  IN p_anio INT,
  IN p_duracion INT,
  IN p_genero ENUM('Comedia', 'Drama', 'Accion', 'Fantasia', 'Musical', 'Romance'),
  OUT p_id INT
)
BEGIN
  INSERT INTO cartelera (titulo, director, anio, duracion, genero)
  VALUES (p_titulo, p_director, p_anio, p_duracion, p_genero);
  SET p_id = LAST_INSERT_ID();
END //
DELIMITER ; 
 
-- Buscar
DELIMITER //
CREATE PROCEDURE sp_obtener_titulo_por_id(
  IN  p_id INT,
  OUT p_titulo VARCHAR(150)
)
BEGIN
  SELECT titulo INTO p_titulo FROM cartelera WHERE id = p_id;
END //
DELIMITER ;

-- Eliminar
DELIMITER //
CREATE PROCEDURE sp_eliminar_pelicula(
  IN  p_id INT,
  OUT p_rows INT
)
BEGIN
  DELETE FROM cartelera WHERE id = p_id;
  SET p_rows = ROW_COUNT();
END //
DELIMITER ;