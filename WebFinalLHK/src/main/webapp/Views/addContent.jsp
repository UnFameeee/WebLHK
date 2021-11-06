
<body>
    <div class="add-content">
        <form  class="form-content" id="addContent-form"  action="<%=request.getContextPath()%>/add" method="post" >
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
    <script type="text/javascript" src="${pageContext.request.contextPath}/Views/Effect/AddContent.js"></script>
</body>