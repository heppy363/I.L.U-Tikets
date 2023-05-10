package classiMatrice;

public class Utente {

    private int id;
    private String email_scolastica;
    private String password;
    private String data_nascita;
    private String email_recupero;
    private int classe;
    private String indirizzo;
    private String nome;
    private String cognome;
    private String sesso;
    private String password_nuova;


    public String getEmail_scolastica() {
        return email_scolastica;
    }

    public String getPassword() {
        return password;
    }

    public String getData_nascita() {
        return data_nascita;
    }

    public String getEmail_recupero() {
        return email_recupero;
    }

    public int getClasse() {
        return classe;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public String getNome() {
        return nome;
    }

    public String getCognome() {
        return cognome;
    }

    public String getSesso() {
        return sesso;
    }

    public String getPassword_nuova() {
        return password_nuova;
    }

    public int getId() {
        return id;
    }
}
