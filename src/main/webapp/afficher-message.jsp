<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>

<%@ page import="fr.miage.moureypierson.model.Message" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<% Message m =  Message.findById(Long.parseLong(request.getParameter("id"))); %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <title>Annuaire</title>

    <!-- Bootstrap -->
    <link href="bootstrap.min.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->

    <script
            src="https://code.jquery.com/jquery-2.2.4.min.js"
            integrity="sha256-BbhdlvQf/xTY9gja0Dq3HiwQF8LaCRTXxZKRutelT44="
            crossorigin="anonymous"></script>
    <sb:head/>
</head>
<body>
<div class="container">
    <h1>Consultez vos messages.</h1>
    <a href="accueil"><button class="btn btn-info">Retour</button></a><br>
    <br>
    <label>Expéditeur : </label> <%= m.getExpediteur().getName() %><br>
    <label>Objet : </label> <%= m.getObjet() %><br>
    <% if(m.getFile() != null && !m.getFile().isEmpty()) { %>
        <label>Pièce jointe : </label> <a href="uploads/files/<%= m.getFile() %>"><%= m.getFile().split("/")[1]%></a><br>
   <% } %>
    <label>Corps : </label><br>
    <%= m.getCorps() %>
</div>
</div>

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="js/bootstrap.min.js"></script>
</body>
</html>