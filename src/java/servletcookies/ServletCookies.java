package servletcookies;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServletCookies extends HttpServlet{
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws IOException, ServletException{
        String value = request.getParameter("value");
        String name = request.getParameter("cookie");
        String duration = request.getParameter("duration");
        int number;
        
        try{
            number = Integer.parseInt(duration);
        }catch(NumberFormatException e){
            number = -1;
        }
        
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        
        try{
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Valores Cookies</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Valores Cookies:</h1>");
            out.println("<ul>");
            
            if( value != null && name != null && !value.equals("") ){
                Cookie cookie = new Cookie(name, value);
                cookie.setMaxAge(number);
                response.addCookie(cookie);
                
                out.println("<li>Nueva cookie:"+cookie.getName()+"</li><br/>");
                out.println("<li>Valor cookie: "+cookie.getValue()+"</li><br/>");
                out.println("<li>Dilacion cookie: "+cookie.getMaxAge()+"</li><br/>");
            }
            
            Cookie[] allCookies = request.getCookies();
            if(allCookies != null){
                for(int i = 0; i < allCookies.length; i++){
                    out.println("<li>Cookie: "+allCookies[i].getName()+"</li><br/>");
                    out.println("<li>Valor cookie: "+allCookies[i].getValue()+"</li><br/>");
                    out.println("<li>Dilacion cookie: "+allCookies[i].getMaxAge()+"</li><br/>");  
                }
            }
            
            out.println("</ul>");
            out.println("</body>");
            out.println("</html>");
        }finally{
        out.close();
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws IOException, ServletException{
        processRequest(request, response);
    }
}