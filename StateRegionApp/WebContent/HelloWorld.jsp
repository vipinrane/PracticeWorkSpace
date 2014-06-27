<%@ page contentType="text/html; charset=UTF-8" %>    
<%@ taglib prefix="s" uri="/struts-tags" %>

<script>
    
    <%@include file="../js/jquery-1.8.2.js"%>
    
</script>
 
<html>

    <!-- JavaScript Plugins -->
    <script>
        function getLoad(){

            var stateId = $('#state').val();

            $.getJSON('HelloWorld.action', {'stateId': stateId},
            function(data) {

                var divisionList = (data.regnList);

                var options = $("#regn");
                options.find('option')
                .remove()
                .end();
                options.append($("<option />").val("-1").text("--Select--"));
                $.each(divisionList, function() {

                    options.append($("<option />").val(this.regnId).text(this.regnName));
                });
            }
        );}
    </script>

    <!-- jQuery-UI Dependent Scripts -->

    <body>
        State List <s:select name="stateId" list="stateList" id="state" listKey="stateId" onchange="getLoad()" listValue="stateName" headerKey="0" headerValue="--select--" />
    Regn List <s:select name="regnId"  list="regnList" listKey="regnId" id="regn" listValue="regnName" headerKey="0" headerValue="--select--" />
</body>
</html>