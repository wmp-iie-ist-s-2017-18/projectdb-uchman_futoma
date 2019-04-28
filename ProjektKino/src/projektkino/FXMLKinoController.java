/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projektkino;

import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

/**
 *
 * @author Gabi
 */
public class FXMLKinoController implements Initializable {

    @FXML
    private Button buttonLoad;
    //initialize observable list to hold out database data

    private ObservableList<RezerwacjaDetails> dataRezerwacje;
    private ObservableList<SeansDetails> dataSeans;
    private ObservableList<FilmDetails> dataFilm;
    private ObservableList<SalaDetails> dataSala;
    private ObservableList<WszystkoDetails> dataWszystko;
    private final ObservableList cbFilmy = FXCollections.observableArrayList();
    private final ObservableList cbDzien = FXCollections.observableArrayList();
    private final ObservableList cbSala = FXCollections.observableArrayList();
    private final ObservableList cbGodzina = FXCollections.observableArrayList();

    private DbPolaczenie dc;

    @FXML
    private TabPane TabPaneDB;
    @FXML
    private Tab tabZamowienie;
    @FXML
    private Tab tabFilm;
    @FXML
    private Tab tabSeans;
    @FXML
    private Tab tabWszystko;
    @FXML
    private TableView<RezerwacjaDetails> tableRezerwacje;
    @FXML
    private TableColumn<RezerwacjaDetails, String> columnNazwisko;
    @FXML
    private TableColumn<RezerwacjaDetails, String> columnNumer;
    @FXML
    private TableColumn<RezerwacjaDetails, Integer> columnIleBiletow;
    @FXML
    private TableView<SeansDetails> tableSeans;
    @FXML
    private TableColumn<SeansDetails, String> columnDzien;
    @FXML
    private TableColumn<SeansDetails, String> columnGodzina;
    @FXML
    private TableColumn<SeansDetails, Integer> columnWolneMiejsca;
    @FXML
    private TableView<FilmDetails> tableFilm;
    @FXML
    private TableColumn<FilmDetails, String> columnTytul;
    @FXML
    private TableColumn<FilmDetails, String> columnGatunek;
    @FXML
    private TableColumn<FilmDetails, String> columnRezyser;
    @FXML
    private TableColumn<FilmDetails, Integer> columnCzasTrwania;
    @FXML
    private Tab tabSale;
    @FXML
    private TableView<SalaDetails> tableSale;
    @FXML
    private TableColumn<SalaDetails, Integer> columnNrSali;
    @FXML
    private TableColumn<SalaDetails, Integer> columnPojemnosc;
    @FXML
    private TableView<WszystkoDetails> tableWszystko;
    @FXML
    private TableColumn<WszystkoDetails, String> columnNazwisko1;
    @FXML
    private TableColumn<WszystkoDetails, String> columnNumer1;
    @FXML
    private TableColumn<WszystkoDetails, Integer> columnIleBiletow1;
    @FXML
    private TableColumn<WszystkoDetails, String> columnDzien1;
    @FXML
    private TableColumn<WszystkoDetails, String> columnGodzina1;
    @FXML
    private TableColumn<WszystkoDetails, String> columnTytul1;
    @FXML
    private TableColumn<WszystkoDetails, Integer> columnCzasTrwania1;
    @FXML
    private TableColumn<WszystkoDetails, Integer> columnNrSali1;
    @FXML
    private AnchorPane paneRezerwacja;
    @FXML
    private Label labelRezerwacja;
    @FXML
    private GridPane gridpaneRezerwacja;
    @FXML
    private Label labelNazwisko;
    @FXML
    private Label labelNumerTelefonu;
    @FXML
    private Label labelIloscBiletow;
    @FXML
    private Label labelNumerSali;
    @FXML
    private ComboBox comboboxFilm;
    @FXML
    private ComboBox comboboxDzien;
    @FXML
    private AnchorPane paneFilm;
    @FXML
    private Button buttonZatwierdzRezerwacje;
    @FXML
    private Label labelFilm;
    @FXML
    private GridPane gridpaneFilm;
    @FXML
    private Label labelTytul;
    @FXML
    private Label labelGatunek;
    @FXML
    private Label labelRezyser;
    @FXML
    private Label labelCzasTrwania;
    @FXML
    private Label labelDzien;
    @FXML
    private Label labelGodzina;
    @FXML
    private Label labelSala;
    @FXML
    private TextField textfieldTytul;
    @FXML
    private TextField textfieldGatunek;
    @FXML
    private TextField textfieldRezyser;
    @FXML
    private TextField textfieldCzasTrwania;
    @FXML
    private TextField textfieldGodzina;
    @FXML
    private Button buttonZatwierdzFilm;
    @FXML
    private Separator separatorPionowy1;
    @FXML
    private Button buttonUsunZaznaczone;
    @FXML
    private Button buttonWyczyscPola;
    @FXML
    private TextField textfieldNazwisko;
    @FXML
    private TextField textfieldNrtel;
    @FXML
    private TextField textfieldIloscBiletow;
    @FXML
    private Button buttonWyjscie;
    @FXML
    private Label labelSeans;
    @FXML
    private GridPane gridpaneSeans;
    @FXML
    private DatePicker dtDataSeansu;
    @FXML
    private ComboBox comboboxFilm1;
    @FXML
    private Button buttonSeans;
    @FXML
    private GridPane gridpaneSala;
    @FXML
    private TextField textfieldIloscMiejsc;
    @FXML
    private Label labelIloscMiejsc;
    @FXML
    private Button buttonZatwierdzSala;
    @FXML
    private ComboBox comboboxSala;
    @FXML
    private TextField textfieldNumerSali;
    private Label labelGodzinaSeansu;
    @FXML
    private ComboBox comboboxGodzina;
    @FXML
    private Button buttonAktualizujSala;
    @FXML
    private Button buttonAktualizujRezerwacja;
    @FXML
    private Button buttonAktualizujFilm;
    @FXML
    private Button buttonAktualizujSeans;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        dc = new DbPolaczenie();
        dtDataSeansu.setEditable(false);
        
        
    }
    
    boolean doUpdateSala = false;
    boolean doUpdateFilm = false;
    boolean doUpdateSeans = false;
    boolean doUpdateRezerwacja = false;

    public void fillComboBox() {
        Connection connec = dc.Connect();
        //comboboxFilm.setItems(null);

        try {
            ResultSet rsCb = connec.createStatement().executeQuery("SELECT id_filmu, tytul FROM film");
            ResultSet rsCb2 = connec.createStatement().executeQuery("SELECT nr_sali FROM sala");

            cbFilmy.clear();
            cbDzien.clear();
            cbSala.clear();
            cbGodzina.clear();

            while (rsCb.next()) {
                cbFilmy.add(rsCb.getString("tytul"));
            }
            comboboxFilm1.setItems(null);
            comboboxFilm1.setItems(cbFilmy);

            while (rsCb2.next()) {
                cbSala.add(rsCb2.getInt(1));
            }

            comboboxSala.setItems(null);
            comboboxSala.setItems(cbSala);

            comboboxFilm.setItems(null);
            comboboxFilm.setItems(cbFilmy);
            //comboboxFilm.setValue(null);

            comboboxFilm.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
                @Override
                public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                    comboboxDzien.setItems(null);
                    comboboxDzien.setValue(null);

                    comboboxGodzina.setItems(null);
                    if (newValue != null) {
                        String temp = newValue.toString();
                        Connection connect = dc.Connect();
                        cbDzien.clear();
                        try {

                            ResultSet rsCb2 = connect.createStatement().executeQuery("SELECT distinct dzien FROM Seans,film WHERE film.tytul='" + temp + "' AND film.id_filmu=Seans.id_filmu");
                            while (rsCb2.next()) {
                                cbDzien.add(rsCb2.getString("dzien"));
                            }

                            comboboxDzien.setItems(cbDzien);

                            connect.close();

                        } catch (SQLException ex) {
                            Logger.getLogger(FXMLKinoController.class.getName()).log(Level.SEVERE, null, ex);
                        }

                        comboboxDzien.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
                            @Override
                            public void changed(ObservableValue observable2, Object oldValue2, Object newValue2) {

                                comboboxGodzina.setItems(null);
                                comboboxGodzina.setValue(null);
                                //String temp2 = newValue.toString();
                                if (newValue2 != null) {
                                    try {
                                        Connection connect2 = dc.Connect();
                                        ResultSet rsL = connect2.createStatement().executeQuery("SELECT godzina FROM Seans,film,sala WHERE Seans.id_filmu=film.id_filmu AND film.tytul='" + temp + "' AND Seans.id_sali=sala.id_sali AND Seans.dzien='" + newValue2.toString() + "'");
                                        cbGodzina.clear();
                                        while (rsL.next()) {
                                            cbGodzina.add(rsL.getString("godzina"));
                                        }

                                        comboboxGodzina.setItems(cbGodzina);

                                        rsL.close();
                                        connect2.close();

                                    } catch (SQLException ex) {
                                        Logger.getLogger(FXMLKinoController.class.getName()).log(Level.SEVERE, null, ex);
                                    }

                                }
                            }
                        });
                    }

                }

            });
            rsCb2.close();

            rsCb.close();
            connec.close();

        } catch (SQLException | NullPointerException ex) {
            Logger.getLogger(FXMLKinoController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void loadDataFromDatabase(ActionEvent event) {
        Connection conn = dc.Connect();
        dataRezerwacje = FXCollections.observableArrayList();
        dataSeans = FXCollections.observableArrayList();
        dataFilm = FXCollections.observableArrayList();
        dataSala = FXCollections.observableArrayList();
        dataWszystko = FXCollections.observableArrayList();


        // ustawic napis w ComboBoxach po kliknieciu "zatwierdz", przycisk update itd itd :D
        try {
            wyczyscPola();
            fillComboBox();
            //zapytanie i przechowanie wyniku w resultsecie
            ResultSet rsRezerwacje = conn.createStatement().executeQuery("SELECT id_rezerwacji, nazwisko, nr_kontaktowy, ilosc_biletow FROM rezerwacja");
            ResultSet rsSeans = conn.createStatement().executeQuery("SELECT id_seansu, dzien, godzina, ilosc_wolnych_miejsc FROM Seans");  //  ustawienie wolnych miejsc
            ResultSet rsFilm = conn.createStatement().executeQuery("SELECT id_filmu, tytul, gatunek, rezyser, czas_trwania FROM film");
            ResultSet rsSala = conn.createStatement().executeQuery("SELECT id_sali, nr_sali, ilosc_miejsc FROM sala");
            ResultSet rsWszystko = conn.createStatement().executeQuery("SELECT rezerwacja.nazwisko, rezerwacja.nr_kontaktowy, rezerwacja.ilosc_biletow, Seans.dzien, Seans.godzina, film.tytul, film.czas_trwania, sala.nr_sali FROM rezerwacja, Seans, film, sala WHERE rezerwacja.id_seansu=Seans.id_seansu AND Seans.id_sali=sala.id_sali AND Seans.id_filmu=film.id_filmu");
            //int odswiez = conn.createStatement().executeUpdate("UPDATE Seans, sala SET Seans.ilosc_wolnych_miejsc = sala.ilosc_miejsc - (SELECT SUM(rezerwacja.ilosc_biletow) FROM rezerwacja WHERE Seans.id_seansu = rezerwacja.id_seansu GROUP BY rezerwacja.id_rezerwacji) WHERE Seans.id_sali = sala.id_sali");

            while (rsRezerwacje.next()) {
                dataRezerwacje.add(new RezerwacjaDetails(rsRezerwacje.getInt(1), rsRezerwacje.getString(2), rsRezerwacje.getString(3), rsRezerwacje.getInt(4)));
            }
            while (rsSeans.next()) {
                dataSeans.add(new SeansDetails(rsSeans.getInt(1), rsSeans.getString(2), rsSeans.getString(3), rsSeans.getInt(4)));
            }
            while (rsFilm.next()) {
                dataFilm.add(new FilmDetails(rsFilm.getInt(1), rsFilm.getString(2), rsFilm.getString(3), rsFilm.getString(4), rsFilm.getInt(5)));
            }
            while (rsSala.next()) {
                dataSala.add(new SalaDetails(rsSala.getInt(1), rsSala.getInt(2), rsSala.getInt(3)));
            }
            while (rsWszystko.next()) {
                dataWszystko.add(new WszystkoDetails(rsWszystko.getString(1), rsWszystko.getString(2), rsWszystko.getInt(3), rsWszystko.getString(4), rsWszystko.getString(5), rsWszystko.getString(6), rsWszystko.getInt(7), rsWszystko.getInt(8)));
            }

            conn.close();
            rsRezerwacje.close();
            rsSeans.close();
            rsFilm.close();
            rsSala.close();
            rsWszystko.close();

        } catch (SQLException ex) {
            System.err.println("Error" + ex);
        }

        //set cell value factory to tableview
        //NB.PropertyValue Factory must be the same with the one set in model class
        columnNazwisko.setCellValueFactory(new PropertyValueFactory<>("nazwisko"));
        columnNumer.setCellValueFactory(new PropertyValueFactory<>("nr_kontaktowy"));
        columnIleBiletow.setCellValueFactory(new PropertyValueFactory<>("ilosc_biletow"));

        tableRezerwacje.setItems(null);
        tableRezerwacje.setItems(dataRezerwacje);

        columnDzien.setCellValueFactory(new PropertyValueFactory<>("dzien"));
        columnGodzina.setCellValueFactory(new PropertyValueFactory<>("godzina"));
        columnWolneMiejsca.setCellValueFactory(new PropertyValueFactory<>("ilosc_wolnych_miejsc"));

        tableSeans.setItems(null);
        tableSeans.setItems(dataSeans);

        columnTytul.setCellValueFactory(new PropertyValueFactory<>("tytul"));
        columnGatunek.setCellValueFactory(new PropertyValueFactory<>("gatunek"));
        columnRezyser.setCellValueFactory(new PropertyValueFactory<>("rezyser"));
        columnCzasTrwania.setCellValueFactory(new PropertyValueFactory<>("czas_trwania"));

        tableFilm.setItems(null);
        tableFilm.setItems(dataFilm);

        columnNrSali.setCellValueFactory(new PropertyValueFactory<>("nr_sali"));
        columnPojemnosc.setCellValueFactory(new PropertyValueFactory<>("ilosc_miejsc"));

        tableSale.setItems(null);
        tableSale.setItems(dataSala);

        columnNazwisko1.setCellValueFactory(new PropertyValueFactory<>("nazwisko"));
        columnNumer1.setCellValueFactory(new PropertyValueFactory<>("nr_kontaktowy"));
        columnIleBiletow1.setCellValueFactory(new PropertyValueFactory<>("ilosc_biletow"));
        columnDzien1.setCellValueFactory(new PropertyValueFactory<>("dzien"));
        columnGodzina1.setCellValueFactory(new PropertyValueFactory<>("godzina"));
        columnTytul1.setCellValueFactory(new PropertyValueFactory<>("tytul"));
        columnCzasTrwania1.setCellValueFactory(new PropertyValueFactory<>("czas_trwania"));
        columnNrSali1.setCellValueFactory(new PropertyValueFactory<>("nr_sali"));

        tableWszystko.setItems(null);
        tableWszystko.setItems(dataWszystko);

    }

    @FXML
    private void wyczyscPola() {
        textfieldNazwisko.setText("");
        textfieldNrtel.setText("");
        textfieldIloscBiletow.setText("");
        comboboxGodzina.setValue(null);
        comboboxDzien.setValue(null);
        comboboxFilm.setValue(null);       
        textfieldGatunek.setText("");
        textfieldTytul.setText("");
        textfieldRezyser.setText("");
        textfieldCzasTrwania.setText("");
        dtDataSeansu.setValue(null);
        textfieldGodzina.setText("");
        comboboxFilm1.setValue(null);
        comboboxSala.setValue(null);
        textfieldIloscMiejsc.setText("");
        textfieldNumerSali.setText("");

    }

    private void czyscWybrane(String zrodlo) {
        switch (zrodlo) {
            case "seans":
                dtDataSeansu.setValue(null);
                textfieldGodzina.setText("");
                comboboxFilm1.setValue(null);
                comboboxSala.setValue(null);
                break;

            case "sala":
                textfieldIloscMiejsc.setText("");
                textfieldNumerSali.setText("");
                break;

            case "rezerwacja":
                textfieldNazwisko.setText("");
                textfieldNrtel.setText("");
                textfieldIloscBiletow.setText("");
                comboboxGodzina.setValue(null);
                comboboxDzien.setValue(null);
                comboboxFilm.setValue(null);
                break;

            case "film":
                textfieldGatunek.setText("");
                textfieldTytul.setText("");
                textfieldRezyser.setText("");
                textfieldCzasTrwania.setText("");
                break;
        }
    }
    
    private boolean validateFieldsRezerwacje(){
        if (textfieldNazwisko.getText().isEmpty() || textfieldNrtel.getText().isEmpty() || textfieldIloscBiletow.getText().isEmpty() || comboboxFilm.getSelectionModel().isEmpty() || comboboxDzien.getSelectionModel().isEmpty() || comboboxGodzina.getSelectionModel().isEmpty()){
            brakZaznaczenia("Brak danych","Nie wszystkie pola zostały uzupełnione","Proszę uzupełnić pola.");
            return false;
        }
        else return true;
    }
    

    @FXML
    private void dodajRezerwacje(ActionEvent event) {
        Connection conn = dc.Connect();
        if (validateFieldsRezerwacje()){
            try {
                String film = comboboxFilm.getSelectionModel().getSelectedItem().toString();
                ResultSet rsIdFilmu = conn.createStatement().executeQuery("SELECT id_filmu FROM film WHERE tytul='" + film + "'");
                String dzien = comboboxDzien.getSelectionModel().getSelectedItem().toString();
                String godzina = comboboxGodzina.getSelectionModel().getSelectedItem().toString();                
                ResultSet rsIdSeansu = conn.createStatement().executeQuery("SELECT id_seansu FROM Seans WHERE id_filmu='" + rsIdFilmu.getInt("id_filmu") + "' AND dzien='" + dzien + "' AND godzina='" + godzina + "'");
                ResultSet rsWM = conn.createStatement().executeQuery("SELECT ilosc_wolnych_miejsc FROM Seans WHERE Seans.id_seansu='"+rsIdSeansu.getInt("id_seansu")+"'");
                Integer wolne =  rsWM.getInt("ilosc_wolnych_miejsc"); 
                Integer bilety = Integer.parseInt(textfieldIloscBiletow.getText());
                               
                if (bilety>0 && bilety<=wolne){
                    Integer wolneMiejsca = wolne - bilety;                
                    int rs = conn.createStatement().executeUpdate("INSERT INTO rezerwacja(id_rezerwacji,nazwisko,nr_kontaktowy,ilosc_biletow,id_seansu) Values "
                            + "(NULL," + "'" + textfieldNazwisko.getText() + "','" + textfieldNrtel.getText() + "','" + textfieldIloscBiletow.getText() + "'," + rsIdSeansu.getInt("id_seansu") + ")");
                    int rs2 = conn.createStatement().executeUpdate("UPDATE Seans SET ilosc_wolnych_miejsc='"+wolneMiejsca+"' WHERE id_seansu='"+rsIdSeansu.getInt("id_seansu")+"'");
                } else brakZaznaczenia("Wystąpił błąd", "Przepraszamy, brak wolnych miejsc", "Spróbuj ponownie.");
                
                conn.close();
                rsIdFilmu.close();
                rsIdSeansu.close();
                rsWM.close();

            } catch (SQLException ex) {
                Logger.getLogger(FXMLKinoController.class.getName()).log(Level.SEVERE, null, ex);
            }
            

            czyscWybrane("rezerwacja");
            loadDataFromDatabase(event);
        }        
    }
    
    private boolean validateFieldsFilm(){
        if (textfieldTytul.getText().isEmpty() || textfieldGatunek.getText().isEmpty() || textfieldRezyser.getText().isEmpty() || textfieldCzasTrwania.getText().isEmpty()){
            brakZaznaczenia("Brak danych","Nie wszystkie pola zostały uzupełnione","Proszę uzupełnić pola.");
            return false;
        }
        else return true;
    }
    

    @FXML
    private void dodajFilm(ActionEvent event) {
        Connection conn = dc.Connect();
        if (validateFieldsFilm()){
            try {
                int rs = conn.createStatement().executeUpdate("INSERT INTO film(id_filmu,tytul,gatunek,rezyser,czas_trwania) VALUES (NULL,'" + textfieldTytul.getText() + "','" + textfieldGatunek.getText() + "','" + textfieldRezyser.getText() + "','" + textfieldCzasTrwania.getText() + "')");
                conn.close();

            } catch (SQLException ex) {
                Logger.getLogger(FXMLKinoController.class.getName()).log(Level.SEVERE, null, ex);
            }
            czyscWybrane("film");
            loadDataFromDatabase(event);
        }
    }

private void brakZaznaczenia(String tytul, String komunikat1, String komunikat2) {
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle(tytul);
        alert.setHeaderText(komunikat1);
        alert.setContentText(komunikat2);
        alert.showAndWait();
    }

    @FXML
    private void usunRezerwacje(ActionEvent event) throws SQLException {

        int index;
        Connection conn = dc.Connect();
        int rs;

        if (tabFilm.isSelected()) {
            index = tableFilm.getSelectionModel().getSelectedIndex();
            if (index >= 0) {
                try {
                    rs = conn.createStatement().executeUpdate("DELETE FROM film WHERE id_filmu = " + tableFilm.getSelectionModel().getSelectedItem().getId_filmu());
                } catch (SQLException ex) {
                    Logger.getLogger(FXMLKinoController.class.getName()).log(Level.SEVERE, null, ex);
                }
                tableFilm.getItems().remove(index);
                tableFilm.getSelectionModel().select(null);
            } else {
                brakZaznaczenia("Brak zaznaczenia","Wiersz nie został zaznaczony","Proszę wybrać wiersz z tabeli.");
            }
        }

        if (tabSale.isSelected()) {
            index = tableSale.getSelectionModel().getSelectedIndex();
            if (index >= 0) {
                try {
                    rs = conn.createStatement().executeUpdate("DELETE FROM sala WHERE id_sali = " + tableSale.getSelectionModel().getSelectedItem().getId_sali());
                } catch (SQLException ex) {
                    Logger.getLogger(FXMLKinoController.class.getName()).log(Level.SEVERE, null, ex);
                }
                tableSale.getItems().remove(index);
                tableSale.getSelectionModel().select(null);
            } else {
                brakZaznaczenia("Brak zaznaczenia","Wiersz nie został zaznaczony","Proszę wybrać wiersz z tabeli.");
            }
        }

        if (tabSeans.isSelected()) {
            index = tableSeans.getSelectionModel().getSelectedIndex();
            if (index >= 0) {
                try {
                    rs = conn.createStatement().executeUpdate("DELETE FROM Seans WHERE id_seansu = " + tableSeans.getSelectionModel().getSelectedItem().getId_seansu());
                } catch (SQLException ex) {
                    Logger.getLogger(FXMLKinoController.class.getName()).log(Level.SEVERE, null, ex);
                }
                tableSeans.getItems().remove(index);
                tableSeans.getSelectionModel().select(null);
            } else {
                brakZaznaczenia("Brak zaznaczenia","Wiersz nie został zaznaczony","Proszę wybrać wiersz z tabeli.");
            }
        }
        
        if(tabWszystko.isSelected()){
                brakZaznaczenia("Błąd usuwania","Próbujesz usunąć podsumowanie rezerwacji","Odmowa dostępu.");
            }

        if (tabZamowienie.isSelected()) {
            index = tableRezerwacje.getSelectionModel().getSelectedIndex();
            if (index >= 0) {
                try {
                    int idRezerwacji = tableRezerwacje.getSelectionModel().getSelectedItem().getId_rezerwacji();
                    ResultSet rsIdR = conn.createStatement().executeQuery("SELECT id_seansu, ilosc_biletow FROM rezerwacja WHERE id_rezerwacji='"+idRezerwacji+"'");
                    Integer bilety, wolneMiejsca;
                    
                    ResultSet rsM = conn.createStatement().executeQuery("SELECT ilosc_wolnych_miejsc FROM Seans WHERE id_seansu='"+rsIdR.getInt("id_seansu")+"'");
                    wolneMiejsca = rsM.getInt("ilosc_wolnych_miejsc");
                    bilety = rsIdR.getInt("ilosc_biletow");
                    wolneMiejsca+=bilety;
                                        
                    rs = conn.createStatement().executeUpdate("DELETE FROM rezerwacja WHERE id_rezerwacji = " + tableRezerwacje.getSelectionModel().getSelectedItem().getId_rezerwacji());
                    int rs2 = conn.createStatement().executeUpdate("UPDATE Seans SET ilosc_wolnych_miejsc='"+wolneMiejsca+"' WHERE id_seansu='"+rsIdR.getInt("id_seansu")+"'");
                    
                    rsIdR.close();
                    rsM.close();
                } catch (SQLException ex) {
                    Logger.getLogger(FXMLKinoController.class.getName()).log(Level.SEVERE, null, ex);
                }
                tableRezerwacje.getItems().remove(index);
                tableRezerwacje.getSelectionModel().select(null);
            } else {
                brakZaznaczenia("Brak zaznaczenia","Wiersz nie został zaznaczony","Proszę wybrać wiersz z tabeli.");
            }
        }
        conn.close();
    }


    @FXML
    private void opuscAplikacje(ActionEvent event) {
        Platform.exit();
    }

    private boolean validateFieldsSeans(){
        if (dtDataSeansu.getEditor().getText().isEmpty() || textfieldGodzina.getText().isEmpty() || comboboxFilm1.getSelectionModel().isEmpty() || comboboxSala.getSelectionModel().isEmpty()){
            brakZaznaczenia("Brak danych","Nie wszystkie pola zostały uzupełnione","Proszę uzupełnić pola.");
            return false;
        }
        else return true;
    }
    
    
    
    @FXML
    private void dodajSeans(ActionEvent event) {
        Connection conn = dc.Connect();
        if(validateFieldsSeans()){
            try {
                String film = comboboxFilm1.getSelectionModel().getSelectedItem().toString();
                String sala = comboboxSala.getSelectionModel().getSelectedItem().toString();


                ResultSet rsS = conn.createStatement().executeQuery("SELECT id_sali FROM sala WHERE nr_sali='" + sala + "'");
                ResultSet rsF = conn.createStatement().executeQuery("SELECT id_filmu FROM film WHERE tytul='" + film + "'");
                ResultSet rsWolne = conn.createStatement().executeQuery("SELECT ilosc_miejsc FROM sala WHERE id_sali='"+rsS.getInt("id_sali")+"'");

                int rs = conn.createStatement().executeUpdate("INSERT INTO Seans(id_seansu,dzien,godzina,ilosc_wolnych_miejsc,id_sali,id_filmu) VALUES (NULL,'" + dtDataSeansu.getValue().toString() + "','" + textfieldGodzina.getText() + "','0','" + rsS.getInt("id_sali") + "','" + rsF.getInt("id_filmu") + "')");
                ResultSet rsIdSeansu = conn.createStatement().executeQuery("SELECT id_seansu FROM Seans WHERE Seans.id_filmu='"+rsF.getInt("id_filmu")+"' AND Seans.id_sali='"+rsS.getInt("id_sali")+"' AND Seans.dzien='"+dtDataSeansu.getValue().toString()+"' AND Seans.godzina='"+textfieldGodzina.getText()+"'");
                int rs2 = conn.createStatement().executeUpdate("UPDATE Seans SET ilosc_wolnych_miejsc='"+rsWolne.getString("ilosc_miejsc")+"' WHERE id_seansu='"+rsIdSeansu.getInt("id_seansu")+"'");

                conn.close();
                rsS.close();
                rsF.close();
                rsWolne.close();
                rsIdSeansu.close();

            } catch (SQLException ex) {
                Logger.getLogger(FXMLKinoController.class.getName()).log(Level.SEVERE, null, ex);
            }

            czyscWybrane("seans");
            loadDataFromDatabase(event);
        }
    }

    private boolean validateFieldsSale(){
        if (textfieldNumerSali.getText().isEmpty() || textfieldIloscMiejsc.getText().isEmpty()){
            brakZaznaczenia("Brak danych","Nie wszystkie pola zostały uzupełnione","Proszę uzupełnić pola.");
            return false;
        }
        else return true;
    }
    
    @FXML
    private void dodajSale(ActionEvent event) {
        Connection conn = dc.Connect();
        if (validateFieldsSale()){
            try {

                int rs = conn.createStatement().executeUpdate("INSERT INTO sala(id_sali,ilosc_miejsc,nr_sali) VALUES (NULL,'" + textfieldIloscMiejsc.getText() + "','" + textfieldNumerSali.getText() + "')");

                conn.close();

            } catch (SQLException ex) {
                Logger.getLogger(FXMLKinoController.class.getName()).log(Level.SEVERE, null, ex);
            }
            czyscWybrane("sala");
            loadDataFromDatabase(event);
        }
    }

    @FXML
    private void fillRezerwacje(MouseEvent event) throws SQLException {
        wyczyscPola();
        if (tableRezerwacje.getSelectionModel().getSelectedItem()!=null){
            Connection conn = dc.Connect(); 
            textfieldNazwisko.setText(tableRezerwacje.getSelectionModel().getSelectedItem().getNazwisko());
            textfieldNrtel.setText(tableRezerwacje.getSelectionModel().getSelectedItem().getNr_kontaktowy());
            Integer bilety = tableRezerwacje.getSelectionModel().getSelectedItem().getIlosc_biletow();
            textfieldIloscBiletow.setText(bilety.toString());
            Integer idRezerwacji = tableRezerwacje.getSelectionModel().getSelectedItem().getId_rezerwacji();
            ResultSet rsF = conn.createStatement().executeQuery("SELECT film.tytul FROM film,Seans,rezerwacja,sala WHERE rezerwacja.id_rezerwacji='"
                    + idRezerwacji.toString() + "' AND rezerwacja.id_seansu=Seans.id_seansu AND Seans.id_filmu=film.id_filmu");
            //błąd przy wypełnianiu comboboxa nullem.
            comboboxFilm.setValue(rsF.getString("tytul"));
            ResultSet rsD = conn.createStatement().executeQuery("SELECT Seans.dzien FROM Seans,rezerwacja WHERE rezerwacja.id_rezerwacji='"
                    + idRezerwacji.toString() + "' AND rezerwacja.id_seansu=Seans.id_seansu");
            comboboxDzien.setValue(rsD.getString("dzien"));
            ResultSet rsG = conn.createStatement().executeQuery("SELECT Seans.godzina FROM Seans,rezerwacja WHERE rezerwacja.id_rezerwacji='"
                    + idRezerwacji.toString() + "' AND rezerwacja.id_seansu=Seans.id_seansu");
            comboboxGodzina.setValue(rsG.getString("godzina"));
            rsF.close();
            rsD.close();
            rsG.close();
            conn.close();
            doUpdateRezerwacja = true;
        } 

        
        
    }

    @FXML 
    private void fillSeans(MouseEvent event) throws SQLException {
        wyczyscPola();
        if (tableSeans.getSelectionModel().getSelectedItem()!=null){
            Connection conn = dc.Connect();
            String data = tableSeans.getSelectionModel().getSelectedItem().getDzien();
            LocalDate ld = LocalDate.parse(data);
            ld.format(DateTimeFormatter.ISO_DATE);
            dtDataSeansu.setValue(ld);
            textfieldGodzina.setText(tableSeans.getSelectionModel().getSelectedItem().getGodzina());            
            Integer idSeansu = tableSeans.getSelectionModel().getSelectedItem().getId_seansu();
            ResultSet rsS = conn.createStatement().executeQuery("SELECT nr_sali FROM sala,Seans WHERE Seans.id_seansu='"+idSeansu.toString()+"' AND Seans.id_sali=sala.id_sali");
            comboboxSala.setValue(rsS.getInt("nr_sali"));
            ResultSet rsF = conn.createStatement().executeQuery("SELECT tytul FROM film,Seans WHERE Seans.id_seansu='"+idSeansu.toString()+"' AND Seans.id_filmu=film.id_filmu");
            comboboxFilm1.setValue(rsF.getString("tytul"));
            rsS.close();
            rsF.close();
            conn.close();
            doUpdateSeans = true;
        }
    }

    @FXML
    private void fillFilm(MouseEvent event) {
        wyczyscPola();
        if (tableFilm.getSelectionModel().getSelectedItem()!=null){
            textfieldTytul.setText(tableFilm.getSelectionModel().getSelectedItem().getTytul());
            textfieldGatunek.setText(tableFilm.getSelectionModel().getSelectedItem().getGatunek());
            textfieldRezyser.setText(tableFilm.getSelectionModel().getSelectedItem().getRezyser());
            Integer czasTrwania = tableFilm.getSelectionModel().getSelectedItem().getCzas_trwania();
            textfieldCzasTrwania.setText(czasTrwania.toString());
            doUpdateFilm = true;
        } 
    }

    @FXML
    private void fillSale(MouseEvent event) throws SQLException {
        wyczyscPola();
        Connection conn = dc.Connect();
        if (tableSale.getSelectionModel().getSelectedItem()!=null){
            Integer nrSali = tableSale.getSelectionModel().getSelectedItem().getNr_sali();
            textfieldNumerSali.setText(nrSali.toString());
            Integer iloscMiejsc = tableSale.getSelectionModel().getSelectedItem().getIlosc_miejsc();
            textfieldIloscMiejsc.setText(iloscMiejsc.toString());
            doUpdateSala = true;
        }
    }

    @FXML
    private void przyciskAktualizujSala(ActionEvent event) throws SQLException { //wyjątek z pustymi polami
        if (doUpdateSala){
            Connection conn = dc.Connect();   
            int rsSale = conn.createStatement().executeUpdate("UPDATE sala SET ilosc_miejsc='"+textfieldIloscMiejsc.getText()+"',nr_sali='"+textfieldNumerSali.getText()+"' WHERE id_sali='"+ tableSale.getSelectionModel().getSelectedItem().getId_sali() +"'");
            doUpdateSala = false;
        } else brakZaznaczenia("Brak zaznaczenia","Wiersz do aktualizacji nie został zaznaczony","Proszę wybrać wiersz z tabeli, w celu dokonania aktualizacji.");
        czyscWybrane("sala");
    }

    @FXML
    private void przyciskAktualizujRezerwacja(ActionEvent event) throws SQLException {
        if (doUpdateRezerwacja){
            Connection conn = dc.Connect();
            Integer idRezerwacji = tableRezerwacje.getSelectionModel().getSelectedItem().getId_rezerwacji();
            
            ResultSet rsFiD = conn.createStatement().executeQuery("SELECT id_filmu FROM film WHERE tytul='"+comboboxFilm.getSelectionModel().getSelectedItem().toString()+"'");
            ResultSet rsS = conn.createStatement().executeQuery("SELECT id_seansu FROM Seans WHERE id_filmu='"+rsFiD.getString("id_filmu")+"' AND dzien='"+comboboxDzien.getSelectionModel().getSelectedItem().toString()
                    +"' AND godzina='"+comboboxGodzina.getSelectionModel().getSelectedItem().toString()+"'");
            
            Integer wolneMiejsca,bilety,bilety2;
            ResultSet rsIdR = conn.createStatement().executeQuery("SELECT id_seansu FROM rezerwacja WHERE id_rezerwacji='"+idRezerwacji+"'");
            ResultSet rsWM = conn.createStatement().executeQuery("SELECT ilosc_wolnych_miejsc FROM Seans WHERE id_seansu='"+rsIdR.getInt("id_seansu")+"'");
            ResultSet rsSB = conn.createStatement().executeQuery("SELECT ilosc_biletow FROM rezerwacja WHERE id_rezerwacji='"+idRezerwacji+"'");
            wolneMiejsca = rsWM.getInt("ilosc_wolnych_miejsc");
            bilety = rsSB.getInt("ilosc_biletow");
            wolneMiejsca+=bilety;
            bilety2 = Integer.parseInt(textfieldIloscBiletow.getText());
            
            if(bilety2>0 && bilety2<=wolneMiejsca){
                wolneMiejsca-=bilety2;
                int rsRezerwacja = conn.createStatement().executeUpdate("UPDATE rezerwacja SET nazwisko='"+textfieldNazwisko.getText()+"',nr_kontaktowy='"
                        +textfieldNrtel.getText()+"',ilosc_biletow='"+textfieldIloscBiletow.getText()+"',id_seansu='"+rsS.getInt("id_seansu")+"' WHERE id_rezerwacji='"+ idRezerwacji +"'");            
                int rsMiejsca = conn.createStatement().executeUpdate("UPDATE Seans SET ilosc_wolnych_miejsc='"+wolneMiejsca+"' WHERE id_seansu='"+rsIdR.getInt("id_seansu")+"'");
            } else brakZaznaczenia("Wystąpił błąd", "Przepraszamy, brak wolnych miejsc", "Aktualizacja danych została przerwana.");
            
            doUpdateRezerwacja = false;
            conn.close();
            rsS.close();
            rsFiD.close();
            rsIdR.close();
            rsWM.close();
            rsSB.close();
            
        } else brakZaznaczenia("Brak zaznaczenia","Wiersz do aktualizacji nie został zaznaczony","Proszę wybrać wiersz z tabeli, w celu dokonania aktualizacji.");
        czyscWybrane("rezerwacja");
    }

    @FXML
    private void przyciskAktualizujFilm(ActionEvent event) throws SQLException {
        if (doUpdateFilm){
            Connection conn = dc.Connect();
            int rsFilm = conn.createStatement().executeUpdate("UPDATE film SET tytul='"+textfieldTytul.getText()+"',gatunek='"+textfieldGatunek.getText()+"',rezyser='"+textfieldRezyser.getText()+"',czas_trwania='"+textfieldCzasTrwania.getText()+"' WHERE id_filmu='"+tableFilm.getSelectionModel().getSelectedItem().getId_filmu()+"'");
            doUpdateFilm = false;
        } else brakZaznaczenia("Brak zaznaczenia","Wiersz do aktualizacji nie został zaznaczony","Proszę wybrać wiersz z tabeli, w celu dokonania aktualizacji.");
        czyscWybrane("film");
    }

    @FXML
    private void przyciskAktualizujSeans(ActionEvent event) throws SQLException {
        if (doUpdateSeans){
            Connection conn = dc.Connect();
            ResultSet rsIdSali = conn.createStatement().executeQuery("SELECT id_sali,ilosc_miejsc FROM sala WHERE nr_sali='"+comboboxSala.getSelectionModel().getSelectedItem().toString()+"'");
            Integer idSali = rsIdSali.getInt("id_sali");
            Integer iloscMiejsc = rsIdSali.getInt("ilosc_miejsc");
            ResultSet rsIdFilmu = conn.createStatement().executeQuery("SELECT id_filmu FROM film WHERE tytul='"+comboboxFilm1.getSelectionModel().getSelectedItem().toString()+"'");
            Integer idFilmu = rsIdFilmu.getInt("id_filmu");
            int rsSeans = conn.createStatement().executeUpdate("UPDATE Seans SET dzien='"+dtDataSeansu.getValue()+"', godzina='"+textfieldGodzina.getText()+"', id_sali='"+idSali.toString()+"', id_filmu='"+idFilmu.toString()+"', ilosc_wolnych_miejsc='"+iloscMiejsc.toString()+"' WHERE id_seansu='"+tableSeans.getSelectionModel().getSelectedItem().getId_seansu()+"'");
            rsIdSali.close();
            rsIdFilmu.close();
            conn.close();
            doUpdateSeans = false;
        } else brakZaznaczenia("Brak zaznaczenia","Wiersz do aktualizacji nie został zaznaczony","Proszę wybrać wiersz z tabeli, w celu dokonania aktualizacji.");
        czyscWybrane("seans");
    }
    
   
    

}
