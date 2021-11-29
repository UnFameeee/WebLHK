const briefValue =document.querySelector('.brief').innerHTML;
const titleValue =document.querySelector('.title').value;
const contentValue =document.querySelector('.content').innerHTML;

const brief=document.querySelector('.brief');
const title =document.querySelector('.title');
const content =document.querySelector('.content');
let submitMessage = document.querySelector(".submitMessage");
const resetBtn = document.querySelector('#reset-btn');



    title.addEventListener("click",function (){
        submitMessage = document.querySelector(".submitMessage");
        if(submitMessage !== null){
            document.querySelector(".submitMessage").remove();
        }
    })


    brief.addEventListener("click",function (){
        submitMessage = document.querySelector(".submitMessage");
        if(submitMessage !== null){
            document.querySelector(".submitMessage").remove();
        }
    })

    content.addEventListener("click",function (){
        submitMessage = document.querySelector(".submitMessage");
        if(submitMessage !== null){
            document.querySelector(".submitMessage").remove();
        }
    })





function ResetForm(options) {
    const formElement = document.querySelector(options.form);
    const inputElements = formElement.querySelectorAll('input[type=text]');
    inputElements.forEach(inputElement => {
        const errorElement = inputElement.parentElement.querySelector(options.errorSelector[0]);
        errorElement.innerHTML = '';
        inputElement.classList.remove('invalid-input');
        inputElement.parentElement.classList.remove('invalid-message');
    })

}


function Validator(options)
    {
        // lưu lại các rules của selector
        var selectorRules={};

        //hàm thực hiện validate
        function validate(inputElement, rule){
        var errorElement=inputElement.parentElement.querySelector(options.errorSelector[0])//('.form-message');


        //  lấy ra các rules của selector
        var rules = selectorRules[rule.selector];

        // lặp qua từng rule và kiểm tra
        // Nếu có lỗi thì dừng việc kiểm tra
        for(var i=0; i<rules.length; i++){

        errorMessage= rules[i](inputElement.value);

        if(errorMessage) break;
    }


        if(errorMessage)
    {
        errorElement.innerText = errorMessage;
        inputElement.parentElement.classList.add('invalid-message');
        inputElement.classList.add('invalid-input')
        //inputElement.classList.remove('valid-input');
        //  headMessage.classList.add('active-Visible');

    }
        else
    {
        errorElement.innerText='';
        inputElement.classList.remove('invalid-input');
        //inputElement.classList.add('valid-input');
        inputElement.parentElement.classList.remove('invalid-message');
        // headMessage.classList.remove('active-Visible');
    }
        return !errorMessage;
    }

        //var headMessage=document.querySelector(options.errorSelector[1])//('.message')

        //lấy elements của form cần validate
        var formElement= document.querySelector(options.form);
        if(formElement)
    {
        //chặn chế độ submit mặc định
        formElement.onsubmit =function(e){
            console.log("hello")


        var isFormValid = true;

        //Thực hiện lặp qua từng rule và validate thẳng luôn khi người dùng bấm submit
        options.rules.forEach(function(rule){
            var inputElement=formElement.querySelector(rule.selector);
            var isValid=validate(inputElement,rule);
            if(!isValid)
            {
                // chỉ cần một input invalid thì cả form sẽ invalid
                isFormValid=false;

            }
        });

        if(!isFormValid){
            e.preventDefault();

        }
    }

        // Xử lý lập qua mỗi rule và xử lý (lắng nghe sự kiện blur, input, ...)
        options.rules.forEach(function(rule)
    {
        //  lưu lại các rules cho mỗi input

        //lần lập thứ 2 để ko bị ghi đè thì ta push giá trị vào để nó lưu lại
        if(Array.isArray(selectorRules[rule.selector])){
        selectorRules[rule.selector].push(rule.test);
    }
        else{ // lần lập đầu tiên thì sẽ gán bằng giá trị của 1 mảng có 1 phần tử
        selectorRules[rule.selector]=[rule.test]
    }


        // tìm trong formElement để tránh nhầm sang các form khác
        var inputElement=formElement.querySelector(rule.selector);
        var errorElement=inputElement.parentElement.querySelector(options.errorSelector[0]);

        // nếu tồn tại inputElements
        if(inputElement)
    {
        //onblur là khi dùng rê chuột ra khỏi input
        //xử lý trường hợp blur ra ngoài
        inputElement.onblur = function ()
    {
        validate(inputElement,rule);
    }

        //xử lý trường hợp mỗi khi ngườI dùng băt đầu nhập input
        inputElement.oninput=function(){

        errorElement.innerText='';
        inputElement.classList.remove('invalid-input');
        inputElement.parentElement.classList.remove('invalid-message');
        // headMessage.classList.remove('active-Visible');
    }
    }
    })
    }


    }
    //định nghĩa các rules
    //Nguyên tắc:
    /*
    1. khi có lỗi thì => message lỗi
    2. khi không có lỗi => ko trả ra gì cả (undefined)
    */
    Validator.isRequired=function(selector, message)
    {
        return {
        selector: selector,
        test: function (value){
        return value.trim() ? undefined:  message ||'Vui lòng nhập trường này!';
    }
    };
    }
    Validator.isEmail=function(selector, message)
    {
        return {
        selector: selector,
        test: function (value) {
        var regex=/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
        return regex.test(value) ? undefined :  message || 'Trường này phải là email!';
    }
    };
    }
    Validator.minLength=function(selector,min, message)
    {
        return {
        selector: selector,
        test: function (value) {
        return value.length >=min ? undefined : message || "Please enter minimum of " + min +" characters";
    }
    };
    }
    Validator.maxLength=function(selector,max, message)
    {
        return {
        selector: selector,
        test: function (value) {
        return value.length <= max ? undefined : message || "Please enter maximum of "+ max+" characters";
    }
    };
    }
    Validator.isConfirmed = function(selector, getConfirmValue, message){
    return{
    selector:selector,
    test: function(value){
    return value==getConfirmValue() ? undefined : message || 'Giá trị nhập vào không chính xác';
}
}
}

    Validator({
    form:['#addContent-form','#editContent-form'],
    errorSelector:['.form-message','.warning-Message'],
    rules:[
    Validator.isRequired('#input_title','Please enter the title of the content!'),
    Validator.isRequired('#input_brief','Please enter the brief of the content!'),
    Validator.isRequired('#input_content','Please enter the content!'),

    Validator.minLength('#input_title',10),
    Validator.minLength('#input_brief',30),
    Validator.minLength('#input_content',50),

    Validator.maxLength('#input_title',200),
    Validator.maxLength('#input_brief',150),
    Validator.maxLength('#input_content',1000),
    ]
    ,
});

if(resetBtn !== null){
    resetBtn.addEventListener("click",(e)=>{
        submitMessage = document.querySelector(".submitMessage");
        if(submitMessage !== null){
            document.querySelector(".submitMessage").remove();
        }
        e.preventDefault();
        document.querySelector('.brief').value=briefValue;
        document.querySelector('.title').value=titleValue;
        document.querySelector('.content').value=contentValue;
        ResetForm({
            form:['#addContent-form','#editContent-form'],
            errorSelector:['.form-message','.warning-Message'],
        })
    })

}