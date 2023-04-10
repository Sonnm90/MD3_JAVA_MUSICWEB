package modal;

import java.io.Serializable;
import java.util.List;

public class Singer implements Serializable {
    private int singerId;
    private  String singerName;
    private List<Song> songsOfSinger;

    public Singer() {
    }

    public Singer(int singerId, String singerName, List<Song> songsOfSinger) {
        this.singerId = singerId;
        this.singerName = singerName;
        this.songsOfSinger = songsOfSinger;
    }

    public int getSingerId() {
        return singerId;
    }

    public void setSingerId(int singerId) {
        this.singerId = singerId;
    }

    public String getSingerName() {
        return singerName;
    }

    public void setSingerName(String singerName) {
        this.singerName = singerName;
    }

    public List<Song> getSongsOfSinger() {
        return songsOfSinger;
    }

    public void setSongsOfSinger(List<Song> songsOfSinger) {
        this.songsOfSinger = songsOfSinger;
    }

    @Override
    public String toString() {
        return "Singer{" +
                "singerId=" + singerId +
                ", singerName='" + singerName + '\'' +

                '}';
    }
}
