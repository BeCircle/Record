package basic;

public class SynDemo {
    // 静态方法锁定class
    public static synchronized void staticMethod() {
        System.out.println("静态同步方法开始");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("静态同步方法结束");
    }
    // 实例方法锁定对象，两者不干扰
    public synchronized void method() {
        System.out.println("实例同步方法开始");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("实例同步方法结束");
    }

    public static void test(String[] args) {
        synchronized (SynDemo.class){
            System.out.println("同步代码块");
        }
    }

    public static void main(String[] args) {
        // 线程1执行静态方法
        Thread thread1 = new Thread(SynDemo::staticMethod);

        // 线程2执行实例方法
        SynDemo synDemo = new SynDemo();
        Thread thread2 = new Thread(synDemo::method);

        thread1.start();
        thread2.start();
    }
}
