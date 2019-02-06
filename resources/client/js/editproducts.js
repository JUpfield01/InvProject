let id = -1;

function pageLoad() {

    let lastPage =  Cookies.get("breadcrumb");
    $("#back").attr("href", lastPage);

    let currentPage = window.location.href;
    Cookies.set("destination", currentPage);

    //checkLogin();

    $("#logout").click(event => {
        Cookies.remove("sessionToken");
        window.location.href = "/client/index.html";
    });

    let params = getQueryStringParameters();
    if (params['id'] !== undefined) {
        id = params['id'];
    }

    console.log(id);

    if (id !== -1) {
        loadProduct();
        //resetDeleteButton();
    }

    resetForm();

}

function resetForm() {

    const form = $("#productForm");

    form.unbind("submit");
    form.submit(event => {
        event.preventDefault();
        $.ajax({
            url: '/product/save/' + id,
            type: 'POST',
            data: form.serialize(),
            success: response => {
                if (response === 'OK') {
                    window.location.href = "/client/products.html";
                } else {
                    alert(response);
                }
            }
        });
    });



}

function loadProduct() {

    $.ajax({
        url: '/product/get/' + id,
        type: 'GET',
        success: productDetails => {
            if (productDetails.hasOwnProperty('error')) {
                alert(productDetails.error);
            } else {
                $("[name='productname']").val(productDetails.productname);
                $("[name='description']").val(productDetails.productdescription);
                $("[name='price']").val(productDetails.productcost);
                $("[name='quantity']").val(productDetails.quantity);
                $("[name='image']").val(productDetails.imageurl);

            }
        }
    });

}
