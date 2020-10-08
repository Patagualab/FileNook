<%@page import="java.util.Date"%>
<%@page import="modelo.Nook"%>
<%@page import="modelo.Comentario"%>
<%@page import="java.util.ArrayList"%>
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
        <script type='text/javascript'>
            function confirmar(url, mensaje){
                if(confirm(mensaje)){
                    window.location.href=url;
                }
            }
        </script>
    </head>
    <body >
        <%
            HttpSession sesion = request.getSession();
            String userName = (String) sesion.getAttribute("usuario");

            Nook nook = (Nook) request.getAttribute("nook");
            int valoracion = (Integer) request.getAttribute("valoracion");
            String nombre = nook.getNombre();
            Date fecha = nook.getFechaCreacion();
            String resumen = nook.getResumen();
            String autor = nook.getAutor();
            int descargas = nook.getDescargas();
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
                    <%=nombre%>
                    </h1>
                </div>

                <div class="pag-archivo">
                    <div class="archivo">
                        <ul>
                            <li><a><img src="imagenes/simpleLogo.png" alt="Imagen de nook"></a></li>
                            <li><p><%=resumen%></p></li>
                        </ul>
                    </div>

                    <div class="opciones">
                        <ul>
                            <%if(userName.equals(autor)){%>
                            <li><a href="perfil.jsp"><img src="recuperarImagenesSV?userName=<%=autor%>"
                                                                              class="imagenesUsuarios"
                                                                                alt="Imagen del perfil de usuario"></a></li>
                            <li><a href="perfil.jsp"><%=autor%></a></li>                            
                            <%}else{%>
                            <li><a href="autorSV?nombre=<%=autor%>"><img src="recuperarImagenesSV?userName=<%=autor%>"
                                                                              class="imagenesUsuarios"
                                                                                alt="Imagen del perfil de usuario"></a></li>
                            <li><a href="autorSV?nombre=<%=autor%>"><%=autor%></a></li>
                            <%}%>
                            <li><%=fecha%></li>
                            <li><button type="button" onclick="window.location.href='/descargarNookSV?idNook=<%=nook.getIdNook()%>'">Descargar</button></li>
                            <li><button type="button" onclick="window.location.href='/documentosSV?idNook=<%=nook.getIdNook()%>'">Ver archivos</button></li>
                            <li><text>Descargas: <%=descargas%></text></li>
                        </ul>
                    </div>
                    <div class="starNook">
                        <form class="valoracion"  method="post" action="/valorarNookSV?idNook=<%=nook.getIdNook()%>&urlPagina=/nookSV?idNook=<%=nook.getIdNook()%>">
                            <p class="clasificacion">
                            <input id="<%=nook.getIdNook()%>,5" type="radio" name="estrellas<%=nook.getIdNook()%>" value="5" onclick="this.form.submit();" <%if(valoracion==5){%> checked <%}%> >
                            <label for="<%=nook.getIdNook()%>,5">&#9733;</label>
                            <input id="<%=nook.getIdNook()%>,4" type="radio" name="estrellas<%=nook.getIdNook()%>" value="4" onclick="this.form.submit();" <%if(valoracion==4){%> checked <%}%>>
                            <label for="<%=nook.getIdNook()%>,4">&#9733;</label>
                            <input id="<%=nook.getIdNook()%>,3" type="radio" name="estrellas<%=nook.getIdNook()%>" value="3" onclick="this.form.submit();" <%if(valoracion==3){%> checked <%}%>>
                            <label for="<%=nook.getIdNook()%>,3">&#9733;</label>
                            <input id="<%=nook.getIdNook()%>,2" type="radio" name="estrellas<%=nook.getIdNook()%>" value="2" onclick="this.form.submit();" <%if(valoracion==2){%> checked <%}%>>
                            <label for="<%=nook.getIdNook()%>,2">&#9733;</label>
                            <input id="<%=nook.getIdNook()%>,1" type="radio" name="estrellas<%=nook.getIdNook()%>" value="1" onclick="this.form.submit();" <%if(valoracion==1){%> checked <%}%>>
                            <label for="<%=nook.getIdNook()%>,1">&#9733;</label>
                            </p>
                            <p class="vMedia"><%=nook.getValoracionMedia()%></p>
                        </form>
                    </div>
                    <div class="comentarios">
                        <form method="post"  action="/crearComentarioSV?idNook=<%=nook.getIdNook()%>&url=/nookSV?<%=nook.getIdNook()%>" >
                            <ul>
                                <li><input id="comentario" type="text" placeholder="Añada un comentario..." name="comentario" required=""></li>

                                <li><div class="EnvComment">
                                    <button type="submit" >Enviar comentario</button>
                                </div></li>
                            </ul>
                        </form>
                    </div>

                    <div class="comentariosUsuarios">
                        <ul>
                            <%
                                ArrayList<Comentario> comentarios = (ArrayList<Comentario>) request.getAttribute("comentarios");
                                ArrayList<Integer> cValoraciones = (ArrayList<Integer>) request.getAttribute("cValoraciones");
                                for(int i = comentarios.size() - 1; i >= 0; i--){
                            %>
                            <li>
                                <div class="comentarioTexto" method="post" action="/valorarComentarioSV?">
                                    <p class="usuario">
                                        <%if(userName.equals(autor)){%>
                                        <a href="perfil.jsp">
                                            <img src="recuperarImagenesSV?userName=<%=comentarios.get(i).getAutor()%>"
                                                      class="imagenesUsuariosComentarios"
                                                      alt="Imagen del perfil de usuario">
                                        </a>
                                        <%=comentarios.get(i).getAutor()%>
                                        <%} else {%>
                                        <a href="autorSV?nombre=<%=comentarios.get(i).getAutor()%>">
                                            <img src="recuperarImagenesSV?userName=<%=comentarios.get(i).getAutor()%>"
                                                      class="imagenesUsuariosComentarios"
                                                      alt="Imagen del perfil de usuario">
                                        </a>
                                        <%=comentarios.get(i).getAutor()%>
                                        <%}%>
                                    </p>
                                    <p><%=comentarios.get(i).getTexto()%></p>
                                    <div class="star1">
                                        <form class="valoracion" method="post" action="/valorarComentarioSV?comentario=<%=comentarios.get(i).getIdComentario()%>&urlPagina=/nookSV?idNook=<%=nook.getIdNook()%>">
                                            <p class="clasificacion">
                                                <input id="c<%=comentarios.get(i).getIdComentario()%>,5" type="radio" name="cestrellas<%=comentarios.get(i).getIdComentario()%>" onclick="this.form.submit();" <%if(cValoraciones.get(i)==5){%> checked <%}%> value="5">
                                                <label for="c<%=comentarios.get(i).getIdComentario()%>,5">&#9733;</label>
                                                <input id="c<%=comentarios.get(i).getIdComentario()%>,4" type="radio" name="cestrellas<%=comentarios.get(i).getIdComentario()%>" onclick="this.form.submit();" <%if(cValoraciones.get(i)==4){%> checked <%}%> value="4">
                                                <label for="c<%=comentarios.get(i).getIdComentario()%>,4">&#9733;</label>
                                                <input id="c<%=comentarios.get(i).getIdComentario()%>,3" type="radio" name="cestrellas<%=comentarios.get(i).getIdComentario()%>" onclick="this.form.submit();" <%if(cValoraciones.get(i)==3){%> checked <%}%> value="3">
                                                <label for="c<%=comentarios.get(i).getIdComentario()%>,3">&#9733;</label>
                                                <input id="c<%=comentarios.get(i).getIdComentario()%>,2" type="radio" name="cestrellas<%=comentarios.get(i).getIdComentario()%>" onclick="this.form.submit();" <%if(cValoraciones.get(i)==2){%> checked <%}%> value="2">
                                                <label for="c<%=comentarios.get(i).getIdComentario()%>,2">&#9733;</label>
                                                <input id="c<%=comentarios.get(i).getIdComentario()%>,1" type="radio" name="cestrellas<%=comentarios.get(i).getIdComentario()%>" onclick="this.form.submit();" <%if(cValoraciones.get(i)==1){%> checked <%}%> value="1">
                                                <label for="c<%=comentarios.get(i).getIdComentario()%>,1">&#9733;</label>
                                            </p>
                                            <p class="vMedia"><%=comentarios.get(i).getValoracionMedia()%></p>
                                        </form>
                                    </div>
                                    <%if(userName.equals(comentarios.get(i).getAutor())){%>
                                        <div class="borrarComentario">
                                            <button type="submit"   class="borrarComentario" onclick="confirmar('borrarComentarioSV?idComentario=<%=comentarios.get(i).getIdComentario()%>&idNook=<%=nook.getIdNook()%>', '¿Seguro que quiere borrar el comentario?')"> Borrar comentario</button>
                                        </div>
                                    <%}%>
                                </div>
                            </li>
                            <%}%>
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
