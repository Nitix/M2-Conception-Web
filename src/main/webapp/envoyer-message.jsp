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
    <div class="row">
        <h1 class="text-center">Envoyer un message</h1>
        <s:actionerror theme="bootstrap"/>
        <s:actionmessage theme="bootstrap"/>
        <s:fielderror theme="bootstrap"/>
        <div class="col-md-12">
            <a href="accueil">< Retour</a>
            <s:form enctype="multipart/form-data" action="envoyerMessage" theme="bootstrap" cssClass="form-horizontal" label="Envoyer un message"
                    method="POST">
                <s:textfield name="objet" label="Objet du message"/>
                <s:textarea name="contenu" label="Contenu du message"/>
                <s:file name="fichier" label="Pièce jointe"/>
                <s:submit value="Envoyer un message" cssClass="btn btn-primary"/>
            </s:form>
        </div>
    </div>
</div>
</body>
</html>