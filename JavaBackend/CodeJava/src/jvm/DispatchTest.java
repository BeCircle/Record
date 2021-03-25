import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodType;

import static java.lang.invoke.MethodHandles.lookup;


public class DispatchTest {
    class GrandFather{
        void thinking(){
            System.out.println("I am grandfather");
        }
    }

    class Father extends GrandFather{
        void thinking(){
            System.out.println("I am father");
        }
    }

    class Son extends Father{
        void thinking(){
            // 在这里如何实现嗲用grandfather的thinking方法
//        GrandFather gf = new GrandFather();
//        gf.thinking();
            MethodType mt = MethodType.methodType(void.class);
            try {
                MethodHandle mh = lookup().findSpecial(GrandFather.class, "thinking", mt, getClass());
                mh.invoke(this);
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        (new DispatchTest().new Son()).thinking();
    }
}
