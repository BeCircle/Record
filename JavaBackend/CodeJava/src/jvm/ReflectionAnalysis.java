import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectionAnalysis {
    static class Proxy{
        public void run(){
            System.out.println("run");
        }
    }

    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Proxy target = new Proxy();
        Method method = Proxy.class.getDeclaredMethod("run");
        method.invoke(target);
    }
}
