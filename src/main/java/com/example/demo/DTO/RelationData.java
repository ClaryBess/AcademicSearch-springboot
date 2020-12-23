package com.example.demo.DTO;

import com.example.demo.bean.Edge;
import com.example.demo.bean.Node;
import com.example.demo.bean.Researcher;

import java.util.List;

public class RelationData {
    String title;
    List<Node> nodes;
    List<Edge> edges;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Edge> getEdges() {
        return edges;
    }

    public void setEdges(List<Edge> edges) {
        this.edges = edges;
    }

    public List<Node> getNodes() {
        return nodes;
    }

    public void setNodes(List<Node> nodes) {
        this.nodes = nodes;
    }

    public RelationData(String title, List<Node> nodes, List<Edge> edges) {
        this.title = title;
        this.nodes = nodes;
        this.edges = edges;
    }
}
