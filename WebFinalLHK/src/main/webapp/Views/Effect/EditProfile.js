const btnReset = document.querySelector('.reset-btn');
const firstname = document.querySelector('#profile-first-name');
const firstnameValue = firstname.value;
const lastname = document.querySelector('#profile-last-name');
const lastnameValue = lastname.value;
const phone = document.querySelector('#profile-phone');
const phoneValue = phone.value;
const description = document.querySelector('#profile-description');
const descriptionValue = description.value;

function ResetForm(options) {
    const formElement = document.querySelector(options.form);
    const inputElements = formElement.querySelectorAll('input[type=text]');
    inputElements.forEach(inputElement => {
        const errorElement = inputElement.parentElement.querySelector(options.errorMessage);
        errorElement.innerHTML = '';
        inputElement.parentElement.classList.remove('invalid');
    })

}

function Validator(options) {

    var selectorRules = {};

    function validate(inputElement, rule, errorElement) {
        // var invalid = rule.test(inputElement.value);
        var rules = selectorRules[rule.selector];

        for (var i = 0; i < rules.length; i++) {

            errorMessage = rules[i](inputElement.value);

            if (errorMessage) break;
        }

        if (errorMessage) {
            errorElement.innerHTML = errorMessage;
            inputElement.parentElement.classList.add('invalid');
        } else {
            errorElement.innerHTML = '';
            errorElement.parentElement.classList.remove('invalid');
        }
        return !errorMessage;
    }
    var formElement = document.querySelector(options.form);
    if (formElement) {
        formElement.onsubmit = (e) => {

            var isFormValid = true;
            options.rules.forEach((rule) => {
                var inputElement = formElement.querySelector(rule.selector);
                var isValid = validate(inputElement, rule);
                if (!isValid) {
                    isFormValid = false;
                }
            });

            if (!isFormValid) {
                e.preventDefault();
            }

        }
        options.rules.forEach(function(rule) {
            var inputElement = formElement.querySelector(rule.selector);
            var errorElement = inputElement.parentElement.querySelector(options.errorMessage);

            if (Array.isArray(selectorRules[rule.selector])) {
                selectorRules[rule.selector].push(rule.test);
            } else {
                selectorRules[rule.selector] = [rule.test]
            }

            if (inputElement) {
                inputElement.onblur = () => {
                    validate(inputElement, rule, errorElement);
                }
                inputElement.oninput = () => {
                    errorElement.innerHTML = '';
                    inputElement.parentElement.classList.remove('invalid');
                }
            }

        });
    }


}

Validator.isRequired = function(selector) {
    return {
        selector,
        test(value) {
            return value.trim() ? undefined : `Please enter a valid!`;
        }
    }
}

Validator.isPhone = function(selector) {
    return {
        selector,
        test(value) {
            var regex = /\(?([0-9]{3})\)?([ .-]?)([0-9]{3})\2([0-9]{4})/;
            return regex.test(value) ? undefined : 'Please enter a valid phone number'
        }
    }
}

Validator.isMaxLength = function(selector, max) {
    return {
        selector,
        test(value) {
            return value.length <= max ? undefined : `Please enter maximum of ${max} characters`;
        }
    }
}

Validator.isMinLength = function(selector, min) {
    return {
        selector,
        test(value) {
            return value.length >= min ? undefined : `Please enter minimum of ${min} characters`;
        }
    }
}

Validator({
    form: '.form-profile',
    errorMessage: '.error-message',
    rules: [
        Validator.isRequired('#profile-first-name'),
        Validator.isMinLength('#profile-first-name', 3),
        Validator.isMaxLength('#profile-first-name', 30),
        Validator.isRequired('#profile-last-name'),
        Validator.isMinLength('#profile-last-name', 3),
        Validator.isMaxLength('#profile-last-name', 30),
        Validator.isPhone('#profile-phone'),
        Validator.isMinLength('#profile-phone', 9),
        Validator.isMaxLength('#profile-phone', 13),
        Validator.isMaxLength('#profile-description', 200)
    ]
});


btnReset.onclick = (e) => {
    firstname.value = firstnameValue;
    lastname.value = lastnameValue;
    phone.value = phoneValue;
    description.value = descriptionValue;
    ResetForm({
        form: '.form-profile',
        errorMessage: '.error-message',
    })
};
