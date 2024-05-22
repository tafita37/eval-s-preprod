<%@ include file="../static/cssPart.jsp" %>
<%
    String errorMessage=(String) request.getAttribute("errorMessage");
%>
    <body>
        <div class="container-scroller">
            <div class="container-fluid page-body-wrapper full-page-wrapper">
                <div class="content-wrapper d-flex align-items-center auth px-0">
                    <div class="row w-100 mx-0">
                        <div class="col-lg-4 mx-auto">
                            <div class="auth-form-light text-left py-5 px-4 px-sm-5">
                                <div class="brand-logo">
                                    <img src="/images/changeEval/logo/logo1.png" alt="logo">
                                </div>
                                <h4>Se connecter en tant que BTP</h4>
                                <form class="pt-3" method="post" action="/login/loginBtp">
                                    <div class="form-group">
                                        <input 
                                            type="email" 
                                            class="form-control form-control-lg" 
                                            id="exampleInputEmail1"
                                            placeholder="Email"
                                            name="emailBtp"
                                            value="btp@gmail.com"
                                        />
                                    </div>
                                    <div class="form-group">
                                        <input 
                                            type="password" 
                                            class="form-control form-control-lg"
                                            id="exampleInputPassword1" 
                                            placeholder="Password"
                                            name="mdpBtp"
                                            value="0000"
                                        />
                                    </div>
                                    <div class="mt-3">
                                        <button 
                                            type="submit" 
                                            class="btn btn-block btn-primary btn-lg font-weight-medium auth-form-btn"
                                        >
                                            SIGN IN
                                        </button>
                                    </div>
                                </form>                                 
                                <%
                                    if(errorMessage!=null) {
                                        %>
                                        <div class="container mt-5">
                                            <div class="alert alert-danger" role="alert">
                                                <%
                                                    out.println(errorMessage);
                                                %>
                                            </div>
                                        </div> 
                                    <% }
                                %> 
                            </div>
                        </div>
                    </div>
                </div>
                <!-- content-wrapper ends -->
            </div>
            <!-- page-body-wrapper ends -->
        </div>
        <%@ include file="../static/jsPart.jsp" %>
    </body>

    </html>