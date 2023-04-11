package view;

import config.Config;
import config.InputMethods;
import controller.*;
import dto.response.ResponseMessage;
import modal.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AdminView {
    UserController userController = new UserController();
    List<User> userList = userController.getListUser();
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
    MyPlaylistController myPlaylistController = new MyPlaylistController();
    List<MyPlaylist> myPlaylists = myPlaylistController.getListMyPlaylist();
//    List<Song> songList = new Config<Song>().readFromFile(Config.PATH_SONG);
//    List<Singer> singerList = new Config<Singer>().readFromFile(Config.PATH_SINGER);
//    List<Band> bandList = new Config<Band>().readFromFile(Config.PATH_BAND);
//    List<Category> categoryList = new Config<Category>().readFromFile(Config.PATH_CATEGORY);
//    List<PlayList> playLists = new Config<PlayList>().readFromFile(Config.PATH_PLAYLIST);

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
                new ProfileView().creatSong();
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
            default:
                manageSong();
        }

    }


    public List<Song> showListSong() {
        if (songList.size() > 0) {
            for (Song song : songList) {
                System.out.println(song);
            }
        } else {
            System.out.println("Empty data");
        }
        System.out.println("Enter any key to previewMenu or back to back MainMenu");
        String backMenu = Config.scanner().nextLine();
        if (backMenu.equalsIgnoreCase("back")) {
            new Navbar();
        } else {
            manageSong();
        }
        return songList;
    }

    public void updateSong() {
        for (int i = 0; i < songList.size(); i++) {
            System.out.println(songList.get(i));
        }
        System.out.println("Enter Id of Song to update");
        int idUpdate = Config.scanner().nextInt();
        boolean check = false;
        for (int i = 0; i < songList.size(); i++) {
            if (songList.get(i).getId() == idUpdate) {
                check = true;
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
                if (categoryController.detailCategory(choose) != null)
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
                        boolean checkSong = false;
                        for (int i = 0; i < song.getSingers().size(); i++) {
                            if (song.getSingers().get(i).getSingerName() == singerController.detailSinger(chooseCheck).getSingerName()) {
                                checkSong = true;
                                break;
                            }
                        }
                        if (!checkSong) {
                            song.getSingers().add(singerController.detailSinger(chooseCheck));
                        }
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
            System.out.println("Enter any key to try again or back to back previewMenu");
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
            System.out.println(songList.get(i));
        }
        while (true) {
            System.out.println("Enter Id of Song to delete or any char to back previewMenu");
            String back = Config.scanner().nextLine();
            if (back == null || back.trim().isEmpty() || Character.isLetter(back.charAt(0))) {
                manageSong();
            } else {
                int idDelete = Integer.parseInt(back);
                boolean check = false;
                for (int i = 0; i < songList.size(); i++) {
                    if (songList.get(i).getId() == idDelete) {
                        check = true;
                    }
                }
                if (check) {
                    System.out.println("Do you want to change: Y/N");
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
        System.out.println("2. Show All Playlist");
        System.out.println("3. Update Playlist");
        System.out.println("4. Delete Playlist");
        System.out.println("5. Add Song to Playlist");
        System.out.println("6. Remove Song Of Playlist");
        System.out.println("7. Back!");
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
        if (playLists.size() > 0) {
            for (PlayList playList : playLists) {
                System.out.println(playList);
            }
            System.out.println("Enter Id of Playlist to show or any char to back previewMenu");
            String back = Config.scanner().nextLine();
            if (back == null || back.trim().isEmpty() || Character.isLetter(back.charAt(0))) {
                manageUser();
            } else {
                int choice = Integer.parseInt(back);
                showSongOfPlaylist(choice);
            }

        } else {
            System.out.println("Data empty!");
        }
        System.out.println("Enter any key to previewMenu or back to back MainMenu");
        String backMenu = Config.scanner().nextLine();
        if (backMenu.equalsIgnoreCase("back")) {
            new Navbar();
        } else {
            playlistManage();
        }
        return playLists;
    }

    public void updatePlaylist() {
        for (int i = 0; i < playLists.size(); i++) {
            System.out.println(playLists.get(i));
        }
        while (true) {
            System.out.println("Enter Id of Playlist to update or anu char to back previewMenu");
            String back = Config.scanner().nextLine();
            if (back == null || back.trim().isEmpty() || Character.isLetter(back.charAt(0))) {
                playlistManage();
            } else {
                int idUpdate = Integer.parseInt(back);
                boolean check = false;
                for (int i = 0; i < playLists.size(); i++) {
                    if (playLists.get(i).getId() == idUpdate) {
                        check = true;
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
                }
            }
        }
    }

    public void removeSongOfPlaylist() {
        for (int i = 0; i < playLists.size(); i++) {
            System.out.println(playLists.get(i));
        }
        while (true) {
            System.out.println("Enter Id of Playlist to delete Song or anu char to back previewMenu");
            String back = Config.scanner().nextLine();
            if (back == null || back.trim().isEmpty() || Character.isLetter(back.charAt(0))) {
                playlistManage();
            } else {
                int idUpdate = Integer.parseInt(back);
                boolean check = false;
                for (int i = 0; i < playLists.size(); i++) {
                    if (playLists.get(i).getId() == idUpdate) {
                        check = true;
                    }
                }
                if (check) {
                    PlayList updatePlaylist = playListController.detailPlaylist(idUpdate);
                    for (Song song : updatePlaylist.getPlaylistSongs()) {
                        System.out.println(song);
                    }
                    System.out.println("Enter Id of Song to delete or anu char to back previewMenu");
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

    }

    public void showSongOfPlaylist(int choice) {
        for (Song song : playListController.detailPlaylist(choice).getPlaylistSongs()) {
            System.out.println(song);
        }
        System.out.println("Enter Id of Song to show or any char to back previewMenu");
        String back = Config.scanner().nextLine();
        if (back == null || back.trim().isEmpty() || Character.isLetter(back.charAt(0))) {
            manageUser();
        } else {
            int choose = Integer.parseInt(back);
            new GuestView().detailSong(choose);
        }

    }

    public void deletePlaylist() {
        for (int i = 0; i < playLists.size(); i++) {
            System.out.println(playLists.get(i));
        }
        while (true) {
            System.out.println("Enter Id of Playlist to delete or any char to back previewMenu");
            String back = Config.scanner().nextLine();
            if (back == null || back.trim().isEmpty() || Character.isLetter(back.charAt(0))) {
                playlistManage();
            } else {
                int idDelete = Integer.parseInt(back);
                boolean check = false;
                for (int i = 0; i < playLists.size(); i++) {
                    if (playLists.get(i).getId() == idDelete) {
                        check = true;
                    }
                }
                if (check) {
                    System.out.println("Do you want to change: Y/N");
                    String choice = Config.scanner().nextLine();
                    if (choice.equalsIgnoreCase("y")) {
//                        playLists.remove(idDelete);
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
    }

    public void addSongToPlaylistBySongId() {
        List<PlayList> list = new ArrayList<>();
        for (int i = 0; i < playLists.size(); i++) {
            list.add(playLists.get(i));
        }
        if (list.size() != 0) {
            boolean check = false;
            boolean checkSong = false;
            PlayList updatePlaylist = new PlayList();
            Song updateSong = new Song();
            int idPlaylist;
            int idSong;
            while (true) {
                for (PlayList playList : list) {
                    System.out.println(playList);
                }
                System.out.println("Enter Id of Playlist which you want to add Song");
                idPlaylist = Config.scanner().nextInt();
                for (int i = 0; i < playLists.size(); i++) {
                    if (playLists.get(i).getId() == idPlaylist) {
                        updatePlaylist = playListController.detailPlaylist(idPlaylist);
                        check = true;
                        break;
                    }
                }
                if (!check) {
                    System.out.println("Id not found! Please try again");
                    System.out.println("Enter any key to previewMenu or back to back MainMenu");
                    String backMenu = Config.scanner().nextLine();
                    if (backMenu.equalsIgnoreCase("back")) {
                        new Navbar();
                    } else {
                        playlistManage();
                    }
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
                        updateSong = songController.detailSong(idSong);
                        checkSong = true;
                        break;
                    }
                }
                if (checkSong) {
                    break;
                } else {
                    System.out.println("Id not found! Please try again.");
                    System.out.println("Enter any key to previewMenu or back to back MainMenu");
                    String backMenu = Config.scanner().nextLine();
                    if (backMenu.equalsIgnoreCase("back")) {
                        new Navbar();
                    } else {
                        playlistManage();
                    }
                }
            }
            List<Song> listUpdate = updatePlaylist.getPlaylistSongs();
            listUpdate.add(updateSong);
            updatePlaylist.setPlaylistSongs(listUpdate);
            playListController.updatePlaylist(updatePlaylist);
            System.out.println("Add Success");
            System.out.println("Enter any key to previewMenu or back to back MainMenu");
            String backMenu = Config.scanner().nextLine();
            if (backMenu.equalsIgnoreCase("back")) {
                new Navbar();
            } else {
                playlistManage();
            }
        } else {
            System.out.println("Empty data");
            System.out.println("Enter any key to try again or back to back previewMenu");
            String backMenu = Config.scanner().nextLine();
            if (backMenu.equalsIgnoreCase("back")) {
                playlistManage();
            } else {
                addSongToPlaylistBySongId();
            }
        }

    }

    public void manageSinger() {
        System.out.println("1. Creat Singer");
        System.out.println("2. Show All Singer");
        System.out.println("3. Update Singer");
        System.out.println("4. Delete Singer");
        System.out.println("5. Back");
        System.out.println("Enter your choice");
        int choice = InputMethods.getInteger();
        switch (choice) {
            case 1:
                createSinger();
                break;
            case 2:
                new GuestView().showListSinger();
                break;
            case 3:
                updateSinger();
                break;
            case 4:
                deleteSinger();
                break;
            case 5:
                new Navbar();
            default:
                manageSinger();

        }
    }

    public void createSinger() {
        int id = 0;
        String name = "";
        List<Song> creatListSong = new ArrayList<>();
        if (singerList.size() == 0) {
            id = 1;
        } else {
            id = singerList.get(singerList.size() - 1).getSingerId() + 1;
        }

        while (true) {
            System.out.println("Enter name of Singer");
            name = Config.scanner().nextLine();
            if (name == null || name.trim().isEmpty()) {
                new ResponseMessage("Please enter name of Singer");
            } else {
                break;
            }
        }
        Singer singer = new Singer(id, name, creatListSong);
        singerController.creatSinger(singer);
        System.out.println(" Create Success");
        System.out.println("Enter any key to previewMenu or back to back MainMenu");
        String backMenu = Config.scanner().nextLine();
        if (backMenu.equalsIgnoreCase("back")) {
            new Navbar();
        } else {
            manageSinger();
        }
    }

    public void deleteSinger() {
        for (int i = 0; i < singerList.size(); i++) {
            System.out.println(singerList.get(i));
        }
        while (true) {
            System.out.println("Enter Id of Singer to delete or any char to back previewMenu");
            String back = Config.scanner().nextLine();
            if (back == null || back.trim().isEmpty() || Character.isLetter(back.charAt(0))) {
                manageSinger();
            } else {
                int idDelete = Integer.parseInt(back);
                boolean check = false;
                for (int i = 0; i < singerList.size(); i++) {
                    if (singerList.get(i).getSingerId() == idDelete) {
                        check = true;
                    }
                }
                if (check) {
                    System.out.println("Do you want to change: Y/N");
                    String choice = Config.scanner().nextLine();
                    if (choice.equalsIgnoreCase("y")) {
//                        playLists.remove(idDelete);
                        singerController.deleteSinger(idDelete);
                        System.out.println("Delete Success");
                        System.out.println("Enter any key to previewMenu or back to back MainMenu");
                        String backMenu = Config.scanner().nextLine();
                        if (backMenu.equalsIgnoreCase("back")) {
                            new Navbar();
                        } else {
                            manageSinger();
                        }
                    } else {
                        deleteSinger();
                    }
                } else {
                    System.out.println("Id not found");
                }
            }
        }
    }

    public void updateSinger() {
        for (int i = 0; i < singerList.size(); i++) {
            System.out.println(singerList.get(i));
        }
        while (true) {
            System.out.println("Enter Id of Singer to update or any char to back previewMenu");
            String back = Config.scanner().nextLine();
            if (back == null || back.trim().isEmpty() || Character.isLetter(back.charAt(0))) {
                manageSinger();
            } else {
                int idUpdate = Integer.parseInt(back);
                boolean check = false;
                for (int i = 0; i < singerList.size(); i++) {
                    if (singerList.get(i).getSingerId() == idUpdate) {
                        check = true;
                    }
                }
                if (check) {
                    Singer updateSinger = singerController.detailSinger(idUpdate);
                    while (true) {
                        System.out.println("Enter name of Singer to update");
                        String name = Config.scanner().nextLine();
                        if (name.length() != 0) {
                            updateSinger.setSingerName(name);
                            break;
                        }
                    }
                    singerController.updateSinger(updateSinger);
                    System.out.println("Update Success");
                    System.out.println("Enter any key to previewMenu or back to back MainMenu");
                    String backMenu = Config.scanner().nextLine();
                    if (backMenu.equalsIgnoreCase("back")) {
                        new Navbar();
                    } else {
                        manageSinger();
                    }
                } else {
                    System.out.println("Id not found!");
                }
            }
        }
    }

    public void manageBand() {
        System.out.println("1. Creat Band");
        System.out.println("2. Show All Band");
        System.out.println("3. Update Band");
        System.out.println("4. Delete Band");
        System.out.println("5. Back");
        System.out.println("Enter your choice");
        int choice = InputMethods.getInteger();
        switch (choice) {
            case 1:
                createBand();
                break;
            case 2:
                new GuestView().showListBand();
                break;
            case 3:
                updateBand();
                break;
            case 4:
                deleteBand();
                break;
            case 5:
                new Navbar();
            default:
                manageBand();

        }

    }

    public void createBand() {
        int id = 0;
        String name = "";
        List<Song> creatListSong = new ArrayList<>();
        if (bandList.size() == 0) {
            id = 1;
        } else {
            id = bandList.get(bandList.size() - 1).getBandId() + 1;
        }

        while (true) {
            System.out.println("Enter name of Band");
            name = Config.scanner().nextLine();
            if (name == null || name.trim().isEmpty()) {
                new ResponseMessage("Please enter name of Band");
            } else {
                break;
            }
        }
        Band newBand = new Band(id, name, creatListSong);
        bandController.creatBand(newBand);
        System.out.println(" Create Success");
        System.out.println("Enter any key to previewMenu or back to back MainMenu");
        String backMenu = Config.scanner().nextLine();
        if (backMenu.equalsIgnoreCase("back")) {
            new Navbar();
        } else {
            manageBand();
        }
    }

    public void updateBand() {
        for (int i = 0; i < bandList.size(); i++) {
            System.out.println(bandList.get(i));
        }
        while (true) {
            System.out.println("Enter Id of Band to update or any char to back previewMenu");
            String back = Config.scanner().nextLine();
            if (back == null || back.trim().isEmpty() || Character.isLetter(back.charAt(0))) {
                manageBand();
            } else {
                int idUpdate = Integer.parseInt(back);
                boolean check = false;
                for (int i = 0; i < bandList.size(); i++) {
                    if (bandList.get(i).getBandId() == idUpdate) {
                        check = true;
                    }
                }
                if (check) {
                    Band updateBand = bandController.detailBand(idUpdate);
                    while (true) {
                        System.out.println("Enter name of Singer to update");
                        String name = Config.scanner().nextLine();
                        if (name.length() != 0) {
                            updateBand.setBandName(name);
                            break;
                        }
                    }
                    bandController.updateBand(updateBand);
                    System.out.println("Update Success");
                    System.out.println("Enter any key to previewMenu or back to back MainMenu");
                    String backMenu = Config.scanner().nextLine();
                    if (backMenu.equalsIgnoreCase("back")) {
                        new Navbar();
                    } else {
                        manageBand();
                    }
                } else {
                    System.out.println("Id not found!");
                }
            }
        }
    }

    public void deleteBand() {
        for (int i = 0; i < bandList.size(); i++) {
            System.out.println(bandList.get(i));
        }
        while (true) {
            System.out.println("Enter Id of Band to delete or any char to back previewMenu");
            String back = Config.scanner().nextLine();
            if (back == null || back.trim().isEmpty() || Character.isLetter(back.charAt(0))) {
                manageBand();
            } else {
                int idDelete = Integer.parseInt(back);
                boolean check = false;
                for (int i = 0; i < bandList.size(); i++) {
                    if (bandList.get(i).getBandId() == idDelete) {
                        check = true;
                    }
                }
                if (check) {
                    System.out.println("Do you want to change: Y/N");
                    String choice = Config.scanner().nextLine();
                    if (choice.equalsIgnoreCase("y")) {
//                        playLists.remove(idDelete);
                        bandController.deleteBand(idDelete);
                        System.out.println("Delete Success");
                        System.out.println("Enter any key to previewMenu or back to back MainMenu");
                        String backMenu = Config.scanner().nextLine();
                        if (backMenu.equalsIgnoreCase("back")) {
                            new Navbar();
                        } else {
                            manageBand();
                        }
                    } else {
                        deleteBand();
                    }
                } else {
                    System.out.println("Id not found");
                }
            }
        }
    }

    public void manageCategory() {
        System.out.println("1. Creat Category");
        System.out.println("2. Show All Category");
        System.out.println("3. Update Category");
        System.out.println("4. Delete Category");
        System.out.println("5. Back");
        System.out.println("Enter your choice");
        int choice = InputMethods.getInteger();
        switch (choice) {
            case 1:
                createCategory();
                break;
            case 2:
                new GuestView().showListCategory();
                break;
            case 3:
                updateCategory();
                break;
            case 4:
                deleteCategory();
                break;
            case 5:
                new Navbar();
            default:
                manageCategory();

        }
    }

    public void createCategory() {
        int id = 0;
        String name = "";
        if (categoryList.size() == 0) {
            id = 1;
        } else {
            id = categoryList.get(categoryList.size() - 1).getId() + 1;
        }

        while (true) {
            System.out.println("Enter name of Category");
            name = Config.scanner().nextLine();
            if (name == null || name.trim().isEmpty()) {
                new ResponseMessage("Please enter name of Category");
            } else {
                break;
            }
        }
        Category category = new Category(id, name);
        categoryController.createCategory(category);
        System.out.println(" Create Success");
        System.out.println("Enter any key to previewMenu or back to back MainMenu");
        String backMenu = Config.scanner().nextLine();
        if (backMenu.equalsIgnoreCase("back")) {
            new Navbar();
        } else {
            manageCategory();
        }
    }

    public void updateCategory() {
        for (int i = 0; i < categoryList.size(); i++) {
            System.out.println(categoryList.get(i));
        }
        while (true) {
            System.out.println("Enter Id of Category to update or any char to back previewMenu");
            String back = Config.scanner().nextLine();
            if (back == null || back.trim().isEmpty() || Character.isLetter(back.charAt(0))) {
                manageBand();
            } else {
                int idUpdate = Integer.parseInt(back);
                boolean check = false;
                for (int i = 0; i < categoryList.size(); i++) {
                    if (categoryList.get(i).getId() == idUpdate) {
                        check = true;
                    }
                }
                if (check) {
                    Category updateCategory = categoryController.detailCategory(idUpdate);
                    while (true) {
                        System.out.println("Enter name of Singer to update");
                        String name = Config.scanner().nextLine();
                        if (name.length() != 0) {
                            updateCategory.setCategoryName(name);
                            break;
                        }
                    }
                    categoryController.updateCategory(updateCategory);
                    System.out.println("Update Success");
                    System.out.println("Enter any key to previewMenu or back to back MainMenu");
                    String backMenu = Config.scanner().nextLine();
                    if (backMenu.equalsIgnoreCase("back")) {
                        new Navbar();
                    } else {
                        manageCategory();
                    }
                } else {
                    System.out.println("Id not found!");
                }
            }
        }
    }

    public void deleteCategory() {
        for (int i = 0; i < categoryList.size(); i++) {
            System.out.println(categoryList.get(i));
        }
        while (true) {
            System.out.println("Enter Id of Category to delete or any char to back previewMenu");
            String back = Config.scanner().nextLine();
            if (back == null || back.trim().isEmpty() || Character.isLetter(back.charAt(0))) {
                manageCategory();
            } else {
                int idDelete = Integer.parseInt(back);
                boolean check = false;
                for (int i = 0; i < categoryList.size(); i++) {
                    if (categoryList.get(i).getId() == idDelete) {
                        check = true;
                    }
                }
                if (check) {
                    System.out.println("Do you want to change: Y/N");
                    String choice = Config.scanner().nextLine();
                    if (choice.equalsIgnoreCase("y")) {
//                        playLists.remove(idDelete);
                        categoryController.deleteCategory(idDelete);
                        System.out.println("Delete Success");
                        System.out.println("Enter any key to previewMenu or back to back MainMenu");
                        String backMenu = Config.scanner().nextLine();
                        if (backMenu.equalsIgnoreCase("back")) {
                            new Navbar();
                        } else {
                            manageCategory();
                        }
                    } else {
                        deleteCategory();
                    }
                } else {
                    System.out.println("Id not found");
                }
            }
        }
    }

    public void manageUser() {
        System.out.println("1. Block User");
        System.out.println("2. Delete User");
        System.out.println("3. Change Role");
        System.out.println("4. Back");
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
                changeRole();
                break;
            case 4:
                new Navbar();
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
                if (userController.detailUser(idBlock) == null) {
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
                                playlistManage();
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
                if (userController.detailUser(idDelete) == null) {
                    System.out.println("Id not found! Try again");
                } else {
                    User deleteUser = userController.detailUser(idDelete);
                    if (deleteUser != null || String.valueOf(deleteUser.getRoles()).equalsIgnoreCase("admin")) {
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
                                playlistManage();
                            }
                        } else {
                            deleteUser();
                        }
                    }
                }
            }
        }
    }

    public void changeRole() {
        for (User user : userList) {
            if (!String.valueOf(user.getRoles()).equalsIgnoreCase("admin")) {
                System.out.println(user.getUserName() + "  " + user.getId() + " " + user.getRoles());
            }
        }
        while (true) {
            System.out.println("Enter Id of User to change or any char to back previewMenu");
            String back = Config.scanner().nextLine();
            if (back == null || back.trim().isEmpty() || Character.isLetter(back.charAt(0))) {
                manageUser();
            } else {
                int idChange = Integer.parseInt(back);
                if (userController.detailUser(idChange) == null) {
                    System.out.println("Id not found! Please try again");
                } else {
                    User changeUser = userController.detailUser(idChange);
                    Set<Role> roleSet = new HashSet<>();
                    if (String.valueOf(changeUser.getRoles()).equalsIgnoreCase("admin")) {
                        System.out.println("Id not found! Please try again");
                    } else {
                        System.out.println("Do you want to delete: Y/N");
                        String choice = Config.scanner().nextLine();
                        if (choice.equalsIgnoreCase("y")) {
                            if (String.valueOf(changeUser.getRoles()).equalsIgnoreCase("user")) {
                                roleSet.add(new Role(0, RoleName.PM));
                            } else {
                                roleSet.add(new Role(0, RoleName.USER));
                            }
                            changeUser.setRoles(roleSet);
                            userController.createUser(changeUser);
                            System.out.println("Change role success");
                            System.out.println("Enter any key to previewMenu or back to back MainMenu");
                            String backMenu = Config.scanner().nextLine();
                            if (backMenu.equalsIgnoreCase("back")) {
                                new Navbar();
                            } else {
                                manageUser();
                            }
                        } else {
                            manageUser();
                        }
                    }
                }
            }

        }
    }

    public void manageMyPlaylist() {
        System.out.println("1. Create MyPlaylist");
        System.out.println("2. Show MyPlaylist");
        System.out.println("3. Update MyPlaylist");
        System.out.println("4. Delete MyPlaylist");
        System.out.println("5. Add Song to MyPlaylist");
        System.out.println("6. Remove Song of MyPlaylist");
        System.out.println("7. Back!");
        int choice = InputMethods.getInteger();
        switch (choice) {
            case 1:
                createMyPlaylist();
                break;
            case 2:
                showAllMyPlaylist();
                break;
            case 3:
                changeRole();
                break;
            case 4:
                deleteMyPlaylist();
                break;
            case 5:
                addSongToMyPlaylistBySongId();
                break;
            case 6:
                removeSongOfMyPlaylist();
                break;
            case 7:
                new Navbar();
            default:
                manageMyPlaylist();
        }
    }

    public void createMyPlaylist() {
        int id = 0;
        String name = "";
        boolean status;

        if (myPlaylists.size() == 0) {
            id = 1;
        } else {
            id = myPlaylists.get(myPlaylists.size() - 1).getId() + 1;
        }
        while (true) {
            System.out.println("Enter name of MyPlaylist:");
            name = Config.scanner().nextLine();
            if (name == null || name.trim().isEmpty()) {
                System.err.println("Please enter name of MyPlaylist");
            } else {
                break;
            }
        }

        MyPlaylist newMyPlaylist = new MyPlaylist(id, name, new ArrayList<>(), user);
        myPlaylistController.createMyPlaylist(newMyPlaylist);
        System.out.println("Creat Success");
        System.out.println("Enter any key to previewMenu or back to back MainMenu");
        String backMenu = Config.scanner().nextLine();
        if (backMenu.equalsIgnoreCase("back")) {
            new Navbar();
        } else {
            manageMyPlaylist();
        }
    }

    public List<MyPlaylist> showAllMyPlaylist() {
        List<MyPlaylist> myList = new ArrayList<>();
        if (myPlaylists.size() > 0) {
            for (MyPlaylist myPlaylist : myPlaylists) {
                if (myPlaylist.getUser().getUserName() == user.getUserName()) {
                    myList.add(myPlaylist);
                    System.out.println(myPlaylist);
                }
            }
            System.out.println("Enter Id of MyPlaylist to show or any char to back previewMenu");
            String back = Config.scanner().nextLine();
            if (back == null || back.trim().isEmpty() || Character.isLetter(back.charAt(0))) {
                manageMyPlaylist();
            } else {
                int choice = Integer.parseInt(back);
                showSongOfMyPlaylist(choice);
            }

        } else {
            System.out.println("Data empty!");
        }
        System.out.println("Enter any key to previewMenu or back to back MainMenu");
        String backMenu = Config.scanner().nextLine();
        if (backMenu.equalsIgnoreCase("back")) {
            new Navbar();
        } else {
            manageMyPlaylist();
        }
        return myList;
    }

    public void showSongOfMyPlaylist(int choice) {
        for (Song song : myPlaylistController.detailMyPlaylist(choice).getSongs()) {
            System.out.println(song);
        }
        System.out.println("Enter Id of Song to show or any char to back previewMenu");
        String back = Config.scanner().nextLine();
        if (back == null || back.trim().isEmpty() || Character.isLetter(back.charAt(0))) {
            manageMyPlaylist();
        } else {
            int choose = Integer.parseInt(back);
            new GuestView().detailSong(choose);
        }

    }

    public void deleteMyPlaylist() {
        List<MyPlaylist> myList = new ArrayList<>();

        for (MyPlaylist myPlaylist : myPlaylists) {
            if (myPlaylist.getUser().getUserName() == user.getUserName()) {
                myList.add(myPlaylist);
            }
        }
        if (myList.size() > 0) {
            for (MyPlaylist myPlayList : myList
            ) {
                System.out.println(myPlayList);
            }
            while (true) {
                System.out.println("Enter Id of MyPlaylist to delete or any char to back previewMenu");
                String back = Config.scanner().nextLine();
                if (back == null || back.trim().isEmpty() || Character.isLetter(back.charAt(0))) {
                    playlistManage();
                } else {
                    int idDelete = Integer.parseInt(back);
                    boolean check = false;
                    for (int i = 0; i < myList.size(); i++) {
                        if (myList.get(i).getId() == idDelete) {
                            check = true;
                        }
                    }
                    if (check) {
                        System.out.println("Do you want to change: Y/N");
                        String choice = Config.scanner().nextLine();
                        if (choice.equalsIgnoreCase("y")) {
//                        playLists.remove(idDelete);
                            myPlaylistController.deleteMyPlaylist(idDelete);
                            System.out.println("Delete Success");
                            System.out.println("Enter any key to previewMenu or back to back MainMenu");
                            String backMenu = Config.scanner().nextLine();
                            if (backMenu.equalsIgnoreCase("back")) {
                                new Navbar();
                            } else {
                                manageMyPlaylist();
                            }
                        } else {
                            deleteMyPlaylist();
                        }
                    } else {
                        System.out.println("Id not found");
                    }
                }
            }
        } else {
            System.out.println("You don't have any list.");
            System.out.println("Enter any key to previewMenu or back to back MainMenu");
            String backMenu = Config.scanner().nextLine();
            if (backMenu.equalsIgnoreCase("back")) {
                new Navbar();
            } else {
                manageMyPlaylist();
            }
        }
    }

    public void addSongToMyPlaylistBySongId() {
        List<MyPlaylist> myList = new ArrayList<>();
        for (MyPlaylist myPlaylist : myPlaylists) {
            if (myPlaylist.getUser().getUserName() == user.getUserName()) {
                myList.add(myPlaylist);
                System.out.println(myPlaylist);
            }
        }
        if (myList.size() > 0) {
            boolean check = false;
            boolean checkSong = false;
            MyPlaylist updateMyPlaylist = new MyPlaylist();
            Song updateSong = new Song();
            int idMyPlaylist;
            int idSong;
            while (true) {
                for (MyPlaylist myPlaylist : myList) {
                    System.out.println(myPlaylist);
                }
                System.out.println("Enter Id of MyPlaylist which you want to add Song");
                idMyPlaylist = Config.scanner().nextInt();
                for (int i = 0; i < myList.size(); i++) {
                    if (myList.get(i).getId() == idMyPlaylist) {
                        updateMyPlaylist = myPlaylistController.detailMyPlaylist(idMyPlaylist);
                        check = true;
                        break;
                    }
                }
                if (!check) {
                    System.out.println("Id not found! Please try again");
                    System.out.println("Enter any key to previewMenu or back to back MainMenu");
                    String backMenu = Config.scanner().nextLine();
                    if (backMenu.equalsIgnoreCase("back")) {
                        new Navbar();
                    } else {
                        manageMyPlaylist();
                    }
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
                        updateSong = songController.detailSong(idSong);
                        checkSong = true;
                        break;
                    }
                }
                if (checkSong) {
                    break;
                } else {
                    System.out.println("Id not found! Please try again.");
                    System.out.println("Enter any key to previewMenu or back to back MainMenu");
                    String backMenu = Config.scanner().nextLine();
                    if (backMenu.equalsIgnoreCase("back")) {
                        new Navbar();
                    } else {
                        manageMyPlaylist();
                    }
                }
            }
            List<Song> listUpdate = updateMyPlaylist.getSongs();
            listUpdate.add(updateSong);
            updateMyPlaylist.setSongs(listUpdate);
            myPlaylistController.updateMyPlaylist(updateMyPlaylist);
            System.out.println("Add Success");
            System.out.println("Enter any key to previewMenu or back to back MainMenu");
            String backMenu = Config.scanner().nextLine();
            if (backMenu.equalsIgnoreCase("back")) {
                new Navbar();
            } else {
                manageMyPlaylist();
            }
        } else {
            System.out.println("Data empty!");
        }
        System.out.println("Enter any key to previewMenu or back to back MainMenu");
        String backMenu = Config.scanner().nextLine();
        if (backMenu.equalsIgnoreCase("back")) {
            new Navbar();
        } else {
            manageMyPlaylist();
        }
    }

    public void removeSongOfMyPlaylist() {
        List<MyPlaylist> myList = new ArrayList<>();
        for (MyPlaylist myPlaylist : myPlaylists) {
            if (myPlaylist.getUser().getUserName() == user.getUserName()) {
                myList.add(myPlaylist);
                System.out.println(myPlaylist);
            }
        }
        if (myList.size() > 0) {
            while (true) {
                System.out.println("Enter Id of Playlist to delete Song or anu char to back previewMenu");
                String back = Config.scanner().nextLine();
                if (back == null || back.trim().isEmpty() || Character.isLetter(back.charAt(0))) {
                    manageMyPlaylist();
                } else {
                    int idUpdate = Integer.parseInt(back);
                    boolean check = false;
                    for (int i = 0; i < myList.size(); i++) {
                        if (myList.get(i).getId() == idUpdate) {
                            check = true;
                        }
                    }
                    if (check) {
                        MyPlaylist updateMyPlaylist = myPlaylistController.detailMyPlaylist(idUpdate);
                        for (Song song : updateMyPlaylist.getSongs()) {
                            System.out.println(song);
                        }
                        System.out.println("Enter Id of Song to delete or anu char to back previewMenu");
                        String choice = Config.scanner().nextLine();
                        if (choice == null || back.trim().isEmpty() || Character.isLetter(back.charAt(0))) {
                            removeSongOfMyPlaylist();
                        } else {
                            boolean checkSong = false;
                            int choiceSong = Integer.parseInt(choice);
                            int index = 0;
                            for (int i = 0; i < updateMyPlaylist.getSongs().size(); i++) {
                                if (updateMyPlaylist.getSongs().get(i).getId() == choiceSong) {
                                    index = i;
                                    checkSong = true;
                                    break;
                                }
                            }
                            if (check) {
                                updateMyPlaylist.getSongs().remove(index);
                            }
                            myPlaylistController.updateMyPlaylist(updateMyPlaylist);
                            System.out.println("Delete Success");
                            System.out.println("Enter any key to previewMenu or back to back MainMenu");
                            String backMenu = Config.scanner().nextLine();
                            if (backMenu.equalsIgnoreCase("back")) {
                                new Navbar();
                            } else {
                                manageMyPlaylist();
                            }
                        }
                    } else {
                        System.out.println("Id not found!");
                    }
                }
            }
        } else {
            System.out.println("Data empty!");
        }
        System.out.println("Enter any key to previewMenu or back to back MainMenu");
        String backMenu = Config.scanner().nextLine();
        if (backMenu.equalsIgnoreCase("back")) {
            new Navbar();
        } else {
            manageMyPlaylist();
        }


    }
}
