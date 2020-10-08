<%@page import="modelo.Nook"%>
<%@page import="java.util.ArrayList"%>
<%@page import="modelo.Documento"%>
<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<!DOCTYPE html>
<html lang="es">
    <head>
        <title>FileNook</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="style.css" rel="stylesheet">
    </head>
    <body >
        <%
        HttpSession sesion = request.getSession();
        String userName = (String) sesion.getAttribute("usuario");
        
        Nook nook = (Nook) request.getAttribute("nook");
        
        String nombre = nook.getNombre();
        String autor = nook.getAutor();
        %>
        <div class="content">
            <header class="header">
                <div class="container logo-nav-container">
                    <a href="/inicialSV" class="logo"><img id="imagenlogo" src="imagenes/LOGO.png" alt="Imagen logo">FILENOOK</a>
                    <nav class="navigation">
                        <ul>
                            <li class="navigation-usuario"><a href="perfil.jsp"><%=userName%></a></li>
                            <li><a href="perfil.jsp"><img <img src="recuperarImagenesSV?userName=<%=userName%>"
                                                               class="imagenesUsuariosCabecera"
                                                           alt="Imagen del perfil de usuario"></a></li>
                        </ul>
                    </nav>
                </div>
            </header>
            <main class="main">
                <div class="container">
                    <div class="paginanook">
                        <ul>
                            <li><img src="imagenes/chico1.png" alt="Imagen del perfil de usuario"></li>
                            <li><h1><%=autor%></h1></li>
                        </ul>
                        <h1><%=nombre%></h1>
                    </div>
                    
                    <%
                                ArrayList<Documento> documentos = (ArrayList<Documento>) request.getAttribute("documentos");
                                for(int i = 0; i < documentos.size(); i++){
                            %>
                    <div class="pag-nook">
                           <div class="nook">
                                   <a><img src="imagenes/docSimpleLogo.png" alt="Imagen del perfil de usuario"></a>
                                    <h3><%=documentos.get(i).getNombre()%></h3>
                                    <p><%=documentos.get(i).getResumen()%></p>
                                    <%if(userName.equals(autor)){%>
                                    <div class="borrarArchivo">
                                        <button type="submit"   class="borrarArchivo" onclick="window.location.href='borrarArchivoSV?nombre=<%=documentos.get(i).getNombre()%>&idNook=<%=nook.getIdNook()%>'">Borrar archivo</button>
                                    </div>
                                    <%}%>
                           </div>
                           
                    </div>
                    <%}%>
                </div>
            </main>
        </div>
        <footer class="footer">
             © Proyecto Filenook - 2019 - Andrés Cabero Mata -  Patricia Aguado Labrador - Rafael Higelmo San Millán - Santiago Blasco Arnaiz
        </footer>
    </body>
</html>