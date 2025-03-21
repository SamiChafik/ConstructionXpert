<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.example.constructionxpert.model.Task" %>
<%@ page import="com.example.constructionxpert.model.Project" %>
<%@ page import="com.example.constructionxpert.model.Ressource" %>
<%@ page import="java.util.List" %>
<html>
<head>
    <title>View Tasks</title>
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
        <h1 class="text-center mb-4">Tasks List</h1>
        <table class="table table-bordered table-striped">
            <thead>
            <tr>
                <th>Task ID</th>
                <th>Name</th>
                <th>Description</th>
                <th>Start Date</th>
                <th>Finish Date</th>
                <th>Project Name</th>
                <th>Resources</th>
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
                    %>
                    <%= ressource.getName() %> (<%= ressource.getType() %>)<br>
                    <%
                        }
                    } else {
                    %>
                    No resources assigned
                    <%
                        }
                    %>
                </td>
                <td>

                    <form action="/task?action=addressourceforn" method="post">
                        <input type="hidden" name="taskId" value="<%= task.getTask_id() %>">
                        <button type="submit" class="btn btn-primary">Add task</button>
                    </form>
                    <!-- Edit Task Button -->
                    <form action="/task?action=editform" method="post">
                        <input type="hidden" name="taskId" value="<%= task.getTask_id() %>">
                        <button type="submit" class="btn btn-warning">Edit</button>
                    </form>
                    <!-- Delete Task Button -->
                    <form action="/task?action=delete" method="post" onsubmit="return confirm('Are you sure you want to delete this task?');">
                        <input type="hidden" name="taskId" value="<%= task.getTask_id() %>">
                        <button type="submit" class="btn btn-danger">Delete</button>
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