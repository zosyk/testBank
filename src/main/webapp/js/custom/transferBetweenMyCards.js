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




function findCardByID(cards, id) {

    for(var i = 0; i<cards.length; i++){
        var card = cards[i];
        if (card.id == id) return card;
    }
}
