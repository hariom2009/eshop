import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.*;
public class DispCart extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession session=request.getSession();
        ArrayList list=(ArrayList)session.getAttribute("cart");
        out.println("<html>");
        out.println("<body>");
        if(list==null)
        {
            out.println("<h2>YOUR CART IS EMPTY</h2>");
            out.println("<h3><a href=ShowCat>Start-Buying</a></h3>");
        }
        else
        {
            out.println("<h3>YOUR CART</h3>");
            out.println("<table border=2>");
            out.println("<tr>");
            out.println("<td>PCode</td>");
            out.println("<td>Name</td>");
            out.println("<td>Desc</td>");
            out.println("<td>Catg</td>");
            out.println("<td>Price</td>");
            out.println("</tr>");
            try{
            Connection con=MyData.connect();
            String qr="select * from products where pcode=?";
            PreparedStatement ps=con.prepareStatement(qr);
            int sum=0;
            for(int i=0;i<list.size();i++)
            {
                String id=(String)list.get(i);
                ps.setInt(1, Integer.parseInt(id));
                ResultSet rs=ps.executeQuery();
                rs.next();
                String s1=rs.getString(1);
                String s2=rs.getString(2);
                String s3=rs.getString(3);
                String s4=rs.getString(4);
                String s5=rs.getString(5);
                sum=sum+Integer.parseInt(s5);
                out.println("<tr>");
                out.println("<td>"+s1+"</td>");
                out.println("<td>"+s2+"</td>");
                out.println("<td>"+s3+"</td>");
                out.println("<td>"+s4+"</td>");
                out.println("<td align=right>"+s5+"</td>");
                out.println("<td><a href=RemoveItem?pid="+s1+">remove</a></td>");
                out.println("</tr>");
            }
            out.println("<tr>");
            out.println("<td></td>");
            out.println("<td></td>");
            out.println("<td></td>");
            out.println("<td>TOTAL</td>");
            out.println("<td>"+sum+"</td>");
            out.println("</tr>");
            out.println("</table>");
            }catch(Exception ex){out.println(ex);}
            
            
            
        }
        out.println("<a href=buyerhome.jsp>BuyerHome</a><br>");
        out.println("<a href=ShowCat>Buy-More</a><br>");
        out.println("<a href=>Confirm-Order</a><br>");
        out.println("</body>");
        out.println("</html>");
        
        
        
        
        
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
