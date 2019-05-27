insert into Usuario (id, nombre, apellidos, email, nickname, password, telefono, cuenta_caducada, cuenta_bloqueada, credenciales_caducadas, admin) values (11111, 'Alvaro', 'Marquez Mata', 'alvaromarquez98@gmail.com', 'Ajeki', '1234', '615073398', false, false, false, true);
insert into Usuario (id, nombre, apellidos, email, nickname, password, telefono, cuenta_caducada, cuenta_bloqueada, credenciales_caducadas, admin) values (11112, 'Mike', 'Ronar Kelpie', 'mike@mike.com', 'RLSTheLecy', '1234', '615073398', false, false, false, false);
insert into Usuario (id, nombre, apellidos, email, nickname, password, telefono, cuenta_caducada, cuenta_bloqueada, credenciales_caducadas, admin) values (11113, 'Guille', 'Abajo Diaz', 'guille@guille.com', 'Bisslit', '1234', '615073398', false, false, false, false);
insert into Usuario (id, nombre, apellidos, email, nickname, password, telefono, cuenta_caducada, cuenta_bloqueada, credenciales_caducadas, admin) values (11114, 'Carlos', 'Aunion Dominguez', 'carlos@carlos.com', 'cad2298', '1234', '615073398', false, false, false, false);

insert into Usuario_Empresa (cif, campo_empresa, direccion_facturacion, nombre_empresa, telefono_empresa, id) values ('1', 'Cabras','Isadora Duncan' , 'CabrasAGoGo', '666666666', 11112);

insert into Invent (id, nombre, usuario_id) values (11111, 'Asoka', 11111);

insert into Conceptos (id, nombre, invent_id) values (11111, 'Tabla Asoka', 11111);
insert into Conceptos (id, nombre, invent_id) values (11112, 'Tabla Asoka 2', 11111);
insert into Conceptos (id, nombre, invent_id) values (11113, 'Tabla Asoka 3', 11111);

insert into Campos (id, nombre, tipo, concepto_id) values (11111, 'Campo Asoka 1', 'String', 11111);
insert into Campos (id, nombre, tipo, concepto_id) values (11112, 'Campo Asoka 2', 'String', 11111);
insert into Campos (id, nombre, tipo, concepto_id) values (11113, 'Campo Asoka 3', 'String', 11111);
insert into Campos (id, nombre, tipo, concepto_id) values (11114, 'Campo Asoka 4', 'String', 11111);

insert into Campos (id, nombre, tipo, concepto_id) values (11115, 'Campo Asoka 1', 'String', 11112);
insert into Campos (id, nombre, tipo, concepto_id) values (11116, 'Campo Asoka 2', 'String', 11112);
insert into Campos (id, nombre, tipo, concepto_id) values (11117, 'Campo Asoka 3', 'String', 11112);
insert into Campos (id, nombre, tipo, concepto_id) values (11118, 'Campo Asoka 4', 'String', 11112);

insert into Campos (id, nombre, tipo, concepto_id) values (11119, 'Campo Asoka 1', 'String', 11113);
insert into Campos (id, nombre, tipo, concepto_id) values (111110, 'Campo Asoka 2', 'String', 11113);
insert into Campos (id, nombre, tipo, concepto_id) values (111111, 'Campo Asoka 3', 'String', 11113);
insert into Campos (id, nombre, tipo, concepto_id) values (111112, 'Campo Asoka 4', 'String', 11113);

insert into Valores_Campos (id, valor, concepto_id, campo_id) values (11111, 'Valor 1', 11111, 11111);
insert into Valores_Campos (id, valor, concepto_id, campo_id) values (11112, 'Valor 2', 11111, 11111);
insert into Valores_Campos (id, valor, concepto_id, campo_id) values (11113, 'Valor 3', 11111, 11111);
insert into Valores_Campos (id, valor, concepto_id, campo_id) values (11114, 'Valor 1', 11111, 11112);
insert into Valores_Campos (id, valor, concepto_id, campo_id) values (11115, 'Valor 2', 11111, 11112);
insert into Valores_Campos (id, valor, concepto_id, campo_id) values (11116, 'Valor 3', 11111, 11112);
insert into Valores_Campos (id, valor, concepto_id, campo_id) values (111113, 'Valor 1', 11111, 11113);
insert into Valores_Campos (id, valor, concepto_id, campo_id) values (111114, 'Valor 2', 11111, 11113);
insert into Valores_Campos (id, valor, concepto_id, campo_id) values (111115, 'Valor 3', 11111, 11113);
insert into Valores_Campos (id, valor, concepto_id, campo_id) values (111116, 'Valor 1', 11111, 11114);
insert into Valores_Campos (id, valor, concepto_id, campo_id) values (111117, 'Valor 2', 11111, 11114);
insert into Valores_Campos (id, valor, concepto_id, campo_id) values (111118, 'Valor 3', 11111, 11114);
insert into Valores_Campos (id, valor, concepto_id, campo_id) values (11117, 'Valor 1', 11112, 11115);
insert into Valores_Campos (id, valor, concepto_id, campo_id) values (11118, 'Valor 2', 11112, 11115);
insert into Valores_Campos (id, valor, concepto_id, campo_id) values (11119, 'Valor 3', 11112, 11115);
insert into Valores_Campos (id, valor, concepto_id, campo_id) values (111110, 'Valor 1', 11112, 11116);
insert into Valores_Campos (id, valor, concepto_id, campo_id) values (111111, 'Valor 2', 11112, 11116);
insert into Valores_Campos (id, valor, concepto_id, campo_id) values (111112, 'Valor 3', 11112, 11116);

