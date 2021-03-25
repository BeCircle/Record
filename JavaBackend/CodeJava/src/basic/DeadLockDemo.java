package basic;

public class DeadLockDemo {
    public static void main(String[] args) {
        Object lock = new Object();
        Object lock2 = new Object();
        Thread thread1 = new Thread(() -> {
            synchronized (lock){
                System.out.println(Thread.currentThread().getName() + "获取到lock锁");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (lock2){
                    System.out.println(Thread.currentThread().getName() + "获取到lock2锁");
                }
            }
        });
        Thread thread2 = new Thread(() -> {
            synchronized (lock2){
                System.out.println(Thread.currentThread().getName() + "获取到lock2锁");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (lock){
                    System.out.println(Thread.currentThread().getName() + "获取到lock锁");
                }
            }
        });
        thread1.start();
        thread2.start();
    }
}
