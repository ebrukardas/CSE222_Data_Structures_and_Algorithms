public class Apart extends Konaklama{

    public Apart(String cityName){
        super(cityName, "Apart");
    }

    @Override
    public String toString() {
        readHotelInfo( "Apart");
        return super.toString();
    }
}