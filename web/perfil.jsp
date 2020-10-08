<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html>
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
        %>
        <div class="content">
        <header class="header">
            <div class="container logo-nav-container">
                <a href="/inicialSV" class="logo"><img id="imagenlogo" src="imagenes/LOGO.png" alt="Imagen logo">FILENOOK</a>
                <nav class="navigation">
                    <ul>
                        <li class="navigation-usuario"><a href="perfil.jsp"><%=userName%></a></li>
                        <li><a href="perfil.jsp"><img src="recuperarImagenesSV?userName=<%=userName%>"
                                                      class="imagenesUsuariosCabecera"
                                                       alt="Imagen del perfil de usuario"></a></li>
                    </ul>
                </nav>
            </div>
        </header>
        <main class="main">
            <div class="container">
                    <div class="nookstitulo">
                        <h1>
                        Mi Perfil
                        </h1>
                        <div class="opcionesPerfil">
                            <ul>
                                <li><a href="misNooksSV">&#128193 Mis nooks</a></li>
                                <li><a href="buzonSV">&#128229 Buzón de entrada</a></li>
                                <li><a href="enviadosSV">&#128228 Mensajes enviados</a></li>
                                <li><a href="configuracionUsuario.jsp">&#9881;&#65039 Configuración</a></li>
                                <li><a href="cerrarSesionSV">&#128682 Salir de la cuenta</a></li>
                            </ul>
                        </div>
                    </div>
            </div>
        </main>
        </div>
        <footer class="footer">
          © Proyecto Filenook - 2019 - Andrés Cabero Mata -  Patricia Aguado Labrador - Rafael Higelmo San Millán - Santiago Blasco Arnaiz
        </footer>
    </body>
</html>
