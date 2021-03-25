package basic;

public class DameThread {

    /**
     * Main 线程结束进程不会结束，所有非守护线程结束，进程结束
     * */
    public static void main(String[] args) {
        Thread t = new MyThread();
        Thread t2 = new MyThread2();
        // 如果注释下一行，观察Thread1的执行情况:
        t.setDaemon(true);
        t.start();
        t2.start();
        System.out.println("main: wait 3 sec...");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
        }
        // 非
        System.out.println("main: end.");
    }

}

class MyThread extends Thread {

    public void run() {
        for (;;) {
            System.out.println(Thread.currentThread().getName());
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
            }
        }
    }
}

class MyThread2 extends Thread {

    public void run() {
        for (int i=0; i<10;i++) {
            System.out.println(Thread.currentThread().getName());
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
            }
        }
        System.out.println(Thread.currentThread().getName()+"end");
    }
}
