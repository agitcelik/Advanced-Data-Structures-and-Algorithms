import java.io.File;
import java.util.*;

public class Graph {

	protected Vector<Node> nodes = new Vector<Node>();
	protected Vector<Edge> edges = new Vector<Edge>();
	protected boolean directed = false;
	protected boolean sortedNeighbors = false;
	int time;

	public void dfsVisit(Node u) {// node==u
		u.visit();// gray
		time = time + 1;//time+=1
		System.out.print("(" + u.toString() + " ");
		u.setDiscoveryTime(time);

		for (Node v : getNeighbors(u)) {// take a look all of te neg. of U in
										// adj(u)
			if (!v.visited) {
				v.predecessor = u;
				dfsVisit(v);
			}
		}
		time = time + 1;
		System.out.print(" " + u.toString() + ")");
		u.setFinishingTime(time);
	}
	public void dfs() {
		unvisitAllNodes();	
		time = 0;
		for (Node node : nodes) {
			if (!node.visited) {
				dfsVisit(node);
			}
		}
	}
/*dfs
 * ilk white 
 * tüm nodeleri kontorl et not visit ise dfs visit (time ++,  System.out paratez gosterimi için tüm komsularý kontrol et  not visit ise 
 * ())
 * time
 *  
 *  edge
 *   2 node olusturduk tree olmasý ýcýn dýscovery týme  1 eksýkse tree oldu
 *     (hoca derste verdi) buyukse  forward küçükse back ward
 *     cross bir tane var kendýne gýdýyorsa bir tane var  (c)
 */
	

	public void discoveryAndFinishingTime() {
		dfs();
		System.out.println("");
		for (Node e : nodes) {
			System.out.println("Discovery time of node " + e.toString() + " is  " + e.getDiscoveryTime()
					+ " , and finishing time is " + e.getFinishingTime());
		}
	}

	public void edgeTypes() {
		dfs();
		System.out.println("\n");
		for (Edge edge : edges) {
			Node u = edge.a;
			Node v = edge.b;
			if( v.getDiscoveryTime()-u.getDiscoveryTime()== 1){
				System.out.println(edge.toString() + " is tree ");
			}
			else if (u.getDiscoveryTime() < v.getDiscoveryTime() && v.getFinishingTime() < u.getFinishingTime()) {
				System.out.println(edge.toString() + " is forward ");

			} else if (v.getDiscoveryTime() < u.getDiscoveryTime() && u.getFinishingTime() < v.getFinishingTime()) {
				System.out.println(edge.toString() + " is backward");
			
			} else if (v.getDiscoveryTime() < v.getFinishingTime() && u.getDiscoveryTime() < u.getFinishingTime()) {
				System.out.println(edge.toString() + " is cross");
			}
		}
	}


	public double[][] getAdjacencyMatrix() {
		double[][] adjMatrix = new double[nodes.size()][nodes.size()];

		for (int i = 0; i < nodes.size(); i++)
			for (int j = 0; j < nodes.size(); j++)
				if (i == j)
					adjMatrix[i][j] = 0;

		for (int i = 0; i < nodes.size(); i++) {
			Node node = nodes.elementAt(i);
			// System.out.println("Current node: " + node);

			for (int j = 0; j < edges.size(); j++) {
				Edge edge = edges.elementAt(j);

				if (edge.a == node) {
					int indexOfNeighbor = nodes.indexOf(edge.b);

					adjMatrix[i][indexOfNeighbor] = edge.weight;
				}
			}
		}

		return adjMatrix;
	}

	public void setDirected() {
		directed = true;
	}

	public void setUndirected() {
		directed = false;
	}

	public boolean isDirected() {
		return directed;
	}

	public boolean isSortedNeighbors() {
		return sortedNeighbors;
	}

	public void setSortedNeighbors(boolean flag) {
		sortedNeighbors = flag;
	}

	public int indexOf(Node a) {
		for (int i = 0; i < nodes.size(); i++)
			if (nodes.elementAt(i).data.equals(a.data))
				return i;

		return -1;
	}

	public Vector<Node> getNodes() {
		return nodes;
	}

	public Vector<Edge> getEdges() {
		return edges;
	}

	public Node getNodeAt(int i) {
		return nodes.elementAt(i);
	}

