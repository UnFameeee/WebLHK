<%@ page import="com.unfame.global.IdGlobal" %>
<body>
<div class="sidebar">
    <div class="sidebar-container">
        <form action="<%=request.getContextPath()%>/search" method="post">
            <div class="form-search">
                <input type="text" placeholder="Search..." class="search-input" name="search" value="<%=IdGlobal.searchValue%>">
                <div class="btn">
                    <input type="submit" value="" class="search-btn">
                    <i class="icon-search fas fa-search"></i>
                </div>

            </div>
        </form>

        <div>
            <form action="" method="post">
                <a href="<%=request.getContextPath()%>/view" class="sidebar-top"><i class="far fa-calendar-alt"></i><span> View contents</span></a>
            </form>
            <form action="" method="post">
                <a href="<%=request.getContextPath()%>/showAdd" class="sidebar-bottom"><i class="far fa-edit"></i><span>Form content</span></a>
            </form>
        </div>

    </div>
</div>
<script type="text/javascript">
    const searchBtn = document.querySelector('.search-btn');
    const searchIconBtn = document.querySelector('.icon-search');
    searchIconBtn.onclick = function (e) {
        searchBtn.click();
    }
</script>
</body>