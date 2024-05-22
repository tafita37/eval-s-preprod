<%@page import="java.util.*"%>
<%@page import="eval.construction.construction.exception.*"%>
<%
    ImportException excelException = (ImportException) request.getAttribute("excelError");
%>
        <%@ include file="../static/cssPart.jsp" %>
        <%@ include file="../static/header.jsp" %>
        <%@ include file="../static/menuDeroulant.jsp" %>
        <div class="main-panel">
            <div class="content-wrapper">
                <div class="row d-flex justify-content-center">
                    <div class="col-md-6 grid-margin stretch-card">
                        <div class="card">
                            <div class="card-body">
                                <h4 class="card-title">
                                    Paiement
                                </h4>
                                <p class="card-description">
                                    Insertion
                                </p>
                                <form class="forms-sample" method="post" action="/devis/importPaiementCSV" enctype="multipart/form-data">
                                    <div class="form-group">
                                        <input type="file" name="paiementFile" class="file-upload-default">
                                        <div class="input-group col-xs-12">
                                            <input type="text" class="form-control file-upload-info"
                                                disabled placeholder="Paiement" />
                                            <span class="input-group-append">
                                                <button class="file-upload-browse btn btn-primary"
                                                    type="button">
                                                    <i class="mdi mdi-paperclip" style="font-size: 0.9rem;"></i>
                                                </button>
                                            </span>
                                        </div>
                                    </div>
                                    <button type="submit" class="btn btn-primary mr-2">
                                        Payer
                                    </button>
                                </form>
                                <%
                                    if(excelException!=null) {
                                        %>                                     
                                        <div class="container mt-5">
                                            <div class="alert alert-danger" role="alert">
                                                <%
                                                    out.println(excelException.getFileName());
                                                %>
                                                <%
                                                    for(int i=0; i<excelException.getListeLineException().size(); i++) {
                                                        out.println(excelException.getListeLineException().get(i).getMessage()+"<br/>");
                                                    }
                                                %>
                                            </div>
                                        </div>  
                                    <% }
                                %>
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
  <!-- container-scroller -->
  <!-- plugins:js -->
  <script src="/vendors/js/vendor.bundle.base.js"></script>
  <!-- endinject -->
  <!-- Plugin js for this page -->
  <script src="/vendors/typeahead.js/typeahead.bundle.min.js"></script>
  <script src="/vendors/select2/select2.min.js"></script>
  <!-- End plugin js for this page -->
  <!-- inject:js -->
  <script src="/js/off-canvas.js"></script>
  <script src="/js/hoverable-collapse.js"></script>
  <script src="/js/template.js"></script>
  <script src="/js/settings.js"></script>
  <script src="/js/todolist.js"></script>
  <!-- endinject -->
  <!-- Custom js for this page-->
  <script src="/js/file-upload.js"></script>
  <script src="/js/typeahead.js"></script>
  <script src="/js/select2.js"></script>
  <!-- End custom js for this page-->
</body>

</html>