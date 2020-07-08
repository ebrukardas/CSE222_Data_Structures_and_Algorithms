public class KiralikEv extends Konaklama{

    public KiralikEv(String cityName){
        super(cityName, "Kiralik Ev");
    }

    @Override
    public String toString() {
        readHotelInfo( "Kiralik Ev");
        return super.toString();
    }
}