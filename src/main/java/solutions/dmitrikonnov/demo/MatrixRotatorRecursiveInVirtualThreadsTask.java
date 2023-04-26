package solutions.dmitrikonnov.demo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
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
        try(ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor()) {
            if ((k-i)%THRESHOLD==0 && matrix.length==THRESHOLD && (l-j)%THRESHOLD==0 && matrix[0].length==THRESHOLD && k!=THRESHOLD && l!=THRESHOLD ){
                executor.submit(()->{
                    System.out.println("All across split");
                    createSubtaskAllAcrossSplit().forEach(MatrixRotatorRecursiveAbstractTask::compute);

                });
            }
            else if ((k-i)%THRESHOLD==0 && k!=THRESHOLD && matrix.length==THRESHOLD)
            {
                executor.submit(()->{
                    System.out.println("Vertical split");
                    createSubtaskVerticalSplit().forEach(MatrixRotatorRecursiveAbstractTask::compute);
                });
            }
            else if ((l-j)%THRESHOLD==0 && l!=THRESHOLD && matrix[0].length==THRESHOLD)
            {
                executor.submit(()->{
                    System.out.println("Horizontal split");
                    createSubtaskHorizontalSplit().forEach(MatrixRotatorRecursiveAbstractTask::compute);
                });
            }

            else {
                System.out.println("Sequential");
                MatrixRotator.rotate(matrix, newMatrix, i, j, matrix.length, matrix[0].length);}
        }
    }

}
