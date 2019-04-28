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
public class RezerwacjaDetails {
    private final IntegerProperty id_rezerwacji;
    private final StringProperty nazwisko;
    private final StringProperty nr_kontaktowy;
    private final IntegerProperty ilosc_biletow;
    
    

    public RezerwacjaDetails(int id_rezerwacji, String nazwisko, String nr_kontaktowy, int ilosc_biletow) {
        this.id_rezerwacji = new SimpleIntegerProperty(id_rezerwacji);
        this.nazwisko = new SimpleStringProperty(nazwisko);
        this.nr_kontaktowy = new SimpleStringProperty(nr_kontaktowy);
        this.ilosc_biletow = new SimpleIntegerProperty(ilosc_biletow);
    }

    //gettery

    public int getId_rezerwacji(){
        return id_rezerwacji.get();
    }
    
    public String getNazwisko(){
        return nazwisko.get();
    }
    
    public String getNr_kontaktowy(){
        return nr_kontaktowy.get();
    }

    public int getIlosc_biletow(){
        return ilosc_biletow.get();
    }
        
    
    //settery
    

    
    public void setNazwisko(String value){
        nazwisko.set(value);
    }
    
    public void setNr_kontaktowy(String value){
        nr_kontaktowy.set(value);
    }
    
    public void setIlosc_biletow(int value){
        ilosc_biletow.set(value);
    }
       

    
    //property values
    
    public IntegerProperty id_rezerwacjiProperty(){
        return id_rezerwacji;
    }
    
    public StringProperty nazwiskoProperty(){
        return nazwisko;
    }

    
    public StringProperty nr_kontaktowyProperty(){
        return nr_kontaktowy;
    }
    
    
    public IntegerProperty ilosc_biletowProperty(){
        return ilosc_biletow;
    }
    
}
