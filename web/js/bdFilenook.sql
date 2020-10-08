DROP TABLE ClasificacionCategorias;
DROP TABLE ValoracionesComentario;
DROP TABLE Comentario;
DROP TABLE ValoracionesNook;
DROP TABLE ValoracionesAutor;
DROP TABLE Documento;
DROP TABLE Nook;
DROP TABLE Mensaje;
DROP TABLE Usuario;



CREATE TABLE Usuario(
	nombre	VARCHAR(50) NOT NULL,
	apellidos VARCHAR(50) NOT NULL,
	nombreUsuario VARCHAR(10) NOT NULL,
	clave VARCHAR(15) NOT NULL,
	correo VARCHAR(80) NOT NULL,
        imagenPerfil BLOB(5M),
	PRIMARY KEY (nombreUsuario)
);


CREATE TABLE Mensaje(
	idMensaje INTEGER GENERATED ALWAYS AS IDENTITY (START WITH 0, INCREMENT BY 1),
        asunto VARCHAR(100) NOT NULL,
	texto VARCHAR(400),
	fecha DATE,
	leido INTEGER CHECK (leido IN (0,1)),
	tipo VARCHAR(10) CHECK (tipo IN ('Enviado', 'Recibido')),
	autor VARCHAR(10) NOT NULL,
	destinatario VARCHAR(10) NOT NULL,
	PRIMARY KEY (idMensaje),
	CONSTRAINT FK_MENSAJE FOREIGN KEY (autor)
		REFERENCES Usuario (nombreUsuario),
	CONSTRAINT FK_MENSAJE2 FOREIGN KEY (destinatario)
		REFERENCES Usuario (nombreUsuario)
);


CREATE TABLE Nook(
	idNook INTEGER GENERATED ALWAYS AS IDENTITY (START WITH 0, INCREMENT BY 1),
	nombre VARCHAR(180) NOT NULL,
	resumen VARCHAR(400),
	autor VARCHAR(10) NOT NULL,
	fechaCreacion DATE,
	fechaModificacion DATE,
	descargas INTEGER,
        valoracionMedia NUMERIC(3,1),
	PRIMARY KEY (idNook),
	CONSTRAINT FK_NOOK FOREIGN KEY (autor)
		REFERENCES Usuario (nombreUsuario)
);


CREATE TABLE Documento(
	nook INTEGER NOT NULL,
	nombre VARCHAR(50) NOT NULL,
	resumen VARCHAR(400),
	fechaCreacion DATE,
	fechaModificacion DATE,
        documento BLOB(16M),
	PRIMARY KEY (nook, nombre),
	CONSTRAINT FK_DOCUMENTO FOREIGN KEY (nook)
		REFERENCES Nook (idNook)
                ON DELETE CASCADE
);


CREATE TABLE ValoracionesAutor(
	autor VARCHAR(10) NOT NULL,
        usuario VARCHAR(10) NOT NULL,
	puntuacion NUMERIC(3,1) NOT NULL,
	PRIMARY KEY (autor, usuario),
	CONSTRAINT FK_VALAUTOR FOREIGN KEY (autor)
		REFERENCES Usuario (nombreUsuario),
        CONSTRAINT FK_VALAUTOR2 FOREIGN KEY (usuario)
		REFERENCES Usuario (nombreUsuario)
);


CREATE TABLE ValoracionesNook(
	nook INTEGER NOT NULL,
	usuario VARCHAR(10) NOT NULL,
	puntuacion INTEGER NOT NULL,
	fecha DATE,
	PRIMARY KEY (nook, usuario),
	CONSTRAINT FK_VALNOOK FOREIGN KEY (nook)
		REFERENCES Nook (idNook)
                ON DELETE CASCADE,
	CONSTRAINT FK_VALNOOK2 FOREIGN KEY (usuario)
		REFERENCES Usuario (nombreUsuario)
);


CREATE TABLE Comentario(
	idComentario INTEGER GENERATED ALWAYS AS IDENTITY (START WITH 0, INCREMENT BY 1),
	idNook INTEGER NOT NULL,
        fecha DATE,
	autor VARCHAR(10) NOT NULL,
	texto VARCHAR(180) NOT NULL,
        valoracionMedia NUMERIC(3,1),
	PRIMARY KEY (idComentario),
        CONSTRAINT FK_COMENTARIO FOREIGN KEY (idNook)
		REFERENCES Nook (idNook)
                ON DELETE CASCADE,
	CONSTRAINT FK_COMENTARIO2 FOREIGN KEY (autor)
		REFERENCES Usuario (nombreUsuario)
);


