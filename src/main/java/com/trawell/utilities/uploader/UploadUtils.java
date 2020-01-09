package com.trawell.utilities.uploader;

import java.io.IOException;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import org.springframework.web.multipart.MultipartFile;

/**
 * UploadUtils
 */
public class UploadUtils {

    private static String savedrelPath = "/uploads";
    private static String relPath = "/src/main/resources/static/uploads";
    private static String basePath = System.getProperty("user.dir") + relPath;

    public static String uploadFile(MultipartFile file, String uploadDir, String fileName){

        File pathToSave = new File(basePath+File.separator+uploadDir);
        pathToSave.mkdirs();

        fileName = fileName + "." + getExtensionByStringHandling(file.getOriginalFilename()).get();

        Path fileNameAndPath = Paths.get(pathToSave.toString(),fileName);

        try {
            Files.write(fileNameAndPath, file.getBytes());
        } 
        catch (IOException e) {
            e.printStackTrace();
        }

        return savedrelPath +uploadDir+"/" +fileName;
    }

    public static String uploadPhoto(MultipartFile file, String uploadDir, String fileName){
        String folder = uploadDir.replace("/", "") + "/";
        return uploadFile(file, Paths.get("/photos",folder).toString(), fileName);
    }


    public static Optional<String> getExtensionByStringHandling(String filename) {
        return Optional.ofNullable(filename)
          .filter(f -> f.contains("."))
          .map(f -> f.substring(filename.lastIndexOf(".") + 1));
    }


    public static String getCurrentTimeUsingDate() {
        Date date = new Date();
        String strDateFormat = "hh:mm:ss a";
        DateFormat dateFormat = new SimpleDateFormat(strDateFormat);
        String formattedDate= dateFormat.format(date);

        return formattedDate;
    } 

}