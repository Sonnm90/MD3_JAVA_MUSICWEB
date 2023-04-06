package view;

import config.Config;
import controller.*;
import dto.response.ResponseMessage;
import modal.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;

public class ProfileView {
    UserController userController = new UserController();
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


    public void changeProfile() {
//        User user = userController.getUserLogin();
        String name = "";
        String username = "";
        while (true) {
            System.out.println("Enter name to update");
            name = Config.scanner().nextLine();
            if (name == null || name.trim().isEmpty()) {
                name = user.getName();
            }
            if (Config.validateName(name)) {
                break;
            }
        }
        while (true) {
            System.out.println("Enter username to update");
            username = Config.scanner().nextLine();
            if (username == null || username.trim().isEmpty()) {
                username = user.getName();
            }
            if (Config.validateName(name)) {
                break;
            }
        }
        user.setName(name);
        user.setUserName(username);
        userController.createUser(user);
        List<User> userLogin = new ArrayList<>();
        userLogin.add(user);
        new Config<User>().writeToFile(Config.PATH_USER_LOGIN, userLogin);
        System.out.println("Change success!");
        while (true) {
            System.out.println("Enter back to back MainMenu");
            String backMenu = Config.scanner().nextLine();
            if (backMenu.equalsIgnoreCase("back")) {
                new Navbar();
                break;
            }
        }
    }

    public void changePassword() {
        String password = "";
        String newPassword = "";
        while (true) {
            System.out.println("Enter oldPassword to check");
            password = Config.scanner().nextLine();
            if (password.equals(user.getPassword())) {
                break;
            } else {
                System.out.println("Enter pass again or nothing to back MainMenu");
                password = Config.scanner().nextLine();
                if (password.equalsIgnoreCase("")) {
                    new Navbar();
                    break;
                } else if (password.equals(user.getPassword())) {
                    break;
                }
            }
        }
        while (true) {
            System.out.println("Enter newPassword to change");
            newPassword = Config.scanner().nextLine();
            if (Config.validatePassword(newPassword)) {
                break;
            }
        }
        user.setPassword(newPassword);
        userController.createUser(user);
        List<User> userLogin = new ArrayList<>();
        userLogin.add(user);
        new Config<User>().writeToFile(Config.PATH_USER_LOGIN, userLogin);
        new ResponseMessage("Change success!");

        while (true) {
            System.out.println("Enter back to back MainMenu");
            String backMenu = Config.scanner().nextLine();
            if (backMenu.equalsIgnoreCase("back")) {
                new Navbar();
                break;
            }
        }
    }

    public void manageSong() {
        System.out.println("1. Creat Song");
        System.out.println("2. Show List Song");
        System.out.println("3. Update Song");
        System.out.println("4. Delete Song");
        System.out.println("5. Back");
        System.out.println("Enter your choice");
        int choice = Config.scanner().nextInt();
        switch (choice) {
            case 1:
                creatSong();
                break;
            case 2:
                showListSong();
                break;
            case 3:
                updateSong();
                break;
            case 4:
                deleteSong();
                break;
            case 5:
                new Navbar();
        }

    }

    public void creatSong() {
        int id = 0;
        String name = "";
        Category creatCategory = new Category();
        List<Singer> creatListSinger = new ArrayList<>();
        List<Band> creatListBand = new ArrayList<>();
        User creatUser = user;
        if (songList.size() == 0) {
            id = 1;
        } else {
            id = songList.get(songList.size() - 1).getId() + 1;
        }

        while (true) {
            System.out.println("Enter name of Song");
            name = Config.scanner().nextLine();
            if (name == null || name.trim().isEmpty()) {
                new ResponseMessage("Please enter name of Song");
            } else {
                break;
            }
        }
        while (true) {
            for (Category category : categoryList) {
                System.out.println(category);
            }
            System.out.println("Enter Id of Category to choose");
            int choose = Integer.parseInt(Config.scanner().nextLine());
            creatCategory = categoryList.get(choose);
            if (creatCategory != null)
                break;
        }

        while (true) {
            Singer newSinger = new Singer();
            Band newBand = new Band();
            while (true) {
                for (Singer singer : singerList) {
                    System.out.println(singer);
                }
                System.out.println("Enter Id of Singer to choose or Enter to skip");
                String choose = (Config.scanner().nextLine());
                if (!choose.trim().isEmpty() && choose.length() != 0) {
                    int chooseCheck = Integer.parseInt(choose);
                    newSinger = singerList.get(chooseCheck);
                    if (singerList.get(chooseCheck) != null) {
                        break;
                    }
                }
                if (choose == null || choose.trim().isEmpty()) {
                    break;
                }

            }
            while (true) {
                for (Band band : bandList) {
                    System.out.println(band);
                }
                System.out.println("Enter Id of Band to choose or Enter to skip");
                String choose = (Config.scanner().nextLine());
                if (!choose.trim().isEmpty() && choose.length() != 0) {
                    int chooseCheck = Integer.parseInt(choose);
                    newBand = bandList.get(chooseCheck);
                    if (bandList.get(chooseCheck) != null) {
                        break;
                    }
                }
                if (choose == null || choose.trim().isEmpty()) {
                    break;
                }

            }

            if (newSinger.getSingerName() != null || newBand.getBandName() != null) {
                if (newSinger.getSingerName() != null) {
                    creatListSinger.add(newSinger);
                }
                if (newBand.getBandName() != null) {
                    creatListBand.add(newBand);
                }
                System.out.println("Success");
                break;
            }

        }
        Song newSong = new Song(id, name, creatListSinger, creatCategory, creatListBand, user);
//        songList.add(newSong);
        songController.createSong(newSong);
        System.out.println(" Create Success");
        System.out.println("Enter any key to previewMenu or back to back MainMenu");
        String backMenu = Config.scanner().nextLine();
        if (backMenu.equalsIgnoreCase("back")) {
            new Navbar();
        } else {
            manageSong();
        }
    }

