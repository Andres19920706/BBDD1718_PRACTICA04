DROP DATABASE IF EXISTS appFutbol;
CREATE DATABASE appFutbol;
USE appFutbol;

#Estadio
DROP TABLE IF EXISTS ESTADIO; #por si existe la elimina
CREATE TABLE ESTADIO (
 #Atributos de equipo
 idEstadio			int  AUTO_INCREMENT,
 capacidad			int default 0,
 direccion          VARCHAR(50) NOT NULL ,
 ciudad             VARCHAR(20) NOT NULL ,
  
 #Clave primaria
 CONSTRAINT PKestadio PRIMARY KEY (idEstadio) 
 
 );
 
 DESCRIBE ESTADIO;
 INSERT INTO ESTADIO VALUES (null,1001,'calle ubeda','linares');
 SELECT * FROM ESTADIO;
#Equipo
DROP TABLE IF EXISTS EQUIPO; #por si existe la elimina
CREATE TABLE EQUIPO (
 #Atributos de equipo
 idEquipo			int  AUTO_INCREMENT,
 posicion			int default 0,
 nombre             VARCHAR(20) NOT NULL ,
 
 #Atributos de estadio
 idEstadio			int  default null,
 
 #Clave primaria
 CONSTRAINT PKequipo PRIMARY KEY (idEquipo),
 #Clave ajena
 CONSTRAINT FKequipo FOREIGN KEY (idEstadio) REFERENCES ESTADIO (idEstadio) ON DELETE SET NULL ON UPDATE CASCADE
  
 );
 
 describe EQUIPO;
 INSERT INTO EQUIPO VALUES (null,0,'Linarejos',null);
 SELECT * FROM EQUIPO;
 
 #Jugador
 DROP TABLE IF EXISTS JUGADOR; #por si existe la elimina
 CREATE TABLE JUGADOR (
 #Atributos de persona
 id					int  AUTO_INCREMENT,
 nombre             VARCHAR(20) NOT NULL ,
 email              VARCHAR(50) NOT NULL ,
 tfl				VARCHAR(12) NOT NULL , 
 #Atributos de jugador
 salario			double NOT NULL,
 num				int NOT NULL,
 posicion			VARCHAR(10),
 titular			boolean default false ,
 idEquipo			int  default null,
 
 #Clave primaria
 CONSTRAINT PKjugador PRIMARY KEY (id),
 #Clave ajena
 #CONSTRAINT FKjugador FOREIGN KEY (idEquipo) REFERENCES EQUIPO (idEquipo)
 FOREIGN KEY (idEquipo) REFERENCES EQUIPO (idEquipo) ON DELETE SET NULL  ON UPDATE CASCADE
 );
 
 DESCRIBE JUGADOR;
 #Arbitro
 DROP TABLE IF EXISTS ARBITRO; #por si existe la elimina
 CREATE TABLE ARBITRO (
 #Atributos de persona
 idArbitro			int  AUTO_INCREMENT,
 nombre             VARCHAR(20) NOT NULL ,
 email              VARCHAR(50) NOT NULL ,
 tfl				VARCHAR(12) NOT NULL , 
 #Atributos de jugador
 tipo				VARCHAR(10),
 salario			double NOT NULL ,

 
 #Clave primaria
 CONSTRAINT PKarbitro PRIMARY KEY (idArbitro)

 );
 
 INSERT INTO ARBITRO VALUES (null,'Jacinto','jacinto@appfutol.es','+34689565123','centro',1500);
 DESCRIBE ARBITRO;
 
 #Partido
 DROP TABLE IF EXISTS PARTIDO; #por si existe la elimina
 CREATE TABLE PARTIDO (
 #Atributos de persona
	idPartido		int  AUTO_INCREMENT PRIMARY KEY,
	idArbitroA		int default null,
    idArbitroB		int default null,
    idEquipoA		int default null,
    idEquipoB		int default null,
    fecha			varchar(10), #No puede tener por defecto null si es timestup
    golesA			int,
    golesB			int,
    ida				boolean NOT NULL,
    idEstadio		int default null,

 #Clave ajena
 FOREIGN KEY (idArbitroA) REFERENCES ARBITRO (idArbitro) ON DELETE SET NULL  ON UPDATE CASCADE,
 FOREIGN KEY (idArbitroB) REFERENCES ARBITRO (idArbitro) ON DELETE SET NULL ON UPDATE CASCADE,
 FOREIGN KEY (idEquipoA) REFERENCES EQUIPO (idEquipo)  ON DELETE CASCADE  ON UPDATE  CASCADE,
 FOREIGN KEY (idEquipoB) REFERENCES EQUIPO (idEquipo)  ON DELETE CASCADE  ON UPDATE  CASCADE, #ElimAna o actualiza la tupla si se borra la tupla referenciada
 FOREIGN KEY (idEstadio) REFERENCES ESTADIO (idEstadio) ON DELETE SET NULL  ON UPDATE CASCADE #Lo pone a null si se borra o acutlaiza la tupla referenciada
 );
