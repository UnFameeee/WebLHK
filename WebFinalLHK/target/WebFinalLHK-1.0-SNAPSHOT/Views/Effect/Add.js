
// var row =1;
// function displayVisibleTable()
// {
//     var table_data=document.getElementById("table_data");
//     table_data.classList.toggle("displayVisible");
// }

// function displayDetails()
// {
//     //lấy giá trị input
//     var No = row;
//     var fname = document.getElementById("fname").value;
//     var lname = document.getElementById("lname").value;
//     var email = document.getElementById("email").value;
//     var username = document.getElementById("username").value;


//     var table_data=document.getElementById("table_data");

//     var newRow=table_data.insertRow(row);

//     var cell1 = newRow.insertCell(0);
//     var cell2 = newRow.insertCell(1);
//     var cell3 = newRow.insertCell(2);
//     var cell4 = newRow.insertCell(3);
//     var cell5 = newRow.insertCell(4);

//     cell1.innerHTML=No;
//     cell2.innerHTML=fname;
//     cell3.innerHTML=lname;
//     cell4.innerHTML=email;
//     cell5.innerHTML=username;

//     row++;

//     //bc row start with 1 and we only need to toggle it visible once
//     if(row===2)displayVisibleTable();

//     //reset input
//     document.getElementById("fname").value='';
//     document.getElementById("lname").value='';
//     document.getElementById("email").value='';
//     document.getElementById("username").value='';
//     document.getElementById("password").value='';
//     document.getElementById("confirmpass").value='';

// }
//Đối tượng validator
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
            inputElement.classList.remove('valid-input');
            //  headMessage.classList.add('active-Visible');

        }
        else
        {
            errorElement.innerText='';
            inputElement.classList.remove('invalid-input');
            inputElement.classList.add('valid-input');
            // headMessage.classList.remove('active-Visible');
        }
        return !errorMessage;
    }

    var headMessage=document.querySelector(options.errorSelector[1])//('.message')

    //lấy elements của form cần validate
    var formElement= document.querySelector(options.form);
    if(formElement)
    {
        //chặn chế độ submit mặc định
        formElement.onsubmit =function(e){
            //e.preventDefault();

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

            if(isFormValid){
                if(typeof options.onSubmit === 'function')
                {
                    var enableInputs=formElement.querySelectorAll('[name]:not([disable])')
                    var formValues = Array.from(enableInputs).reduce(function(values,input){
                        return (values[input.name]=input.value) && values;
                    },{});
                    // console.log(formValues)
                    //
                    // options.onSubmit(formValues);
                }

                // options.rules.forEach(function(rule){
                // var inputElement=formElement.querySelector(rule.selector);
                // inputElement.classList.remove('valid-input');
                // });
                // displayDetails();

            }
            else{
                // headMessage.classList.add('active-Visible');
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
            return value.length >=min ? undefined : message || `Vui lòng nhập tối thiểu ${min} kí tự`;
        }
    };
}
Validator.maxLength=function(selector,max, message)
{
    return {
        selector: selector,
        test: function (value) {
            return value.length <= max ? undefined : message || `Vui lòng nhập chỉ nhập tối đa ${max} kí tự`;
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