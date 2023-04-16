package view;

import config.Config;
import config.InputMethods;
import controller.UserController;
import modal.Role;
import modal.User;

import java.sql.SQLOutput;

public class Navbar {
    UserController userController = new UserController();

    public Navbar() {
        User user = userController.getUserLogin();
        String roleName = "";
        if (user != null) {
            for (Role role : user.getRoles()
            ) {
                roleName = String.valueOf(role.getName());
            }
            if (roleName.equalsIgnoreCase("user")) {
                System.out.println("WELCOME " + roleName + " " + user.getName().toUpperCase());
                System.out.println("1. ACCOUNT SETTING");
                System.out.println("2. SONG MANAGEMENT");
                System.out.println("3. PLAYLIST MANAGEMENT");
                System.out.println("4. SHOW ALL SONG");
                System.out.println("5. MY PLAYLIST MANAGEMENT");
                System.out.println("6. LOGOUT");
                System.out.println("Enter your choose:");
                int chooseMenu = InputMethods.getInteger();
                switch (chooseMenu) {
                    case 1:
                        System.out.println("WELCOME " + roleName + " " + user.getName().toUpperCase());
                        new ProfileView().manageAccount();
                        break;
                    case 2:
                        System.out.println("WELCOME " + roleName + " " + user.getName().toUpperCase());
                        new ProfileView().manageSong();
                        break;
                    case 3:
                        System.out.println("WELCOME " + roleName + " " + user.getName().toUpperCase());
                        new ProfileView().playlistManage();
                        break;
                    case 4:
                        System.out.println("WELCOME " + roleName + " " + user.getName().toUpperCase());
                        new GuestView().showListSong();
                        break;
                    case 5:
                        System.out.println("WELCOME " + roleName + " " + user.getName().toUpperCase());
                        new AdminView().manageMyPlaylist();
                        break;
                    case 6:
                        new ProfileView().logOutUser();
                        break;
                    default:
                        new Navbar();

                }
            } else if (roleName.equalsIgnoreCase("PM")) {
                System.out.println("WELCOME " + roleName + " " + user.getName().toUpperCase());
                System.out.println("1. ACCOUNT SETTING");
                System.out.println("2. SONG MANAGEMENT");
                System.out.println("3. PLAYLIST MANAGEMENT");
                System.out.println("4. USER MANAGEMENT");
                System.out.println("5. SHOW ALL SONG");
                System.out.println("6. MY PLAYLIST MANAGEMENT");
                System.out.println("7. LOGOUT");
                System.out.println("Enter your choose:");
                int chooseMenu = InputMethods.getInteger();
                switch (chooseMenu) {
                    case 1:
                        System.out.println("WELCOME " + roleName + " " + user.getName().toUpperCase());
                        new ProfileView().manageAccount();
                        break;
                    case 2:
                        System.out.println("WELCOME " + roleName + " " + user.getName().toUpperCase());
                        new AdminView().manageSong();
                        break;
                    case 3:
                        System.out.println("WELCOME " + roleName + " " + user.getName().toUpperCase());
                        new AdminView().playlistManage();
                        break;
                    case 4:
                        System.out.println("WELCOME " + roleName + " " + user.getName().toUpperCase());
                        new PMView().manageUser();
                        break;
                    case 5:
                        System.out.println("WELCOME " + roleName + " " + user.getName().toUpperCase());
                        new GuestView().showListSong();
                        break;
                    case 6:
                        System.out.println("WELCOME " + roleName + " " + user.getName().toUpperCase());
                        new AdminView().manageMyPlaylist();
                        break;
                    case 7:
                        new ProfileView().logOutUser();
                        break;
                    default:
                        new Navbar();

                }

            } else {
                System.out.println("WELCOME " + roleName + " " + user.getName().toUpperCase());
                System.out.println("1. ACCOUNT SETTING");
                System.out.println("2. SONG MANAGEMENT");
                System.out.println("3. PLAYLIST MANAGEMENT");
                System.out.println("4. USER MANAGEMENT");
                System.out.println("5. SHOW ALL SONG");
                System.out.println("6. BAND MANAGEMENT");
                System.out.println("7. SINGER MANAGEMENT");
                System.out.println("8. CATEGORY MANAGEMENT");
                System.out.println("9. MY PLAYLIST MANAGEMENT");
                System.out.println("10. LOGOUT");
                System.out.println("Enter your choose:");
                int chooseMenu = InputMethods.getInteger();
                switch (chooseMenu) {
                    case 1:
                        System.out.println("WELCOME " + roleName + " " + user.getName().toUpperCase());
                        new ProfileView().manageAccount();
                        break;
                    case 2:
                        System.out.println("WELCOME " + roleName + " " + user.getName().toUpperCase());
                        new AdminView().manageSong();
                        break;
                    case 3:
                        System.out.println("WELCOME " + roleName + " " + user.getName().toUpperCase());
                        new AdminView().playlistManage();
                        break;
                    case 4:
                        System.out.println("WELCOME " + roleName + " " + user.getName().toUpperCase());
                        new AdminView().manageUser();
                        break;
                    case 5:
                        System.out.println("WELCOME " + roleName + " " + user.getName().toUpperCase());
                        new GuestView().showListSong();
                        break;
                    case 6:
                        System.out.println("WELCOME " + roleName + " " + user.getName().toUpperCase());
                        new AdminView().manageBand();
                        break;
                    case 7:
                        System.out.println("WELCOME " + roleName + " " + user.getName().toUpperCase());
                        new AdminView().manageSinger();
                        break;
                    case 8:
                        System.out.println("WELCOME " + roleName + " " + user.getName().toUpperCase());
                        new AdminView().manageCategory();
                        break;
                    case 9:
                        System.out.println("WELCOME " + roleName + " " + user.getName().toUpperCase());
                        new AdminView().manageMyPlaylist();
                        break;
                    case 10:
                        new ProfileView().logOutUser();
                        break;
                    default:
                        new Navbar();
                }
            }
        } else {
            System.out.println("WELCOME TO WEBSITE MUSIC");
            System.out.println("==========================");
            System.out.println("1. LIST SINGER");
            System.out.println("2. LIST BAND");
            System.out.println("3. CATEGORY MUSIC");
            System.out.println("4. LIST SONG");
            System.out.println("5. ALL PLAYLIST");
            System.out.println("6. TOP VIEW MUSIC");
            System.out.println("7. TOP LIKE MUSIC");
            System.out.println("8. REGISTER");
            System.out.println("9. LOGIN");
            System.out.println("Enter your choose");
            int chooseMenu = InputMethods.getInteger();
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
                    new GuestView().showTopViewSong();
                    break;
                case 7:
                    new GuestView().showTopLikeSong();
                    break;
                case 8:
                    new UserView().formRegister();
                    break;
                case 9:
                    new UserView().formLogin();
                    break;
                default:
                    new Navbar();
            }
        }

    }

    public static void main(String[] args) {
        new Navbar();

    }
}