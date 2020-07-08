public class Otel extends Konaklama{

    public Otel(String cityName){
        super(cityName, "Otel");
    }

    @Override
    public String toString() {
        readHotelInfo( "Otel");
        return super.toString();
    }
}