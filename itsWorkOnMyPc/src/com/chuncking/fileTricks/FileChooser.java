package com.chuncking.fileTricks;

import java.awt.*;

public class FileChooser {
    public static String getFile(){
        FileDialog dialog = new FileDialog((Frame)null, "Select File to Open");
        dialog.setMode(FileDialog.LOAD);
        dialog.setVisible(true);
        String file = dialog.getDirectory() + dialog.getFile();
        return file;
    }
}
