function displayLowestThree(list) {

    let sortedList = list.sort((a, b) => a.quantity - b.quantity);

    let topThree = ['N/A', 'N/A', 'N/A'];

    for (let i = 0; i < 3; i++) {
        if (sortedList.length > i) {
            topThree[i] = `<div>${sortedList[i].productname}</div>` +
                `<div class="py-2">Quantity ${sortedList[i].quantity}</div>` +
                `<div ><img width="120" height="90" src="${sortedList[i].imageurl}"></div>`;
        }
    }

    let lowestHTML = `<div class="container">` +
                        `<div class="row">` +
                            `<div class="col-4 font-weight-bold">${topThree[0]}</div>` +
                            `<div class="col-4 font-weight-bold">${topThree[1]}</div>` +
                            `<div class="col-4 font-weight-bold">${topThree[2]}</div>` +
                        `</div>` +
                      `</div>`;

    $("#runninglow").html(lowestHTML);

}

function updateInventory(user) {

    let id = user.id;

    $.ajax({
        url: '/inventory/list/' + id,
        type: 'GET',
        success: inventoryList => {

            displayLowestThree(inventoryList);

            if (inventoryList.hasOwnProperty('error')) {

                alert(inventoryList.error);

            } else {

                let inventoryHTML = `<div class="container">`
                    + `<div class="row mb-2">`
                    + `<div class="col-3 bg-light font-weight-bold">Product Name</div>`
                    + `<div class="col-4 bg-light font-weight-bold">Description</div>`
                    + `<div class="col-1 bg-light font-weight-bold">Quantity</div>`
                    + `<div class="col-3 bg-light font-weight-bold">Image</div>`
                    + `</div>`;

                let sortedInventoryList = inventoryList.sort((a, b) => b.productid - a.productid);

                console.log(sortedInventoryList);

                for (let product of sortedInventoryList) {
                    inventoryHTML += `<div class="row mb-2">`
                        + `<div class="col-3">${product.productname}</div>`
                        + `<div class="col-4">${product.productdescription}</div>`
                        + `<div class="col-1">${product.quantity}</div>`
                        + `<div class="col-3"><a href="${product.imageurl}" target=”_blank”><img width="120" height="90" src="${product.imageurl}"></a></div>`

                        + `</div>`;
                }
                inventoryHTML += `</div>`;

                $('#inventory').html(inventoryHTML);

            }

        }
    });
}

function checkLogin() {

    console.log("Invoked checkLogin()");


    let token = Cookies.get("sessionToken");

    if (token === undefined) {
        window.location.href = "/client/login.html";
    } else {
        $.ajax({
            url: '/user/get',
            type: 'GET',
            success: user => {
                if (user === "") {
                    window.location.href = "/client/login.html";
                } else {
                    $("#username").html(user.username);
                    updateInventory(user);
                }
            }
        });
    }
}

function logout() {
    Cookies.remove("sessionToken");
    window.location.href = "/client/login.html";
}

function pageLoad() {

    checkLogin();

}



