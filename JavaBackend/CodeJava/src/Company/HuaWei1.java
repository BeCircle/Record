package Company;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class HuaWei1 {
    /**
     * 最短路径
     * 输入二维数组，3的位置表示入口，4表示出口，3,4 位置不确定
     * 中间1表示通畅，0表示不通畅
     * hashMap 存储每个节点到3的最短路径
     * 从3开始，取周围节点，存入队列；
     * 队列每个节点到3的最短路径
     * 节点往四周传播
     * */


    public static int nodesPathLength(int [][] graph){
        int start = 3;
        int end = 4;
        // 行
        int row = graph.length;
        // 列
        int column = graph[0].length;
        // 1. 找到起点
        int startX = -1,startY=-1;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                if (graph[i][j] ==start){
                    startX=i;
                    startY = j;
                    break;
                }
            }
            if (startX!=-1){
                break;
            }
        }
        // 2. 传播
        // 记录生病节点和里源头的最短距离
        Map<String, Integer> illNode = new HashMap<>();
//        List<Node> circle = new LinkedList<>();

        return 0;
    }


    public static void main(String[] args) {

    }
}
