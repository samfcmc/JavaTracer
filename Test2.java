import ist.meic.pa.Trace;

import java.util.*;

class Test {

    Map m = new HashMap();

    public Object identity(Object o) {
        return o;
    }

    public void test() {
        Object o = new String("MyObj");

        identity(o);
        m.put(2,o);
        m.get(2);

        Trace.print(o);

        for (Object obj : m.values()) {
            System.out.println(obj);
        }

        Trace.print(o);

    }
}

public class Test2 {
    public static void main(String args[]) {
        (new Test()).test();
    }
}