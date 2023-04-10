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
        String roleName = "";
        if (user != null) {
            for (Role role : user.getRoles()
            ) {
                roleName = String.valueOf(role.getName());
            }
            if (roleName.equalsIgnoreCase("user")) {
                System.out.println("WELCOME " + roleName + " " + user.getName().toUpperCase());
                System.out.println("1. CHANGE PROFILE");
                System.out.println("2. CHANGE PASSWORD");
                System.out.println("3. SONG MANAGEMENT");
                System.out.println("4. PLAYLIST MANAGEMENT");
                System.out.println("5. SHOW ALL SONG");
                System.out.println("6. LOGOUT");
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
                    case 5:
                        System.out.println("WELCOME " + roleName + " " + user.getName().toUpperCase());
                        new GuestView().showListSong();
                        break;
                    case 6:
                        new ProfileView().logOutUser();
                        break;

                }
            } else if (roleName.equalsIgnoreCase("PM")) {
                System.out.println("WELCOME " + roleName + " " + user.getName().toUpperCase());
                System.out.println("1. CHANGE PROFILE");
                System.out.println("2. CHANGE PASSWORD");
                System.out.println("3. SONG MANAGEMENT");
                System.out.println("4. PLAYLIST MANAGEMENT");
                System.out.println("5. USER MANAGEMENT");
                System.out.println("6. SHOW ALL SONG");
                System.out.println("7. LOGOUT");
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
                        new AdminView().manageSong();
                        break;
                    case 4:
                        System.out.println("WELCOME " + roleName + " " + user.getName().toUpperCase());
                        new AdminView().playlistManage();
                        break;
                    case 5:
                        System.out.println("WELCOME " + roleName + " " + user.getName().toUpperCase());
                        new PMView().manageUser();
                        break;
                    case 6:
                        System.out.println("WELCOME " + roleName + " " + user.getName().toUpperCase());
                        new GuestView().showListSong();
                        break;
                    case 7:
                        new ProfileView().logOutUser();
                        break;

                }

            } else {
                System.out.println("WELCOME " + roleName + " " + user.getName().toUpperCase());
                System.out.println("1. CHANGE PROFILE");
                System.out.println("2. CHANGE PASSWORD");
                System.out.println("3. SONG MANAGEMENT");
                System.out.println("4. PLAYLIST MANAGEMENT");
                System.out.println("5. USER MANAGEMENT");
                System.out.println("6. SHOW ALL SONG");
                System.out.println("7. BAND MANAGEMENT");
                System.out.println("8. SINGER MANAGEMENT");
                System.out.println("9. CATEGORY MANAGMENT");
                System.out.println("10. LOGOUT");
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
                        new AdminView().manageSong();
                        break;
                    case 4:
                        System.out.println("WELCOME " + roleName + " " + user.getName().toUpperCase());
                        new AdminView().playlistManage();
                        break;
                    case 5:
                        System.out.println("WELCOME " + roleName + " " + user.getName().toUpperCase());
                        new AdminView().manageUser();
                        break;
                    case 6:
                        System.out.println("WELCOME " + roleName + " " + user.getName().toUpperCase());
                        new GuestView().showListSong();
                        break;
                    case 7:
                        System.out.println("WELCOME " + roleName + " " + user.getName().toUpperCase());
                        new AdminView().manageBand();
                        break;
                    case 8:
                        System.out.println("WELCOME " + roleName + " " + user.getName().toUpperCase());
                        new AdminView().manageSinger();
                        break;
                    case 9:
                        System.out.println("WELCOME " + roleName + " " + user.getName().toUpperCase());
                        new AdminView().manageCategory();
                        break;
                    case 10:
                        new ProfileView().logOutUser();
                        break;
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
            }
        }

    }

    public static void main(String[] args) {
        new Navbar();

    }
}