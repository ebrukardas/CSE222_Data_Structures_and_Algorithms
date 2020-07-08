public class Main {

    public static void main(String[] args){

        DoubleHashingMap<Integer, String> obj = new DoubleHashingMap<>();
        obj.put(5, "esra");
        System.out.println(obj);
        obj.put(8, "sera");
        System.out.println(obj);

        System.out.println("SIZE:"+obj.size());
        System.out.println("Is empty: "+obj.isEmpty());
        System.out.println("get key:10 ->" + obj.get(10));
        System.out.println("key:"+obj.containsKey(7));
        System.out.println("value:"+obj.containsValue("esra"));

    }

}
