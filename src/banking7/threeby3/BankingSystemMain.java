package banking7.threeby3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Scanner;

public class BankingSystemMain implements MenuChoice {
	public static Scanner sc = new Scanner(System.in);
	static Account[] accounts = new Account[50];
	static int cnt=0;
	static ArrayList<Character> list = new ArrayList<>(Arrays.asList('1', '2', '3', '4', '5', '6', '7', '8', 'X'));
	static ArrayList<Character> list2 = new ArrayList<>(Arrays.asList('1', '2', '3', '4', '5', '6', '7', '8', 'X'));
	
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
			case MenuChoice.GAME:
				do{threeby3Game();
				}while(askRestart());
				System.out.println("게임을 종료합니다. 감사합니다. ");
				break;
			case MenuChoice.EXIT:
				System.out.println("프로그램 종료"); return;
			default : System.out.println("범위가 잘못되었습니다. \n1~6사이의 숫자를 입력해주세요.");
			}
		}
	}
	
	public static void showMenu() {
		System.out.println("-------Menu-------\n1. 계좌개설 \n2. 입금 \n3. 출금 \n4. 전체계좌정보출력 \n5. 3 by 3 GAME \n6. 프로그램 종료\n선택 : ");
	}

	public static void makeAccount() {
		String iaccNum, iname;
		int ibal;
		System.out.println("***신규계좌개설***\n계좌번호 : ");
		iaccNum = sc.nextLine();
//		sc.nextLine();
		
		System.out.println("고객명 : ");
		iname = sc.nextLine();
		System.out.println("잔액 : ");
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
		for(int i=0; i<cnt; i++) accounts[i].showAccInfo();
		System.out.println("전체계좌정보 출력이 완료되었습니다.");
	}
	
	public static void threeby3Game() {
		shuffle();
		list2show();
		while(!compareList(list, list2)) {
		System.out.println("[이동] a:LEFT d:RIGHT w:UP s:DOWN\n[종료] x:EXIT\n키를 입력해주세요 : ");
		char arrow = sc.next().charAt(0);
		switch(list2.indexOf('X')) {
		case 0:
			switch(arrow){
			case 'a': case 'A': 
				Collections.swap(list2, 0, 1);
				list2show(); break;
			case 'w': case 'W':
				Collections.swap(list2, 0, 3);
				list2show(); break;
			default: System.out.println("XXXXXX이동불가XXXXXX"); 
			}
			break;
		case 1:
			switch(arrow){
			case 'a': case 'A': 
				Collections.swap(list2, 1, 2);
				list2show(); break;
			case 'd': case 'D': 
				Collections.swap(list2, 1, 0);
				list2show(); break;
			case 'w': case 'W':
				Collections.swap(list2, 1, 4);
				list2show(); break;
			default: System.out.println("XXXXXX이동불가XXXXXX"); 
			}
			break;
		case 2:
			switch(arrow){
			case 'd': case 'D': 
				Collections.swap(list2, 2, 1);
				list2show(); break;
			case 'w': case 'W':
				Collections.swap(list2, 2, 5);
				list2show(); break;
			default: System.out.println("XXXXXX이동불가XXXXXX"); 
			}
			break;
		case 3:
			switch(arrow){
			case 'a': case 'A': 
				Collections.swap(list2, 3, 4);
				list2show(); break;
			case 's': case 'S': 
				Collections.swap(list2, 3, 0);
				list2show(); break;
			case 'w': case 'W':
				Collections.swap(list2, 3, 6);
				list2show(); break;
			default: System.out.println("XXXXXX이동불가XXXXXX"); 
			}
			break;
		case 4:
			switch(arrow){
			case 'a': case 'A': 
				Collections.swap(list2, 4, 5);
				list2show(); break;
			case 'd': case 'D': 
				Collections.swap(list2, 4, 3);
				list2show(); break;
			case 's': case 'S': 
				Collections.swap(list2, 4, 1);
				list2show(); break;
			case 'w': case 'W':
				Collections.swap(list2, 4, 7);
				list2show();
			}
			break;
		case 5:
			switch(arrow){
			case 'd': case 'D': 
				Collections.swap(list2, 5, 4);
				list2show(); break;
			case 's': case 'S': 
				Collections.swap(list2, 5, 2);
				list2show(); break;
			case 'w': case 'W':
				Collections.swap(list2, 5, 8);
				list2show(); break;
			default: System.out.println("XXXXXX이동불가XXXXXX"); 
			}
			break;
		case 6:
			switch(arrow){
			case 'a': case 'A': 
				Collections.swap(list2, 6, 7);
				list2show(); break;
			case 's': case 'S':
				Collections.swap(list2, 6, 3);
				list2show(); break;
			default: System.out.println("XXXXXX이동불가XXXXXX"); 
			}
			break;
		case 7:
			switch(arrow){
			case 'a': case 'A': 
				Collections.swap(list2, 7, 8);
				list2show(); break;
			case 'd': case 'D': 
				Collections.swap(list2, 7, 6);
				list2show(); break;
			case 's': case 'S':
				Collections.swap(list2, 7, 4);
				list2show(); break;
			default: System.out.println("XXXXXX이동불가XXXXXX"); 
			}
			break;
		case 8: listshow(); return;
		}
		}
	}
	
	public static boolean askRestart() {
		while(true) {
			listshow();
			System.out.println("게임을 재시작하시겠습니까?\n(y 누르면 재시작, 나머지는 종료) : ");
			char choice4 = sc.next().charAt(0);
			sc.nextLine();
			if(choice4=='y'||choice4=='Y') return true;
			else return false;
		}
	}
	
	public static void list2show() {
		System.out.println("=========");
		System.out.println(list2.get(0)+" "+list2.get(1)+" "+list2.get(2)+" ");
		System.out.println(list2.get(3)+" "+list2.get(4)+" "+list2.get(5)+" ");
		System.out.println(list2.get(6)+" "+list2.get(7)+" "+list2.get(8)+" ");
		System.out.println("=========");
	}
	
	public static void listshow() {
		System.out.println("===^^정답입니다^^===");
		System.out.println("=========");
		System.out.println(list.get(0)+" "+list.get(1)+" "+list.get(2)+" ");
		System.out.println(list.get(3)+" "+list.get(4)+" "+list.get(5)+" ");
		System.out.println(list.get(6)+" "+list.get(7)+" "+list.get(8)+" ");
		System.out.println("=========");
	}
	
	public static void shuffle() {
		int n1 = (int)(Math.random()*4)+1;
		int n2 = (int)(Math.random()*4)+1;
		int n3 = (int)(Math.random()*4)+1;
//		System.out.println(n1+", "+n2+", "+n3);
		switch(n1) {
		case 1: 
			Collections.swap(list2, 8, 7); 
			switch(n2) {
			case 1: 
				Collections.swap(list2, list2.indexOf('X'), list2.indexOf('X')-1); 
				switch(n3) {
				case 2: Collections.swap(list2, list2.indexOf('X'), list2.indexOf('X')+1); break;
				case 4: Collections.swap(list2, list2.indexOf('X'), list2.indexOf('X')-3);
				}
				break;
			case 2: 
				Collections.swap(list2, list2.indexOf('X'), list2.indexOf('X')+1); 
				switch(n3) {
				case 1: Collections.swap(list2, list2.indexOf('X'), list2.indexOf('X')-1); break;
				case 4: Collections.swap(list2, list2.indexOf('X'), list2.indexOf('X')-3);
				}
				break;
			case 4: 
				Collections.swap(list2, list2.indexOf('X'), list2.indexOf('X')-3); 
				switch(n3) {
				case 1: Collections.swap(list2, list2.indexOf('X'), list2.indexOf('X')-1); break;
				case 2: Collections.swap(list2, list2.indexOf('X'), list2.indexOf('X')+1); break;
				case 3: Collections.swap(list2, list2.indexOf('X'), list2.indexOf('X')+3); break;
				case 4: Collections.swap(list2, list2.indexOf('X'), list2.indexOf('X')-3);
				}
			}
			break;
		case 4: 
			Collections.swap(list2, 8, 5);
			switch(n2) {
			case 1: 
				Collections.swap(list2, list2.indexOf('X'), list2.indexOf('X')-1); 
				switch(n3) {
				case 1: Collections.swap(list2, list2.indexOf('X'), list2.indexOf('X')-1); break;
				case 2: Collections.swap(list2, list2.indexOf('X'), list2.indexOf('X')+1); break;
				case 3: Collections.swap(list2, list2.indexOf('X'), list2.indexOf('X')+3); break;
				case 4: Collections.swap(list2, list2.indexOf('X'), list2.indexOf('X')-3);
				}
				break;
			case 3: 
				Collections.swap(list2, list2.indexOf('X'), list2.indexOf('X')+3); 
				switch(n3) {
				case 1: Collections.swap(list2, list2.indexOf('X'), list2.indexOf('X')-1); break;
				case 4: Collections.swap(list2, list2.indexOf('X'), list2.indexOf('X')-3);
				}
				break;
			case 4: 
				Collections.swap(list2, list2.indexOf('X'), list2.indexOf('X')-3); 
				switch(n3) {
				case 1: Collections.swap(list2, list2.indexOf('X'), list2.indexOf('X')-1); break;
				case 3: Collections.swap(list2, list2.indexOf('X'), list2.indexOf('X')+3);
				}
			}
		}
	}
	
//	public static void shuffle(int num) {
//		try {
//		for(int i=0; i<num; i++) {
//			int n1 = (int)(Math.random()*4)+1;
//			switch(n1) {
//			case 1: Collections.swap(list2, list2.indexOf('X'), list2.indexOf('X')-1); break;
//			case 2: Collections.swap(list2, list2.indexOf('X'), list2.indexOf('X')+1); break;
//			case 3: Collections.swap(list2, list2.indexOf('X'), list2.indexOf('X')+3); break;
//			case 4: Collections.swap(list2, list2.indexOf('X'), list2.indexOf('X')-3); 
//			}
//		}
//		}catch(IndexOutOfBoundsException e) {
//			return;
//		}
//	}
	
	public static boolean compareList(ArrayList<Character> list1, ArrayList<Character> list2) {
		for(int i=0; i<list1.size(); i++) {
			if(list1.get(i)!=list2.get(i)) return false; 
		}
		return true;
	}
}
