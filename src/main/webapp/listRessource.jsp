<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.example.constructionxpert.model.Project" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.constructionxpert.model.User" %>
<%@ page import="com.example.constructionxpert.model.Ressource" %>
<html>
<head>
    <title>View Projects</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <!-- Font Awesome for icons -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" rel="stylesheet">
    <style>

        body {
            background: url('assets/images/7014634.jpg') no-repeat center center fixed;
            background-size: cover;
            margin: 0;
            padding: 0;
            height: 90vh;
            flex-direction: row;
            /*align-items: center;*/
            /*justify-content: center;*/
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

        .sectionTable{
            display: flex;
            /*align-items: center;*/
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
    </style>
</head>
<body>
<section>
    <jsp:include page="header.jsp" />
</section>
<section class="sectionTable">
    <div class="blurry-box">
        <h1 class="text-center mb-4">Ressource List</h1>
        <table class="table table-bordered table-striped">
            <thead>
            <tr>
                <th>Ressource ID</th>
                <th>Name</th>
                <th>Type</th>
                <th>Quantity</th>
                <th>Supplier</th>
                <th>Options</th>
            </tr>
            </thead>
            <tbody>
            <%
                List<Ressource> ressources = (List<Ressource>) request.getAttribute("ressources");
                if (ressources != null) {
                    for (Ressource ressource : ressources) {
            %>
            <tr>
                <td><%= ressource.getRessource_id() %></td>
                <td><%= ressource.getName() %></td>
                <td><%= ressource.getType() %></td>
                <td><%= ressource.getQuantity() %></td>
                <td><%= ressource.getSupplier() %></td>
                <td>
                    <!-- Edit Project Button -->
                    <a href="ressource?...=<%= ressource.getRessource_id() %>" class="btn btn-custom btn-sm">
                        <i class="fas fa-edit"></i> Edit
                    </a>
                    <!-- Delete Project Button -->
                    <%--                    <a href="project?action=delete?projectId=<%= project.getProject_id() %>" class="btn btn-danger btn-sm" onclick="return confirm('Are you sure you want to delete this project?');">--%>
                    <%--                        <i class="fas fa-trash"></i> Delete--%>
                    <%--                    </a>--%>
                    <form action="/ressource?action=delete" method="post" onsubmit="return confirm('Are you sure you want to delete this ressource?');">
                        <input type="hidden" name="ressourceId" value="<%= ressource.getRessource_id() %>">
                        <button type="submit" class="btn btn-danger">Supprimer</button>
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