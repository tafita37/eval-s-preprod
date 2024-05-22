<%
    String[] listeJsFiles=(String[]) request.getAttribute("jssFiles");
%>

<script src="/vendors/js/vendor.bundle.base.js"></script>
<!-- endinject -->
<!-- Plugin js for this page -->
<!-- End plugin js for this page -->
<!-- inject:js -->
<script src="/js/off-canvas.js"></script>
<script src="/js/hoverable-collapse.js"></script>
<script src="/js/template.js"></script>
<script src="/js/settings.js"></script>
<script src="/js/todolist.js"></script>
<%
    if(listeJsFiles!=null) {
        for(int i=0; i<listeJsFiles.length; i++) {
            %>
            <script src='<% out.println("/js/"+listeJsFiles[i]); %>'></script>
        <% }
    }
%>