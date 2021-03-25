package concurrency;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Future;
import java.util.concurrent.locks.*;

public class LockAndCond {
    public static void testStampedLock() throws InterruptedException {
        final StampedLock lock
                = new StampedLock();
        Thread T1 = new Thread(() -> {
            // 获取写锁
            lock.writeLock();
            // 永远阻塞在此处，不释放写锁
            LockSupport.park();
        });
        T1.start();
        // 保证T1获取写锁
        Thread.sleep(1);
        //阻塞在悲观读锁
        Thread T2 = new Thread(lock::readLock);
        T2.start();
        // 保证T2阻塞在读锁
        Thread.sleep(1);
        //中断线程T2
        //会导致线程T2所在CPU飙升
        T2.interrupt();
        T2.join();
    }


    public static void main(String[] args) throws InterruptedException {
        new ConvertStampLock().moveIfAtOrigin(1,2);
    }

}

class ConvertStampLock{
    private double x, y;
    final StampedLock sl = new StampedLock();
    // 存在问题的方法
    void moveIfAtOrigin(double newX, double newY) {
        long stamp = sl.readLock();
        try {
            while (x == 0.0 && y == 0.0) {
                long ws = sl.tryConvertToWriteLock(stamp);
                if (ws != 0L) {
                    x = newX;
                    y = newY;
                    // !!升级之后需要记录最新的stamp用于解锁。
                    stamp=ws;
                    break;
                } else {
                    // 升级不成，解锁后加锁
                    sl.unlockRead(stamp);
                    stamp = sl.writeLock();
                }
            }
        } finally {
            sl.unlock(stamp);
        }
    }
}

