package banking7.jdbc;

import java.util.Scanner;

public class BankingSystemMain implements MenuChoice {
	public static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		while(true) {
			showMenu();
			int choice = sc.nextInt();
			sc.nextLine();
			switch(choice) {
			case MenuChoice.MAKE: 
				new InsertSQL().execute(); break;
			case MenuChoice.DEPOSIT:
				new DepositSQL().execute(); break;
			case MenuChoice.WITHDRAW: 
				new WithdrawSQL().execute(); break;
			case MenuChoice.INQUIRE:
				new SelectAccount().execute(); break;
			case MenuChoice.EXIT:
				System.out.println("프로그램 종료"); return;
			default : System.out.println("범위가 잘못되었습니다. 1~5사이의 숫자를 입력해주세요.");
			}
		}
	}
	
	public static void showMenu() {
		System.out.println("-----------Menu-----------\n1. 계좌개설    2. 입금    3. 출금    4. 계좌정보출력    5. 프로그램 종료");
		System.out.print("--------------------------\n선택 : ");
	}

}
