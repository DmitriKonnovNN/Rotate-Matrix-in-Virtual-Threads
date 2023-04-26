package solutions.dmitrikonnov.demo;

import java.util.concurrent.ForkJoinTask;

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

}
