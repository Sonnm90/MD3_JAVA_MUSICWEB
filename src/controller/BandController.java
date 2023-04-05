package controller;

import modal.Band;
import modal.Singer;
import service.band.BandServiceIMPL;
import service.band.IBandService;
import service.singer.ISingerService;
import service.singer.SingerServiceIMPL;

import java.util.List;

public class BandController {
    private IBandService bandService =new BandServiceIMPL();
    public List<Band> getListBand(){
        return bandService.findAll();
    }
    public void creatBand(Band band){
        bandService.save(band);
    }
    public void updateBand(Band band){
        bandService.save(band);
    }
    public Band detailBand(int id){
        return  bandService.findById(id);
    }
    public void deleteBand(int id){
        bandService.deleteById(id);
    }
}
