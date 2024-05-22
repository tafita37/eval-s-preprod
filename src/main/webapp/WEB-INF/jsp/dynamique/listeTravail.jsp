<%@ include file="../static/cssPart.jsp" %>
  <%@ include file="../static/header.jsp" %>
    <%@ include file="../static/menuDeroulant.jsp" %>
      <%@page import="java.util.*" %>
        <%@ page import="java.text.*" %>
          <%@page import="eval.construction.construction.model.construction.*" %>
            <% List<Travail> listeTravail=(List<Travail>) request.getAttribute("listeTravaux");
                Locale locale = new Locale("fr", "FR");
                NumberFormat formatteur = NumberFormat.getInstance(locale);
                %>
                <div class="main-panel">
                  <div class="content-wrapper">
                    <div class="row">
                      <div class="col-md-12 grid-margin stretch-card">
                        <div class="card">
                          <div class="card-body">
                            <p class="card-title">Liste des travaux</p>
                            <p class="card-description">
                              Cliquer pour modifier
                            </p>
                            <div class="row">
                              <div class="col-12">
                                <div class="table-responsive">
                                  <table id="example" class="display expandable-table" style="width:100%">
                                    <thead>
                                      <tr>
                                        <th>
                                          Nom
                                        </th>
                                        <th>
                                          Code
                                        </th>
                                        <th>
                                          Unite
                                        </th>
                                        <th>
                                          Prix unitaire
                                        </th>
                                      </tr>
                                    </thead>
                                    <tbody>
                                      <%
                                        for(int i=0; i<listeTravail.size(); i++) {
                                            %>
                                            <tr>
                                                <td>
                                                    <%
                                                        out.println(listeTravail.get(i).getNomTravail());
                                                    %>
                                                </td>
                                                <td>
                                                    <%
                                                        out.println(listeTravail.get(i).getNumero());
                                                    %>
                                                </td>
                                                <td>
                                                    <%
                                                        out.println(listeTravail.get(i).getUnite().getNomUnite());
                                                    %>
                                                </td>
                                                <td>
                                                    <%
                                                        out.println(formatteur.format(listeTravail.get(i).getPrixUnitaire())+" ar");
                                                    %>
                                                </td>
                                                <td>
                                                    <a href=<% out.println("/devis/loadFormModifTravail?idTravail="+listeTravail.get(i).getIdTravail()); %>>
                                                        Modifier
                                                    </a>
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