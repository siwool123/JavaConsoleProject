package shopping;

import java.sql.SQLException;
import java.util.Scanner;

public class SelectShop extends ConnectDB {
	
	public SelectShop() {
		super("education", "1234");
	}

	public void execute() {
		boolean isFind = false;
		Scanner sc = new Scanner(System.in);
		System.out.print("검색할 상품명을 입력하세요 : ");
		String p_name = sc.nextLine();

		String sql = "SELECT * FROM sh_goods WHERE goods_name like '%"+p_name+"%' ";
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				System.out.println("입력하신 상품정보가 조회되었습니다.");
				String rdate = rs.getString(4).substring(0, 16);
				System.out.println(rs.getInt(1)+" | "+rs.getString(2)+" | "+rs.getInt(3)+" | "+rdate+" | "+rs.getInt(5));
				isFind = true;
			}
			if(isFind==false) System.out.println("입력하신 정보와 일치하는 상품이 없습니다.");
		}catch(SQLException e) {
			System.out.println("상품정보조회시 예외가 발생했습니다.\n"+e.getMessage());
		}finally {
			close();
		}
	}
	public static void main(String[] args) {
		new SelectShop().execute();
	}
}
