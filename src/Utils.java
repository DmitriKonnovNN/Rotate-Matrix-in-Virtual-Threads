public class Utils {

    public static void generateRandom2DMatrix(){
        int length = (int) (Math.random() * 100);
        int width = (int) (Math.random() * 100);
        generateMatrix(length, width);
    }

    public static void generateRandom2DMatrix(int length, int width){
        generateMatrix(length, width);
    }

    public static void generateRandom2DMatrix(int size){
        generateMatrix(size, size);
    }

    public static void generateRandomMatrices(int numberOfMatrices){
        for (int i = 0; i < numberOfMatrices; i++) {
            generateRandom2DMatrix();
        }
    }

    public static void generateRandomMatrices(int numberOfMatrices, int length, int width){
        for (int i = 0; i < numberOfMatrices; i++) {
            generateRandom2DMatrix(length, width);
        }
    }

    public static void generateRandomMatrices(int numberOfMatrices, int size){
        for (int i = 0; i < numberOfMatrices; i++) {
            generateRandom2DMatrix(size);
        }
    }
    private static void generateMatrix(int length, int width){
        int[][] matrix = new int[length][width];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                matrix[i][j] = (int) (Math.random() * 100);
            }
        }
    }
}
