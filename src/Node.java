public class Node
{
	Node parent;
	ArrayList<Node> children;
	String name;
	public Node()
	{
		parent = null;
		children = new ArrayList<Node>;
		name = "";
	}
	public Node(String s)
	{
		parent = null;
		children = new ArrayList<Node>;
		name = s;
	}
	public Node(String s, Node n)
	{
		parent = n;
		children = new ArrayList<Node>;
		name = s;
	}
}
