package shopping;

import java.util.Scanner;

public class InsertShop extends IConnectImpl {
	
	public InsertShop() {
		super(ORACLE_DRIVER, "education", "1234");
	}
	
	public void execute() {
		String sql = "INSERT INTO sh_goods VALUES (seq_total_idx.nextVal, ?, ?, sysdate, ?)";	
		try {
			psmt = con.prepareStatement(sql);
			psmt.setString(1, scanValue("상품명"));
			psmt.setString(2, scanValue("상품가격"));
			psmt.setString(3, scanValue("상품코드"));
			int row = psmt.executeUpdate();
			System.out.println(row+" 행이 입력되었습니다.");
			if(row<0) System.out.println("상품명이 중복됩니다.");
		}catch(Exception e) {
			System.out.println("상품정보 등록에 오류가 발생했습니다.\n"+e.getMessage());
		}finally {
			close();
		}
	}
	
	public static void main(String[] args) {
		new InsertShop().execute();
	}

}
