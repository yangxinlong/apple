package jewelry.thread;

/**
 * Created by yang on 16-1-21.
 */
public class AutoSaleTicket implements Runnable {
    private int ticket=30;
    /**
     * Starts executing the active part of the class' code. This method is
     * called when a thread is started that has been created with a class which
     * implements {@code Runnable}.
     */
    @Override
    public void run() {
        while (true) {// 循环是指线程不停的去卖票
            // 当操作的是共享数据时,用同步代码块进行包围起来,这样在执行时,只能有一个线程执行同步代码块里面的内容
            synchronized (this) {
                if (ticket > 0) {

                    // 不要在同步代码块里面sleep,作用只是自已不执行,也不让线程执行
                    System.out.println(Thread.currentThread().getName()
                            + " 卖出 第 " + (20 - ticket + 1) + " 张票");
                    ticket--;

                } else {
                    break;
                }
            }
            // 所以把sleep放到同步代码块的外面,这样卖完一张票就休息一会,让其他线程再卖,这样所有的线程都可以卖票
            try {
                Thread.sleep(200);
            } catch (Exception ex) {
            }
        }
    }
}
