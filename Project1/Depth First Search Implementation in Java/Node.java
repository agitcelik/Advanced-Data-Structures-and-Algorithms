public class Node<T> implements Comparable<Node<T>> {
     
    protected T data;
    protected boolean visited;
    public Integer index = null;
    public Integer lowlink = null;
    public double distance = Double.POSITIVE_INFINITY;
    public Node<T> predecessor = null;
     public int discoveryTime, finishingTime;
    public Node(T data) {
        this.data = data;
    }
    
    
    public Node() {
         
    }
     
   public boolean isVisited() {
        return visited;
    }
     
    public void visit() {
        visited = true;
    }
     
    public void unvisit() {
        visited = false;
        predecessor=null;
    }
     
 


	public int getDiscoveryTime() {
		return discoveryTime;
	}


	public void setDiscoveryTime(int discoveryTime) {
		this.discoveryTime = discoveryTime;
	}


	public int getFinishingTime() {
		return finishingTime;
	}


	public void setFinishingTime(int finishingTime) {
		this.finishingTime = finishingTime;
	}


	public int compareTo(Node<T> ob) {
        String tempA = this.toString();
        String tempB = ob.toString();
         
        return tempA.compareTo(tempB);
    }
     
    public String toString() {
        return data.toString();
    }
     
}
