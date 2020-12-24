package com.gasis.reader.io.file.noded;

import com.gasis.reader.io.file.converter.ConverterInterface;
import com.gasis.reader.io.file.noded.line.CommentRemover;
import com.gasis.reader.io.file.noded.line.IndentationCalculator;
import com.gasis.reader.io.file.noded.line.Trimmer;
import com.gasis.reader.io.file.noded.line.Validator;
import com.gasis.reader.io.file.noded.node.Node;
import com.gasis.reader.io.file.noded.node.NodeInterface;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Reader implements ReaderInterface {

    private static final String DELIMITER = ":";
    private static final String COMMENT_SYMBOL = "#";

    private IndentationCalculator indentationCalculator;
    private Validator validator;
    private ConverterInterface inputConverter;
    private Trimmer trimmer;
    private CommentRemover commentRemover;

    public Reader(IndentationCalculator indentationCalculator, Validator validator, ConverterInterface inputConverter, Trimmer trimmer, CommentRemover commentRemover) {
        this.indentationCalculator = indentationCalculator;
        this.validator = validator;
        this.inputConverter = inputConverter;
        this.trimmer = trimmer;
        this.commentRemover = commentRemover;
    }

    @Override
    public NodeInterface read(String inputSource) {
        try {
            return getRootNode(inputSource);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    private NodeInterface getRootNode(String inputSource) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputConverter.convert(inputSource)));
        NodeInterface root = new Node();
        NodeInterface currentNode = root;
        int previousIndentationLevel = 0;
        String line = null;

        while ((line = reader.readLine()) != null) {
            line = commentRemover.removeComments(line, COMMENT_SYMBOL);

            if (!validator.validate(line, DELIMITER)) {
                continue;
            }

            int indentationLevel = indentationCalculator.getLineIndentationLevel(line);

            line = trimmer.trim(line);

            currentNode = getCurrentNodeFromIndentation(currentNode, previousIndentationLevel, indentationLevel);
            currentNode = modifyCurrentNode(currentNode, line);

            previousIndentationLevel = indentationLevel;
        }

        return root;
    }

    private NodeInterface getCurrentNodeFromIndentation(NodeInterface currentNode, int previousIndentationLevel, int indentationLevel) {
        int indentationDifference = indentationLevel - previousIndentationLevel;

        if (indentationDifference > 1) {
            throw new RuntimeException("Invalid config file line indentation detected");
        }

        if (indentationLevel == previousIndentationLevel || indentationDifference == 1) {
            return currentNode;
        }

        return getCurrentNodeFromIndentation(currentNode.getParentNode(), previousIndentationLevel - 1, indentationLevel);
    }

    private NodeInterface modifyCurrentNode(NodeInterface currentNode, String line) {
        String[] pathValue = line.split(DELIMITER);
        String path = pathValue[0];
        String value = pathValue.length > 1 ? pathValue[1] : null;

        if (value == null || value.isEmpty()) {
            NodeInterface child = new Node();
            child.setParentNode(currentNode);
            currentNode.addNode(path, child);

            return child;
        } else {
            currentNode.addValue(path, value.trim());

            return currentNode;
        }
    }
}
