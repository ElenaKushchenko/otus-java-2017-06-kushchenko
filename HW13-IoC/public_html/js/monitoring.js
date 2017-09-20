var base = window.location.pathname.substr(0, window.location.pathname.lastIndexOf("/"));

$(document).ready(
    function () {
        var user = JSON.parse(window.localStorage.getItem("user"));

        if (!user) {
            $(location).attr('href', "login.html");
        }

        $("#greeting").text("Hello " + user.name + "!");
        if (user.role === "ADMIN") {
            $("#monitoring").show();
            loadCacheInfo();
        } else {
            $("#accessWarning").show();
        }
    }
);

$(function () {
    $('#changeAccount').click(function () {
        $.ajax({
            url: base + '/login',
            type: 'DELETE',
            success: function (data, status) {
                window.localStorage.removeItem("user");
                $(location).attr('href', "login.html");
            },
            error: function (data, status) {
                alert('Server error: ' + data.status);
            }
        });
    })
});

$(function () {
    $('#refresh').click(function () {
        loadCacheInfo()
    })
});

$(function () {
    $('#userForm')
        .submit(function (event) {
            event.preventDefault();

            var form = $(this);
            $.ajax({
                url: base + '/user',
                type: 'GET',
                data: form.serialize(),
                dataType: "json",
                success: function (data, status) {
                    console.log(data);
                    loadCacheInfo();
                },
                error: function (data, status) {
                    if (data)
                        resolveErrorStatus(data.status);
                }
            });
        });
});

function loadCacheInfo() {
    $.ajax({
        url: base + '/cache',
        type: 'GET',
        dataType: "json",
        success: function (data, status) {
            $("#count").text(data.count);
            $("#hit").text(data.hit);
            $("#miss").text(data.miss);
            $("#maxElements").text(data.maxElements);
            $("#lifeTime").text(data.lifeTimeMs);
            $("#idleTime").text(data.idleTimeMs);
        },
        error: function (data, status) {
            if (data)
                resolveErrorStatus(data.status);
        }
    });
}

function resolveErrorStatus(status) {
    switch (status) {
        case 403:
            alert('Permission denied');
            break;
        case 401:
            alert('Unauthorized');
            break;
        default:
            alert('Server error: ' + status);
    }
}