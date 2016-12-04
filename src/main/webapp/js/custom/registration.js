/**
 * Created by alex on 11/26/16.
 */

var errorMessage = "Internal error";


function registration(name, surname, email, password, confirmPassword){

    var result = true;
    var errorString = "Please recheck next fields: \n";
    if(name.val().trim().length == 0){
        errorString += "name\n";
        result = false;
        name.val("");
    }

    if(surname.val().trim().length == 0){
        errorString += "surname\n";
        result = false;
        surname.value = "";
    }

    if(email.val().trim().length == 0){
        errorString += "email\n";
        result = false;
        email.val("");
    }

    if(password.val().trim().length == 0){
        errorString += "password\n";
        result = false;
        password.val("");
    }

    if(confirmPassword.val().trim().length == 0){
        errorString += "confirmPassword\n";
        result = false;
        confirmPassword.val("");
    }


    if(result == true){
        if (password.val() !=confirmPassword.val()){
            result = false;
            errorString = "Passwords do not match!"
        }
    }
    if(result == false){
        alert(errorString);
    }

    if(result == true){
        checkEmail(email.val().trim());
    }
}

function checkEmail(email) {

    var emailObj = {
        "email": email
    };

    $.ajax({
        type: "POST",
        url: "/registration/checkEmail",
        contentType: "application/x-www-form-urlencoded",
        data: emailObj,
        dataType: "json",
        success: function (data) {
            onSuccess(data)},
        error: function (jqXHR, testStatus, errorThrown) {
            alert(errorMessage);
        }
    })
}

function onSuccess(data) {
    var email = $("#email");
    var errorEmail = $("#error-email");
    var emailValue = email.val();

    if(data.hasOwnProperty("error")){
        errorEmail.text(data.error);
        email.addClass("error-input");
        email.keypress(function (event) {
            clearInput(email, errorEmail, emailValue);
        });

        return;
    }


    $('#registerUser').submit();

}

function clearInput(email, errorEmail, emailValue) {
    email.removeClass("error-input");
    errorEmail.text("");
}

