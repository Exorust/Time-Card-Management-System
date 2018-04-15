create or replace procedure login_check(loginid in number) as
  select pass from emp where uid=loginid;$$
