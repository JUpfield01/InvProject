function resetLoginForm() {
    const loginForm = $('#loginForm');
    loginForm.submit(event => {
        event.preventDefault();
        $.ajax({
            url: '/user/login',
            type: 'POST',
            data: loginForm.serialize(),
            success: response => {
                if (response.startsWith('Error:')) {
                    alert(response);
                } else {
                    Cookies.set("sessionToken", response);
                    window.location.href = "/client/index.html";
                }
            }
        });
    });
}

function resetNewUserForm() {
    const newUserForm = $('#newUserForm');
    newUserForm.submit(event => {
        event.preventDefault();
        $.ajax({
            url: '/user/new',
            type: 'POST',
            data: newUserForm.serialize(),
            success: response => {
                if (response.startsWith('Error:')) {
                    alert(response);
                } else {
                    Cookies.set("sessionToken", response);
                    window.location.href = "/client/index.html";
                }
            }
        });
    });
}
function checkLogin() {
    if (window.location.href !== "http://localhost:8081/client/login.html?id=-1") {
        window.location.replace("http://localhost:8081/client/login.html?id=-1");

    }


    function pageLoad() {
        resetLoginForm();
        resetNewUserForm();
        checkLogin();
    }
}
