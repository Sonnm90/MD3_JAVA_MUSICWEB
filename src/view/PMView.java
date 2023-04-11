package view;

import config.Config;
import config.InputMethods;
import controller.*;
import dto.response.ResponseMessage;
import modal.*;

import java.util.ArrayList;
import java.util.List;

public class PMView {
    UserController userController = new UserController();
    List<User> userList = userController.getListUser();
    User user = userController.getUserLogin();
    SingerController singerController = new SingerController();
    List<Singer> singerList = singerController.getListSinger();
    SongController songController = new SongController();
    List<Song> songList = songController.getListSong();
    CategoryController categoryController = new CategoryController();
    List<Category> categoryList = categoryController.getListCategory();
    List<Band> bandList = new BandController().getListBand();
    PlayListController playListController = new PlayListController();
    List<PlayList> playLists = playListController.getListPlaylist();
//    List<Song> songList = new Config<Song>().readFromFile(Config.PATH_SONG);
//    List<Singer> singerList = new Config<Singer>().readFromFile(Config.PATH_SINGER);
//    List<Band> bandList = new Config<Band>().readFromFile(Config.PATH_BAND);
//    List<Category> categoryList = new Config<Category>().readFromFile(Config.PATH_CATEGORY);
//    List<PlayList> playLists = new Config<PlayList>().readFromFile(Config.PATH_PLAYLIST);

      public void manageUser() {
        System.out.println("1. Block User");
        System.out.println("2. Delete User");
        System.out.println("3. Back");
        System.out.println(" Enter your choice:");
        int choice = InputMethods.getInteger();
        switch (choice) {
            case 1:
                changeStatusUser();
                break;
            case 2:
                deleteUser();
                break;
            case 3:
                new Navbar();
                break;
            default:
                manageUser();
        }
    }

    public void changeStatusUser() {
        for (User user : userList) {
            if (!String.valueOf(user.getRoles()).equalsIgnoreCase("admin")) {
                System.out.println(user.getUserName() + "  " + user.isStatus());
            }
        }
        while (true) {
            System.out.println("Enter Id of User to change or any char to back previewMenu");
            String back = Config.scanner().nextLine();
            if (back == null || back.trim().isEmpty() || Character.isLetter(back.charAt(0))) {
                manageUser();
            } else {
                int idBlock = Integer.parseInt(back);
                if (userController.detailUser(idBlock)==null) {
                    System.out.println("Id not found! Try again");
                } else {
                    User blockUser = userController.detailUser(idBlock);
                    if (blockUser != null || String.valueOf(blockUser.getRoles()).equalsIgnoreCase("admin")) {
                        System.out.println("Id not found! Please try again!");
                    } else {
                        System.out.println("Do you want to change: Y/N");
                        String choice = Config.scanner().nextLine();
                        if (choice.equalsIgnoreCase("y")) {
                            blockUser.setStatus(!blockUser.isStatus());
                            userController.createUser(blockUser);
                            System.out.println("Change Status Success!");
                            System.out.println("Enter any key to previewMenu or back to back MainMenu");
                            String backMenu = Config.scanner().nextLine();
                            if (backMenu.equalsIgnoreCase("back")) {
                                new Navbar();
                            } else {
                                new AdminView().playlistManage();
                            }
                        } else {
                            changeStatusUser();
                        }
                    }
                }
            }
        }
    }

    public void deleteUser() {
        for (User user : userList) {
            if (!String.valueOf(user.getRoles()).equalsIgnoreCase("admin")) {
                System.out.println(user.getUserName() + "  " + user.getId() + " " + user.isStatus());
            }
        }
        while (true) {
            System.out.println("Enter Id of User to delete or any char to back previewMenu");
            String back = Config.scanner().nextLine();
            if (back == null || back.trim().isEmpty() || Character.isLetter(back.charAt(0))) {
                manageUser();
            } else {
                int idDelete = Integer.parseInt(back);
                if (userController.detailUser(idDelete)==null) {
                    System.out.println("Id not found! Try again");
                } else {
                    User deleteUser = userController.detailUser(idDelete);
                    if (deleteUser != null || String.valueOf(deleteUser.getRoles()).equalsIgnoreCase("pm") || String.valueOf(deleteUser.getRoles()).equalsIgnoreCase("admin")) {
                        System.out.println("Id not found! Please try again!");
                    } else {
                        System.out.println("Do you want to delete: Y/N");
                        String choice = Config.scanner().nextLine();
                        if (choice.equalsIgnoreCase("y")) {
                            userController.deleteUser(idDelete);
                            System.out.println("Delete Success");
                            System.out.println("Enter any key to previewMenu or back to back MainMenu");
                            String backMenu = Config.scanner().nextLine();
                            if (backMenu.equalsIgnoreCase("back")) {
                                new Navbar();
                            } else {
                                new AdminView().playlistManage();
                            }
                        } else {
                            deleteUser();
                        }
                    }
                }
            }
        }
    }
}
