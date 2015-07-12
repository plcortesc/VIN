package edu.iit.cs445.Management;

import java.util.ArrayList;

import edu.iit.cs445.vin.Wine;

public class MsWinePost {
    
    public ArrayList<Wine> wines = new ArrayList<Wine>();
    
    public MsWinePost() {}
    
    public void setWines(ArrayList<Wine> wines){
        this.wines = wines;
    }
    
    public ArrayList<Wine> getWines(){
        return this.wines;
    }
    
    public void RWaddWines(){
        Wine wr = new Wine();
        Wine ww = new Wine(WineVariety.WHITE, WineType.SWEET, "hola", "Hello", "Ni hao", "Spain", "Sterling", 2000);
        for(int i=0;i<3;i++){
            wines.add(wr);
        }
        for(int i=0;i<3;i++){
            wines.add(ww);
        }
    }
    
    public void ARaddWines(){
        Wine wr = new Wine();
        for(int i=0;i<6;i++){
            wines.add(wr);
        }
    }
    public void AWaddWines(){
        Wine ww = new Wine(WineVariety.WHITE, WineType.SWEET, "hola", "Hello", "Ni hao", "Spain", "Sterling", 2000);
        for(int i=0;i<6;i++){
            wines.add(ww);
        }
    }
}
