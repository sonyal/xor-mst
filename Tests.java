import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class Tests {

	@Test
	public void test0() {
		int v = 4, e = 3;
		Graph g = new Graph(v, e);

		g.edge[0].src = 1;
		g.edge[0].dest = 2;
		g.edge[0].weight = 26;

		g.edge[1].src = 2;
		g.edge[1].dest = 3;
		g.edge[1].weight = 18;

		g.edge[2].src = 3;
		g.edge[2].dest = 4;
		g.edge[2].weight = 5;
		assertEquals(13, g.findXOR(g, v, g.getFirstEdge(), 0, g.getFirstEdge().weight));
	}

	@Test
	public void test1() {
		int v = 4, e = 4;
		Graph g = new Graph(v, e);
		g.edge[0].src = 1;
		g.edge[0].dest = 2;
		g.edge[0].weight = 4;

		g.edge[1].src = 2;
		g.edge[1].dest = 3;
		g.edge[1].weight = 1;

		g.edge[2].src = 3;
		g.edge[2].dest = 4;
		g.edge[2].weight = 5;

		g.edge[3].src = 4;
		g.edge[3].dest = 1;
		g.edge[3].weight = 7;

		assertEquals(0, g.findXOR(g, v, g.getFirstEdge(), 0, g.getFirstEdge().weight));
	}

	@Test
	public void test2() {
		int v = 7;
		int e = 8;

		Graph g = new Graph(v, e);
		g.edge[0].src = 1;
		g.edge[0].dest = 2;
		g.edge[0].weight = 170;

		g.edge[1].src = 1;
		g.edge[1].dest = 3;
		g.edge[1].weight = 1;

		g.edge[2].src = 2;
		g.edge[2].dest = 4;
		g.edge[2].weight = 85;

		g.edge[3].src = 3;
		g.edge[3].dest = 4;
		g.edge[3].weight = 2;

		g.edge[4].src = 4;
		g.edge[4].dest = 5;
		g.edge[4].weight = 15;

		g.edge[5].src = 5;
		g.edge[5].dest = 7;
		g.edge[5].weight = 240;

		g.edge[6].src = 4;
		g.edge[6].dest = 6;
		g.edge[6].weight = 4;

		g.edge[7].src = 6;
		g.edge[7].dest = 7;
		g.edge[7].weight = 8;
		assertEquals(0, g.findXOR(g, v, g.getFirstEdge(), 0, g.getFirstEdge().weight));
	}

	@Test
	public void test3() {
		int v = 10;
		int e = 15;

		Graph g = new Graph(v, e);
		g.edge[0].src = 1;
		g.edge[0].dest = 2;
		g.edge[0].weight = 311;

		g.edge[1].src = 2;
		g.edge[1].dest = 3;
		g.edge[1].weight = 891;

		g.edge[2].src = 3;
		g.edge[2].dest = 4;
		g.edge[2].weight = 217;

		g.edge[3].src = 4;
		g.edge[3].dest = 5;
		g.edge[3].weight = 354;

		g.edge[4].src = 5;
		g.edge[4].dest = 6;
		g.edge[4].weight = 464;

		g.edge[5].src = 6;
		g.edge[5].dest = 7;
		g.edge[5].weight = 9;

		g.edge[6].src = 7;
		g.edge[6].dest = 8;
		g.edge[6].weight = 90;

		g.edge[7].src = 8;
		g.edge[7].dest = 9;
		g.edge[7].weight = 206;

		g.edge[8].src = 9;
		g.edge[8].dest = 10;
		g.edge[8].weight = 663;

		g.edge[9].src = 10;
		g.edge[9].dest = 1;
		g.edge[9].weight = 744;

		g.edge[10].src = 1;
		g.edge[10].dest = 7;
		g.edge[10].weight = 230;

		g.edge[11].src = 2;
		g.edge[11].dest = 4;
		g.edge[11].weight = 475;

		g.edge[12].src = 3;
		g.edge[12].dest = 8;
		g.edge[12].weight = 759;

		g.edge[13].src = 4;
		g.edge[13].dest = 10;
		g.edge[13].weight = 43;

		g.edge[14].src = 5;
		g.edge[14].dest = 9;
		g.edge[14].weight = 613;
		assertEquals(32, g.findXOR(g, v, g.getFirstEdge(), 0, g.getFirstEdge().weight));
	}
}
