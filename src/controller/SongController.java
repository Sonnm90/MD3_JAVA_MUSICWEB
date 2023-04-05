package controller;

import modal.Song;
import service.song.ISongService;
import service.song.SongServiceIMPL;

import java.util.List;

public class SongController {
    private ISongService songService = new SongServiceIMPL();

    public List<Song> getListSong() {
        return songService.findAll();
    }

    public void createSong(Song song) {
        songService.save(song);
    }
    public void updateSong(Song song){
        songService.save(song);
    }
    public Song detailSong(int id){
        return songService.findById(id);
    }
    public void  deleteSong(int id){
        songService.deleteById(id);
    }
}
