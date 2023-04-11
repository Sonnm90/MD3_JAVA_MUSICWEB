package service.myplaylist;

import config.Config;
import modal.Band;
import modal.Comment;
import modal.MyPlaylist;
import modal.PlayList;

import java.util.List;

public class MyPlaylistServiceIMPL implements IMyPlaylistService{
    List<MyPlaylist> myPlaylists = new Config<MyPlaylist>().readFromFile(Config.PATH_MY_PLAYLIST);



    @Override
    public List<MyPlaylist> findAll() {
        return myPlaylists;
    }

    @Override
    public void save(MyPlaylist myPlaylist) {
        if (findById(myPlaylist.getId())==null) {
            myPlaylists.add(myPlaylist);
        } else {
            myPlaylists.set(myPlaylists.indexOf(findById(myPlaylist.getId())),myPlaylist);
        }
        new Config<MyPlaylist>().writeToFile(Config.PATH_MY_PLAYLIST, myPlaylists);
    }

    @Override
    public MyPlaylist findById(int id) {
        for (int i = 0; i < myPlaylists.size(); i++) {
            if (myPlaylists.get(i).getId()==id){
                return myPlaylists.get(i);
            }
        }
        return null;
    }

    @Override
    public void deleteById(int id) {
        for (int i = 0; i < myPlaylists.size(); i++) {
            if (myPlaylists.get(i).getId()==id){
                myPlaylists.remove(i);
                new Config<MyPlaylist>().writeToFile(Config.PATH_MY_PLAYLIST, myPlaylists);
            }
        }
    }
}
