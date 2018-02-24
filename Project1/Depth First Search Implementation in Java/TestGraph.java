import java.util.ArrayList;
import java.util.Stack;
import java.util.Vector;

public class TestGraph {
	private static Stack<String> stack;

	public static void main(String[] args) throws Exception {

		Graph g = new Graph();

		/*
		 * g.setDirected();
		 * 
		 * Node<Integer> n1=new Node<Integer>(1); Node<Integer> n2=new
		 * Node<Integer>(2); Node<Integer> n3=new Node<Integer>(3);
		 * Node<Integer> n4=new Node<Integer>(4); Node<Integer> n5=new
		 * Node<Integer>(5);
		 * 
		 * 
		 * Edge e1=new Edge(n1,n2,1); Edge e2=new Edge(n1,n3,1); Edge e3=new
		 * Edge(n3,n2,1); Edge e4=new Edge(n3,n4,1); Edge e5=new Edge(n3,n5,1);
		 * 
		 * g.addNode(n1); g.addNode(n2); g.addNode(n3); g.addNode(n4);
		 * g.addNode(n5);
		 * 
		 * 
		 * 
		 * g.addEdge(e1); g.addEdge(e2); g.addEdge(e3); g.addEdge(e4);
		 * g.addEdge(e5);
		 * 
		 */
	/*	g.loadDot("data/exgraph1.dot");
		double d[][] = g.getAdjacencyMatrix();

		for (int i = 0; i < d.length; i++)
			System.out.print("\t" + g.nodes.get(i));
		System.out.println();
		int r = 0;

		for (double arr[] : d) {
			System.out.print(g.nodes.get(r++) + "\t");
			for (double x : arr) {
				System.out.print(x + "\t");
			}
			System.out.println();
		}

		for (Node n : g.getNodes())
			System.out.println(n);

		for (Edge e : g.getEdges())
			System.out.println(e);

		stack = new Stack<>();

		Node current = null, last = null;
		for (Edge e : g.getEdges()) {
			if (e.b == last)
				continue;
			if (current == e.a) {
				stack.push(last.data.toString() + ") ");
			} else {
				current = e.a;
				if (!e.a.isVisited()) {
					e.a.visit();
					stack.push("(" + e.a.data.toString() + " ");
				}
			}

			if (last == e.b) {
				stack.push("(" + e.b.data.toString() + " " + e.b.data.toString() + ") ");
			} else {
				last = e.b;
				e.b.visit();
				stack.push("(" + e.b.data.toString() + " ");
			}
		}

		stack.push(last.data.toString() + ") ");
		stack.push(current.data.toString() + ") ");

		Node ust;
		while ((ust = current.predecessor) != null) {
			stack.push(ust.data.toString() + ") ");
			current = ust;
		}

		for (String s : stack) {
			System.out.print(s);
		}*/
		g.loadDot("data/exgraph2.dot");
		//g.dfs();
		g.discoveryAndFinishingTime();
		g.edgeTypes();
	}
}
