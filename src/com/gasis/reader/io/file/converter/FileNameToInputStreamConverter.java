package com.gasis.reader.io.file.converter;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class FileNameToInputStreamConverter implements ConverterInterface {

    @Override
    public InputStream convert(String file) {
        try {
            return new FileInputStream(file);
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            throw new IllegalArgumentException("File not found: " + file);
        }
    }
}
