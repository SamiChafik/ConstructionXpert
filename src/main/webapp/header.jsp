<%@ page import="com.example.constructionxpert.model.User" %>
<%@ page session="true" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<%--%>
<%--    User user = (User) session.getAttribute("user");--%>
<%--    String name = user != null ? user.getName() : "";--%>
<%--%>--%>
<link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" rel="stylesheet">
<style>
    .sidebar {
        height: 100%;
        width: 250px;
        position: fixed;
        top: 0;
        background-color: #343a40;
        padding-top: 20px;
        transition: all 0.3s;
        z-index: 1000;
    }

    .sidebar.active {
        left: 0;
    }

    .sidebar a {
        padding: 10px 15px;
        text-decoration: none;
        font-size: 18px;
        color: #d1d1d1;
        display: block;
    }

    .sidebar a:hover {
        background-color: #5AE4A7;
        color: #000;
    }

    .sidebar-logo-name img {
        height: auto;
        width: 150px;
    }

    .sidebar-logo {
        display: flex;
        align-items: center;
        justify-content: center;
    }

    .sidebar-toggle {
        position: fixed;
        left: 15px;
        top: 15px;
        z-index: 1001;
        background: #343a40;
        color: white;
        border: none;
        border-radius: 2px;
        font-size: 24px;
        cursor: pointer;
        display: none;
    }

    .sidebar-overlay {
        position: fixed;
        top: 0;
        left: 0;
        right: 0;
        bottom: 0;
        background: rgba(0,0,0,0.5);
        z-index: 999;
        display: none;
    }

    @media (max-width: 890px) {

        .sidebar {
            left: -250px;
        }

        .sidebar-toggle {
            display: block;
        }

        .sidebar.active + .sidebar-overlay {
            display: block;
        }
    }
</style>

<!-- Bouton burger -->
<button class="sidebar-toggle" id="sidebarToggle">
    <i class="fas fa-bars"></i>
</button>

<div class="sidebar-overlay" id="sidebarOverlay"></div>

<div class="sidebar" id="sidebar">
    <div class="sidebar-logo-name">
        <div class="sidebar-logo">
            <img src="assets/logo/ConstructionXpert-removebg-preview-white.PNG" alt="Logo">
        </div>
    </div>

    <a href="/accueil.jsp"><i class="fas fa-home"></i> Accueil</a>
    <a href="/project?action=new"><i class="fas fa-plus"></i> Ajouter un Projet</a>
    <a href="/project?action=list"><i class="fas fa-list"></i> Voir les Projets</a>
    <a href="/ressource?action=new"><i class="fas fa-plus"></i> Ajouter une Ressource</a>
    <a href="/ressource?action=list"><i class="fas fa-list"></i> Voir les Ressources</a>
    <a href="/task?action=list"><i class="fas fa-list"></i> Liste des Tâches</a>
    <a href="/logout"><i class="fas fa-sign-out-alt"></i> Déconnexion</a>
</div>

<script>
    document.getElementById('sidebarToggle').addEventListener('click', function() {
        document.getElementById('sidebar').classList.toggle('active');
    });

    document.getElementById('sidebarOverlay').addEventListener('click', function() {
        document.getElementById('sidebar').classList.remove('active');
        this.style.display = 'none';
    });

    document.querySelectorAll('.sidebar a').forEach(link => {
        link.addEventListener('click', function() {
            if (window.innerWidth <= 768) {
                document.getElementById('sidebar').classList.remove('active');
                document.getElementById('sidebarOverlay').style.display = 'none';
            }
        });
    });
</script>