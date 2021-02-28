package lesson7;

import java.util.List;
import java.util.Objects;

public class Vertex {

    private final String label;
    private boolean isVisited;
    private Vertex previousVertex; // HW: поле для хранения предыдущей вершины

    public Vertex(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public boolean isVisited() {
        return isVisited;
    }

    public void setVisited(boolean visited) {
        isVisited = visited;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vertex vertex = (Vertex) o;
        return Objects.equals(label, vertex.label);
    }

    @Override
    public int hashCode() {
        return Objects.hash(label);
    }

    @Override
    public String toString() {
        return "Vertex{" +
                "label='" + label + '\'' +
                '}';
    }

    // HW: геттер и сеттер для previousVertex
    public Vertex getPreviousVertex() {
        return previousVertex;
    }

    public void setPreviousVertex(Vertex previousVertex) {
        this.previousVertex = previousVertex;
    }
}
