package controller;

import modal.PlayList;
import modal.Singer;
import service.playlist.IPlayListService;
import service.playlist.PlaylistServiceIMPL;
import service.singer.ISingerService;
import service.singer.SingerServiceIMPL;

import java.util.List;

public class PlayListController {
    private IPlayListService playListService = new PlaylistServiceIMPL();

    public List<PlayList> getListPlaylist() {
        return playListService.findAll();
    }

    public void creatPlaylist(PlayList playList) {
        playListService.save(playList);
    }

    public void updatePlaylist(PlayList playList) {
        playListService.save(playList);
    }

    public PlayList detailPlaylist(int id) {
        return playListService.findById(id);
    }

    public List<PlayList> searchPlaylistByName(String name) {
        return playListService.searchByName(name);
    }

    public boolean addSongToPlaylistBySongId(PlayList playList, int id) {
        return playListService.addSongToPlaylistBySongId(playList, id);
    }

    public void deletePlaylist(int id) {
        playListService.deleteById(id);
    }
    public void showSongOfPlaylist(PlayList playList){
        playListService.showSongOfPlaylist(playList);
    }
}
