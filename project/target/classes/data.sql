insert into user values (1,'123','ADMIN','slava');
insert into user values (2,'123','ADMIN','viktor');
insert into user values (3,'123','ADMIN','Bob');

insert into customer values (4,'123','CUST','cust');
insert into customer values (5,'123','CUST','b');
insert into customer values (6,'123','CUST','c');

insert into user values (7,'123','COMP','comp');
insert into user values (8,'123','COMP','e');
insert into user values (9,'123','COMP','f');

insert into coupon values (1,20,20100505,'bob',5,20230505,'ONSALE','abc','FOOD');
insert into coupon values (2,20,20100505,'bob',5,20210505,'ONSALE','abc','FOOD');
insert into coupon values (3,20,20100505,'bob',5,20220505,'ONSALE','abc','FOOD');
insert into coupon values (4,20,20100505,'bob',5,20200505,'ONSALE','abc','FOOD');
insert into coupon values (5,20,20100505,'bob',5,20210505,'ONSALE','abc','FOOD');
insert into coupon values (100,20,20220505,'bob',5,20110505,'ONSALE','abc','FOOD');
insert into coupon values (101,20,20220505,'bob',5,20110505,'ONSALE','abc','FOOD');

insert into user_coupons values (2,5);

insert into customer_coupons values (2,5);