
/**
 * Devolopers : 
 * Muhammed Emin Çevikol No : 120315013 
 * Uğur Kafalı No : 120315021 
 * Hilmi Araz No : 130315042 
 */
 
import java.util.*;
import java.io.*;
class test {

public static final int INFINITY = 999;
	
	public static void main(String[] args) throws IOException {
		
		int choice = 0;
		
		File file = new File("GraphList.txt");
		BufferedReader in = new BufferedReader(new FileReader(file));
		int size = Integer.parseInt(in.readLine());
		WeightedGraph list = new WeightedGraph(size);
		if (size > 0) {
		  int vNum;
		  int vNum2;
		  int weight;
		  String sval = in.readLine();
		  while (sval != null) {
			vNum = Integer.parseInt(sval);
			vNum2 = Integer.parseInt(in.readLine());
			weight = Integer.parseInt(in.readLine());
			list.addEdgeLoad(vNum, vNum2, weight);
			sval = in.readLine();
		  }
		}
		in.close();
		
		Scanner scan = new Scanner(System.in);
		
		while(choice != 6) {
		
		System.out.println("1.Add input");
		System.out.println("2.Print the graph");
		System.out.println("3.Shortest path");
		System.out.println("4.Minimum Spanning Tree");
		System.out.println("5.Search");
		System.out.println("6.Exit");
		System.out.print("Select 1-6 choices : ");
		choice = scan.nextInt();
		
			switch(choice) {
				case 1:
					System.out.print("Please enter input as many you like to the graph like 1(2,3) and enter -1 for exit : ");
					String input = scan.next();
					while(!input.equals("-1")) {
						int ch1 = input.charAt(0);
						int ch2 = input.charAt(2);
						int ch3 = input.charAt(4);
						ch1 = ch1 - '0';
						ch2 = ch2 - '0';
						ch3 = ch3 - '0';
						list.addEdge(ch1, ch2, ch3);
						input = scan.next();
					}
				break;
				case 2:
					System.out.println(list.toString());
				break;
				case 3:
					int adjacency_matrix[][];
					int numberofvertices = list.sizeArr();
					adjacency_matrix = new int[numberofvertices + 1][numberofvertices + 1];
					for (int source = 1; source <= numberofvertices; source++) {
						for (int destination = 1; destination <= numberofvertices; destination++) {
							adjacency_matrix[source][destination] = 0;
						}
					}
					Iterator<Edge> iter;
					Edge e;
					for (int i = 0; i < list.sizeArr(); i++) {
					  iter = list.getIncidentEdges(i);
					  while (iter.hasNext()) { 
						e = iter.next();
						adjacency_matrix[e.getVertex1()][e.getVertex2()] = e.getWeight();
					  }
					}
					for (int source = 1; source <= numberofvertices; source++) {
						for (int destination = 1; destination <= numberofvertices; destination++) {
							if (source == destination) {
								adjacency_matrix[source][destination] = 0;
								continue;
							}
							if (adjacency_matrix[source][destination] == 0) {
								adjacency_matrix[source][destination] = INFINITY;
							}
						}
					}
					System.out.println("The Transitive Closure of the Graph");
					shortestPath shortestPath= new shortestPath(numberofvertices);
					shortestPath.shortestPath(adjacency_matrix);
				break;
				case 4:
				numberofvertices = list.sizeArr();
				adjacency_matrix = new int[numberofvertices + 1][numberofvertices + 1];
				for (int source = 1; source <= numberofvertices; source++) {
						for (int destination = 1; destination <= numberofvertices; destination++) {
							adjacency_matrix[source][destination] = 0;
						}
					}
					for (int i = 0; i < list.sizeArr(); i++) {
					  iter = list.getIncidentEdges(i);
					  while (iter.hasNext()) { 
						e = iter.next();
						adjacency_matrix[e.getVertex1()][e.getVertex2()] = e.getWeight();
					  }
					}
					for (int source = 1; source <= numberofvertices; source++) {
						for (int destination = 1; destination <= numberofvertices; destination++) {
							if (source == destination) {
								adjacency_matrix[source][destination] = 0;
								continue;
							}
							if (adjacency_matrix[source][destination] == 0) {
								adjacency_matrix[source][destination] = INFINITY;
							}
						}
					}
				
				Prims prims = new Prims(numberofvertices);
				prims.primsAlgorithm(adjacency_matrix);
				prims.printMST();
				break;
				case 5:
					System.out.println("Enter an input : ");
					int searchString = scan.nextInt();
					boolean boo = list.searchElement(searchString);
					if(boo)
						System.out.println("Your input has been found!!!");
					else
						System.out.println("Your input has not been found!!!");
				break;
				case 6:
				FileWriter fw = new FileWriter(file, false);
				BufferedWriter bw = new BufferedWriter(fw);
				
				String SS = "" + list.sizeArr();
				bw.write(SS);
				bw.newLine();
				for (int i = 0; i < list.sizeArr(); i++) {
				  iter = list.getIncidentEdges(i);
				  while (iter.hasNext()) { 
					e = iter.next();
					String S= ""+e.getVertex1();
					bw.write(S);
					bw.newLine();
					S= ""+e.getVertex2();
					bw.write(S);
					bw.newLine();
					S= ""+e.getWeight();
					bw.write(S);
					bw.newLine();
				  }
				}
				bw.close();
				
				choice = 6;
				break;
				default:
				System.out.println("Please enter 1-6 numbers!!!");
				break;
			}
			
		}
		
	}
}