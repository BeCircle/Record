package concurrency;

import java.util.Arrays;
import java.util.List;

public class Parallism {
    public static void testA(){
        List<String> values = Arrays.asList("1200","1300","1400");
        values.parallelStream().forEach(x-> {
            try {
                test(x);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });//System.out表示对象，println表示方法
    }

    public static void test(String oper) throws InterruptedException {
        if(oper.equalsIgnoreCase("1200")){
//            Thread.sleep(1000);
            System.out.println(oper);
        }
        if(oper.equalsIgnoreCase("1300")){
            System.out.println(oper);
        }
        if(oper.equalsIgnoreCase("1400")){
//            Thread.sleep(2000);
            System.out.println(oper);
        }
    }

    public static void main(String[] args) {
        testA();
    }
}

