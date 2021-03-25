import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodType;

import static java.lang.invoke.MethodHandles.lookup;

public class MethodHandleTest {
    static class ClassA{
        public void println(String s){
            System.out.println(s);
        }
    }

    public static void main(String[] args) throws Throwable {
        // 动态类型模拟
        Object object = System.currentTimeMillis()%2==0?System.out:new ClassA();
        getPrintLnMH(object).invokeExact("hello");
    }
    private static MethodHandle getPrintLnMH(Object receiver) throws Throwable{
        MethodType mt = MethodType.methodType(void.class, String.class);
        return lookup().findVirtual(receiver.getClass(), "println", mt).bindTo(receiver);
    }
}
