import java.awt.Graphics;
import java.util.ArrayList;

public class Obstacle {
	
	private ArrayList<Vertex> list;
	private Graphics g;
	ArrayList<Vertex> temp_list;
	
	public Obstacle(Graphics g) {
		list = new ArrayList<Vertex>();
		this.g = g;
	}

	public ArrayList<Vertex> getList() {
		return list;
	}

	public void setList(ArrayList<Vertex> list) {
		this.list = list;
	}
	
	public void addVertex(Vertex p) {
		list.add(p);
	}
	
	public int[] getXValues() {
		int[] x_vals = new int[list.size()];
		for(int i = 0; i < list.size(); i++) {
			x_vals[i] = list.get(i).getX();
		}
		return x_vals;
	}
	public int[] getYValues() {
		int[] y_vals = new int[list.size()];
		for(int i = 0; i < list.size(); i++) {
			y_vals[i] = list.get(i).getY();
		}
		return y_vals;
	}
	
	public void drawObstacle() {
		temp_list = (ArrayList<Vertex>) list.clone();
		for(Vertex p : temp_list) {
			p.drawVertex();
		}
		if(temp_list.size() > 1) {
			g.drawPolygon(getXValues(), getYValues(), getXValues().length);
		}
	}
}
