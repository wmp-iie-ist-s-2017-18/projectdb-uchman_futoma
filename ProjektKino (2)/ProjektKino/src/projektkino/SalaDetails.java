/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projektkino;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 *
 * @author Gabi
 */
public class SalaDetails {
    private final IntegerProperty id_sali;
    private final IntegerProperty nr_sali;
    private final IntegerProperty ilosc_miejsc;
    
    public SalaDetails (int id_sali, int nr_sali, int ilosc_miejsc){
        this.id_sali = new SimpleIntegerProperty(id_sali);
        this.nr_sali = new SimpleIntegerProperty(nr_sali);
        this.ilosc_miejsc = new SimpleIntegerProperty(ilosc_miejsc);
    }
    
    //gettery
    
    public int getId_sali(){
        return id_sali.get();
    }
    
    public int getNr_sali(){
        return nr_sali.get();
    }
    
    public int getIlosc_miejsc(){
        return ilosc_miejsc.get();
    }
    
    //settery
    
    
    public void setNr_sali(int value){
        nr_sali.set(value);
    }
    
    public void setIlosc_miejsc(int value){
        ilosc_miejsc.set(value);
    }
    
    //property values
    
    public IntegerProperty id_saliProperty(){
        return id_sali;
    }
    
    public IntegerProperty nr_saliProperty(){
        return nr_sali;
    }
    
    public IntegerProperty ilosc_miejscProperty(){
        return ilosc_miejsc;
    }
    
    
    
}
