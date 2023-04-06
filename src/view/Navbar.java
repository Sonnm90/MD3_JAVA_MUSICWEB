package view;

import config.Config;
import controller.UserController;
import modal.Role;
import modal.User;

import java.sql.SQLOutput;

public class Navbar {
    UserController userController = new UserController();

    public Navbar() {
        User user = userController.getUserLogin();
        String roleName="";
        if (user != null) {
            for (Role role: user.getRoles()
                 ) {roleName= String.valueOf(role.getName());
            }
             if (roleName.equalsIgnoreCase("user")){
                System.out.println("WELCOME " + roleName + " " + user.getName().toUpperCase());
                System.out.println("1. CHANGE PROFILE");
                System.out.println("2. CHANGE PASSWORD");
                System.out.println("3. SONG MANAGE");
                System.out.println("4. PLAYLIST MANAGE");
                System.out.println("5. LOGOUT");
                 System.out.println("Enter your choose:");
                int chooseMenu = Config.scanner().nextInt();
                switch (chooseMenu) {
                    case 1:
                        System.out.println("WELCOME " + roleName + " " + user.getName().toUpperCase());
                        new ProfileView().changeProfile();
                        break;
                    case 2:
                        System.out.println("WELCOME " + roleName + " " + user.getName().toUpperCase());
                        new ProfileView().changePassword();
                        break;
                    case 3:
                        System.out.println("WELCOME " + roleName + " " + user.getName().toUpperCase());
                        new ProfileView().manageSong();
                        break;
                    case 4:
                        System.out.println("WELCOME " + roleName + " " + user.getName().toUpperCase());
                        new ProfileView().playlistManage();
                        break;
                }
                } else if (roleName.equalsIgnoreCase("PM")){
                 System.out.println("PM");
             }else {
                 System.out.println("Admin");
             }

        } else {
            System.out.println("WELCOME TO WEBSITE MUSIC");
            System.out.println("==========================");
            System.out.println("1. LIST SINGER");
            System.out.println("2. LIST BAND");
            System.out.println("3. CATEGORY MUSIC");
            System.out.println("4. LIST SONG");
            System.out.println("5. ALL PLAYLIST");
            System.out.println("6. REGISTER");
            System.out.println("7. LOGIN");
            System.out.println("Enter your choose");
            int chooseMenu = Config.scanner().nextInt();
            switch (chooseMenu) {
                case 1:
                    new GuestView().showListSinger();
                    break;
                case 2:
                    new GuestView().showListBand();
                    break;
                case 3:
                    new GuestView().showListCategory();
                    break;
                case 4:
                    new GuestView().showListSong();
                    break;
                case 5:
                    new GuestView().showAllPlaylist();
                    break;
                case 6:
                    new UserView().formRegister();
                    break;
                case 7:
                    new UserView().formLogin();
                    break;
            }
        }

    }

    public static void main(String[] args) {
        new Navbar();

    }
}