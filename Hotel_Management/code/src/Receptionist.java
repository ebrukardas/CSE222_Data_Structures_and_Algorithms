import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileReader;
import java.io.InvalidClassException;
import java.util.Scanner;
/**
* @author ebru_kardas
*/
public class Receptionist extends Hotel {

// ----------------		CONSTRUCTORS		------------------------

	public Receptionist(String file, String name, String pw)throws InvalidClassException {
		super(file);
		if( !readFile(name, pw) ){
			System.out.println("Your name and/or password NOT VALID");
			throw new InvalidClassException("Reception couldn't created");
		}
	}

	public Receptionist(String name, String pw)throws InvalidClassException{this("rooms.csv", name, pw);}

	/**
	* This method will be used to book the room
	* @param fullname is guest name
	* @param roomNumber is number that wanted to booked
	* @param personNum is number of person stays in the room
	* @return {@code true} if booking the room process is successful.
	*		  It may already booked or room number may not be available
	*/
	public boolean bookRoom(String fullname, int roomNumber, int personNum) {
		return enter(fullname, roomNumber, personNum, 'B');
	}

	/**
	 * This method will be used to cancel reservation
	 * @param fullname is guest name
	 * @param roomNumber is number that wanted to be canceled
	 * @return {@code true} if canceling the room process is successful.
	 *		  It may not matched, room number may be invalid, name may not match.
	 */
	public boolean cancelRoom(String fullname, int roomNumber) {
		return exit(fullname, roomNumber);
	}

	/**
	 * This method will be used to check-in the room
	 * @param name is guest name
	 * @param roomNum is number that wanted to booked
	 * @param personNum is number of person stays in the room
	 * @return {@code true} if booking the room process is successful.
	 *		  It may already booked or room number may not be available
	 */
	public boolean checkin(String name, int roomNum, int personNum) {
		System.out.print("");
		if( roomNum>0 && roomNum<=size )
			if( (rooms[roomNum-1].getName()).equals(name) && rooms[roomNum-1].getBooked() == 'R' )
				return enter(name, roomNum, personNum, 'B');
		return false;
	}

	/**
	* @param name guest name who is checked out
	* @param roomNum room number will be checked out
	* @return {@code true} if process is successfull, name is valid.
	*/
	public boolean checkout(String name, int roomNum) {
		if( roomNum>0 && roomNum<=size )
			if( (rooms[roomNum-1].getName()).equals(name) )
				return exit(name, roomNum);
		return false;
	}

	/**
	* Method is used for if receptionist is choosen by main
	*/
	public void runSystem( ){

		Scanner sc = new Scanner(System.in);
		String in = "";
		int choice = 1, c2 = 0;

		printHotel();
		while( choice<4 && choice>0 ) {
			System.out.println("Choose a service:\n" +
								"1: Book a room\n" +
								"2: Cancel reservation\n" +
								"3: Check-in\n" +
								"4: Check-out\n" +
								"5: Exit");

			choice = Integer.parseInt(sc.nextLine());

			switch (choice) {
				case 1:
					System.out.print("Enter guest name: ");
					in = sc.nextLine();
					System.out.print("Enter room number: ");
					choice = Integer.parseInt(sc.nextLine());
					System.out.print("Enter person number: ");
					c2 = Integer.parseInt(sc.nextLine());
					bookRoom(in, choice, c2);
					choice=1;
					break;
				case 2:
					System.out.print("Enter guest name: ");
					in = sc.nextLine();
					System.out.print("Enter room number: ");
					c2 = Integer.parseInt(sc.nextLine());
					cancelRoom(in,c2);
					choice=1;
					break;
				case 3:
					System.out.print("Enter guest name: ");
					in = sc.nextLine();
					System.out.print("Enter room number: ");
					choice = Integer.parseInt(sc.nextLine());
					System.out.print("Enter person number: ");
					c2 = Integer.parseInt(sc.nextLine());
					checkin(in, choice, c2);
					choice=1;
					break;
				case 4:
					System.out.print("Enter guest name: ");
					in = sc.nextLine();
					System.out.print("Enter room number: ");
					c2 = Integer.parseInt(sc.nextLine());
					checkout(in,c2);
					choice=1;
					break;
			}
			printHotel();
		}
		writeFile(filename);
	}


	/**
	* Method used for print hotel information for receptionist
	*/
	public void printHotel() {
		for(int i=0; i<size; ++i){
			if( rooms[i].getBooked() == 'E' )
				System.out.println("Room " + (i+1) + ":\t\tEmpty");
			else
				System.out.println("Room " + (i+1) + ":\t\t" + rooms[i]);
		}
	}

	/**
	* This method checks if person that wanted to login the system as
	* receptionist or not. Default file name is recep.csv
	* @param name is receptionist name
	* @param pw is receptionist's password for login the system
	* @return {@code true} if receptionist and password are valid
	*/
	private boolean readFile(String name, String pw ) {
		BufferedReader br = null;
		String line = "";

		try{
			br = new BufferedReader( new FileReader("recep.csv") );
			while( (line=br.readLine()) != null ){

				String arr[] = line.split(",");
				if( arr[0].equals(name) )
					if( arr[1].equals(pw) )
						return true;

			}
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}finally{
			try {
				br.close();
			}catch(IOException e){
				e.printStackTrace();
				return false;
			}
		}
		return false;
	}

	String name;

}