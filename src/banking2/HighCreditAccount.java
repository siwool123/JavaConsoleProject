package banking2;

public class HighCreditAccount extends Account {
	private int interest;
	private char grade;
	
	public int getInterest() {
		return interest;
	}

	public int getHighInter(char grade) {
		int answer = 0;
		switch(grade) {
		case 'a': case 'A': answer = 7; break;
		case 'b': case 'B': answer = 4; break;
		case 'c': case 'C': answer = 2; break;
		}
		return answer;
	}

	public HighCreditAccount(String accNum, String name, int bal, int inter, char grade) {
		super(accNum, name, bal);
		interest = inter;
		this.grade = grade;
	}	
	
	public boolean plusMoney(int money) {
		int sum = super.getBal() + (super.getBal()*interest/100) + (super.getBal()*getHighInter(grade)/100) + money;
		super.setBal(sum);
		return true;
	}
	
	public void showAccInfo() {
		super.showAccInfo();
		System.out.println("기본이자 : "+interest+" %");
		System.out.println("신용등급 : "+grade);
	}
}
