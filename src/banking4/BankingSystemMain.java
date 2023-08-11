package banking4;

import java.util.InputMismatchException;
import java.util.Scanner;

public class BankingSystemMain implements MenuChoice {
	public static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		AccountManager manager = new AccountManager();
		while(true) {
			manager.showMenu();
			int choice =0;
			try {
			choice = sc.nextInt();
//			if(choice<1||choice>6) {
//				MenuSelectException ex = new MenuSelectException();
//				throw ex;}
			}catch(InputMismatchException e) {
				System.out.println("[예외발생] 숫자만 입력하세요. ");
				sc.nextLine();
				return;
			}
//			catch(MenuSelectException e) {
//				System.out.println(e.getMessage());
//				sc.nextLine();
//				return;
//			}
			sc.nextLine();
			switch(choice) {
			case MenuChoice.MAKE: 
				manager.makeAccount(); break;
			case MenuChoice.DEPOSIT:
				manager.depositMoney(); break;
			case MenuChoice.WITHDRAW: 
				manager.withdrawMoney(); break;
			case MenuChoice.INQUIRE:
				manager.showAccInfo(); break;
			case MenuChoice.DELETE:
				manager.deleteAcc(); break;
			case MenuChoice.EXIT:
				System.out.println("프로그램 종료"); return;
			default : System.out.println("범위가 잘못되었습니다. \n1~5사이의 숫자를 입력해주세요.");
			}
			}
	}
}
