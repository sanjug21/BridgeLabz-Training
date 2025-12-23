import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class cloneGraph {
    // 133. Clone Graph
    // https://leetcode.com/problems/clone-graph/
// Definition for a Node.
static class Node {
    public int val;
    public List<Node> neighbors;
    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}
    public static void main(String[] args) {
        cloneGraph obj=new cloneGraph();
        Node node1=new Node(1); 
        Node node2=new Node(2);
        Node node3=new Node(3);
        Node node4=new Node(4);
        node1.neighbors.add(node2);
        node1.neighbors.add(node4);
        node2.neighbors.add(node1);
        node2.neighbors.add(node3);
        node3.neighbors.add(node2);
        node3.neighbors.add(node4);
        node4.neighbors.add(node1);
        node4.neighbors.add(node3);
        Node cloned=obj.cloneGraphSol(node1);
        System.out.println(cloned);
    }



    private HashMap<Node,Node>vis=new HashMap<>();
    public Node cloneGraphSol(Node node) {
        if(node ==null)return null;
        if(vis.containsKey(node))return vis.get(node);
        Node cloned=new Node(node.val);
        vis.put(node,cloned);
        for(Node n:node.neighbors){
            cloned.neighbors.add(cloneGraphSol(n));
        }
        return cloned;
    }
}

