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
                        <h3 style="margin-top: 3rem;">CREAR NOOK</h3>
                    <div class="pag-crearNook">

                        <form class="tablamensajetexto"  method="post" action="/crearNookSV" >

                            <div class="partes">
                                <label for="nombre" class="nombreNook" style="padding-right: 2.5em;">Nombre:</label>
                                <input class="tablaNooktext" name="nombre" required>
                            </div>

                            <div class="partes">
                                <label for="etiquetas1" class="etiquetasNook" style="padding-right: 2em;">Categorías:<br></label>
                                <input class="tablaNooktext" name="etiquetas1" required>
                            </div>
                            
                            <div class="partes">
                                <label for="etiquetas2" class="etiquetasNook""></label>
                                <input class="tablaNooktext" name="etiquetas2" required>
                            </div>
                            
                            <div class="partes">
                                <label for="etiquetas3" class="etiquetasNook""></label>
                                <input class="tablaNooktext" name="etiquetas3" required>
                            </div>

                            <div class="partes" >
                                <label for="resumen" class="descripcionNook" style="padding-right: 1em;">Descripción:</label>
                                <input class="tablaNooktext" name="resumen" required>
                            </div>
                              <div class="partes" >
                                <input type="submit" class="button mainaction" tabindex="10" id="crear_NOOKbotton"value="CREAR" >
                                <input type="reset" class="button mainaction" tabindex="10" id="borrar_NOOKbotton"value="BORRAR" >
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



