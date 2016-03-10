import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

/**
 * Created by edreichua on 3/1/16.
 */
public class Graph {

    private Map<Integer,Set<Integer>> data;

    public Graph(String filename){
        data = new HashMap<>();
        createGraph(filename);

    }

    /**
     * createGraph
     * function to create a graph based on the file mapping
     * @param filename
     */
    private void createGraph(String filename) {

        BufferedReader input = null;
        try {
            input = new BufferedReader(new FileReader(filename)); // Open file
            String pair;
            while ((pair = input.readLine()) != null) { // Read file and add int to map
                String[] parts = pair.split("\t");
                int first = Integer.parseInt(parts[0]);
                int second = Integer.parseInt(parts[1]);
                checkVertices(first,second);
                addEdge(first,second);

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close file if file exist. If not, catch the exception
            try {
                input.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * checkVertices
     * function to enforce vertices i.e. add vertex to the graph if it is
     * not already there
     * @param first
     * @param second
     */
    private void checkVertices(int first, int second){

        if(!data.containsKey(first))
            data.put(first,new HashSet<>());
        if(!data.containsKey(second))
            data.put(second,new HashSet<>());
    }

    /**
     * addEdge
     * function to add an edge between first and second
     * @param first
     * @param second
     */
    private void addEdge(int first, int second){
        data.get(first).add(second);
    }

    public Iterable<Integer> allVertices(){
        return data.keySet();
    }

    public boolean hasEdge(int first, int second){
        return data.get(first).contains(second);
    }

    public int numOutgoingEdges(int first){
        return data.get(first).size();
    }

    public int size(){
        return data.size();
    }

}
