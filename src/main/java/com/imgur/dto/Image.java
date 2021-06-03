package com.imgur.dto;

public enum Image {

    POSITIVE("src/test/resources/image.jpg"),
    SMALL("src/test/resources/1kb.png"),
    NORMAL("src/test/resources/size_5_2.jpg"),
    BIG("src/test/resources/10_1.png"),
    OVER_SIZE("src/test/resources/17_4.png");

    public final String path;

    Image(String path) {
        this.path = path;
    }
}
