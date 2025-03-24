<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.constructionxpert.model.Task" %>
<%@ page import="com.example.constructionxpert.model.Project" %>
<%@ page import="com.example.constructionxpert.model.Ressource" %>
<html>
<head>
    <title>Voir les Tâches</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" rel="stylesheet">
    <style>
        body {
            background: url('assets/images/6029473.jpg') no-repeat center center fixed;
            background-size: cover;
        }

        .blurry-box {
            background: rgba(42, 42, 42, 0.4);
            backdrop-filter: blur(10px);
            padding: 20px;
            border-radius: 15px;
            border: 2px solid #5AE4A7;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
            width: auto;
            /*max-width: 1800px;*/
            margin: 20px auto;
        }

        .sectionTable {
            display: flex;
            justify-content: center;
            width: 82%;
            margin-left: 250px;
            height: auto;
        }

        .table {
            color: white;
        }

        .table thead th {
            background-color: #5AE4A7;
            color: #000;
        }

        .table tbody td {
            color: white;
        }

        .btn-custom {
            background-color: #5AE4A7;
            border: none;
            color: #000;
            transition: background-color 0.3s ease;
        }

        .btn-custom:hover {
            background-color: #4acf97;
        }

        body {
            display: flex;
            flex-direction: column;
        }

        section {
            width: 100%;
        }

        #options {
            display: flex;
            gap: 2px;
        }

        .ressourceBtn {
            font-size: 11px;
        }

        #ressource {
            display: flex;
            gap: 4px;
        }

        #remove-ressource {
            background: none;
            border: none;
            margin-top: 2px;
        }
    </style>
</head>
<body>
<section>
    <jsp:include page="header.jsp" />
</section>
<section class="sectionTable">
    <div class="blurry-box">
        <h1 class="text-center mb-4">Liste des Tâches</h1>
        <table class="table table-bordered table-striped">
            <thead>
            <tr>
                <th>ID de Tâche</th>
                <th>Nom</th>
                <th>Description</th>
                <th>Date de Début</th>
                <th>Date de Fin</th>
                <th>Nom du Projet</th>
                <th>Ressources</th>
                <th>Options</th>
            </tr>
            </thead>
            <tbody>
            <%
                List<Task> tasks = (List<Task>) request.getAttribute("tasks");
                if (tasks != null) {
                    for (Task task : tasks) {
                        Project project = task.getProject();
                        List<Ressource> ressources = task.getRessources();
            %>
            <tr>
                <td><%= task.getTask_id() %></td>
                <td><%= task.getName() %></td>
                <td><%= task.getDescription() %></td>
                <td><%= task.getStart_date() %></td>
                <td><%= task.getFinish_date() %></td>
                <td><%= project != null ? project.getName() : "N/A" %></td>
                <td>
                    <%
                        if (ressources != null) {
                            for (Ressource ressource : ressources) {
                                int tacheRessourceId = ressource.getTacheRessourceId();
                    %>
                    <div id="ressource">
                        <form action="/task?action=deleteressourcetask" method="post" onsubmit="return confirm('Êtes-vous sûr de vouloir supprimer cette ressource ?');">
                            <input type="hidden" name="task_ressourceId" value="<%= tacheRessourceId %>">
                            <button type="submit" id="remove-ressource"><i class="fas fa-trash fa-s" style="color: #ff1100;"></i></button>
                        </form>
                        <%= ressource.getName() %> (<%= ressource.getType() %>)<br>
                    </div>
                    <%
                        }
                    } else {
                    %>
                    Aucune ressource assignée
                    <%
                        }
                    %>
                </td>
                <td id="options">
                    <form action="/task?action=addressourceforn" method="post">
                        <input type="hidden" name="taskId" value="<%= task.getTask_id() %>">
                        <button type="submit" class="btn btn-primary ressourceBtn"><i class="fas fa-object-group fa-lg"></i> Ressource</button>
                    </form>
                    <form action="/task?action=editform" method="post">
                        <input type="hidden" name="taskId" value="<%= task.getTask_id() %>">
                        <button type="submit" class="btn btn-warning"><i class="fas fa-pen"></i></button>
                    </form>
                    <form action="/task?action=delete" method="post" onsubmit="return confirm('Êtes-vous sûr de vouloir supprimer cette tâche ?');">
                        <input type="hidden" name="taskId" value="<%= task.getTask_id() %>">
                        <button type="submit" class="btn btn-danger"><i class="fas fa-trash"></i></button>
                    </form>
                </td>
            </tr>
            <%
                    }
                }
            %>
            </tbody>
        </table>
    </div>
</section>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>