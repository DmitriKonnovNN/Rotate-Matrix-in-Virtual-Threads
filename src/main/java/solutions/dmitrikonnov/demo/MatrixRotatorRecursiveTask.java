package solutions.dmitrikonnov.demo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveAction;
import java.util.stream.IntStream;

public class MatrixRotatorRecursiveTask extends RecursiveAction implements MatrixRotatorTask {
    private static final int THRESHOLD = 10;
    private int[][] matrix;
    private int[][] newMatrix;
    int i,j,k,l;

    public MatrixRotatorRecursiveTask(int[][] matrix) {
        if(matrix.length!=matrix[0].length) throw new RuntimeException("Rotating of matrix with unequal length and with is not implemented!");
        this.matrix = matrix;
        this.newMatrix = new int[matrix.length][matrix[0].length];
        this.i = 0;
        this.j = 0;
        this.k = matrix.length;
        this.l = matrix[0].length;
    }

    public MatrixRotatorRecursiveTask(int[][] matrix, int [][] newMatrix, int i, int j, int k, int l) {
        this.matrix = matrix;
        this.newMatrix = newMatrix;
        this.i = i;
        this.j = j;
        this.k = k;
        this.l = l;
    }


    /**
     * Does rotation, if matrix isn't splittable. Otherwise, splits vertically or/and horizontally, creates new recursive task
     * and submits those to ForkJoinPool.
     * */
    @Override
    public void compute() {
        if(i==k || j==l) return;

        if ((k-i)%THRESHOLD==0 && matrix.length==THRESHOLD && (l-j)%THRESHOLD==0 && matrix[0].length==THRESHOLD && k!=THRESHOLD && l!=THRESHOLD ){
            ForkJoinTask.invokeAll(createSubtaskAllAcrossSplit());
        }
        else if ((k-i)%THRESHOLD==0 && k!=THRESHOLD && matrix.length==THRESHOLD)
            {
                ForkJoinTask.invokeAll(createSubtaskVerticalSplit());
            }
        else if ((l-j)%THRESHOLD==0 && l!=THRESHOLD && matrix[0].length==THRESHOLD)
            {
                ForkJoinTask.invokeAll(createSubtaskHorizontalSplit());
            }

        else MatrixRotator.rotate(matrix, newMatrix, i, j, matrix.length, matrix[0].length);
    }

    private Collection<MatrixRotatorRecursiveTask> createSubtaskVerticalSplit() {
        Collection<MatrixRotatorRecursiveTask> dividedTasks = new ArrayList<>();
        int divider = (k-i)/THRESHOLD;
        IntStream.range(0,divider).forEach(d-> dividedTasks.add(new MatrixRotatorRecursiveTask(
                matrix,
                newMatrix,
                THRESHOLD*d,
                j,
                THRESHOLD*(d+1),l )));
        return dividedTasks;
    }
    private Collection<MatrixRotatorRecursiveTask> createSubtaskHorizontalSplit() {
        List<MatrixRotatorRecursiveTask> dividedTasks = new ArrayList<>();
        int divider = (l-j)/THRESHOLD;
        IntStream.range(0,divider).forEach(d-> dividedTasks.add(new MatrixRotatorRecursiveTask(
                matrix,
                newMatrix,
                i,
                j*THRESHOLD,
                k,
                THRESHOLD*(l+1) )));
        return dividedTasks;
    }

    /**
     * Splits matrix vertically and horizontally and passes start- and end- indexes to subtask.
     * */
    private Collection<MatrixRotatorRecursiveTask> createSubtaskAllAcrossSplit() {
        Collection<MatrixRotatorRecursiveTask> dividedTasks = new ArrayList<>();
        int dividerVertical = (k-i)/THRESHOLD;
        int dividerHorizontal = (l-j)/THRESHOLD;
        IntStream.range(0,dividerVertical)
                .forEach(dv-> IntStream.range(0, dividerHorizontal)
                        .forEach(dh-> dividedTasks.add(new MatrixRotatorRecursiveTask(
                                matrix,
                                newMatrix,
                                THRESHOLD*dv,
                                THRESHOLD*dh,
                                THRESHOLD*(dv+1),
                                THRESHOLD*(dh+1)))));
        return dividedTasks;
    }
}