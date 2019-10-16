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

	// vertValue = n, the final vert
	//
	int findXOR(Graph g, int finalVerticeValue, Edge edge, int currEdge, int min) {
		// adjacent edges = same vertice source
		// backtracking creates 0s
		// how do i ignore backtracking?

		// problem is getting out of bounds on currEdge indexing of arrays
		// of all edges, check current edge source if equal to final
		/*
		 * if (currEdge == g.E) { System.out.println("uhoh"); return 0;}// returns same
		 * value, 0^anything =anyti else if (g.edge[currEdge].src == finalVerticeValue)
		 * {
		 * 
		 * return g.edge[currEdge].weight; }
		 */

		if (edge.src == 1) {
			System.out.println("\n");
		}

		if (min == 0) {
			// System.out.print("boo");
		}
		if (g.edge[currEdge].src == 4) {
			System.out.println("hi");
		}
		g.edge[currEdge].traversed = true;
		// CHECK DEST
		if (g.edge[currEdge].dest == finalVerticeValue) {
			System.out.print(g.edge[currEdge].src + "-(" + g.edge[currEdge].weight + ")->" + g.edge[currEdge].dest);
			return g.edge[currEdge].weight;
		}

		// for loop through all adjacent edges of current edge (share source or
		// destination
		// initially edge[0] passed in for edge, as that is the first edge
		int forLoopSize = findAdj(g, edge, currEdge).size();
		for (int i = 0; i < findAdj(g, edge, currEdge).size(); i++) {
			System.out.print(g.edge[currEdge].src + "-(" + g.edge[currEdge].weight + ")->");
			
			// i is less than size of all adjacent list
			
			 
			// if edge dest is final vert, return this path
			int XORresult = (g.edge[currEdge].weight
					^ findXOR(g, finalVerticeValue, findAdj(g, edge, currEdge).get(i), 1+currEdge, min));
			//if(g.edge[currEdge].dest==finalVerticeValue) { i--; }
			// System.out.println( XORresult);
			if (min > XORresult) {
				min = XORresult;
			}
		}
		// System.out.println("--");
		/*
		 * if(min == 32) {
		 * 
		 * System.out.println(min); }
		 */
		return min;
	}

	// make method to get adjacent edges
	ArrayList<Edge> findAdj(Graph g, Edge e, int currentEdge) {
		// considered shared if the yshare a vertex
		ArrayList<Edge> adjacent = new ArrayList<Edge>();
		if (currentEdge < g.E) {
			for (int i = 0; i < g.E; i++) {
				if ((!g.edge[i].traversed)
						&& ((g.edge[currentEdge].src == g.edge[i].src && g.edge[currentEdge].dest != g.edge[i].dest)
								|| g.edge[currentEdge].src == g.edge[i].dest
								|| g.edge[currentEdge].dest == g.edge[i].src)) {
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
