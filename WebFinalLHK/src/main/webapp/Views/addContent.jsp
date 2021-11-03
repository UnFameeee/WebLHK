<body>
    <div class="add-content">
        <form action="addContent" class="form-content" id="addContent-form" method="post">
            <h1>Add Content</h1>
            <hr>

            <div class="container">
                <div class="add-content-title">
                    <h2>Content Form Elements</h2>
                </div>
                <div class="input-content">
                    <div class="input-field">
                        <label for="">Title<br></label>
                        <input type="text" class="title" name="title" id="input_title" placeholder="Enter the title">
                        <div class="invalid-Message">
                            <span class="form-message"></span>
                        </div>
                    </div>
                    <div class="input-field">
                        <label for="">Brief<br></label>
                        <textarea id ="input_brief" class="brief" name="brief" rows="4" cols="50"> </textarea>
                        <div class="invalid-Message">
                            <span class="form-message"></span>
                        </div>
                    </div>
                    <div class="input-field">
                        <label for="">Content<br></label>
                        <textarea id="input_content" class="content" name="content" rows="4" cols="50"> </textarea>
                        <div class="invalid-Message">
                            <span class="form-message"></span>
                        </div>
                    </div>
                    <div class="button-field">
                        <input type="submit" value="Submit Button">
                        <input type="reset" value="Reset Button">
                    </div>
                </div>
            </div>
        </form>
    </div>

<%--    <script>--%>
<%--        // Mong  muốn của chúng ta--%>
<%--        Validator({--%>
<%--            form:'#addContent-form',--%>
<%--            errorSelector:['.form-message','.warning-Message'],--%>
<%--            rules:[--%>
<%--                Validator.isRequired('#input_title','Vui lòng nhập title của content!'),--%>
<%--                Validator.isRequired('#input_brief','Vui lòng nhập brief của content!'),--%>
<%--                Validator.isRequired('#input_content','Vui lòng nhập content!'),--%>

<%--                Validator.minLength('#input_title',10),--%>
<%--                Validator.minLength('#input_brief',30),--%>
<%--                Validator.minLength('#input_content',50),--%>

<%--                Validator.maxLength('#input_title',200),--%>
<%--                Validator.maxLength('#input_brief',150),--%>
<%--                Validator.maxLength('#input_content',1000),--%>
<%--            ]--%>
<%--            ,--%>
<%--            //   onSubmit:function(data){ console.log(data); }--%>
<%--        });--%>

<%--    </script>--%>
</body>