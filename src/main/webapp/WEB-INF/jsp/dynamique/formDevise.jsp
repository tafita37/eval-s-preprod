<%@ include file="../static/cssPart.jsp" %>
    <%@ include file="../static/headerClient.jsp" %>
        <%@ include file="../static/menuDeroulantClient.jsp" %>
        <%@page import="eval.construction.construction.model.construction.*"%>
        <%@page import="java.util.List"%>
        <%
            List<TypeMaison> listeTypeMaison = (List<TypeMaison>) request.getAttribute("listeTypeMaison");
            List<TypeFinition> listeTypeFinition = (List<TypeFinition>) request.getAttribute("listeTypeFinition");
        %>
            <div class="main-panel">
                <div class="content-wrapper">
                    <div class="row d-flex justify-content-center">
                        <div class="col-md-6 grid-margin stretch-card">
                            <div class="card">
                                <div class="card-body">
                                    <h4 class="card-title">Creer une nouveau devis</h4>
                                    <p class="card-description">
                                        Insertion
                                    </p>
                                    <form class="forms-sample" method="post" action="/devis/newDevis"
                                        enctype="multipart/form-data">
                                        <div class="row">
                                            <%
                                                for(int i=0; i<listeTypeMaison.size(); i++) {
                                                    %>
                                                    <div class="col-lg-3 grid-margin stretch-card ml-4" style="border: solid 1px black; border-radius: 20px;">
                                                        <div class="card">
                                                            <div class="card-body">
                                                                <h4 class="card-title">
                                                                    <%
                                                                        out.println(listeTypeMaison.get(i).getNomTypeMaison());
                                                                    %>
                                                                </h4>
                                                                <p class="card-description">
                                                                    <%
                                                                        out.println("Duree : "+listeTypeMaison.get(i).getDureeJour()+"j");
                                                                    %>
                                                                </p>
                                                            </div>
                                                            <div class="d-flex justify-content-end">
                                                                <div class="form-check">
                                                                    <label class="form-check-label">
                                                                        <input 
                                                                            type="radio" 
                                                                            class="form-check-input" 
                                                                            name="idTypeMaison" 
                                                                            id="optionsRadios1"
                                                                            value=<% out.println(listeTypeMaison.get(i).getIdTypeMaison()); %>
                                                                        />
                                                                    </label>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                <% }
                                            %>
                                        </div>
                                        <div class="form-group">
                                            <label for="inputPrixVente">Type de finition</label>
                                            <select name="idTypeFinition" id="" class="form-control">
                                                <option value="">
                                                    Choisir un type de finition
                                                </option>
                                                <%
                                                    for(int i=0; i<listeTypeFinition.size(); i++) {
                                                        %>
                                                        <option value=<% out.println(listeTypeFinition.get(i).getIdTypeFinition()); %>>
                                                            <%
                                                                out.println(listeTypeFinition.get(i).getNomTypeFinition());
                                                            %>
                                                        </option>
                                                    <% }
                                                %>
                                            </select>
                                        </div>
                                        <div class="form-group">
                                            <label>Date de debut : </label>
                                            <input type="date" name="dateDebut" id="" class="form-control">
                                        </div>
                                        <button type="submit" class="btn btn-primary mr-2">
                                            Inserer
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