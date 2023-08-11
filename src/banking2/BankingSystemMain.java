package banking2;

import java.util.InputMismatchException;
import java.util.Scanner;

public class BankingSystemMain implements MenuChoice {
	public static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		AccountManager manager = new AccountManager();
		try {
		while(true) {
			manager.showMenu();
			int choice = sc.nextInt();
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
			case MenuChoice.EXIT:
				System.out.println("프로그램 종료"); return;
			default : System.out.println("범위가 잘못되었습니다. \n1~5사이의 숫자를 입력해주세요.");
			}
			}
			}catch(InputMismatchException e) {
				System.out.println("잘못입력하셨습니다. 숫자가 아닙니다.");
			}
	}
}
