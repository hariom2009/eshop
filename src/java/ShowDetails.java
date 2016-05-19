import java.sql.*;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowDetails extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String pid=request.getParameter("pid");
        String qr="select * from products where pcode=?";
        try{
        Connection con=MyData.connect();
        PreparedStatement ps=con.prepareStatement(qr);
        ps.setInt(1, Integer.parseInt(pid));
        ResultSet rs=ps.executeQuery();
        rs.next();
        out.println("<html>");
        out.println("<body>");
        out.println("<h3>Product Details</h3>");
        out.println("<hr>");
        out.println("<table border=2>");
        out.println("<tr>");
        out.println("<td>PCode</td>");
        out.println("<td>PName</td>");
        out.println("<td>Desc</td>");
        out.println("<td>Catg</td>");
        out.println("<td>Price</td>");
        out.println("</tr>");
        out.println("<tr>");
        out.println("<td>"+rs.getString("pcode")+"</td>");
        out.println("<td>"+rs.getString("pname")+"</td>");
        out.println("<td>"+rs.getString("pdesc")+"</td>");
        out.println("<td>"+rs.getString("pcat")+"</td>");
        out.println("<td>"+rs.getString("price")+"</td>");
        out.println("</tr>");
        out.println("</table>");
        out.println("<hr>");
        out.println("<a href=CartManager?pid="+pid+">Add-To-Cart</a><br>");
        out.println("<a href=buyerhome.jsp>BuyerHome</a><br>");
        out.println("<a href=ShowCat>CategoryPage</a><br>");
        out.println("</body>");
        out.println("</html>");
        con.close();
        }catch(Exception e){}
    
    
    
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
