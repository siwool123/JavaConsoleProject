package banking7.threeby3;

public class WithdrawSQL extends IConnectImpl {

	public WithdrawSQL() {
		super("education", "1234");
	}
	
	public void execute() {
		String sql = "UPDATE banking_tb SET balance=balance-? WHERE accnum=?";
		try {
			psmt = con.prepareStatement(sql);
			while(true) {
				psmt.setString(2, scanValue("출금할 계좌번호"));
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
