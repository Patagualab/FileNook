<%@page import="modelo.Nook"%>
<%@page import="java.util.ArrayList"%>
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
                  <div class="resultado_busqueda">
                      <h1>
                       Resultados de la búsqueda
                      </h1>



                      <form class="search-bar" method="post" action="/buscarSV" >
                            <input type="search" placeholder="&#128269 Busca aquí nooks..." name="search" required>
                            <input type="submit" value="Buscar">
                      </form>

                  </div>
                 <div class="pag-nook_n">
                     <%
                        ArrayList<Nook> nooks = (ArrayList<Nook>) request.getAttribute("nooksB");
                        ArrayList<String> nooksCategorias = (ArrayList<String>) request.getAttribute("categorias");
                        for(int i = 0; i < nooks.size(); i++){
                        double valoracion = Math.round(nooks.get(i).getValoracionMedia());
                        %>
                        <div class="nook_n">

                                 <div class="nook_n_img">
                                    <a href="/nookSV?idNook=<%=nooks.get(i).getIdNook()%>"><img src="imagenes/simpleLogo.png"></a>
                                   </div>
                            
                                 <div class="titulo_nook">
                                   <h3><%=nooks.get(i).getNombre()%></h3>
                                    <%if(nooksCategorias.get(i)!=null){%>
                                    <h4>Categoria: <%=nooksCategorias.get(i)%></h4>
                                    <%}else{%>
                                    <h4>Categoria: Ninguna<h4>
                                    <%}%>
                                    <p><%=nooks.get(i).getResumen()%></p>

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
