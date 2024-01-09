create database jdbc_db;
use jdbc_db;
create table employee(
emp_id int primary key,
ename varchar (200),
salary int
);

insert into employee values (1,"Ilma", 20000);
insert into employee values (2,"Kamal", 30000);
insert into employee values (3,"Rishi", 40000);


use jdbc_db;
-- create procedure
delimiter $$
create procedure GetEmp()
begin
select * from employee;
end$$
delimiter ;

SELECT * FROM jdbc_db.employee;

call GetEmp();

DROP PROCEDURE IF EXISTS GetEmp;
