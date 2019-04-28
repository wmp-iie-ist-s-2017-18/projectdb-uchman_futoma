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
public class WszystkoDetails {
    private final StringProperty nazwisko;
    private final StringProperty nr_kontaktowy;
    private final IntegerProperty ilosc_biletow;
    private final StringProperty dzien;
    private final StringProperty godzina;
    private final StringProperty tytul;
    private final IntegerProperty czas_trwania;
    private final IntegerProperty nr_sali;
    
    public WszystkoDetails (String nazwisko, String nr_kontaktowy, int ilosc_biletow, String dzien, String godzina, String tytul, int czas_trwania, int nr_sali){
        this.nazwisko = new SimpleStringProperty(nazwisko);
        this.nr_kontaktowy = new SimpleStringProperty(nr_kontaktowy);
        this.ilosc_biletow = new SimpleIntegerProperty(ilosc_biletow);
        this.dzien = new SimpleStringProperty(dzien);
        this.godzina = new SimpleStringProperty(godzina);
        this.tytul = new SimpleStringProperty(tytul);
        this.czas_trwania = new SimpleIntegerProperty(czas_trwania);
        this.nr_sali = new SimpleIntegerProperty(nr_sali);
    }
    
    //gettery

    
    public String getNazwisko(){
        return nazwisko.get();
    }
    
    public String getNr_kontaktowy(){
        return nr_kontaktowy.get();
    }

    public int getIlosc_biletow(){
        return ilosc_biletow.get();
    }
    
     public String getDzien(){
        return dzien.get();
    }
    
    public String getGodzina(){
        return godzina.get();
    }

    
    public String getTytul(){
        return tytul.get();
    }

    public int getCzas_trwania(){
        return czas_trwania.get();
    }
    
    public int getNr_sali(){
        return nr_sali.get();
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
    
    public void setDzien(String value){
        dzien.set(value);
    }
    
    public void setGodzina(String value){
        godzina.set(value);
    }

    
    public void setTytul(String value){
        tytul.set(value);
    }

    
    public void setCzas_trwania(int value){
        czas_trwania.set(value);
    }
    
    public void setNr_sali(int value){
        nr_sali.set(value);
    }

    
    //property values
    

    
    public StringProperty nazwiskoProperty(){
        return nazwisko;
    }

    
    public StringProperty nr_kontaktowyProperty(){
        return nr_kontaktowy;
    }
    
    
    public IntegerProperty ilosc_biletowProperty(){
        return ilosc_biletow;
    }
    
    public StringProperty dzienProperty(){
        return dzien;
    }

    
    public StringProperty godzinaProperty(){
        return godzina;
    }
    

    
    public StringProperty tytulProperty(){
        return tytul;
    }


    public IntegerProperty czas_trwaniaProperty(){
        return czas_trwania;
    }
    
     public IntegerProperty nr_saliProperty(){
        return nr_sali;
    }

    
    
    
    
}
