package modal;

import java.io.Serializable;
import java.util.List;

public class PlayList implements Serializable {
    private int id;
    private String playlistName;
    private List<Song> playlistSongs;
    private User user;
    private boolean status;

    public PlayList() {
    }

    public PlayList(int id, String playlistName, List<Song> playlistSongs, User user,boolean status) {
        this.id = id;
        this.playlistName = playlistName;
        this.playlistSongs = playlistSongs;
        this.user = user;
        this.status =status;
    }

    public PlayList(int id, String playlistName, User user, boolean status) {
        this.id = id;
        this.playlistName = playlistName;
        this.user = user;
        this.status = status;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPlaylistName() {
        return playlistName;
    }

    public void setPlaylistName(String playlistName) {
        this.playlistName = playlistName;
    }

    public List<Song> getPlaylistSongs() {
        return playlistSongs;
    }

    public void setPlaylistSongs(List<Song> playlistSongs) {
        this.playlistSongs = playlistSongs;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "PlayList{" +
                "id=" + id +
                ", playlistName='" + playlistName + '\'' +
                ", playlistSongs=" + playlistSongs +
                ", user=" + user +
                '}';
    }
}
