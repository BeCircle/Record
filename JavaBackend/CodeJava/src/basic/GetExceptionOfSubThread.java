package basic;

import java.util.Arrays;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class GetExceptionOfSubThread {
    public static void catchInFuture() {
        Arrays.asList( "a", "b", "d" ).sort( ( e1, e2 ) -> e1.compareTo( e2 ) );
        FutureTask<Integer> futureTask = new FutureTask<>(() ->  {
            System.out.println(Thread.currentThread().getId());
            Thread.sleep(10000);
            return 1/0;
        });
        Thread thread = new Thread(futureTask);
        thread.start();
        try {
            futureTask.get();
        } catch (InterruptedException | ExecutionException  e) {
            System.out.println(Thread.currentThread().getId()+":catched exception in main thread by future");
        }
    }

    public static void catchByExceptionHandler() {
        Thread thread = new Thread(() -> {
            System.out.println(Thread.currentThread().getId());
            System.out.println(1 / 0);
        });
        Thread.UncaughtExceptionHandler eh = new MyUncaughtExceptionHandler();
        thread.setUncaughtExceptionHandler(eh);
        thread.start();
    }

    static class MyUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {
        @Override
        public void uncaughtException(Thread t, Throwable e) {
            // 异常处理，日志记录等
            // handler是在子线程中运行
            System.out.println(Thread.currentThread().getId()+":catched exception in ExceptionHandler");
        }

    }

    public static void main(String[] args) {
        catchInFuture();
        catchByExceptionHandler();
    }
}

