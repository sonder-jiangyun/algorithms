package graph;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;


public class LazyPrimMST {

    /**
     * 标记最小生成树的节点
     */
    private boolean[] marked;
    /**
     * 存储最小生成树边的队列
     */
    private Queue<Edge> mst;
    /**
     * 存储图节点连接边的队列
     */
    private PriorityQueue<Edge> priorityQueue;

    public LazyPrimMST(Graph graph) {
        priorityQueue = new PriorityQueue<>(graph.E, (Comparator.comparingInt(o -> o.weight)));
        marked = new boolean[graph.v];
        mst = new LinkedList<>();
        visit(graph, 0);
        while (!priorityQueue.isEmpty()) {
            Edge edge = priorityQueue.poll();
            int v = edge.either();
            int w = edge.other(v);
            if (marked[v] && marked[w]) {
                continue;
            }
            mst.add(edge);
            if (!marked[v]) {
                visit(graph, v);
            }
            if (!marked[w]) {
                visit(graph, w);
            }
        }
    }

    public int getWeightSum() {
        int result = 0;
        for (Edge edge : mst) {
            result += edge.weight;
        }

        return result;
    }

    private void visit(Graph graph, int v) {
        marked[v] = true;
        for (Edge edge : graph.adjacency[v]) {
            if (!marked[edge.other(v)]) {
                priorityQueue.add(edge);
            }
        }
    }


    public static void main(String[] args) {
        Graph graph = new Graph(8);
        graph.addEdge(0, 1, 3);
        graph.addEdge(0, 3, 5);
        graph.addEdge(1, 2, 4);
        graph.addEdge(1, 4, 7);
        graph.addEdge(2, 5, 2);
        graph.addEdge(4, 5, 8);
        graph.addEdge(4, 6, 3);
        graph.addEdge(5, 7, 3);
        graph.addEdge(6, 7, 4);

        LazyPrimMST lazyPrimMST = new LazyPrimMST(graph);
        System.out.println(lazyPrimMST.getWeightSum());

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

        /**
         * @return 加权图中的所有边
         */
        public Iterable<Edge> edges() {
            LinkedList<Edge> edges = new LinkedList<>();
            for (int v = 0; v < this.v; v++) {
                for (Edge edge : this.adjacency[v]) {
                    if (edge.other(v) > v) {
                        edges.add(edge);
                    }
                }
            }
            return edges;
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
