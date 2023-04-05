package service.band;

import modal.Band;
import modal.Song;
import service.IGenericService;

import java.util.List;

public interface IBandService extends IGenericService<Band> {
List<Band> findAll();
Band findByName(String name);
List<Song> showAllSongOfBand(String name);
}
