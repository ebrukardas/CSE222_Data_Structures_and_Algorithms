import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Set;

public abstract class Konaklama{
	Set<Info> list= new LinkedHashSet<>();
	String cityName;
	int size=0;

	public Konaklama(String cityName, String type){
		this.cityName = cityName;
		readHotelInfo(type);
		size = list.size();
	}

	/**
	 * Seçilmiş konaklama tipindeki yerleri fiyatlarına göre
	 * küçükten büyüğe sıralıyarak ekrana basıyor
	 */
	public void sortByPrice() {
		LinkedList<Info> temp = new LinkedList<Info>();
		for (Info info: list )
			temp.add(info);
		for(int j=0; j<temp.size(); ++j)
			for(int i=0; i<temp.size()-1; ++i)
				if( temp.get(i).minPrice > temp.get(i+1).minPrice ){
					Info t = temp.get(i);
					temp.set(i, temp.get(i+1));
					temp.set(i+1, t);
				}
		for (Info info: temp )
			System.out.println(info.name + "\t" + info.minPrice);
	}

	/**
	 * Konaklama yerinin ne zaman/hangi odaların müsait olduğunu ekrana basacak
	 * @param i konaklama yerinin isimleri
	 */
	public void showEnable(int i){
		String locName = getLoc(i);
		System.out.println(locName);
		try{

			String filename = locName+".csv", line = "";
			BufferedReader br = new BufferedReader( new FileReader(filename) );
			int bos = 0, dolu = 0;
			while( (line=br.readLine()) != null ) {
				String[] arr = line.split(",");
				for(int j=0; j<arr.length; ++j)
					if(!arr[j].equals("-"))
						++bos;
					else
						++dolu;
			}
			System.out.println("Boş oda sayısı:" + bos + "\tToplam oda sayısı:" + (bos+dolu) );
			br.close();
		} catch(FileNotFoundException e ) {
			System.out.println("Girdiğiniz yerin dosyası bulunamadı");
		} catch(Exception e) {
			e.getMessage();
		}
	}
	
	public void showExtra(String locName){ // ve  iletisim

		Info info = null;
		for(Info info1 : list){
			if(info1.name.equals(locName)) {
				Iterator<String> iter = info1.extraProperties.iterator();
				while(iter.hasNext())
					System.out.println(iter.next());
				break;
			}
		}

	}

	/**
	 * This method returns location name in that index.
	 * Structure LinkedHashSet, so no error here
	 * @param index index
	 * @return location name
	 */
	public String getLoc(int index){
		String  str = "";
		int i=1;
		for(Info n:list)
			if (index == i++) {
				str = n.name;
				break;
			}
		return str;
	}

	@Override
	public String toString(){
		String str = "";
		int i=0;
		for(Info n : list)
			str = str + (++i) + ". " + n + "\n";
		return str;
	}

	/**
	* Bu metod konaklama alanıyla ilgileri bilgileri okuyor.
	* @return {@code true} if the file is read successfully
	*/
	protected boolean readHotelInfo( String type ) {

		try{
			String filename = "K"+cityName+".csv", line = "";
			BufferedReader br = new BufferedReader( new FileReader(filename) );
			list.clear();
			line=br.readLine();
			while( (line=br.readLine()) != null ){
				Info o = new Info(line);
				if(o.type.equals(type))							// KBursa
					list.add(o);
			}
			br.close();

		} catch(FileNotFoundException e ) {
			System.out.println("Girdiginiz sehir dosyasi bulunamadi");
			return false;
		} catch(Exception e) {
			e.getMessage();
			return false;
		}
		return true;
	}

	public class Info{

		String type;
		String name;
		String district;
		int minPrice;
		int maxPrice;
		LinkedList<Integer> numOfPerson = new LinkedList<Integer>();
		LinkedList<String> extraProperties = new LinkedList<String>();
		String connect="";

		public Info(String line){
			String[] arr = line.split(",");
			type = arr[0];
			name = arr[1];
			district = arr[2];
			minPrice = Integer.parseInt(arr[3]);
			maxPrice = Integer.parseInt(arr[4]);
			String arr3[] = arr[5].split("!");
			for (int i=0; i<arr3.length; ++i )
				numOfPerson.add( Integer.parseInt(arr3[i]) );
			String[] arr2 = arr[6].split("/");
			for (int i=0; i<arr2.length ; ++i )
				extraProperties.add(arr2[i]);
			if(arr.length==8)
				connect = arr[7];
			else
				connect = "-";
		}

		@Override
		public String toString(){
			String str = "";
			str = name + "\t\t" + district + "\t\tMinimum fiyat:" + minPrice + "\tMaksimum fiyat:" + maxPrice + "\t\t" ;
/*			for(int i =0; i<numOfPerson.size(); ++i)
				str = str + numOfPerson.get(i)+ " - ";
*/			str = str + "\t\t";
			for (int i =0; i<extraProperties.size(); ++i)
			 	str = str + extraProperties.get(i) + ", ";
			 return str;
		}


	}

}