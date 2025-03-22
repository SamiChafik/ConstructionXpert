<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Project</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" rel="stylesheet">
    <style>
        body {
            background: url('assets/images/7014634.jpg') no-repeat center center fixed;
            background-size: cover;
        }

        .main-content {
            margin-left: 250px;
            padding: 20px;
        }

        .form-container {
            background: rgba(42, 42, 42, 0.274);
            backdrop-filter: blur(10px);
            max-width: 600px;
            margin: 0 auto;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
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
    </style>
</head>
<body>
<jsp:include page="header.jsp" />
<div class="main-content">
    <h1>Add Project</h1>
    <div class="form-container">
        <form action="project?action=edit" method="post">
            <input type="hidden" name="projectId" value="<%= request.getAttribute("project_id") == null ? "" : request.getAttribute("project_id") %>">

            <div class="form-group">
                <label for="name">Project Name:</label>
                <input type="text" id="name" name="name" class="form-control" value="<%= request.getAttribute("name") == null ? "" : request.getAttribute("name") %>" required>
            </div>

            <div class="form-group">
                <label for="description">Description:</label>
                <textarea id="description" name="description" class="form-control" rows="4" required>
                    <%= request.getAttribute("description") == null ? "" : request.getAttribute("description") %>
                </textarea>
            </div>

            <div class="form-group">
                <label for="start_date">Start Date:</label>
                <input type="date" id="start_date" name="start_date" class="form-control" value="<%= request.getAttribute("start_date") == null ? "" : request.getAttribute("start_date") %>" required>
            </div>

            <div class="form-group">
                <label for="finish_date">Finish Date:</label>
                <input type="date" id="finish_date" name="finish_date" class="form-control" value="<%= request.getAttribute("finish_date") == null ? "" : request.getAttribute("finish_date") %>" required>
            </div>

            <div class="form-group">
                <label for="budget">Budget:</label>
                <input type="number" id="budget" name="budget" class="form-control" value="<%= request.getAttribute("budget") == null ? "" : request.getAttribute("budget") %>" required>
            </div>

            <button type="submit" class="btn btn-custom btn-block">Modifier</button>
        </form>
    </div>
</div>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>