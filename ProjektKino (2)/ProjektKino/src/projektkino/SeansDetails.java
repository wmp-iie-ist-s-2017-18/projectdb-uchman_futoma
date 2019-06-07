/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projektkino;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


/**
 *
 * @author Gabi
 */
public class SeansDetails {
    private final IntegerProperty id_seansu;
    private final StringProperty dzien;
    private final StringProperty godzina;
    private final IntegerProperty ilosc_wolnych_miejsc;
    
    public SeansDetails(int id_seansu,String dzien, String godzina, int ilosc_wolnych_miejsc) {
        this.id_seansu = new SimpleIntegerProperty(id_seansu);
        this.dzien = new SimpleStringProperty(dzien);
        this.godzina = new SimpleStringProperty(godzina);
        this.ilosc_wolnych_miejsc = new SimpleIntegerProperty(ilosc_wolnych_miejsc);
    }
    
    //gettery

    public int getId_seansu(){
        return id_seansu.get();
    }
    
    public String getDzien(){
        return dzien.get();
    }
    
    public String getGodzina(){
        return godzina.get();
    }

    public int getIlosc_wolnych_miejsc(){
        return ilosc_wolnych_miejsc.get();
    }
    
    
    //settery
    

    
    public void setDzien(String value){
        dzien.set(value);
    }
    
    public void setGodzina(String value){
        godzina.set(value);
    }
    
    public void setIlosc_wolnych_miejsc(int value){
        ilosc_wolnych_miejsc.set(value);
    }
    
    
    //property values
    
    public IntegerProperty id_seansuProperty(){
        return id_seansu;
    }
    
    public StringProperty dzienProperty(){
        return dzien;
    }

    
    public StringProperty godzinaProperty(){
        return godzina;
    }
    
    
    public IntegerProperty ilosc_wilnych_miejscProperty(){
        return ilosc_wolnych_miejsc;
    }
    
    
    
    
}
