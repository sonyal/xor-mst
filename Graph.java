import java.util.*; 
import java.lang.*; 
import java.io.*; 
  
class Graph 
{ 
    // A class to represent a graph edge 
    class Edge implements Comparable<Edge> 
    { 
        int src, dest, weight; 
  
        // Comparator function used for sorting edges  
        // based on their weight 
        public int compareTo(Edge compareEdge) 
        { 
            return this.weight-compareEdge.weight; 
        } 
    }; 
  
    // A class to represent a subset for union-find 
    class subset 
    { 
        int parent, rank; 
    }; 
  
    int V, E;    // V-> no. of vertices & E->no.of edges 
    Edge edge[]; // collection of all edges 
  
    // Creates a graph with V vertices and E edges 
    Graph(int v, int e) 
    { 
        V = v; 
        E = e; 
        edge = new Edge[E]; 
        for (int i=0; i<e; ++i) 
            edge[i] = new Edge(); 
    } 
    //vertValue = n, the final vert
    //
    int findXOR(Graph g, int vertValue, Edge edge, int edgeChoice) {
    	//HOW check if current vert i what it is?
    	/*
    	 * src
    	 *
    	 * weight
    	 */
    	//if value equals destination value
    	//source and destination must equal?
    	//only test if edge source nad dest are same??
    	//CHECK IF DESTINATION IS THE SAME AS PREVIES OU SOURCE
    	if(g.edge[edgeChoice].src == vertValue || (g.edge[edgeChoice].dest!= g.edge[edgeChoice -1].src)) {
    		return g.edge[edgeChoice].weight;
    		
    	}
    	//edge is an array, each index is each adjacent edge
    	//     check weight of current edge path   XOR
    	//i want to return edge with current src, beginning at 0
    	//how do i enforce going from connecting src destinations, and not random edges??
    	//how do you ignore origins
    	
    	return g.edge[edgeChoice].weight ^ findXOR(g, vertValue, g.edge[++edgeChoice], edgeChoice);
    }
    
    //make method to get adjacent edges
    ArrayList<Edge> findAdj(Graph g, Edge e){
    	//considered shared if the yshare a vertex
    	ArrayList<Edge> adjacent = new ArrayList<Edge>();
    	int j = 0;
    	for(int i = 0; i <g.E; i++) {
    			if(g.edge[i].src == g.edge[j].src
    			|| g.edge[i].src == g.edge[j].dest
    			|| g.edge[i].dest == g.edge[j].src) {
    				adjacent.add(g.edge[i]);
    				j++;
    			}
    	}
    	return adjacent;
    }
    
    
    
    // A utility function to find set of an element i 
    // (uses path compression technique) 
    int find(subset subsets[], int i) 
    { 
        // find root and make root as parent of i (path compression) 
        if (subsets[i].parent != i) 
            subsets[i].parent = find(subsets, subsets[i].parent); 
  
        return subsets[i].parent; 
    } 
    
    
    
    
    
    
  
    // A function that does union of two sets of x and y 
    // (uses union by rank) 
    void Union(subset subsets[], int x, int y) 
    { 
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
        else
        { 
            subsets[yroot].parent = xroot; 
            subsets[xroot].rank++; 
        } 
    } 
}
