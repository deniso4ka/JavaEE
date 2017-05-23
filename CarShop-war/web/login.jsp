<%-- 
    Document   : newjsf
    Created on : 17-Nov-2016, 21:31:22
    Author     : den
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">



    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
            <title>JSP Login Page</title>
            <style>
                
form {
    border: 3px solid #f1f1f1;
}


input[type=text], input[type=password] {
    width: 100%;
    padding: 12px 20px;
    margin: 8px 0;
    display: inline-block;
    border: 1px solid #ccc;
    box-sizing: border-box;
}


submit {
    background-color: #4CAF50;
    color: white;
    padding: 14px 20px;
    margin: 8px 0;
    border: none;
    cursor: pointer;
    width: 100%;
}


body{
    background-color:lightgray; 
}


            </style>
        </head>
        <body>
            <form action="j_security_check" method="POST">
                <label>Username:</label><input type="text" name="j_username" ><br> <br>
             <label>Password:</label><input type="password" name="j_password" >
            <input type="submit" value="Login">
        </form>
        </body>
    </html>

