import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class SubDynamicProxy implements InvocationHandler {
    private Object realObject;

    public Object bind(Object realObject) {
        this.realObject = realObject;
        return Proxy.newProxyInstance(
                realObject.getClass().getClassLoader(),
                realObject.getClass().getInterfaces(),
                this
        );
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("addition process.");
        return method.invoke(realObject, args);
    }
}
