CREATE TABLE usuarios (
	usuario_id INT,
	username VARCHAR(400) NOT NULL,
	password VARCHAR(100) NOT NULL,
	apellidos VARCHAR(200) NOT NULL,
	nombres VARCHAR(200) NOT NULL,
	email VARCHAR(300) NOT NULL,
	account_non_expired BIT NOT NULL,
	is_account_non_locked BIT NOT NULL,
	is_credentials_non_expired BIT NOT NULL,
	is_enabled BIT NOT NULL,
	CONSTRAINT pk_usuario_id PRIMARY KEY (usuario_id)
);
