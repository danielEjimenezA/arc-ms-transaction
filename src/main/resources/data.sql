INSERT INTO person (id,name,gender,age,address,phone) VALUES ('1725374134','Jose Lema','Masculino',25,'Otavalo sn y principal','098254785');
INSERT INTO person (id,name,gender,age,address,phone) VALUES ('1750848390','Marianela Montalvo','Femenino',25,'Amazonas y  NNUU','097548965');
INSERT INTO person (id,name,gender,age,address,phone) VALUES ('1725374126','Juan Osorio','Masculino',25,'13 junio y Equinoccial','098874587');

INSERT INTO client (client_id, password, status, person_id) VALUES (347892,1234 ,True ,1725374134);
INSERT INTO client (client_id, password, status, person_id) VALUES (756567,5678  ,True ,1750848390);
INSERT INTO client (client_id, password, status, person_id) VALUES (786794,1245  ,True ,1725374126);

INSERT INTO account(account_id, client_id, account_number, account_type, initial_amount, status) VALUES (789834 ,347892, 478758 ,'Ahorros' ,2000 ,True );
INSERT INTO account(account_id, client_id, account_number, account_type, initial_amount, status) VALUES (324521 ,756567, 225487  ,'Corriente' ,100  ,True );
INSERT INTO account(account_id, client_id, account_number, account_type, initial_amount, status) VALUES (122345 ,786794, 495878  ,'Ahorros' ,0 ,True );
INSERT INTO account(account_id, client_id, account_number, account_type, initial_amount, status) VALUES (237643 ,756567, 496825  ,'Ahorros' ,540 ,True );