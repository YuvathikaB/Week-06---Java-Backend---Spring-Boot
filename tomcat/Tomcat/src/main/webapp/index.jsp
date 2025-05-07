<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Welcome to Tomcat</title>
    <style>
        body { font-family: 'Verdana', sans-serif; background-color: #f7f7f7; display: flex; flex-direction: column; justify-content: center; align-items: center; height: 100vh; margin: 0; }
        .container { background-color: #ffffff; padding: 40px; border-radius: 10px; box-shadow: 0 4px 8px rgba(0,0,0,0.1); text-align: center; }
        h1 { color: #3498db; margin-bottom: 20px; }
        p { color: #555; font-size: 1.1em; }
        .links a { display: inline-block; margin: 10px; padding: 10px 20px; background-color: #2ecc71; color: white; text-decoration: none; border-radius: 5px; transition: background-color 0.3s ease; }
        .links a:hover { background-color: #27ae60; }
    </style>
</head>
<body>
    <div class="container">
        <h1>Welcome to your Tomcat Web Application!</h1>
        <p>This is the default index.jsp page.</p>
        <p>Navigate to your servlets and login pages:</p>
        <div class="links">
            <a href="FirstServlet">Go to FirstServlet</a>
            <a href="login.html">Go to Login Page</a>
        </div>
    </div>
</body>
</html>