package dungeon;

/**
 * This class provides union find algorithm that helps connect two nodes and 
 * find exactly one path that could be able to pass through all the nodes.
 *
 */
public class UnionFind {
  
  private int count;
  private int[] parent;
  
  /**
   * Construct a UnionFind object with n nodes and initialize the parent of 
   * each node is itself.
   * 
   * @param n the number of nodes
   */
  public UnionFind(int n) {
    this.count = n;
    parent = new int[n];
    for (int i = 0; i < n; i++) {
      parent[i] = i;
    }
  }
  
  /**
   * Connect two nodes with each other.
   * @param p node p
   * @param q node q
   */
  public void union(int p, int q) {
    int rootP = find(p);
    int rootQ = find(q);
    if (rootP == rootQ) {
      return;
    }
    parent[rootP] = rootQ;
    count--;
  }
  
  /**
   * Check if  two nodes have been connected with each other.
   * @param p node p
   * @param q node q
   * @return true if they are connected together, otherwise false
   */
  public boolean isConnected(int p, int q) {
    int rootP = find(p);
    int rootQ = find(q);
    return rootP == rootQ;
  }
  
  /**
   * Find the root of node x.
   * @param x the node to be searched
   * @return the root node of the node x
   */
  private int find(int x) {
    if (parent[x] != x) {
      parent[x] = find(parent[x]);
    }
    return parent[x];
  }
  
  /**
   * Get how many independent groups there are.
   * @return the number of independent groups
   */
  public int getCount() {
    return count;
  }

}
