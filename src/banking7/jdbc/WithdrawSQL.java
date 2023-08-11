package banking7.jdbc;

import java.util.Scanner;

public class WithdrawSQL extends IConnectImpl {
	public static Scanner sc = new Scanner(System.in);
	
	public WithdrawSQL() {
		super("education", "1234");
	}
	
	public void execute() {
		boolean isFind = false;
		System.out.println("출금할 계좌번호를 입력하세요 : ");
		String accnum = sc.nextLine();
		String selectsql = "SELECT * FROM banking_tb WHERE accnum="+accnum;
		String sql = "UPDATE banking_tb SET balance=balance-? WHERE accnum=?";
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(selectsql);
			while(rs.next()) {
				System.out.println(rs.getInt(1)+" | "+rs.getString(2)+" | "+rs.getString(3)+" | "+rs.getInt(4));
				isFind=true;
			}
			if(isFind==false) System.out.println("일치하는 계좌가 없습니다.");
			else {
			psmt = con.prepareStatement(sql);
//			while(true) {
				psmt.setString(2, accnum);
				psmt.setString(1, scanValue("출금액"));
				int affected = psmt.executeUpdate();
				if(affected>0) System.out.println("출금이 완료되었습니다.");
				else System.out.println("계좌번호가 일치하지 않습니다.");
			}
		}catch(Exception e) {
			System.out.println("출금에 문제가 발생했습니다.\n"+e.getMessage());
		}finally {
			close();
		}
	}
	public static void main(String[] args) {
		new WithdrawSQL().execute();
	}
}
