package service.band;

import config.Config;
import modal.*;

import java.util.ArrayList;
import java.util.List;

public class BandServiceIMPL implements IBandService {
    List<Band> listOfBand = new Config<Band>().readFromFile(Config.PATH_BAND);

//    public static List<Band> listOfBand = new ArrayList<>();
//
//
//    static {
//        listOfBand.add(new Band(0, "AAA", new ArrayList<>()));
//        listOfBand.add(new Band(1, "BBB", new ArrayList<>()));
//        listOfBand.add(new Band(2, "CCC", new ArrayList<>()));
//
//    }


    @Override
    public List<Band> findAll() {
        return listOfBand;
    }

    @Override
    public void save(Band band) {
        if (findById(band.getBandId())==null) {
            listOfBand.add(band);
        } else {
            listOfBand.set(listOfBand.indexOf(findById(band.getBandId())),band);
        }
        new Config<Band>().writeToFile(Config.PATH_BAND,listOfBand);
    }

    @Override
    public Band findById(int id) {
        for (int i = 0; i < listOfBand.size(); i++) {
            if (listOfBand.get(i).getBandId()==id){
                return listOfBand.get(i);
            }
        }
        return null;
    }

    @Override
    public void deleteById(int id) {
        for (int i = 0; i < listOfBand.size(); i++) {
            if (listOfBand.get(i).getBandId()==id){
                listOfBand.remove(i);
                new Config<Band>().writeToFile(Config.PATH_BAND,listOfBand);

            }
        }
    }

    @Override
    public Band findByName(String name) {
        for (int i = 0; i < listOfBand.size(); i++) {
            if (listOfBand.get(i).getBandName().toLowerCase().contains(name.toLowerCase())) {
                return listOfBand.get(i);
            }
        }
        return null;
    }

    @Override
    public List<Song> showAllSongOfBand(String name) {
        for (int i = 0; i < listOfBand.size(); i++) {
            if (listOfBand.get(i).getBandName().toLowerCase().equals(name.toLowerCase())){
                return listOfBand.get(i).getSongsOfBand();
            }
        }
        return null;
    }
}

