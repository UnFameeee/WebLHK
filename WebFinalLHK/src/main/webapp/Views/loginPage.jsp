<!DOCTYPE html>
<html>
    <head>
        <meta charset ="UTF-8">
		<link rel="stylesheet" type="text/css" href="../style1.css">
        <title>Login Page</title>
    </head>

     <body>
        <form id="login-form" method="post" action="<%=request.getContextPath()%>">
            <div class="login-container">

                <div class="login-header">
                    <label>Please Sign In</label>
                </div>
    
                <div class="login-content">
                    <input class="login-textbox" id="email" name="email" type="text" placeholder="E-mail">
                    <div class="error">
                        <span class="error-message"></span>
                    </div>
                </div>
    
                <div class="login-content">
                    <input class="login-textbox" id="password" name="password" type="password" placeholder="Password">                   
                    <div class="error">
                        <span class="error-message"></span>
                    </div>
                </div>
    
                <div class="login-content">
                    <input class="login-checkbox" type="checkbox" name="remember_me">
                    <label>Remember me</label>
                </div>
    
                <div class="login-content">
                    <input id="login-button" type="submit" value="Login">
                </div>
                
                <a href="${pageContext.request.contextPath}/Views/registerPage.jsp" target="_blank">
                    <span style="color: blue;">Click here to Register</span>
                </a>            
            </div>
        </form>        
    </body>

    <script type="text/javascript" src="${pageContext.request.contextPath}/Views/Effect/Login_Register.js"> </script>
    <script>
        Validator({
            form:'#login-form',
            error: '.error-message',
            rules: [
                Validator.isEmail('#email'),
                Validator.isRequired('#password'),
                Validator.needLength('#email', 5, 50),
                Validator.needLength('#password', 8, 30),
            ],
            onSubmit: function (data) {
                console.log(data);
            }
        })
    </script>

</html>