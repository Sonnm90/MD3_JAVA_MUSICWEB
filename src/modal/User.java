package modal;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class User implements Serializable {
    private int id;
    private String name;
    private String userName;
    private String email;
    private String password;
    private boolean status;
    private List<Song> listManage;
    private List<PlayList> playLists;
    private Set<Role> roles = new HashSet<>();
    private List<Song> myPlaylist;


    public User() {
    }

    public User(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public List<Song> getMyPlaylist() {
        return myPlaylist;
    }

    public void setMyPlaylist(List<Song> myPlaylist) {
        this.myPlaylist = myPlaylist;
    }

    public User(int id, String name, String userName, String email, String password, boolean status, List<Song> listManage, List<PlayList> playLists, Set<Role> roles, List<Song> myPlaylist) {
        this.id = id;
        this.name = name;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.status = status;
        this.listManage = listManage;
        this.playLists = playLists;
        this.roles = roles;
        this.myPlaylist = myPlaylist;
    }

//    public User(int id, String name, String userName, String email, String password, boolean status, List<Song> listManage, List<PlayList> playLists, Set<Role> roles) {
//        this.id = id;
//        this.name = name;
//        this.userName = userName;
//        this.email = email;
//        this.password = password;
//        this.status = status;
//        this.listManage = listManage;
//        this.playLists = playLists;
//        this.roles = roles;
//    }

    public User(int id, String name, String username, String email, String password, Set<Role> roleSet) {
        this.id = id;
        this.name = name;
        this.userName = username;
        this.email = email;
        this.password = password;
        this.roles = roleSet;
    }

    public List<PlayList> getPlayLists() {
        return playLists;
    }

    public void setPlayLists(List<PlayList> playLists) {
        this.playLists = playLists;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public List<Song> getListManage() {
        return listManage;
    }

    public void setListManage(List<Song> listManage) {
        this.listManage = listManage;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", status=" + status +
                ", listManage=" + listManage +
                ", roles=" + roles +
                '}';
    }
}
