package service.comment;

import config.Config;
import modal.Category;
import modal.Comment;

import java.util.List;

public class CommentServiceIMPL implements ICommentService{
    List<Comment> commentList = new Config<Comment>().readFromFile(Config.PATH_COMMENT);

    @Override
    public List<Comment> findAll() {
        return commentList;
    }

    @Override
    public void save(Comment comment) {
        if (findById(comment.getCommentId())==null) {
            commentList.add(comment);
        } else {
            commentList.set(commentList.indexOf(findById(comment.getCommentId())),comment);
        }
        new Config<Comment>().writeToFile(Config.PATH_COMMENT, commentList);
    }

    @Override
    public Comment findById(int id) {
        for (int i = 0; i < commentList.size(); i++) {
            if (commentList.get(i).getCommentId()==id){
                return commentList.get(i);
            }
        }
        return null;
    }

    @Override
    public void deleteById(int id) {
        for (int i = 0; i < commentList.size(); i++) {
            if (commentList.get(i).getCommentId()==id){
                commentList.remove(i);
                new Config<Comment>().writeToFile(Config.PATH_COMMENT,commentList);
            }
        }
    }
}
