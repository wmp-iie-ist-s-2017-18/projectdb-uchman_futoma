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
public class FilmDetails {
    private final IntegerProperty id_filmu;
    private final StringProperty tytul;
    private final StringProperty gatunek;
    private final StringProperty rezyser;
    private final IntegerProperty czas_trwania;
    
    public FilmDetails(int id_filmu,String tytul, String gatunek, String rezyser, int czas_trwania){
        this.id_filmu = new SimpleIntegerProperty(id_filmu);
        this.tytul = new SimpleStringProperty(tytul);
        this.gatunek = new SimpleStringProperty(gatunek);
        this.rezyser = new SimpleStringProperty(rezyser);
        this.czas_trwania = new SimpleIntegerProperty(czas_trwania);
    }
    
    //gettery

    public int getId_filmu(){
        return id_filmu.get();
    }
    
    public String getTytul(){
        return tytul.get();
    }
    
    public String getGatunek(){
        return gatunek.get();
    }
    
    public String getRezyser(){
        return rezyser.get();
    }

    public int getCzas_trwania(){
        return czas_trwania.get();
    }
    
    //settery
    
    
    public void setTytul(String value){
        tytul.set(value);
    }
    
    public void setGatunek(String value){
        gatunek.set(value);
    }
    
    public void setRezyser(String value){
        rezyser.set(value);
    }
    
    public void setCzas_trwania(int value){
        czas_trwania.set(value);
    }
    
    //property values
    
    public IntegerProperty id_filmuProperty(){
        return id_filmu;
    }
    
    public StringProperty tytulProperty(){
        return tytul;
    }

    
    public StringProperty gatunekProperty(){
        return gatunek;
    }
    
    public StringProperty rezyserProperty(){
        return rezyser;
    }
    
    
    public IntegerProperty czas_trwaniaProperty(){
        return czas_trwania;
    }
    
    
    
    
}
