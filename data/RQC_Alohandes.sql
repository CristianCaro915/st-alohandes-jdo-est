 --REQUERIMIENTO 1

 --PROPIETARIO INMUEBLE
 SELECT NOMBRE,TIPO,TIPOCONTRATO,DURACION,RESERVADO,PRECIOESPECIAL,PRECIOFINAL AS PRECIO FROM PROPIETARIOINMUEBLE 
 INNER JOIN OFERTA ON OFERTA.ID_PROPIETARIOI=PROPIETARIOINMUEBLE.ID_PI  
 INNER JOIN CONTRATO ON CONTRATO.ID_OFERTA=OFERTA.ID_O
 WHERE OFERTA.RESERVADO=1 AND CONTRATO.FECHAINICIO <= '04/03/2023'  AND  
 CONTRATO.FECHAINICIO >= '01/01/2023';
--EMPRESA
 SELECT NOMBRE,TIPO,TIPOCONTRATO,DURACION-DURACIONPREPAID,PRECIOESPECIAL,PRECIOFINAL AS PRECIO FROM EMPRESA 
 INNER JOIN OFERTA ON OFERTA.ID_EMPRESA=EMPRESA.ID_E 
 INNER JOIN CONTRATO ON CONTRATO.ID_OFERTA=OFERTA.ID_O
  WHERE OFERTA.RESERVADO=1 AND CONTRATO.FECHAINICIO <= '30/12/2023'  AND  
 CONTRATO.FECHAINICIO >= '01/01/2023';

 --REQUERIMIENTO 2
SELECT TIPOH AS CATEGORIA, COUNT(ID_H) AS CANTIDAD FROM HABITACION WHERE ROWNUM <20 GROUP BY HABITACION.TIPOH;
SELECT TIPOI AS CATEGORIA, COUNT(ID_I) AS CANTIDAD FROM INMUEBLE WHERE ROWNUM <20 GROUP BY INMUEBLE.TIPOI;
SELECT TIPOCONTRATO AS CATEGORIA, COUNT(ID_C) AS CANTIDAD FROM CONTRATO WHERE ROWNUM <20 GROUP BY CONTRATO.TIPOCONTRATO;

--REQUERIMIENTO 3


--REQUERIMIENTO 4
SELECT * FROM OFERTA 
INNER JOIN HABITACION ON HABITACION.ID_OFERTA=OFERTA.ID_O 
INNER JOIN INMUEBLE ON HABITACION.ID_INMUEBLE= INMUEBLE.ID_I
INNER JOIN CONTRATO ON HABITACION.ID_CONTRATO=CONTRATO.ID_C
INNER JOIN OFERTA ON OFERTA.ID_O=CONTRATO.ID_OFERTA
INNER JOIN BRINDAN ON BRINDAN.ID_HABITACION=HABITACION.ID_H
INNER JOIN SERVICIO ON BRINDAN.ID_SERVICIO= SERVICIO.ID_SH
INNER JOIN INMUEBLE ON INMUEBLE.ID_OFERTA=OFERTA.ID_O
WHERE OFERTA.RESERVADO=0  AND CONTRATO.FECHAINICIO <='04/03/2023' AND  
 CONTRATO.FECHAINICIO >= '01/01/2023' 
AND SERVICIO.NOMBRE='PARQUEADERO' 
AND SERVICIO.NOMBRE='COCINETA' ;

--REQUERIMIENTO 5

---REQUERIMIENTO 6
SELECT CLIENTE.NOMBRE,CONTRATO.DURACION,CONTRATO.FECHAINICIO,CONTRATO.TIPOCONTRATO from CLIENTE 
INNER JOIN OFERTA ON CLIENTE.ID_CL = OFERTA.ID_CLIENTE
INNER JOIN CONTRATO ON CONTRATO.ID_OFERTA=OFERTA.ID_O;

--RFC7
SELECT ID_HOSTAL, FECHAINICIO,COUNT(FECHAINICIO) AS NUM_FECHAS FROM OFERTA
INNER JOIN CONTRATO ON OFERTA.ID_O = CONTRATO.ID_OFERTA 
GROUP BY ID_HOSTAL,FECHAINICIO HAVING ID_HOSTAL IS NOT NULL ORDER BY ID_HOSTAL DESC;

--RFC8
SELECT ID_O, COUNT(ID_CLIENTE) AS NUM_CLIENTES FROM OFERTA GROUP BY ID_O ORDER BY NUM_CLIENTES DESC;
--RFC9
SELECT ID_PROPIETARIOI, ID_EMPRESA, ID_HOSTAL, ID_HOTEL, COUNT(ID_O) AS NUM_OFERTAS FROM OFERTA 
GROUP BY ID_PROPIETARIOI, ID_EMPRESA, ID_HOSTAL, ID_HOTEL ORDER BY NUM_OFERTAS DESC;

--ESCENARIOS DE PRUEBA FKS Y CKS
 
INSERT INTO HOSTAL (ID_HS,NOMBRE,RECEPCION,HORACIERRE,HORAAPERTURA,TIPO) VALUES (10,'sleepySneaky',1,
TO_DATE('7:29:00', 'HH:MI:SS'),TO_DATE('11:28:30', 'HH:MI:SS'),'HOSTAL');

INSERT INTO PROPIETARIOINMUEBLE (ID_PI,NOMBRE,VINCULO,TIPO) VALUES (10,'Jonnas Brother','EXTERNO','PERSONANAT');
INSERT INTO PROPIETARIOINMUEBLE (ID_PI,NOMBRE,VINCULO,TIPO) VALUES (11,'Jonnas Brother','EGRESADO','JUBILADO');

INSERT INTO SEGURO (ID_S,FECHAVENCE,DESCRIPCION,ID_INMUEBLE) VALUES (10,TO_DATE('3/03/2025', 'DD/MM/YY'),'aa',999999);

INSERT INTO OFERTA (ID_O,RESERVADO,ID_CLIENTE,ID_PROPIETARIOI,ID_EMPRESA,ID_HOSTAL,ID_HOTEL) VALUES (10,1,NULL,999999,NULL,NULL,NULL);
INSERT INTO OFERTA (ID_O,RESERVADO,ID_CLIENTE,ID_PROPIETARIOI,ID_EMPRESA,ID_HOSTAL,ID_HOTEL) VALUES (11,0,NULL,NULL,NULL,NULL,9999);

INSERT INTO INMUEBLE (ID_I,TIPOI,UBICACION,COSTOADMIN,NUMHABITACIONES,ID_OFERTA) VALUES (10,'VIVIENDA','UBI1',20,2,9999);
INSERT INTO INMUEBLE (ID_I,TIPOI,UBICACION,COSTOADMIN,NUMHABITACIONES,ID_OFERTA) VALUES (11,'APARTAESTUDIO','UBI1',20,2,1);

INSERT INTO HABITACION (ID_H,TAMANIO,TIPOH,PRECIOFINAL,UBICACION,ID_OFERTA,ID_CONTRATO,ID_INMUEBLE) VALUES 
(10,80,'SEMISUITE',170,'UBI3',1,1,999);
INSERT INTO HABITACION (ID_H,TAMANIO,TIPOH,PRECIOFINAL,UBICACION,ID_OFERTA,ID_CONTRATO,ID_INMUEBLE) VALUES 
(11,90,'CAMASTRO',170,'UBI3',1,1,2);

INSERT INTO BRINDAN (ID_HABITACION,ID_SERVICIO) VALUES (9999,1);
INSERT INTO BRINDAN (ID_HABITACION,ID_SERVICIO) VALUES (9999,9998);