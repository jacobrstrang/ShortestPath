import java.awt.*;

public class Vertex implements Comparable {
	
	final private int RADIUS = 2;
	private Graphics g;
	private int x, y, type;
	private double angle;

	public Vertex(int x, int y, int type, Graphics g) {
		this.x = x;
		this.y = y;
		this.type = type;
		this.g = g;
		this.angle = 0;
	}
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
	
	public void calculateAngle(Vertex p) {
		this.angle = 360 - Math.atan((this.getY() - p.getY())/(this.getX() - p.getX()));
	}
	
	public double getAngle() {
		return this.angle;
	}
	
	public void drawVertex() {
		int this_diameter;
		if(type == 0) {
			this_diameter = RADIUS * 6;
			g.setColor(Color.BLUE);
			g.fillOval(x - this_diameter/2, y - this_diameter/2, this_diameter, this_diameter);
		}
		else {
			this_diameter = RADIUS * 2;
			g.setColor(Color.RED);
			g.fillOval(x - this_diameter/2, y - this_diameter/2, this_diameter, this_diameter);
		}
	}
	
	public void drawLineTo(Vertex v) {
		if(type == 0) {
			g.setColor(Color.GREEN);
		}
		else{
			g.setColor(Color.RED);
		}
		g.drawLine(this.x, this.y, v.x, v.y);
	}

	@Override
	public int compareTo(Object arg0) {
		if (this.angle-((Vertex)arg0).getAngle() < 0){
			return -1;
		}
		else if (this.angle-((Vertex)arg0).getAngle() > 0){
			return 1;
		}
		else {
			return 0;
		}
	}
	
	
}
