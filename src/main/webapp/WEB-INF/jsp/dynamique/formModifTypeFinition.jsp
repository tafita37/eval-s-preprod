<%@ include file="../static/cssPart.jsp" %>
    <%@ include file="../static/header.jsp" %>
        <%@ include file="../static/menuDeroulantClient.jsp" %>
            <%@page import="eval.construction.construction.model.construction.*" %>
                <%@page import="java.util.List" %>
                    <% 
                        TypeFinition typeFinition = (TypeFinition) request.getAttribute("typeFinition");
                    %>
                                    <div class="main-panel">
                                        <div class="content-wrapper">
                                            <div class="row d-flex justify-content-center">
                                                <div class="col-md-6 grid-margin stretch-card">
                                                    <div class="card">
                                                        <div class="card-body">
                                                            <h4 class="card-title">Modifier type de finition</h4>
                                                            <p class="card-description">
                                                                Modification
                                                            </p>
                                                            <form class="forms-sample" method="post"
                                                                action="/devis/modifierTypeFinition" enctype="multipart/form-data">
                                                                <div class="form-group">
                                                                    <label>
                                                                        Taux de finition
                                                                    </label>
                                                                    <input type="number" class="form-control" step="any" name="pourcentageAugmentation" placeholder="Taux de finition" value=<% out.println(typeFinition.getPourcentageAugmentation()); %>>
                                                                </div>
                                                                <input type="hidden" name="idTypeFinition" value=<% out.println(typeFinition.getIdTypeFinition()); %>>
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