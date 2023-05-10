package serviziUtente.Edit;

import classiMatrice.Utente;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import serviziUtente.LogIn.MetodiLogIn;

public class MetodiEdit {

    public boolean cotrolloPassword(Utente utente, String encodedPassword){
        MetodiLogIn log = new MetodiLogIn();
        boolean check = log.controlloPassword(utente,encodedPassword);
        return check;
    }

    public String hashPassword(String password){
        Argon2PasswordEncoder encoder = new Argon2PasswordEncoder(32,64,1, 15*1024,2);
        //decodifica la passvors del dataBase
        String encodedPassword = encoder.encode(password);

        return encodedPassword;
    }

}