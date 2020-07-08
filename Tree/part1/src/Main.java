public class Main {

    public static void main(String[] args)  {

        try{
            test();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    private static void test(){

        MyTree<String> o1 = new MyTree<String>(), o2 = new MyTree<String>();

        o1.add(null, "Fatma");
        o1.add("Fatma", "Aysel");
        o1.add("Fatma", "Aysel");
        o1.add("Fatma", "Nursel");
        o1.add("Fatma", "Abdurrahman");
        o1.add("Fatma", "Mustafa");
        o1.add("Fatma", "İsmail");
        o1.add("Aysel", "Elif");
        o1.add("Aysel", "Esra");
        o1.add("Aysel", "Ebru");
        o1.add("Nursel", "Güler");
        o1.add("Nursel", "Güven");
        o1.add("Abdurrahman", "Asuman");
        o1.add("Elif", "Zeynep Ece");
        o1.add("Esra", "Oğuz");

        o2.add(null, "Zaide");
        o2.add("Zaide","İsmet");
        o2.add("Zaide","Fatma");
        o2.add("Zaide","Gülşen");
        o2.add("Zaide","Hikmet");
        o2.add("Zaide","Zeki");
        o2.add("Fatma","Nurten");
        o2.add("Fatma","Mehmet");
        o2.add("Hikmet","Elif");
        o2.add("Hikmet","Esra");
        o2.add("Gülşen","Birgül");
        o2.add("Gülşen","Fethiye");
        o2.add("Gülşen","Faruk");


        System.out.println(o1+"\n\n");
        System.out.println(o1.postOrder("İsmail")+"\n");
        System.out.println(o1.postOrder("Aysel")+"\n");
        System.out.println(o1.postOrder("Atahan")+"\n");


        System.out.println(o2+"\n\n");
        System.out.println(o2.levelOrder("Esra"));
        System.out.println(o2.levelOrder("Faruk"));
        System.out.println(o2.levelOrder("Ahmet"));

    }
}
