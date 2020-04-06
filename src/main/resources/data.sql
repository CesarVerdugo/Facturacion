insert into roles (id,name,description) values (1,'ADMIN',null);
insert into roles (id,name,description) values (2,'USER',null);
insert into roles (id,name,description) values (3,'CUSTOMER',null);
insert into roles (id,name,description) values (4,'PROVIDER',null);

insert into users (id, email,is_active,is_eliminated) values (null,'csv@verdugo.com',true,false);

insert into users_has_roles (id_users,id_roles)values (1,1);

insert into categories (id,name,is_eliminated) values (null,'tecnologia',false);
insert into categories (id,name,is_eliminated) values (null,'Aseo',false);

insert into products (id, is_eliminated,min_stock,name,prece,stock,id_category) values (null,false,5,'Computador',500000,20,1);

INSERT INTO invoices (id, amount_owed, amount_paid, is_eliminated, payment_status, total_price) VALUES 
	(null, 0, 0, false, 'CANCELED', 120000 ),
	(null, 0, 0, false, 'CANCELED', 100000 ),
	(null, 0, 0, false, 'CANCELED', 50000 ) ;
	
INSERT INTO invoice_details (id, is_eliminated, quantity, total_price, id_invoice, id_product) VALUES
	(null, false, 3, 7500, 1, 1),
	(null, false, 2, 5000, 2, 1),
	(null, false, 1, 2500, 3, 1);



