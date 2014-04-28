import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Main extends Applet implements MouseListener, KeyListener
{
	private final int ENDPOINT = 0;
	private final int OBSTACLE_POINT = 1;
	private final int APPLET_WIDTH = 1920;
	private final int APPLET_HEIGHT = 1080;
	private Graphics g;
	private Graphics buffer_g;
	private Image image_buffer;
	private int phase;
	private Vertex start, end;
	private ArrayList<Obstacle> obstacles;
	private ArrayList<Obstacle> temp_obstacles;
	private int current_obstacle_index;
	
	public void init() {
		phase = 0;
	    setVisible(true);
	    g = getGraphics();
	    image_buffer = createImage(APPLET_WIDTH, APPLET_HEIGHT);
	    buffer_g = image_buffer.getGraphics();
	    addMouseListener(this);
	    addKeyListener(this);
	    
	    start = null;
	    end = null;
	    
	    obstacles = new ArrayList<Obstacle>();
	    current_obstacle_index = -1;
	}

	public void start()
	{   
		showStatus("Create the start point...");
		while(true)
		{
			buffer_g.setColor(Color.WHITE);
			buffer_g.clearRect(0, 0, APPLET_WIDTH, APPLET_HEIGHT);
			if(start != null) {
				start.drawVertex();
			}
			if(end != null) {
				end.drawVertex();
			}
			temp_obstacles = (ArrayList<Obstacle>) obstacles.clone();
			for(Obstacle o : temp_obstacles) {
				o.drawObstacle();
			}
			g.drawImage(image_buffer, 0, 0, this);
		}
	}
	
	public void processClick(int x, int y) {
		if(phase == 0) { // process point 1
			start = new Vertex(x, y, ENDPOINT, buffer_g);
			phase++;
			showStatus("Create the end point...");
		}
		else if(phase == 1) { // process point 2
			end = new Vertex(x, y, ENDPOINT, buffer_g);
			phase = -1;
			showStatus("[O] start new obstacle; [SPACE] render visibility graph");
		}
		else if(phase == 2) { // process obstacle point
			if(current_obstacle_index >= obstacles.size()) {
				obstacles.add(new Obstacle(buffer_g));
			}
			obstacles.get(current_obstacle_index).addVertex(new Vertex(x, y, OBSTACLE_POINT, buffer_g));
		}
	}
	
	public void mouseClicked(MouseEvent me) {}
	public void mousePressed(MouseEvent me) {
		int x, y;
		x = me.getX();
		y = me.getY();
		processClick(x, y);
	}
	public void mouseReleased(MouseEvent me) {}
	public void mouseEntered(MouseEvent me) {}
	public void mouseExited(MouseEvent me) {}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_O) {
			phase = 2;
			current_obstacle_index++;
		}
		if(e.getKeyCode() == KeyEvent.VK_SPACE) {
			if(phase == 2 || phase == 4) {
				phase = 3;
			}
			else if(phase == 3) {
				phase = 4;
			}
			
			if(phase == 3) {
				showStatus("Now rendering visibility graph; [SPACE] toggle between visibility graph and shortest path");
			}
			else if(phase == 4) {
				showStatus("Now rendering shortest path; [SPACE] toggle between visibility graph and shortest path");
			}
		}
	}
	
	public void visibilityGraph(ArrayList<Obstacle> visObstacles) {
		Graph visGraph = new Graph(new ArrayList<Vertex>());
		for (Obstacle o : visObstacles) {
			visGraph.addVertices(o.getList());
		}
		for (Vertex v : visGraph.getVertices()){
			ArrayList<Vertex> W = visibleVertices(visGraph.getVertices(), v);
			for (Vertex w : W) {
				Edge e = new Edge(v.getX(), v.getY(), w.getX(), w.getY());
				visGraph.addEdge(e);
			}
		}
	}
	
	public ArrayList<Vertex> visibleVertices(ArrayList<Vertex> oVertices, Vertex v){
		for (Vertex x : oVertices) {
			x.calculateAngle(v);
		}
		Collections.sort(oVertices);
		return new ArrayList<Vertex>(); //FIXME
	}
	
	@Override
	public void keyReleased(KeyEvent e) {}
	@Override
	public void keyTyped(KeyEvent e) {}
}