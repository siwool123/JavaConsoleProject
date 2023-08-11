package banking7.jdbc;

public class InsertSQL extends IConnectImpl {
	
	public InsertSQL() {
		super(ORACLE_DRIVER, "education", "1234");
	}

	public void execute() {
		try {
			System.out.println("***신규계좌개설***");
			String query = "INSERT INTO banking_tb VALUES (?, ?, ?, seq_banking.nextVal)";  
//			while(true) {
			psmt = con.prepareStatement(query);
			psmt.setString(1, scanValue("계좌번호"));
			psmt.setString(2, scanValue("고객명"));
			psmt.setString(3, scanValue("잔고"));
			int affected = psmt.executeUpdate();
			System.out.println(affected+" 건의 신규계좌개설이 완료되었습니다.");
//			}
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
