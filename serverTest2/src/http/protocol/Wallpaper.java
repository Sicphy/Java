package http.protocol;

import java.io.*;

public class Wallpaper {
    private File folder = null;

    Wallpaper(String folderName) {
        this.folder = new File(folderName);
    }


    public boolean isExist(String imageName) {
        File[] listOfFiles = folder.listFiles();

        for(int i=0; i<listOfFiles.length; i++) {
            if(listOfFiles[i].getName().equals(imageName + ".jpg")) {
                return true;
            }
        }
        return false;
    }

    public String getWallpapers() {
        File[] listOfFiles = folder.listFiles();
        String wallpapersNames = new String();


        for(int i=0; i<listOfFiles.length; i++) {
            wallpapersNames += listOfFiles[i].getName().substring(0, listOfFiles[i].getName().lastIndexOf('.'));
            wallpapersNames += " ";
        }

        return wallpapersNames;
    }
}
