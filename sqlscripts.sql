drop database timecard;
create database timecard;
use timecard;
create table dept(did int primary key, dname varchar(20));
create table projects(pid int primary key,dno int references dept(did),pname varchar(20),bonus int,salary int,deadline date,cust varchar(20));
create table emp(uid int primary key,f_name varchar(20),l_name varchar(20),sex varchar(1),dob date,type varchar(1),pass varchar(20),sanswer varchar(90),doj date,dno int references dept(did),pno int references projects(pid));

create table dependents(uno int ,name varchar(20),sex varchar(1),dob date,relation varchar(20),primary key(uno,name),foreign key(uno) references emp(uid));
create table works(eid int,pno int references projects(pid) ,w_date date,checkin time,checkout time,primary key (eid,w_date),foreign key (eid) references emp(uid));
create table leav(lid int primary key,start date,days int,reason varchar(200),status int,eno int references emp(uid));

insert into dept
(did,dname)
values
(10,'hr'),
(20,'prod'),
(30,'test');

-- changes dno
insert into projects
  (pid,dno,pname,bonus,salary,deadline,cust)
values
(102,10,'dsa',50,100,'2019-11-10','gopesh'),
(103,20,'dbms',60,90,'2018-12-10','shubham'),
(104,10,'cn',10,85,'2019-01-11','chetan'),
(105,30,'cnw',10,85,'2019-01-11','mayank'),
(106,30,'optimization',20,100,'2019-02-11','aditya'),
(107,20,'testing',30,90,'2019-01-01','mahaditya');

insert into emp
  (uid,f_name,l_name,sex,dob,type,pass,sanswer,doj,dno,pno)
values
(101,'gopesh','khandelwal','m','1997-03-08','e','abc123','dog','2013-10-16',10,102),
(102,'chandrahas','aroori','m','1997-04-05','e','abc123','cat','2015-08-04',20,103),
(103,'shubham','paliwal','m','1996-11-11','a','abc123','pig','2014-07-14',10,104),
(104,'chetan','vibandik','m','1996-11-16','a','abc123','lion','2013-11-18',10,102),
(105,'sparsh','sharma','m','1995-11-16','a','1234','horse','2012-12-12',10,102),
(106,'shivangi','sharma','f','1995-12-16','e','1234','dog','2011-11-11',20,103),
(107,'rajbir','singh','m','1995-12-17','e','1234','dog','2012-12-12',20,107),
(108,'pranay','panday','m','1997-12-17','a','abc123','dog','2013-12-11',30,106),
(109,'mihir','kumar','m','1997-12-13','a','abc123','cat','2015-12-12',10,102);
--
-- insert into emp
--   (uid,sanswer)
-- values
-- (101,'dog'),
-- (102,'cat'),
-- (103,'pig'),
-- (104,'lion');


-- pno added
insert into works
(eid,pno,w_date,checkin,checkout)

values
(101,102,'2018-02-01','08:00:00','16:00:00'),
(102,103,'2018-02-01','07:49:00','15:49:00'),
(103,104,'2018-02-11','07:50:00','18:49:00'),
(101,102,'2018-03-01','08:00:00','16:00:00'),
(102,103,'2018-03-01','07:49:00','15:49:00'),
(103,104,'2018-03-11','07:50:00','18:49:00'),
(101,102,'2018-04-01','08:00:00','16:00:00'),
(102,103,'2018-04-01','07:49:00','15:49:00'),
(103,104,'2018-04-11','07:50:00','18:49:00'),
(104,102,'2018-04-11','08:10:00','13:49:00');

insert into leav
(lid,start,days,reason,status,eno)
values
(1123,'2018-03-03',5,'Daughters Wedding',0,104),
(1456,'2018-02-02',3,'Sons Wedding',0,102);

insert into dependents
(uno,name,sex,dob,relation)
values
(101,'chandu','m','2008-11-06','son'),
(102,'mayank','m','2005-12-07','son'),
(103,'shalini','f','2004-01-01','daughter'),
(104,'mansi','f','2004-07-07','daughter'),
(105,'shalu','f','2004-12-12','daughter'),
(106,'rajbir','m','1994-12-15','husband'),
(107,'shalini','f','2004-01-01','daughter'),
(108,'shalini','f','2004-01-01','daughter');
