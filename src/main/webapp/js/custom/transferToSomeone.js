/**
 * Created by alex on 11/16/16.
 */

var fromCard = 0;
var cards;

var fromCardSelector;


function createSelectFromCards(cardsSelector, data) {
    fromCardSelector = cardsSelector;
    cards = JSON.parse(data);
    var size = cards.length;

    for(var i = 0; i<size; i++){
        var card = cards[i];
        var option = document.createElement("option");
        option.setAttribute("value", card.cardNumber);
        option.text = card.number + " " +card.type +   "  sum: " + card.value + " $";
        cardsSelector.appendChild(option);
    }

    selectorFromCardsListener(cardsSelector);
}


function selectorFromCardsListener(selector) {
    selector.onchange = function () {
        fromCard = this.selectedIndex;
        console.log("from card index: " + this.selectedIndex + " , cardID: " + cards[fromCard].id);

    }
}


function createTransactionToSomeOne() {

    if(validate()){

        var number = $("#toCardNumber");
        checkCardNumber(number.val().trim());

    }
}

function validate(){

    var result = true;
    var errorMessage = "";

    if($("#toCardNumber").val() == ""){
        result = false;
        errorMessage += "Enter card number whom you want to transfer money\n";
    } else if($("#toCardNumber").val().length <16){
        result = false;
        errorMessage += "Card number must be 16 digits\n";
    }

    if($("#sum").val() == ""){
        result = false;
        errorMessage += "Enter sum of money what you want to transfer";
    }

    if(!result)
        alert(errorMessage);

    return result;
}

function checkCardNumber(number) {

    var nubmerObj = {
        "number": number
    };

    $.ajax({
        type: "POST",
        url: "/checkCard",
        contentType: "application/x-www-form-urlencoded",
        data: nubmerObj,
        dataType: "json",
        success: function (data) {
            onSuccess(data)},
        error: function (jqXHR, testStatus, errorThrown) {
            alert(errorMessage);
        }
    })
}

function onSuccess(data) {
    var cardNumber = $("#toCardNumber");
    var errorCardNumber = $("#error-card-number");

    if(data.hasOwnProperty("error")){
        errorCardNumber.text(data.error);
        cardNumber.addClass("error-input");
        cardNumber.keypress(function (event) {
            clearInput(cardNumber, errorCardNumber);
        });

        return;
    }


    $("#fromCardNumber").val(cards[fromCard].number);

    $('#create_transaction_form').submit();

}

function clearInput(email, errorCardNumber) {
    email.removeClass("error-input");
    errorCardNumber.text("");
}

