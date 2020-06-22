//package wang.JUC;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//import java.util.concurrent.*;
//
//public class test10 {
//
//    public static void main(String[] args) {
//        long length = 100000000;
//
//
//        Counter counter =new FamilyCounter();
//        long startTime = System.currentTimeMillis();
//        long total =counter.count(length);
//        long endTime = System.currentTimeMillis();
//        System.out.println(endTime-startTime);
//    }
//
//
//
//    public interface  Counter{
//        long count(long count);
//    }
//
//    public static class FamilyCounter implements Counter{
//        private int familyMember;
//        private ExecutorService pool;
//
//
//
//        public FamilyCounter(){
//            this.familyMember =4;
//            this.pool = Executors.newFixedThreadPool(this.familyMember);
//        }
//        private static class CounterRiceTask implements Callable<Long> {
//            private double[] riceArray;
//            private int from;
//            private int to;
//            public CounterRiceTask(double[] riceArray,int from, int to){
//                this.riceArray=riceArray;
//                this.from =from;
//                this.to =to;
//            }
//
//            @Override
//            public Long call() throws Exception {
//                long total =0;
//                for(int i =from;i<=to;i++){
//                    if(riceArray[i]==1) total+=1;
//                    if(total>= 100000000/4) break;
//                }
//                return total;
//            }
//        }
//
//
//        @Override
//        public long count(long count) {
//            long total = 0;
//            List<Future<Long>> results =new ArrayList<>();
//            long part =count /familyMember;
//
//            for(int i =0;i<familyMember;i++){
//                results.add(pool.submit(new FamilyCounter.CounterRiceTask(riceArray,i*part,(i+1)*part)));
//            }
//            for(Future<Long>j :results){
//                try{
//                    total += j.get();
//                }catch (InterruptedException e){
//                    e.printStackTrace();
//                }catch(ExecutionException ignore){
//                    ;
//                }
//            }
//            return total;
//        }
//    }
//
//
//
//}
