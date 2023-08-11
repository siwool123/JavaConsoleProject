package banking6;

import java.io.Serializable;

abstract class Account implements Serializable {
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
	
	public int hashCode() {
		int rCode = this.accNum.hashCode();
		return rCode;
	}
	
	public boolean equals(Object obj) {
		Account ac = (Account) obj;
		if(ac.accNum.equals(this.accNum)) return true;
		else return false;
	}
	
	public String toString() {
		return "계좌번호 : "+accNum+" | 고객명 : "+name+" | 잔액 : "+bal;
	}
}