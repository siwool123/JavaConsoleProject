package banking6;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;

public class AutoSaver extends Thread {
	public HashSet<Account> set; 
	
	public AutoSaver(HashSet<Account> set) {
		this.set = set;
	}

	public void run() {
		while(true) {
			try {
			sleep(5000);
			PrintWriter out = new PrintWriter(new FileWriter("src/banking6/AutoSaveAccount.txt"));
			for(Account i:set)	out.println(i.toString());
			out.close();
			System.out.println("AutoSaveAccount.txt 가 생성되었습니다.");
			}catch(IOException e) {
				System.out.println("자동저장시 오류가 발생했습니다.");
				break;
			}catch(InterruptedException e) {
				System.out.println("자동저장이 중단되었습니다. ");
				break;
			}catch(Exception e) {
				System.out.println("자동저장시 오류가 발생했습니다. ");
				break;
			}
		}
	}
}
