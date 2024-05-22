<%@ include file="../static/cssPart.jsp" %>
  <%@ include file="../static/header.jsp" %>
    <%@ include file="../static/menuDeroulant.jsp" %>
      <%@page import="java.util.*" %>
        <%@ page import="java.text.*" %>
          <%@page import="eval.construction.construction.model.devis.*" %>
            <% 
                Locale locale = new Locale("fr", "FR");
                NumberFormat formatteur = NumberFormat.getInstance(locale);
                List<HistoriqueDevisTravail> listeDetailsDevis = (List<HistoriqueDevisTravail>) request.getAttribute("listeDetailsDevis");
            %>
                <div class="main-panel">
                  <div class="content-wrapper">
                    <div class="row">
                      <div class="col-md-12 grid-margin stretch-card">
                        <div class="card">
                          <div class="card-body">
                            <p class="card-title">Details du devis numero <% out.println(request.getAttribute("idDevis")); %></p>
                            <div class="row">
                              <div class="col-12">
                                <div class="table-responsive">
                                  <table id="example" class="display expandable-table" style="width:100%">
                                    <thead>
                                      <tr>
                                        <th>
                                          Travail
                                        </th>
                                        <th>
                                          Quantite
                                        </th>
                                        <th>
                                          Prix Unitaire
                                        </th>
                                        <th>
                                          Prix total
                                        </th>
                                      </tr>
                                    </thead>
                                    <tbody>
                                        <%
                                            for(int i=0; i<listeDetailsDevis.size(); i++) {
                                                %>
                                                <tr>
                                                    <td>
                                                        <%
                                                            out.println(listeDetailsDevis.get(i).getTravail().getNomTravail());
                                                        %>
                                                    </td>
                                                    <td>
                                                        <%
                                                            out.println(
                                                                formatteur.format(listeDetailsDevis.get(i).getQuantiteTravail())
                                                            );
                                                        %>
                                                    </td>
                                                    <td>
                                                        <%
                                                            out.println(
                                                                formatteur.format(listeDetailsDevis.get(i).getPrixUnitaireTravail())+
                                                                " ar"
                                                            );
                                                        %>
                                                    </td>
                                                    <td>
                                                        <%
                                                            out.println(
                                                                formatteur.format(listeDetailsDevis.get(i).getPrixTotal())+
                                                                " ar"
                                                            );
                                                        %>
                                                    </td>
                                                </tr>
                                            <% }
                                        %>
                                    </tbody>
                                  </table>
                                </div>
                              </div>
                            </div>
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