CREATE TABLE ValoracionesComentario(
	comentario INTEGER,
	puntuacion INTEGER,
	fecha DATE,
	usuario VARCHAR(10),
	PRIMARY KEY (comentario, usuario),
	CONSTRAINT FK_VALCOMENTARIO FOREIGN KEY (comentario)
		REFERENCES Comentario (idComentario)
                ON DELETE CASCADE,
	CONSTRAINT FK_VALCOMENTARIO2 FOREIGN KEY (usuario)
		REFERENCES Usuario (nombreUsuario)
);


CREATE TABLE ClasificacionCategorias(
	idNook INTEGER NOT NULL,
	categoria VARCHAR(60) NOT NULL,
	PRIMARY KEY (idNook, categoria),
	CONSTRAINT FK_CLASCATEGORIAS FOREIGN KEY (idNook)
		REFERENCES Nook (idNook)
                ON DELETE CASCADE
);


INSERT INTO Usuario VALUES ('Helio','Fernandez Abad','admin','adminfilenook','admin@alumnos.uva.es',null);

INSERT INTO Usuario VALUES ('Andrés','Cabero Mata','andcabe','1122','andres.cabero@alumnos.uva.es',null);
INSERT INTO Usuario VALUES ('Rafael','Higelmo San Millán','rafhige','3344','rafael.higelmo@alumnos.uva.es',null);
INSERT INTO Usuario VALUES ('Patricia','Aguado Labrador','patagua','5566','patricia.aguado@alumnos.uva.es',null);
INSERT INTO Usuario VALUES ('Santiago','Blasco Arnaiz','sanblas','7788','santiago.blasco@alumnos.uva.es',null);

INSERT INTO Mensaje (asunto,texto,fecha,leido,tipo,autor,destinatario) VALUES ('Duda','Me gustaria que me explicase la función de la lía 127 de su código, gracias.','2019-04-09',1,'Enviado','patagua','rafhige');
INSERT INTO Mensaje (asunto,texto,fecha,leido,tipo,autor,destinatario) VALUES ('Duda','Me gustaria que me explicase la función de la lía 127 de su código, gracias.','2019-04-09',0,'Recibido','patagua','rafhige');

INSERT INTO Mensaje (asunto,texto,fecha,leido,tipo,autor,destinatario) VALUES ('Respuesta','Corresponde a un desplazamiento hacia la derecha para realizar una división.','2019-04-13',1,'Enviado','rafhige','patagua');
INSERT INTO Mensaje (asunto,texto,fecha,leido,tipo,autor,destinatario) VALUES ('Respuesta','Corresponde a un desplazamiento hacia la derecha para realizar una división.','2019-04-13',0,'Recibido','rafhige','patagua');

INSERT INTO Mensaje (asunto,texto,fecha,leido,tipo,autor,destinatario) VALUES ('Pregunta sobre Criptografía','Buenos días, me gustaria una explicación más ampliada del ejercicio 5, gracias de antemano.','2018-12-25',1,'Enviado','andcabe','patagua');
INSERT INTO Mensaje (asunto,texto,fecha,leido,tipo,autor,destinatario) VALUES ('Pregunta sobre Criptografía','Buenos días, me gustaria una explicación más ampliada del ejercicio 5, gracias de antemano.','2019-12-25',0,'Recibido','andcabe','patagua');


