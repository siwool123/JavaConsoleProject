package shopping;

import java.util.Scanner;

public class ShoppingSystemMain {
	public static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		while(true) {
			System.out.println("-----------Menu-----------\n1. 상품등록    2. 상품수정    3. 상품삭제    4. 상품조회    5. 프로그램 종료");
			System.out.print("--------------------------\n선택 : ");
			int choice = sc.nextInt();
			sc.nextLine();
			switch(choice) {
			case 1: new InsertShop().execute(); break;
			case 2: new UpdateShop().execute(); break;
			case 3: new DeleteShop().execute(); break;
			case 4: new SelectShop().execute(); break;
			case 5: System.out.println("프로그램 종료"); return;
			default : System.out.println("범위가 잘못되었습니다. 1~5사이의 숫자를 입력해주세요.");
			}
		}
	}
}
