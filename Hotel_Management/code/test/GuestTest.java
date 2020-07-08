import org.junit.Test;

import java.io.InvalidClassException;

import static org.junit.Assert.*;

public class GuestTest {

    @Test
    public void bookRoom() throws InvalidClassException {
        Guest obj1 = new Guest("rooms_test1.csv", "Aysel Tasdemir");
        assertEquals(true, obj1.bookRoom(45, 2));
        assertEquals(false, obj1.bookRoom(44, 2));
        assertEquals(false, obj1.bookRoom(555, 2));


        Guest obj2 = new Guest("Elif Buyuketi");
        assertEquals(true, obj2.bookRoom(5, 1));
        assertEquals(false, obj2.bookRoom(4, 2));
        assertEquals(false, obj2.bookRoom(7, 5));
    }

    @Test
    public void cancelReservation() throws InvalidClassException {
        Guest obj1 = new Guest("rooms_test1.csv", "Not Exist Name");
        assertEquals(false, obj1.cancelReservation(5) );

        Guest obj2 = new Guest("rooms_test1.csv", "Elif Buyuketi");
        assertEquals(false, obj2.cancelReservation(5) );
        assertEquals(false, obj2.cancelReservation(-9) );
        assertEquals(true, obj2.cancelReservation(9) );

        Guest obj3 = new Guest("Ebru A");
        assertEquals(false, obj3.cancelReservation(5) );
        assertEquals(true, obj3.cancelReservation(1) );

    }

    @Test
    public void cancelReservation1() throws InvalidClassException {
        Guest obj1 = new Guest("Not Exist Name");
        assertEquals(false, obj1.cancelReservation() );

        Guest obj2 = new Guest("Gizem UUU");
        assertEquals(true, obj2.cancelReservation() );
    }
}