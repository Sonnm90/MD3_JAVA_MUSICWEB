package controller;

import modal.Band;
import modal.Comment;
import service.band.BandServiceIMPL;
import service.band.IBandService;
import service.comment.CommentServiceIMPL;
import service.comment.ICommentService;

import java.util.List;

public class CommentController {
    private ICommentService commentService =new CommentServiceIMPL();
    public List<Comment> getListComment(){
        return commentService.findAll();
    }
    public void creatComment(Comment comment){
        commentService.save(comment);
    }
    public void updateComment(Comment comment){
        commentService.save(comment);
    }
    public Comment detailComment(int id){
        return  commentService.findById(id);
    }
    public void deleteComment(int id){
        commentService.deleteById(id);
    }
}
