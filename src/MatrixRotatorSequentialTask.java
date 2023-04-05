public class MatrixRotatorSequentialTask implements MatrixRotatorTask {

    private int [][] matrix;

    public MatrixRotatorSequentialTask(int[][] matrix) {
        this.matrix = matrix;
    }
    @Override
    public void compute (){
        MatrixRotator.rotate(matrix);
    }
}
