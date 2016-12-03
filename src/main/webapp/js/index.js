/**
 * Created by alex on 11/5/16.
 */


var page = 0;

function validate(pincode, confirmPincode) {

    var result  = true;
    var errorMes = "";

    if(pincode == '' || confirmPincode == '') {
        result = false;
        errorMes = "Enter your pincode";
    } else if(pincode.length <4 || confirmPincode.length <4) {
        result = false;
        errorMes = "Length of pincode must be 4 digits";
    } else if(pincode != confirmPincode){
        result = false;
        errorMes = "pincode isn't match"
    }

    if(!result)
        alert(errorMes);

    return result;
}

function createCard(pincode, confirm_pincode, type, createCardContainer) {

    if(createCardContainer.offsetHeight == 0){
        createCardContainer.style.display = 'block';
        return;
    }

    if (validate(pincode.value, confirm_pincode.value)) {

        var card = {
            "pincode": pincode.value,
            "type": type
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
                console.log("in success method, createCard");
                createCardContainer.style.display = 'none';
                pincode.value = '';
                confirm_pincode.value = '';
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
    var table = "<table class=' table table-striped'>";

    console.log("offset: " + data.offset + " , limit: " + data.limit + " , size: " + data.size);
    var size = data.creditCards.length;
    if(size == 0){
        table += "<p>There is no any card. You can create it below:</p>"
    }
    for (var i=0;i!=size;++i) {
        if(i == 0){
            table += "<tr><th>Card Type</th><th>Card Number</th><th>Card Value</th></tr>"
        }
        table += "<tr><td>"+data.creditCards[i].type+"</td><td>"+data.creditCards[i].number+"</td><td>"+data.creditCards[i].value+" \$</td><td>" + "<a href=" + "/getHistory?id=" +  data.creditCards[i].id +  " >Watch History</a>" + "</td></tr>"
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
