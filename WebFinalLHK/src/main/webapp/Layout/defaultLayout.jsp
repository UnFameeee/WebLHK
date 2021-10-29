<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<html>
<head>
<%--    <link rel="stylesheet" type="text/css" href="style123.css">--%>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.12.1/css/all.min.css">
    <title>Title</title>

    <style>
        /* -------------------- Login - 2Du -------------------- */
        #login-form{
            width: 30vw;
            height: 45vh;
            margin-right: auto;
            margin-left: auto;
            margin-top: 20vh;
            border: 2px solid #CCCCCC;
            border-radius: 4px;

        }

        .login-container{
            padding: 2vh 2vw;
        }

        .login-content{
            margin-bottom: 3vh;
        }

        .login-header{
            border-bottom: 2px solid #CCCCCC;
            margin: -2vh -2vw 3vh -2vw;
            padding: 2vh 2vw;
            background-color: rgb(245, 245, 245) ;
            font-size: 22px;
            font-weight: 400;
        }

        .login-textbox{
            width: 25.5vw;
            height: 5vh;
            border: 2px solid #CCCCCC;
            border-radius: 5px;
            text-indent: 15px;
        }

        .login-checkbox{
            margin-left: 0;
            background: #DEDEDE;
        }

        #login-button{
            width: 25.5vw;
            height: 6vh;
            color: white;
            background-color: rgb(92, 184, 92);
            border: 0;
            border-radius: 5px;
            font-size: 22px;
            font-weight: 600;
        }

        /* -------------------- Register - 2Du -------------------- */
        #register-form{
            width: 30vw;
            height: 55vh;
            margin-left: auto;
            margin-right: auto;
            margin-top: 15vh;
            border: 2px solid #CCCCCC;
            border-radius: 4px;
        }

        .register-container{
            padding: 2vh 2vw;
        }

        .register-content{
            margin-bottom: 3vh;
        }

        .register-header{
            border-bottom: 2px solid #CCCCCC;
            margin: -2vh -2vw 3vh -2vw;
            padding: 2vh 2vw;
            background-color: rgb(245, 245, 245);
            font-size: 22px;
            font-weight: 400;
        }

        .register-textbox{
            width: 25.5vw;
            height: 5vh;
            border: 2px solid #CCCCCC;
            border-radius: 4px;
            text-indent: 15px;
        }

        .register-checkbox{
            margin-left: 0;
        }

        #register-button{
            width: 25.5vw;
            height: 6vh;
            color: white;
            background-color: rgb(92, 184, 92);
            border: 0;
            border-radius: 5px;
            font-size: 22px;
            font-weight: 600;
        }




        /* -------------------- Edit Profile - Wibu -------------------- */

        html {
            scroll-behavior: smooth;
        }

        /* #main::after {
            content: "";
            display: block;
            clear: both;
        } */

        #content {
            height: 900px;
            width: 1535px;
            /* float: right; */
            margin-left: 250px;
        }

        #content .heading {
            border-bottom: 2px solid #CCCCCC;
            margin-left: 35px;
            padding-top: 50px;
        }


        #content .form-content {
            border: 1px solid #CCCCCC;
            margin-top: 30px;
            margin-left: 35px;
        }

        #content .form-content {
            background-color: #FFFFFF;
        }

        #content .form-content .heading-form-profile {
            height: 40px;
            border-bottom: 2px solid #CCCCCC;
            background-color: #F5F5F5;
        }

        #content .form-content .heading-form-profile .text-heading {
            position: relative;
            left: 20px;
            top: 10px;
            font-size: 18px;
            font-weight: 300;
        }

        #content .form-profile {
            width: 80%;
            margin: 4px;
            background-color: #FFFFFF;
            padding-left: 15px;
        }

        #content .form-profile {
            margin: 20px 50px 10px 0;
        }


        #content .form-profile .profile-label {
            margin: 4px;
            font-weight: 550;
        }

        #content .form-profile .profile-input {
            width: 100%;
            margin: 8px 4px 12px;
            padding: 4px 8px;
            height: 35px;
            border: 1px solid #CCCCCC;
            border-radius: 4px;
        }

        #content .form-profile #profile-description.profile-input {
            height: 350px;
        }

        #profile-email {
            display: block;
            margin: 8px 4px 16px;
            width: 100%;
        }

        #profile-description {
            height: 64px;
            word-break: break-all;
            white-space: nowrap;
        }

        .btn {
            margin-left: 5px;
            padding: 5px 5px 5px 5px;
            background: #FFFFFF;
            border: 1px solid #CCCCCC;
            border-radius: 4px;
            height: 35px;
            width: 110px;
        }

        /* -------------------- Add Content - Dibu -------------------- */
        *{
            padding: 0;
            margin: 0;
            box-sizing: border-box;
            font-family: 'Roboto', sans-serif;
            zoom: 100%;
        }

        .add-content{
            padding-top: 50px;
            margin-left: 250px;
            padding-left: 2%;
            /*height: 100%;*/
        }

        .add-content .form-content hr{
            width:98%;
        }

        .add-content .form-content .container {
            width: 98%;
            margin-top: 30px;
        }

        .add-content .form-content .container h2{
            font-size: 16px;
            font-weight: 300;
            position: relative;
            left: 20px;
            top: 10px;
        }

        .add-content .form-content .container .add-content-title{
            margin-top: 30px;
            border: 1px solid #CCCCCC;
            width: 100%;
            height: 40px;
            background-color: #F5F5F5;
        }

        .add-content .form-content .container .input-content{
            border: 1px solid #CCCCCC;
            padding-left: 15px;
            padding-bottom: 10px;
        }

        .add-content .form-content .container .input-content .input-field{
            margin: 20px 50px 10px 5px;
        }

        .add-content .form-content .container .input-content .input-field label{
            font-weight: 550;
        }

        .add-content .form-content .container .input-content .input-field input, textarea{
            width: 95%;
            padding: 5px 5px 5px 5px;
            border: 1px solid #CCCCCC;
            border-radius: 3px;
            margin-top: 5px;
        }

        .add-content .form-content .container .input-content .input-field .title {
            height: 35px;
        }

        .add-content .form-content .container .input-content .input-field .brief {
            height: 150px;
        }

        .form-content .container .input-content .input-field .content {
            height: 250px;
        }

        .form-content .container .input-content .button-field input{
            margin-left: 5px;
            padding: 5px 5px 5px 5px;
            background: #FFFFFF;
            border: 1px solid #CCCCCC;
            border-radius: 4px;
            height: 35px;
            width: 110px;

        }
        .form-content .container .input-content .button-field input:hover{
            cursor: pointer;
            color: whitesmoke;
            background: gray;
        }

        /* -------------------- View Content - Thắng -------------------- */
        body{
            margin: 0;
            padding: 0;
            font-family: "Roboto", sans-serif;
        }

        header{
            background-color: #F8F8F8;
            padding: 20px;
            width: 100%;
            height: 60px;
            border-bottom: 1px solid #CCCCCC;
        }

        .left-area h3{
            color: #B9A48D;
            /*margin: 0;*/
            margin-left: -5px;
            text-transform: uppercase;
            font-size: 22px;
            font-weight: 900;
        }

        .logout_btn{
            padding: 5px;
            /* background: #337AB7; */
            color: #337AB7;
            text-decoration: none;
            float: right;
            margin-top: -30px;
            margin-right: 0;
            border-radius: 2px;
            font-size: 15px;
            transition-property: background;
        }
        .logout_btn:hover{
            color: #49a1ee;
        }

        .sidebar{
            background-color: #F8F8F8;
            margin-top: 0;
            padding-top: 30px;
            position: fixed;
            left: 0;
            height: 100%;
            width: 250px;
            border-right: 1px solid #CCCCCC;
        }

        .sidebar a{
            color: #3B7FB9;
            display: block;
            /* width: 100%; */
            line-height: 40px;
            text-decoration: none;
            padding-left: 15px;
            box-sizing: border-box;
            border: 1px solid #CCCCCC;
            transition: 0.5s;
            transition-property: background;
        }

        .sidebar a:hover{
            background-color: #EEEEEE;
        }


        .sidebar .form-search {
            display: flex;
            align-items: center;
            flex-direction: row;
            margin-top: -5px;
            margin-left: 25px;
            margin-bottom: 25px;
            /* border-bottom: 2px solid rgb(200, 200, 200); */
        }


        .sidebar .form-search .search-input {
            padding: 2px;
            border: 2px solid #CCCCCC;
            height: 35px;
            border-top-left-radius: 4px;
            border-bottom-left-radius: 4px;
            text-indent: 10px;
        }

        .sidebar .form-search .search-btn {
            /* background-color: #ccc; */
            border-top: 2px solid #CCCCCC;
            border-right: 2px solid #CCCCCC;
            border-bottom: 2px solid #CCCCCC;
            border-top-right-radius: 4px;
            border-bottom-right-radius: 4px;
            height: 35px;
            width: 35px;

        }

        .sidebar .form-search .search-btn i{
            position: relative;
            top: 50%;
            left: 50%;
            transform: translate(-30%, -50%);
        }

        .sidebar i{
            padding-right: 10px;
        }

        .view-content{
            padding-top: 50px;
            margin-left: 250px;
            padding-left: 2%;
            /*height: 100%;*/
        }

        .view-content hr{
            width: 98%;
            margin-left: 0;
        }

        .view-content-list-title{
            margin-top: 30px;
            border: 1px solid #DDDDDD;
            width: 98%;
            height: 40px;
            background-color: #F5F5F5;
        }

        .view-content-list-title p{
            position: relative;
            left: 20px;
            top: 10px;
        }

        .view-content-list-container {
            border-left: 1px solid #DDDDDD;
            border-bottom: 1px solid #DDDDDD;
            border-right: 1px solid #DDDDDD;
            width: 98%;
            padding: 20px;
            text-align: left;

        }

        #View-content-list {
            border: 1px solid #DDDDDD;
            border-collapse: collapse;
            width:95%;
            max-height: 650px;
        }

        #View-content-list thead{
            height: 30px;
        }

        #View-content-list thead th{
            padding-left: 10px;
            border: 1px solid #DDDDDD;
        }

        #View-content-list tbody{
            height: 50px;
        }

        #View-content-list tbody td{
            padding-left: 10px;
            border: 1px solid #DDDDDD;
        }

    </style>
</head>
<body>
    <tiles:insertAttribute name="header" />
    <tiles:insertAttribute name="sidebar" />
    <tiles:insertAttribute name="body" />
</body>
</html>
