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
}



