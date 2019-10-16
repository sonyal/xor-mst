import java.util.*;
import java.lang.*;
import java.io.*;

class Graph {
	// A class to represent a graph edge
	class Edge implements Comparable<Edge> {
		int src, dest, weight;
		boolean traversed;

		// Comparator function used for sorting edges
		// based on their weight
		public int compareTo(Edge compareEdge) {
			return this.weight - compareEdge.weight;
		}
	};

	// A class to represent a subset for union-find
	class subset {
		int parent, rank;
	};

	int V, E; // V-> no. of vertices & E->no.of edges
	Edge edge[]; // collection of all edges

	// Creates a graph with V vertices and E edges
	Graph(int v, int e) {
		V = v;
		E = e;
		edge = new Edge[E];
		for (int i = 0; i < e; ++i)
			edge[i] = new Edge();
	}

	
	/*
	 * Wrote XOR, pathwipe, findAdj, and findFirstEdge
	 * findAdj ignores previously visited edges
	 * XOR still hasnt passed test3, require better printing for testing
	 */
	
	int findXOR(Graph g, int finalVerticeValue, Edge edge, int pathLengthMinusOne, int min) {
		/*
		 * Testing would be much faster if  I had a reliable print for the path
		 * being taken, current one is unclear.
		 */
		
		// adjacent edges = same vertice source
		// backtracking creates 0s
		// how do i ignore backtracking? SOLVED use boolean traversed
		// problem is getting out of bounds on currEdge indexing of arrays - SOLVED using a list resizes it
		if (edge.src == 1 ) {
			System.out.println("\n");
			pathWipe(g);
		}
		edge.traversed = true;
		// CHECK DEST
		if (edge.dest == finalVerticeValue) {
			System.out.print(edge.src + "-(" + edge.weight + ")->" + edge.dest);
			return edge.weight;
		}

		// for loop through all adjacent edges of current edge (share source or
		// destination
		// initially edge[0] passed in for edge, as that is the first edge
		for(Edge e: findAdj(g, edge, pathLengthMinusOne)) {//an enhanced for loops lets size adapt, helps outta bounds problems
		
			System.out.print(" " + edge.src + "-(" + edge.weight + ")->");
			
			 
			// if edge dest is final vert, return this path
			//currently explores adjacent, but ignores weight of final edge
			int XORresult = (edge.weight
					^ findXOR(g, finalVerticeValue, e, 1+pathLengthMinusOne, min));
			
			
			
			if (min > XORresult) {
				min = XORresult;
			}
		}
		return min;
	}
	//reset all visited paths to unvisted, in case freakier paths reuse routes
	void pathWipe(Graph g) {
		for(Edge e: g.edge) {
			e.traversed = false;
		}
	}
	
	// make method to get adjacent edges
	ArrayList<Edge> findAdj(Graph g, Edge e, int pathLengthMinusOneParam) {
		// considered shared if the yshare a vertex
		ArrayList<Edge> adjacent = new ArrayList<Edge>();
		if (pathLengthMinusOneParam < g.E) {
			for (int i = 0; i < g.E; i++) {
				if ((!g.edge[i].traversed)
						&& ((e.src == g.edge[i].src && e.dest != e.dest)
								|| e.src == g.edge[i].dest
								|| e.dest == g.edge[i].src)) {
					adjacent.add(g.edge[i]);
				}
			}
		}
		return adjacent;
	}

	// Gets an edge with its source being vertice 1, the rest can be accessed with
	// find Adj
	Edge getFirstEdge() {
		Edge firstEdge = null;
		for (int i = 0; i < edge.length; i++) {
			if (edge[i].src == 1) {
				return edge[i];
			}
		}
		return firstEdge;
	}

	
	
	
	
	
	
	
	
	// A utility function to find set of an element i
	// (uses path compression technique)
	int find(subset subsets[], int i) {
		// find root and make root as parent of i (path compression)
		if (subsets[i].parent != i)
			subsets[i].parent = find(subsets, subsets[i].parent);

		return subsets[i].parent;
	}

	// A function that does union of two sets of x and y
	// (uses union by rank)
	void Union(subset subsets[], int x, int y) {
		int xroot = find(subsets, x);
		int yroot = find(subsets, y);

		// Attach smaller rank tree under root of high rank tree
		// (Union by Rank)
		if (subsets[xroot].rank < subsets[yroot].rank)
			subsets[xroot].parent = yroot;
		else if (subsets[xroot].rank > subsets[yroot].rank)
			subsets[yroot].parent = xroot;

		// If ranks are same, then make one as root and increment
		// its rank by one
		else {
			subsets[yroot].parent = xroot;
			subsets[xroot].rank++;
		}
	}
}
