package banking1;

import java.util.Scanner;

public class BankingSystemMain implements MenuChoice {
	public static Scanner sc = new Scanner(System.in);
	static Account[] accounts = new Account[50];
	static int cnt=0;
	
	public static void main(String[] args) {
		while(true) {
			showMenu();
			int choice = sc.nextInt();
			sc.nextLine();
			switch(choice) {
			case MenuChoice.MAKE: 
				makeAccount(); break;
			case MenuChoice.DEPOSIT:
				depositMoney(); break;
			case MenuChoice.WITHDRAW: 
				withdrawMoney(); break;
			case MenuChoice.INQUIRE:
				showAccInfo(); break;
			case MenuChoice.EXIT:
				System.out.println("프로그램 종료"); return;
			default : System.out.println("범위가 잘못되었습니다. \n1~5사이의 숫자를 입력해주세요.");
			}
		}
	}
	
	public static void showMenu() {
		System.out.println("-------Menu-------\n1. 계좌개설 \n2. 입금 \n3. 출금 \n4. 전체계좌정보출력 \n5. 프로그램 종료\n선택 : ");
	}

	public static void makeAccount() {
		String iaccNum, iname;
		int ibal;
		System.out.println("***신규계좌개설***\n계좌번호 : ");
		iaccNum = sc.nextLine();
//		sc.nextLine();
		System.out.println("고객명 : ");
		iname = sc.nextLine();
		System.out.println("잔고 : ");
		ibal = sc.nextInt();
		Account addAcc = new Account(iaccNum, iname, ibal);
		accounts[cnt++] = addAcc;
		System.out.println("신규계좌 개설이 완료되었습니다.");
	}
	
	public static void depositMoney() {
		boolean isFind = false;
		String iaccNum;
		int iMoney;
		System.out.println("***입금***\n계좌번호와 입금할 금액을 입력하세요.\n계좌번호 : ");
		iaccNum = sc.nextLine();
		System.out.println("입금액 : ");
		iMoney = sc.nextInt();
		
		for(int i=0; i<cnt; i++) {
			if(accounts[i].getAccNum().equals(iaccNum)) {
				accounts[i].setBal(accounts[i].getBal()+iMoney);
				System.out.println("입금이 완료되었습니다.");
				isFind = true;
			}
		}
		if(isFind==false) System.out.println("--- 입력하신 계좌가 없습니다 ---");
	}
	
	public static void withdrawMoney() {
		Scanner sc = new Scanner(System.in);
		boolean isFind = false;
		String iaccNum;
		int iMoney;
		System.out.println("***출금***\n계좌번호와 출금할 금액을 입력하세요.");
		System.out.println("계좌번호 : ");
		iaccNum = sc.nextLine();
		System.out.println("출금액 : ");
		iMoney = sc.nextInt();
		for(int i=0; i<cnt; i++) {
			if(accounts[i].getAccNum().equals(iaccNum)) {
				accounts[i].setBal(accounts[i].getBal()-iMoney);
				System.out.println("출금이 완료되었습니다.");
				isFind = true;
			}
		}
		if(isFind==false) System.out.println("--- 입력하신 계좌가 없습니다 ---");
	}
	
	public static void showAccInfo() {
		System.out.println("***계좌정보출력***");
		for(int i=0; i<cnt; i++) {
			accounts[i].showAccInfo();
		}		
		System.out.println("전체계좌정보 출력이 완료되었습니다.");
	}
}
