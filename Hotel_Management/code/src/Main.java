import java.util.Scanner;
/**
* @author ebru_kardas
*/
public class Main {

	public static void main(String[] args) {

		SystemTest ob = new SystemTest();
		try {
			ob.test("inputGuest.txt");
			//ob.test("inputRecept.txt");

		}catch (Exception e){
			System.out.println( e.getMessage() );

		}
	}
}
