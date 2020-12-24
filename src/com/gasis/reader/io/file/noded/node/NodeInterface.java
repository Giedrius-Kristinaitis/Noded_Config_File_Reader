package com.gasis.reader.io.file.noded.node;

import java.util.Map;

public interface NodeInterface {

    boolean hasValues();

    boolean hasNodes();

    void addValue(String name, String value);

    void addNode(String name, NodeInterface node);

    String getValue(String name);

    NodeInterface getNode(String name);

    NodeInterface getParentNode();

    void setParentNode(NodeInterface parentNode);

    Map<String, NodeInterface> getNodes();

    Map<String, String> getValues();
}