    public List<Song> showListSong() {
        List<Song> list = new ArrayList<>();
        for (int i = 0; i < songList.size(); i++) {
            if (songList.get(i).getUser().getId() == user.getId()) {
                list.add(songList.get(i));
            }
        }
        if (list.size() != 0) {
            for (Song song : list
            ) {
                System.out.println(song);
            }
        } else {
            System.out.println("User don't have ownerSong");
        }
        System.out.println("Enter any key to previewMenu or back to back MainMenu");
        String backMenu = Config.scanner().nextLine();
        if (backMenu.equalsIgnoreCase("back")) {
            new Navbar();
        } else {
            manageSong();
        }
        return list;
    }

    public void updateSong() {
        for (int i = 0; i < songList.size(); i++) {
            if (songList.get(i).getUser().getId() == user.getId()) {
                System.out.println(songList.get(i));
            }
        }
        System.out.println("Enter Id of Song to update");
        int idUpdate = Config.scanner().nextInt();
        boolean check = false;
        for (int i = 0; i < songList.size(); i++) {
            if (songList.get(i).getUser().getId() == user.getId()) {
                if (songList.get(i).getId() == idUpdate) {
                    check = true;
                }
            }
        }
        if (check) {
            Song song = songController.detailSong(idUpdate);

            while (true) {
                System.out.println("Enter name of Song");
                String name = Config.scanner().nextLine();
//                if (name == null || name.trim().isEmpty()) {
//                    new ResponseMessage("Please enter name of Song");
//                } else {
//                    song.setName(name);
//                    break;
//                }
                if (name.length() != 0) {
                    song.setName(name);
                    break;
                }
            }
            while (true) {
                for (Category category : categoryList) {
                    System.out.println(category);
                }
                System.out.println("Enter Id of Category to choose");
                int choose = Integer.parseInt(Config.scanner().nextLine());
                if (categoryList.get(choose) != null)
                    song.setCategory(categoryList.get(choose));
                break;
            }
            while (true) {
                for (Singer singer : singerList) {
                    System.out.println(singer);
                }
                System.out.println("Enter Id of Singer to choose or Enter to skip");
                String choose = (Config.scanner().nextLine());
                if (!choose.trim().isEmpty() && choose.length() != 0) {
                    int chooseCheck = Integer.parseInt(choose);
                    if (singerList.get(chooseCheck) != null) {
                        for (int i = 0; i < song.getSingers().size(); i++) {
                            if (song.getSingers().get(i) == singerList.get(chooseCheck)) {
                                break;
                            }
                        }
                        song.getSingers().add(singerList.get(chooseCheck));
                        break;
                    }
                }
                if (choose == null || choose.trim().isEmpty()) {
                    break;
                }
            }
            while (true) {
                for (Band band : bandList) {
                    System.out.println(band);
                }

                System.out.println("Enter Id of Singer to choose or Enter to skip");
                String choose = (Config.scanner().nextLine());
                if (!choose.trim().isEmpty() && choose.length() != 0) {
                    int chooseCheck = Integer.parseInt(choose);
                    if (bandList.get(chooseCheck) != null) {
                        for (int i = 0; i < song.getBands().size(); i++) {
                            if (song.getBands().get(i) == bandList.get(chooseCheck)) {
                                break;
                            }
                        }
                        song.getBands().add(bandList.get(chooseCheck));
                        break;
                    }
                }
                if (choose == null || choose.trim().isEmpty()) {
                    break;
                }
            }
//            songList.set(idUpdate, song);
            songController.updateSong(song);
            System.out.println(" Update Success");
            System.out.println("Enter any key to previewMenu or back to back MainMenu");
            String backMenu = Config.scanner().nextLine();
            if (backMenu.equalsIgnoreCase("back")) {
                new Navbar();
            } else {
                manageSong();
            }
        } else {
            System.out.println("Id not found!");
            System.out.println("Enter any key to try agian or back to back previewMenu");
            String backMenu = Config.scanner().nextLine();
            if (backMenu.equalsIgnoreCase("back")) {
                manageSong();
            } else {
                updateSong();
            }
        }

    }

