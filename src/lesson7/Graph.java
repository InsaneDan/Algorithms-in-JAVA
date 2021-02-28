package lesson7;

import java.util.*;

public class Graph implements IGraph {

    private final List<Vertex> vertexList;
    private final boolean[][] adjMat;

    public Graph(int maxVertexCount) {
        this.vertexList = new ArrayList<>(maxVertexCount);
        this.adjMat = new boolean[maxVertexCount][maxVertexCount];
    }

    @Override
    public void addVertex(String label) {
        vertexList.add(new Vertex(label));
    }

    @Override
    public void addEdge(String startLabel, String endLabel) {
        int startIndex  = indexOf(startLabel);
        int endIndex    = indexOf(endLabel);

        if (startIndex == -1 || endIndex == -1) {
            throw new IllegalArgumentException("Invalid label for vertex");
        }

        adjMat[startIndex][endIndex] = true;
        adjMat[endIndex][startIndex] = true;
    }

    @Override
    public void addEdges(String startLabel, String secondLabel, String... others) {
        addEdge(startLabel, secondLabel);
        for (String other : others) {
            addEdge(startLabel, other);
        }
    }

    private int indexOf(String startLabel) {
        for (int index = 0; index < getSize(); index++) {
            if (vertexList.get(index).getLabel().equals(startLabel)) {
                return index;
            }
        }

        return -1;
    }

    @Override
    public int getSize() {
        return vertexList.size();
    }

    @Override
    public void display() {
        for (int i = 0; i < getSize(); i++) {
            System.out.print(vertexList.get(i));
            for (int j = 0; j < getSize(); j++) {
                if (adjMat[i][j]) {
                    System.out.print(" -> " + vertexList.get(j));
                }
            }
            System.out.println();
        }
    }

    @Override
    public void dfs(String startLabel) {                                    // Depth First Search
        int startIndex = indexOf(startLabel);
        if (startIndex == -1) {
            throw new IllegalArgumentException("Invalid start label");
        }

        Stack<Vertex> stack = new Stack<>();
        Vertex vertex = vertexList.get(startIndex);

        visitVertex(stack, vertex);
        while (!stack.isEmpty()) {
            vertex = getNearUnvisitedVertex(stack.peek()); // stack.peek() будет брать последний Vertex
            if (vertex != null) {
                visitVertex(stack, vertex);
            } else {
                stack.pop();
            }
        }

        resetVertexState();
    }

    @Override
    public void bfs(String startLabel) { // Breadth First Search
        int startIndex = indexOf(startLabel);
        if (startIndex == -1) {
            throw new IllegalArgumentException("Invalid start label");
        }

        Queue<Vertex> queue = new LinkedList<>();
        Vertex vertex = vertexList.get(startIndex);

        visitVertex(queue, vertex);
        while (!queue.isEmpty()) {
            vertex = getNearUnvisitedVertex(queue.peek()); // queue.peek() будет брать первую вершину Vertex
            if (vertex != null) {
                visitVertex(queue, vertex);
            } else {
                queue.remove();
            }
        }

        resetVertexState();
    }

    private void resetVertexState() {
        for (Vertex vertex : vertexList) {
            vertex.setVisited(false);
        }
    }

    private Vertex getNearUnvisitedVertex(Vertex current) {
        int currentIndex = vertexList.indexOf(current);
        for (int i = 0; i < getSize(); i++) {
            if (adjMat[currentIndex][i] && !vertexList.get(i).isVisited()) {
                return vertexList.get(i);
            }
        }
        return null;
    }

    private void visitVertex(Stack<Vertex> stack, Vertex vertex) {
//        System.out.println(vertex);
        vertex.setVisited(true);
        stack.push(vertex);
    }
    private void visitVertex(Queue<Vertex> queue, Vertex vertex) {
//        System.out.println(vertex);
        vertex.setVisited(true);
        queue.add(vertex);
    }

    // HW: перегруженный метод с сохранением предыдущей вершины
    private void visitVertex(Queue<Vertex> queue, Vertex vertex, Vertex previousVertex) {
        visitVertex(queue, vertex);
        vertex.setPreviousVertex(previousVertex);
    }

    public List<Vertex> trackShortWay(String startLabel, String endLabel) {
        int startIndex = indexOf(startLabel);
        if (startIndex == -1) {
            throw new IllegalArgumentException("Invalid start label");
        }

        Queue<Vertex> queue = new LinkedList<>();
        Vertex vertex = vertexList.get(startIndex);

        // обходим и сохраняем количество шагов от стартовой вершины (текущая - queue.peek())
        visitVertex(queue, vertex, null);
        while (!queue.isEmpty()) {
            vertex = getNearUnvisitedVertex(queue.peek());
            if (vertex != null) {
                visitVertex(queue, vertex, queue.peek());
            } else {
                queue.remove();
            }
        }

        // восстанавливаем путь (в обратном порядке)
        List<Vertex> track = new ArrayList<>();
        vertex = vertexList.get(indexOf(endLabel));
        while (vertex != null) {
            track.add(vertex);
            vertex = vertex.getPreviousVertex();
        }

        // реверс коллекции
        if (track.size() > 0) {
            Collections.reverse(track);
        }
        resetVertexState(); // очистка значений после обхода
        return track; // вернуть результат
    }
}
