package service.user;

import config.Config;
import modal.User;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UserServiceIMPL implements IUserService{
    List<User> userList = new Config<User>().readFromFile(Config.PATH_USER);

    @Override
    public List<User> findAll() {
        return userList;
    }

    @Override
    public void save(User user) {
        if (findById(user.getId())==null) {
            userList.add(user);
        } else {
            userList.set(userList.indexOf(findById(user.getId())),user);
        }
        new Config<User>().writeToFile(Config.PATH_USER,userList);

    }

    @Override
    public User findById(int id) {
        for (int i = 0; i < userList.size(); i++) {
            if (userList.get(i).getId()==id){
                return userList.get(i);
            }
        }
        return null;
    }

    @Override
    public void deleteById(int id) {
        for (int i = 0; i < userList.size(); i++) {
            if (userList.get(i).getId()==id){
                userList.remove(i);
                new Config<User>().writeToFile(Config.PATH_USER,userList);
            }
        }
    }

    @Override
    public boolean existedByUsername(String username) {
        for (int i = 0; i < userList.size(); i++) {
            if (userList.get(i).getUserName().equals(username)){
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean existedByEmail(String email) {
        for (int i = 0; i < userList.size(); i++) {
            if (userList.get(i).getEmail().equals(email)){
                return true;
            }
        }
        return false;
    }
    @Override
    public boolean checkLogin(String username, String password) {
        List<User> userLogin = new ArrayList<>();
        for (int i = 0; i < userList.size(); i++) {
            if(userList.get(i).getUserName().equals(username)&&userList.get(i).getPassword().equals(password)){
                if (!userList.get(i).isStatus()) {
                    userLogin.add(userList.get(i));
                    new Config<User>().writeToFile(Config.PATH_USER_LOGIN, userLogin);
                    return true;
                }else {
                    System.err.println("Account blocked!");
                }
            }
        }
        return false;
    }

    @Override
    public User getCurentUser() {
        if(new Config<User>().readFromFile(Config.PATH_USER_LOGIN).size()!=0){
            User user = new Config<User>().readFromFile(Config.PATH_USER_LOGIN).get(0);
            return user;
        }
        return null;
    }

    @Override
    public void logOutUser() {
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(Config.PATH_USER_LOGIN);
                fileOutputStream.write(("").getBytes());
                fileOutputStream.close();
            } catch (FileNotFoundException f) {
                System.err.println("File not found!");
            } catch (IOException i) {
                System.err.println("IOE exception!");
            }
        }
}
