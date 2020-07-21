USE weblifeforms;

CREATE TABLE Categoria (
	id INT IDENTITY NOT NULL,
	nombre VARCHAR(100),
	descripcion TEXT,
	PRIMARY KEY(id)
);