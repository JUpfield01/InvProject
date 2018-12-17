function updateProductList() {

    $.ajax({
        url: '/product/list',
        type: 'GET',
        success: productList => {

            if (productList.hasOwnProperty('error')) {

                alert(productList.error);

            } else {

                let productsHTML = `<div class="container">`
                    + `<div class="row mb-2">`
                    + `<div class="col-3 bg-light font-weight-bold">Product Name</div>`
                    + `<div class="col-2 bg-light font-weight-bold">Description</div>`
                    + `<div class="col-1 bg-light font-weight-bold">Cost</div>`
                    + `<div class="col-2 bg-light font-weight-bold">Image</div>`
                    + `<div class="col-3 text-right bg-light font-weight-bold">Options</div>`
                    + `</div>`;

                for (let product of productList) {
                    productsHTML += `<div class="row mb-2">`
                        + `<div class="col-3">${product.productname}</div>`
                        + `<div class="col-2">${product.productdescription}</div>`
                        + `<div class="col-1">${product.productcost}</div>`
                        + `<div class="col-2"><a href="${product.imageurl}" target=”_blank”><img width="120" height="90" src="${product.imageurl}"></a></div>`
                        + `<div class="col-3 text-right">`
                        + `<a class="btn btn-sm btn-info mr-2"  href="/client/games.html?id=${product.id}">Update</a>`
                        + `<a class="btn btn-sm btn-info mr-2"  href="/client/accessories.html?id=${product.id}">Remove</a>`
                        + `<a class="btn btn-sm btn-success"  href="/client/editconsole.html?id=${product.id}">Edit</a>`
                        + `</div>`;
                }
                productsHTML += `</div>`;

                $('#products').html(productsHTML);

            }

        }
    });
    function checkLogin() {

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

        $("#logout").click(event => {
            Cookies.remove("sessionToken");
            window.location.href = "/client/login.html";
        });
    }

}
