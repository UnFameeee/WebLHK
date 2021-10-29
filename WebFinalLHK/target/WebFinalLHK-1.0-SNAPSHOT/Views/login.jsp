<!DOCTYPE html>
<html>
    <head>
        <meta charset ="UTF-8">
		<link rel="stylesheet" type="text/css" href="../style1.css">
        <title>Login Page</title>
    </head>

    <body>
        <form id="login-form" method="post">
            <div class="login-container">

                <div class="login-header">
                    <label>Please Sign In</label>
                </div>
    
                <div class="login-content">
                    <input class="login-textbox" id="email" name="email" type="text" placeholder="E-mail">
                </div>
    
                <div class="login-content">
                    <input class="login-textbox" id="password" name="password" type="password" placeholder="Password">
                </div>
    
                <div class="login-content">
                    <input class="login-checkbox" type="checkbox" name="remember_me">
                    <label>Remember me</label>
                </div>
    
                <div class="login-content">
                    <input id="login-button" type="submit" value="Login">
                </div>
                
                <a>
                    <span style="color: blue;">Click here to Register</span>
                </a>            
            </div>
        </form>        
    </body>

</html>