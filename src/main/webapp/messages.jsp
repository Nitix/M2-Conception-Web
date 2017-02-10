<%@ page import="fr.miage.moureypierson.model.Annuaire" %>
<%@ page import="fr.miage.moureypierson.model.Abonne" %>
<%@ page import="fr.miage.moureypierson.model.Message" %>
<%@ page import="java.util.List" %>

<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="fr">
<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <title>Annuaire</title>

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
        <h1>Actions disponibles</h1>
        <a href="envoyerMessage"><button class="btn btn-info">Envoyer un message</button></a>
        <s:if test="%{user.administrator}">
            <a href="ajouterAnnuaire"><button class="btn btn-info">Ajouter un annuaire</button></a>
            <a href="supprimerAnnuaire"><button class="btn btn-info">Supprimer un annuaire</button></a>
        </s:if>
        <s:else>
            <a href="desinscrire"><button class="btn btn-danger">Se désinscrire</button></a>
        </s:else>
        <a href="voirAbonne"><button class="btn btn-info">Voir les abonnées</button></a>
        <a href="deconnexion"><button class="btn btn-info">Se déconnecter</button></a>

        <h1>Consultez vos messages.</h1>
        <ul class="menu clearfix">
            <s:iterator value="messages" >
                <li><a href="afficherMessage?id=<s:property value="id" />"><s:property value="expediteur.name" />: <s:property value="objet" /></a></li>
            </s:iterator>
        </ul>
    </div>
    </body>
</html>