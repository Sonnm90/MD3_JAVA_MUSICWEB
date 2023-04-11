package view;

import config.Config;
import config.InputMethods;
import controller.*;
import dto.response.ResponseMessage;
import modal.*;

import java.util.ArrayList;
import java.util.List;


public class ProfileView {
    UserController userController = new UserController();
    User user = userController.getUserLogin();
    SingerController singerController = new SingerController();
    List<Singer> singerList = singerController.getListSinger();
    SongController songController = new SongController();
    List<Song> songList = songController.getListSong();
    CategoryController categoryController = new CategoryController();
    List<Category> categoryList = categoryController.getListCategory();
    BandController bandController = new BandController();
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
            System.out.println("Old name: "+ user.getName());
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
            System.out.println("Old username: "+ user.getUserName());
            System.out.println("Enter username to update");
            username = Config.scanner().nextLine();
            if (username == null || username.trim().isEmpty()) {
                username = user.getName();
            }
            if (Config.validateName(name)) {
                break;
            }
        }
        System.out.println("Do you want to change: Y/N");
        String choice = Config.scanner().nextLine();
        if (choice.equalsIgnoreCase("y")) {
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
                }
            }
        } else {
            System.out.println(" Enter any key to continue or back to back Menu");
            String back = Config.scanner().nextLine();
            if (back.equalsIgnoreCase("back")) {
                new Navbar();
            } else {
                changeProfile();
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
        System.out.println("Do you want to change: Y/N");
        String choice = Config.scanner().nextLine();
        if (choice.equalsIgnoreCase("y")) {
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
                }
            }
        } else {
            changePassword();
        }
    }

    public void manageSong() {
        System.out.println("1. Creat Song");
        System.out.println("2. Show List Song");
        System.out.println("3. Update Song");
        System.out.println("4. Delete Song");
        System.out.println("5. Back");
        System.out.println("Enter your choice");
        int choice = InputMethods.getInteger();
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
                break;
            default:
                manageSong();
        }

    }

    public void creatSong() {
        int id = 0;
        String name = "";
        Category creatCategory = new Category();
        List<Singer> creatListSinger = new ArrayList<>();
        List<Band> creatListBand = new ArrayList<>();
        List<User> likeUsers = new ArrayList<>();
        List<Comment> commentList = new ArrayList<>();
        Singer newSinger = new Singer();
        Band newBand = new Band();
        int numberOfView = 0;
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
            int choose = InputMethods.getInteger();
            creatCategory = categoryController.detailCategory(choose);
            if (creatCategory != null)
                break;
        }

        while (true) {

            while (true) {
                for (Singer singer : singerList) {
                    System.out.println(singer);
                }
                System.out.println("Enter Id of Singer to choose or Enter to skip");
                String choose = (Config.scanner().nextLine());
                if (!choose.trim().isEmpty() && choose.length() != 0) {
                    int chooseCheck = Integer.parseInt(choose);
                    newSinger = singerController.detailSinger(chooseCheck);
                    if (newSinger != null) {
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
                    newBand = bandController.detailBand(chooseCheck);
                    if (newBand != null) {
                        break;
                    }
                }
                if (choose == null || choose.trim().isEmpty()) {
                    break;
                }

            }

            if (newSinger.getSingerName() != null || newBand.getBandName() != null) {
                Song newSong = new Song(id, name, new ArrayList<>(), creatCategory, new ArrayList<>(), user, numberOfView, likeUsers, commentList);
                if (newSinger.getSingerName() != null) {
                    creatListSinger.add(newSinger);
                    List<Song> updateSongOfSinger = newSinger.getSongsOfSinger();
                    updateSongOfSinger.add(newSong);
                    newSinger.setSongsOfSinger(updateSongOfSinger);
                    newSong.setSingers(creatListSinger);
                    singerController.updateSinger(newSinger);
                }
                if (newBand.getBandName() != null) {
                    creatListBand.add(newBand);
                    List<Song> updateSongOfBand = newBand.getSongsOfBand();
                    updateSongOfBand.add(newSong);
                    newBand.setSongsOfBand(updateSongOfBand);
                    newSong.setBands(creatListBand);
                    bandController.updateBand(newBand);
                }

                System.out.println("Success");
//        songList.add(newSong);
                songController.createSong(newSong);
                break;
            }

        }

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
        while (true) {
            System.out.println("Enter Id of Song to update or any char to back previewMenu");
            String back = Config.scanner().nextLine();
            if (back == null || back.trim().isEmpty() || Character.isLetter(back.charAt(0))) {
                manageSong();
            } else {
                int idUpdate = Integer.parseInt(back);
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
                            song.setCategory(categoryController.detailCategory(choose));
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
                            if (singerController.detailSinger(chooseCheck) != null) {
                                for (int i = 0; i < song.getSingers().size(); i++) {
                                    if (song.getSingers().get(i) == singerController.detailSinger(chooseCheck)) {
                                        break;
                                    }
                                }
                                song.getSingers().add(singerController.detailSinger(chooseCheck));
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
                            if (bandController.detailBand(chooseCheck) != null) {
                                for (int i = 0; i < song.getBands().size(); i++) {
                                    if (song.getBands().get(i) == bandController.detailBand(chooseCheck)) {
                                        break;
                                    }
                                }
                                song.getBands().add(bandController.detailBand(chooseCheck));
                                break;
                            }
                        }
                        if (choose == null || choose.trim().isEmpty()) {
                            break;
                        }
                    }
                    System.out.println("Do you want to update: Y/N");
                    String choice = Config.scanner().nextLine();
                    if (choice.equalsIgnoreCase("y")) {
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
                        updateSong();
                    }
                } else {
                    System.out.println("Id not found!");

                }
            }
        }
    }

    public void deleteSong() {
        for (int i = 0; i < songList.size(); i++) {
            if (songList.get(i).getUser().getId() == user.getId()) {
                System.out.println(songList.get(i));
            }
        }
        while (true) {
            System.out.println("Enter Id of Song to delete or any char to back PreviewMenu");
            String back = Config.scanner().nextLine();
            if (back == null || back.trim().isEmpty() || Character.isLetter(back.charAt(0))) {
                manageSong();
            } else {
                int idDelete = Integer.parseInt(back);
                boolean check = false;
                for (int i = 0; i < songList.size(); i++) {
                    if (songList.get(i).getUser().getId() == user.getId()) {
                        if (songList.get(i).getId() == idDelete) {
                            check = true;
                        }
                    }
                }
                if (check) {
                    System.out.println("Do you want to delete: Y/N");
                    String choice = Config.scanner().nextLine();
                    if (choice.equalsIgnoreCase("y")) {
//                        songList.remove(idDelete);
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
                        deleteSong();
                    }
                } else {
                    System.out.println("Id not found");

                }
            }
        }
    }

    public void playlistManage() {
        System.out.println("1. Create Playlist");
        System.out.println("2. Show Playlist");
        System.out.println("3. Update Playlist");
        System.out.println("4. Delete Playlist");
        System.out.println("5. Add Song to Playlist");
        System.out.println("6. Remove Song of Playlist");
        System.out.println("6. Back!");
        System.out.println("Enter your choice");
        int choice = InputMethods.getInteger();
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
                removeSongOfPlaylist();
                break;
            case 7:
                new Navbar();
            default:
                playlistManage();
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
        while (true) {
            System.out.println("Enter Id of Playlist to update or any key to back previewMenu");
            String back = Config.scanner().nextLine();
            if (back == null || back.trim().isEmpty() || Character.isLetter(back.charAt(0))) {
                playlistManage();
            } else {
                int idUpdate = Integer.parseInt(back);
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
                    System.out.println("Do you want to update: Y/N");
                    String choice = Config.scanner().nextLine();
                    if (choice.equalsIgnoreCase("y")) {
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
                        updatePlaylist();
                    }
                } else {
                    System.out.println("Id not found!");

                }
            }
        }
    }

    public void deletePlaylist() {
        for (int i = 0; i < playLists.size(); i++) {
            if (playLists.get(i).getUser().getId() == user.getId()) {
                System.out.println(playLists.get(i));
            }
        }
        System.out.println("Enter Id of Playlist to delete or any char to back previewMenu");
        String back = Config.scanner().nextLine();
        if (back == null || back.trim().isEmpty() || Character.isLetter(back.charAt(0))) {
            playlistManage();
        } else {
            int idDelete = Integer.parseInt(back);
            boolean check = false;
            for (int i = 0; i < playLists.size(); i++) {
                if (playLists.get(i).getUser().getId() == user.getId()) {
                    if (playLists.get(i).getId() == idDelete) {
                        check = true;
                    }
                }
            }
            if (check) {
                System.out.println("Do you want to delete: Y/N");
                String choice = Config.scanner().nextLine();
                if (choice.equalsIgnoreCase("y")) {
//                    playLists.remove(idDelete);
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
                    deletePlaylist();
                }
            } else {
                System.out.println("Id not found");
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
            PlayList updatePlaylist = new PlayList();
            Song addSong = new Song();
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
                            updatePlaylist = playListController.detailPlaylist(idPlaylist);
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

            addSong = songController.detailSong(idSong);
            List<Song> updateList = updatePlaylist.getPlaylistSongs();
            updateList.add(addSong);
            updatePlaylist.setPlaylistSongs(updateList);
            playListController.updatePlaylist(updatePlaylist);
            System.out.println(updatePlaylist.getPlaylistSongs());
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
    public void removeSongOfPlaylist() {
        List<PlayList> list = new ArrayList<>();
        for (int i = 0; i < playLists.size(); i++) {
            if (playLists.get(i).getUser().getId() == user.getId()) {
                list.add(playLists.get(i));
            }
        }
        if (list.size()>0) {
            while (true) {
                System.out.println("Enter Id of Playlist to remove Song or anu char to back previewMenu");
                String back = Config.scanner().nextLine();
                if (back == null || back.trim().isEmpty() || Character.isLetter(back.charAt(0))) {
                    playlistManage();
                } else {
                    int idUpdate = Integer.parseInt(back);
                    boolean check = false;
                    for (int i = 0; i < list.size(); i++) {
                        if (list.get(i).getId() == idUpdate) {
                            check = true;
                        }
                    }
                    if (check) {
                        PlayList updatePlaylist = playListController.detailPlaylist(idUpdate);
                        for (Song song : updatePlaylist.getPlaylistSongs()) {
                            System.out.println(song);
                        }
                        System.out.println("Enter Id of Song to remove or anu char to back previewMenu");
                        String choice = Config.scanner().nextLine();
                        if (choice == null || back.trim().isEmpty() || Character.isLetter(back.charAt(0))) {
                            removeSongOfPlaylist();
                        } else {
                            boolean checkSong = false;
                            int choiceSong = Integer.parseInt(choice);
                            int index = 0;
                            for (int i = 0; i < updatePlaylist.getPlaylistSongs().size(); i++) {
                                if (updatePlaylist.getPlaylistSongs().get(i).getId() == choiceSong) {
                                    index = i;
                                    checkSong = true;
                                    break;
                                }
                            }
                            if (check) {
                                updatePlaylist.getPlaylistSongs().remove(index);
                            }
                            playListController.updatePlaylist(updatePlaylist);
                            System.out.println("Delete Success");
                            System.out.println("Enter any key to previewMenu or back to back MainMenu");
                            String backMenu = Config.scanner().nextLine();
                            if (backMenu.equalsIgnoreCase("back")) {
                                new Navbar();
                            } else {
                                playlistManage();
                            }
                        }
                    } else {
                        System.out.println("Id not found!");
                    }
                }
            }
        } else {
            System.out.println("You don't have any Playlist");
            System.out.println("Enter any key to previewMenu or back to back MainMenu");
            String backMenu = Config.scanner().nextLine();
            if (backMenu.equalsIgnoreCase("back")) {
                new Navbar();
            } else {
                playlistManage();
            }
        }
    }

    public void logOutUser() {
        userController.logOutUser();
        new Navbar();
    }
}


