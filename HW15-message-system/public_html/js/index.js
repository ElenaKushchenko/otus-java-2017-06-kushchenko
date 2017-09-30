$(document).ready(function () {
    var base = window.location.pathname.substr(0, window.location.pathname.lastIndexOf("/"));
    $.ajax({
        url: base + '/login',
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