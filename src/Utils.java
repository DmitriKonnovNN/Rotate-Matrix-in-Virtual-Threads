public class Utils {

    public static void createRandom2DMatrix(){
        int length = (int) (Math.random() * 100);
        int width = (int) (Math.random() * 100);
        createMatrix(length, width);
    }

    public static void createRandom2DMatrix(int length, int width){
        createMatrix(length, width);
    }

    public static void createRandom2DMatrix(int size){
        createMatrix(size, size);
    }

    public static void createRandomMatrices(int numberOfMatrices){
        for (int i = 0; i < numberOfMatrices; i++) {
            createRandom2DMatrix();
        }
    }

    public static void createRandomMatrices(int numberOfMatrices, int length, int width){
        for (int i = 0; i < numberOfMatrices; i++) {
            createRandom2DMatrix(length, width);
        }
    }

    public static void createRandomMatrices(int numberOfMatrices, int size){
        for (int i = 0; i < numberOfMatrices; i++) {
            createRandom2DMatrix(size);
        }
    }
    private static void createMatrix(int length, int width){
        int[][] matrix = new int[length][width];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                matrix[i][j] = (int) (Math.random() * 100);
            }
        }

    }
}
