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
                        <h3 style="margin-top: 3rem;">AGREGAR ARCHIVOS</h3>
                    <div class="pag-agregarDocumento">

                        <form class="tablamensajetexto"  method="post" enctype="multipart/form-data" action="/agregarArchivoSV" >

                            <div class="partes" >
                                <label for="resumen" class="descripcionNook" style="padding-right: 1em;">Descripción:</label>
                                <input class="tablaNooktext" name="resumen" required>
                            </div>

                            <div class="partes" >
                                <label for="subirarchivo" class="SubirArchivo" style="padding-right: 2em;">Subir Archivo:</label>
                            </div>
                            <input type="file" name="documento" required> <!--multiple="multiple"--> 

                              <div class="partes" >
                                <input type="submit" class="button mainaction" tabindex="10" id="agregar_Archivo" value="AGREGAR" >
                                <input type="submit" class="button mainaction" tabindex="10" id="terminar_archivo" onclick="window.location.href='misNooksSV'" value="TERMINAR" >
                                <input type="reset" class="button mainaction" tabindex="10" id="borrar_Archivo" value="BORRAR" >
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



