import java.util.LinkedList;
import java.util.Queue;

public class BFS {
 
	private Graph graph;
	private Node node;
	
	public void BFS(Graph g,Node s)
	{
		Node node = new Node<>();
		Queue q = new LinkedList<Node>();
		for(Node u : g.getNeighbors(s))
		{
			u.visited=false;
			u.predecessor=null;
			
		}
			
		
		q.add(s);
		
		
		node.visited=true;
		while(!q.isEmpty())
		{
			Node u = (Node) q.remove();
			
			for(Node v : g.getNeighbors(u))
			{
				if(v.isVisited()== false)
				{
					v.visited=true;
					v.distance=u.distance+1;
					v.predecessor=u;
					q.add(v);
				}
				v.visited=true;
			}
		
			
			
		}
		
		
		
		
	}
}
