function updateInv(user) {

    let id = user.id;

    $.ajax({
        url: '/inventory/list/' + id,
        type: 'GET',
        success: productList => {

            if (productList.hasOwnProperty('error')) {

                alert(productList.error);

            } else {

                let productHTML = `<div class="container">`
                    + `<div class="row mb-2">`
                    +`<div class="col-2 bg-light font-weight-bold">Product ID</div>`
                    + `<div class="col-4 bg-light font-weight-bold">Product Name</div>`
                    + `<div class="col-2 bg-light font-weight-bold">Price</div>`
                    + `<div class="col-2 bg-light font-weight-bold">Quantity</div>`
                    + `<div class="col-2 text-right bg-light font-weight-bold">Edit Product</div>`


                    + `</div>`;

                for (let product of productList) {
                    productHTML += `<div class="row mb-2">`
                        + `<div class="col-2">${product.productid}</div>`
                        + `<div class="col-4">${product.productname}</div>`
                        + `<div class="col-2">${product.productcost}</div>`
                        + `<div class="col-2">${product.quantity}</div>`
                        + `<div class="col-2 text-right">`
                        + `<a class="container-fluid"  href="/client/editproducts.html?id=${product.productid}">Edit</a>`
                        +`</div>`
                        + `</div>`;
                }
                productHTML += `</div>`;

                $('#inventory').html(productHTML);

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
                if (!user.username) {
                    window.location.href = "/client/login.html";
                } else {
                    $("#username").html(user.username);
                    updateInv(user);
                }
            }
        });
    }
}

function logout() {
    Cookies.remove("sessionToken");
    window.location.href = "/client/login.html";
}

function pageLoadInventory() {

    checkLogin();

}

