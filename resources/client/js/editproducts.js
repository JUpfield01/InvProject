function pageLoad() {

    let id = -1;

    let lastPage =  Cookies.get("breadcrumb");
    $("#back").attr("href", lastPage);

    let currentPage = window.location.href;
    Cookies.set("destination", currentPage);

    checkLogin();

    $("#logout").click(event => {
        Cookies.remove("sessionToken");
        window.location.href = "/client/index.html";
    });

    let params = getQueryStringParameters();
    if (params['id'] !== undefined) {
        id = params['id'];
    }

    if (id !== -1) {
        loadProduct();
        resetDeleteButton();
    }

    resetForm();

}
function loadProduct() {

    $.ajax({
        url: '/product/get/' + id,
        type: 'GET',
        success: produtcDetails => {
            if (productDetails.hasOwnProperty('error')) {
                alert(productDetails.error);
            } else {
                $("[name='productname']").val(productDetails.productname);
                $("[name='manufacturer']").val(productDetails.manufacturer);
                $("[name='mediaType']").val(productDetails.mediaType);
                $("[name='year']").val(productDetails.year);
                $("[name='imageURL']").val(productDetails.imageURL);

            }
        }
    });

}
