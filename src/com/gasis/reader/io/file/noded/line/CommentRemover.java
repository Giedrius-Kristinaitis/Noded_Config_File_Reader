package com.gasis.reader.io.file.noded.line;

public class CommentRemover {

    public String removeComments(String line, String commentSymbol) {
        if (!line.contains(commentSymbol)) {
            return line;
        }

        int commentIndex = line.indexOf(commentSymbol);

        return line.substring(0, commentIndex);
    }
}
