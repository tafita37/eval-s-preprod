<%@ include file="../static/cssPart.jsp" %>
    <%@ include file="../static/header.jsp" %>
        <%@ include file="../static/menuDeroulant.jsp" %>
            <%@page import="java.util.*" %>
                <%@ page import="java.text.*" %>
                    <%@page import="eval.construction.construction.model.devis.*" %>
                        <% 
                            Locale locale=new Locale("fr", "FR" ); NumberFormat
                            formatteur=NumberFormat.getInstance(locale); 
                            double montantDevisTotal = (double) request.getAttribute("montantDevisTotal");
                            double montantPayer = (double) request.getAttribute("montantPayer");
                        %>
                            <div class="main-panel">
                                <div class="content-wrapper">
                                    <div class="row">
                                        <div class="col-md-12 grid-margin stretch-card">
                                            <div class="card">
                                                <div class="card-body">
                                                    <div class="d-flex justify-content-between">
                                                        <p class="card-title">Stat/Mois</p>
                                                        <div class="form-group">
                                                            <input type="number" id="annee" class="form-control" name="annee" placeholder="Annee">
                                                        </div>
                                                    </div>
                                                    <p class="font-weight-500">
                                                        Montant total des devis : <% out.println(formatteur.format(montantDevisTotal)+" ar"); %>
                                                    </p>
                                                    <p class="font-weight-500">
                                                        Montant payer : <% out.println(formatteur.format(montantPayer)+" ar"); %>
                                                    </p>
                                                    <div id="sales-legend" class="chartjs-legend mt-4 mb-2"></div>
                                                    <canvas id="sales-chart"></canvas>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <%@ include file="../static/footer.jsp" %>
                                    <!-- content-wrapper ends -->
                            </div>
                            <!-- main-panel ends -->
                            </div>
                            <!-- page-body-wrapper ends -->
                            </div>
                            <%@ include file="../static/jsPart.jsp" %>
                                </body>

                                </html>