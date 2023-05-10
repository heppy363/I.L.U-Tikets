package dataBase;

import classiMatrice.Utente;

import java.sql.*;

public class DatabaseDAO {


    private String dbUrl = "jdbc:mysql://128.116.243.236:3306/progetto_ilu";
    private String dbUname = "matteo";
    private String dbPassword = "1234";
    private String dbDriver = "com.mysql.cj.jdbc.Driver";



    public void loadDriver(String dbDriver){
        try {
            Class.forName(dbDriver);
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    public Connection getConnection(){
        Connection con = null;

        try {
            con = DriverManager.getConnection(dbUrl,dbUname,dbPassword);
        }catch (SQLException sql){
            sql.printStackTrace();
        }

        return con;
    }


    //metodo per controllare se la email è gia presete nel DB
    public ResultSet prelevoEmail(String email){
        loadDriver(dbDriver);
        Connection con = getConnection();
        ResultSet result = null;
        String query = "SELECT * FROM studente WHERE email = ?";

        try {
            PreparedStatement staP = con.prepareStatement(query);
            staP.setString(1,email);
            result = staP.executeQuery();
        }catch (SQLException e){
            e.printStackTrace();
        }

        //chiusura connessione
        try {
            con.close();
        }catch (SQLException e){
            e.printStackTrace();
        }


        return result;
    }


    //metodo per inserire gli utenti nel DB
    public void inserisciUtente(Utente utente, String hashPassword){
        loadDriver(dbDriver);
        Connection con = getConnection();
        ResultSet result = null;


        String nome = utente.getNome();
        String cognome = utente.getCognome();
        String sesso = utente.getSesso();
        String email = utente.getEmail_scolastica();
        String data_nascita = utente.getData_nascita();
        String email_scolastica = utente.getEmail_scolastica();
        int classe = utente.getClasse();
        String indirizzo = utente.getIndirizzo();

        String query = "INSERT INTO studente(nome, cognome, classe, indirizzo, sesso, data_nascita, email_scolastica, password) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement staP = con.prepareStatement(query);
            staP.setString(1, nome);
            staP.setString(2, cognome);
            staP.setInt(3, classe);
            staP.setString(4, indirizzo);
            staP.setString(5, sesso);
            staP.setString(6, data_nascita);
            staP.setString(7, email_scolastica);
            staP.setString(8, hashPassword);

            staP.executeQuery();
        }catch (SQLException e){
            e.printStackTrace();
        }

        try {
            con.close();
        }catch (SQLException e){
            e.printStackTrace();
        }

    }


    //Metodo per prendere la password dal database
    public ResultSet getPassword(String email){
        loadDriver(dbDriver);
        Connection con = getConnection();
        ResultSet result = null;
        String query = "SELECT * FROM studente WHERE email = ?";

        try {
            PreparedStatement staP = con.prepareStatement(query);
            staP.setString(1,email);
            result = staP.executeQuery();
        }catch (SQLException e){
            e.printStackTrace();
        }

        //chiusura connessione
        try {
            con.close();
        }catch (SQLException e){
            e.printStackTrace();
        }


        return result;
    }


    public ResultSet getPasswordByID(int id){
        loadDriver(dbDriver);
        Connection con = getConnection();
        ResultSet result = null;
        String query = "SELECT * FROM studente WHERE id = ?";

        try{
            PreparedStatement staP = con.prepareStatement(query);
            staP.setInt(1,id);
            result = staP.executeQuery();
        }catch (SQLException e){
            e.printStackTrace();
        }

        //chiusura connessione
        try {
            con.close();
        }catch (SQLException e){
            e.printStackTrace();
        }

        return result;
    }


    //metodo per inserire i nuovi dati dell'utente per il metodo edit
    public void editDati(Utente utente, String password){
        loadDriver(dbDriver);
        Connection con = getConnection();
        ResultSet result = null;

        int id = utente.getId();
        int classe = utente.getClasse();
        String indirizzo = utente.getIndirizzo();
        String sesso = utente.getSesso();

        String query = "UPDATE studente SET classe=?, indirizzo=?, sesso=?, password=? WHERE id=? ";

        try {
            PreparedStatement staP = con.prepareStatement(query);
            staP.setInt(1, classe);
            staP.setString(2, indirizzo);
            staP.setString(3, sesso);
            staP.setString(4, password);
            staP.setInt(5, id);

            staP.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try {
            con.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

}