INSERT INTO Nook (nombre,resumen,autor,fechaCreacion,fechaModificacion,descargas,valoracionMedia) VALUES ('MIPS','Prácticas en lenguaje ensamblador de primero de carrera de Ingeniería Informática','rafhige','2016-06-20','2016-06-21',16,4.0);
INSERT INTO Nook (nombre,resumen,autor,fechaCreacion,fechaModificacion,descargas,valoracionMedia) VALUES ('Códigos Reed-Solomon','Ejercicios de la asignatura de Criptografía','patagua','2017-12-20','2017-12-20',0,0.0);
INSERT INTO Nook (nombre,resumen,autor,fechaCreacion,fechaModificacion,descargas,valoracionMedia) VALUES ('Estructura del vehiculo','Diagnostico de daños estructurales','rafhige','2016-07-10','2016-07-10',2,0.0);
INSERT INTO Nook (nombre,resumen,autor,fechaCreacion,fechaModificacion,descargas,valoracionMedia) VALUES ('Gramáticas y lenguajes formales','Expresiones Regulares y autómatas finitos','sanblas','2018-01-12','2018-01-12',120,0.0);
INSERT INTO Nook (nombre,resumen,autor,fechaCreacion,fechaModificacion,descargas,valoracionMedia) VALUES ('Literatura del siglo XX','1984; George Orwell','sanblas','2014-10-05','2014-10-05',29,0.0);
INSERT INTO Nook (nombre,resumen,autor,fechaCreacion,fechaModificacion,descargas,valoracionMedia) VALUES ('MRP. Publicidad y Relaciones públicas','Aplicación de MRP como un sistema de programación y ordenamiento','sanblas','2016-05-10','2016-05-10',65,0.0);
INSERT INTO Nook (nombre,resumen,autor,fechaCreacion,fechaModificacion,descargas,valoracionMedia) VALUES ('Derecho','Acta de constitución de sociedad mercantil','andcabe','2014-05-05','2014-05-05',78,0.0);
INSERT INTO Nook (nombre,resumen,autor,fechaCreacion,fechaModificacion,descargas,valoracionMedia) VALUES ('Derecho mercantil','Arrendamiento financiero. Leasing','andcabe','2015-01-20','2015-01-20',12,0.0);
INSERT INTO Nook (nombre,resumen,autor,fechaCreacion,fechaModificacion,descargas,valoracionMedia) VALUES ('Blockchain','Tecnologías de la actualidad','patagua','2018-03-12','2018-03-12',10,0.0);
INSERT INTO Nook (nombre,resumen,autor,fechaCreacion,fechaModificacion,descargas,valoracionMedia) VALUES ('Electrónica y Mecánica','Curso 1, primer cuatrimestre','rafhige','2018-10-05','2018-10-05',83,0.0);
INSERT INTO Nook (nombre,resumen,autor,fechaCreacion,fechaModificacion,descargas,valoracionMedia) VALUES ('Diseño y diagnóstico de organizaciones en entornos complejos','Cibernética organizacional','sanblas','2018-05-10','2018-05-10',17,0.0);
INSERT INTO Nook (nombre,resumen,autor,fechaCreacion,fechaModificacion,descargas,valoracionMedia) VALUES ('Ingeniería de elevación','Aparatos de elevación motorizados','rafhige','2019-01-20','2019-01-20',3,0.0);
INSERT INTO Nook (nombre,resumen,autor,fechaCreacion,fechaModificacion,descargas,valoracionMedia) VALUES ('Psicología','Acoso psicológico laboral','andcabe','2016-05-05','2016-05-05',37,0.0);
INSERT INTO Nook (nombre,resumen,autor,fechaCreacion,fechaModificacion,descargas,valoracionMedia) VALUES ('Criptomonedas','Diferencia entre criptomoneda, moneda virtual y dinero digital','patagua','2018-10-12','2018-10-12',91,0.0);

