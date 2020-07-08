import javafx.util.Pair;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

public class DoubleHashingMap<K,V> implements Map<K,V> {

    int SIZE;
    int capasity;
    int deleted;
    Pair<K,V> [] table;

    public DoubleHashingMap(){
        capasity = 43;
        deleted=0;
        table = new Pair[capasity];
        clear();
    }

    @Override
    public int size() {
        return SIZE;
    }

    @Override
    public boolean isEmpty() {
        return SIZE==0;
    }

    @Override
    public boolean containsKey(Object key) {
        int k = (int) key;
        if(k<0 || k>=capasity)
            return  false;
        return table[k] != null;
    }

    private Pair<K, V> search(V value){
        int k = hashCode1(value);
        if(table[k] != null)
            return table[k];
        int i=1;
        int ind = hashCode2(value);
        while (i<=SIZE){
            k = (k + (i*ind)) % capasity;
            if(table[k] != null)
                return table[k];
            ++i;
        }
        return null;
    }

    @Override
    public boolean containsValue(Object value) {
        V v = (V) value;
        return search(v)!=null;
    }

    @Override
    public V get(Object key) {
        int k = (int) key;
        try {
            return table[k].getValue();
        }catch (NullPointerException e){
            return null;
        }
    }

    @Override
    public V put(K key, V value) {
        if(containsValue(value))
            return null;
        if(SIZE>0 &&capasity/SIZE == 2){
//            rehash();
            return null;
        }
         int k = hashCode1(value);
         int i = hashCode2(value);
         System.out.println(k + "-" + i);
         while (table[k] != null){
             k = (k+i) % capasity;
         }
         table[k] = new Pair<>(key, value);
         ++SIZE;
         return value;
    }

    @Override
    public V remove(Object key) {
/*        int k = (int)key;
        if(table[k] != null) {
            table[k] = new Pair<>(k, new deleted()) ;
        }
        int i=1;
        int ind = hashCode2(value);
        while (table[k] != null){
            k = (k + (i*ind)) % capasity;
            ++i;
        }

*/        return null;
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {

    }

    @Override
    public void clear() {
        SIZE = 0;
        for(int i=0; i<capasity; ++i)
            table[i] = null;

    }

    @Override
    public Set<K> keySet() {
        return null;
    }

    @Override
    public Collection<V> values() {
        return null;
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        return null;
    }

    @Override
    public String toString() {
        String str = "";
        for(int i=0; i<table.length; ++i)
            str = str + table[i] + " | ";
        return str;
    }

    private int hashCode1(V str){
        return str.hashCode() % capasity;
    }

    private int hashCode2(V str){
        return 7 - (str.hashCode() % 7);
    }

    public class deleted/*extends V*/ {
        private String str = "deleted";

        @Override
        public String toString() {
            return str;
        }
    }

}
