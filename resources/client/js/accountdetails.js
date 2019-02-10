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

function updatePassword(){
    const form = $('#passwordForm');

    form.unbind("submit");
    form.submit(event => {
        $ajax({
            url: '/user/save/' + id,
            type: 'POST',
            data: form.serialize(),
            success: response => {
                if (response === 'OK') {
                    window.location.href = "/client/accountdetails.html";
                } else {
                    alert(response);
                }
            }
        });
    });
}

function updateUserName(){
    const form = $('#userNameForm');
    console.log("Invoked user()");
    form.unbind("submit");
    form.submit(event => {
        $ajax({
            url: '/updateUser/save/' + id,
            type: 'POST',
            data: form.serialize(),
            success: response => {
                if (response === 'OK') {
                    window.location.href = "/client/accountdetails.html";
                } else {
                    alert(response);
                }
            }
        });
    });
}
