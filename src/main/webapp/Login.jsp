<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background: url('assets/images/7014634.jpg') no-repeat center center fixed;
            background-size: cover;
            height: 100vh;
            display: flex;
            align-items: center;
            justify-content: center;
            margin: 0;
        }

        .login-container {
            background: rgba(67, 67, 67, 0.2);
            backdrop-filter: blur(10px);
            padding: 30px;
            border-radius: 10px;
            border: 2px solid #5AE4A7;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
            max-width: 400px;
            width: 100%;
        }

        .error-message {
            color: red;
            text-align: center;
            margin-top: 15px;
        }

        .btn-custom {
            background-color: #5AE4A7;
            border: none;
            color: #000;
            transition: background-color 0.3s ease;
        }

        .btn-custom:hover {
            background-color: #10d782;
        }
    </style>
</head>
<body>
<div class="login-container">
    <form action="login" method="post">
        <div class="form-group">
            <label for="email">Email:</label>
            <input type="email" id="email" name="email" class="form-control" required>
        </div>
        <div class="form-group">
            <label for="password">Password:</label>
            <input type="password" id="password" name="password" class="form-control" required>
        </div>
        <div class="buttons-container">
            <button type="submit" class="btn btn-custom btn-block">Se Connecter</button>
            <a href="accueil.jsp" class="btn btn-custom btn-block">Accueil</a>
        </div>
    </form>

    <% if (request.getParameter("error") != null) { %>
    <p class="error-message">Invalid email or password. Please try again.</p>
    <% } %>
</div>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>