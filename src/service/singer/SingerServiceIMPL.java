package service.singer;

import config.Config;
import modal.Singer;
import modal.Song;

import java.util.List;

public class SingerServiceIMPL implements ISingerService {
    List<Singer> singerList = new Config<Singer>().readFromFile(Config.PATH_SINGER);
    @Override
    public List<Singer> findAll() {
        return singerList;
    }

    @Override
    public void save(Singer singer) {
        if (findById(singer.getSingerId())==null) {
            singerList.add(singer);
        } else {
            singerList.set(singerList.indexOf(findById(singer.getSingerId())),singer);
        }
        new Config<Singer>().writeToFile(Config.PATH_SINGER,singerList);
    }


    @Override
    public Singer findById(int id) {
        for (int i = 0; i < singerList.size(); i++) {
            if (singerList.get(i).getSingerId()==id){
                return singerList.get(i);
            }
        }
        return null;
    }

    @Override
    public void deleteById(int id) {
        for (int i = 0; i < singerList.size(); i++) {
            if (singerList.get(i).getSingerId()==id){
                singerList.remove(i);
                new Config<Singer>().writeToFile(Config.PATH_SINGER,singerList);

            }
        }
    }

    @Override
    public Singer findByName(String name) {
        for (Singer singer: singerList) {
            if (singer.getSingerName().toLowerCase().contains(name.toLowerCase())){
                return singer;
            }
        }
        return null;
    }

    @Override
    public List<Song> showAllSongOfSinger(String name) {
        for (int i = 0; i < singerList.size(); i++) {
            if (singerList.get(i).getSingerName().toLowerCase().equals(name.toLowerCase())){
                return singerList.get(i).getSongsOfSinger();
            }
        }
        return null;
    }
}
