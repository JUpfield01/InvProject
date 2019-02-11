let id = -1;

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
                    id = user.id;
                    $("#username").html(user.username);
                    $("#password").html(user.password);
                    $("#userid").html(user.id);
                }
            }
        });
    }
}

function logout() {
    Cookies.remove("sessionToken");
    window.location.href = "/client/login.html";
}

function accountDetailsPageLoad() {

    checkLogin();
    prepareUpdateUserNameForm();
    prepareUpdatePasswordForm();

}

function prepareUpdatePasswordForm(){
    console.log("Preparing password form...");
    const form = $('#userPasswordForm');
    form.unbind("submit");
    form.submit(event => {
        event.preventDefault();
        if (id === -1) {
            alert("Error: Failed to establish user!");
        }
        $.ajax({
            url: '/user/updatePassword/' + id,
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

function prepareUpdateUserNameForm(){
    console.log("Preparing username form...");
    const form = $('#userNameForm');
    form.unbind("submit");
    form.submit(event => {
        event.preventDefault();
        if (id === -1) {
            alert("Error: Failed to establish user!");
        }
        $.ajax({
            url: '/user/updateUser/' + id,
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
