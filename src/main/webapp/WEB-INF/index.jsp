<%@ page contentType="text/html; charset=UTF-8" %>
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
</head>
    <body>
    <h1>Identifiez-vous ou créez votre compte pour utiliser notre service.</h1>
    <div class="container-fluid">
        <div class="row">
            <div class="col-md-6">
                <form method="post" action="index.jsp">
                    <input type="hidden" name="type" value="inscription">
                    <div class="form-group">
                        <label for="login">Identifiant</label>
                        <input type="text" class="form-control" id="login" name="login" laceholder="Identifiant">
                    </div>
                    <div class="form-group">
                        <label for="password">Password</label>
                        <input type="password" class="form-control" id="password" name="password" placeholder="Mot de passe">
                    </div>
                    <div class="checkbox">
                        <label>
                            <input name="entreprise" type="checkbox"> Je suis une entreprise
                        </label>
                    </div>
                    <div class="form-group">
                        <label for="nom">Nom</label>
                        <input type="text" class="form-control" id="nom" name="nom" placeholder="Nom">
                        <p class="help-block">Inutile si vous êtes une entreprise</p>
                    </div>
                    <div class="form-group">
                        <label for="prenom">Prénom</label>
                        <input type="text" class="form-control" id="prenom" name="prenom" placeholder="Prénom">
                        <p class="help-block">Inutile si vous êtes une entreprise</p>
                    </div>
                    <div class="form-group">
                        <label for="raison">Raison sociale</label>
                        <input type="text" class="form-control" id="raison" name="raison" placeholder="Raison sociale">
                        <p class="help-block">Inutile si vous êtes une personne</p>
                    </div>
                    <button type="submit" class="btn btn-default">S'enregistrer</button>
                </form>
            </div>
            <div class="col-md-2">
                <form class="form-horizontal" method="post" action="index.jsp">
                    <input type="hidden" name="type" value="connecxion">
                    <div class="form-group">
                        <label for="login"-co>Identifiant</label>
                        <input type="text" class="form-control" id="login-co" name="login" placeholder="Identifiant">
                    </div>
                    <div class="form-group">
                        <label for="password"-co>Password</label>
                        <input type="password" class="form-control" id="password-co" name="password" placeholder="Mot de passe">
                    </div>
                    <div class="form-group">
                        <div class="col-sm-offset-2 col-sm-10">
                            <button type="submit" class="btn btn-default">S'identifier/button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>


    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/bootstrap.min.js"></script>
    </body>
</html>