import org.junit.Test;

import static org.junit.Assert.*;

public class ReceptionistTest {

    @Test
    public void bookRoom() {

        try{
            Receptionist obj1 = new Receptionist("Ebru Kar","123");
            assertEquals(true, obj1.bookRoom("Emire Kork", 16, 2));
            assertEquals(false, obj1.bookRoom("Emire Kork", 16, 2));
            assertEquals(false, obj1.bookRoom("Emire Kork", 15, 3));
            assertEquals(false, obj1.bookRoom("Emire Kork", -1, 2));

            Receptionist obj2 = new Receptionist("rooms_test1.csv", "Ebru Kar","123");
            assertEquals(true, obj2.bookRoom("Emire Kork", 16, 2));
            assertEquals(false, obj2.bookRoom("Emire Kork", 16, 2));
            assertEquals(false, obj2.bookRoom("Emire Kork", 15, 3));
            assertEquals(false, obj2.bookRoom("Emire Kork", -1, 2));

            Receptionist obj3 = new Receptionist("invalidfile.csv", "Ebru Kar","123");
        }catch(Exception e){
            System.out.println( e.getMessage() );
        }

        try{
            Receptionist obj4 = new Receptionist("rooms_test1.csv", "Ebru Kar","wrongpw");
        }catch(Exception e){
            System.out.println( e.getMessage() );
        }
        try{
            Receptionist obj5 = new Receptionist("rooms_test1.csv", "WrongRep","111");
        }catch(Exception e){
            System.out.println( e.getMessage() );
        }
    }

    @Test
    public void cancelRoom() {

        try{
            Receptionist obj1 = new Receptionist("Ebru Kar","123");
            assertEquals(false, obj1.cancelRoom("Ebru Al", 16));
            assertEquals(false, obj1.cancelRoom("Emire Kork", 15));
            assertEquals(false, obj1.cancelRoom("Emire Kork", -1));
            assertEquals(true, obj1.cancelRoom("Ebru A", 1));

            Receptionist obj2 = new Receptionist("rooms_test1.csv", "Ebru Kar","123");
            assertEquals(false, obj2.cancelRoom("Emire Kork", 15));
            assertEquals(false, obj2.cancelRoom("Ebru Al", -1));
            assertEquals(true, obj2.cancelRoom("Esra Cal", 13));

            Receptionist obj3 = new Receptionist("invalidfile.csv", "Ebru Kar","123");
        }catch(Exception e){
            System.out.println( e.getMessage() );
        }

    }

    @Test
    public void checkin() {
        try{
            Receptionist obj1 = new Receptionist("Ebru Kar","123");
            assertEquals(true, obj1.checkin("Emire Kork", 16, 2));
            assertEquals(false, obj1.checkin("Emire Kork", 16, 2));
            assertEquals(false, obj1.checkin("Emire Kork", 15, 3));
            assertEquals(false, obj1.checkin("Emire Kork", -1, 2));

            Receptionist obj2 = new Receptionist("rooms_test1.csv", "Ebru Kar","123");
            assertEquals(true, obj2.checkin("Emire Kork", 16, 2));
            assertEquals(false, obj2.checkin("Emire Kork", 16, 2));
            assertEquals(false, obj2.checkin("Emire Kork", 15, 3));
            assertEquals(false, obj2.checkin("Emire Kork", -1, 2));

        }catch(Exception e){
            System.out.println( e.getMessage() );
        }
    }

    @Test
    public void checkout() {
        try{
            Receptionist obj1 = new Receptionist("Ebru Kar","123");
            assertEquals(false, obj1.checkout("Ebru Al", 16));
            assertEquals(false, obj1.checkout("Emire Kork", 15));
            assertEquals(false, obj1.checkout("Emire Kork", -1));
            assertEquals(true, obj1.checkout("Ebru A", 1));

            Receptionist obj2 = new Receptionist("rooms_test1.csv", "Ebru Kar","123");
            assertEquals(false, obj2.checkout("Emire Kork", 15));
            assertEquals(false, obj2.checkout("Ebru Al", -1));
            assertEquals(true, obj2.checkout("Esra Cal", 13));

        }catch(Exception e){
            System.out.println( e.getMessage() );
        }
    }
}