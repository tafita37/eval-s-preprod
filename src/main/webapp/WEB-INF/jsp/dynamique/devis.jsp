<%@ include file="../static/cssPart.jsp" %>
    <%@ include file="../static/header.jsp" %>
        <%@ include file="../static/menuDeroulant.jsp" %>
            <div class="main-panel">
                <div class="content-wrapper">
                    <div class="row">
                        <div class="col-lg-12 grid-margin stretch-card">
                            <div class="card">
                                <div class="card-body">
                                    <h4 class="card-title">Exemple de devis</h4>
                                    <div class="table-responsive">
                                        <table class="table table-hover">
                                            <thead>
                                                <tr>
                                                    <th>Num</th>
                                                    <th>Designations</th>
                                                    <th>Unite</th>
                                                    <th>Quantite</th>
                                                    <th>Prix Unitaire</th>
                                                    <th>Montant total</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <tr>
                                                    <td colspan="2">
                                                        000-TRAVAUX PREPARATOIRE
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td>
                                                        1
                                                    </td>
                                                    <td>
                                                        Mur de soutenement et demi Cloture de ht 1m
                                                    </td>
                                                    <td>
                                                        m3
                                                    </td>
                                                    <td>
                                                        26,98
                                                    </td>
                                                    <td>
                                                        190 000,00
                                                    </td>
                                                    <td>
                                                        5 126 200,00
                                                    </td>
                                                </tr>
                                                <tr class="font-weight-bold">
                                                    <td colspan="5" class="text-right">
                                                        TOTAL TRAVAUX PREPARATOIRE 
                                                    </td>
                                                    <td>
                                                        5 126 200,00
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td colspan="2">
                                                        100-TRAVAUX DE TERRASSEMENT
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td>
                                                        101
                                                    </td>
                                                    <td>
                                                        Décapage des terrains meubles
                                                    </td>
                                                    <td>
                                                        m2
                                                    </td>
                                                    <td>
                                                        101,36
                                                    </td>
                                                    <td>
                                                        3 072,87
                                                    </td>
                                                    <td>
                                                        311 468,69
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td>
                                                        102
                                                    </td>
                                                    <td>
                                                        Dressage du plateforme
                                                    </td>
                                                    <td>
                                                        m2
                                                    </td>
                                                    <td>
                                                        101,36
                                                    </td>
                                                    <td>
                                                        3 736,26
                                                    </td>
                                                    <td>
                                                        378 711,32
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td>
                                                        103
                                                    </td>
                                                    <td>
                                                        Fouille d'ouvrage terrain ferme
                                                    </td>
                                                    <td>
                                                        m3
                                                    </td>
                                                    <td>
                                                        24,44
                                                    </td>
                                                    <td>
                                                        9 390,93
                                                    </td>
                                                    <td>
                                                        229 514,29
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td>
                                                        104
                                                    </td>
                                                    <td>
                                                        Remblai d'ouvrage
                                                    </td>
                                                    <td>
                                                        m3
                                                    </td>
                                                    <td>
                                                        15,59
                                                    </td>
                                                    <td>
                                                        37 563,26
                                                    </td>
                                                    <td>
                                                        585 761,49
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td>
                                                        105
                                                    </td>
                                                    <td>
                                                        Travaux d'implantation
                                                    </td>
                                                    <td>
                                                        fft
                                                    </td>
                                                    <td>
                                                        1,00
                                                    </td>
                                                    <td>
                                                        152 656,00
                                                    </td>
                                                    <td>
                                                        152 656,00
                                                    </td>
                                                </tr>
                                                <tr class="font-weight-bold">
                                                    <td colspan="5" class="text-right">
                                                        TOTAL TRAVAUX DE TERRASSEMENT 
                                                    </td>
                                                    <td>
                                                        1 658 111,79
                                                    </td>
                                                </tr>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="card-body" style="display: flex;">
                            <a href="/livraison/exportPdf" class="btn btn-danger btn-icon-text">
                                <i class="ti-download btn-icon-prepend"></i>
                                Export pdf
                            </a>
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