<!DOCTYPE html>
<html>
    <head>
        <meta charset ="UTF-8">
		<link rel="stylesheet" type="text/css" href="../style1.css">
        <title>Register Page</title>
    </head>

    <body>
        <form id="register-form" method="post">
            <div class="register-container">

                <div class="register-header">
                    <label>Register</label>
                </div>

                <div class="register-content">
                    <input class="register-textbox" id="" name="username" type="username" placeholder="Username">
                </div>
    
                <div class="register-content">
                    <input class="register-textbox" id="email" name="email" type="text" placeholder="E-mail">
                </div>
    
                <div class="register-content">
                    <input class="register-textbox" id="password" name="password" type="password" placeholder="Password">
                </div>

                <div class="register-content">
                    <input class="register-textbox" id="repassword" name="repassword" type="password" placeholder="Re Password">
                </div>
    
                <div class="register-content">
                    <input id="register-button" type="submit" value="Register">
                </div>
                
                <a>
                    <span style="color: blue;">Click here to Login</span>
                </a>            
            </div>
        </form>        
    </body>

</html>