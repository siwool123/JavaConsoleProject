package banking4;

public class NormalAccount extends Account {
	private int interest;
	
	public int getInterest() {
		return interest;
	}

	public NormalAccount(String accNum, String name, int bal, int inter) {
		super(accNum, name, bal);
		interest = inter;
	}
	
	public boolean plusMoney(int money) {
		int sum = super.getBal() + super.getBal()*interest/100 + money;
		super.setBal(sum);
		return true;
	}
		
	public void showAccInfo() {
		super.showAccInfo();
		System.out.println("기본이자 : "+interest+" %");
	}
}
