package banking7.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/* 인터페이스를 구현한 클래스로 extends 대신 implements 를 사용
 * 또한 용어도 '상속' 이 아닌 '구현' 이라 표현한다. */
public class IConnectImpl implements IConnect{
	//멤버변수
	public Connection con; //DB연결
	public PreparedStatement psmt; //동적쿼리문실행
	public ResultSet rs; //select 실행결과 반환
	
	public Statement stmt; //정적쿼리 실행
	public CallableStatement csmt; //프로시저 실행
	//생성자1:기본
	public IConnectImpl() {
		System.out.println("IConnectImpl 기본생성자 호출");
	}
	//생성자2 : 아이디, 비번을 매개변수로 받음
	public IConnectImpl(String user, String pass) {
		try {
			Class.forName(ORACLE_DRIVER); //인터페이스에 선언된 멤버상수를 그대로 사용
			connect(user, pass); //매개변수로 받은 계정정보사용
		}catch(ClassNotFoundException e){
			System.out.println("드라이버 로딩 실패");
			e.printStackTrace();
		}
	}
	//생성자3 : 드라이버명, 아이디, 비번을 매개변수로 받음
	public IConnectImpl(String driver, String user, String pass) {
		try {
			Class.forName(driver);
			connect(user, pass);
		}catch(ClassNotFoundException e){
			System.out.println("드라이버 로딩 실패");
			e.printStackTrace();
		}
	}
	//DB연결 
	public void connect(String user, String pass) {
		try {
			con = DriverManager.getConnection(ORACLE_URL, user, pass);
		}catch(SQLException e) {
			System.out.println("데이터베이스 연결오류");
			e.printStackTrace();
		}
	}
	public void execute() {
//하는일없음. 오버라이딩 목적으로 정의한 메소드로 각 클래스에서 목적에맞게 재정의하면된다.
	}
	//자원반납
	public void close() {
		try {
			if(psmt!=null) psmt.close(); //연결확인후 자원해제한다
			else if(con!=null) con.close();
			else if(rs!=null) rs.close();
			else if(stmt!=null) stmt.close(); //필요한경우 활성화하여 사용하면된다
			else if(csmt!=null) csmt.close();
			System.out.println("DB자원반납완료");
		}catch(Exception e){
			System.out.println("자원반납시 오류가 발생했습니다.");
			e.printStackTrace();
		}
	}
	//사용자로부터 입력값 받기위한 메소드
	public String scanValue (String title) {
		Scanner sc = new Scanner(System.in);
		System.out.println(title+" 을/를 입력 (exit > 종료)");
		String inputStr = sc.nextLine();
/* equalsIgnoreCase() : equals()와 동일하게 문자열이 동일한지 비교하는 메소드로 
 * 대소문자 구분않고 비교한다. 즉 EXIT 와 exit 를 같은문자열로판단 */
		if("EXIT".equalsIgnoreCase(inputStr)) {
			System.out.println("프로그램을 종료합니다. ");
			close(); //자원반납
			System.exit(0);	//프로그램 자체가 즉시 종료된다.
		}
		return inputStr; //exit입력이 아니라면 사용자가 입력한값을 반환한다
	}
}
