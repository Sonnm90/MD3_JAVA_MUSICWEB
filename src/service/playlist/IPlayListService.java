package service.playlist;

import modal.PlayList;
import modal.Song;
import service.IGenericService;

import java.util.List;

public interface IPlayListService extends IGenericService<PlayList> {
    List<PlayList> searchByName(String name);
boolean addSongToPlaylistBySongId(PlayList playList, int id);
}
