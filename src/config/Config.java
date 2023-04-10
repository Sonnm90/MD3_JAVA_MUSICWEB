package config;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Config<T> {
    public static final String PATH_USER ="src/database/user.csv";
    public static final String PATH_CATEGORY ="src/database/category.csv";
    public static final String PATH_SONG ="src/database/song.csv";
    public static final String PATH_COMMENT ="src/database/comment.csv";
    public static final String PATH_SINGER ="src/database/singer.csv";
    public static final String PATH_BAND ="src/database/band.csv";
    public static final String PATH_USER_LOGIN ="src/database/currentUser.csv";
    public static final String PATH_PLAYLIST ="src/database/playlist.csv";



    public static Scanner scanner(){
        Scanner scanner = new Scanner(System.in);
        return scanner;
    }
    public List<T> readFromFile(String pathFile){
        List<T> tList =new ArrayList<>();
        try{
            FileInputStream fileInputStream=new FileInputStream(pathFile);
            if (fileInputStream.available()!=0) {
                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
                tList = (List<T>) objectInputStream.readObject();
                fileInputStream.close();
                objectInputStream.close();
            }
        } catch (FileNotFoundException f){
            System.err.println("File Not Found!");
        } catch (IOException i){
            System.err.println("IOE Exception!");
        } catch (ClassNotFoundException c){
            System.err.println("Class Not Found!");
        }
        return tList;
    }

    public void writeToFile(String pathFile,List<T>tList){
        File file = new File(pathFile);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            ObjectOutputStream objectOutputStream =new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(tList);
            fileOutputStream.close();
            objectOutputStream.flush();
            objectOutputStream.close();
        } catch (FileNotFoundException f){
            System.err.println("File Not Found!");
        } catch (IOException i){
            System.err.println("IOE Exception!");
        }
    }


    public static boolean validateName(String name) {
        if (name == null || name.trim().isEmpty()) {
            System.out.println("Tên không được để trống.");
            return false;
        }
        if (name.length() > 40) {
            System.out.println("Tên không được quá 40 ký tự.");
            return false;
        }
        return true;
    }
    public static boolean validateUsername(String username) {
        if (username == null || username.trim().isEmpty()) {
            System.out.println("Tên đăng nhập không được để trống.");
            return false;
        }
        if (username.length() > 30) {
            System.out.println("Tên đăng nhập không được quá 40 ký tự.");
            return false;
        }
        if (!username.matches("^[a-zA-Z0-9]+$")) {
            System.out.println("Tên đăng nhập chỉ chứa các ký tự viết liền (a-zA-Z0-9).");
            return false;
        }
        return true;
    }

    public static boolean validateEmail(String email) {
        if (email == null || email.isEmpty()) {
            return false;
        }

        // Định dạng chuẩn của địa chỉ email
        String emailRegex = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";

        // Kiểm tra định dạng của email
        if (!email.matches(emailRegex)) {
            return false;
        }

        return true;
    }

    public static boolean validatePassword(String password) {
        String pattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{6,10}$";
        if (!Pattern.matches(pattern, password)) {
            return false;
        }
        return true;
    }
}