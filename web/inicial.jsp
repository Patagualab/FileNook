<%@page import="modelo.Nook"%>
<%@page import="java.util.ArrayList"%>
<%@page import="modelo.Usuario"%>
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
        <script src="js/jquery-2.2.4.min.js"></script>
        <script src="js/multislider.min.js"></script>
    </head>
    <body >
        <%
        HttpSession sesion = request.getSession();
        String userName = (String) sesion.getAttribute("usuario");
        %>
        <div class="content">
            <header class="header">
                <nav class="container logo-nav-container">
                    <a href="/inicialSV" class="logo"><img id="imagenlogo" src="imagenes/LOGO.png" alt="Imagen logo">FILENOOK</a>
                    <div class="navigation">
                        <ul>
                            <li class="navigation-usuario"><a href="perfil.jsp"><%=userName%></a></li>
                            <li><a href="perfil.jsp"><img src="recuperarImagenesSV?userName=<%=userName%>"
                                                           class="imagenesUsuariosCabecera"
                                                           alt="Imagen del perfil de usuario"></a></li>
                        </ul>
                    </div>
                </nav>
            </header>
            <main class="main">
                <div class="container">
                    <div class="proyectos-inicial">
                        <form class="search-bar" method="post" action="/buscarSV" >
                            <input type="search" placeholder="&#128269 Busca aquí nooks..." name="search" required>
                            <div class="clearfix">
                                <input type="submit" value="Buscar" >
                            </div>
                        </form>
                        
                        
                        
                        <p>Populares</p>
                        <div id="populares" class="slider">      <!-- Give wrapper ID to target with jQuery & CSS -->
                            <div class="MS-content">
                                <%
                                    ArrayList<Nook> nooksP = (ArrayList<Nook>)request.getAttribute("nooksPopulares");
                                    for(int i=0;i<nooksP.size();i++){
                                %>
                                <div class="item"><a href='/nookSV?idNook=<%=nooksP.get(i).getIdNook()%>'><div class="item-link"><%=nooksP.get(i).getNombre()%><br>Valoración media: <%=nooksP.get(i).getValoracionMedia()%></div></a></div>
                                <%}%>
                            </div>
                            <div class="MS-controls">
                                <button class="MS-left"><i class="fa fa-chevron-left" aria-hidden="true"></i></button>
                                <button class="MS-right"><i class="fa fa-chevron-right" aria-hidden="true"></i></button>
                            </div>
                        </div>
              
                        <p>Descargas</p>
                        <div id="descargas" class="slider">      <!-- Give wrapper ID to target with jQuery & CSS -->
                            <div class="MS-content">
                                <%
                                    ArrayList<Nook> nooksD = (ArrayList<Nook>) request.getAttribute("nooksDescargas");
                                    for(int i=0;i<nooksD.size();i++){
                                %>
                                <div class="item"><a href='/nookSV?idNook=<%=nooksD.get(i).getIdNook()%>'><div class="item-link"><%=nooksD.get(i).getNombre()%><br><br>Nº de descargas: <%=nooksD.get(i).getDescargas()%></div></a></div>
                                <%}%>
                            </div>
                            <div class="MS-controls">
                                <button class="MS-left"><i class="fa fa-chevron-left" aria-hidden="true"></i></button>
                                <button class="MS-right"><i class="fa fa-chevron-right" aria-hidden="true"></i></button>
                            </div>
                        </div>

                        <script>
                            $('#populares').multislider({
                                interval:0,
                                duration: 100
                            });
                            
                            $('#descargas').multislider({
                                interval:0,
                                duration: 100
                            });
                        </script>
                    </div>
                </div>
            </main>
        </div>
        <footer class="footer">
          © Proyecto Filenook - 2019 - Andrés Cabero Mata -  Patricia Aguado Labrador - Rafael Higelmo San Millán - Santiago Blasco Arnaiz
        </footer>
    </body>
</html>
