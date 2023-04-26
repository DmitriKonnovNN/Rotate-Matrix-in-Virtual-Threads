package solutions.dmitrikonnov.demo;

import java.util.Arrays;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class MatrixRotator implements RotateMatrix {

    public static void rotate(int [][] matrix, int[][]tempMatrix,int i, int j, int k, int l){
        System.out.println("Current " + Thread.currentThread());
        final int tempI = i;
        final int tempJ = j;

        for (; i < l; i++){
            for (; j < k; j++){
                tempMatrix[j][matrix.length - 1 - i] = matrix[i][j];

            }
            if (j==k)j=tempJ;
        }
        i = tempI;
        j = tempJ;

        for (;i < l; i++){
            for(;j < k; j++){
                matrix[i][j]= Utils.generateHighCpuLoad(tempMatrix[i][j]);

            }
            if (j==k)j=tempJ;
        }
    }
    public static void rotate(int [][] matrix){
        rotate(matrix,new int[matrix.length][matrix[0].length],0,0,matrix.length,matrix[0].length );
    }

    @Override
    public void rotate90VirtualThread(MatrixRotatorTask[] tasks, boolean withExecutor, boolean withFactory){
        if (withExecutor && withFactory) rotate90VirtualThreadWithExecutorAndFactory(tasks);
        else if (withExecutor) rotate90VirtualThreadWithExecutor(tasks);
        else if (withFactory) rotate90VirtualThreadWithFactory(tasks);
        else rotate90VirtualThread(tasks);

    }

    @Override
    public void rotate90VirtualThread(MatrixRotatorTask[] tasks) {
        System.out.println("Execution in Virtual Threads started!\nWait…");
        var startTime =System.currentTimeMillis();

        Arrays.stream(tasks).forEach(task -> {
            try {
                executeWithVirtualThreads(task::compute).join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        var endTime = System.currentTimeMillis();
        System.out.println("Time elapsed: " + (endTime - startTime) + " ms");
    }


    public void rotate90VirtualThreadWithFactory(MatrixRotatorTask[] tasks){
        System.out.println("Execution in Virtual Threads started!\nWait…");
        var startTime =System.currentTimeMillis();
        ThreadFactory factory = Thread.ofVirtual().name("VT-factory",0).factory();
        Arrays.stream(tasks).forEach(task -> {
            try {
                executeWithFactoryOfVirtualThreads(task::compute,factory).join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        var endTime = System.currentTimeMillis();
        System.out.println("Time elapsed: " + (endTime - startTime) + " ms");
    }


    public void rotate90VirtualThreadWithExecutor(MatrixRotatorTask[] tasks ){
        System.out.println("Execution in Virtual Threads started!\nWait…");
        var startTime =System.currentTimeMillis();

        try (ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor()) {
            Arrays.stream(tasks).forEach(task -> executor.submit(task::compute));

        }

        var endTime = System.currentTimeMillis();
        System.out.println("Time elapsed: " + (endTime - startTime) + " ms");
    }

    //TODO: fix
    public void rotate90VirtualThreadWithExecutorAndFactory(MatrixRotatorTask[] tasks) {
        System.out.println("Execution in Virtual Threads started!\nWait…");
        var startTime =System.currentTimeMillis();
        ThreadFactory factory = Thread.ofVirtual().name("VT-factory",0).factory();
        try (ExecutorService executor = Executors.newThreadPerTaskExecutor(factory)) {
            Arrays.stream(tasks).forEach(task -> executor.submit(task::compute));
        }
        var endTime = System.currentTimeMillis();
        System.out.println("Time elapsed: " + (endTime - startTime) + " ms");
    }

    private Thread executeWithVirtualThreads(Runnable task){

        var thread  = Thread.ofVirtual().name("inner-thread",0).start(task);

        return thread;
    }

    private Thread executeWithFactoryOfVirtualThreads(Runnable task, ThreadFactory factory){
        Thread thread = factory.newThread(task);
        thread.start();
        return thread;
    }

    @Override
    public void rotate90Sequential(MatrixRotatorTask[] tasks) {

        executeWithMetrics(()-> Arrays.stream(tasks).forEach(MatrixRotatorTask::compute));

    }


    @Override
    public void rotate90CompletableFuture(MatrixRotatorTask[] tasks) {
        executeWithMetrics(()->{
            var futures = Arrays.stream(tasks)
                    .map(task -> CompletableFuture.runAsync(task::compute)).toList();

            futures.forEach(CompletableFuture::join);
        });

    }

    @Override
    public void rotate90CompletableFutureWithExecutor(MatrixRotatorTask[] tasks, int numberOfThreads) {

        try (ExecutorService executor = Executors.newFixedThreadPool(Math.min(numberOfThreads, tasks.length))){

            executeWithMetrics(()->{
                var futures = Arrays.stream(tasks)
                        .map(task -> CompletableFuture.runAsync(task::compute, executor)).toList();

                futures.forEach(CompletableFuture::join);
            });
        }
    }

    @Override
    public void rotate90ParallelStream(MatrixRotatorTask[] tasks) {

        executeWithMetrics(()-> Arrays.stream(tasks).parallel().forEach(MatrixRotatorTask::compute));

    }



    private void executeWithMetrics(Runnable task){

        System.out.println("Execution started!\nWait…");
        var startTime =System.currentTimeMillis();
        task.run();
        var endTime = System.currentTimeMillis();
        System.out.println("Time elapsed: " + (endTime - startTime) + " ms");

    }


    @Override
    public void rotate180Sequential(MatrixRotatorTask[] tasks) {
        rotate90Sequential(tasks);
        rotate90Sequential(tasks);
    }

    @Override
    public void rotate180CompletableFuture(MatrixRotatorTask[] tasks) {
        rotate90CompletableFuture(tasks);
        rotate90CompletableFuture(tasks);
    }

    @Override
    public void rotate180CompletableFutureWithExecutor(MatrixRotatorTask[] tasks, int numberOfThreads) {
        rotate90CompletableFutureWithExecutor(tasks, numberOfThreads);
        rotate90CompletableFutureWithExecutor(tasks, numberOfThreads);
    }

    @Override
    public void rotate180ParallelStream(MatrixRotatorTask[] tasks) {
        rotate90ParallelStream(tasks);
        rotate90ParallelStream(tasks);
    }

    @Override
    public void rotate270Sequential(MatrixRotatorTask[] tasks) {
        rotate180Sequential(tasks);
        rotate90Sequential(tasks);
    }

    @Override
    public void rotate270CompletableFuture(MatrixRotatorTask[] tasks) {
        rotate180CompletableFuture(tasks);
        rotate90CompletableFuture(tasks);
    }

    @Override
    public void rotate270CompletableFutureWithExecutor(MatrixRotatorTask[] tasks, int numberOfThreads) {
        rotate180CompletableFutureWithExecutor(tasks, numberOfThreads);
        rotate90CompletableFutureWithExecutor(tasks, numberOfThreads);
    }

    @Override
    public void rotate270ParallelStream(MatrixRotatorTask[] tasks) {
        rotate180ParallelStream(tasks);
        rotate90ParallelStream(tasks);
    }
}
