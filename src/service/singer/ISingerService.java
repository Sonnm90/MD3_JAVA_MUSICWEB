package service.singer;

import modal.Singer;
import modal.Song;
import service.IGenericService;

import java.util.List;

public interface ISingerService extends IGenericService<Singer> {
    List<Singer> findAll();
    Singer findByName(String name);
    List<Song> showAllSongOfSinger(String name);
}
