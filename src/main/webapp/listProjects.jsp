<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.example.constructionxpert.model.Project" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.constructionxpert.model.User" %>
<html>
<head>
    <title>Voir les Projets</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" rel="stylesheet">
    <style>
        body {
            background: url('assets/images/7014634.jpg') no-repeat center center fixed;
            background-size: cover;
            margin: 0;
            padding: 0;
            height: 90vh;
            flex-direction: row;
        }

        .blurry-box {
            background: rgba(42, 42, 42, 0.4);
            backdrop-filter: blur(10px);
            padding: 20px;
            border-radius: 15px;
            border: 2px solid #5AE4A7;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
            width: 90%;
            max-width: 1200px;
            margin: 20px auto;
        }

        .sectionTable {
            display: flex;
            justify-content: center;
            width: 82%;
            margin-left: 250px;
            height: auto;
        }

        .project-grid {
            display: grid;
            grid-template-columns: repeat(2, 1fr);
            gap: 20px;
        }

        .project-box {
            background: rgba(67, 67, 67, 0.5);
            backdrop-filter: blur(10px);
            padding: 20px;
            border-radius: 10px;
            border: 1px solid #343a40;
            color: white;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
            transition: transform 0.2s ease;
        }

        .project-box:hover {
            transform: scale(1.01);
        }

        .project-box h3 {
            margin-top: 0;
            color: #5AE4A7;
        }

        .project-box p {
            margin: 5px 0;
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

        #options {
            display: flex;
            gap: 10px;
            margin-top: 15px;
        }

        .btn-warning, .btn-danger {
            color: white;
        }

        @media (max-width: 768px) {
            .project-grid {
                grid-template-columns: 1fr;
            }
        }
    </style>
</head>
<body>
<section>
    <jsp:include page="header.jsp" />
</section>
<section class="sectionTable">
    <div class="blurry-box">
        <h1 class="text-center mb-4">Liste des Projets</h1>
        <div class="project-grid">
            <%
                List<Project> projects = (List<Project>) request.getAttribute("projects");
                if (projects != null) {
                    for (Project project : projects) {
            %>
            <div class="project-box">
                <h3><%= project.getName() %></h3>
                <p><strong>ID:</strong> <%= project.getProject_id() %></p>
                <p><strong>Description:</strong> <%= project.getDescription() %></p>
                <p><strong>Date de Début:</strong> <%= project.getStart_date() %></p>
                <p><strong>Date de Fin:</strong> <%= project.getFinish_date() %></p>
                <p><strong>Budget:</strong> <%= project.getBudget() %></p>
                <div id="options">
                    <form action="/task?action=new" method="post">
                        <input type="hidden" name="projectId" value="<%= project.getProject_id() %>">
                        <button type="submit" class="btn btn-primary btn-sm"><i class="fas fa-thumbtack"></i></button>
                    </form>
                    <form action="/project?action=editform" method="post">
                        <input type="hidden" name="projectId" value="<%= project.getProject_id() %>">
                        <button type="submit " class="btn btn-warning btn-sm"><i class="fas fa-pen"></i></button>
                    </form>
                    <form action="/project?action=delete" method="post" onsubmit="return confirm('Êtes-vous sûr de vouloir supprimer ce projet ?');">
                        <input type="hidden" name="projectId" value="<%= project.getProject_id() %>">
                        <button type="submit" class="btn btn-danger btn-sm"><i class="fas fa-trash"></i></button>
                    </form>
                </div>
            </div>
            <%
                    }
                }
            %>
        </div>
    </div>
</section>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>