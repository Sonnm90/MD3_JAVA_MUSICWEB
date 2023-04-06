package view;

import config.Config;
import controller.*;
import dto.response.ResponseMessage;
import modal.*;
import service.song.ISongService;
import service.song.SongServiceIMPL;

import java.util.ArrayList;
import java.util.List;

public class GuestView {
    SongController songController = new SongController();
    List<Song> songList = songController.getListSong();
    SingerController singerController = new SingerController();
    CategoryController categoryController = new CategoryController();
    BandController bandController = new BandController();
    PlayListController playListController = new PlayListController();

    public void showListSong() {
        for (Song song : songController.getListSong()
        ) {
            System.out.println(song);
        }
        System.out.println("Enter Id of Song to show detail");
        int choice = Config.scanner().nextInt();
        if (choice < songController.getListSong().size() && choice >= 0) {
            System.out.println("Name of Song: " + songController.getListSong().get(choice).getName());
            System.out.println(showDetailSong(choice));
        } else {
            System.out.println("Not Found!");
        }
        System.out.println("Enter any key to continue or back to back MainMenu");
        String backMenu = Config.scanner().nextLine();
        if (backMenu.equalsIgnoreCase("back")) {
            new Navbar();
        } else {
            showListSong();
        }
    }

    public Song showDetailSong(int id) {
        return songController.detailSong(id);
    }

    public void showListSinger() {
        if (singerController.getListSinger().size() != 0) {
            for (Singer singer : singerController.getListSinger()
            ) {
                System.out.println(singer);
            }
            System.out.println("Enter Id of Singer to show details");
            int choice = Config.scanner().nextInt();
            if (choice < singerController.getListSinger().size() && choice >= 0) {
                System.out.println("Song of Singer: " + singerController.getListSinger().get(choice).getSingerName());
                for (Song song : showDetailSinger(choice)
                ) {
                    System.out.println(song);
                }
            } else {
                System.out.println("Not Found!");
            }
            System.out.println("Enter any key to continue or back to back MainMenu");
            String backMenu = Config.scanner().nextLine();
            if (backMenu.equalsIgnoreCase("back")) {
                new Navbar();
            } else {
                showListSinger();
            }
        } else {
            System.err.println("not found");
            while (true) {
                System.out.println("Enter back to back MainMenu");
                String backMenu = Config.scanner().nextLine();
                if (backMenu.equalsIgnoreCase("back")) {
                    new Navbar();
                    break;
                }
            }
        }
    }

    public List<Song> showDetailSinger(int id) {
        return singerController.detailSinger(id).getSongsOfSinger();
    }

    public void showListCategory() {
        if (categoryController.getListCategory().size()!=0) {
            for (Category category : categoryController.getListCategory()
            ) {
                System.out.println(category);
            }
            System.out.println("Enter Id of Category to show details");
            int choice = Config.scanner().nextInt();
            if (choice < categoryController.getListCategory().size() && choice >= 0) {
                System.out.println("Song of Category: " + categoryController.getListCategory().get(choice).getCategoryName());
                for (Song song : showDetailCategory(choice)
                ) {
                    System.out.println(song);
                }
            } else {
                System.out.println("Not Found!");
            }
            System.out.println("Enter any key to continue or back to back MainMenu");
            String backMenu = Config.scanner().nextLine();
            if (backMenu.equalsIgnoreCase("back")) {
                new Navbar();
            } else {
                showListCategory();
            }
        }else {
            System.err.println("not found");
            while (true) {
                System.out.println("Enter back to back MainMenu");
                String backMenu = Config.scanner().nextLine();
                if (backMenu.equalsIgnoreCase("back")) {
                    new Navbar();
                    break;
                }
            }
        }
    }

    public List<Song> showDetailCategory(int id) {
        List<Song> list = new ArrayList<>();
        for (int i = 0; i < songController.getListSong().size(); i++) {
            if (songController.getListSong().get(i).getCategory().getCategoryName().equals(categoryController.detailCategory(id).getCategoryName())) {
                list.add(songController.getListSong().get(i));
            }
        }
        return list;
    }

    public void showListBand() {
//        System.out.println(bandController.getListBand());
        if (bandController.getListBand().size()!=0) {
            for (Band band : bandController.getListBand()
            ) {
                System.out.println(band);
            }
            System.out.println("Enter Id of Band to show details");
            int choice = Config.scanner().nextInt();
            if (choice < bandController.getListBand().size() && choice >= 0) {
                System.out.println("Song of Band: " + bandController.getListBand().get(choice).getBandName());
                for (Song song : showDetailBand(choice)
                ) {
                    System.out.println(song);
                }
            } else {
                new ResponseMessage("Not Found");

            }
            System.out.println("Enter any key to continue or back to back MainMenu");
            String backMenu = Config.scanner().nextLine();
            if (backMenu.equalsIgnoreCase("back")) {
                new Navbar();
            } else {
                showListBand();
            }
        } else {
            System.err.println("not found");
            while (true) {
                System.out.println("Enter back to back MainMenu");
                String backMenu = Config.scanner().nextLine();
                if (backMenu.equalsIgnoreCase("back")) {
                    new Navbar();
                    break;
                }
            }
        }
    }

    public List<Song> showDetailBand(int id) {
        return bandController.detailBand(id).getSongsOfBand();
    }

    public void showAllPlaylist() {
        if (playListController.getListPlaylist().size()!=0) {
            for (PlayList playList : playListController.getListPlaylist()
            ) {
                System.out.println(playList);
            }
            System.out.println("Enter Id of Playlist to show details");
            int choice = Config.scanner().nextInt();
            if (choice < playListController.getListPlaylist().size() && choice >= 0) {
                System.out.println("Song of Playlist: " + playListController.getListPlaylist().get(choice).getPlaylistName());
                for (Song song : showDetailPlaylist(choice)
                ) {
                    System.out.println(song);
                }
            } else {
                System.out.println("Not Found!");
            }
            System.out.println("Enter any key to continue or back to back MainMenu");
            String backMenu = Config.scanner().nextLine();
            if (backMenu.equalsIgnoreCase("back")) {
                new Navbar();
            } else {
                showAllPlaylist();
            }
        }else {
            System.err.println("not found");
            while (true) {
                System.out.println("Enter back to back MainMenu");
                String backMenu = Config.scanner().nextLine();
                if (backMenu.equalsIgnoreCase("back")) {
                    new Navbar();
                    break;
                }
            }
        }
    }

    public List<Song> showDetailPlaylist(int id) {
        return playListController.detailPlaylist(id).getPlaylistSongs();
    }
}
