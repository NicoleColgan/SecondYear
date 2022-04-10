package ngtwk8solAstar;

public class Node {
	// open/closed states
	public boolean isOpen;
	public boolean isClosed;
	// position of parent
	public int parentx,parenty;
	// A* data 5
	public int f,g,h;
	// position on map
	public int x,y;
}
