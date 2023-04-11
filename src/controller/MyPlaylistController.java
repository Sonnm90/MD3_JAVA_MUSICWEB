package controller;

import modal.Comment;
import modal.MyPlaylist;
import service.comment.CommentServiceIMPL;
import service.comment.ICommentService;
import service.myplaylist.IMyPlaylistService;
import service.myplaylist.MyPlaylistServiceIMPL;

import java.util.List;

public class MyPlaylistController {
    private IMyPlaylistService myPlaylistService =new MyPlaylistServiceIMPL();
    public List<MyPlaylist> getListMyPlaylist(){
        return myPlaylistService.findAll();
    }
    public void createMyPlaylist(MyPlaylist myPlaylist){
        myPlaylistService.save(myPlaylist);
    }
    public void updateMyPlaylist(MyPlaylist myPlaylist){
        myPlaylistService.save(myPlaylist);
    }
    public MyPlaylist detailMyPlaylist(int id){
        return  myPlaylistService.findById(id);
    }
    public void deleteMyPlaylist(int id){
        myPlaylistService.deleteById(id);
    }
}
