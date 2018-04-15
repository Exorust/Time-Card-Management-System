drop database timecard;
create database timecard;
use timecard;
create table dept(did int primary key, dname varchar(20));
create table emp(uid int primary key,f_name varchar(20),l_name varchar(20),sex varchar(1),dob date,type varchar(1),pass varchar(20),doj date,dno int references dept(did));
create table projects(pid int primary key,pname varchar(20),bonus int,salary int,deadline date,cust varchar(20));
create table dependents(uno int primary key references emp(uid),name varchar(20),sex varchar(1),dob date,relation varchar(20));
create table works(eid int primary key references emp(uid),w_date date,checkin time,checkout time);

insert into dept
(did,dname)
values
(10,'hr'),
(20,'prod'),
(30,'test');

insert into emp
  (uid,f_name,l_name,sex,dob,type,pass,doj,dno)
values
(101,'gopesh','khandelwal','m','1997-03-08','a','abc123','2013-10-16',10),
(102,'chandrahas','aroori','m','1997-04-05','a','abc123','2015-08-04',10),
(103,'shubham','paliwal','m','1996-11-11','a','abc123','2014-07-14',10),
(104,'chetan','vibandik','m','1996-11-16','a','abc123','2013-11-18',10);

insert into projects
  (pid,pname,bonus,salary,deadline,cust)
values
(102,'dsa',50,100,'2017-11-10','gopesh'),
(103,'dbms',60,90,'2018-12-10','shubham'),
(104,'cn',10,85,'2017-01-11','chetan');

insert into works
(eid,w_date,checkin,checkout)

values
(101,'2018-02-01','08:00:00','16:00:00'),
(102,'2018-02-01','07:49:00','15:49:00'),
(103,'2018-02-11','07:50:00','18:49:00'),
(104,'2018-02-11','08:10:00','13:49:00');

