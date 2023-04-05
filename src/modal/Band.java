package modal;

import java.io.Serializable;
import java.util.List;

public class Band implements Serializable {
    private int bandId;
    private String bandName;
    private List<Song> songsOfBand;

    public Band() {
    }

    public Band(int bandId, String bandName, List<Song> songsOfBand) {
        this.bandId = bandId;
        this.bandName = bandName;
        this.songsOfBand = songsOfBand;
    }

    public int getBandId() {
        return bandId;
    }

    public void setBandId(int bandId) {
        this.bandId = bandId;
    }

    public String getBandName() {
        return bandName;
    }

    public void setBandName(String bandName) {
        this.bandName = bandName;
    }

    public List<Song> getSongsOfBand() {
        return songsOfBand;
    }

    public void setSongsOfBand(List<Song> songsOfBand) {
        this.songsOfBand = songsOfBand;
    }

    @Override
    public String toString() {
        return "Band{" +
                "bandId=" + bandId +
                ", bandName='" + bandName + '\'' +
                ", songsOfBand=" + songsOfBand +
                '}';
    }
}
