package banking3;

import java.util.InputMismatchException;
import java.util.Scanner;

public class AccountManager implements MenuChoice {
	public static Scanner sc = new Scanner(System.in);
	static Account[] accounts = new Account[50];
	static int cnt=0;
//	public AccountManager(int num) {
//		this.accounts = new Account[num];
//		cnt = 0;
//	}
		
	public static void showMenu() {
		System.out.println("-------Menu-------\n1. 계좌개설 \n2. 입금 \n3. 출금 \n4. 전체계좌정보출력 \n5. 프로그램 종료\n선택 : ");
	}
	
	public void makeAccount() {
		String iaccNum, iname;
		int ibal, binter;
		char grade;
		System.out.println("***신규계좌개설***\n1. 보통계좌 \n2. 신용신뢰계좌\n선택 : ");
		int choice2 = 0;
		try {
		choice2 = sc.nextInt();
		if(choice2<1||choice2>2) {
			System.out.println("범위가 잘못되었습니다. \n1~2사이의 숫자를 입력해주세요.");
			return;
		}
		}catch(InputMismatchException e) {
			System.out.println("[예외발생] 숫자만 입력하세요.");
			sc.nextLine();
			return;
		}
				
		sc.nextLine();
		System.out.println("계좌번호 : ");
		iaccNum = sc.nextLine();
		System.out.println("고객명 : ");
		iname = sc.nextLine();
		System.out.println("잔고 : ");
		ibal = sc.nextInt();
		
		switch(choice2) {
		case 1 :
			System.out.println("기본이자%(정수로 입력) : ");
			binter = sc.nextInt();
			Account addNAcc = new NormalAccount(iaccNum, iname, ibal, binter);
			accounts[cnt++] = addNAcc;
			break;
		case 2 : 
			System.out.println("기본이자%(정수로 입력) : ");
			binter = sc.nextInt();
			System.out.println("신용등급(A,B,C 등급) : ");
			grade = sc.next().charAt(0);
			Account addHAcc = new HighCreditAccount (iaccNum, iname, ibal, binter, grade);
			accounts[cnt++] = addHAcc;
//		default : 
//			System.out.println("범위가 잘못되었습니다. \n1~2사이의 숫자를 입력해주세요.");
		}
		System.out.println("신규계좌 개설이 완료되었습니다.");
	}
	
	public void depositMoney() {
		int iMoney = 0;
		sc.nextLine();
		System.out.println("***입금***\n계좌번호와 입금할 금액을 입력하세요.\n계좌번호 : ");
		String iaccNum = sc.nextLine();

		System.out.println("입금액 : ");
//		while(true) {
		try{
			iMoney = sc.nextInt();
			if(iMoney<0||iMoney%500!=0) {
				System.out.println("500원 단위의 양수만 입금 가능합니다.");
			}else {
				for(int i=0; i<cnt; i++) {
					if(accounts[i].getAccNum().equals(iaccNum)) {
						accounts[i].plusMoney(iMoney);
						System.out.println("입금이 완료되었습니다.");
						break;
					}else System.out.println("--- 입력하신 계좌가 없습니다 ---");
				}
			}
		}catch(InputMismatchException e) {
			System.out.println("[예외발생] 숫자만 입력하세요.");
			sc.nextLine();
		}
//		}
	}
	
	public void withdrawMoney() {
		int iMoney = 0;
		sc.nextLine();
		System.out.println("***출금***\n계좌번호와 출금할 금액을 입력하세요.\n계좌번호 : ");
		String iaccNum = sc.nextLine();
		System.out.println("출금액 : ");
//		while(true) {
		try{
			iMoney = sc.nextInt();
			if(iMoney<0||iMoney%1000!=0) {
				System.out.println("1000원 단위의 양수만 출금 가능합니다.");
			}else {
				for(int i=0; i<cnt; i++) {
					if(accounts[i].getAccNum().equals(iaccNum)) {
						if(iMoney<=accounts[i].getBal()) {
							accounts[i].minusMoney(iMoney);
							System.out.println("출금이 완료되었습니다.");
							break;
						}else {
							System.out.println("잔고가 부족합니다. \n금액전체를 출금할까요? (Y/N)");
//							while(true) {
							char a = sc.next().charAt(0);
							switch(a) {
							case 'Y': case 'y': 
								System.out.println(accounts[i].getBal()+" 원 (잔액 전부) 출금 완료되었습니다.");
								accounts[i].setBal(0);									
								break;
							case 'N': case 'n': 
								System.out.println("출금이 취소되었습니다.");
								return;
							default : System.out.println("문자 Y 또는 N 만 입력가능합니다. ");
							}
//							}
						}
					}else System.out.println("--- 입력하신 계좌가 없습니다 ---");
				}
			}
		}catch(InputMismatchException e) {
			System.out.println("[예외발생] 숫자만 입력하세요.");
			sc.nextLine();
		}
//		}
	}
	
	public static void showAccInfo() {
		System.out.println("***계좌정보출력***");
		for(int i=0; i<cnt; i++) {
			System.out.println("---------------");
			accounts[i].showAccInfo();
			System.out.println("---------------");
		}		
		System.out.println("전체계좌정보 출력이 완료되었습니다.");
	}
}
