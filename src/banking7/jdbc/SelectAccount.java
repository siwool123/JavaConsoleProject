package banking7.jdbc;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class SelectAccount extends IConnectImpl {
	public static Scanner sc = new Scanner(System.in);
	
	public SelectAccount() {
		super("education", "1234");
	}

	public void execute() {
		System.out.print("조회할 계좌번호를 입력하세요 : ");
        String accnum = sc.nextLine();
        boolean isFind = false;
		String sql = "SELECT * FROM banking_tb WHERE accnum like '%"+accnum+"%'";
		
	try {
		Statement stmt = con.createStatement(); //statement 객체생성
		ResultSet rs = stmt.executeQuery(sql);
		while(rs.next()) {
			System.out.println("입력하신 계좌정보 조회에 성공했습니다.");
			System.out.println(rs.getString(1)+" | "+rs.getString(2)+" | "+rs.getString(3)+" | "+rs.getString(4));
			isFind = true;
		}
		if(isFind==false) System.out.println("입력 정보와 일치하는 계좌가 없습니다.");
		rs.close();
		stmt.close();
		con.close();
	}catch(Exception e) {
		System.out.println("입력 계좌정보 조회시 예외가 발생했습니다.");
		e.printStackTrace();
	}		
	}
	
	public static void main(String[] args) {
        new SelectAccount().execute();
	}
}
