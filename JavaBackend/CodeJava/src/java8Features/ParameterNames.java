package java8Features;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

public class ParameterNames {
    //以上代码需要在编译时加上参数`-parameters`才有效
    public static void main(String[] arg) throws Exception {
        Method method = ParameterNames.class.getMethod( "main", String[].class );
        for( final Parameter parameter: method.getParameters() ) {
            System.out.println( "Parameter: " + parameter.getName() );
        }
    }
}