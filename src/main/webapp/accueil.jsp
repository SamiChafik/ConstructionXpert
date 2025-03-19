<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Accueil</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <style>
        * {
            font-family: Arial, Helvetica, sans-serif;
        }

        .navbar-brand img {
            height: 80px;
        }

        .custom-navbar {
            background-color: #5AE4A7;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
        }

        .navbar-nav .nav-link {
            color: #333;
            margin: 0 10px;
            transition: color 0.3s ease;
        }

        .navbar-nav .nav-link:hover {
            color: #ffffff;
        }

        .navbar-nav .nav-item:last-child .nav-link {
            background-color: #000000;
            color: white;
            padding: 8px 20px;
            border-radius: 5px;
            transition: background-color 0.3s ease;
        }

        .navbar-nav .nav-item:last-child .nav-link:hover {
            background-color: #ffffff;
            color: rgb(0, 0, 0);
        }

        #section1 {
            height: 500px;
            background: url(assets/images/7014634.jpg);
            background-attachment: fixed;
            background-size: cover;
            background-position: center;
            display: flex;
            justify-content: center;
            align-items: center;
        }

        .blurry-box {
            background: rgba(255, 255, 255, 0.2);
            backdrop-filter: blur(10px);
            padding: 40px;
            border-radius: 15px;
            text-align: center;
            max-width: 600px;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
            transition: transform 0.3s ease, box-shadow 0.3s ease;
        }

        .blurry-box:hover {
            transform: translateY(-10px);
            box-shadow: 0 8px 12px rgba(0, 0, 0, 0.2);
        }

        .blurry-box h1 {
            color: #000000;
            margin-bottom: 20px;
        }

        .blurry-box p {
            color: #000000;
            font-size: 18px;
            line-height: 1.6;
        }

        #section2 {
            background-color: #658074;
            padding: 60px 20px;
            display: flex;
            justify-content: center;
            gap: 90px;
            flex-wrap: wrap;
        }

        .blurry-box img {
            width: 100%;
            max-width: 300px;
            height: auto;
            border-radius: 10px;
            margin-bottom: 20px;
        }

        .blurry-box h2 {
            color: #000000;
            font-size: 24px;
            margin-bottom: 10px;
        }
    </style>
</head>
<body>
<header>
    <nav class="navbar navbar-expand-lg custom-navbar" id="navabr">
        <!-- Logo on the left -->
        <a class="navbar-brand" href="#">
            <img src="assets/logo/ConstructionXpert__1_-removebg-preview_3.PNG" alt="Logo">
        </a>

        <!-- Hamburger menu for mobile -->
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <!-- Navbar items -->
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav ml-auto">
                <!-- Les Projets -->
                <li class="nav-item">
                    <a class="nav-link" href="/project?action=list">Les Projets</a>
                </li>
                <!-- Les Ressources -->
                <li class="nav-item">
                    <a class="nav-link" href="#">Les Ressources</a>
                </li>
                <!-- Se Connecter -->
                <li class="nav-item">
                    <a class="nav-link" href="/login">Se Connecter</a>
                </li>
            </ul>
        </div>
    </nav>
</header>
<main>
    <section id="section1">
        <div class="blurry-box">
            <h1>À Propos</h1>
            <p>ConstructionXpert Services Solution est une application web pour gérer des projets de construction. Elle permet de créer, organiser et suivre des projets, assigner des tâches, et gérer les ressources avec une interface conviviale.</p>
        </div>
    </section>
    <section id="section2">
        <div class="blurry-box">
            <img src="assets/images/crop-architect-opening-blueprint-2.JPG" alt="Image 1">
            <h2>Projets</h2>
        </div>
        <div class="blurry-box">
            <img src="assets/images/building-plans-design-2.JPG" alt="Image 2">
            <h2>Ressources</h2>
        </div>
    </section>
</main>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>