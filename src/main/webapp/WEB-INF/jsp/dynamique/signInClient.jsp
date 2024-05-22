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
                                <h4>Inscrivez vous</h4>
                                <form class="pt-3" method="post" action="/login/signInClient">
                                    <div class="form-group">
                                        <input 
                                            type="email" 
                                            class="form-control form-control-lg" 
                                            id="exampleInputEmail1"
                                            placeholder="Email"
                                            name="emailClient"
                                            value="client1@gmail.com"
                                        />
                                    </div>
                                    <div class="form-group">
                                        <input 
                                            type="password" 
                                            class="form-control form-control-lg"
                                            id="exampleInputPassword1" 
                                            placeholder="Password"
                                            name="mdpClient"
                                            value="0000"
                                        />
                                    </div>
                                    <div class="mt-3">
                                        <button 
                                            type="submit" 
                                            class="btn btn-block btn-primary btn-lg font-weight-medium auth-form-btn"
                                        >
                                            SIGN UP
                                        </button>
                                    </div>
                                    <div class="text-center mt-4 font-weight-light">
                                        Already have an account? Login 
                                        <a href="/login/formLoginClient" class="text-primary">Login</a>
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