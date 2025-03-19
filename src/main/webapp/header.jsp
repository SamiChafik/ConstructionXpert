<%@ page import="com.example.constructionxpert.model.User" %>
<%@ page session="true" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    User user = (User) session.getAttribute("user");
    String name = user != null ? user.getName() : "";
%>
<link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" rel="stylesheet">
<style>
    .sidebar {
        height: 100vh;
        width: 250px;
        position: fixed;
        top: 0;
        left: 0;
        background-color: #343a40;
        padding-top: 20px;
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

    /*.sidebar-logo-name {*/
    /*    display: flex;*/
    /*    flex-direction: column;*/
    /*}*/
</style>
<div class="sidebar">
    <div class="sidebar-logo-name">
        <div class="sidebar-logo">
            <img src="assets/logo/ConstructionXpert-removebg-preview-white.PNG" alt="Logo">
        </div>

        <a class="navbar-brand" href="#">
<%--            Bienvenue, <%= ((User) session.getAttribute("user")).getName() %>--%>
        </a>
    </div>

    <a href="/accueil.jsp"><i class="fas fa-home"></i> Home</a>
    <a href="/project?action=new"><i class="fas fa-plus"></i> Add Project</a>
    <a href="/project?action=list"><i class="fas fa-list"></i> View Projects</a>
    <a href="/ressource?action=new"><i class="fas fa-plus"></i> Add Resource</a>
    <a href="#"><i class="fas fa-users"></i> Manage Resources</a>
    <a href="#"><i class="fas fa-cog"></i> Settings</a>
    <a href="/logout"><i class="fas fa-sign-out-alt"></i> Logout</a>
</div>
