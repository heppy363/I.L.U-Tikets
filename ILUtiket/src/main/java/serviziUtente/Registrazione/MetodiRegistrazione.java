package serviziUtente.Registrazione;

import classiMatrice.Utente;
import dataBase.DatabaseDAO;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MetodiRegistrazione {
    DatabaseDAO con = new DatabaseDAO();

    //metodo per controllare se la email è gia presente nel DB
    public boolean controlloEmail(Utente utente) throws SQLException {
        boolean control;
        ResultSet result = con.prelevoEmail(utente.getEmail_scolastica());
        String email = result.getString("email_scolastica");
        if(result.next()){
            control = true;
        }else  control = false;

        return control;
    }

    //metodo per controllare se la email è scolastica
    public boolean controlloEmailScolastica(Utente utente){
        boolean control;
        if(utente.getEmail_scolastica().contains("@aldini.istruzioneer.it")){
            control = true;
        }else control = false;

        return control;
    }

    //metodo per hashare la password
    public String hashPassword(Utente utente){
        Argon2PasswordEncoder encoder = new Argon2PasswordEncoder(32,64,1, 15*1024,2);
        String password = utente.getPassword();
        String encodedPassword = encoder.encode(password);

        return encodedPassword;
    }
}
