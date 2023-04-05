package service.playlist;

import config.Config;
import modal.PlayList;
import modal.Song;
import modal.User;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class PlaylistServiceIMPL implements IPlayListService {
    List<PlayList> playLists = new Config<PlayList>().readFromFile(Config.PATH_PLAYLIST);
    List<Song> songList =new Config<Song>().readFromFile(Config.PATH_SONG);

    @Override
    public List<PlayList> findAll() {
        return playLists;
    }

    @Override
    public void save(PlayList playList) {
        if (findById(playList.getId())==null) {
            playLists.add(playList);
        } else {
            playLists.set(playLists.indexOf(findById(playList.getId())),playList);
        }
        new Config<PlayList>().writeToFile(Config.PATH_PLAYLIST, playLists);
    }

    @Override
    public PlayList findById(int id) {
        for (int i = 0; i < playLists.size(); i++) {
            if (playLists.get(i).getId()==id){
                return playLists.get(i);
            }
        }
        return null;
    }

    @Override
    public void deleteById(int id) {
        for (int i = 0; i < playLists.size(); i++) {
            if (playLists.get(i).getId()==id){
                playLists.remove(i);
                new Config<PlayList>().writeToFile(Config.PATH_PLAYLIST,playLists);
            }
        }
    }

    @Override
    public List<PlayList> searchByName(String name) {
        List<PlayList> list = new ArrayList<>();
        for (PlayList playList: playLists) {
            if (playList.getPlaylistName().toLowerCase().contains(name.toLowerCase())){
                list.add(playList);
            }
        }
        return list;
    }

    @Override
    public boolean addSongToPlaylistBySongId(PlayList playList,int id) {
        for (int i = 0; i < playLists.size(); i++) {
            if (playList.getId()==playLists.get(i).getId()){
                for (int j = 0; j < songList.size(); j++) {
                    if (songList.get(i).getId()==id){
                        playLists.get(i).getPlaylistSongs().add(songList.get(i));
                        new Config<PlayList>().writeToFile(Config.PATH_PLAYLIST,playLists);
                        return true;
                    }
                }
            }
        }
        return false;
    }


}