INSERT INTO Documento (nook,nombre,resumen,fechaCreacion,fechaModificacion) VALUES (0,'Sumador.s','Programa MIPS para sumar número en hexadecimal y que muestre el resultado en binario complemento a dos.','2016-06-20','2016-06-20');
INSERT INTO Documento (nook,nombre,resumen,fechaCreacion,fechaModificacion) VALUES (0,'Shunting.s','Algoritmo shunting yard implementado en MIPS mediante el uso de notación polaca inversa que devuelve resultados en decimal o hexadecimal como su entrada.','2016-06-20','2016-06-20');
INSERT INTO Documento (nook,nombre,resumen,fechaCreacion,fechaModificacion) VALUES (0,'Conversor.s','Algoritmo que pasa de hexadecimal a binario.','2016-06-21','2016-06-21');
INSERT INTO Documento (nook,nombre,resumen,fechaCreacion,fechaModificacion) VALUES (1,'Ejercicios Resueltos.pdf','Ejercicios de codificación y decodificación de códigos Reed-Solomon.','2017-12-20','2017-12-20');
INSERT INTO Documento (nook,nombre,resumen,fechaCreacion,fechaModificacion) VALUES (2,'Análisis visual de una colisión.doc','Diagnóstico previo de las deformaciones que haya podido sufrir un vehículo en una colisión.','2016-07-10','2016-07-10');
INSERT INTO Documento (nook,nombre,resumen,fechaCreacion,fechaModificacion) VALUES (3,'Practica1.pdf','Procesamiento de ficheros CSV.','2018-01-12','2018-01-12');
INSERT INTO Documento (nook,nombre,resumen,fechaCreacion,fechaModificacion) VALUES (3,'Práctica2.pdf','Análisis Léxico: Resumen de ficheros HTML.','2018-01-12','2018-01-12');
INSERT INTO Documento (nook,nombre,resumen,fechaCreacion,fechaModificacion) VALUES (3,'Practica3.pdf','Intérprete para el lenguaje P.','2018-01-12','2018-01-12');
INSERT INTO Documento (nook,nombre,resumen,fechaCreacion,fechaModificacion) VALUES (4,'1984.pdf','Resumen de la obra 1984 escrita por George Orwell.','2014-10-05','2014-10-05');
INSERT INTO Documento (nook,nombre,resumen,fechaCreacion,fechaModificacion) VALUES (5,'MRP.pdf','Sistema de planificación y administración, normalmente asociado con un software que planifica la producción y un sistema de control de inventarios.','2016-05-10','2016-05-10');
INSERT INTO Documento (nook,nombre,resumen,fechaCreacion,fechaModificacion) VALUES (6,'Sociedad mercantil.pdf','Acta de constitución de sociedad mercantil.','2014-05-05','2014-05-05');
INSERT INTO Documento (nook,nombre,resumen,fechaCreacion,fechaModificacion) VALUES (7,'Leasing.pdf','El arrendamiento financiero es un contrato mediante el cual, el arrendador traspasa el derecho a usar un bien a un arrendatario, a cambio del pago de rentas de arrendamiento durante un plazo determinado, al término del cual el arrendatario tiene la opción de comprar el bien arrendado pagando un precio determinado, devolverlo o renovar el contrato.','2015-01-20','2015-01-20');
INSERT INTO Documento (nook,nombre,resumen,fechaCreacion,fechaModificacion) VALUES (8,'Blockchain.pdf','Criptografía básica para entender la tecnología blockchain.','2018-03-12','2018-03-12');
INSERT INTO Documento (nook,nombre,resumen,fechaCreacion,fechaModificacion) VALUES (9,'ExpresiónGráfica.pdf','Primera prueba escrita.','2018-10-05','2018-10-05');
INSERT INTO Documento (nook,nombre,resumen,fechaCreacion,fechaModificacion) VALUES (9,'Fundamentos de la Informática.pdf','Practica 1.','2018-10-05','2018-10-05');
INSERT INTO Documento (nook,nombre,resumen,fechaCreacion,fechaModificacion) VALUES (10,'Kybernetes.pdf','Pérez Ríos, J. (2004). A self-organizing network for the systems community. Kybernetes: The International Journal of Systems & Cybernetics. Vol.33 Nº3/4.','2018-05-10','2018-05-10');
INSERT INTO Documento (nook,nombre,resumen,fechaCreacion,fechaModificacion) VALUES (11,'Aparatos de elevación motorizados.doc','Resumen y diferencias de los distintos aparatos de elevación motorizados.','2019-01-20','2019-01-20');
INSERT INTO Documento (nook,nombre,resumen,fechaCreacion,fechaModificacion) VALUES (12,'Acoso.pdf','¿Qué tipos de conductas son acoso y cuáles se pueden confundir con esta situación?.','2016-05-05','2016-05-05');
INSERT INTO Documento (nook,nombre,resumen,fechaCreacion,fechaModificacion) VALUES (13,'Criptomonedas.pdf','Actualidad. Resumen y diferencias entre criptomoneda, moneda virtual y dinero digital.','2018-10-12','2018-10-12');


INSERT INTO ValoracionesAutor VALUES ('rafhige','patagua',2);
INSERT INTO ValoracionesAutor VALUES ('rafhige','sanblas',4);


INSERT INTO ValoracionesNook VALUES (0,'patagua',5,'2016-11-12');
INSERT INTO ValoracionesNook VALUES (0,'sanblas',3,'2017-08-06');
INSERT INTO ValoracionesNook VALUES (0,'andcabe',4,'2018-05-15');

INSERT INTO Comentario (fecha,idNook,autor,texto,valoracionMedia) VALUES ('2017-08-06',0,'sanblas','Buen nook!!',4.0);
INSERT INTO Comentario (fecha,idNook,autor,texto,valoracionMedia) VALUES ('2019-10-06',0,'patagua','Hola, muy buenas!!',0.0);
INSERT INTO Comentario (fecha,idNook,autor,texto,valoracionMedia) VALUES ('2016-11-13',0,'andcabe','Tengo una duda con el archivo Sumador.s te mando un correo.',0.0);
INSERT INTO Comentario (fecha,idNook,autor,texto,valoracionMedia) VALUES ('2019-05-06',1,'patagua','CHACHI!!',0.0);
INSERT INTO Comentario (fecha,idNook,autor,texto,valoracionMedia) VALUES ('2019-08-06',2,'andcabe','Holiiiiii!!',0.0);

INSERT INTO Comentario (fecha,idNook,autor,texto,valoracionMedia) VALUES ('2019-09-06',4,'rafhige','Buen nook!!',0.0);

INSERT INTO ValoracionesComentario VALUES (0,4,'2017-09-16','rafhige');

INSERT INTO ClasificacionCategorias VALUES (0,'MIPS');
INSERT INTO ClasificacionCategorias VALUES (0,'IngenieríaInformática');
INSERT INTO ClasificacionCategorias VALUES (1,'IngenieríaInformática');
INSERT INTO ClasificacionCategorias VALUES (0,'PrimerCurso');
INSERT INTO ClasificacionCategorias VALUES (1,'Códigos');
INSERT INTO ClasificacionCategorias VALUES (1,'Criptografía');
