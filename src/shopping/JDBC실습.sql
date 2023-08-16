--Java���� ó������ JDBC ���α׷��� �غ���
--�켱 system �������� ���� �� �� �ǽ����� ����
alter session set "_ORACLE_SCRIPT"=true;
create user education identified by 1234;
grant connect, resource, unlimited tablespace to education;
create table member (
    id varchar2(30) not null,
    pass varchar2(40) not null,
    name varchar2(50) not null,
    regidate date default sysdate,
    primary key (id)    );
insert into member (id, pass, name) values ('test', 1234, '�׽�Ʈ');
commit;

insert into member (id, pass, name) values ('test9', 9999, '�׽�Ʈ9');
commit;
--HR���������� ���� ������ ����
select * from employees where department_id=50 order by employee_id desc;

------------------------------------------------------------------------------
--JDBC > CallableStatement �������̽� ����ϱ�
--education ���� ���� �ǽ�
select substr('hongildong', 1, 1) from dual;
select rpad('h', 10, '*') from dual;
select rpad(substr('hongildong', 1, 1), length('hongildong'), '*') from dual;

/* ����1-1] �Լ� : fillAsterik()
�ó�����] �Ű������� ȸ�����̵�(String)�� ������ ù���� ������ ������ �κ��� *�� ��ȯ�ϴ� �Լ��� �����Ͻÿ�
��) oracle21c -> 0********    */
create or replace function fillAsterik (id varchar2)
return varchar2 
is st varchar2(30); 
begin
    st := rpad(substr(id, 1, 1), length(id), '*');
    return st;
end;
/
select fillAsterik('Nancy') from dual;
select fillAsterik(id) from member;

/* ����2-1] ���ν��� : MyMemberInsert()
�ó�����] member ���̺� ���ο� ȸ�������� �Է��ϴ� ���ν����� �����Ͻÿ�
    �Ķ���� : In => ���̵�, �н�����, �̸�     Out => returnVal(����:1, ����:0) */
create or replace procedure MyMemberInsert 
    (id in varchar2, pw in varchar2, name in varchar2, rval out number)
is 
begin
    insert into member (id, pass, name) values (id, pw, name);
    if sql%found then --�Է�����ó���� true��ȯ
        rval := sql%rowcount; --�Է¼����� �హ�� ���� out�Ķ���Ϳ� ����
        commit; --�ຯȭ����Ƿ� �ݵ�� commit�ؾ��Ѵ�
    else rval := 0; --���н� 0��ȯ
    end if;
end;
/
var res varchar2;
execute MyMemberInsert('pro02', '1234', '���ν���1', :res);
execute MyMemberInsert('pro03', '1234', '���ν���2', :res);
execute MyMemberInsert('pro04', '1234', '���ν���4', :res);
print res;

/* ����3-1] ���ν��� : MyMemberDelete()
�ó�����] member���̺��� ���ڵ带 �����ϴ� ���ν����� �����Ͻÿ�
    �Ķ���� : In => member_id(���̵�)    Out => returnVal(SUCCESS/FAIL ��ȯ) */
create or replace procedure MyMemberDelete (mid in varchar2, rval out varchar2)
is 
begin
    delete from member where id=mid;
    if sql%found then 
        rval := 'SUCCESS'; --ȸ�����ڵ尡 ��������� ����, Ŀ���Ѵ�. 
        commit; 
    else rval := 'FAIL'; 
    end if;
end;
/
var res2 varchar2(20);
execute MyMemberDelete('pro05', :res2);
execute MyMemberDelete('test99', :res2);
print res2;

/* ����4-1] ���ν��� : MyMemberAuth()
�ó�����] ���̵�� �н����带 �Ű������� ���޹޾Ƽ� ȸ������ ���θ� �Ǵ��ϴ� ���ν����� �ۼ��Ͻÿ�. 
    �Ű����� : In -> user_id, user_pass,    Out -> returnVal
    ��ȯ�� : 
        0 -> ȸ����������(�Ѵ�Ʋ��)
        1 -> ���̵�� ��ġ�ϳ� �н����尡 Ʋ�����
        2 -> ���̵�/�н����� ��� ��ġ�Ͽ� ȸ������ ����  */
create or replace procedure MyMemberAuth (uid in varchar2, upw in varchar2, rval out number)
is
    mcnt number(1) := 0; --count(*)�� ��ȯ�Ǵ°� ����
    mpw varchar2(30); --���̺��� �Է��� ���̵�� ��ġ�ϴ� ���ڵ��� ��� ������ ����
begin
    select count(*) into mcnt from member where id=uid;
    if mcnt=1 then --ȸ�����̵� �����ϴ°����
        select pass into mpw from member where id=uid; --�н�����Ȯ������ �ι�°��������
        if mpw=upw then rval := 2; --���Ķ���ͷ� ���޵� ����� DB���� ������ ��� ��
        else rval := 1; --���̵� ��ġ�Ѱ��
        end if;
    else rval := 0; --���̵� ��ġ�����ʴ°��
    end if;
end;
/
var res_auth varchar2(1);
execute MyMemberAuth('pro07', '1234', :res_auth);
execute MyMemberAuth('pro07', '1111', :res_auth);
execute MyMemberAuth('pro08', '1111', :res_auth);
print res_auth;