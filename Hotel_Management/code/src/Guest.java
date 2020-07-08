import java.io.InvalidClassException;
import java.util.InvalidPropertiesFormatException;
import java.util.Scanner;
/**
* @author ebru_kardas
*/
public class Guest extends Hotel {

	public Guest(String file, String name)throws InvalidClassException {
		super(file);
		setName(name);
	}

	public Guest(String name)throws InvalidClassException {this("rooms.csv", name);}


// --------------------		USABLE METHODS		---------------------------
	/**
	 * This method will be used to book the room
	 * @param roomNumber is number that wanted to booked
	 * @param personN is number of person stays in the room
	 * @return {@code true} if booking the room process is successful.
	 *          It may already booked or room number may not be available
	 */
	public boolean bookRoom(int roomNumber, int personN) {
		if(roomNumber>0 && roomNumber<=size)
			if( rooms[roomNumber-1].getBooked() == 'E' )
				return enter(fullname, roomNumber, personN, 'R');
		return false;
	}

	/**
	* This method will be used for canceling the reservation
	* @param roomNum room number of which room will be canceled
	* 		if there is more than one reserved room.
	* @return {@code true} if process is successfull,
	* 				if there is a room which reserved for the guest.
	*/
	public boolean cancelReservation(int roomNum) {
		return exit(fullname, roomNum);
	}

	/**
	 * This method will be used for canceling the reservation
	 * @return {@code true} if process is successfull,
	 * 				if there is a room which reserved for the guest.
	 */
	public boolean cancelReservation() {
		return exit(fullname);
	}

	/**
	* Method is used for if receptionist is choosen by main
	*/
	public void runSystem( ){

		Scanner sc = new Scanner(System.in);
		String in = "";
		int choice = 1, c2 = 0;

		printHotel();
		while( choice<3 && choice>0 ) {
			System.out.println("\nChoose a service:\n" +
					"1: Book a room\n" +
					"2: Cancel reservation\n" +
					"3: Exit");

			choice = Integer.parseInt(sc.nextLine());
			switch (choice) {
				case 1:
					System.out.print("Enter room number: ");
					choice = Integer.parseInt(sc.nextLine());
					System.out.print("Enter person number: ");
					c2 = Integer.parseInt(sc.nextLine());
					bookRoom(choice, c2);
					choice=1;
					break;
				case 2:
					cancelReservation();
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
    			System.out.println("Room " + (i+1) + ":\t\tFULL");
        }
	}

// --------------------		MEMBER		---------------------------

	String fullname;

// --------------------		GETTERS		---------------------------
	/**
	* @return guest name
	*/
	public String getName(){return fullname;}
// --------------------		SETTERS		---------------------------

	/**
	* This method sets guest name
	* @param name is guest name
	*/
	public void setName(String name){
		if(name.equals("")){
			System.out.println("Name not valid. Cannot be empty");
			return;
		}
		fullname = name;
	}

// --------------------		OVERRIDES		---------------------------
	@Override
	public String toString() {
		String str = fullname + " Room " + searchRoom(fullname) ;
		return str;
	}

}
