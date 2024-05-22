<%@ include file="../static/cssPart.jsp" %>
  <%@ include file="../static/headerClient.jsp" %>
    <%@ include file="../static/menuDeroulantClient.jsp" %>
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
                            <p class="card-title">Vos devis</p>
                            <p class="card-description">
                              Cliquer sur une ligne pour payer
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
                                          Prix total sans finition
                                        </th>
                                        <th>
                                          Prix final
                                        </th>
                                        <th>
                                          Pourcentage payer
                                        </th>
                                        <th></th>
                                      </tr>
                                    </thead>
                                    <tbody>
                                      <% for(int i=0; i<listeDevis.size(); i++) { %>
                                        <tr style="cursor: pointer;">
                                          <td 
                                            data-toggle="modal" 
                                            data-target=<% out.println("#paiementDevis"+listeDevis.get(i).getIdDevis()); %>
                                          >
                                            <% out.println(listeDevis.get(i).getTypeMaison().getNomTypeMaison()); %>
                                          </td>
                                          <td data-toggle="modal" data-target=<% out.println("#paiementDevis"+listeDevis.get(i).getIdDevis()); %>">
                                            <% out.println(listeDevis.get(i).getTypeFinition().getNomTypeFinition()); %>
                                          </td>
                                          <td data-toggle="modal" data-target=<% out.println("#paiementDevis"+listeDevis.get(i).getIdDevis()); %>>
                                            <% out.println(listeDevis.get(i).getDateDebut()); %>
                                          </td>
                                          <td data-toggle="modal" data-target=<% out.println("#paiementDevis"+listeDevis.get(i).getIdDevis()); %>>
                                            <% out.println(listeDevis.get(i).getDureeTypeMaison()+"j"); %>
                                          </td>
                                          <td data-toggle="modal" data-target=<% out.println("#paiementDevis"+listeDevis.get(i).getIdDevis()); %>>
                                            <% out.println(listeDevis.get(i).getDateFin()); %>
                                          </td>
                                          <td class="text-right" data-toggle="modal" data-target=<% out.println("#paiementDevis"+listeDevis.get(i).getIdDevis()); %>>
                                            <% out.println(formatteur.format(listeDevis.get(i).getPrixTotal())+" ar");
                                              %>
                                          </td>
                                          <td class="text-right" data-toggle="modal" data-target=<% out.println("#paiementDevis"+listeDevis.get(i).getIdDevis()); %>>
                                            <% out.println(formatteur.format(listeDevis.get(i).getFinalPrice())+" ar");
                                              %>
                                          </td>
                                          <td data-toggle="modal" data-target=<% out.println("#paiementDevis"+listeDevis.get(i).getIdDevis()); %>>
                                            <%
                                              out.println(listeDevis.get(i).getPourcentagePayer());
                                            %>
                                          </td>
                                          <td>
                                            <a href=<% out.println("/devis/exportPdf?idDevis="+listeDevis.get(i).getIdDevis()); %> 
                                              class=" btn btn-danger btn-icon-text">
                                              <i class="ti-download btn-icon-prepend"></i>
                                              Export pdf
                                            </a>
                                          </td>
                                        </tr>
                                        <div class="modal fade" id=<%out.println("paiementDevis"+listeDevis.get(i).getIdDevis()); %>
                                          tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
                                          aria-hidden="true">
                                          <div class="modal-dialog" role="document">
                                            <div class="modal-content">
                                              <div class="modal-header">
                                                <h5 class="modal-title" id="exampleModalLabel">
                                                  Paiement
                                                </h5>
                                                <button type="button" class="close" data-dismiss="modal"
                                                  aria-label="Close">
                                                  <span aria-hidden="true">&times;</span>
                                                </button>
                                              </div>
                                              <form class="forms-sample" id="paiementForm" enctype="multipart/form-data">
                                                <div class="modal-body">
                                                  <input type="hidden" name="idDevis" value=<% out.println(listeDevis.get(i).getIdDevis()); %>/>
                                                  <div class="form-group">
                                                    <label for="inputNomPack">Montant</label>
                                                    <input class="form-control" name="montant" type="number" step="any" placeholder="Montant"/>
                                                  </div>
                                                  <div class="form-group">
                                                    <label for="inputPrixVente">Date de paiement</label>
                                                    <input class="form-control" name="datePaiement" type="date"/>
                                                  </div>
                                                </div>
                                                <div class="container mt-5" id="error">
                                                  <!-- <div class="alert alert-danger" role="alert"> -->
                                                    
                                                  <!-- </div> -->
                                                </div> 
                                                <div class="modal-footer">
                                                  <button type="button" class="btn btn-secondary" data-dismiss="modal">
                                                    Fermer
                                                  </button>
                                                  <button type="submit" class="btn btn-primary">
                                                    Payer
                                                  </button>
                                                </div>
                                              </form>
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