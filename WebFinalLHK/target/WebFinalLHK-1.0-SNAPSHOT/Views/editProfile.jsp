<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.12.1/css/all.min.css">
    <link rel="stylesheet" href="../style1.css">
    <title>Edit Profile</title>
</head>

<body>
    <!-- Header area start -->
    <header>
        <div class="left-area">
            <h3>CMS</h3>
        </div>
        <div class="right-area">
            <a href="#" class="logout_btn"><i class="fas fa-user"></i> <i class="fas fa-caret-down"></i></a>
        </div>
    </header>
    <!-- Header area end -->
    <!-- Sidebar start -->
    <div class="sidebar">
        <div class="sidebar-container">
            <div class="form-search">
                <input type="text" placeholder="Search..." class="search-input">
                <div class="search-btn">
                    <i class="fas fa-search"></i>
                </div>
            </div>
            <div>
                <a href="#"><i class="far fa-calendar-alt"></i><span> View contents</span></a>
                <a href="#"><i class="far fa-edit"></i><span>Form content</span></a>
            </div>
        </div>
    </div>
    <!-- Sidebar end -->

    <div id="content">
        <div class="heading">
            <h1 class="text-heading">Edit Profile</h1>
        </div>
        <div class="form-content">
            <div class="heading-form-profile">
                <h5 class="text-heading">Profile Form Elements</h5>
            </div>
            <form action="" class="form-profile">
                <div class="row">
                    <label for="profile-first-name" class="profile-label">First Name</label>
                    <input id="profile-first-name" type="text" class="profile-input" placeholder="Enter the first name">
                </div>
                <div class="row">
                    <label for="profile-last-name" class="profile-label">Last Name</label>
                    <input id="profile-last-name" type="text" class="profile-input" placeholder="Enter the last name">
                </div>
                <div class="row">
                    <label for="" class="profile-label">Email</label>
                    <span id="profile-email"></span>
                </div>
                <div class="row">
                    <label for="profile-phone" class="profile-label">Phone</label>
                    <input id="profile-phone" type="text" class="profile-input" placeholder="Enter the phone number">
                </div>
                <div class="row">
                    <label for="profile-description" class="profile-label">Description</label>
                    <textarea id="profile-description" rows="4" cols="50" class="profile-input"></textarea>
                </div>
                <input type="submit" value="Submit button" class="btn submit-btn">
                <input type="submit" value="Reset button" class="btn reset-btn">
            </form>
        </div>
    </div>

</body>

</html>