package solutions.dmitrikonnov.demo;

import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.atomic.AtomicInteger;

public class MatrixRotatorRecursiveTask extends MatrixRotatorRecursiveAbstractTask implements MatrixRotatorTask {
    public MatrixRotatorRecursiveTask(int[][] matrix) {
        super(matrix);
    }

    public MatrixRotatorRecursiveTask(int[][] matrix, int[][] newMatrix, int i, int j, int k, int l) {
        super(matrix, newMatrix, i, j, k, l);
    }

    @Override
    HexaInitializer<MatrixRotatorRecursiveTask> getInitializer(){
        HexaInitializer<MatrixRotatorRecursiveTask> initializer = (matrix, newMatrix, i, j, k, l) -> {
            return new MatrixRotatorRecursiveTask(matrix,newMatrix,i,j,k,l);
        };
        return initializer;
    }

    /**
     * Does rotation, if matrix isn't splittable. Otherwise, splits vertically or/and horizontally, creates new recursive task
     * and submits those to ForkJoinPool.
     * */
    @Override
    public void compute() {
        if(i==k || j==l) return;

        if ((k-i)%THRESHOLD==0 && k-i!=THRESHOLD && (l-j)%THRESHOLD==0 && l-j!=THRESHOLD && k!=THRESHOLD && l!=THRESHOLD ){
            System.out.println("All across split " + allAcrossSplit.incrementAndGet() + " times");
            ForkJoinTask.invokeAll(createSubtaskAllAcrossSplit());
        }
        else if ((k-i)%THRESHOLD==0 && k!=THRESHOLD && k-i!=THRESHOLD)
            {
                System.out.println("Vertical split");
                ForkJoinTask.invokeAll(createSubtaskVerticalSplit());
            }
        else if ((l-j)%THRESHOLD==0 && l!=THRESHOLD && l-j!=THRESHOLD)
            {
                System.out.println("Horizontal split");
                ForkJoinTask.invokeAll(createSubtaskHorizontalSplit());
            }

        else {
            System.out.println("Sequential " + counterRotation.incrementAndGet() + " times");
            MatrixRotator.rotate(matrix, newMatrix, i, j, matrix.length, matrix[0].length);}
    }

}
