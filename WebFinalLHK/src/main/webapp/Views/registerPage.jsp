<body>
    <form id="register-form" method="post" action="<%=request.getContextPath()%>/registerAccount">
        <div class="register-container">

            <div class="register-header">
                <label>Register</label>
            </div>

            <div class="register-content">
                <input class="register-textbox invalid" id="username" name="username" type="text" placeholder="Username">
                 <div class="error">
                    <span class="error-message"></span>
                </div>
            </div>

            <div class="register-content">
                <input class="register-textbox" id="email" name="email" type="text" placeholder="E-mail">
                 <div class="error">
                    <span class="error-message"></span>
                </div>
            </div>

            <div class="register-content">
                <input class="register-textbox" id="password" name="password" type="password" placeholder="Password">
                 <div class="error">
                    <span class="error-message"></span>
                </div>
            </div>

            <div class="register-content">
                <input class="register-textbox" id="repassword" name="repassword" type="password" placeholder="Re Password">
                 <div class="error">
                    <span class="error-message"></span>
                </div>
            </div>

            <div class="register-content">
                <input id="register-button" type="submit" value="Register">
            </div>

            <a href="<%=request.getContextPath()%>/login">
                <span style="color: blue;">Click here to Login</span>
            </a>
        </div>
    </form>
</body>

<script type="text/javascript" src="${pageContext.request.contextPath}/Views/Effect/Login_Register.js"> </script>
<script>
    Validator({
        form:'#register-form',
        error: '.error-message',
        rules: [
            Validator.isRequired('#username'),
            Validator.needLength('#username', 3, 30),
            Validator.isEmail('#email'),
            Validator.needLength('#email', 5, 50),
            Validator.isRequired('#password'),
            Validator.needLength('#password', 8, 30),
            Validator.isRequired('#repassword'),
            Validator.isConfirmed('#repassword', function(){
               return document.querySelector('#password').value;
            })
        ]
    })
</script>
