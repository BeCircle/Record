package algorithm;

/**
 * 给定一个无向图graph，当这个图为二分图时返回true。
 * 如果我们能将一个图的节点集合分割成两个独立的子集A和B，并使图中的每一条边的两个节点一个来自A集合，一个来自B集合，我们就将这个图称为二分图。
 * graph将会以邻接表方式给出，graph[i]表示图中与节点i相连的所有节点。每个节点都是一个在0到graph.length-1之间的整数。这图中没有自环和平行边： graph[i] 中不存在i，并且graph[i]中没有重复的值。
 *
 * 链接：https://leetcode-cn.com/problems/is-graph-bipartite
 * */
public class BiPartite_785 {
    public static boolean isBipartite(int[][] graph) {
        int nodeNum = graph.length;
        int []visited = new int[nodeNum];
        for (int i=0; i <nodeNum ; i++) {
            if(visited[i]==0&& !bfs(graph, visited, i, 1)){
                return false;
            }
        }
        return true;
    }

    public static boolean bfs(int[][] graph, int[] visited, int n, int color) {
        boolean res = true;
        for (int node: graph[n]){
            if(visited[node]!=0&&visited[node]!=color){
                return false;
            }else if(visited[node]==0) {
                visited[node]=color;
                res &= bfs(graph, visited, node, -color);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[][] inArr={{1,3}, {0,2}, {1,3}, {0,2}};
        int[][] inArr2={{}};
        int [][] inArr3={{1,2,3},{0,2},{0,1,3},{0,2}};
        System.out.println(isBipartite(inArr3));
    }
}
