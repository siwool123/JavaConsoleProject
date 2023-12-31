package banking7.jdbc;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Account {
	private String accNum, name;
	private int bal;
	
	public String getAccNum() {return accNum;}
	public String getName() {return name;}
	public int getBal() {return bal;}
	public void setBal(int bal) {this.bal = bal;}
	
	public Account(String accNum, String name, int bal) {
		this.accNum = accNum;
		this.name = name;
		this.bal = bal;
	}

	public void showAccInfo() {
		System.out.println("계좌번호 : "+accNum+"  |  고객명 : "+name+"  |  잔액 : "+bal);
	}
	
	public boolean plusMoney(int money) {
		bal += money;
		return true;
	}
	
	public boolean minusMoney(int money) {
		bal -= money;
		return true;
	}
}