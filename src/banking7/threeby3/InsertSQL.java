package banking7.threeby3;

import java.util.Scanner;

public class InsertSQL extends IConnectImpl {
	
	public InsertSQL() {
		super(ORACLE_DRIVER, "education", "1234");
	}

	public void execute() {
		try {
			System.out.println("신규계좌개설을 위한 정보를 입력해주세요.");
			Scanner sc = new Scanner(System.in);
			System.out.println("계좌번호 : ");
			String accnum = sc.nextLine();
			System.out.println("고객명 : ");
			String name = sc.nextLine();
			System.out.println("잔액 : ");
			int balance = sc.nextInt();
			
			String query = "INSERT INTO banking_tb VALUES (seq_banking.nextVal, ?, ?, ?)";  
			psmt = con.prepareStatement(query);
			psmt.setString(1, accnum);
			psmt.setString(2, name);
			psmt.setInt(3, balance);
			int affected = psmt.executeUpdate();
			if(affected==1) System.out.println("신규계좌개설이 완료되었습니다.");
			else System.out.println("계좌번호 중복으로 계좌개설에 실패하였습니다.");
		}catch(Exception e) {
//			e.printStackTrace();
			System.out.println("계좌번호 중복으로 계좌개설에 실패하였습니다.");
		}finally {
			close();
		}
	}
	public static void main(String[] args) {
		new InsertSQL().execute();
	}

}
