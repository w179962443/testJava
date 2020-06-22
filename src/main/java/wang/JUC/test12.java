package wang.JUC;

import java.util.LinkedList;
import java.util.Random;
import java.util.concurrent.*;

public class test12 {


    static long count = 1500000;//
    static int familyMember=4;
    static LinkedBlockingQueue<Integer> res = new LinkedBlockingQueue<>();
    public static void main(String[] args) {
        //
        // ExecutorService submit
        //Executor execute
        //Executors
//        ExecutorService executor = Executors.newFixedThreadPool(familyMember);
        ForkJoinPool pool= new ForkJoinPool(familyMember);


//        for(int i = 0; i<familyMember; i++){
//            executor.execute(new RiceCounterTask(   count/familyMember));
//        }
        long startTime = System.currentTimeMillis();
        pool.invoke(new RiceCounterTask(0,count));
        System.out.println("gg");
        long endTime = 0;


        while(true){
            if (res.size()==4){//res.size()==6
                endTime =System.currentTimeMillis();
                break;
            }
        }
        System.out.println(endTime-startTime);

        //shutdown()是调用interrupt()给与各个线程一个中断标志，空闲的线程会中断处理。
    }
    private static class RiceCounterTask extends RecursiveTask<Long> {
        private int  THRESHOLD = (int) (count/familyMember);
        private long from;
        private long to;

        public RiceCounterTask(long from, long to) {
            this.from = from;
            this.to = to;
        }

        @Override
        protected Long compute() {

            if(to - from <=THRESHOLD){
                int total = (int) (to - from);
                //System.out.println(total+" "+from+" "+to);

                Random rnd = new Random();
                LinkedList<Integer> t = new LinkedList<>();
                for(int i =0;i<total;i++){
                    t.addLast(rnd.nextInt());
                }
                System.out.println("over");
                res.add(1);
                return to-from;
            }else{
                //int mid = (int) ((to-from  )/3+from);//非平均任务分配
                int mid = (int) ((to+from)/2);
                System.out.println(" "+from+" "+mid+" "+to);
                RiceCounterTask left =new RiceCounterTask(from,mid);
                left.fork();
                RiceCounterTask right =new RiceCounterTask(mid+1,to);
                right.fork();
                return left.join()+right.join();

            }



        }
    }



}