#Insección de claves ajeas con alter
#ALTER TABLE PARITDO ADD CONSTRAINT FKPA1 FOREIGN KEY (idArbitroA) REFERENCES ARBITRO (idArbitro); #ON UPDATE CASCADE;
#ALTER TABLE PARITDO ADD FOREIGN KEY (idArbitroA) REFERENCES ARBITRO (idArbitro);
#ALTER TABLE PARITDO ADD FOREIGN KEY (idArbitroB) REFERENCES ARBITRO (idArbitro); #ON UPDATE CASCADE;
#ALTER TABLE PARITDO ADD FOREIGN KEY (idEquipoA) REFERENCES EQUIPO (idEquipo); #ON UPDATE CASCADE;
#ALTER TABLE PARITDO ADD FOREIGN KEY (idEquipoB) REFERENCES EQUIPO (idEquipo); #ON UPDATE CASCADE;
DESCRIBE PARTIDO;
INSERT INTO JUGADOR VALUES (null,'Andres','andres@appfutol.es','+34689565123',1500,1,'Portero',true,null);

#UPDATE JUGADOR SET JUGADOR.idEquipo = null WHERE id = 1;
#Completamos el equipo 2
INSERT INTO ESTADIO VALUES (2,1001,'calle beta','ubeda');
INSERT INTO EQUIPO VALUES (2,0,'Ubedi',2);
INSERT INTO JUGADOR VALUES (null,'Ramon','ramon@appfutol.es','+34689565123',1500,1,'Portero',true,2);
INSERT INTO JUGADOR VALUES (null,'Luis','luis@appfutol.es','+34689565124',1500,1,'Medio',true,2);
INSERT INTO JUGADOR VALUES (null,'Angel','angel@appfutol.es','+34689565125',1500,1,'Defensa',true,2);
INSERT INTO JUGADOR VALUES (null,'Pedro','pedro@appfutol.es','+34689565126',1500,1,'Ataque',true,2);

#Jugadores sin equipo
INSERT INTO JUGADOR VALUES (null,'Jaime','jaime@appfutol.es','+34689565123',1500,1,'Portero',true,null);
INSERT INTO JUGADOR VALUES (null,'Luis Ramon','luisR@appfutol.es','+34689565123',1500,1,'Defensa',true,null);
INSERT INTO JUGADOR VALUES (null,'Cristobal','cristobal@appfutol.es','+34689565123',1500,1,'Medio',true,null);
INSERT INTO JUGADOR VALUES (null,'Paco','paco@appfutol.es','+34689565123',1500,1,'Ataque',true,null);

SELECT * FROM JUGADOR;
#SELECT * FROM JUGADOR WHERE JUGADOR.id = 1;
SELECT * FROM JUGADOR WHERE JUGADOR.idEquipo = 3 ;
SELECT * FROM EQUIPO;
SELECT * FROM ESTADIO;
SELECT * FROM ARBITRO;

#Partido
describe PARTIDO;
INSERT INTO PARTIDO VALUES (null,1,null,1,2,'2018-01-06',1,3,true,1); #INSERT INTO test VALUES ( UNIX_TIMESTAMP(STR_TO_DATE('2013-08-05 18:19:03', '%Y-%m-%d %H:%i:%s')) )
SELECT * FROM PARTIDO;

#Prueba
INSERT INTO EQUIPO VALUES (3,0,'Los pajarillos',2);
SELECT * FROM EQUIPO;
SELECT * FROM EQUIPO WHERE idEquipo = 2 and idEquipo!=2;
SELECT idPartido as id, idArbitroA  FROM PARTIDO WHERE fecha = '2018-01-09';

SELECT idEquipo FROM EQUIPO WHERE nombre = 'Ubedi';
SELECT * FROM JUGADOR WHERE idEquipo = (SELECT idEquipo FROM EQUIPO WHERE nombre = 'Ubedi');