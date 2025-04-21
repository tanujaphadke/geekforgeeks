package graphs.lecture17;

import trees.lecture8.TreeNode;

import java.util.*;

class Vertex {
	int value;
	int color;
	Vertex parent;
	// FOR BFS we have distance
	int d; // distance from source
	// Distance from source is infinity , color is white and parent is nill for
	// each vertix
	// for DFS we have start time and endTime
	int startTime;
	int endTime;

	public Vertex(int value, int color) {
		super();
		this.value = value;
		this.color = color;
		this.d = Integer.MAX_VALUE;
	}

	@Override
	public String toString() {
		return "Vertix [value=" + value + ", color=" + color + ", parent="
				+ parent + ", d=" + d + "]";
	}

}

class Graph {

	Map<Vertex, List<Vertex>> adjMap = new HashMap<Vertex, List<Vertex>>();
	Vertex vertices[];

	public Graph() {
	}

	List<Vertex> getAdjListFor(Vertex u) {
		return adjMap.get(u);
	}

	public void addEdge(Vertex u, Vertex v) {
		if (adjMap.get(u) != null) {
			adjMap.get(u).add(v);
		} else {
			List<Vertex> adjList = new LinkedList<Vertex>();
			adjList.add(v);
			adjMap.put(u, adjList);
		}
	}

	public void setVertices(Vertex vs[]) {
		this.vertices = vs;
	}

}

public class GraphAlgorithm {
	private static int WHITE = 0;
	private static int GREY = 1;
	private static int BLACK = 2;

	public static void main(String args[]) {
		Graph g = new Graph();
		//Create a graph with 3 vertices. The first argument represents its value 
		Vertex zero = new Vertex(0, WHITE);
		Vertex one = new Vertex(1, WHITE);
		Vertex two = new Vertex(2, WHITE);
		Vertex three = new Vertex(3, WHITE);
		g.addEdge(zero, one);
		g.addEdge(zero, two);
		g.addEdge(one, two);
		g.addEdge(two, zero);
		g.addEdge(two, three);
		g.addEdge(three, three);

		Vertex vertices[] = { zero, one, two, three };
		g.setVertices(vertices);
		
		Vertex source = two; // make its distacne from source = 0
		source.d = 0;
		//List<Vertix> output = BFS(g, source);
//		for (Vertix each : output) {
//			System.out.print(": " + each.value);
//		}
//		
		//DFS(g);
		
		System.out.println( isPath(	g, zero,three )) ;

	}

	// BFS : Returns a list that can be printed
	public static List<Vertex> BFS(Graph g, Vertex source) {

		List<Vertex> visitedVertex = new LinkedList<Vertex>(); // output
		Queue<Vertex> queue = new LinkedList<Vertex>(); // standard Q

		queue.add(source);
		while (!queue.isEmpty()) {
			Vertex u = queue.peek();
			for (Vertex each : g.getAdjListFor(u)) {
				if (each.color == WHITE) {
					each.color = GREY; // discovered;
					each.parent = u;
					each.d = u.d + 1;
					queue.add(each);
				}
			}
			Vertex visited = queue.poll(); // DEQUEUE
			visitedVertex.add(visited);
			visited.color = BLACK;
		}

		return visitedVertex;
	}

	public static void DFS(Graph g) {
		for (Vertex u : g.vertices) {
			if (u.color == WHITE) {
				DFSVisit(g,u, 0);
			}
		}
	}

	public static int DFSVisit(Graph g, Vertex u, int sTime) {
		u.color = GREY;
		u.startTime = u.startTime + sTime + 1;
		int endTime = 0;
		for (Vertex v : g.getAdjListFor(u)) {
			if (v.color == WHITE) {
				v.parent = u;
				endTime = DFSVisit(g, v, u.startTime) + 1;
			}else{
				endTime = u.startTime +1 ;
			}
		}// All the elements in the deoth are visited
		u.color = BLACK;
		System.out.println(u);
		return endTime;

	}
	
	
	
	
	public static boolean isPath(Graph g, Vertex v1, Vertex v2){
		if ( v1.color == WHITE){
			return DFSVisitModified(g, v1, 0, v2 );
		}
		return false;
	}
	//Question : Find if there is a Route between nodes. Given a graph find if there is route between two nodes. 
	public static boolean DFSVisitModified(Graph g, Vertex u, int sTime, Vertex v2) {
		u.color = GREY;
		u.startTime = u.startTime + sTime + 1;
		boolean found = false;
		for (Vertex adjU : g.getAdjListFor(u)){
			if (adjU.value == v2.value) return true;
			if (adjU.color == WHITE){
				found = DFSVisitModified(g, adjU,u.startTime , v2 );
			}
		}
		return found;
	}
	
	//Create a minumal tree
	public static void minimalTree(){
		
	}
	
	//Question: To find all the nodes at each leavel in the tree 
	//List of depth ()
	 public static void listofDepth (TreeNode n ){
		 
		 
	 }
	 
	 
	 //This code prints nodes at each level 
	 public static void BFSmodified( TreeNode source) {

			Queue<TreeNode> queue = new LinkedList<TreeNode>(); // standard Q

			queue.add(source); //root node
			int i = 0;
			List<TreeNode> prevList = new ArrayList<TreeNode>();
			prevList.add(source);
			Map<Integer, List<TreeNode>> levelNoofNodesMap  = new HashMap<Integer, List<TreeNode>>();
			levelNoofNodesMap.put(i, prevList);

			while(true){
				List<TreeNode> atNextLevel = getAdjListFor(prevList);
				if (atNextLevel.size() == 0 ) break;
				levelNoofNodesMap.put(i++, atNextLevel);
				prevList = atNextLevel;
			}
			
			//Print nodes at each level using the hashmap levelNoofNodesMap
			
	}
	 
	 public static List<TreeNode> getAdjListFor(List<TreeNode> prevList){
		 List<TreeNode> lst = new ArrayList<TreeNode>();
		for (TreeNode each : prevList){
			if (each.left!= null ) {
				lst.add(each.left);
			}
			if (each.right!= null ) {
				lst.add(each.right);
			}

		}
		return lst;
	 }
	 
	 public static List<TreeNode> getAdjListForTreeNode(List<TreeNode> prevList, TreeNode n ){
		List<TreeNode> lst = new ArrayList<TreeNode>();
		lst.add(n.left);
		lst.add(n.right);
		return lst;
	 }
	
}
