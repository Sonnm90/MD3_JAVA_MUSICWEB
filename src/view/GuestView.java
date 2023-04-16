package view;

import config.Config;
import controller.*;
import dto.response.ResponseMessage;
import modal.*;
import service.song.ISongService;
import service.song.SongServiceIMPL;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GuestView {
    SongController songController = new SongController();
    List<Song> songList = songController.getListSong();
    SingerController singerController = new SingerController();
    CategoryController categoryController = new CategoryController();
    BandController bandController = new BandController();
    PlayListController playListController = new PlayListController();
    UserController userController = new UserController();
    User user = userController.getUserLogin();
    CommentController commentController = new CommentController();


    public void showListSong() {
        for (Song song : songController.getListSong()
        ) {
            System.out.println("Id: "+song.getId()+" "+"Name: "+song.getName());

        }
        System.out.println("Enter Id of Song to show detail ");
        int choice = Config.scanner().nextInt();
        detailSong(choice);
        System.out.println("Enter any key to continue or back to back MainMenu");
        String backMenu = Config.scanner().nextLine();
        if (backMenu.equalsIgnoreCase("back")) {
            new Navbar();
        } else {
            showListSong();
        }
    }

    public void detailSong(int choice) {
        if (songController.detailSong(choice) != null) {
            Song song = songController.detailSong(choice);
            System.out.println("Name of Song: " + song.getName());
            System.out.println(song);
            System.out.println("Number of Like: "+songController.detailSong(choice).getLikeUsers().size());
            System.out.println("Number of View: "+songController.detailSong(choice).getNumberOfView());
            if (song.getComments() != null) {
                for (Comment comment : song.getComments()) {
                    System.out.printf(comment.getUser().getUserName() + " :");
                    System.out.printf(comment.getText());
                    System.out.println();
                }
            }
            if (user != null) {
                User userCheck = checkLike(songController.detailSong(choice), user);
                System.out.println("1. Play");
                System.out.println(userCheck == null ? "2. Like" : "2. Dislike");
                System.out.println("3. Comment");
                System.out.println("Enter your choice or any char to back previewMenu");
                String back = Config.scanner().nextLine();
                if (back == null || back.trim().isEmpty() || Character.isLetter(back.charAt(0))) {
                    new Navbar();
                } else {
                    int choose = Integer.parseInt(back);
                    switch (choose) {
                        case 1:
                            playSong();
                            song.setNumberOfView(song.getNumberOfView() + 1);
                            songController.updateSong(song);
                            break;
                        case 2:
                            if (userCheck != null) {
                                for (int i = 0; i < song.getLikeUsers().size(); i++) {
                                    if (userCheck.getId() == song.getLikeUsers().get(i).getId()) {
                                        song.getLikeUsers().remove(i);
                                    }
                                }
                            } else {
                                song.getLikeUsers().add(user);
                            }
                            songController.updateSong(song);
                            System.out.println("Success");
                            break;
                        case 3:
                            addComment(song, user);
                            System.out.println("Enter a number to continue or any char to back previewMenu");
                            back = Config.scanner().nextLine();
                            if (back == null || back.trim().isEmpty() || Character.isLetter(back.charAt(0))) {
                                new Navbar();
                            } else {
                                showListSong();
                            }
                    }
                }
            } else {
                System.out.println("1. Play");
                System.out.println("Enter your choice or any char to back previewMenu");
                String back = Config.scanner().nextLine();
                if (back == null || back.trim().isEmpty() || Character.isLetter(back.charAt(0))) {
                    new Navbar();
                } else {
                    int choose = Integer.parseInt(back);
                    switch (choose) {
                        case 1:
                            playSong();
                            song.setNumberOfView(song.getNumberOfView() + 1);
                            songController.updateSong(song);
                            System.out.println("Enter back to back Menu");
                            back = Config.scanner().nextLine();
                            if (back.equalsIgnoreCase("back")) {
                                new Navbar();
                            } else {
                                showListSong();
                            }

                    }
                }
            }
        } else {
            System.out.println("Not Found!");
        }
    }

    public void playSong() {
        System.out.println("Playing......1");
        System.out.println("Playing......2");
        System.out.println("Playing......3");
        System.out.println("Playing......4");
        System.out.println("Playing......5");
    }

    public Song showDetailSong(int id) {
        return songController.detailSong(id);

    }

    public void showListSinger() {
        if (singerController.getListSinger().size() != 0) {
            for (Singer singer : singerController.getListSinger()
            ) {
                System.out.println(singer);
            }
            System.out.println("Enter Id of Singer to show details");
            int choice = Config.scanner().nextInt();
            if (singerController.detailSinger(choice)!=null) {
                System.out.println("Song of Singer: " + singerController.detailSinger(choice).getSingerName());
                List<Song> songs = singerController.detailSinger(choice).getSongsOfSinger();
//                System.out.println(songs);
                if (songs.size()>0) {
                    for (Song song : songs
                    ) {
                        System.out.println(song);
                    }
                    System.out.println("Enter Id of Song to show or any char to back previewMenu");
                    String back = Config.scanner().nextLine();
                    if (back == null || back.trim().isEmpty() || Character.isLetter(back.charAt(0))) {
                        new Navbar();
                    } else {
                        int idShow = Integer.parseInt(back);
                        detailSong(idShow);
                    }
                } else {
                    System.out.println("Data is empty");
                    System.out.println("Enter any key to continue or back to back MainMenu");
                    String backMenu = Config.scanner().nextLine();
                    if (backMenu.equalsIgnoreCase("back")) {
                        new Navbar();
                    } else {
                        showListSinger();
                    }
                }
            } else {
                System.out.println("Not Found!");
            }
            System.out.println("Enter any key to continue or back to back MainMenu");
            String backMenu = Config.scanner().nextLine();
            if (backMenu.equalsIgnoreCase("back")) {
                new Navbar();
            } else {
                showListSinger();
            }
        } else {
            System.err.println("not found");
            while (true) {
                System.out.println("Enter back to back MainMenu");
                String backMenu = Config.scanner().nextLine();
                if (backMenu.equalsIgnoreCase("back")) {
                    new Navbar();
                    break;
                }
            }
        }
    }

    public List<Song> showDetailSinger(int id) {
        return singerController.detailSinger(id).getSongsOfSinger();
    }

    public void showListCategory() {
        if (categoryController.getListCategory().size() != 0) {
            for (Category category : categoryController.getListCategory()
            ) {
                System.out.println(category);
            }
            System.out.println("Enter Id of Category to show details");
            int choice = Config.scanner().nextInt();
            if (choice < categoryController.getListCategory().size() && choice >= 0) {
                System.out.println("Song of Category: " + categoryController.getListCategory().get(choice).getCategoryName());
                for (Song song : showDetailCategory(choice)
                ) {
                    System.out.println(song);
                }
                System.out.println("Enter Id of Song to show or any char to back previewMenu");
                String back = Config.scanner().nextLine();
                if (back == null || back.trim().isEmpty() || Character.isLetter(back.charAt(0))) {
                    new Navbar();
                } else {
                    int idShow = Integer.parseInt(back);
                    detailSong(idShow);
                }
            } else {
                System.out.println("Not Found!");
            }
            System.out.println("Enter any key to continue or back to back MainMenu");
            String backMenu = Config.scanner().nextLine();
            if (backMenu.equalsIgnoreCase("back")) {
                new Navbar();
            } else {
                showListCategory();
            }
        } else {
            System.err.println("not found");
            while (true) {
                System.out.println("Enter back to back MainMenu");
                String backMenu = Config.scanner().nextLine();
                if (backMenu.equalsIgnoreCase("back")) {
                    new Navbar();
                    break;
                }
            }
        }
    }

    public List<Song> showDetailCategory(int id) {
        List<Song> list = new ArrayList<>();
        for (int i = 0; i < songController.getListSong().size(); i++) {
            if (songController.getListSong().get(i).getCategory().getCategoryName().equals(categoryController.detailCategory(id).getCategoryName())) {
                list.add(songController.getListSong().get(i));
            }
        }
        return list;
    }

    public void showListBand() {
//        System.out.println(bandController.getListBand());
        if (bandController.getListBand().size() != 0) {
            for (Band band : bandController.getListBand()
            ) {
                System.out.println("ID: "+band.getBandId()+" "+"Name: "+band.getBandName());
            }
            System.out.println("Enter Id of Band to show details");
            int choice = Config.scanner().nextInt();
            if (bandController.detailBand(choice) != null) {
                System.out.println("Song of Band: " + bandController.detailBand(choice).getSongsOfBand());
                for (Song song : showDetailBand(choice)
                ) {
                    System.out.println(song);
                }
                while (true) {
                    System.out.println("Enter Id of Song to show or any char to back previewMenu");
                    String back = Config.scanner().nextLine();
                    if (back == null || back.trim().isEmpty() || Character.isLetter(back.charAt(0))) {
                        new Navbar();
                    } else {
                        boolean check = false;
                        int idShow = Integer.parseInt(back);
                        for (int i = 0; i < bandController.detailBand(choice).getSongsOfBand().size(); i++) {
                            if (idShow == bandController.detailBand(choice).getSongsOfBand().get(i).getId()) {
                                check = true;
                                break;
                            }
                        }
                        if (check) {
                            detailSong(idShow);
                        }

                    }
                }
            } else {
                System.err.println("not found");
                while (true) {
                    System.out.println("Enter back to back MainMenu");
                    String backMenu = Config.scanner().nextLine();
                    if (backMenu.equalsIgnoreCase("back")) {
                        new Navbar();
                        break;
                    }
                }
            }
        }
    }
    public List<Song> showDetailBand(int id) {
        return bandController.detailBand(id).getSongsOfBand();
    }

    public void showAllPlaylist() {
        boolean check = false;
        if (playListController.getListPlaylist().size() != 0) {
            for (PlayList playList : playListController.getListPlaylist()
            ) {
                if (playList.isStatus()) {
                    System.out.println(playList);
                    check=true;
                }
            }
            if (check) {
                System.out.println("Enter Id of Playlist to show details");
                int choice = Config.scanner().nextInt();
                if (playListController.detailPlaylist(choice) != null) {
                    System.out.println("Song of Playlist: " + playListController.detailPlaylist(choice));
                    for (Song song : showDetailPlaylist(choice)
                    ) {
                        System.out.println(song);
                    }
                    System.out.println("Enter Id of Song to show or any char to back previewMenu");
                    String back = Config.scanner().nextLine();
                    if (back == null || back.trim().isEmpty() || Character.isLetter(back.charAt(0))) {
                        new Navbar();
                    } else {
                        int idShow = Integer.parseInt(back);
                        detailSong(idShow);
                    }
                } else {
                    System.out.println("Not Found!");
                }
                System.out.println("Enter any key to continue or back to back MainMenu");
                String backMenu = Config.scanner().nextLine();
                if (backMenu.equalsIgnoreCase("back")) {
                    new Navbar();
                } else {
                    showAllPlaylist();
                }
            } else {
                System.out.println("Data empty");
                System.out.println("Enter any key to continue or back to back MainMenu");
                String backMenu = Config.scanner().nextLine();
                if (backMenu.equalsIgnoreCase("back")) {
                    new Navbar();
                } else {
                    showAllPlaylist();
                }
            }
        } else {
            System.err.println("not found");
            while (true) {
                System.out.println("Enter back to back MainMenu");
                String backMenu = Config.scanner().nextLine();
                if (backMenu.equalsIgnoreCase("back")) {
                    new Navbar();
                    break;
                }
            }
        }
    }

    public List<Song> showDetailPlaylist(int id) {
        return playListController.detailPlaylist(id).getPlaylistSongs();
    }

    public User checkLike(Song song, User user) {
        for (int i = 0; i < song.getLikeUsers().size(); i++) {
            if (user.getId() == song.getLikeUsers().get(i).getId()) {
                return user;
            }
        }
        return null;
    }

    public void addComment(Song song, User user) {
        int id = 0;
        System.out.println("Enter your comment");
        String commentText = Config.scanner().nextLine();
        if (commentController.getListComment().size() == 0) {
            id = 1;
        } else {
            id = commentController.getListComment().get(commentController.getListComment().size() - 1).getCommentId() + 1;
        }
        Comment comment = new Comment(id, commentText, user);
        commentController.creatComment(comment);
        song.getComments().add(comment);
        songController.updateSong(song);
        System.out.println("Comment Success");
    }

    public void showTopViewSong() {
        List<Song> newList = new ArrayList<>(songList);
        Collections.sort(newList, new SongViewComparator());
        System.out.println("TOP 10 SONG OF VIEWS:");
        if (newList.size() > 10) {
            for (int i = 0; i < 10; i++) {
                System.out.println("Id: "+ newList.get(i).getId()+" "+"Name: "+newList.get(i).getName()+" "+"View: "+newList.get(i).getNumberOfView());
            }
        } else {
            for (Song song : newList) {
                System.out.println("Id: "+ song.getId()+" "+"Name: "+song.getName()+" "+"View: "+song.getNumberOfView());

            }
        }
        System.out.println("Enter Id of Song to show or any char to back previewMenu");
        String back = Config.scanner().nextLine();
        if (back == null || back.trim().isEmpty() || Character.isLetter(back.charAt(0))) {
            new Navbar();
        } else {
            int idShow = Integer.parseInt(back);
            detailSong(idShow);
        }
    }

    public void showTopLikeSong() {
        List<Song> newList = new ArrayList<>(songList);
        Collections.sort(newList, new SongLikeComparator());
        System.out.println("TOP 10 SONG OF LIKES:");
        if (newList.size() > 10) {
            for (int i = 0; i < 10; i++) {
                System.out.println("Id: "+ newList.get(i).getId()+" "+"Name: "+newList.get(i).getName()+" "+"View: "+newList.get(i).getLikeUsers().size());

            }
        } else {
            for (Song song : newList) {
                System.out.println("Id: "+song.getId()+" "+"Name: "+song.getName()+" "+"View: "+song.getLikeUsers().size());

            }
        }
        System.out.println("Enter Id of Song to show or any char to back previewMenu");
        String back = Config.scanner().nextLine();
        if (back == null || back.trim().isEmpty() || Character.isLetter(back.charAt(0))) {
            new Navbar();
        } else {
            int idShow = Integer.parseInt(back);
            detailSong(idShow);
        }
    }
}
