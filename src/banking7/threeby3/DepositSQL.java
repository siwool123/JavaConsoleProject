package banking7.threeby3;

public class DepositSQL extends IConnectImpl {

	public DepositSQL() {
		super("education", "1234");
	}

	public void execute() {
		String sql = "UPDATE banking_tb SET balance=balance+? WHERE accnum=?";
		try {
			psmt = con.prepareStatement(sql);
			while(true) {
				psmt.setString(2, scanValue("입금할 계좌번호"));
				psmt.setString(1, scanValue("입금액"));
				int affected = psmt.executeUpdate();
				if(affected>0) System.out.println("입금이 완료되었습니다.");
				else System.out.println("계좌번호가 일치하지 않습니다.");
			}
		}catch(Exception e) {
//			e.printStackTrace();
			System.out.println("입금에 문제가 발생했습니다.\n"+e.getMessage());
		}finally {
			close();
		}
	}
	
	public static void main(String[] args) {
		new DepositSQL().execute();
	}

}
