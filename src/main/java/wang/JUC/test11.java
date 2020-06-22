package wang.JUC;

import java.util.LinkedList;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;

public class test11 {
    static long count = 1500000;//
    static int familyMember=4;
    static LinkedBlockingQueue<Integer> res = new LinkedBlockingQueue<>();
    public static void main(String[] args) {
        //
        // ExecutorService submit
        //Executor execute
        //Executors
        ExecutorService executor = Executors.newFixedThreadPool(familyMember);


//        for(int i = 0; i<familyMember; i++){
//            executor.execute(new RiceCounterTask( (count/15)*(1<<i)  ));
//        }
        for(int i = 0; i<familyMember; i++){
            executor.execute(new RiceCounterTask(   count/familyMember));
        }
        int mi =0;
        while(true){
            if (res.size()==4){
                for(Integer a: res){
                    System.out.println(a);
                    mi+=a;
                }
                break;
            }
        }
        System.out.println(mi);
        executor.shutdown();
        //shutdown()是调用interrupt()给与各个线程一个中断标志，空闲的线程会中断处理。
    }
    private static class RiceCounterTask implements Runnable {

        private long total;

        public RiceCounterTask(long total) {
            this.total = total;
        }

        @Override
        public void run() {
            System.out.println(total);
            long startTime = System.currentTimeMillis();
            Random rnd = new Random();
            LinkedList<Integer> t = new LinkedList<>();
            for(int i =0;i<total;i++){
                t.addLast(rnd.nextInt());
            }
            System.out.println("over");
            long endTime = System.currentTimeMillis();
            res.add((int) (endTime-startTime));
        }
    }

}
