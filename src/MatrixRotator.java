import java.util.Arrays;

public class MatrixRotator implements RotateMatrix {
    public static final byte ARRAY_CONCAT_HORIZ = 0, ARRAY_CONCAT_VERT = 1;


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


    public static Object[][] arrayConcat(Object[][] a, Object[][] b) {

        Object[][] result = new Object[a.length+b.length][];
        System.arraycopy(a, 0, result, 0, a.length);
        System.arraycopy(b, 0, result, a.length, b.length);
        return result;
    }

    @Override
    public int[][] rotate90InOneThread(int[][] matrix) {
        System.out.println("Run on sequential manner. \nWait…");
        var startTime =System.currentTimeMillis();


        var endTime = System.currentTimeMillis();
        System.out.println("Time elapsed: " + (endTime - startTime) + " ms");
        return new int[0][];
    }

    @Override
    public int[][] rotate90CompletableFuture(int[][] matrix) {
        return new int[0][];
    }

    @Override
    public int[][] rotate90CompletableFutureWithExecutor(int[][] matrix) {
        return new int[0][];
    }

    @Override
    public int[][] rotate90ParallelStream(int[][] matrix) {
        return new int[0][];
    }


}
