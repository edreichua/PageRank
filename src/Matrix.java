import java.text.DecimalFormat;

/**
 * Created by edreichua on 2/25/16.
 */
public class Matrix {

    public double[][] data;
    private int numRows, numColumns;

    /**
     * Constructor for Matrix, default all elements to zero
     * @param numRows
     * @param numColumns
     */
    public Matrix(int numRows, int numColumns){

        this.numRows = numRows;
        this.numColumns = numColumns;
        data = new double[numRows][numColumns];
    }

    /**
     * transpose: Find the transposition matrix
     * @return transposition matrix
     */
    public Matrix transpose() {

        Matrix result = new Matrix(numColumns, numRows);
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numColumns; j++) {
                result.data[j][i] = this.data[i][j];
            }
        }
        return result;
    }

    /**
     * multiplyVector: multiply the matrix A by a vector v (i.e. A*v)
     * @param vect
     * @return a vector of the product
     */
    public double[] multiplyVector(double[] vect){

        if(vect.length != numColumns)
            return null;
        double[] result = new double[numRows];
        for(int i = 0; i < numRows; i++){
            double currSum = 0;
            for(int j = 0; j < numColumns; j++){
                currSum += data[i][j]*vect[j];
            }
            result[i] = currSum;
        }
        return result;
    }

    /**
     * multiplyConstant
     * function to multiply a matrix by a constant k
     * @param k
     */
    public void multiplyConstant(double k){

        for(int r = 0; r < numRows; r++){
            for(int c = 0; c < numColumns; c++){
                data[r][c] = k*data[r][c];
            }
        }
    }

    /**
     * addMatrix
     * function to add 2 matrices together
     * @param m
     */
    public void addMatrix(Matrix m){

        for(int r = 0; r < numRows; r++){
            for(int c = 0; c < numColumns; c++){
                data[r][c] += m.data[r][c];
            }
        }
    }

    /**
     * powerMethod
     * function to calculate the eigenvalue using the power method
     * Algorithm 2 of Kamvar et al
     *
     * @param vect
     * @param epsilon
     * @param max_k
     * @return
     */
    public double[] powerMethod(double[] vect, double epsilon, int max_k){

        int k = 1;
        double delta = Double.MAX_VALUE;
        double[] prevVect = vect;

        while(delta > epsilon && k < max_k){
            double[] newVect = multiplyVector(prevVect);
            delta = distance(newVect, prevVect);
            prevVect = newVect;
            k++;

        }
        return prevVect;
    }

    /**
     * distance
     * distance heuristic for power method
     * @param v1
     * @param v2
     * @return the euclidean distance between two vectors
     */
    private double distance(double[] v1, double[] v2){

        double sum = 0;
        for(int i = 0; i < v1.length; i++){
            sum += Math.pow(v1[i] - v2[i],2);
        }
        return Math.sqrt(sum);
    }

    public String toString() {

        DecimalFormat fourdp = new DecimalFormat("#0.0000");

        String s = "";
        for (int r = 0; r < numRows; r++) {
            for (int c = 0; c < numColumns; c++) {
                s += (fourdp.format(data[r][c])+" ");
            }
            s += "\n";
        }
        return s;
    }
}
