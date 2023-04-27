package solutions.dmitrikonnov.demo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.atomic.AtomicInteger;

public class MatrixRotatorRecursiveInVirtualThreadsTask extends MatrixRotatorRecursiveAbstractTask implements MatrixRotatorTask{



    public MatrixRotatorRecursiveInVirtualThreadsTask(int[][] matrix) {
        super(matrix);
    }

    public MatrixRotatorRecursiveInVirtualThreadsTask(int[][] matrix, int[][] newMatrix, int i, int j, int k, int l) {
        super(matrix, newMatrix, i, j, k, l);
    }

   @Override
   HexaInitializer<MatrixRotatorRecursiveInVirtualThreadsTask> getInitializer(){
        HexaInitializer<MatrixRotatorRecursiveInVirtualThreadsTask> initializer = (matrix, newMatrix, i, j, k, l) -> {
            return new MatrixRotatorRecursiveInVirtualThreadsTask(matrix,newMatrix,i,j,k,l);
        };
        return initializer;
    }




    @Override
    public void compute() {
        if(i==k || j==l) return;
        try(ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor()) {
            if ((k-i)%THRESHOLD==0 && k-i!=THRESHOLD && (l-j)%THRESHOLD==0 && l-j!=THRESHOLD && k!=THRESHOLD && l!=THRESHOLD ){
                executor.submit(()->{
                    System.out.println("All across split " + allAcrossSplit.incrementAndGet() + " times");
                    createSubtaskAllAcrossSplit().forEach(MatrixRotatorRecursiveAbstractTask::compute);

                });
            }
            else if ((k-i)%THRESHOLD==0 && k!=THRESHOLD && k-i!=THRESHOLD)
            {
                executor.submit(()->{
                    System.out.println("Vertical split");
                    createSubtaskVerticalSplit().forEach(MatrixRotatorRecursiveAbstractTask::compute);
                });
            }
            else if ((l-j)%THRESHOLD==0 && l!=THRESHOLD && l-j!=THRESHOLD)
            {
                executor.submit(()->{
                    System.out.println("Horizontal split");
                    createSubtaskHorizontalSplit().forEach(MatrixRotatorRecursiveAbstractTask::compute);
                });
            }

            else {
                System.out.println("Sequential" + counterRotation.incrementAndGet() + " times");
                MatrixRotator.rotate(matrix, newMatrix, i, j, matrix.length, matrix[0].length);}
        }
    }

}
