package solutions.dmitrikonnov.demo;

import java.util.Arrays;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MatrixRotator implements RotateMatrix {

    public static void rotate(int [][] matrix, int[][]tempMatrix,int i, int j, int k, int l){

        final int tempI = i;
        final int tempJ = j;
//        System.out.println("Inside rotate: before");
//        main.java.solutions.dmitrikonnov.Utils.printMatrix(matrix);
        for (; i < l; i++){
            for (; j < k; j++){
                tempMatrix[j][matrix.length - 1 - i] = matrix[i][j];

            }
            if (j==k)j=tempJ;
        }
        i = tempI;
        j = tempJ;
//        System.out.println("Inside rotate: tempMatrix after");
//        main.java.solutions.dmitrikonnov.Utils.printMatrix(tempMatrix);
        for (;i < l; i++){
            for(;j < k; j++){
                matrix[i][j]= Utils.generateHighCpuLoad(tempMatrix[i][j]);

            }
            if (j==k)j=tempJ;
        }
//        System.out.println("Inside rotate: matrix after rotate");
//        main.java.solutions.dmitrikonnov.Utils.printMatrix(matrix);
    }
    public static void rotate(int [][] matrix){
        rotate(matrix,new int[matrix.length][matrix[0].length],0,0,matrix.length,matrix[0].length );
    }

    @Override
    public void rotate90VirtualThead(MatrixRotatorTask[] task) {
        executeWithVirtualThreads(()-> Arrays.stream(task).forEach(MatrixRotatorTask::compute));
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

        ExecutorService executor = Executors.newFixedThreadPool(Math.min(numberOfThreads, tasks.length));

        executeWithMetrics(()->{
            var futures = Arrays.stream(tasks)
                    .map(task -> CompletableFuture.runAsync(task::compute, executor)).toList();

            futures.forEach(CompletableFuture::join);
        });

        executor.shutdown();
    }

    @Override
    public void rotate90ParallelStream(MatrixRotatorTask[] tasks) {

        executeWithMetrics(()-> Arrays.stream(tasks).parallel().forEach(MatrixRotatorTask::compute));

    }

    private void executeWithVirtualThreads(Runnable task){
        System.out.println("Execution in Virtual Threads started!\nWait…");
        var startTime =System.currentTimeMillis();
        Thread.ofVirtual().start(task);
        var endTime = System.currentTimeMillis();
        System.out.println("Time elapsed: " + (endTime - startTime) + " ms");
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
