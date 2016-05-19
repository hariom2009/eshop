<%
     Cookie ck[]=request.getCookies();
    String v1="",v2="";
    if(ck!=null){
        for(int i=0; i<ck.length; i++){
            String name=ck[i].getName();
            if(name.equals("userid")){
                v1=ck[i].getValue();
            }
            if(name.equals("password")){
                v2=ck[i].getValue();
            }
            
        }
    }
%>
<html>
    <body>
        <h2>Online Shopping</h2>
        <hr>
        <form action="VerifyUser">
            <pre>
            UserId      <input type="text" name="t1" value="<%=v1%>"/>
            Password    <input type="password" name="t2" value="<%=v2%>"/>
            Save Pwd    <input type="checkbox" name="c1" value="yes"/>
                        <input type="submit" value="Login"/>
            </pre>
        </form> 
        <hr>
        <a href="registration.jsp">NewUser</a><br>
    </body>
</html>
