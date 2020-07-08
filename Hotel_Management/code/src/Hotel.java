import java.io.*;
import java.util.InvalidPropertiesFormatException;

/**
* @author ebru_kardas
*/
public abstract class Hotel implements ManagementSystem {

// --------------------		MEMBER		---------------------------
	Room[] rooms;
	int size;
	int capasity;
	String filename;

// ----------------		CONSTRUCTORS		------------------------

	public Hotel( String filename ) throws InvalidClassException{
		capasity = 5;
		size = 0;
		resize();
		if( !readFile(filename) ){
			throw new InvalidClassException("Source file couldnt read!");
		}
		this.filename = filename;
	}

	public Hotel() throws InvalidClassException {
		this("rooms.csv");
	}

	public abstract void printHotel();

// ----------------		HELPER METHODS		------------------------

	/**
	* This method will be used by subclasses to exit guest
	* @param name who will be exited the room
	* @return {@code true} if process is successful, name valid
	*/
	protected boolean exit(String name) {
		int ind= searchRoom(name);
		 if( ind != -1 ){
			 rooms[ind].delete();
			 System.out.println("Successful");
			 return true;
		 }
		 System.out.println("Name couldn't found!");
		 return false;
	}

	/**
	 * This method will be used by subclasses to exit guest
	 * @param name who will be exited the room
	 * @param roomNum the room number will be exited
	 * 			if more than one room is reserved/booked
	 * @return {@code true} if process is successful, name and number valid
	 */
	protected boolean exit(String name, int roomNum) {
		if (roomNum > 0 && roomNum <= size){
			if ((rooms[roomNum-1].getName()).equals(name)) {
				rooms[roomNum-1].delete();
				System.out.println("Successful");
				return true;
			} else {
				System.out.println("Name didn't match or didn't exist!");
			}
		}else{
			System.out.println("Invalid room number");
		}
		return false;
	}


	/**
	 * This method will be used to book the room
	 * @param name is guest name
	 * @param roomNumber is number that wanted to booked
	 * @param personNumber is number of person stays in the room
	 * @param book the book situation (for reserved R, for booked B)
	 * @return {@code true} if booking the room process is successful.
	 *          It may already booked or room number may not be available
	 */
	protected boolean enter( String name, int roomNumber, int personNumber, char book) {
		if( roomNumber>size || roomNumber<1) {
			System.out.println("Invalid room number");
			return false;
		}else if( personNumber<0 || personNumber>2 ){
			System.out.println("Invalid person number");
			return false;
		}else if( (rooms[roomNumber-1].getBooked() != 'B' && (rooms[roomNumber-1].getName()).equals(name) ) ||
				book == 'R'){
			rooms[roomNumber-1].booking(name, book, personNumber);
			System.out.println("Successful");
			return true;
		}else {
			System.out.println("Room " + roomNumber + 
								" is not available. Choose another room");
			return false;
		}
	}


	/**
	* This method searchs room
	* @param value guest name for searching
	* @return if value couldn't find, returns -1; if found, returns index.
	*/
	protected int searchRoom( String value ) {
		for( int i=0; i<size; ++i )
			if( (rooms[i].getName()).equals(value) )
				return i;
		return -1;
	}

	/**
	* This method reads information about hotel rooms from .csv file
	* @param filename is input file for guest-hotel info
	* @return {@code true} if the file is read successfully
	*/
	protected boolean readFile( String filename ) {
		BufferedReader br = null;
		String line = "";

		if( (!filename.endsWith(".csv")) )
			return false;

		try{
			br = new BufferedReader( new FileReader(filename) );
			while( (line=br.readLine()) != null ){

				String arr[] = line.split(",");

				if(size>=capasity)
					resize();
				if( arr[0].equals("E") )
					rooms[size++].delete();
				else
					rooms[size++].booking(arr[1], arr[0].charAt(0), Integer.parseInt(arr[2]) );
			}
			br.close();
		}catch(FileNotFoundException e ){
			System.out.println("Hotel source is not valid. (File not found)");
			return false;
		}
		catch(Exception e){
			e.getMessage();
			return false;
		}
		return true;
	}

	/**
	* This method writes updated information about hotel room
	* to same .csv file
	* @param filename target filename to write room information
	*/
	public void writeFile(String filename) {
		String str = "";
		try {
			PrintWriter pw = new PrintWriter(new File(filename));
			for(int i=0; i<size; ++i) {
				if( rooms[i].getBooked() != 'E' ){
					str = rooms[i].getBooked()+ "," + rooms[i].getName() + 
							"," + rooms[i].getPersonNum() + "\n";
				}else 
					str = "E,,0\n";	
				
				pw.print(str);
			}
			pw.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}


	@Override
	public String toString() {
		String str = "";
		for(int i=0; i<size; ++i) {
			str = str + "Room " + (i+1) + ": " ;
			if( rooms[i].getBooked() != 'E' )
				str = str + "\t" + rooms[i] + "\n";
			else
				str = str + "\tEmpty\n";
		}
		return str;
	}

	/**
	* This method increases the Room array with all information
	*/
	private void resize() {
		capasity+=10;
		Room[] temp = new Room[capasity];
		for (int i = 0; i < size; i++)
			temp[i] = rooms[i];
		for (int i = size; i < capasity; i++)
			temp[i] = new Room();
		rooms = temp;
	}

}
