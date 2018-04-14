create table dept(did int primary key, dname varchar(20));
create table emp(uid int primary key,f_name varchar(20),l_name varchar(20),sex varchar(1),dob date,type varchar(1),pass varchar(20),doj date,dno int references dept(did));
create table projects(pid int primary key,pname varchar(20),bonus int,salary int,deadline date,cust varchar(20));
create table dependents(uno int primary key references emp(uid),name varchar(20),sex varchar(1),dob date,relation varchar(20));
create table inout(eid int primary key references emp(uid),w_date date,in date );

insert into dept
(did,dname)
values
(10,'hr'),
(20,'prod'),
(30,'test');

insert into emp
  (uid,f_name,l_name,sex,dob,type,pass,doj,dno)
values
(101,'gopesh','khandelwal','m','12-11-1996','a','18-11-2013',10),
(102,'chandrahas','aroori','m','13-11-1996','a','23-11-2013',10),
(103,'shubham','paliwal','m','14-11-1996','a','21-11-2013',10),
(104,'chetan','vibandik','m','11-11-1996','a','21-11-2013',10);

insert into projects
  (pid,pname,bonus,salary,deadline,cust)
values
(102,'dsa',50,100,'10-11-2018','gopesh'),
(103,'dbms',60,90,'10-12-2018','shubham'),
(104,'cn',10,85,'11-01-2019','chetan');
