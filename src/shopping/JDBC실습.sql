--Java에서 처음으로 JDBC 프로그래밍 해보기
--우선 system 계정으로 연결 후 새 실습계정 생성
alter session set "_ORACLE_SCRIPT"=true;
create user education identified by 1234;
grant connect, resource, unlimited tablespace to education;
create table member (
    id varchar2(30) not null,
    pass varchar2(40) not null,
    name varchar2(50) not null,
    regidate date default sysdate,
    primary key (id)    );
insert into member (id, pass, name) values ('test', 1234, '테스트');
commit;

insert into member (id, pass, name) values ('test9', 9999, '테스트9');
commit;
--HR계정연결후 다음 쿼리문 실행
select * from employees where department_id=50 order by employee_id desc;

------------------------------------------------------------------------------
--JDBC > CallableStatement 인터페이스 사용하기
--education 계정 에서 실습
select substr('hongildong', 1, 1) from dual;
select rpad('h', 10, '*') from dual;
select rpad(substr('hongildong', 1, 1), length('hongildong'), '*') from dual;

/* 예제1-1] 함수 : fillAsterik()
시나리오] 매개변수로 회원아이디(String)을 받으면 첫문자 제외한 나머지 부분을 *로 변환하는 함수를 생성하시오
예) oracle21c -> 0********    */
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

/* 예제2-1] 프로시저 : MyMemberInsert()
시나리오] member 테이블에 새로운 회원정보를 입력하는 프로시저를 생성하시오
    파라미터 : In => 아이디, 패스워드, 이름     Out => returnVal(성공:1, 실패:0) */
create or replace procedure MyMemberInsert 
    (id in varchar2, pw in varchar2, name in varchar2, rval out number)
is 
begin
    insert into member (id, pass, name) values (id, pw, name);
    if sql%found then --입력정상처리시 true반환
        rval := sql%rowcount; --입력성공한 행갯수 얻어와 out파라미터에 저장
        commit; --행변화생기므로 반드시 commit해야한다
    else rval := 0; --실패시 0반환
    end if;
end;
/
var res varchar2;
execute MyMemberInsert('pro02', '1234', '프로시저1', :res);
execute MyMemberInsert('pro03', '1234', '프로시저2', :res);
execute MyMemberInsert('pro04', '1234', '프로시저4', :res);
print res;

/* 예제3-1] 프로시저 : MyMemberDelete()
시나리오] member테이블에서 레코드를 삭제하는 프로시저를 생성하시오
    파라미터 : In => member_id(아이디)    Out => returnVal(SUCCESS/FAIL 반환) */
create or replace procedure MyMemberDelete (mid in varchar2, rval out varchar2)
is 
begin
    delete from member where id=mid;
    if sql%found then 
        rval := 'SUCCESS'; --회원레코드가 정삭삭제시 실행, 커밋한다. 
        commit; 
    else rval := 'FAIL'; 
    end if;
end;
/
var res2 varchar2(20);
execute MyMemberDelete('pro05', :res2);
execute MyMemberDelete('test99', :res2);
print res2;

/* 예제4-1] 프로시저 : MyMemberAuth()
시나리오] 아이디와 패스워드를 매개변수로 전달받아서 회원인지 여부를 판단하는 프로시저를 작성하시오. 
    매개변수 : In -> user_id, user_pass,    Out -> returnVal
    반환값 : 
        0 -> 회원인증실패(둘다틀림)
        1 -> 아이디는 일치하나 패스워드가 틀린경우
        2 -> 아이디/패스워드 모두 일치하여 회원인증 성공  */
create or replace procedure MyMemberAuth (uid in varchar2, upw in varchar2, rval out number)
is
    mcnt number(1) := 0; --count(*)로 반환되는값 저장
    mpw varchar2(30); --테이블에서 입력한 아이디와 일치하는 레코드중 비번 저장할 변수
begin
    select count(*) into mcnt from member where id=uid;
    if mcnt=1 then --회원아이디가 존재하는경우라면
        select pass into mpw from member where id=uid; --패스워드확인위해 두번째쿼리실행
        if mpw=upw then rval := 2; --인파라미터로 전달된 비번과 DB에서 가져온 비번 비교
        else rval := 1; --아이디만 일치한경우
        end if;
    else rval := 0; --아이디가 일치하지않는경우
    end if;
end;
/
var res_auth varchar2(1);
execute MyMemberAuth('pro07', '1234', :res_auth);
execute MyMemberAuth('pro07', '1111', :res_auth);
execute MyMemberAuth('pro08', '1111', :res_auth);
print res_auth;