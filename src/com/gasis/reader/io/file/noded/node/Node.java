package com.gasis.reader.io.file.noded.node;

import java.util.HashMap;
import java.util.Map;

public class Node implements NodeInterface {

    private Map<String, String> values;
    private Map<String, NodeInterface> nodes;
    private NodeInterface parentNode;

    public Node() {
        values = new HashMap<String, String>();
        nodes = new HashMap<String, NodeInterface>();
    }

    @Override
    public boolean hasValues() {
        return false;
    }

    @Override
    public boolean hasNodes() {
        return false;
    }

    @Override
    public void addValue(String name, String value) {
        values.put(name, value);
    }

    @Override
    public void addNode(String name, NodeInterface node) {
        nodes.put(name, node);
    }

    @Override
    public String getValue(String name) {
        return values.get(name);
    }

    @Override
    public NodeInterface getNode(String name) {
        return nodes.get(name);
    }

    @Override
    public void setParentNode(NodeInterface parentNode) {
        this.parentNode = parentNode;
    }

    @Override
    public NodeInterface getParentNode() {
        return parentNode;
    }

    @Override
    public Map<String, NodeInterface> getNodes() {
        return nodes;
    }

    @Override
    public Map<String, String> getValues() {
        return values;
    }
}
