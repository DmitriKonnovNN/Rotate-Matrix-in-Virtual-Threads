import java.util.Arrays;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MatrixRotator implements RotateMatrix {

    public static void rotate(int [][] matrix, int[][]newMatrix,int i, int j, int k, int l){

        for (; i < l; i++){
            for (; j < k; j++){
                newMatrix[j][matrix.length - 1 - i] = matrix[i][j];
            }
        }
    }
    public static void rotate(int [][] matrix){
        rotate(matrix,new int[matrix.length][matrix[0].length],0,0,matrix.length,matrix[0].length );
    }


    @Override
    public void rotate90Sequential(MatrixRotatorTask[] tasks) {

        executeWithMetrics(()-> Arrays.stream(tasks).peek(MatrixRotatorTask::compute));

    }


    @Override
    public void rotate90CompletableFuture(MatrixRotatorTask[] tasks) {

        executeWithMetrics(()->{
            var futures = Arrays.stream(tasks)
                    .map(task -> CompletableFuture.runAsync(task::compute)).toList();

            futures.stream().peek(CompletableFuture::join);
        });

    }

    @Override
    public void rotate90CompletableFutureWithExecutor(MatrixRotatorTask[] tasks, int numberOfThreads) {

        ExecutorService executor = Executors.newFixedThreadPool(Math.min(numberOfThreads, tasks.length));

        executeWithMetrics(()->{
            var futures = Arrays.stream(tasks)
                    .map(task -> CompletableFuture.runAsync(task::compute, executor)).toList();

            futures.stream().peek(CompletableFuture::join);
        });

        executor.shutdown();
    }

    @Override
    public void rotate90ParallelStream(MatrixRotatorTask[] tasks) {

        executeWithMetrics(()-> Arrays.stream(tasks).parallel().peek(MatrixRotatorTask::compute));

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
