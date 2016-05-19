import java.sql.*;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowList extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String cat=request.getParameter("pc");
        
        Cookie ck=new Cookie("choice",cat);
        ck.setMaxAge(60*60*24*7);
        response.addCookie(ck);
        
        String qr="select pcode, pname from products where pcat=?";
        try{
        Connection con=MyData.connect();
        PreparedStatement ps=con.prepareStatement(qr);
        ps.setString(1,cat);
        ResultSet rs=ps.executeQuery();
        out.println("<html>");
        out.println("<body>");
        out.println("<h3>Online Shopping</h3>");
        out.println("<h4>Select Desired Product</h4>");
        out.println("<hr>");
        while(rs.next()){
            String name=rs.getString("pname");
            String id=rs.getString("pcode");
            out.println("<a href=ShowDetails?pid="+id+">");
            out.println(name);
            out.println("<a/><br>");
        }
        out.println("<hr>");
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
