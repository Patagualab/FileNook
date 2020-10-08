<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
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
        
        String emisor = request.getParameter("emisor");
        if(emisor == null){
            emisor = "";
        }
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
                    <h3 style="margin-top: 2rem;">ENVIAR MENSAJE</h3>
                    <div class="pag-mensajeContenido" >

                        <form class="tablamensajetexto" method="post" action="/crearMensajeSV">
                        
                            <div class="partes">
                                <label>Enviar a: </label>
                                <input class="usuarioEmisoryAsunto" value="<%=emisor%>" name="destinatario" required>
                            </div>

                            <div class="partes">
                                <label>Asunto:</label>
                                <input class="usuarioEmisoryAsunto"  name="asunto" required>
                            </div>

                            <div class="partes" >
                                <label>Mensaje:</label>
                                <input class="mensajeEnterotext" name="texto" required>
                            </div>


                            <div class="partes" >
                                <input type="submit" tabindex="10" id="enviarmensajebotton" style="margin-left: 3em" onclick="window.location.href='crearMensajeSV'"   value="Enviar" >
                                <input type="button" tabindex="10" id="volverbuzonbotton" style="margin-left: 22em" onclick="window.location.href='buzonSV'"   value="Volver al buzón">
                            </div>
                        </form>
                        
                    </div>
                </div>
            </main>
            </div>
            <footer class="footer">
              © Proyecto Filenook - 2019 - Andrés Cabero Mata -  Patricia Aguado Labrador - Rafael Higelmo San Millán - Santiago Blasco Arnaiz
            </footer>
    </body>
</html>
