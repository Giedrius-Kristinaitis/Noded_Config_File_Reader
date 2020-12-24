package com.gasis.reader.config;

import com.gasis.reader.io.file.noded.ReaderInterface;
import com.gasis.reader.io.file.noded.node.NodeInterface;
import com.gasis.reader.io.file.noded.node.ValueFinderInterface;

public class ConfigValueProvider implements ConfigValueProviderInterface {

    private String configFileName;
    private NodeInterface rootNode;
    private ValueFinderInterface valueFinder;
    private ReaderInterface inputReader;

    public ConfigValueProvider(String configFileName, ReaderInterface inputReader, ValueFinderInterface valueFinder) {
        this.configFileName = configFileName;
        this.valueFinder = valueFinder;
        this.inputReader = inputReader;
    }

    @Override
    public String getConfigValue(String path) {
        if (rootNode == null) {
            initialize();
        }

        String value = valueFinder.findValue(path, rootNode);

        if (value == null) {
            throw new RuntimeException("Config path '" + path + "' not found");
        }

        return value;
    }

    private void initialize() {
        rootNode = inputReader.read(configFileName);

        if (rootNode != null) {
            return;
        }

        throw new RuntimeException("Invalid config file structure");
    }
}
