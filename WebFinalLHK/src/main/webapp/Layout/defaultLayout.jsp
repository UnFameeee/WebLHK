<%@ page import="com.unfame.Model.ViewContent" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<%--    <link rel="stylesheet" type="text/css" href="../Views/viewContent.jsp">--%>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.12.1/css/all.min.css">
    <meta charset="UTF-8">
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
        /* validation  */
        .invalid-input{
            border: 1px solid red !important;
        }
        .valid-input{
            border: 2px solid rgb(70, 79, 206) !important;
        }
        .active-Visible{
            visibility:visible !important;
        }
        .invalid-Message{
            color: red;
            /*position:absolute;*/
            text-align: left;
            margin-left: 3px;
            margin-top: 1px;
            margin-bottom: 10px;
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

            visibility: hidden;
            opacity: 0;
        }

        .view-content.show{
            visibility: visible;
            opacity: 1;
        }

        #loading{
            /*padding-top: 50px;*/
            margin-top: -250px;
            margin-left: 250px;
            padding-left: 2%;

            visibility: visible;
            opacity: 1;
        }

        #loading.hide{
            visibility: hidden;
            opacity: 0;
        }

        #loading p{
            font-size: 25px;
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
            width: 100%;
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
<%--    <tiles:insertAttribute name="JS" />--%>
<%--<script>--%>
<%--    //Đối tượng validator--%>
<%--    function Validator(options)--%>
<%--    {--%>
<%--        // lưu lại các rules của selector--%>
<%--        var selectorRules={};--%>

<%--        //hàm thực hiện validate--%>
<%--        function validate(inputElement, rule){--%>
<%--            var errorElement=inputElement.parentElement.querySelector(options.errorSelector[0])//('.form-message');--%>


<%--            //  lấy ra các rules của selector--%>
<%--            var rules = selectorRules[rule.selector];--%>

<%--            // lặp qua từng rule và kiểm tra--%>
<%--            // Nếu có lỗi thì dừng việc kiểm tra--%>
<%--            for(var i=0; i<rules.length; i++){--%>

<%--                errorMessage= rules[i](inputElement.value);--%>

<%--                if(errorMessage) break;--%>
<%--            }--%>


<%--            if(errorMessage)--%>
<%--            {--%>
<%--                errorElement.innerText = errorMessage;--%>
<%--                inputElement.parentElement.classList.add('invalid-message');--%>
<%--                inputElement.classList.add('invalid-input')--%>
<%--                inputElement.classList.remove('valid-input');--%>
<%--                //  headMessage.classList.add('active-Visible');--%>

<%--            }--%>
<%--            else--%>
<%--            {--%>
<%--                errorElement.innerText='';--%>
<%--                inputElement.classList.remove('invalid-input');--%>
<%--                inputElement.classList.add('valid-input');--%>
<%--                // headMessage.classList.remove('active-Visible');--%>
<%--            }--%>
<%--            return !errorMessage;--%>
<%--        }--%>

<%--        var headMessage=document.querySelector(options.errorSelector[1])//('.message')--%>

<%--        //lấy elements của form cần validate--%>
<%--        var formElement= document.querySelector(options.form);--%>
<%--        if(formElement)--%>
<%--        {--%>
<%--            //chặn chế độ submit mặc định--%>
<%--            formElement.onsubmit =function(e){--%>
<%--                //e.preventDefault();--%>

<%--                var isFormValid = true;--%>

<%--                //Thực hiện lặp qua từng rule và validate thẳng luôn khi người dùng bấm submit--%>
<%--                options.rules.forEach(function(rule){--%>
<%--                    var inputElement=formElement.querySelector(rule.selector);--%>
<%--                    var isValid=validate(inputElement,rule);--%>
<%--                    if(!isValid)--%>
<%--                    {--%>
<%--                        // chỉ cần một input invalid thì cả form sẽ invalid--%>
<%--                        isFormValid=false;--%>
<%--                    }--%>
<%--                });--%>

<%--                if(isFormValid){--%>
<%--                    if(typeof options.onSubmit === 'function')--%>
<%--                    {--%>
<%--                        var enableInputs=formElement.querySelectorAll('[name]:not([disable])')--%>
<%--                        var formValues = Array.from(enableInputs).reduce(function(values,input){--%>
<%--                            return (values[input.name]=input.value) && values;--%>
<%--                        },{});--%>
<%--                        // console.log(formValues)--%>
<%--                        //--%>
<%--                        // options.onSubmit(formValues);--%>
<%--                    }--%>

<%--                    // options.rules.forEach(function(rule){--%>
<%--                    // var inputElement=formElement.querySelector(rule.selector);--%>
<%--                    // inputElement.classList.remove('valid-input');--%>
<%--                    // });--%>
<%--                    // displayDetails();--%>

<%--                }--%>
<%--                else{--%>
<%--                    // headMessage.classList.add('active-Visible');--%>
<%--                }--%>

<%--            }--%>

<%--            // Xử lý lập qua mỗi rule và xử lý (lắng nghe sự kiện blur, input, ...)--%>
<%--            options.rules.forEach(function(rule)--%>
<%--            {--%>
<%--                //  lưu lại các rules cho mỗi input--%>

<%--                //lần lập thứ 2 để ko bị ghi đè thì ta push giá trị vào để nó lưu lại--%>
<%--                if(Array.isArray(selectorRules[rule.selector])){--%>
<%--                    selectorRules[rule.selector].push(rule.test);--%>
<%--                }--%>
<%--                else{ // lần lập đầu tiên thì sẽ gán bằng giá trị của 1 mảng có 1 phần tử--%>
<%--                    selectorRules[rule.selector]=[rule.test]--%>
<%--                }--%>


<%--                // tìm trong formElement để tránh nhầm sang các form khác--%>
<%--                var inputElement=formElement.querySelector(rule.selector);--%>
<%--                var errorElement=inputElement.parentElement.querySelector(options.errorSelector[0]);--%>

<%--                // nếu tồn tại inputElements--%>
<%--                if(inputElement)--%>
<%--                {--%>
<%--                    //onblur là khi dùng rê chuột ra khỏi input--%>
<%--                    //xử lý trường hợp blur ra ngoài--%>
<%--                    inputElement.onblur = function ()--%>
<%--                    {--%>
<%--                        validate(inputElement,rule);--%>
<%--                    }--%>

<%--                    //xử lý trường hợp mỗi khi ngườI dùng băt đầu nhập input--%>
<%--                    inputElement.oninput=function(){--%>

<%--                        errorElement.innerText='';--%>
<%--                        inputElement.classList.remove('invalid-input');--%>
<%--                        // headMessage.classList.remove('active-Visible');--%>
<%--                    }--%>
<%--                }--%>
<%--            })--%>
<%--        }--%>


<%--    }--%>
<%--    //định nghĩa các rules--%>
<%--    //Nguyên tắc:--%>
<%--    /*--%>
<%--        1. khi có lỗi thì => message lỗi--%>
<%--        2. khi không có lỗi => ko trả ra gì cả (undefined)--%>
<%--    */--%>
<%--    Validator.isRequired=function(selector, message)--%>
<%--    {--%>
<%--        return {--%>
<%--            selector: selector,--%>
<%--            test: function (value){--%>
<%--                return value.trim() ? undefined:  message ||'Vui lòng nhập trường này!';--%>
<%--            }--%>
<%--        };--%>
<%--    }--%>
<%--    Validator.isEmail=function(selector, message)--%>
<%--    {--%>
<%--        return {--%>
<%--            selector: selector,--%>
<%--            test: function (value) {--%>
<%--                var regex=/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;--%>
<%--                return regex.test(value) ? undefined :  message || 'Trường này phải là email!';--%>
<%--            }--%>
<%--        };--%>
<%--    }--%>
<%--    Validator.minLength=function(selector,min, message)--%>
<%--    {--%>
<%--        return {--%>
<%--            selector: selector,--%>
<%--            test: function (value) {--%>
<%--                return value.length >=min ? undefined : message || `Vui lòng nhập tối thiểu ${min} kí tự`;--%>
<%--            }--%>
<%--        };--%>
<%--    }--%>
<%--    Validator.maxLength=function(selector,max, message)--%>
<%--    {--%>
<%--        return {--%>
<%--            selector: selector,--%>
<%--            test: function (value) {--%>
<%--                return value.length <= max ? undefined : message || `Vui lòng nhập chỉ nhập tối đa ${max} kí tự`;--%>
<%--            }--%>
<%--        };--%>
<%--    }--%>
<%--    Validator.isConfirmed = function(selector, getConfirmValue, message){--%>
<%--        return{--%>
<%--            selector:selector,--%>
<%--            test: function(value){--%>
<%--                return value==getConfirmValue() ? undefined : message || 'Giá trị nhập vào không chính xác';--%>
<%--            }--%>
<%--        }--%>
<%--    }--%>


<%--    // Mong  muốn của chúng ta &ndash;%&gt;--%>
<%--            Validator({--%>
<%--                form:'#addContent-form',--%>
<%--                errorSelector:['.form-message','.warning-Message'],--%>
<%--                rules:[--%>
<%--                    Validator.isRequired('#input_title','Vui lòng nhập title của content!'),--%>
<%--                    Validator.isRequired('#input_brief','Vui lòng nhập brief của content!'),--%>
<%--                    Validator.isRequired('#input_content','Vui lòng nhập content!'),--%>

<%--                    Validator.minLength('#input_title',10),--%>
<%--                    Validator.minLength('#input_brief',30),--%>
<%--                    Validator.minLength('#input_content',50),--%>

<%--                    Validator.maxLength('#input_title',200),--%>
<%--                    Validator.maxLength('#input_brief',150),--%>
<%--                    Validator.maxLength('#input_content',1000),--%>
<%--                ]--%>
<%--                ,--%>
<%--                //   onSubmit:function(data){ console.log(data); }--%>
<%--            });--%>
<%--</script>--%>
</body>
</html>
