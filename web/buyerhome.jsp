<%
    String s=(String)session.getAttribute("user");
    if(s==null){
        response.sendRedirect("index.jsp");
    }
%>
<html>
    <body>
        <h1>Welcome <%=session.getAttribute("user")%></h1>
        <hr>
        <a href="ShowCat">Explore-Store</a><br>
        <a href="DispCart">View-Shopping-Cart</a><br>
        <a href="EndSession">Logout</a><br>
        <hr>
    </body>
</html>
