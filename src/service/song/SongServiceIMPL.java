package service.song;

import config.Config;
import modal.*;

import java.util.ArrayList;
import java.util.List;

public class SongServiceIMPL implements ISongService {
//    List<Song> songList = new Config<Song>().readFromFile(Config.PATH_SONG);
    public static List<Song> songList =new ArrayList<>();
    public static List<Singer> singers = new ArrayList<>();
    public static List<Band> bands = new ArrayList<>();

    static {
        songList.add(new Song(0,"Mua",singers,new Category(1,"nhac tre"),bands,new User(0,"huy"),0,new ArrayList<>()));
        songList.add(new Song(1,"Buon",singers,new Category(1,"nhac tre"),bands,new User(10,"son"),0,new ArrayList<>()));

    }

    @Override
    public List<Song> findAll() {
        return songList;
    }

    @Override
    public void save(Song song) {
        if (findById(song.getId())==null) {
            songList.add(song);
        } else {
            songList.set(songList.indexOf(findById(song.getId())),song);
        }
        new Config<Song>().writeToFile(Config.PATH_SONG,songList);
    }

    @Override
    public Song findById(int id) {
        for (int i = 0; i < songList.size(); i++) {
            if (songList.get(i).getId()==id){
                return songList.get(i);
            }
        }
        return null;
    }

    @Override
    public void deleteById(int id) {
        for (int i = 0; i < songList.size(); i++) {
            if (songList.get(i).getId()==id){
                songList.remove(i);
                new Config<Song>().writeToFile(Config.PATH_SONG,songList);

            }
        }
    }

    @Override
    public List<Song> searchByName(String name) {
        List<Song> list = new ArrayList<>();
        for (Song song: songList) {
            if (song.getName().toLowerCase().contains(name.toLowerCase())){
                list.add(song);
            }
        }
        return list;
    }

    @Override
    public List<Song> searchBySinger(String nameOfSinger) {
        List<Song> list = new ArrayList<>();
        for (Song song: songList) {
            for (int i = 0; i < song.getSingers().size(); i++) {
                if (song.getSingers().get(i).getSingerName().toLowerCase().contains(nameOfSinger.toLowerCase())){
                    list.add(song);
                }
            }
        }
        return list;
    }

    @Override
    public List<Song> searchByBand(String nameOfBand) {
        List<Song> list = new ArrayList<>();
        for (Song song: songList) {
            for (int i = 0; i < song.getBands().size(); i++) {
                if (song.getBands().get(i).getBandName().toLowerCase().contains(nameOfBand.toLowerCase())){
                    list.add(song);
                }
            }
        }
        return list;
    }
}
