package serviziUtente.Registrazione;

import classiMatrice.Utente;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import dataBase.DatabaseDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;


@WebServlet(name = "registrazione", value = "/registrazione")
public class Registrazione extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        resp.addHeader("Access-Control-Allow-Origin", "*");
        resp.addHeader("Access-Control-Allow-Methods","POST");
        resp.addHeader("Access-Control-Allow-Headers", "Content-Type");
        PrintWriter out = resp.getWriter();
        DatabaseDAO con = new DatabaseDAO();
        MetodiRegistrazione reg = new MetodiRegistrazione();
        Gson gson = new Gson();

        //prendere i dati in ingresso
        Utente respUtente = gson.fromJson(req.getReader(), Utente.class);
        String hashPassword;

        //response da mandare indietro
        JsonObject jsonResponse = new JsonObject();


        try {
            if(!reg.controlloEmail(respUtente)){
                jsonResponse.addProperty("result", 502);
            } else if (reg.controlloEmailScolastica(respUtente)){
                jsonResponse.addProperty("result", 502);
            }else {
                hashPassword = reg.hashPassword(respUtente);
                con.inserisciUtente(respUtente,hashPassword);
                jsonResponse.addProperty("result", 200);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        out.println(jsonResponse);
        out.close();
    }


}
