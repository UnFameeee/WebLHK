<header>
    <div class="left-area">
        <h3>CMS</h3>
    </div>
    <div class="right-area">
        <div class="logout_btn">
            <a>
                <i class="fas fa-user"></i><i class="fas fa-caret-down"></i>
            </a>
            <ul class="subnav">
                <li class="subnav-top"><a href="<%=request.getContextPath()%>/profile"><i class="fas fa-user"></i> User Profile</a></li>
                <li class="subnav-bottom"><a href="<%=request.getContextPath()%>/logoutAccount"><i class="fas fa-sign-out-alt"></i> Log out</a></li>
            </ul>
        </div>
    </div>

    <script>
        const btnLogout = document.querySelector('.logout_btn');
        const subnav = document.querySelector('.subnav');
        btnLogout.onclick = () => {
            btnLogout.classList.toggle('active')
            subnav.classList.toggle('active');
        }
    </script>
</header>