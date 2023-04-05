package view;

import config.Config;
import controller.UserController;
import modal.User;

public class Navbar {
    UserController userController = new UserController();
    public Navbar() {
        User user = userController.getUserLogin();
        if(user!=null){
            System.out.println("WELCOME "+user.getRoles()+user.getName());
            System.out.println("1. GO TO PROFILE");
            int chooseMenu = Config.scanner().nextInt();
            switch (chooseMenu){
                case 1:
                    new ProfileView();
                    break;
                case 2:
                    new Navbar();
                    break;
            }
        }else {
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
            switch (chooseMenu){
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