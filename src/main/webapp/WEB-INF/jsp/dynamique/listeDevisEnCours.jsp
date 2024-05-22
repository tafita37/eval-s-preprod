<%@ include file="../static/cssPart.jsp" %>
  <%@ include file="../static/header.jsp" %>
    <%@ include file="../static/menuDeroulant.jsp" %>
      <%@page import="java.util.*" %>
        <%@ page import="java.text.*" %>
          <%@page import="eval.construction.construction.model.devis.*" %>
            <% List<Devis> listeDevis=(List<Devis>) request.getAttribute("listeDevis");
                Locale locale = new Locale("fr", "FR");
                NumberFormat formatteur = NumberFormat.getInstance(locale);
                %>
                <div class="main-panel">
                  <div class="content-wrapper">
                    <div class="row">
                      <div class="col-md-12 grid-margin stretch-card">
                        <div class="card">
                          <div class="card-body">
                            <p class="card-title">Les devis en cours</p>
                            <p class="card-description">
                              Cliquer sur les colonnes de montant pour voir les details de paiement
                            </p>
                            <div class="row">
                              <div class="col-12">
                                <div class="table-responsive">
                                  <table id="example" class="display expandable-table" style="width:100%">
                                    <thead>
                                      <tr>
                                        <th>
                                          Type de maison
                                        </th>
                                        <th>
                                          Type de finition
                                        </th>
                                        <th>
                                          Date de debut
                                        </th>
                                        <th>
                                          Duree de construction
                                        </th>
                                        <th>
                                          Date de fin
                                        </th>
                                        <th>
                                          Prix final
                                        </th>
                                        <th>
                                          Montant payer
                                        </th>
                                        <th>
                                          Reste a payer
                                        </th>
                                        <th>
                                          %Payer
                                        </th>
                                      </tr>
                                    </thead>
                                    <tbody>
                                      <% for(int i=0; i<listeDevis.size(); i++) { %>
                                        <tr style="cursor: pointer;">
                                          <td>
                                            <a href=<% out.println("/devis/detailDevis?idDevis="+listeDevis.get(i).getIdDevis()); %>>
                                              <% out.println(listeDevis.get(i).getTypeMaison().getNomTypeMaison()); %>
                                            </a>
                                          </td>
                                          <td>
                                            <% out.println(listeDevis.get(i).getTypeFinition().getNomTypeFinition()); %>
                                          </td>
                                          <td>
                                            <% out.println(listeDevis.get(i).getDateDebut()); %>
                                          </td>
                                          <td>
                                            <% out.println(listeDevis.get(i).getDureeTypeMaison()+"j"); %>
                                          </td>
                                          <td>
                                            <% out.println(listeDevis.get(i).getDateFin()); %>
                                          </td>
                                          <td class="text-right" data-toggle="modal" data-target=<% out.println("#paiementDevis"+listeDevis.get(i).getIdDevis()); %>>
                                            <% out.println(formatteur.format(listeDevis.get(i).getFinalPrice())+" ar");
                                              %>
                                          </td>
                                          <td class="text-right" data-toggle="modal" data-target=<% out.println("#paiementDevis"+listeDevis.get(i).getIdDevis()); %>>
                                            <% out.println(formatteur.format(listeDevis.get(i).getMontantPayer())+" ar");
                                              %>
                                          </td>
                                          <td class="text-right" data-toggle="modal" data-target=<% out.println("#paiementDevis"+listeDevis.get(i).getIdDevis()); %>>
                                            <% out.println(formatteur.format(listeDevis.get(i).getResteAPayer())+" ar");
                                              %>
                                          </td>
                                          <%
                                            if(listeDevis.get(i).getPourcentagePayerDouble()>50) {
                                              %>
                                              <td style="background-color: greenyellow;" class="text-right" data-toggle="modal" data-target=<% out.println("#paiementDevis"+listeDevis.get(i).getIdDevis()); %>>
                                                <% out.println(listeDevis.get(i).getPourcentagePayer());
                                                  %>
                                              </td>
                                            <% } else if(listeDevis.get(i).getPourcentagePayerDouble()<50) {
                                              %>
                                              <td style="background-color: red;" class="text-right" data-toggle="modal" data-target=<% out.println("#paiementDevis"+listeDevis.get(i).getIdDevis()); %>>
                                                <% out.println(listeDevis.get(i).getPourcentagePayer());
                                                  %>
                                              </td>
                                            <% } else {
                                              %>
                                              <td class="text-right" data-toggle="modal" data-target=<% out.println("#paiementDevis"+listeDevis.get(i).getIdDevis()); %>>
                                                <% out.println(listeDevis.get(i).getPourcentagePayer());
                                                  %>
                                              </td>
                                            <% }
                                          %>
                                        </tr>
                                        <div class="modal fade" id=<%out.println("paiementDevis"+listeDevis.get(i).getIdDevis()); %>
                                          tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
                                          aria-hidden="true">
                                          <div class="modal-dialog" role="document">
                                            <div class="modal-content">
                                              <div class="modal-header">
                                                <h5 class="modal-title" id="exampleModalLabel">
                                                  Details de paiement
                                                </h5>
                                                <button type="button" class="close" data-dismiss="modal"
                                                  aria-label="Close">
                                                  <span aria-hidden="true">&times;</span>
                                                </button>
                                              </div>
                                              <div>
                                                <ul>
                                                <%
                                                  for(int j=0; j<listeDevis.get(i).getListePaiements().size(); j++) {
                                                    %>
                                                      <li style="margin-block-start: 20px;">
                                                        <%
                                                          out.println(listeDevis.get(i).getListePaiements().get(j).stringValue());
                                                        %>
                                                      </li>
                                                  <% }
                                                %>
                                              </ul>
                                              </div>
                                            </div>
                                          </div>
                                        </div>
                                        <% } %>
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