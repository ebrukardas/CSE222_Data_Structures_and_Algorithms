import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InvalidClassException;
import java.security.InvalidParameterException;
import java.util.Scanner;

public class SystemTest{

    public void test( String inputfilename )throws InvalidParameterException, InvalidClassException{

        System.out.println("WELCOME TO HOTEL GTU\n");
        System.out.println("Before the use the system, please select user type.");

        int i=0;        //(r/g)
        String choice = "", filechoice = "", inputfile = "", username = "", pw="";
        String arr[] = readInputTest(inputfilename);
        if( arr==null || arr.length==0 )
            return;
/*
        for (int j=0; j<arr.length; ++j)
            System.out.println(arr[j]);
*/

        System.out.println("For receptionist, press R/r; for guest, press G/g:");
        choice = arr[i].toLowerCase();                          // 0 R/G
        System.out.println(choice);
        if(!(choice.equals("r")) && !(choice.equals("g")) )
            throw new InvalidParameterException("Invalid choice parameter for sequence!");
        else if( choice.equals("r") )
            System.out.println("You want to login as a receptionist");
        else
            System.out.println("You want to login as a guest");


        System.out.println("Do you want to enter hotel source file(Y/N)? --> ");
        filechoice = arr[++i].toLowerCase();                        // 1 Y/N
        if(!(filechoice.equals("y")) && !(filechoice.equals("n")) )
            throw new InvalidParameterException("Invalid choice parameter for sequence!");
        else if( filechoice.equals("y") ) {
            System.out.println("Enter hotel source file? --> ");
            inputfile = arr[++i];                               // 2 filename
            if( !inputfile.endsWith(".csv") )
                throw new InvalidParameterException("Invalid filename parameter for sequence!");

        }
        System.out.println("Enter your name: ");
        username = arr[++i];
        if(username.equals(""))
            throw new InvalidParameterException("Invalid username parameter for sequence!");

        Receptionist r;
        Guest g;
        if(choice.equals("r")){
            System.out.println("Enter your password: ");
            pw = arr[++i];
            if(filechoice.equals("y"))
                r = new Receptionist( inputfile, username, pw );
            else
                r = new Receptionist(username, pw);
            r.printHotel();


            int choiceNum=1;

            while( choiceNum<4 && choiceNum>0/* && i+1==arr.length*/) {
                System.out.println("Choose a service:\n" +
                        "1: Book a room\n" +
                        "2: Cancel reservation\n" +
                        "3: Check-in\n" +
                        "4: Check-out\n" +
                        "5: Exit");

                choiceNum = Integer.parseInt(arr[++i]);
                switch (choiceNum) {
                    case 1:
                        System.out.println("Enter guest name: ");
                        System.out.println("Enter room number: ");
                        System.out.println("Enter person number: ");
                        r.bookRoom(arr[++i], Integer.parseInt(arr[++i]), Integer.parseInt(arr[++i]));
                        choiceNum=1;
                        break;
                    case 2:
                        System.out.print("Enter guest name: ");
                        System.out.print("Enter room number: ");
                        r.cancelRoom(arr[++i], Integer.parseInt(arr[++i]));
                        choiceNum=1;
                        break;
                    case 3:
                        System.out.print("Enter guest name: ");
                        System.out.print("Enter room number: ");
                        System.out.print("Enter person number: ");
                        r.checkin(arr[++i], Integer.parseInt(arr[++i]), Integer.parseInt(arr[++i]));
                        choiceNum=1;
                        break;
                    case 4:
                        System.out.print("Enter guest name: ");
                        System.out.print("Enter room number: ");
                        r.checkout(arr[++i], Integer.parseInt(arr[++i]));
                        choiceNum=1;
                        break;
                }
                if(choiceNum==5) {
                    System.out.println("EXIT FROM SYSTEM");
                    break;
                }
                r.printHotel();
            }
            r.writeFile(inputfile);
        }
        else{   // GUEST
            if(filechoice.equals("y"))
                g = new Guest( inputfile, username );
            else
                g = new Guest(username);

            g.printHotel();

            int choiceNum=1;
            while( choiceNum<3 && choiceNum>0/* && i+1==arr.length*/) {
                System.out.println("Choose a service:\n" +
                        "1: Book a room\n" +
                        "2: Cancel reservation\n" +
                        "3: Exit");

                choiceNum = Integer.parseInt(arr[++i]);
                switch (choiceNum) {
                    case 1:
                        System.out.println("Enter room number: ");
                        System.out.println("Enter person number: ");
                        g.bookRoom( Integer.parseInt(arr[++i]), Integer.parseInt(arr[++i]) );
                        choiceNum=1;
                        break;
                    case 2:
                        System.out.print("Enter room number: ");
                        g.cancelReservation( Integer.parseInt(arr[++i]) );
                        choiceNum=1;
                        break;
                }
                if(choiceNum==3) {
                    System.out.println("EXIT FROM SYSTEM");
                    break;
                }else
                    g.printHotel();
            }
            g.writeFile(inputfile);
        }

    }

    public void test2(){

        System.out.println("WELCOME TO HOTEL GTU\n");
        System.out.println("Before the use the system, please select user type.");

        String choice = "", filechoice = "", inputfile = "", username = "";
        Scanner sc = new Scanner(System.in);

//	User should enter which user type she/he is.
        do {
            System.out.print("For receptionist, press R/r; for guest, press G/g: ");
            choice = (sc.nextLine()).toLowerCase();
        } while (!(choice.equals("r")) && !(choice.equals("g")) );

        do {
            System.out.println("Do you want to enter hotel source file(Y/N)? --> ");
            filechoice = (sc.nextLine()).toLowerCase();
        } while (!(filechoice.equals("y")) && !(filechoice.equals("n")));

        if (filechoice.equals("y")) {
            System.out.println("Enter hotel source file? --> ");
            inputfile = sc.nextLine();
        }

        do {
            System.out.print("Enter your name: ");
            username = sc.nextLine();
        } while ( username.equals("") );

        ManagementSystem o;
        try {
            if (choice.equals("r")) {
                System.out.print("Enter your password: ");
                choice = sc.nextLine();
                if(filechoice.equals("y"))
                    o = new Receptionist(inputfile, username, choice);
                else
                    o = new Receptionist(username, choice);

            } else {			// GUEST CHOICE

                if(filechoice.equals("y"))
                    o = new Guest(inputfile, username );
                else
                    o = new Guest(username);
            }
            o.runSystem();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private String[] readInputTest( String filename ){

        BufferedReader br = null;
        String line = "";
        String[] arr = null;

        if(filename.equals("") && !filename.endsWith(".txt") )
            return arr;

        try{
            br = new BufferedReader( new FileReader(filename) );
            while( (line=br.readLine()) != null ){
                arr = line.split("-");
            }
            br.close();
        }catch(FileNotFoundException e ){
            System.out.println("Input file is not valid. (File not found)");
        }
        catch(Exception e){
            e.getMessage();
        }
        return arr;
    }
}