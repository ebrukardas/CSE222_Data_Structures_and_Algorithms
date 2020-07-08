import java.util.Vector;

public class Main {

    public static void main(String[] args){

        Vector<Integer> in = new Vector<Integer>();
        in.add(4); in.add(21);in.add(70);
        MultiDimItem<Integer> obj = new MultiDimItem<Integer>(in);

        Vector<Integer> in1 = new Vector<>();
        in1.add(10);in1.add(2);in1.add(80);
        MultiDimItem<Integer> obj2 = new MultiDimItem<>(in1);

        Vector<Integer> vec = new Vector<Integer>(3);
        vec.add(1);vec.add(4);vec.add(-9);
        MultiDimItem<Integer> obj3 = new MultiDimItem<Integer>(vec);

        Vector<Integer> in2 = new Vector<>();
        in2.add(2);
        in2.add(5);
        in2.add(10);
        MultiDimItem<Integer> obj4 = new MultiDimItem<>(in2);

        Vector<Integer> in3 = new Vector<>();
        in3.add(0);
        in3.add(8);
        in3.add(9);
        MultiDimItem<Integer> obj5 = new MultiDimItem<>(in3);

        Vector<Integer> in4 = new Vector<>();
        in4.add(-5);
        in4.add(8);
        in4.add(9);
        MultiDimItem<Integer> obj6 = new MultiDimItem<>(in4);



        MDST< MultiDimItem<Integer> > object = new MDST< MultiDimItem<Integer> > ();
		object.add(obj);
        System.out.println("----\n"+object);
		object.add(obj2);
        System.out.println("----\n"+object);
        object.add(obj3);
        System.out.println("----\n"+object);
        object.add(obj4);
        System.out.println("----\n"+object);
        object.add(obj5);

        System.out.println("----\n"+object);

        System.out.println(object.contains(obj5));
        System.out.println(object.contains(obj6));

/*
        System.out.println(object.delete(obj5)+"\n");
        System.out.println("----\n"+object);

        System.out.println(object.delete(obj6));
        System.out.println("----\n"+object);
*/
    }

}
