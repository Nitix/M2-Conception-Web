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
        <h1 class="text-center">Identifiez-vous ou créez votre compte pour utiliser notre service.</h1>
        <s:actionerror theme="bootstrap"/>
        <s:actionmessage theme="bootstrap"/>
        <s:fielderror theme="bootstrap"/>
        <div class="col-md-6" style="border-right: 1px solid;border-image: linear-gradient(to bottom, rgba(255,255,255,0) 0%,rgb(119,119,119) 50%,rgba(255,255,255,0) 100%) 1 100%;" >
            <s:form action="inscription" theme="bootstrap" cssClass="form-horizontal" label="Inscription" method="POST">
                <div class="col-md-12">
                    <s:textfield label="Identifiant" name="login"/>
                    <s:password label="Mot de passe" name="password"/>
                    <s:select list="annuaires" listKey="id" listValueKey="nom" name="annuaire" label="Annuaire"/>
                    <s:checkbox label="Je suis une entreprise" name="entreprise" fieldValue="true"/>
                    <s:textfield label="Nom" name="nom" tooltip="Inutilse si vous êtes une entreprise" tooltipDelay="0"/>
                    <s:textfield label="Prénom" name="prenom" tooltip="Inutilse si vous êtes une entreprise"
                                 tooltipDelay="0"/>
                    <s:textfield label="Raison sociale" name="raison" tooltip="Inutilse si vous êtes une personne"
                                 tooltipDelay="0"/>
                    <s:submit label="S'inscrire" cssClass="btn btn-primary"/>
                </div>

            </s:form>
        </div>
        <div class="col-md-6">
            <s:form action="identification" theme="bootstrap" cssClass="form-horizontal" label="Connexion" method="POST">
                <div class="col-md-12">
                    <s:textfield label="Identifiant" name="login"/>
                    <s:password label="Mot de passe" name="password"/>
                    <s:submit label="Se connecter" cssClass="btn btn-primary"/>
                </div>

            </s:form>
        </div>
    </div>
</div>
</body>
</html>