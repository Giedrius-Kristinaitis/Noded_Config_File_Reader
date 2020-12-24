package com.gasis.reader.io.file.noded.line;

public class Validator {

    public boolean validate(String line, String delimiter) {
        if (line == null || line.isEmpty()) {
            return false;
        }

        if (!line.contains(delimiter)) {
            throw new RuntimeException("Invalid config detected: '" + line + "'");
        }

        String[] splitData = line.split(delimiter);

        if (splitData[0].trim().isEmpty()) {
            throw new RuntimeException("Invalid config detected: '" + line + "'");
        }

        return true;
    }
}
