select pass from emp where uid=______;   //prints password of a given id
select sanswer from emp where uid=_____; // prints security answer
insert into works values(eid,date,in,out);  // to insert in and out time of employ
select status form leav where eno=______;  //to check status of leav application
insert into leav values(lid(random),start,days,reason,0,eno); // apply for leave
update emp set f_name=___,l_name=____,sex=_,dob=______,pass=_____ where uid=____;  // update employee values
update emp set name=___,sex=____,dob=_____,relation=___ where uno=___ and name=_____;   // update dependents

/*select count(*) from works where eid=____ && to_char(w_date,'yyyy')=to_char(select to_char(sysdate,'yyyy') from dual) && to_char(w_date,'mm')=to_char(sysdate,'mm');*/
update leav set status=___ where lid=__ ;
select * from leav where eno=_____; check leaves of particular employee
