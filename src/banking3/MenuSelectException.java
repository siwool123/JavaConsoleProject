package banking3;

public class MenuSelectException extends Exception {

	public MenuSelectException() {
//		System.out.println("범위가 잘못되었습니다. \n1~5사이의 숫자를 입력해주세요.");
		super("범위가 잘못되었습니다. \n1~5사이의 숫자를 입력해주세요.");
	}
}
