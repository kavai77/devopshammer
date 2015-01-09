/*
 * SamlExamples.java
 * 
 * Copyright (c) 2015 by General Electric Company. All rights reserved.
 */
package com.smartdevs.engine;

/**
 * @author 212418603
 */
enum SamlExamples {
        SAMPLE1(
                "<root><bla/><bla2>text</bla2></root>",
                "PHJvb3Q+PGJsYS8+PGJsYTI+dGV4dDwvYmxhMj48L3Jvb3Q+",
                "synKzy%2Bxs0nKSdQHk0Z2JakVJTb6YKaNPlgWAA%3D%3D"
        );

        String decoded;
        String postEncoded;
        String redirectEncoded;

        SamlExamples( String decoded, String postEncoded, String redirectEncoded ) {
                this.decoded = decoded;
                this.postEncoded = postEncoded;
                this.redirectEncoded = redirectEncoded;
        }
}
