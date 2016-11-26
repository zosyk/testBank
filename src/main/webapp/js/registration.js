/**
 * Created by alex on 11/26/16.
 */


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


    return result;
}