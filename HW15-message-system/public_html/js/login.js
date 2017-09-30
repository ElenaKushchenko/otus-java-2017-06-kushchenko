$(document).ready(function () {
    if (window.localStorage.getItem("user")) {
        $(location).attr('href', "monitoring.html");
    }
});

$(function () {
    var base = window.location.pathname.substr(0, window.location.pathname.lastIndexOf("/"));

    $('#loginForm')
        .submit(function (event) {
            event.preventDefault();

            var form = $(this);
            $.ajax({
                url: base + '/login',
                type: 'POST',
                data: form.serialize(),
                dataType: "json",
                success: function (data, status) {
                    window.localStorage.setItem("user", JSON.stringify(data));
                    $(location).attr('href', "monitoring.html");
                },
                error: function (data, status) {
                    if (data && data.status === 401) {
                        alert('Incorrect login or password');
                    } else {
                        alert('Server error: ' + data.status);
                    }
                }
            });
        });
});