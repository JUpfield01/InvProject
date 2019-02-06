
function updateP(user) {

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
                    + `<div class="col-2 bg-light font-weight-bold">Product Name</div>`
                    + `<div class="col-4 bg-light font-weight-bold">Description</div>`
                    + `<div class="col-2 bg-light font-weight-bold">Price</div>`
                    + `<div class="col-1 bg-light font-weight-bold">Quantity</div>`
                    + `<div class="col-2 bg-light font-weight-bold">Image</div>`
                    + `<div class="col-1 bg-light font-weight-bold">Edit</div>`
                    + `</div>`;

                for (let product of productList) {
                    productHTML += `<div class="row mb-2">`
                        + `<div class="col-2">${product.productname}</div>`
                        + `<div class="col-4">${product.productdescription}</div>`
                        + `<div class="col-2">${product.productcost}</div>`
                        + `<div class="col-1">${product.quantity}</div>`
                        + `<div class="col-2"><a href="${product.imageurl}" target=”_blank”><img width="120" height="90" src="${product.imageurl}"></a></div>`
                        + `<div class="col-1 text-right">`
                        + `<a class="container-fluid"  href="/client/editproducts.html?id=${product.productid}">Edit</a>`

                        + `</div>`;
                }
                productHTML += `</div>`;

                $('#product').html(productHTML);

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
                    updateP(user);
                }
            }
        });
    }
}

function logout() {
    Cookies.remove("sessionToken");
    window.location.href = "/client/login.html";
}

function pageLoadProduct() {

    checkLogin();

}

