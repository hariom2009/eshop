import java.io.IOException;
import java.sql.*;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class VerifyUser extends HttpServlet {
    Connection con; PreparedStatement ps;
    public void init(){
        try{
            con=MyData.connect();
            String qr="select * from users where userid=? and password=?";
            ps=con.prepareStatement(qr);
        }catch(Exception ex){}
    }
    public void destroy(){
        try{
            con.close();
        }catch(Exception ex){}
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String id=request.getParameter("t1");
        String pw=request.getParameter("t2");
        try{
        ps.setString(1,id);
        ps.setString(2, pw);
        ResultSet rs=ps.executeQuery();
        boolean found=rs.next();
        if(found){
            String uname=rs.getString("uname");
            HttpSession session=request.getSession();
            session.setAttribute("user", uname);
            String save=request.getParameter("c1");
            if(save!=null){
                Cookie c1=new Cookie("userid",id);
                Cookie c2=new Cookie("password",pw);
                
                c1.setMaxAge(60*60*24*7);
                c2.setMaxAge(60*60*24*7);
                
                response.addCookie(c1);
                response.addCookie(c2);    
                
                
            }
            response.sendRedirect("buyerhome.jsp");

            //RequestDispatcher rd=request.getRequestDispatcher("buyerhome.jsp");
            //rd.forward(request, response);
        }else{
            out.println("INVALID USER");
            out.println("<a href=index.jsp>Try-Again</a>");
        }
        
        
        
        
        
        }catch(Exception ex){
            out.println(ex);
        }
        
        
        
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
