package com.PlanMyEvent.util;

import java.io.File;
import java.io.IOException;

import jakarta.servlet.http.Part;

/**
 * Utility class for handling image uploads with unique naming and saving directly into the Eclipse project folder.
 */
public class ImageUtil {

    /**
     * Extracts the original file name from the uploaded Part header.
     *
     * @param part the uploaded file part
     * @return the extracted file name or "download.png" if not found
     */
    public String getImageNameFromPart(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");
        String imageName = null;

        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                imageName = s.substring(s.indexOf("=") + 2, s.length() - 1);
            }
        }

        return (imageName == null || imageName.isEmpty()) ? "download.png" : imageName;
    }

    /**
     * Uploads the image to a specified folder in the Eclipse project and returns a unique filename.
     *
     * @param part       the uploaded file part
     * @param folderName the target folder to save under (e.g., "Profile")
     * @return the unique saved filename or null if failed
     */
    public String uploadImage(Part part, String folderName) {
        String savePath = getSavePath(folderName);
        File fileSaveDir = new File(savePath);

        if (!fileSaveDir.exists() && !fileSaveDir.mkdirs()) {
            System.err.println("Failed to create directory: " + savePath);
            return null;
        }

        try {
            String originalName = getImageNameFromPart(part);
            String uniqueFileName = System.currentTimeMillis() + "_" + originalName;
            String filePath = savePath + File.separator + uniqueFileName;

            part.write(filePath);
            return uniqueFileName;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Returns the full Eclipse file system path to save images.
     */
    public String getSavePath(String folderName) {
        return "C:/Users/ENVY/eclipse-workspace/PlanMyEvents/src/main/webapp/" + folderName;
    }
}
