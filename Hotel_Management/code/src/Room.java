/**
* @author ebru_kardas
*/
public class Room {

// ----------------		CONSTRUCTORS		------------------------

	public Room(char booked_, String name, int num)  {
		setName(name);
		setBooked(booked_);
		setPerson(num);
	}

	public Room(String name){
		this('R', name,  1);
	}

	public Room() {
		this('E', "", 0);
	}

// --------------------		GETTERS		---------------------------

	/**
	* This method will be used to change fullness of room
	* by guest and receptionist
	*
	* @param name is guest name
	* @param book is situation if room is empty, reserved, booked
	* @param num is number of person stays in the room
	*/
	public void booking(String name, char book, int num) {
		this.name = name;
		booked = book;
		personNum = num;
	}

	/**
	* This method will be used to clear out the room
	*/
	public void delete(){
		name="";
		booked='E';
		personNum=0;
	}

// --------------------		MEMBERS		---------------------------

	String name;
	char booked;
	int personNum;

// --------------------		GETTERS		---------------------------

	/**
	* @return guest's name who responsible for the room
	*/
	public String getName() {   return name;    }

	/**
	* @return B, if the room is booked; R, if it is made reservation or
	* 			E, if it is empty
	*/
	public char getBooked() {    return booked;  }

	/**
	* @return number of person stays in the room
	*/
	public int getPersonNum(){	return personNum;	}

// --------------------		SETTERS		---------------------------

	/**
	* @param val is empty if the room is empty. If room is not empty,
	*  val is the guest's name.
	*/
	public void setName(String val) {
		if(val.equals("")){
			name = "";
			booked = 'E';
		}else{
			name = val;
			booked = 'R';
		}
	}


	/**
	* Room condition
	* @param val E means empty; B, booked; R, reserved
	*/
	public void setBooked(char val) {
		booked = val;
		if( val == 'E' )
			name = "";
	}


	/**
	* Person number must be 1 or 2. It is bed number in room
	* @param n value is number of person -in the room-
	*/
	public void setPerson(int n){
		if(n<0 || n>2)
			return;
		personNum = n;
	}

// --------------------		OVERRIDES		---------------------------

	/**
	* @param obj should be Room which is checked. All member should be same.
	* @return {@code true} if it is same object or members are same, o/w false.
	*/
	@Override
	public boolean equals(Object obj) {

		if(this==obj)
			return true;
		if(obj==null)
			return false;
		if( obj instanceof Room ){
			Room ob = (Room)obj;
			return ( name.equals(ob.name) && booked==ob.booked && personNum==ob.personNum );
		}
		return false;
	}

	/**
	* @return class member in a meaningful way
	*/
	@Override
	public String toString() {
		String str = "Name: " + name + "\t\tPerson number in room: " + personNum;
		return str;
	}

}
