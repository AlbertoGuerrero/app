USE weblifeforms;

CREATE TABLE Categoria (
	id INT IDENTITY NOT NULL,
	nombre VARCHAR(100),
	descripcion TEXT,
	PRIMARY KEY(id)
);

-- RELACIÓN MANY TO MANY
CREATE TABLE Tag (
	id INT IDENTITY NOT NULL,
	name VARCHAR(255),
	PRIMARY KEY(id)
);

CREATE TABLE Post (
	id INT IDENTITY NOT NULL,
	title VARCHAR(255),
	PRIMARY KEY(id)
);

CREATE TABLE Post_Tag (
	tag_id INT,
	post_id INT,
	CONSTRAINT tag_post_pk PRIMARY KEY (tag_id, post_id),
	CONSTRAINT fk_tag FOREIGN KEY (tag_id) REFERENCES Tag (id),
	CONSTRAINT fk_post FOREIGN KEY (post_id) REFERENCES Post (id)
);