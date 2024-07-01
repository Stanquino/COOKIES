package com.josue.cookie.manejocookie;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import javax.swing.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/Servlet")
public class Servlet  extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    // variable de nuevo usuario
        //para saber si es un usuario nuevo o antiguo
        boolean nuevoUsuario = true;
        //Vamos a obtener el arreglo de la cookie
        Cookie[] cookies = request.getCookies();
        //vamos a validar si existe o no
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("visitanteRecurrente") && cookie.getValue().equals("si")) {
                    nuevoUsuario = false;
                    break;
                }
            }
        }
        String mensaje = null;
        if (nuevoUsuario) {
            Cookie visitanteCookie = new Cookie("visitanteRecurrente", "si");
            response.addCookie(visitanteCookie);
            mensaje = "GRACIAS POR INGRESAR A MI SITIO WEB POR PRIMERA VEZ";
        }
        else {
            mensaje ="Gracias por visitar mi sitio web nuevamente";
        }
        response.setContentType("text/html;chrset=UTF-8");
        PrintWriter st = response.getWriter();
        st.println("Mensaje: "+mensaje);
    }

}
