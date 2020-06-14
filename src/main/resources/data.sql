insert into roles (id,name,description) values (1,'ADMIN',null);
insert into roles (id,name,description) values (2,'USER',null);
insert into roles (id,name,description) values (3,'CUSTOMER',null);
insert into roles (id,name,description) values (4,'PROVIDER',null);

insert into users (id, first_name,email,is_active,is_eliminated,password) values (null,'cesar','csv@verdugo.com',true,false,'$2y$12$7fDLNBepaZ4rieeVqroFF.T51Hc7hX4mvWCuD5PstfynOKuYQR4S6');
insert into users (id, first_name,email,is_active,is_eliminated,password) values(null,'customer','customer@invoice.com',true,false,'$2y$12$7fDLNBepaZ4rieeVqroFF.T51Hc7hX4mvWCuD5PstfynOKuYQR4S6');

insert into users_has_roles (id_users,id_roles)values (1,1);

insert into categories (id,name,is_eliminated) values (null,'tecnologia',false);
insert into categories (id,name,is_eliminated) values (null,'Aseo',false);

insert into products (id, is_eliminated,min_stock,name,prece,stock,id_category) values (null,false,5,'Computador',500000,20,1);

INSERT INTO invoices (id, amount_owed, amount_paid, is_eliminated, payment_status, total_price) VALUES 
	(null, 0, 0, false, 'CANCELLED', 120000 ),
	(null, 0, 0, false, 'CANCELLED', 100000 ),
	(null, 0, 0, false, 'CANCELLED', 50000 ) ;
	
INSERT INTO invoice_details (id, is_eliminated, quantity, total_price, id_invoice, id_product) VALUES
	(null, false, 3, 7500, 1, 1),
	(null, false, 2, 5000, 2, 1),
	(null, false, 1, 2500, 3, 1);



