<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Ajouter une Ressource</title>
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
  <div class="form-container">
    <h1>Ajouter une Ressource</h1>
    <form action="ressource?action=add" method="post">
      <input type="hidden" name="ressource_id" value="">

      <div class="form-group">
        <label for="name">Nom de la Ressource :</label>
        <input type="text" id="name" name="name" class="form-control" required>
      </div>

      <div class="form-group">
        <label for="type">Type de Ressource :</label>
        <input type="text" id="type" name="type" class="form-control" required>
      </div>

      <div class="form-group">
        <label for="quantity">Quantité de Ressource :</label>
        <input type="text" id="quantity" name="quantity" class="form-control" required>
      </div>

      <div class="form-group">
        <label for="supplier">Fournisseur :</label>
        <input type="text" id="supplier" name="supplier" class="form-control" required>
      </div>

      <button type="submit" class="btn btn-custom btn-block">Ajouter la Ressource</button>
    </form>
  </div>
</div>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>