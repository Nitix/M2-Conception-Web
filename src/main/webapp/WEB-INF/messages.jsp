<%@ page import="fr.miage.moureypierson.model.Annuaire" %>
<%@ page import="java.util.Set" %>
<%@ page import="fr.miage.moureypierson.model.Abonne" %>
<%@ page import="fr.miage.moureypierson.model.Message" %>
<%@ page import="java.util.ArrayList" %>
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
    <h1>Consultez vos messages.</h1>
    <div class="container-fluid">
            <ul class="menu clearfix">
                <% ArrayList<Message>listeMessage = new ArrayList<>(); //Set<Message> listeMessage = Abonne.getMessages();
                    for (Message m : listeMessage){%>
                <li><a><%= m.getExpediteur()%> <%= m.getObjet()%></a></li>
                <%
                    }
                %>
            <ul/>
        </div>
    </div>
    
    <h1>Choissiez un abonné à qui envoyer votre message.</h1>
    <div class="container-fluid">
        <div class="row">
            <div class="col-md-6">
                <form method="post" action="index.jsp">
                    <input type="hidden" name="type" value="inscription">
                    <div class="form-group">
                        <label for="destinataire">Destinataire</label>
                        <SELECT id="destinataire" name="destinataire" size="1">
                            <% Set<Abonne> liste = Annuaire.getAbonnes();
                                for (Abonne a : liste){%>
                                <OPTION value="<%= a.getLogin()%>"><%= a.getLogin()%></OPTION>
                            <%
                                }
                            %>
                        </SELECT>
                    </div>
                    <div class="form-group">
                        <label for="message_content">Message</label>
                        <textarea type="text" class="form-control" id="message_content" name="message_content" placeholder="Contenu de votre message"></textarea>
                    </div>

                    <button type="submit" class="btn btn-default">Envoyer</button>
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