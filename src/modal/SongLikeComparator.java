package modal;

import java.util.Comparator;

public class SongLikeComparator implements Comparator<Song> {
    @Override
    public int compare(Song o1, Song o2) {
        return o2.getLikeUsers().size()-o1.getLikeUsers().size();
    }
}
