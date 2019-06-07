/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projektkino;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Gabi
 */
public class FXMLLogowanieController implements Initializable {

    @FXML
    private AnchorPane ekranLogowania;
    @FXML
    private Button bZaloguj;
    @FXML
    private TextField textLogin;
    @FXML
    private Label lTytul;
    @FXML
    private Label lLogin;
    @FXML
    private Label lHaslo;
    @FXML
    private Label lBlad;
    @FXML
    private PasswordField textHaslo;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void zalogujAction(ActionEvent event) throws IOException {

        Parent logowanie_parent = FXMLLoader.load(getClass().getResource("FXMLKino.fxml"));
        Scene logowanie_scene = new Scene(logowanie_parent);
        Stage aplikacja_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        if (isValidCredentials()) {
            aplikacja_stage.hide();
            aplikacja_stage.setScene(logowanie_scene);
            aplikacja_stage.show();
        } else {
            textLogin.clear();
            textHaslo.clear();
            lBlad.setVisible(true);
        }
    }

    private boolean isValidCredentials() {
        String DRIVER = "org.sqlite.JDBC";
        boolean let_in = false;
        System.out.println("SELECT * FROM uzytkownicy WHERE LOGIN=" + "'" + textLogin.getText() + "'" + " AND HASLO= " + textHaslo.getText() + "'");
        Connection c = null;
        Statement stmt = null; 
        
        try {
            Class.forName(DRIVER);
            c = DriverManager.getConnection("jdbc:sqlite:uzytkownicy.sqlite");            
            c.setAutoCommit(false);

            System.out.println("Otwarcie bazy danych przebiegło pomyślnie");
            stmt = c.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT * FROM uzytkownicy WHERE LOGIN= " + "'" + textLogin.getText() + "'" + " AND HASLO= " + "'" + textHaslo.getText() + "'");

            while (rs.next()) {
                if (rs.getString("LOGIN") != null && rs.getString("HASLO") != null) {
                    String login = rs.getString("LOGIN");
                    System.out.println("LOGIN = " + login);
                    String haslo = rs.getString("HASLO");
                    System.out.println("HASLO = " + haslo);
                    let_in = true;
                }
            }
            rs.close();
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("isValidCredentials zakończyła się pomyślnie");
        return let_in;

    }
}
