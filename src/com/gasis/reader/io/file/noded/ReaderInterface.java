package com.gasis.reader.io.file.noded;

import com.gasis.reader.io.file.noded.node.NodeInterface;

public interface ReaderInterface {

    NodeInterface read(String inputSource);
}
