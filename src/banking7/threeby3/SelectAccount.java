package banking7.threeby3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class SelectAccount {

	public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("조회할 계좌번호를 입력하세요 : ");
        String accnum = sc.nextLine();
        boolean isFind = false;
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String id = "education";
		String pw = "1234";
		String sql = "SELECT * FROM banking_tb WHERE accnum like '%'||"+accnum+"||'%'";
		
	try {
		Class.forName("oracle.jdbc.OracleDriver");
		Connection con = DriverManager.getConnection(url, id, pw);
		Statement stmt = con.createStatement(); //statement 객체생성
		ResultSet rs = stmt.executeQuery(sql);
//		System.out.println(rs);
		while(rs.next()) {
			System.out.println("입력하신 계좌정보 조회에 성공했습니다.");
			System.out.println(rs.getInt(1)+" | "+rs.getString(2)+" | "+rs.getString(3)+" | "+rs.getInt(4));
			isFind = true;
		}
		if(isFind==false) System.out.println("입력하신 정보와 일치하는 계좌가 없습니다.");
		rs.close();
		stmt.close();
		con.close();
	}catch(ClassNotFoundException | SQLException e) {
		System.out.println("입력하신 계좌정보 조회 시 예외가 발생했습니다.");
		e.printStackTrace();
	}		
	}

}
