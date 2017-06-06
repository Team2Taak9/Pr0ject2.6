package oogheelkunde;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;


/**
 * 
 *
 * @author 
 */
@WebServlet(name = "getTokenServlet", urlPatterns = { "/gettokenservlet" })
public class getTokenServlet extends HttpServlet
{
    private static final long serialVersionUID = 1L;
    private Oogheelkunde database;
    
    @Resource(name = "**")
    private DataSource dataSource;


    public void init()
    {
        try
        {
            database = new Oogheelkunde(dataSource);            
        }
        catch (Exception e)
        {
            System.out.println("Kan geen token ophalen van database: " + e.getMessage());
        }

    }  
      
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException
    {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        
        try
        {
            int identificatienummer = Integer.parseInt(request.getParameter("identificatienummer"));
            haalTokenOp(identificatienummer, request, out, database);
            
            
        }
        catch (SQLException | IllegalArgumentException e)
        {
            out.println("Kan token niet geven: " + e.getMessage());
        }

        out.println("</p></body></html>");
        out.close();
    }
    
    private void haalTokenOp(int identificatienummer, HttpServletRequest request, PrintWriter out,
            Oogheelkunde database) throws SQLException
    {        
       
                 Tokens token = database.getToken(identificatienummer);
                
             out.println("<html>\n" +
"    <head> \n" +
"        <link type=\"text/css\" rel=\"stylesheet\" href=\"stylesheet.css\">\n" +
"        <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js\"></script>\n" +
"        <!--<script src = \"http://ajax.googleapis.com/ajas/lis/jquery/1.11.2/jquery.min.js\"></script>--->\n" +
"        <script src = \"http://code.jquery.com/ui/1.11.3/jquery-ui.min.js\"></script>\n" +
"        <script type = \"text/javascript\" src=\"logica.js\"></script>\n" +
"        <title>Oogheelkunde cataract enquête</title>\n" +
"    </head>\n" +
"    <body>\n" +
"        <div id = \"header\"><h1>Oogheelkunde cataract operatie enquête</h1></div>\n" +
"            <p id= \"logo\"><img src=\"http://www.vmca.nl/dynamic/image/afbeeldingen/amc-doorzichtig.png\"/></p>\n" +
"             \n" +
"        <div id=\"inhoud\">\n" +
"            <p id=\"inhoudtitel\"><h2>Token</h2></p>\n" +
"        <form id =\"formulier\" method=\"post\" action=\"gettokenservlet\">\n" +
"        <table>\n" +
"            <tr>\n" +
"                <td><p><h4>Token</h4><input id = \"token\" value="+token.getTokens(identificatienummer)+" name = \"token\" type = \"text\"></p></td>\n" +
"            </tr>\n" +
"            <tr>\n" +        
"        </table>\n" +
"        <input id = \"opslaan\" name=\"opslaan\" type = \"submit\" value=\"Opslaan\"></input>\n" +
"        </form>\n" +
"            \n" +
"        </div>\n" +
"             <div id = \"einde\">\n" +
"                 <p id = \"studentgegevens\"><h6>Laura van de Langemheen, 11120924</h6></p></div>\n" +
"    </body>\n" +
"</html>");
             
             out.close();
        }
}
        
        
  
    
  
