

<body>
<div class="sidebar">
    <div class="sidebar-container">
        <div class="form-search">
            <input type="text" placeholder="Search..." class="search-input">
            <div class="search-btn">
                <i class="fas fa-search"></i>
            </div>
        </div>
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
</body>