package controller;

import modal.Singer;
import service.singer.ISingerService;
import service.singer.SingerServiceIMPL;

import java.util.List;

public class SingerController {
    private ISingerService singerService =new SingerServiceIMPL();
    public List<Singer> getListSinger(){
        return singerService.findAll();
    }
    public void creatSinger(Singer singer){
        singerService.save(singer);
    }
    public void updateSinger(Singer singer){
        singerService.save(singer);
    }
    public Singer detailSinger(int id){
       return  singerService.findById(id);
    }
    public void deleteSinger(int id){
        singerService.deleteById(id);
    }
}
