package graph;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * leetcode 1584
 * https://leetcode-cn.com/problems/min-cost-to-connect-all-points/
 * 最小生成树
 */
public class TestPrimMST {

    public int minCostConnectPoints(int[][] points) {
        Graph graph = this.convert(points);
        //最小生成树的节点
        boolean[] marked = new boolean[graph.v];
        //存储最小生成树边的队列
        Queue<Edge> mst = new LinkedList<>();
        //存储图节点连接边的队列
        PriorityQueue<Edge> priorityQueue = new PriorityQueue<>((Comparator.comparingInt(o -> o.weight)));

        visit(graph, 0, marked, priorityQueue);
        while (!priorityQueue.isEmpty()) {
            Edge edge = priorityQueue.poll();
            int v = edge.either();
            int w = edge.other(v);
            if (marked[v] && marked[w]) {
                continue;
            }
            mst.add(edge);
            int visitNode = !marked[v] ? v : w;
            visit(graph, visitNode, marked, priorityQueue);
        }
        return this.getWeightSum(mst);
    }

    private Graph convert(int[][] points) {
        int number = points.length;
        Graph graph = new Graph(number);
        for (int i = 1; i < points.length; i++) {
            int currentX = points[i][0];
            int currentY = points[i][1];
            for (int j = 0; j < i; j++) {
                int tempX = points[j][0];
                int tempY = points[j][1];
                int val = Math.abs(currentX - tempX) + Math.abs(currentY - tempY);
                graph.addEdge(j, i, val);
            }
        }
        return graph;
    }

    private int getWeightSum(Queue<Edge> mst) {
        int result = 0;
        for (Edge edge : mst) {
            result += edge.weight;
        }
        return result;
    }

    private void visit(Graph graph, int v, boolean[] marked, PriorityQueue<Edge> priorityQueue) {
        marked[v] = true;
        for (Edge edge : graph.adjacency[v]) {
            if (!marked[edge.other(v)]) {
                priorityQueue.add(edge);
            }
        }
    }


    /**
     * 图的邻接表存储
     */
    public static class Graph {
        private int v;
        private int E;
        private LinkedList<Edge>[] adjacency;

        public Graph(int vNumber) {
            this.v = vNumber;
            this.adjacency = new LinkedList[vNumber];
            for (int i = 0; i < vNumber; i++) {
                adjacency[i] = new LinkedList<>();
            }
        }

        public void addEdge(int v, int w, int weight) {
            Edge edge = new Edge(v, w, weight);
            adjacency[v].add(edge);
            adjacency[w].add(edge);
            E++;
        }
    }

    /**
     * 图中顶点间的权重
     */
    public static class Edge {
        private final int v;
        private final int w;
        private int weight;

        public Edge(int v, int w, int weight) {
            this.v = v;
            this.w = w;
            this.weight = weight;
        }

        public int either() {
            return this.v;
        }

        public int other(int vertex) {
            if (vertex == v) {
                return w;
            } else if (vertex == w) {
                return v;
            } else {
                throw new RuntimeException("Inconsistent edge");
            }
        }
    }
}
