package graphs.lecture18;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Queue;
import java.util.Set;

class GraphNode {

	private String label;
	private Set<GraphNode> neighbors;
	private Optional<String> color;

	public GraphNode(String label) {
		this.label = label;
		neighbors = new HashSet<GraphNode>();
		color = Optional.empty();
	}

	public String getLabel() {
		return label;
	}

	public Set<GraphNode> getNeighbors() {
		return Collections.unmodifiableSet(neighbors);
	}

	public void addNeighbor(GraphNode neighbor) {
		neighbors.add(neighbor);
	}

	public boolean hasColor() {
		return color.isPresent();
	}

	public String getColor() {
		return color.get();
	}

	public void setColor(String color) {
		this.color = Optional.ofNullable(color);
	}
}

public class GraphColoring {

	public static void main(String args[]) {

		GraphNode a = new GraphNode("a");
		GraphNode b = new GraphNode("b");
		GraphNode c = new GraphNode("c");

		a.addNeighbor(b);
		b.addNeighbor(a);
		b.addNeighbor(c);
		c.addNeighbor(b);

		GraphNode[] graph = new GraphNode[] { a, b, c };

	}
	static Set<String>colors  ;
	static int maxDegree ;

	public static void colorGraph (GraphNode[] vertices ) {
		//Let integers represent colors
		maxDegree = findMaxDegree(vertices);
		colors  = new HashSet<String> (maxDegree);
		for ( int i = 0; i <= maxDegree; i++ ) {
			colors.add(String.valueOf(i));
		}
		
		//Take one vertixas the begining vertix at random. lets take verix 0 
		int next = 0;
		Queue<GraphNode> queue = new LinkedList<GraphNode>();
		List<String> adjListColors = new ArrayList<String>();
		
		while (!queue.isEmpty()) {
			GraphNode vertix = queue.poll();
			for ( GraphNode each : vertix.getNeighbors()) {
				queue.add(each);
				adjListColors.add(each.getColor());
			}
			String validColor = getValidColor(vertix, adjListColors);
			vertix.setColor(validColor);
			
		}
		
	}

public static String getValidColor(GraphNode vertix, List<String> adjListColors) {
	if (adjListColors.size() == 0 ) {
		//return a random color 
		return colors.iterator().next();
	} else {
		Set<String > copyColors = new HashSet (colors);
		for( String eachAdjColor : adjListColors) {
			if(!copyColors.add(eachAdjColor)) {
				//Remove the color from the colors set which are used in adjList
				copyColors.remove(eachAdjColor);
			}
		}
		//The remaining colors in the copyColors are the unused ones in the Adj List
		//So just pick and return the first unused color 
		return copyColors.iterator().next();
		
	}
	
}

	public static int findMaxDegree(GraphNode[] vertices) {
		int max = Integer.MIN_VALUE;
		for (GraphNode vertex : vertices) {
			max = Math.max(vertex.getNeighbors().size(), max);
		}
		return max;
	}

}