    public void deleteSong() {
        for (int i = 0; i < songList.size(); i++) {
            if (songList.get(i).getUser().getId() == user.getId()) {
                System.out.println(songList.get(i));
            }
        }
        System.out.println("Enter Id of Song to delete");
        int idDelete = Config.scanner().nextInt();
        boolean check = false;
        for (int i = 0; i < songList.size(); i++) {
            if (songList.get(i).getUser().getId() == user.getId()) {
                if (songList.get(i).getId() == idDelete) {
                    check = true;
                }
            }
        }
        if (check) {
            songList.remove(idDelete);
            songController.deleteSong(idDelete);
            System.out.println("Delete Success");
            System.out.println("Enter any key to previewMenu or back to back MainMenu");
            String backMenu = Config.scanner().nextLine();
            if (backMenu.equalsIgnoreCase("back")) {
                new Navbar();
            } else {
                manageSong();
            }
        } else {
            System.out.println("Id not found");
            System.out.println("Enter any key to try again or back to back previewMenu");
            String backMenu = Config.scanner().nextLine();
            if (backMenu.equalsIgnoreCase("back")) {
                manageSong();
            } else {
                deleteSong();
            }
        }
    }

    public void playlistManage() {
        System.out.println("1. Create Playlist");
        System.out.println("2. Show Playlist");
        System.out.println("3. Update Playlist");
        System.out.println("4. Delete Playlist");
        System.out.println("5. Add Song to Playlist");
        System.out.println("6. Back!");
        System.out.println("Enter your choice");
        int choice = Config.scanner().nextInt();
        switch (choice) {
            case 1:
                createPlaylist();
                break;
            case 2:
                showPlaylist();
                break;
            case 3:
                updatePlaylist();
                break;
            case 4:
                deletePlaylist();
                break;
            case 5:
                addSongToPlaylistBySongId();
                break;
            case 6:
                new Navbar();
        }
    }

    public void createPlaylist() {
        int id = 0;
        String name = "";
        boolean status;
        String roleName = "";
        if (playLists.size() == 0) {
            id = 1;
        } else {
            id = playLists.get(playLists.size() - 1).getId() + 1;
        }
        while (true) {
            System.out.println("Enter name of Playlist:");
            name = Config.scanner().nextLine();
            if (name == null || name.trim().isEmpty()) {
                System.err.println("Please enter name of Playlist");
            } else {
                break;
            }
        }
        for (Role role : user.getRoles()) {
            roleName = String.valueOf(role.getName());
        }
        if (roleName.equalsIgnoreCase("user")) {
            status = false;
        } else {
            status = true;
        }
        PlayList newPlaylist = new PlayList(id, name, new ArrayList<>(), user, status);
//        playLists.add(newPlaylist);
        playListController.creatPlaylist(newPlaylist);
        System.out.println("Creat Success");
        System.out.println("Enter any key to previewMenu or back to back MainMenu");
        String backMenu = Config.scanner().nextLine();
        if (backMenu.equalsIgnoreCase("back")) {
            new Navbar();
        } else {
            playlistManage();
        }
    }

    public List<PlayList> showPlaylist() {
        List<PlayList> list = new ArrayList<>();
        for (int i = 0; i < playLists.size(); i++) {
            if (playLists.get(i).getUser().getId() == user.getId()) {
                list.add(playLists.get(i));
            }
        }
        if (list.size() != 0) {
            for (PlayList playList : list
            ) {
                System.out.println(playList);
            }
        } else {
            System.out.println("You don't have ownerPlaylist");
        }
        System.out.println("Enter any key to previewMenu or back to back MainMenu");
        String backMenu = Config.scanner().nextLine();
        if (backMenu.equalsIgnoreCase("back")) {
            new Navbar();
        } else {
            playlistManage();
        }
        return list;
    }

