public class MatrixRotatorSequentialTask implements MatrixRotatorTask {

    private int [][] matrix;

    public MatrixRotatorSequentialTask(int[][] matrix) {
        if(matrix.length!=matrix[0].length) throw new RuntimeException("Rotating of matrix with unequal length and with is not implemented!");
        this.matrix = matrix;
    }
    @Override
    public void compute (){
        MatrixRotator.rotate(matrix);
    }
}
