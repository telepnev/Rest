package com.imgur.dto.utils;

import com.imgur.dto.Image;
import lombok.experimental.UtilityClass;

import java.io.File;
import java.io.IOException;

@UtilityClass
public class FileEncodingUtils {

    static final String INPUT_IMAGE_FILE_PATH = Image.NORMAL.path;

    public byte[] getFileContent() {
        File inputFile = new File(INPUT_IMAGE_FILE_PATH);

        byte[] fileContent = new byte[0];
        try {
            fileContent = org.apache.commons.io.FileUtils.readFileToByteArray(inputFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileContent;
    }
}
