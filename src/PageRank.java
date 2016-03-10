/**
 * Created by edreichua on 3/1/16.
 */
import java.text.DecimalFormat;
import java.util.*;

public class PageRank {

    // constants for power method
    private static final double EPSILON = 0.0001;
    private static final int MAXTRIES = 1000;

    // damping factor for random surf jump
    private static final double DAMPING = 0.15;

    // transition matrix
    private Matrix transition;

    // vector
    private double[] vect;

    // map an index in the transition matrix to the value in the graph
    private Map<Integer,Integer> index2value;

    // size of matrix
    private int size;

    // debugging flag
    private static final boolean isDebug = false;


    /**
     * constructor for PageRank
     * @param filename
     */
    public PageRank(String filename, boolean isDangling, boolean isJump){

        constructTransition(new Graph(filename));
        initStartVector();

        if(isDangling)
            enforceDanglingNodes();
        if(isJump)
            randomJump(DAMPING);

        vect = transition.powerMethod(vect,EPSILON,MAXTRIES);
    }

    /**
     * initStartVector
     * function to initialize the start vector by having each page ranked with
     * probability = 1/size
     */
    private void initStartVector(){

        vect = new double[size];
        Arrays.fill(vect,1.0/(double)size);
    }

    /**
     * constructTransition
     * function to construct transition matrix based on graph
     * @param g
     */
    private void constructTransition(Graph g){

        size = g.size();
        transition = new Matrix(size,size);
        index2value = new HashMap<>();

        int index = 0;
        for(int value : g.allVertices()){
            index2value.put(index++,value);
        }

        for(int c = 0; c < size; c++){
            for(int r = 0; r < size; r++){
                int first = index2value.get(c);
                int second = index2value.get(r);
                double p = g.numOutgoingEdges(first);

                if(g.hasEdge(first,second)){
                    transition.data[r][c] = 1.0/p;
                }
            }
        }
    }

    /**
     * randomJump
     * Implementing random jump with a damping factor, using:
     * M = (1-p).A + p.B, p is the damping factor, A is the original
     * transition matrix and B is a matrix with all entries = 1/n
     *
     * @param damping
     */
    private void randomJump(double damping){

        Matrix B = new Matrix(size,size);

        for(int r = 0; r < size; r++)
            Arrays.fill(B.data[r],1);

        B.multiplyConstant(damping/(double)size);
        transition.multiplyConstant(1-damping);
        transition.addMatrix(B);

    }

    /**
     * enforceDanglingNodes
     * function to account for dangling nodes (nodes without any outgoing edges)
     */
    private void enforceDanglingNodes(){

        for(int c = 0; c < size; c++){
            if(!hasOutgoingEdge(c)){
                fillColumn(c, 1.0/(double)size);
            }
        }
    }

    /**
     * hasOutgoingEdge
     * helper function to check if a node has any outgoing edge
     * @param c
     * @return
     */
    private boolean hasOutgoingEdge(int c){

        for(int r = 0; r < size; r++){
            if(transition.data[r][c] != 0 )
                return true;
        }
        return false;
    }

    /**
     * fillColumn
     * helper function to fill column col of transition matrix with constant p
     * @param col
     * @param p
     */
    private void fillColumn(int col, double p){

        for(int r = 0; r < size; r++){
            transition.data[r][col] = p;
        }
    }

    /**
     * printSortedResults
     * function to print results sorted in decreasing order of rank
     * (decreasing order of importance)
     */
    public void printSortedResults(){

        DecimalFormat fourdp = new DecimalFormat("#0.0000");
        double[][] results = new double[size][2];

        for(int i = 0; i < size; i++){
            results[i][0] = index2value.get(i);
            results[i][1] = vect[i];
        }
        Arrays.sort(results, Comparator.comparing((double[] arr) -> arr[1]).reversed());

        System.out.println("Results sorted in decreasing order of rank or importance");
        for(int i = 0; i < size; i++){
            System.out.println((int)results[i][0]+" = "+fourdp.format(results[i][1]*100)+"%");
        }

    }

    /**
     * printResults
     * function to print all the relevant results
     */
    public void printResults(){

        DecimalFormat fourdp = new DecimalFormat("#0.0000");
        double[][] results = new double[size][2];

        for(int i = 0; i < size; i++){
            results[i][0] = index2value.get(i);
            results[i][1] = vect[i];
        }
        Arrays.sort(results, Comparator.comparing((double[] arr) -> arr[0]));

        for(int i = 0; i < size; i++){
            System.out.println((int)results[i][0]+"="+fourdp.format(results[i][1]*100)+"%");
        }


        if(isDebug)
            System.out.println(transition.toString());
    }

}
