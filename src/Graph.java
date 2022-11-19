import java.util.ArrayList;
import java.util.Scanner;
class pair{
    Integer Key, Value;
    public pair(Integer dest, Integer weight) {
        this.Key = dest;
        this.Value = weight;
    }
    Integer Key(){
        return this.Key;
    }
    Integer Value(){
        return this.Value;
    }
}
class createGraph {
    ArrayList<ArrayList<pair>> graph = new ArrayList<>();
    int vertices;
    createGraph(Integer vertices) {
        for (int i = 0; i <= 5; i++) {
            this.graph.add(new ArrayList<pair>());
            this.vertices = vertices;
        }
    }
    void createEdges(Character source, Character dest, int weight) {
        Integer iSource = source - 'A';
        Integer iDest = dest - 'A';
        this.graph.get(iSource).add(new pair(iDest, weight));
    }
}
public class Graph {
    public static void main(String[] args) {
    	Scanner s=new Scanner(System.in);
    	String c,c1;
    	System.out.println("Enter the source and destination vertices: ");
    	c=s.next();
    	c1=s.next();
        calculateAverageDistanceBetweenTwoPoints(c, c1);
    }
    public static void calculateAverageDistanceBetweenTwoPoints(String X, String Y){
        Integer vertices = 5;
        createGraph g = new createGraph(vertices);
        g.createEdges('A', 'B', 12);
        g.createEdges('A', 'C', 13);
        g.createEdges('A', 'E', 8);
        g.createEdges('A', 'D', 11);
        g.createEdges('D', 'E', 7);
        g.createEdges('E', 'C', 4);
        g.createEdges('B', 'C', 3);
        Integer source = X.charAt(0)- 'A';
        Integer dest = Y.charAt(0) - 'A';
        ArrayList<ArrayList<pair>> totalPath = new ArrayList<ArrayList<pair>>();
        ArrayList<pair> path = new ArrayList<pair>();
        dfs(g, totalPath, path, source, dest, 0 );
        int pathCount = totalPath.size();
        int distance = 0;
        for(ArrayList<pair> it: totalPath){
            for(pair p : it) {
                distance += p.Value();
            }
        }
        double averageDistance = (double)distance /pathCount;
        System.out.println("The average distance from source to destination is "+averageDistance);
    }
    private static void dfs(createGraph g, ArrayList<ArrayList<pair>> totalPath, ArrayList<pair> path, Integer source, Integer dest, Integer weight) {
        path.add(new pair(source, weight));
        if(source.equals(dest)){
            totalPath.add(new ArrayList<pair>(path));
        }
        for(pair x : g.graph.get(source)){
            int newSource = x.Key;
            int newWeight = x.Value;
            dfs(g, totalPath, path, newSource, dest, newWeight);
        }
        path.remove(path.size() - 1);
    }
}