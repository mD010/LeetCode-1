/*
	Number of Islands II
	A 2d grid map of m rows and n columns is initially filled with water. We may perform an addLand operation which turns the water at position (row, col) into a land. Given a list of positions to operate, count the number of islands after each addLand operation. An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.

	Example:

	Given m = 3, n = 3, positions = [[0,0], [0,1], [1,2], [2,1]].
	Initially, the 2d grid grid is filled with water. (Assume 0 represents water and 1 represents land).

		0 0 0
		0 0 0
		0 0 0
		Operation #1: addLand(0, 0) turns the water at grid[0][0] into a land.

		1 0 0
		0 0 0   Number of islands = 1
		0 0 0
		Operation #2: addLand(0, 1) turns the water at grid[0][1] into a land.

		1 1 0
		0 0 0   Number of islands = 1
		0 0 0
		Operation #3: addLand(1, 2) turns the water at grid[1][2] into a land.

		1 1 0
		0 0 1   Number of islands = 2
		0 0 0
		Operation #4: addLand(2, 1) turns the water at grid[2][1] into a land.

		1 1 0
		0 0 1   Number of islands = 3
		0 1 0
	We return the result as an array: [1, 1, 2, 3]
*/
public class Solution {
    private int[][] dir = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};
    public List<Integer> numIslands2(int m, int n, int[][] positions) {
        UnionFind2D islands = new UnionFind2D(m, n);
        List<Integer> res = new ArrayList<>();
        for (int[] position : positions) {
            int x = position[0];
            int y = position[1];
            int p = islands.add(x, y);
            for (int[] d : dir) {
                int q = islands.getID(x + d[0], y + d[1]);
                if (q > 0 && !islands.find(p, q)) {
                    islands.unite(p, q);
                }
            }
            res.add(islands.size());
        }
        return res;
    }
    
}
class UnionFind2D {
    private int[] id;
    private int[] size;//count every id's size
    private int m, n, count;
    
    public UnionFind2D(int m, int n) {
        this.count = 0;
        this.n = n;
        this.m = m;
        this.id = new int[m * n + 1];
        this.size = new int[m * n + 1];
    }
    
    public int index(int x, int y) {
        return x * n + y + 1;
    }
    
    public int size() {
        return this.count;
    }
    
    public int getID(int x, int y) {
        if (0 <= x && x < m && 0 <= y && y < n) {
            return id[index(x, y)];
        }
        return 0;
    }
    public int add(int x, int y) { //every time add a point, 
        int i = index(x, y);
        id[i] = i;
        size[i] = 1;
        count++;
        return i;
    }
    public boolean find(int p, int q) {
        return root(p) == root(q);
    }
    public void unite(int p, int q) {
        int i = root(p);
        int j = root(q);
        if (size[i] < size[j]) {// weighted quick union
            id[i] = j;
            size[j] += size[i];
        } else {
            id[j] = i;
            size[i] += size[j];
        }
        count--;
    }
    private int root(int i) {
        while (i != id[i]) {//path compression
            i = id[i]; 
        }
        return i;
    }
}


//Solution2 concise
public class Solution {
	//using to check the near point
	int[][] dirs = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};
    public List<Integer> numIslands2(int m, int n, int[][] positions) {
        List<Integer> res = new ArrayList<>();
        if (m <= 0 || n <= 0) {
            return res;
        }
        
        int count = 0;// number of islands
        int[] roots = new int[m * n]; // one island = one tree
        Arrays.fill(roots, -1);
        
        for (int[] p : positions) {
            int root = n * p[0] + p[1];//index the 2D matrix
            roots[root] = root;// add new island
            count++;
            
            for (int[] dir : dirs) {
                int x = p[0] + dir[0];
                int y = p[1] + dir[1];
                int idNear = n * x + y;
                if (x < 0 || y < 0 || x >= m || y >= n || roots[idNear] == -1) {
                    continue;
                }
                int rootIdNear = find(roots, idNear);
                if (root != rootIdNear) {// if neighbor is in another island
                    roots[rootIdNear] = root; // union two islands
                    count--;
                }
            }
            res.add(count);
        }
        return res;
    }
    
    public int find(int[] roots, int id) {
        while (id != roots[id]) {
            id = roots[id];
        }
        return id;
    }
}