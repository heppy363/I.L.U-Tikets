package serviziUtente.LogIn;

import classiMatrice.Utente;
import dataBase.DatabaseDAO;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import serviziUtente.Registrazione.MetodiRegistrazione;

import java.sql.SQLException;

public class MetodiLogIn {
    DatabaseDAO con = new DatabaseDAO();

    //metodo per controllare se l'email inserita è esistente nel databse usando il metodo gia usato in registrazione
    public boolean controlloEmail(Utente utente) throws SQLException {
        MetodiRegistrazione reg = new MetodiRegistrazione();
        boolean check = reg.controlloEmail(utente);
        return check;
    }

    //metodo per controllare la password
    public boolean controlloPassword(Utente utente, String encodedPassword){
        Argon2PasswordEncoder encoder = new Argon2PasswordEncoder(32,64,1, 15*1024,2);

        String password = utente.getPassword();
        //confrontiamo la password con quella hashata
        boolean validPassword = encoder.matches(password, encodedPassword);     //return true se le password sono uguali altrimenti false
        return validPassword;
    }

}
