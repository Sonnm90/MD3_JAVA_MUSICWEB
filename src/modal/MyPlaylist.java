package modal;

import java.util.List;

public class MyPlaylist {
    private int id;
    private String name;
    private List<Song> songs;
    private User user;

    public MyPlaylist() {
    }

    public MyPlaylist(int id, String name, List<Song> songs, User user) {
        this.id = id;
        this.name = name;
        this.songs = songs;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Song> getSongs() {
        return songs;
    }

    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "MyPlaylist{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", songs=" + songs +
                ", user=" + user +
                '}';
    }
}
