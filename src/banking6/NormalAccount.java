package banking6;

public class NormalAccount extends Account {
//	private static final long serialVersionUID = 1L;
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
		System.out.print("  |  기본이자 : "+interest+" %");
	}
	
	public String toString() {
		return super.toString()+" | 기본이자(%) : "+interest;
	}
}
