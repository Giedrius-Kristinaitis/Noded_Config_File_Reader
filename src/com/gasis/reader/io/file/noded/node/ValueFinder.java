package com.gasis.reader.io.file.noded.node;

public class ValueFinder implements ValueFinderInterface {

    private static final String PATH_DELIMITER = "/";

    @Override
    public String findValue(String path, NodeInterface rootNode) {
        String[] pathData = path.split(PATH_DELIMITER);
        NodeInterface currentNode = rootNode;

        for (int i = 0; i < pathData.length - 1; i++) {
            currentNode = currentNode.getNode(pathData[i]);

            if (currentNode == null) {
                return null;
            }
        }

        return currentNode.getValue(pathData[pathData.length - 1]);
    }
}
