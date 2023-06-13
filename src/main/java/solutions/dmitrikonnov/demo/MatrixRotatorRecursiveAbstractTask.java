package solutions.dmitrikonnov.demo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

public abstract class MatrixRotatorRecursiveAbstractTask extends RecursiveAction {

    static final int THRESHOLD = 50;

    final static AtomicInteger counterRotation = new AtomicInteger(0);
    final static AtomicInteger allAcrossSplit = new AtomicInteger(0);

    int[][] matrix;
    int[][] newMatrix;
    int i,j,k,l;


    abstract HexaInitializer<? extends MatrixRotatorRecursiveAbstractTask> getInitializer();

    MatrixRotatorRecursiveAbstractTask(int[][] matrix) {
        if(matrix.length!=matrix[0].length) throw new RuntimeException("Rotating of matrix with unequal length and width is not implemented!");
        this.matrix = matrix;
        this.newMatrix = new int[matrix.length][matrix[0].length];
        this.i = 0;
        this.j = 0;
        this.k = matrix.length;
        this.l = matrix[0].length;
    }

    MatrixRotatorRecursiveAbstractTask(int[][] matrix, int [][] newMatrix, int i, int j, int k, int l) {
        this.matrix = matrix;
        this.newMatrix = newMatrix;
        this.i = i;
        this.j = j;
        this.k = k;
        this.l = l;
    }


    @Override
    abstract protected void compute();

    Collection<MatrixRotatorRecursiveAbstractTask> createSubtaskVerticalSplit(){
        Collection<MatrixRotatorRecursiveAbstractTask > dividedTasks = new ArrayList<>();
        int divider = (k-i)/THRESHOLD;
        IntStream.range(0,divider)
                .forEach(d-> dividedTasks.
                add(
                        (getInitializer()
                                .initialize(
                                        matrix,
                                        newMatrix,
                                        THRESHOLD*d,
                                        j,
                                        THRESHOLD*(d+1),
                                        l))));
        return dividedTasks;
    }

    Collection<MatrixRotatorRecursiveAbstractTask> createSubtaskHorizontalSplit(){
        List<MatrixRotatorRecursiveAbstractTask> dividedTasks = new ArrayList<>();
        int divider = (l-j)/THRESHOLD;

        IntStream.range(0,divider)
                .forEach(d-> dividedTasks.add( getInitializer().initialize(
                matrix,
                newMatrix,
                i,
                j*THRESHOLD,
                k,
                THRESHOLD*(l+1)
        )));
        return dividedTasks;
    }

    /**
     * Splits matrix vertically and horizontally and passes start- and end- indexes to subtask.
     * */
    Collection<MatrixRotatorRecursiveAbstractTask> createSubtaskAllAcrossSplit(){
        Collection<MatrixRotatorRecursiveAbstractTask> dividedTasks = new ArrayList<>();
        int dividerVertical = (k-i)/THRESHOLD;
        int dividerHorizontal = (l-j)/THRESHOLD;

        IntStream.range(0,dividerVertical)
                .forEach(dv-> IntStream.range(0, dividerHorizontal)
                        .forEach(dh-> dividedTasks.add(getInitializer().initialize(
                                matrix,
                                newMatrix,
                                THRESHOLD*dv,
                                THRESHOLD*dh,
                                THRESHOLD*(dv+1),
                                THRESHOLD*(dh+1)))));
        return dividedTasks;
    }
}
