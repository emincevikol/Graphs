
/**
 * Devolopers : 
 * Muhammed Emin Çevikol No : 120315013 
 * Uğur Kafalı No : 120315021 
 * Hilmi Araz No : 130315042 
 */
 
import java.io.*;
import java.util.*;
import java.lang.reflect.Array;

public class WeightedGraph {
  public static final int DefaultSize = 10;
  private SLinkedList<Edge>[] edges;      // edges of the graph

  /** post: intialize graph of default size (number of vertices) */
  public WeightedGraph() {
    this(DefaultSize);
  }

  /** post: intialize graph of indicated size
            display error message if size is invalid */
  public WeightedGraph(int size) {
    if (size > 0) {
      edges = (SLinkedList<Edge>[])Array.newInstance(SLinkedList.class, size);
      for (int i = 0; i < size; i++) {
        edges[i] = new SLinkedList<Edge>();
      }
    } else {
      System.out.println("Error: invalid number of vertices.");
    }
  }
  
  public void addEdgeLoad(int v1, int v2, int weight) {
    if (v1 == v2) {
      System.out.println("Error: self loops not allowed.");
    } else if (v1 < 0 || v2 < 0 || v1 >= edges.length || v2 >= edges.length) {
      System.out.println("Error: invalid vertex.");
    } else if (hasEdge(v1, v2)) {
	
    } else {
      edges[v1].addLast(new Edge(v1, v2, weight));
      edges[v2].addLast(new Edge(v2, v1, weight));
    }
  }

  /** post: add edge from vertex #v1 to #v2 with the specified weight
            if both vertex numbers are valid */
  public void addEdge(int v1, int v2, int weight) {
    if (v1 == v2) {
      System.out.println("Error: self loops not allowed.");
    } else if (v1 < 0 || v2 < 0 || v1 >= edges.length || v2 >= edges.length) {
      System.out.println("Error: invalid vertex.");
    } else if (hasEdge(v1, v2)) {
      System.out.println("Error: edge already exists.");
    } else {
      edges[v1].addLast(new Edge(v1, v2, weight));
      edges[v2].addLast(new Edge(v2, v1, weight));
    }
  }

  /**  @return true iff v1 and v2 are valid and adjacent  */
  public boolean hasEdge(int v1, int v2) {
    return getWeight(v1, v2) != Integer.MAX_VALUE;
  }

  /** @return the weight of the edge from v1 to v2, or infinity if
              no such edge exists */
  public int getWeight(int v1, int v2) {
    if (v1 < 0 || v2 < 0 || v1 >= edges.length || v2 >= edges.length) {
      return Integer.MAX_VALUE;
    } else {
      Iterator<Edge> it = getIncidentEdges(v1);
      while (it.hasNext()) {
        Edge e = it.next();
        if (e.getVertex2() == v2) {
          return e.getWeight();
        }
      }
      return Integer.MAX_VALUE;
    }
  }
 
  /** @return an iterator over all edges incident on vertex # vNum */
  public Iterator<Edge> getIncidentEdges(int vNum) {
    if (vNum < 0 || vNum >= edges.length) return null;
    else return edges[vNum].iterator();
  }
  
   /** @return true iff the sequence of vertices is a path in the graph */
  public boolean isAPath(int [] verts) {
    for (int i = 0; i < verts.length - 1; i++) {
      if (!hasEdge(verts[i], verts[i + 1])) {
        return false;
      }
    }
    return true;
  }

  /** @return the total weight of all edges in the path represented by
                  the array verts
              infinity if verts does not represent a path */
  public int totalWeight(int [] verts) {
    int sum = 0;
    for (int i = 0; i < verts.length - 1; i++) {
      sum += getWeight(verts[i], verts[i + 1]);
    }
    return sum;
  }

  /** @return all information about the graph (edges and weights) in
              a human readable form */   
  public String toString() {
    String res = "Edges:\n";
    Iterator<Edge> iter;
    Edge e;
    for (int i = 0; i < edges.length; i++) {
      iter = getIncidentEdges(i);
      while (iter.hasNext()) { 
        e = iter.next();
        res += e;
      }
      res += "\n";
    }
    return res;
  }
  
  public boolean searchElement(int input) {
	Iterator<Edge> iter;
    Edge e;
    for (int i = 0; i < edges.length; i++) {
      iter = getIncidentEdges(i);
      while (iter.hasNext()) { 
        e = iter.next();
        if(e.getVertex1() == input || e.getVertex2() == input)
			return true;
      }
    }
	return false;
  }

  /** post: read the representation of a weighted graph from a file */
    public static WeightedGraph readGraph() throws IOException {
    BufferedReader in = new BufferedReader(new FileReader("GraphList.txt"));
    int size = Integer.parseInt(in.readLine());
    WeightedGraph wg = new WeightedGraph(size);
    if (size > 0) {
      int vNum;
      int vNum2;
      int weight;
      String sval = in.readLine();
      while (sval != null) {
        vNum = Integer.parseInt(sval);
        vNum2 = Integer.parseInt(in.readLine());
        weight = Integer.parseInt(in.readLine());
        wg.addEdge(vNum, vNum2, weight);
        sval = in.readLine();
      }
    }
    in.close();
    return wg;
  }
  public int sizeArr() {
	return edges.length;
  }
  
  public void saveGraph() throws IOException {
  
	FileWriter fw = new FileWriter("GraphList.txt", false);
	BufferedWriter bw = new BufferedWriter(fw);
	
	Iterator<Edge> iter;
    Edge e;
	bw.write(edges.length);
    for (int i = 0; i < edges.length; i++) {
      iter = getIncidentEdges(i);
      while (iter.hasNext()) { 
		e = iter.next();
        bw.write(e.getVertex1());
		bw.newLine();
		bw.write(e.getVertex2());
		bw.newLine();
		bw.write(e.getWeight());
		bw.newLine();
      }
    }
  }
}

