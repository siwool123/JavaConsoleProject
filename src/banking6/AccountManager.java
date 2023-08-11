package banking6;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class AccountManager implements MenuChoice {
	public static Scanner sc = new Scanner(System.in);
	static HashSet<Account> set = new HashSet<Account>();
	
	public static void loadData() {
		try{
			ObjectInputStream in = new ObjectInputStream(new FileInputStream("src/banking6/AccountInfo.obj"));
			set = (HashSet<Account>)in.readObject();
			in.close(); //역직렬화 완료
			}catch(FileNotFoundException e) {
				System.out.println("가져올 파일이 없습니다.");
			}catch(ClassNotFoundException e) {
				System.out.println("해당 클래스가 없습니다.");
			}catch(IOException e) {
				System.out.println("파일을 가져오는데 문제가 발생했습니다.");
			}
	}
	
	public static void showMenu() {
		System.out.println("-----------Menu-----------\n1. 계좌개설        2. 입금       3. 출금     4. 전체계좌정보출력 \n5. 계좌정보 삭제     6. 저장옵션     7. 프로그램 종료");
		System.out.print("--------------------------\n선택 : ");
	}
	
	public void makeAccount() {
		String iaccNum, iname;
		int ibal, binter;
		char grade;
		System.out.print("*******신규계좌개설*******\n1. 보통계좌		2. 신용신뢰계좌\n**********************\n선택 : ");
		int choice2 = 0;
		try {
		choice2 = sc.nextInt();
		if(choice2<1||choice2>2) {
			System.out.print("범위가 잘못되었습니다. 1~2사이의 숫자를 입력해주세요 : ");
			return;
		}
		}catch(InputMismatchException e) {
			System.out.print("[예외발생] 숫자만 입력하세요 : ");
			sc.nextLine();
			return;
		}
				
		sc.nextLine();
		System.out.print("계좌번호 : ");
		iaccNum = sc.nextLine();
		System.out.print("고객명 : ");
		iname = sc.nextLine();
		System.out.print("잔고 : ");
		ibal = sc.nextInt();
		System.out.print("기본이자%(정수 입력) : ");
		binter = sc.nextInt();

		switch(choice2) {
		case 1 :
			Account addNAcc = new NormalAccount(iaccNum, iname, ibal, binter);
			if(set.add(addNAcc)==false) {
				System.out.print("중복계좌가 발견되었습니다. 갱신할까요? (Y/N) : ");
				char b = sc.next().charAt(0);
				switch(b) {
				case 'Y': case 'y': 
					set.remove(addNAcc);
					set.add(addNAcc);
					System.out.println("갱신하여 신규계좌개설을 완료하였습니다.");								
					break;
				case 'N': case 'n': 
					System.out.println("계좌개설이 취소되었습니다.");
					return;
				default : System.out.println("문자 Y 또는 N 만 입력가능합니다. "); return;
				}
			}
//			else set.add(addNAcc);
			break;
		case 2 : 
			System.out.print("신용등급(A,B,C 등급) : ");
			grade = sc.next().charAt(0);
			Account addHAcc = new HighCreditAccount (iaccNum, iname, ibal, binter, grade);
			if(set.add(addHAcc)==false) {
				System.out.print("중복계좌가 발견되었습니다. 갱신할까요? (Y/N) : ");
				char b = sc.next().charAt(0);
				switch(b) {
				case 'Y': case 'y': 
					set.remove(addHAcc);
					set.add(addHAcc);
					System.out.println("갱신하여 신규계좌개설을 완료하였습니다.");								
					break;
				case 'N': case 'n': 
					System.out.println("계좌개설이 취소되었습니다.");
					return;
				default : System.out.println("문자 Y 또는 N 만 입력가능합니다. "); return;
				}
			}
			else set.add(addHAcc);
		}
		System.out.println("신규계좌 개설이 완료되었습니다.");
	}
	
	public void depositMoney() {
		int iMoney = 0;
		sc.nextLine();
		System.out.print("***입금***\n계좌번호 : ");
		String iaccNum = sc.nextLine();
		System.out.print("입금액 : ");
		while(true) {
		try{
			iMoney = sc.nextInt();
			sc.nextLine();
			break;
		}catch(InputMismatchException e) {
			System.out.println("[예외발생] 숫자만 입력하세요.");
			return;
//			sc.nextLine();
		}
		}
		if(iMoney<0||iMoney%500!=0) System.out.println("500원 단위의 양수만 입금 가능합니다.");
		else {
			for(Account i:set) {
				if(i.getAccNum().equals(iaccNum)) {
					i.plusMoney(iMoney);
					System.out.println("입금이 완료되었습니다.");
					return;
				}
			}
			System.out.println("--- 입력하신 계좌가 없습니다 ---"); 
		}
	}

	public void withdrawMoney() {
		int iMoney = 0; boolean isFind = false;
		sc.nextLine();
		System.out.print("***출금***\n계좌번호 : ");
		String iaccNum = sc.nextLine();
		System.out.print("출금액 : ");
		while(true) {
		try{
			iMoney = sc.nextInt();
			sc.nextLine();
			break;
		}catch(InputMismatchException e) {
			System.out.println("[예외발생] 숫자만 입력하세요.");
			return;
//			sc.nextLine();
		}
		}
		if(iMoney<0||iMoney%1000!=0) {
				System.out.println("1000원 단위의 양수만 출금 가능합니다.");
			}else {
				for(Account i:set) {
					if(i.getAccNum().equals(iaccNum)) {
						if(iMoney<=i.getBal()) {
							i.minusMoney(iMoney);
							System.out.println("출금이 완료되었습니다.");
							return;
						}else {
							System.out.print("잔고가 부족합니다. 잔액전부를 출금할까요? (Y/N) : ");
//							while(true) {
							char a = sc.next().charAt(0);
							switch(a) {
							case 'Y': case 'y': 
								System.out.println("잔고 "+i.getBal()+" 원 전부 출금 완료되었습니다.");
								i.setBal(0);									
								break;
							case 'N': case 'n': 
								System.out.println("출금이 취소되었습니다.");
								return;
							default : System.out.println("문자 Y 또는 N 만 입력가능합니다. ");
							}
//							}
						}
						isFind = true;
					} 
				}if(isFind==false) System.out.println("--- 입력하신 계좌가 없습니다 ---");
			}
	}
	
	public static void showAccInfo() {
		System.out.println("***계좌정보출력***");
		for(Account i:set) {
			System.out.println("---------------");
			i.showAccInfo();
			System.out.println("---------------");
		}		
		System.out.println("전체계좌정보 출력이 완료되었습니다.");
	}
	
	public static void deleteAcc() {
		sc.nextLine(); boolean isFind=false;
		System.out.print("***계좌삭제***\n삭제할 계좌번호를 입력하세요 : ");
		String iaccNum = sc.nextLine();
		for(Account i:set) {
			if(i.getAccNum().equals(iaccNum)) {
				set.remove(i);
				System.out.println(iaccNum+" 계좌를 삭제하였습니다.");
				isFind = true; 
				break; }		
		}if(isFind==false) System.out.println("--- 입력하신 계좌가 없습니다 ---");
	}
	
	public static void exit() {
		try {
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("src/banking6/AccountInfo.obj"));
			out.writeObject(set);
			out.close(); //직렬화 완료			
		}catch(IOException e) {
			System.out.println("파일 저장하는데 문제가 발생했습니다.");
		}
		System.out.println("정상적으로 파일 저장되었습니다. 프로그램을 종료합니다.");
	}
}
