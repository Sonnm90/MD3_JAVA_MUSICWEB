package modal;

import java.util.Comparator;

public class SongViewComparator implements Comparator<Song> {

    @Override
    public int compare(Song o1, Song o2) {
        return o2.getNumberOfView()- o1.getNumberOfView();
    }
}
