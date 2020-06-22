package wang.JUC;

import java.util.concurrent.TimeUnit;

public class testTimeUnit {
    public static void main(String[] args) {

//sleep是什么？它是一个静态方法，暂停线程时它不会释放锁，该方法会抛出InterrupttedException异常（如果有线程中断了当前线程）

        //一个库，用于替代Thread.sleep，那个方法的单位是毫秒，很不好处理。
        new Thread( new Runnable() {
            @Override
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep( 5 );
                    System.out.println( "延时5秒，完成了");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();  ;

        new Thread( new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep( 5 * 1000 );
                    System.out.println( "延时完成了");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();



    }



}
