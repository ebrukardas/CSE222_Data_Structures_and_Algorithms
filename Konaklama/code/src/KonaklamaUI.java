import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class KonaklamaUI {

    public void main() throws IOException {
        System.out.println("=================================================================================");
        System.out.println("\n\t\t\t\tKONAKLAMA BOLUMUMUZE HOSGELDINIZ\n");
        System.out.println("=================================================================================");
        kullanici();
    }

    public void kullanici() throws IOException {
        String choice = "", city="";
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int flag=-1, i=0;

        do {
            do {
                System.out.println("Asagidaki konaklama seceneklerinden birini giriniz:");
                System.out.println("\t1- Otel\n\t2- Apart\n\t3- Kiralik Ev\n\t4- Cikis");
                choice = br.readLine();
            } while (!(choice.equals("1")) && !(choice.equals("2")) && !(choice.equals("3"))&& !(choice.equals("4")));

            if(choice.equals("4")) {
                System.out.println("Tekrar bekleriz :)");
                return;
            }
            String temp="";
            do {
                try {
                    flag=0;
                    System.out.println("Asagida belirtilen sehirlerden birinin numarasini giriniz:");
                    System.out.println("1- ANKARA\n" +
                            "2- ANTALYA\n" +
                            "3- BOLU\n" +
                            "4- BURSA\n" +
                            "5- GAZI ANTEP\n" +
                            "6- ISTANBUL\n" +
                            "7- IZMIR\n" +
                            "8- MUGLA\n" +
                            "9- NEVSEHIR\n" +
                            "10- TRABZON\n");
                    temp = br.readLine();
                    i = Integer.parseInt(temp);
                }catch(NumberFormatException e){
                    System.out.println("Bir SAYI giriniz.");
                    flag=-1;
                }
            }while( i<1 || i>10  || flag==-1 );
            flag=-1;

            switch (i){
                case 1:
                    city = "ANKARA";
                    break;
                case 2:
                    city = "ANTALYA";
                    break;
                case 3:
                    city = "BOLU";
                    break;
                case 4:
                    city = "BURSA";
                    break;
                case 5:
                    city = "GAZI ANTEP";
                    break;
                case 6:
                    city = "ISTANBUL";
                    break;
                case 7:
                    city = "IZMIR";
                    break;
                case 8:
                    city = "MUGLA";
                    break;
                case 9:
                    city = "NEVSEHIR";
                    break;
                case 10:
                    city = "TRABZON";
                    break;
            }

            Konaklama k;
            if (choice.equals("1"))
                k = new Otel(city);
            else if (choice.equals("2"))
                k = new Apart(city);
            else
                k = new KiralikEv(city);

            if(k.size==0){
                System.out.println("Bilgi bulunamadi. Lütfen başka bir seçenekle tekrar deneyiniz.");
                choice="e";
                continue;
            }

            System.out.println(k);

            do {
                do {
                    System.out.println("İstediginiz islemi giriniz:");
                    System.out.println("\t1- Fiyata göre sırala\n\t2- Detaylı bilgi\n\t3- Sistemden cikis");
                    choice = br.readLine();
                } while (!(choice.equals("1")) && !(choice.equals("2")) && !(choice.equals("3")));


                if (choice.equals("1"))
                    k.sortByPrice();
                else if (choice.equals("2")) {
                    do{
                        System.out.print("Istediginiz yerin numarasini giriniz:");
                        i=0; flag=0;
                        try{
                            choice = br.readLine();
                            i = Integer.parseInt(choice);
                        }catch(NumberFormatException e) {
                            System.out.println(choice + "girildi. Bir SAYI giriniz");
                            flag=-1;
                        }
                    }while(i<=0 || i>k.size || flag==-1);
                    if(k instanceof Otel)
                        k.showEnable(i);
                    System.out.println(" ");
                    k.showExtra(k.getLoc(i));
                } else {
                    System.out.println("Tekrar bekleriz :)");
                    return;
                }

                do{
                    System.out.println("Baska islem yapmak ister misiniz?(E/H)");
                    choice = br.readLine();
                }while(!(choice.equals("E")) && !(choice.equals("H")) && !(choice.equals("e")) && !(choice.equals("h")));

            }while( choice.equals("E") || choice.equals("e") );

            do{
                System.out.println("Geri dönmek ister misiniz?(E/H)");
                choice = br.readLine();
            }while(!(choice.equals("E")) && !(choice.equals("H")) && !(choice.equals("e")) && !(choice.equals("h")));

        }while(choice.equals("E") || choice.equals("e"));

    }

}
