package banking7.jdbc;

public class MenuSelectException extends Exception {

	public MenuSelectException() {
		System.out.println("잘못입력하셨습니다. 숫자가 아닙니다.");
	}
}
