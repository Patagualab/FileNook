<%@page import="java.util.ArrayList"%>
<%@page import="modelo.Nook"%>
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
        <script type='text/javascript'  src='js/jquery-2.2.4.min.js'></script>
        <script type='text/javascript'  src='js/miscripts.js'></script>
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
                    Mis Nooks
                    </h1>
                </div>


                <div class="nooks">
                    <button type="submit" class="nuevoNook" onclick="window.location.href='crearNook.jsp'">Nuevo Nook</button>
                </div>


                <div class="pag-nook_n">

                    <%
                        ArrayList<Nook> misNooks = (ArrayList<Nook>) request.getAttribute("misNooks");
                        ArrayList<String> misNooksCategorias = (ArrayList<String>) request.getAttribute("misNooksCategorias");
                        ArrayList<Integer> valoraciones = (ArrayList<Integer>) request.getAttribute("valoraciones");
                        for(int i = misNooks.size() - 1; i >= 0; i--){
                    %>
                       <div class="nook_n">
                            <div class="nook_n_img">
                                <a href="/nookSV?idNook=<%=misNooks.get(i).getIdNook()%>"><img src="imagenes/simpleLogo.png"></a>
                            </div>
                            <div class="titulo_nook">
                                <h3><%=misNooks.get(i).getNombre()%></h3>
                                <%if(misNooksCategorias.get(i)!=null){%>
                                <h4>Categoria: <%=misNooksCategorias.get(i)%></h4>
                                <%}else{%>
                                <h4>Categoria: Ninguna<h4>
                                <%}%>
                                <p><%=misNooks.get(i).getResumen()%></p>
                            </div>
                            <div class="star">
                                <form class="valoracion"  method="post" action="/valorarNookSV?idNook=<%=misNooks.get(i).getIdNook()%>&urlPagina=/misNooksSV">
                                    <p class="clasificacion">
                                        <input id="<%=misNooks.get(i).getIdNook()%>,5" type="radio" name="estrellas<%=misNooks.get(i).getIdNook()%>" value="5" onclick="this.form.submit();" <%if(valoraciones.get(i)==5){%> checked <%}%> >
                                        <label for="<%=misNooks.get(i).getIdNook()%>,5">&#9733;</label>
                                        <input id="<%=misNooks.get(i).getIdNook()%>,4" type="radio" name="estrellas<%=misNooks.get(i).getIdNook()%>" value="4" onclick="this.form.submit();" <%if(valoraciones.get(i)==4){%> checked <%}%>>
                                        <label for="<%=misNooks.get(i).getIdNook()%>,4">&#9733;</label>
                                        <input id="<%=misNooks.get(i).getIdNook()%>,3" type="radio" name="estrellas<%=misNooks.get(i).getIdNook()%>" value="3" onclick="this.form.submit();" <%if(valoraciones.get(i)==3){%> checked <%}%>>
                                        <label for="<%=misNooks.get(i).getIdNook()%>,3">&#9733;</label>
                                        <input id="<%=misNooks.get(i).getIdNook()%>,2" type="radio" name="estrellas<%=misNooks.get(i).getIdNook()%>" value="2" onclick="this.form.submit();" <%if(valoraciones.get(i)==2){%> checked <%}%>>
                                        <label for="<%=misNooks.get(i).getIdNook()%>,2">&#9733;</label>
                                        <input id="<%=misNooks.get(i).getIdNook()%>,1" type="radio" name="estrellas<%=misNooks.get(i).getIdNook()%>" value="1" onclick="this.form.submit();" <%if(valoraciones.get(i)==1){%> checked <%}%>>
                                        <label for="<%=misNooks.get(i).getIdNook()%>,1">&#9733;</label>
                                    </p>
                                    <p class="vMedia"><%=misNooks.get(i).getValoracionMedia()%></p>
                                </form>
                            </div>
                            <div class="borrarNook">
                                <button type="submit"   class="borrarNook" onclick="confirmar('borrarNookSV?idNook=<%=misNooks.get(i).getIdNook()%>','¿Seguro que quieres borrar el nook?');">Borrar Nook</button>
                            </div>
                       </div>
                    <%}%>
                </div>
        </div>
        </main>
        </div>
        <footer class="footer">
          © Proyecto Filenook - 2019 - Andrés Cabero Mata -  Patricia Aguado Labrador - Rafael Higelmo San Millán - Santiago Blasco Arnaiz
        </footer>
    </body>
</html>
