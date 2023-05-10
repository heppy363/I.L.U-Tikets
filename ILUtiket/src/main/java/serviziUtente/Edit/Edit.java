package serviziUtente.Edit;

import classiMatrice.Utente;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import dataBase.DatabaseDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import serviziUtente.Registrazione.MetodiRegistrazione;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;


@WebServlet(name = "edit", value = "/edit")
public class Edit extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException  {
        PrintWriter out = resp.getWriter();

        Gson gson = new Gson();
        JsonObject jsonResponse = new JsonObject();

        jsonResponse.addProperty("result", 505);
        jsonResponse.addProperty("descrizone", "metodo non abilitato usare solo POST");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json");
        resp.addHeader("Access-Control-Allow-Origin", "*");
        resp.addHeader("Access-Control-Allow-Methods","POST");
        resp.addHeader("Access-Control-Allow-Headers", "Content-Type");
        PrintWriter out = resp.getWriter();
        DatabaseDAO con = new DatabaseDAO();
        MetodiEdit edit = new MetodiEdit();
        Gson gson = new Gson();

        //prendere i dati in ingresso
        Utente respUtente = gson.fromJson(req.getReader(), Utente.class);

        //response da mandare indietro
        JsonObject jsonResponse = new JsonObject();

        ResultSet result = con.getPasswordByID(respUtente.getId());
        try {
            String encodedPassword = result.getString("password");
            boolean check = edit.cotrolloPassword(respUtente,encodedPassword);
            if(check){
                //cambiare dati nel db
                int classe = respUtente.getClasse();
                String indirizzo = respUtente.getIndirizzo();
                String sesso = respUtente.getSesso();
                String password_nuova = respUtente.getPassword_nuova();
                String nuova_password_hashed = edit.hashPassword(password_nuova);


                con.editDati(respUtente, nuova_password_hashed);

                jsonResponse.addProperty("result", 200);

            }else{
                jsonResponse.addProperty("result", 406);
                jsonResponse.addProperty("descrizione", "password sbagliata");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        out.println(jsonResponse);
        out.close();
    }
}