	public void unvisitAllNodes() {
		for (int i = 0; i < nodes.size(); i++)
			nodes.elementAt(i).unvisit();
	}

	public Vector<Node> getNeighbors(Node a) {
		Vector<Node> neighbors = new Vector<Node>();

		for (int i = 0; i < edges.size(); i++) {
			Edge edge = edges.elementAt(i);

			if (edge.a == a)
				neighbors.add(edge.b);

			if (!directed && edge.b == a)
				neighbors.add(edge.a);
		}

		if (sortedNeighbors)
			Collections.sort(neighbors);

		return neighbors;
	}

	public int addNode(Node a) {
		nodes.add(a);

		return nodes.size() - 1;
	}

	public void addEdge(Edge edge) {
		edges.add(edge);

		if (!directed)
			edges.add(new Edge(edge.b, edge.a, edge.weight));
	}

	public void printNodes() {
		System.out.println(nodes);
	}

	public void printEdges() {
		System.out.println(edges);
	}

	void loadDot(String filename) throws Exception {
		// BufferedInputStream fr=new BufferedInputStream(new
		// FileInputStream(filename));
		Scanner scanner = new Scanner(new File(filename));
		boolean inGraph = false;
		while (scanner.hasNext()) {
			String token = scanner.next().trim();
			if (token.equals("{")) {
				inGraph = true;
				scanner.useDelimiter(";");
			} else if (!inGraph && token.equals("graph"))
				setUndirected();
			else if (!inGraph && token.equals("digraph"))
				setDirected();
			else if (inGraph && token.length() > 0 && !(token.equals("}"))) {
				if (token.lastIndexOf(" ") < 0) { // a vertex
					Node<String> n = new Node<String>(token);
					System.out.println("adding " + n);
					addNode(n);
				} else {
					String[] parts = token.split(" ");
					String from = parts[0], to = parts[2];

					System.out.println(from);
					System.out.println(to);

					if (!isNode(from) || !isNode(to))
						throw new Exception("from or to vertex can not be found for line: " + token);

					Edge edge = new Edge(this.getNode(from), this.getNode(to));
					if (parts.length > 3) {
						String[] tmp = parts[3].replace("[", "").replace("]", "").split("=");
						if (tmp[0].equals("weight") || tmp[0].equals("w"))
							edge.setWeight(Double.parseDouble(tmp[1]));
					}
					edges.add(edge);
					System.out.println("adding " + edge);

					if (!directed) {
						Edge reverseEdge = new Edge(getNode(to), getNode(from));
						reverseEdge.setWeight(edge.weight);
						edges.add(reverseEdge);
						System.out.println("adding " + reverseEdge);
					}
				}
			}
		}
	}

	private Node getNode(String from) {
		for (Node n : this.nodes)
			if (n.data.equals(from))
				return n;

		return null;
	}

	private boolean isNode(String from) {

		for (Node n : this.nodes)
			if (n.data.equals(from))
				return true;
		return false;
	}

	public void breadthFirstSearch(Node root) throws InterruptedException {
		Queue<Node> queue = new LinkedList<>();

		unvisitAllNodes();

		root.visit();
		queue.add(root);

		while (!queue.isEmpty()) {
			Node r = queue.remove();
			System.out.println(r.data.toString());

			for (Node n : getNeighbors(r)) {
				if (!n.isVisited()) {
					n.visit();
					n.predecessor = r;
					queue.add(n);
				}
			}
		}
	}

	public LinkedList<Node> shortestPath(Node a, Node b) throws InterruptedException {
		breadthFirstSearch(getNode((String) a.data));
		Node x = b;

		LinkedList<Node> list = new LinkedList<>();

		while (x != null) {
			list.add(x);
			x = x.predecessor;
		}

		return list;
	}

	public void depthFirstSearch(Node root) throws InterruptedException {

		Stack<Node> stack = new Stack<>();

		root.visit();
		stack.push(root);

		int startTime = 0;
		int finishTime = 0;
		int count = 0;

		while (!stack.isEmpty()) {

			startTime++;

			Node u = stack.pop();

			System.out.println(u.data.toString() + " " + "Start time is:" + " " + startTime + " " + finishTime);

			u.visited = true;
			for (Node n : getNeighbors(u)) {

				if (!n.isVisited()) {
					n.visit();
					n.distance = finishTime;

					stack.push(n);

				}

			}

		}

	}

}
