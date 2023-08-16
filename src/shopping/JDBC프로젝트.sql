create table member (
    id varchar2(30) not null,
    pass varchar2(40) not null,
    name varchar2(50) not null,
    regidate date default sysdate,
    primary key (id)    );
insert into member (id, pass, name) values ('test', 1234, '�׽�Ʈ');
commit;

create table banking_tb (
    id varchar2(30) primary key,
    name varchar2(30) not null,
    bal number not null);
commit;

create sequence seq_banking
     increment by 1
     start with 1
     minValue 1
     maxValue 50
     cycle
     nocache;
alter sequence seq_banking
     nocycle
     nocache;
select * from user_sequences;
commit;
alter table banking_tb rename column id to accNum;
commit;
insert into banking_tb values (111, '����ȭ', 5000);
alter table banking_tb add sequence number;
drop table banking_tb;
create table banking_tb (
    sequence number,
    accnum varchar2(30) primary key,
    name varchar2(30) not null,
    bal number not null);
commit;
insert into banking_tb values (seq_banking.nextVal, 111, '����ȭ', 5000);
insert into banking_tb values (seq_banking.nextVal, 222, 'ȫ�浿', 5000);
select * from banking_tb;
create table sh_product_code (
    p_code number(10) primary key,
    category_name varchar2(20) not null
);
commit;
create table sh_goods (
    g_idx number(10) primary key,
    goods_name varchar2(20) not null,
    goods_price number(10),
    regidate date default sysdate,
    p_code number(10),
    foreign key(p_code) references sh_product_code (p_code)
);
commit;
create table sh_goods_log (
    log_idx number(10) primary key,
    goods_name varchar2(20),
    goods_idx number(10),
    p_action varchar2(20) check(p_action in ('Insert', 'Delete'))
);
commit;
create sequence seq_total_idx
     increment by 1
     start with 1
     minvalue 1
     nomaxvalue
     nocycle
     nocache;
commit;
select * from user_sequences;
alter table sh_goods add sequence number(10);
alter table sh_goods_log add sequence number(10);
alter table sh_product_code add sequence number(10);

insert into sh_product_code values (100, '����', seq_total_idx.nextval);
insert into sh_product_code values (200, '����', seq_total_idx.nextval);
insert into sh_product_code values (300, '�Ƿ�', seq_total_idx.nextval);
insert into sh_product_code values (400, '��ǰ', seq_total_idx.nextval);
insert into sh_product_code values (500, '����', seq_total_idx.nextval);

insert into sh_goods values (101, '�����', 400000, null, 100, seq_total_idx.nextval);
update sh_goods set regidate=sysdate where g_idx=101;
alter table sh_goods modify regidate not null;
delete from sh_goods;
delete from sh_product_code;
alter table sh_goods drop column sequence;
alter table sh_product_code drop column sequence;
alter table sh_goods_log drop column sequence;

drop sequence seq_total_idx;
commit;
create sequence seq_total_idx
     increment by 1
     start with 1
     minvalue 1
     nomaxvalue
     nocycle
     nocache;
commit;
drop sequence seq_total_idx;
commit;
create sequence seq_total_idx
     increment by 1
     start with 1
     minvalue 1
     nomaxvalue
     nocycle
     nocache;
commit;
drop sequence seq_total_idx;
create sequence seq_total_idx
     increment by 1
     start with 1
     minvalue 1
     nomaxvalue
     nocycle
     nocache;
commit;
delete from sh_product_code;
insert into sh_product_code values (seq_total_idx.nextval, '����');
insert into sh_product_code values (seq_total_idx.nextval, '����');
insert into sh_product_code values (seq_total_idx.nextval, '�Ƿ�');
insert into sh_product_code values (seq_total_idx.nextval, '��ǰ');
insert into sh_product_code values (seq_total_idx.nextval, '����');

insert into sh_goods values (seq_total_idx.nextval, '��Ź��', 400000, sysdate, 1);
insert into sh_goods values (seq_total_idx.nextval, '�����', 800000, sysdate, 1);
insert into sh_goods values (seq_total_idx.nextval, '���ǿ���', 10000, sysdate, 2);
insert into sh_goods values (seq_total_idx.nextval, '�ѱռ�', 20000, sysdate, 2);
insert into sh_goods values (seq_total_idx.nextval, '���е�', 80000, sysdate, 3);
insert into sh_goods values (seq_total_idx.nextval, 'û����', 60000, sysdate, 3);
insert into sh_goods values (seq_total_idx.nextval, '����', 40000, sysdate, 4);
insert into sh_goods values (seq_total_idx.nextval, '����', 20000, sysdate, 4);
insert into sh_goods values (seq_total_idx.nextval, '����', 40000, sysdate, 5);
insert into sh_goods values (seq_total_idx.nextval, '���̺�', 80000, sysdate, 5);
commit;

delete from sh_goods where g_idx between 19 and 20;
commit;

create or replace procedure ShopUpdateGoods 
(pname in varchar2, price in number, pcode in number, idx in number, rslt out number)
is
begin
    update sh_goods set goods_name=pname, goods_price=price, p_code=pcode where g_idx=idx;
    if sql%found then
        rslt := 1;
        commit;
    else rslt := 0;
    end if;
end;
/

create or replace procedure ShopDeleteGoods 
(idx in number, rslt out number)
is
begin
    delete from sh_goods where g_idx=idx;
    if sql%found then
        rslt := 1;
        commit;
    else rslt := 0;
    end if;
end;
/

SELECT * FROM banking_tb WHERE accnum='555-555';
delete from banking_tb;
alter table banking_tb modify sequence invisible;
alter table banking_tb modify sequence visible;
insert into banking_tb values (seq_banking.nextVal, 111-111, '����ȭ', 5000);
commit;
SELECT * FROM banking_tb WHERE accnum='111-111';

create or replace trigger shop_log_trigger 
    after insert or delete on sh_goods for each row
declare
begin
    if inserting then
        insert into sh_goods_log values (seq_total_idx.nextval, :new.goods_name, :new.g_idx, 'Insert');
    elsif deleting then 
        insert into sh_goods_log values (seq_total_idx.nextval, :old.goods_name, :old.g_idx, 'Delete'); 
    end if;
end;
/
insert into sh_goods values (seq_total_idx.nextval, '���콺', 30000, sysdate, 3);
select * from user_constraints;
