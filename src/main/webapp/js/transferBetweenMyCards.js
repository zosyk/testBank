/**
 * Created by alex on 11/16/16.
 */

var fromCard = 0, toCard = 0;
var cards;

var fromCardSelector, toCardSelector;
var sumInput;


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

function createSelectFromCardsAjax(cardsSelector, cards) {
    fromCardSelector = cardsSelector;
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



function createSelectToCards(cardsSelector, data) {
    toCardSelector = cardsSelector;
    var cards = JSON.parse(data);
    var size = cards.length;

    for(var i = 0; i<size; i++){
        var card = cards[i];
        var option = document.createElement("option");
        option.setAttribute("value", card.cardNumber);
        option.text = card.number + " " +card.type +   "  sum: " + card.value + " $";
        cardsSelector.appendChild(option);
    }

    selectorToCardsListener(cardsSelector);
}


function createSelectToCardsAjax(cardsSelector, cards) {
    toCardSelector = cardsSelector;
    var size = cards.length;

    for(var i = 0; i<size; i++){
        var card = cards[i];
        var option = document.createElement("option");
        option.setAttribute("value", card.cardNumber);
        option.text = card.number + " " +card.type +   "  sum: " + card.value + " $";
        cardsSelector.appendChild(option);
    }

    selectorToCardsListener(cardsSelector);
}

function selectorToCardsListener(selector) {
    selector.onchange = function () {
        toCard = this.selectedIndex;
        console.log(" to Card index: " + this.selectedIndex + " , cardID: " + cards[toCard].id);

    }
}

function createTransactionBetween(transactionForm) {

    var fromInput = transactionForm.elements["fromID"];
    var toInput = transactionForm.elements["toID"];

  if(validateTransactionBetween()){
      var card = {
          "fromID": cards[fromCard].id,
          "toID": cards[toCard].id
      }

      fromInput.value = card.fromID;
      toInput.value = card.toID;

      transactionForm.submit();


  } else {
      alert("Please choose credit card, which you want to fill")
  }

}

function validateTransactionBetween() {
    var result = true;
    if(fromCard == toCard)
        result = false;
    return result;
}

function initSelectors(data) {
    var model = '${cards}';
    sumInput.value = '';
    removeOptions(fromCardSelector);
    removeOptions(toCardSelector);
    createSelectFromCardsAjax(fromCardSelector, data);
    createSelectToCardsAjax(toCardSelector, data);
}

function removeOptions(selectbox)
{
    var i;
    for(i = selectbox.options.length - 1 ; i >= 0 ; i--)
    {
        selectbox.remove(i);
    }
}

function tableTransactions(json) {
    json = JSON.parse(json);
    var transactions = json.transactions;
    var cards = json.cards;
    var table = "<table class='table'>";

    var size = transactions.length;
    if(size == 0){
        table += "<p>There is no any transactions</p>"
    }
    for (var i=0;i!=size;++i) {
        if(i == 0){
            table += "<tr><th>From Card</th><th>To Card</th><th>Sum</th><th>Date</th></tr>"
        }
        var transaction = transactions[i];
        var date = new Date(transaction.time);
        var dateToString = date.getDate() + "." + (date.getMonth() + 1) + "." + date.getFullYear();
        var fromCard = findCardByID(cards, transaction.fromID);
        var toCard = findCardByID(cards, transaction.toID);
        table += "<tr><td>"+fromCard.cardNumber + " " +fromCard.cardType+"</td><td>"+toCard.cardNumber + " " +toCard.cardType+"</td><td>"+transaction.sum + " $"+"</td><td>" + dateToString + "</td></tr>"
    }
    table += "</table>"

    $('#transactions').html(table);
}


function findCardByID(cards, id) {

    for(var i = 0; i<cards.length; i++){
        var card = cards[i];
        if (card.id == id) return card;
    }
}
