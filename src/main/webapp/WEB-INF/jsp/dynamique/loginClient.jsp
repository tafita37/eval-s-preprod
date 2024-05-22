<%@ include file="../static/cssPart.jsp" %>
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
                                <h4>Se connecter en tant que client</h4>
                                <form class="pt-3" method="post" action="/login/loginClient">
                                    <div class="form-group">
                                        <input 
                                            type="text" 
                                            class="form-control form-control-lg" 
                                            id="exampleInputEmail1"
                                            placeholder="Email"
                                            name="numeroClient"
                                            value="0341880137"
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