
CREATE TABLE cartelera (
    id INT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(150),
    director VARCHAR(50),
    año INT NOT NULL,
    duracion INT,
    genero ENUM('Comedia', 'Drama', 'Accion', 'Fantasia', 'Musical', 'Romance')
);      