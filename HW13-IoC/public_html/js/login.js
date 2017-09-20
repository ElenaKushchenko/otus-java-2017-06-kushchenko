$(document).ready(function () {
    if (window.localStorage.getItem("user")) {
        $(location).attr('href', "monitoring.html");
    }
});

$(function () {
    $('#loginForm')
        .submit(function (event) {
            event.preventDefault();

            var form = $(this);
            $.ajax({
                url: '/hw13/login',
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