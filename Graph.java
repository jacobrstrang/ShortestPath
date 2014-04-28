import java.util.ArrayList;

public class Graph {
	
	ArrayList<Vertex> vertices;
	ArrayList<Edge> edges;
	
	public Graph(ArrayList<Vertex> vertices) {
		this.vertices = vertices;
		edges = new ArrayList<Edge>();
	}
	
	public void addVertices(ArrayList<Vertex> newVertices){
		for (Vertex v : newVertices){
			vertices.add(v);
		}
	}
	
	public void addEdges(ArrayList<Edge> newEdges){
		for (Edge v : newEdges){
			edges.add(v);
		}
	}
	
	public ArrayList<Vertex> getVertices() {
		return this.vertices;
	}
	
	public void addEdge(Edge e) {
		edges.add(e);
	}
}
