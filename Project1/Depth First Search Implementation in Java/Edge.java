
public class Edge {
 
    protected Node a, b;
    protected double weight;
     
    public Edge(Node a, Node b) {
        this(a, b, Double.POSITIVE_INFINITY);
    }
     
    public Edge(Node a, Node b, double weight) {
        this.a = a;
        this.b = b;
        this.weight = weight;
    }
     
    public double getWeight() {
        return weight;
    }
    
    public void setWeight(double d){
    	this.weight=d;
    }
    
    
    public String toString() {
        return a + " ==> " + b;
    }
    
    

	
 
}
