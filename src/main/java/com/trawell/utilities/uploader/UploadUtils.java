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
 * @author Umberto Russomando, Vincent Milione
 * This class is a utility class which allows us to upload files
 */
public class UploadUtils {

    private static String savedrelPath = "/uploads";
    private static String relPath = "/src/main/resources/static/uploads";
    private static String basePath = System.getProperty("user.dir") + relPath;


    /**
     * This method create all the necessary dirs and save the file into it
     * @param file
     * @param uploadDir
     * @param fileName
     * @return a string containing the path
     */
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

    /**
     * This method upload a photo in the file system
     * @param file
     * @param uploadDir
     * @param fileName
     * @return a string containing the path
     */
    public static String uploadPhoto(MultipartFile file, String uploadDir, String fileName){
        String folder = uploadDir.replace("/", "") + "/";
        return uploadFile(file, Paths.get("/photos",folder).toString(), fileName);
    }

    public static String uploadDocument(MultipartFile file, String uploadDir, String fileName){
        String folder = uploadDir.replace("/", "") + "/";
        return uploadFile(file, Paths.get("/documents",folder).toString(), fileName);
    }

    /**
     * This method returns the extension of the file
     * @param filename
     * @return a string containing the file extention
     */
    public static Optional<String> getExtensionByStringHandling(String filename) {
        return Optional.ofNullable(filename)
          .filter(f -> f.contains("."))
          .map(f -> f.substring(filename.lastIndexOf(".") + 1));
    }

    /**
     * This method is used too get the current time
     * @return a string containing the currente time
     */
    public static String getCurrentTimeUsingDate() {
        Date date = new Date();
        String strDateFormat = "hh:mm:ss a";
        DateFormat dateFormat = new SimpleDateFormat(strDateFormat);
        String formattedDate= dateFormat.format(date);

        return formattedDate;
    } 

}