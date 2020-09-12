$(document).ready(function () {
    $("#authenticate").click(function () {
        $.get({
            url: "/api/steam/authenticate",
            success: onGetAuthenticationUrl,
        });
    });
    var url = new URL(window.location.href);
    var id = url.searchParams.get("openid.sig");
    if (id) {
        var params = getJsonFromUrl(location.search);
        console.debug("verify")
        $.post({
            url: "/api/steam/verify",
            data: JSON.stringify(params),
            contentType: "application/json; charset=utf-8",
            success: onVerify,
            error: function (req, status, error) {
                console.log(req, status, error)
            }
        });

    }
});


function onGetAuthenticationUrl(data) {

    window.location.href = data;
}

function onVerify(data) {
    console.log(data);
    if (data) {
        $("#auth-result").text("Authentifizierung erfolgreich - name: " + data.personaname)
        $("#auth-image").attr("src", data.avatar)
    } else {
        $("#auth-result").text("Authentifizierung fehlerhaft")
    }
}

function getJsonFromUrl(url) {
    if (!url) url = location.search;
    var query = url.substr(1);
    var result = {};
    query.split("&").forEach(function (part) {
        var item = part.split("=");
        result[item[0]] = decodeURIComponent(item[1]);
    });
    return result;
}
