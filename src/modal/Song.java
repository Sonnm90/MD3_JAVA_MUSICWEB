package modal;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Song implements Serializable {
    private int id;
    private String name;
    private List<Singer> singers;
    private Category category;
    private List<Band> bands;
    private User user;
    private int numberOfView;
    private List<User> likeUsers=new ArrayList<>();
    private List<Comment> comments;

    public Song() {
    }

    public Song(int id, String name, List<Singer> singers, Category category, List<Band> bands, User user, int numberOfView, List<User> likeUsers, List<Comment> comments) {
        this.id = id;
        this.name = name;
        this.singers = singers;
        this.category = category;
        this.bands = bands;
        this.user = user;
        this.numberOfView = numberOfView;
        this.likeUsers = likeUsers;
        this.comments = comments;
    }

//    public Song(int id, String name, List<Singer> singers, Category category, List<Band> bands, User user) {
//        this.id = id;
//        this.name = name;
//        this.singers = singers;
//        this.category = category;
//        this.bands = bands;
//        this.user = user;
//    }
//
//    public Song(int id, String name, List<Singer> singers, Category category, List<Band> bands, User user, int numberOfView, List<User> likeUsers) {
//        this.id = id;
//        this.name = name;
//        this.singers = singers;
//        this.category = category;
//        this.bands = bands;
//        this.user = user;
//        this.numberOfView = numberOfView;
//        this.likeUsers = likeUsers;
//    }

    public Song(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public List<User> getLikeUsers() {
        return likeUsers;
    }

    public void setLikeUsers(List<User> likeUsers) {
        this.likeUsers = likeUsers;
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

    public List<Singer> getSingers() {
        return singers;
    }

    public void setSingers(List<Singer> singers) {
        this.singers = singers;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Band> getBands() {
        return bands;
    }

    public void setBands(List<Band> bands) {
        this.bands = bands;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getNumberOfView() {
        return numberOfView;
    }

    public void setNumberOfView(int numberOfView) {
        this.numberOfView = numberOfView;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "Song{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", singers=" + singers +
                ", category=" + category +
                ", bands=" + bands +
                ", user=" + user +
                ", numberOfView=" + numberOfView +
                ", numberOfLike=" + likeUsers.size() +
                ", comments=" + comments +
                '}';
    }


}