    public void updatePlaylist() {
        for (int i = 0; i < playLists.size(); i++) {
            if (playLists.get(i).getUser().getId() == user.getId()) {
                System.out.println(playLists.get(i));
            }
        }
        System.out.println("Enter Id of Playlist to update");
        int idUpdate = Config.scanner().nextInt();
        boolean check = false;
        for (int i = 0; i < playLists.size(); i++) {
            if (playLists.get(i).getUser().getId() == user.getId()) {
                if (playLists.get(i).getId() == idUpdate) {
                    check = true;
                }
            }
        }
        if (check) {
            PlayList updatePlaylist = playListController.detailPlaylist(idUpdate);
            while (true) {
                System.out.println("Enter name of Playlist to update");
                String name = Config.scanner().nextLine();
                if (name.length() != 0) {
                    updatePlaylist.setPlaylistName(name);
                    break;
                }
            }
//            playLists.add(idUpdate,updatePlaylist);
            playListController.updatePlaylist(updatePlaylist);
            System.out.println("Update Success");
            System.out.println("Enter any key to previewMenu or back to back MainMenu");
            String backMenu = Config.scanner().nextLine();
            if (backMenu.equalsIgnoreCase("back")) {
                new Navbar();
            } else {
                playlistManage();
            }
        } else {
            System.out.println("Id not found!");
            System.out.println("Enter any key to try again or back to back previewMenu");
            String backMenu = Config.scanner().nextLine();
            if (backMenu.equalsIgnoreCase("back")) {
                updatePlaylist();
            } else {
                playlistManage();
            }
        }
    }

    public void deletePlaylist() {
        for (int i = 0; i < playLists.size(); i++) {
            if (playLists.get(i).getUser().getId() == user.getId()) {
                System.out.println(playLists.get(i));
            }
        }
        System.out.println("Enter Id of Playlist to delete");
        int idDelete = Config.scanner().nextInt();
        boolean check = false;
        for (int i = 0; i < playLists.size(); i++) {
            if (playLists.get(i).getUser().getId() == user.getId()) {
                if (playLists.get(i).getId() == idDelete) {
                    check = true;
                }
            }
        }
        if (check) {
            playLists.remove(idDelete);
            playListController.deletePlaylist(idDelete);
            System.out.println("Delete Success");
            System.out.println("Enter any key to previewMenu or back to back MainMenu");
            String backMenu = Config.scanner().nextLine();
            if (backMenu.equalsIgnoreCase("back")) {
                new Navbar();
            } else {
                playlistManage();
            }
        } else {
            System.out.println("Id not found");
            System.out.println("Enter any key to try again or back to back previewMenu");
            String backMenu = Config.scanner().nextLine();
            if (backMenu.equalsIgnoreCase("back")) {
                playlistManage();
            } else {
                deletePlaylist();
            }
        }
    }

    public void addSongToPlaylistBySongId() {
        List<PlayList> list = new ArrayList<>();
        for (int i = 0; i < playLists.size(); i++) {
            if (playLists.get(i).getUser().getId() == user.getId()) {
                list.add(playLists.get(i));
            }
        }
        if (list.size() != 0) {
            boolean check = false;
            boolean checkSong = false;
            int idPlaylist;
            int idSong;
            while (true) {
                for (PlayList playList : list) {
                    System.out.println(playList);
                }
                System.out.println("Enter Id of Playlist which you want to add Song");
                idPlaylist = Config.scanner().nextInt();
                for (int i = 0; i < playLists.size(); i++) {
                    if (playLists.get(i).getUser().getId() == user.getId()) {
                        if (playLists.get(i).getId() == idPlaylist) {
                            check = true;
                            break;
                        }
                    }
                }
                if (!check) {
                    System.out.println("Id not found! Please try again");
                } else {
                    break;
                }
            }
            while (true) {
                for (Song song : songController.getListSong()) {
                    System.out.println(song);
                }
                System.out.println("Enter Id of Song to add");
                idSong = Config.scanner().nextInt();
                for (int j = 0; j < songList.size(); j++) {
                    if (songList.get(j).getId() == idSong) {
                        checkSong = true;
                        break;
                    }
                }
                if (checkSong) {
                    break;
                } else {
                    System.out.println("Id not found! Please try again.");
                }
            }
//            playLists.get(idPlaylist).getPlaylistSongs().add(songList.get(idSong));
            playListController.addSongToPlaylistBySongId(playLists.get(idPlaylist), idSong);
            System.out.println(playLists.get(idPlaylist).getPlaylistSongs());
            System.out.println("Add Success");
            System.out.println("Enter any key to previewMenu or back to back MainMenu");
            String backMenu = Config.scanner().nextLine();
            if (backMenu.equalsIgnoreCase("back")) {
                new Navbar();
            } else {
                playlistManage();
            }
        } else {
            System.out.println("You don't have ownerPlaylist");
            System.out.println("Enter any key to try again or back to back previewMenu");
            String backMenu = Config.scanner().nextLine();
            if (backMenu.equalsIgnoreCase("back")) {
                playlistManage();
            } else {
                addSongToPlaylistBySongId();
            }
        }

    }
}


