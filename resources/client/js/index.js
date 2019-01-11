function updateInventory() {

    $.ajax({
        url: '/inventory/list/{id}',
        type: 'GET',
        success: inventoryList => {

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

                for (let product of inventoryList) {
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
            success: username => {
                if (username === "") {
                    window.location.href = "/client/login.html";
                } else {
                    $("#username").html(username);
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
    updateInventory();
}



