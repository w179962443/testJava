package wang.JUC;

import java.util.concurrent.*;

public class testFuture {

    //future就是异步地写程序。


    public static void main(String[] args) throws ExecutionException, InterruptedException
    {
        FutureTask<Integer> futureTask = new FutureTask(new Mythread());



        ExecutorService executor = Executors.newCachedThreadPool();
        executor.submit(futureTask);
        int i =0;
        while(!futureTask.isDone()){
            i+=1;
        }


        //FutureTask的get会导致阻塞
        Integer result = futureTask.get();
        System.out.println(result);
        System.out.println(i);
        executor.shutdown();
    }

    static class Mythread implements Callable<Integer>
    {
        @Override
        public Integer call() throws Exception
        {
            Thread.sleep(TimeUnit.MILLISECONDS.toSeconds(4));
            System.out.println("*****come in call method()");
            return 1024;

        }
    }


}
