//Đối tượng
function Validator(options) {

    //mảng lưu lại các rules để không bị ghi đè
    var selectorRules = {};

    function validate(inputElement, rule) {
        var errorElement = inputElement.parentElement.querySelector(options.error);
        var errorMessage;

        //lấy ra từng rule của selector
        var rules = selectorRules[rule.selector];

        //lặp qua từng rule và kiểm tra có lỗi thì dừng việc kiểm tra
        for(var i = 0; i < rules.length; i++){
            errorMessage = rules[i](inputElement.value);
            if(errorMessage) break;
        }

        //Nếu có tbáo lỗi thì in ra thông báo lỗi và thêm class invalid vào để làm đỏ khung
        if (errorMessage) {     //true là có lỗi
            errorElement.innerText = errorMessage;
            inputElement.parentElement.classList.add('invalid');
            inputElement.parentElement.classList.remove('valid')
        }
        //Nếu không thì xóa tbáo lỗi và xóa đi class invalid và thêm class valid làm xanh khung
        else {                  //false là không có lỗi
            errorElement.innerText = '';
            inputElement.parentElement.classList.remove('invalid')
            inputElement.parentElement.classList.add('valid')
        }

        return !errorMessage;
    }

    var formElement = document.querySelector(options.form);

    if (formElement) {

        formElement.onsubmit = function (e) {
            e.preventDefault();

            var isFormValid = true;

            //Lặp qua từng rule và validate
            options.rules.forEach(function (rule) {
                var inputElement = formElement.querySelector(rule.selector);
                var isValid = validate(inputElement, rule);
                if (!isValid) { isFormValid = false; }
            });           
        }

        options.rules.forEach(function (rule) {
            //nếu là mảng (có nhiều hơn 1 rule thì sẽ vào đây)
            if(Array.isArray(selectorRules[rule.selector])){ 
                selectorRules[rule.selector].push(rule.test);
            }
            //trường hợp rỗng (trường hợp đầu tiên khi mà mảng selectorRules chưa có rule nào)
            else{ 
                selectorRules[rule.selector] = [rule.test];
            }

            var inputElement = formElement.querySelector(rule.selector);    //querySelector sẽ tìm kiếm trong document thằng nào phù hợp với tham số truyền vào rồi trả ra nó

            if (inputElement) {
                //Xử lý trường hợp blur khỏi input
                inputElement.onblur = function () {
                    validate(inputElement, rule);
                }

                //Xử lý trường hợp mỗi khi người dùng đang nhập vào input - thì phải mất dáu đỏ khung đi
                inputElement.oninput = function () {
                    //Chưa tối ưu được code
                    var errorElement = inputElement.parentElement.querySelector(options.error);
                    errorElement.innerText = '';
                    inputElement.parentElement.classList.remove('invalid')
                    inputElement.parentElement.classList.remove('valid')
                }
            }
        });
    }
}

//do 1 function cũng là 1 object
/*
    Nguyên tắc của các RULES:
    1. Khi có lỗi => trả ra message báo lỗi
    2. Khi hợp lệ => Không trả ra cái gì cả (undefined)
 
*/
Validator.isRequired = function (selector, message) {
    return {
        selector: selector,
        test: function (value) {
            return value.trim() ? undefined : message || 'This field is required'
        }
    };
}

Validator.isEmail = function (selector, message) {
    return {
        selector: selector,
        test: function (value) {
            var regex = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
            return regex.test(value) ? undefined : message || 'Please enter a valid Email address, ex: \"abc@gmail.com\"\n'
        }
    };
}

Validator.isConfirmed = function (selector, getPass, message) {  //getPass ở đây là function callback
    return {
        selector: selector,
        test: function (value) {
            return value === getPass() ? undefined : message || 'The password confirmation doesn\'t match'
        }
    };
}

Validator.needLength = function (selector, min, max, message) {
    return {
        selector: selector,
        test: function (value) {
            return (value.length >= min && value.length <= max) ? undefined :  message || `This field should be between ${min} and ${max} characters`;
        }
    };
}

