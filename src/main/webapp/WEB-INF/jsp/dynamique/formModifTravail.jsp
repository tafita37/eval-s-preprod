<%@ include file="../static/cssPart.jsp" %>
    <%@ include file="../static/header.jsp" %>
        <%@ include file="../static/menuDeroulantClient.jsp" %>
            <%@page import="eval.construction.construction.model.construction.*" %>
                <%@page import="java.util.List" %>
                    <% 
                        Travail travail = (Travail) request.getAttribute("travail");
                        List<TypeFinition> listeTypeFinition = (List<TypeFinition>)
                        request.getAttribute("listeTypeFinition");
                        List<Unite> listeUnite=(List<Unite>) request.getAttribute("listeUnite");
                    %>
                                    <div class="main-panel">
                                        <div class="content-wrapper">
                                            <div class="row d-flex justify-content-center">
                                                <div class="col-md-6 grid-margin stretch-card">
                                                    <div class="card">
                                                        <div class="card-body">
                                                            <h4 class="card-title">Modifier travail </h4>
                                                            <p class="card-description">
                                                                Modification
                                                            </p>
                                                            <form class="forms-sample" method="post"
                                                                action="/devis/modifierTravail" enctype="multipart/form-data">
                                                                <div class="form-group">
                                                                    <label for="inputPrixVente">Nom : </label>
                                                                    <input type="text" name="nomTravail" placeholder="Nom de travail" class="form-control" value="<% out.println(travail.getNomTravail()); %>">
                                                                </div>
                                                                <div class="form-group">
                                                                    <label>Code de travail : </label>
                                                                    <input type="text" name="numero" placeholder="Nom de travail" class="form-control" value="<% out.println(travail.getNumero()); %>">
                                                                </div>
                                                                <div class="form-group">
                                                                    <select name="idUnite" id="" class="form-control">
                                                                        <option value="">
                                                                            Choisir une unite
                                                                        </option>
                                                                        <%
                                                                            for(int i=0; i<listeUnite.size(); i++) {
                                                                                if(listeUnite.get(i).getIdUnite()==travail.getUnite().getIdUnite()) {
                                                                                    %>
                                                                                    <option selected value=<% out.println(listeUnite.get(i).getIdUnite()); %>>
                                                                                        <% out.println(listeUnite.get(i).getNomUnite()); %>
                                                                                    </option>
                                                                                <% } else {
                                                                                    %>
                                                                                    <option value=<% out.println(listeUnite.get(i).getIdUnite()); %>>
                                                                                        <% out.println(listeUnite.get(i).getNomUnite()); %>
                                                                                    </option>
                                                                                <% }
                                                                            }
                                                                        %>
                                                                    </select>
                                                                </div>
                                                                <div class="form-group">
                                                                    <input type="number" class="form-control" name="prixUnitaire" placeholder="Prix Unitaire" value=<% out.println(travail.getPrixUnitaire()); %>>
                                                                </div>
                                                                <input type="hidden" name="idTravail" value=<% out.println(travail.getIdTravail()); %>>
                                                                <button type="submit" class="btn btn-primary mr-2">
                                                                    Modifier
                                                                </button>
                                                            </form>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <%@ include file="../static/footer.jsp" %>
                                    </div>
                                    <!-- main-panel ends -->
                                    </div>
                                    <!-- page-body-wrapper ends -->
                                    </div>
                                    <%@ include file="../static/jsPart.jsp" %>
                                        </body>

                                        </html>