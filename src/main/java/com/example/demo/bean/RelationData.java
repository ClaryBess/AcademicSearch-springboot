package com.example.demo.bean;

import java.util.List;

public class RelationData {
    String title;
    List<Researcher> nodes;
    List<Edge> edges;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Researcher> getNodes() {
        return nodes;
    }

    public void setNodes(List<Researcher> nodes) {
        this.nodes = nodes;
    }

    public List<Edge> getEdges() {
        return edges;
    }

    public void setEdges(List<Edge> edges) {
        this.edges = edges;
    }

    public RelationData(String title, List<Researcher> nodes, List<Edge> edges) {
        this.title = title;
        this.nodes = nodes;
        this.edges = edges;
    }
}
