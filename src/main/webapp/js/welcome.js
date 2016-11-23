/**
 * Created by alex on 11/5/16.
 */


var page = 0;

function validate(cardNumber, cardPassword, cardType) {
    var result  = true;

    if(cardNumber == ''){
        result = false;
        alert("Enter your cardNumber please");
    } else if(cardPassword == '') {
        result = false;
        alert("Enter your cardPassword please");
    } else if(cardType == ''){
        result = false;
        alert("Enter your cardType please");
    }
    return result;
}

function createCard(cardNumber, cardPassword, cardType) {

    if (validate(cardNumber, cardPassword, cardType)) {

        var card = {
            "cardNumber": cardNumber,
            "cardPassword": cardPassword,
            "cardType": cardType
        }

        $.ajax({
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            method : 'post',
            url: '/createCard',
            data: JSON.stringify(card),
            complete: function () {
                console.log("in complete method, createCard");
            },
            success: function (data) {
                loadUsers();
                console.log("in success method, createCard")
            },
            error: function (jqXHR, testStatus, errorThrown) {
                console.log("in error method, error: " + errorThrown);
            }
        });
    }
}

function loadUsers() {
    $.ajax({
        url: "/getAllCards?page="+ page,
        method : 'get',
        success: function (data) {
            callBackLoad(data)},
        error: function (jqXHR, testStatus, errorThrown) {
            console.log("in getAllCards method, error: " + errorThrown);
        }
    });
}

function callBackLoad(data) {
    console.log("success : getAll");

    // var usersBlock = document.getElementById("users");
    var table = "<table class='table'>";

    console.log("offset: " + data.offset + " , limit: " + data.limit + " , size: " + data.size);
    var size = data.creditCards.length;
    if(size == 0){
        table += "<p>There is no any card. You can create it below:</p>"
    }
    for (var i=0;i!=size;++i) {
        if(i == 0){
            table += "<tr><th>Card Type</th><th>Card Number</th><th>Card Value</th></tr>"
        }
        table += "<tr><td>"+data.creditCards[i].cardType+"</td><td>"+data.creditCards[i].cardNumber+"</td><td>"+data.creditCards[i].cardValue+" \$</td><td>" + "<a href=" + "/getHistory?id=" +  data.creditCards[i].id +  " >Watch History</a>" + "</td></tr>"
    }
    table += "</table>"
    drawPagination(data.offset, data.limit, data.size);

    // usersBlock.innerHTML = table;
    $('#cards_table').html(table);
    $('#card_number').val('');
    $('#card_ps').val('');
    $('#card_conf_ps').val('');
}

function drawPagination(offset, limit, size) {

    var table = "<table>";

    var size = size/limit;
    table += "<tr>";
    for(var i = 0; i<size; i++){
        var pag = i+1;
        table += "<td>" + "<a onclick='paginate(" + (i+1) + ")'  id='pag' href=" + "#page" + (i+1) + ">" +(i+1) + "</a>" + "</td>";
    }
    table += "</tr>"
    table += "</table>"

    $('#pagination').html(table);

}

function paginate(pag) {
    // alert('pag: ' + pag);
    page = pag-1;
    loadUsers();
}


function registration() {

    var xmlHttp = new XMLHttpRequest();
    xmlHttp.open( "GET", "/registration", false ); // false for synchronous request
    xmlHttp.send();



}

function getCards() {
    
}
