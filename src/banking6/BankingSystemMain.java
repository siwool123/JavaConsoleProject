package banking6;

import java.util.InputMismatchException;
import java.util.Scanner;

public class BankingSystemMain implements MenuChoice {
	static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		AccountManager manager = new AccountManager();
		AutoSaver as = new AutoSaver(manager.set);
		manager.loadData();
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
			}
//			catch(MenuSelectException e) {
//				System.out.println(e.getMessage());
//				sc.nextLine();
//				return;
//			}
			sc.nextLine();
			switch(choice) {
			case MenuChoice.MAKE: manager.makeAccount(); break;
			case MenuChoice.DEPOSIT: manager.depositMoney(); break;
			case MenuChoice.WITHDRAW: manager.withdrawMoney(); break;
			case MenuChoice.INQUIRE: manager.showAccInfo(); break;
			case MenuChoice.DELETE: manager.deleteAcc(); break;
			case MenuChoice.SAVE:
				System.out.println("***저장옵션***\n1. 자동저장 On    2. 자동저장 Off");
				int choice3 = sc.nextInt();
				sc.nextLine();
				switch(choice3) {
				case 1: 
					try{
					as.setDaemon(true);
					as.start();
					}catch(IllegalThreadStateException e) {
						System.out.println("이미 자동저장이 실행중입니다.");
						break;
					}
					break;
				case 2:
					as.interrupt();
					System.out.println("자동저장이 중단되었습니다.");
				}
				break;
			case MenuChoice.EXIT: manager.exit(); return;
			default : System.out.println("범위가 잘못되었습니다. 1~7사이의 숫자를 입력해주세요.");
			}
			}
	}
}
