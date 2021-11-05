<%@ page import="com.unfame.Model.EditProfile" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.12.1/css/all.min.css">
    <link rel="stylesheet" href="../Style.css">
    <title>Edit Profile</title>
</head>
    <%
        EditProfile profile = (EditProfile)request.getAttribute("profile");
    %>
<body>
    <div id="content">
        <div class="heading">
            <h1 class="text-heading">Edit Profile</h1>
        </div>
        <div class="form-content">
            <div class="heading-form-profile">
                <h5 class="text-heading">Profile Form Elements</h5>
            </div>
            <form action="<%=request.getContextPath()%>/updateProfile" class="form-profile">
                <div class="row">
                    <label for="profile-first-name" class="profile-label">First Name</label>
                    <input id="profile-first-name" type="text" class="profile-input" placeholder="Enter the first name" value="${profile.firstname}">
                </div>
                <div class="row">
                    <label for="profile-last-name" class="profile-label">Last Name</label>
                    <input id="profile-last-name" type="text" class="profile-input" placeholder="Enter the last name" value="${profile.lastname}">
                </div>
                <div class="row">
                    <label for="profile-email" class="profile-label">Email</label>
                    <span id="profile-email">value="${profile.email}</span>
                </div>
                <div class="row">
                    <label for="profile-phone" class="profile-label">Phone</label>
                    <input id="profile-phone" type="text" class="profile-input" placeholder="Enter the phone number" value="${profile.phone}">
                </div>
                <div class="row">
                    <label for="profile-description" class="profile-label">Description</label>
                    <textarea id="profile-description" rows="4" cols="50" class="profile-input" value="${profile.description}"></textarea>
                </div>
                <input type="submit" value="Submit button" class="btn submit-btn">
                <input type="button" value="Reset button" class="btn reset-btn">
            </form>
        </div>
    </div>

</body>

</html>