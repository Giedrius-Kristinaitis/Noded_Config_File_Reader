package com.gasis.reader;

import com.gasis.reader.io.file.converter.FileNameToInputStreamConverter;
import com.gasis.reader.io.file.noded.Reader;
import com.gasis.reader.io.file.noded.ReaderInterface;
import com.gasis.reader.io.file.noded.line.CommentRemover;
import com.gasis.reader.io.file.noded.line.IndentationCalculator;
import com.gasis.reader.io.file.noded.line.Trimmer;
import com.gasis.reader.io.file.noded.line.Validator;
import com.gasis.reader.io.file.noded.node.NodeInterface;
import com.gasis.reader.io.file.noded.node.ValueFinder;
import com.gasis.reader.io.file.noded.node.ValueFinderInterface;

public class Main {

    public static void main(String[] args) {
        ReaderInterface reader = new Reader(
                new IndentationCalculator(),
                new Validator(),
                new FileNameToInputStreamConverter(),
                new Trimmer(),
                new CommentRemover()
        );

        ValueFinderInterface valueFinder = new ValueFinder();

        NodeInterface rootNode = reader.read("example.config");

        int someValue = Integer.parseInt(valueFinder.findValue("general/some_value", rootNode));
        int someOtherValue = Integer.parseInt(valueFinder.findValue("general/some_other_section/some_other_value", rootNode));
        String someStringValue = valueFinder.findValue("general/some_other_section/some_string_value", rootNode);
        boolean someBooleanValue = Boolean.parseBoolean(valueFinder.findValue("general/some_other_section/some_boolean_value", rootNode));

        System.out.println("Some value: " + someValue);
        System.out.println("Some other value: " + someOtherValue);
        System.out.println("Some string value: " + someStringValue);
        System.out.println("Some boolean value: " + someBooleanValue);
    }
}
