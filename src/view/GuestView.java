package view;

import config.Config;
import controller.*;
import modal.Band;
import modal.Category;
import modal.Singer;
import modal.Song;
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
    PlayListController playListController =new PlayListController();

    public void showListSong() {
        for (Song song:songController.getListSong()
        ) {
            System.out.println(song);
        }
        System.out.println("Enter Id of Song to show detail");
        int choice =Config.scanner().nextInt();
        if(choice <songController.getListSong().size()&& choice>=0){
            System.out.println("Name of Song: "+songController.getListSong().get(choice).getName());
                System.out.println(showDetailSong(choice));
        } else {
            System.out.println("Not Found!");
        }
        System.out.println("Enter any key to continue or back to back MainMenu");
        String backMenu =Config.scanner().nextLine();
        if (backMenu.equalsIgnoreCase("back")){
            new Navbar();
        } else {
            showListSong();
        }
    }

    public Song showDetailSong(int id) {
        return songController.detailSong(id);
    }

    public void showListSinger() {
        for (Singer singer:singerController.getListSinger()
        ) {
            System.out.println(singer);
        }
        System.out.println("Enter Id of Singer to show details");
        int choice =Config.scanner().nextInt();
        if(choice <singerController.getListSinger().size()&& choice>=0){
            System.out.println("Name of Singer: "+singerController.getListSinger().get(choice).getSingerName());
            for (Song song: showDetailSinger(choice)
            ) {
                System.out.println(song);
            }
        } else {
            System.out.println("Not Found!");
        }
        System.out.println("Enter any key to continue or back to back MainMenu");
        String backMenu =Config.scanner().nextLine();
        if (backMenu.equalsIgnoreCase("back")){
            new Navbar();
        } else {
            showListSinger();
        }
    }

    public List<Song> showDetailSinger(int id) {
        return singerController.detailSinger(id).getSongsOfSinger();
    }

    public void showListCategory() {
        System.out.println(categoryController);
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
        for (Band band:bandController.getListBand()
             ) {
            System.out.println(band);
        }
        System.out.println("Enter Id of Band to show details");
        int choice =Config.scanner().nextInt();
        if(choice <bandController.getListBand().size()&& choice>=0){
            System.out.println("Name of Band: "+bandController.getListBand().get(choice).getBandName());
            for (Song song: showDetailBand(choice)
                 ) {
                System.out.println(song);
            }
        } else {
            System.out.println("Not Found!");
        }
        System.out.println("Enter any key to continue or back to back MainMenu");
        String backMenu =Config.scanner().nextLine();
        if (backMenu.equalsIgnoreCase("back")){
            new Navbar();
        } else {
            showListBand();
        }
    }
    public List<Song> showDetailBand(int id) {
        return bandController.detailBand(id).getSongsOfBand();
    }
    public void showAllPlaylist(){
        System.out.println(playListController.getListPlaylist());
    }
    public List<Song> showDetailPlaylist(int id){
        return playListController.detailPlaylist(id).getPlaylistSongs();
    }
}
