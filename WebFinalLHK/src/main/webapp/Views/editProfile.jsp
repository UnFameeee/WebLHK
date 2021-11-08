<%@ page import="com.unfame.Model.EditProfile" %>

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
            <form class="form-profile" action="<%=request.getContextPath()%>/editProfile?Id=<%=profile.getId()%>" method="POST">

                <div class="row">
                    <label for="profile-first-name" class="profile-label">First Name</label>
                    <input name="Firstname" id="profile-first-name" type="text" class="profile-input" placeholder="Enter the first name" value="<%= profile.getFirstName() %>">
                </div>
                <div class="row">
                    <label for="profile-last-name" class="profile-label">Last Name</label>
                    <input name="Lastname" id="profile-last-name" type="text" class="profile-input" placeholder="Enter the last name" value="<%= profile.getLastName() %>">
                </div>
                <div class="row">
                    <label for="profile-email" class="profile-label">Email</label>
                    <span id="profile-email"> <%= profile.getEmail() %> </span>
                </div>
                <div class="row">
                    <label for="profile-phone" class="profile-label">Phone</label>
                    <input name="Phone" id="profile-phone" type="text" class="profile-input" placeholder="Enter the phone number" value="<%= profile.getPhoneNumber() %>">
                </div>
                <div class="row">
                    <label for="profile-description" class="profile-label">Description</label>
                    <textarea name="Description" id="profile-description" rows="4" cols="50" class="profile-input"><%= profile.getDescription() %></textarea>
                </div>
                <input type="submit" value="Submit button" class="btn submit-btn">
                <input type="button" value="Reset button" class="btn reset-btn">
            </form>
        </div>
    </div>
    <script>
        const btnReset = document.querySelector('.reset-btn');
        const firstname = document.querySelector('#profile-first-name');
        const firstnameValue = firstname.value;
        const lastname = document.querySelector('#profile-last-name');
        const lastnameValue = lastname.value;
        const phone = document.querySelector('#profile-phone');
        const phoneValue = phone.value;
        const description = document.querySelector('#profile-description');
        const descriptionValue = description.value;

        btnReset.onclick = (e) => {
            firstname.value = firstnameValue;
            lastname.value = lastnameValue;
            phone.value = phoneValue;
            description.value = descriptionValue;
        };
    </script>
</body>