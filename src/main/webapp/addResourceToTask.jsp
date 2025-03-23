<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.example.constructionxpert.model.Ressource" %>
<%@ page import="java.util.List" %>
<html>
<head>
    <title>Add Resource to Task</title>
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

        .btn-custom:disabled {
            background-color: #cccccc;
            color: #666666;
        }

        .btn-custom:hover {
            background-color: #4acf97;
        }

        .error-message {
            color: #dc3545; /* Red color for error messages */
            font-size: 0.875em; /* Smaller font size */
            margin-top: 5px;
            display: none; /* Hidden by default */
        }
    </style>
</head>
<body>
<jsp:include page="header.jsp" />
<div class="main-content">
    <div class="form-container">
        <h1>Add Resource to Task</h1>
        <form action="task?action=addressourcetotask" method="post">
            <input type="hidden" name="taskId" value="<%= request.getAttribute("taskId") %>">

            <div class="form-group">
                <label for="resourceId">Select Resource:</label>
                <select id="resourceId" name="resourceId" class="form-control" required>
                    <option value="">-- Select a Resource --</option>
                    <%
                        List<Ressource> ressources = (List<Ressource>) request.getAttribute("ressources");
                        if (ressources != null) {
                            for (Ressource ressource : ressources) {
                    %>
                    <option value="<%= ressource.getRessource_id() %>" data-quantity="<%= ressource.getQuantity() %>">
                        <%= ressource.getName() %> (<%= ressource.getQuantity() %> available)
                    </option>
                    <%
                            }
                        }
                    %>
                </select>
            </div>

            <div class="form-group">
                <label for="resourceQuantity">Quantity to Use:</label>
                <input type="number" id="resourceQuantity" name="resourceQuantity" class="form-control" required onchange="validateQuantity()">
                <!-- Error message div -->
                <div id="quantityError" class="error-message">
                    The entered quantity exceeds the available quantity.
                </div>
            </div>

            <button type="submit" id="submitButton" class="btn btn-custom btn-block">Add Resource</button>
        </form>
    </div>
</div>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

<script>
    function validateQuantity() {
        const resourceSelect = document.getElementById('resourceId');
        const quantityInput = document.getElementById('resourceQuantity');
        const submitButton = document.getElementById('submitButton');
        const errorMessage = document.getElementById('quantityError');

        const selectedResource = resourceSelect.options[resourceSelect.selectedIndex];
        const availableQuantity = selectedResource.getAttribute('data-quantity');
        const enteredQuantity = quantityInput.value;

        if (parseInt(enteredQuantity) > parseInt(availableQuantity)) {
            submitButton.disabled = true;
            submitButton.classList.remove('btn-custom');
            submitButton.classList.add('btn-secondary');
            errorMessage.style.display = 'block';
        } else {
            submitButton.disabled = false;
            submitButton.classList.remove('btn-secondary');
            submitButton.classList.add('btn-custom');
            errorMessage.style.display = 'none';
        }
    }

    document.getElementById('resourceId').addEventListener('change', validateQuantity);
</script>
</body>
</html>