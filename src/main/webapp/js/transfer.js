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
        option.text = card.cardNumber + " " +card.cardType +   "  sum: " + card.cardValue + " $";
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
        option.text = card.cardNumber + " " +card.cardType +   "  sum: " + card.cardValue + " $";
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
        option.text = card.cardNumber + " " +card.cardType +   "  sum: " + card.cardValue + " $";
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
        option.text = card.cardNumber + " " +card.cardType +   "  sum: " + card.cardValue + " $";
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

function createTransaction(sum) {
    sumInput = sum;

  if(validateTransaction()){
      var card = {
          "fromID": cards[fromCard].id,
          "toID": cards[toCard].id,
          "sum": sum.value
      }

      $.ajax({
          headers: {
              'Accept': 'application/json',
              'Content-Type': 'application/json'
          },
          method : 'post',
          url: '/createTransaction',
          data: JSON.stringify(card),
          success: function (data) {
              initSelectors(data);
              alert("Transaction completed")
          },
          error: function (jqXHR, testStatus, errorThrown) {
              console.log("in error method, error: " + errorThrown);
          }
      });
  } else {
      alert("Please choose credit card, which you want to fill")
  }
}

function validateTransaction() {
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
