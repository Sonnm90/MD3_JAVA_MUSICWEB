package modal;

import java.io.Serializable;

public class Comment implements Serializable {
    private int commentId;
    private String text;
    private User user;

    public Comment() {
    }

    public Comment(int commentId, String text, User user) {
        this.commentId = commentId;
        this.text = text;
        this.user = user;
    }

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "commentId=" + commentId +
                ", text='" + text + '\'' +
                ", user=" + user +
                '}';
    }
}
