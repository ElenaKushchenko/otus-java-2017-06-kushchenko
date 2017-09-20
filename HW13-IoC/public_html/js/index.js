$(document).ready(function () {
    $.ajax({
        url: '/login',
        type: 'GET',
        success: function (data, status) {
            window.localStorage.setItem("user", JSON.stringify(data));
            $(location).attr('href', "monitoring.html");
        },
        error: function (data, status) {
            window.localStorage.removeItem("user");
            if (data && data.status === 401) {
                $(location).attr('href', "login.html");
            } else {
                alert('Server error: ' + data.status);
            }
        }
    });
});