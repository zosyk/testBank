


function tableTransactions(json) {
    json = JSON.parse(json);

    var transactions = json.transactions;
    var cards = json.creditCards;
    var table = "<table class='table'>";
    var size = transactions.length;

    if(size == 0){
        table += "<p>There is no any transactions</p>"
    }

    for (var i=0; i!=size; ++i) {
        if(i == 0){
            table += "<tr><th>From Card</th><th>To Card</th><th>Sum</th><th>Date</th></tr>"
        }
        var transaction = transactions[i];
        var date = new Date(transaction.time);
        var dateToString = date.getDate() + "." + (date.getMonth() + 1) + "." + date.getFullYear();
        var fromCard = findCardByNumber(cards, transaction.fromNumber);
        var toCard = findCardByNumber(cards, transaction.toNumber);

        table += "<tr><td>"+fromCard.number + " " +fromCard.type + " - "
            +"<span class='full-name'> " + fromCard.ownerName + "</span>"
            +"</td><td>"+toCard.number + " " +toCard.type + " - "
            +"<span class='full-name'> " + toCard.ownerName
            +  "</span>" + "</td><td>"+transaction.sum + " $"+"</td><td>" + dateToString + "</td></tr>"
    }
    table += "</table>"

    $('#transactions').html(table);
}

function findCardByNumber(cards, number) {

    for(var i = 0; i<cards.length; i++){
        var card = cards[i];
        if (card.number == number)
            return card;
    }
}