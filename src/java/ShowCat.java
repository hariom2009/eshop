import java.io.IOException;
import java.sql.*;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ShowCat extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        //fetching name of user from session
        HttpSession session=request.getSession();
        String unm=(String)session.getAttribute("user");
        if(unm==null){
            response.sendRedirect("index.jsp");
        }
        
        
        
        
        response.setContentType("text/html;charset=UTF-8");
                PrintWriter out = response.getWriter();
        String qr="select distinct  pcat from products";
        
        String option=" ALL ";
        
        Cookie ck[]=request.getCookies();
        
        for(int i=0; i<ck.length; i++){
            String nm= ck[i].getName();
            if(nm.equals("choice")){
                option=ck[i].getValue();
                break;
            }
        }
        
        
        
        try{
            Connection con=MyData.connect();
            Statement stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery(qr);
            out.println("<html>");
            out.println("<body>");
            out.println("<h2>Welcome "+unm+"</h2>");
            out.println("<h2>Online Shopping</h2>");
            out.println("<marquee><b><i>Attractive Offers On "+option+" Products</i></b></marquee>");
            out.println("<h3>Select Desired Category</h3>");
            out.println("<hr>");
            while(rs.next()){
                String s=rs.getString(1);
                out.println("<a href=ShowList?pc="+s+">");
                out.println(s);
                out.println("</a><br>");
             }
            out.println("<hr>");
            out.println("<a href=buyerhome.jsp>BuyerHome</a><br>");
            out.println("</body>");
            out.println("</html>");
            
            con.close();
        }catch(Exception ex){}
        
        
        
        
        
        
        
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
