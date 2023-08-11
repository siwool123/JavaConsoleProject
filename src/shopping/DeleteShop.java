package shopping;

import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

public class DeleteShop extends IConnectImpl {
	public static Scanner sc = new Scanner(System.in);
	public DeleteShop() {
		super("education", "1234");
	}

	public void execute() {
		boolean isFind = false;
		System.out.print("삭제할 상품 일련번호를 입력하세요 : ");
		int idx = sc.nextInt();
		String sql = "SELECT * FROM sh_goods WHERE g_idx="+idx;
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				System.out.println(rs.getInt(1)+" | "+rs.getString(2)+" | "+rs.getInt(3)+" | "+rs.getDate(4)+" | "+rs.getInt(5));
				isFind=true;
			}
			if(isFind==false) System.out.println("입력하신 정보와 일치하는 상품이 없습니다.");
			else {
			csmt = con.prepareCall("{call ShopDeleteGoods(?, ?)}");
			csmt.setInt(1, idx);
			csmt.registerOutParameter(2, Types.NUMERIC);
			csmt.execute();
			System.out.println(csmt.getInt(2)+" 행 삭제 성공");
			}
		}catch(SQLException e) {
			System.out.println("입력하신 정보와 일치하는 상품이 없습니다.");
		}finally {
			close();
		}
	}
	public static void main(String[] args) {
		new DeleteShop().execute();
	}

}
