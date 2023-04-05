package service.song;

import modal.Band;
import modal.Singer;
import modal.Song;
import service.IGenericService;

import java.util.List;

public interface ISongService extends IGenericService<Song> {
    List<Song> searchByName(String name);
    List<Song> searchBySinger(String nameOfSinger);
    List<Song> searchByBand(String nameOfBand);
}
