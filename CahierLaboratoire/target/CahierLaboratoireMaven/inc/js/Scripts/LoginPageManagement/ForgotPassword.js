//$.getScript("inc/js/Scripts/Popup/Popup.js");


function forgotPopup(name){
    $(".modal").css("background-color","rgb(0,0,0,0.4)");
    $(".modal-content").css("background-color","white");
    $(".modal-content").css("color","black");
    $(".modal-content").empty();
    $(".modal-content").append(
        '<p>Veuillez renseigner votre adresse email, si votre compte existe nous vous enverrons un lien de réinitialisation.</p>'+
        '    <form class="form-group row " >' +
        '      <div class="col-sm-9">' +
        '        <input class="form-control" type="email" placeholder="Email de votre compte">' +
        '      </div>' +
        '       <div class="col-sm-2" >' +
        '            <button class="btn btn-info" type="submit"> Confirmer</button>' +
        '       </div>'+
        '    </form>');

    var buttonSubmit = document.getElementsByClassName("btn btn-info")[0];
    buttonSubmit.onclick = function () {
        $("#"+name).hide();
        resetPopup();
    };
    var modal = document.getElementById(name);
    window.onclick = function (event) {
        if (event.target === modal) {
            $("#"+name).hide();
            resetPopup();
        }

    }

    $("#"+name).fadeIn();
}

function resetPopup() {
    $(".modal").css("background-color","rgb(0,0,0,0)");
    $(".modal-content").css("color","white");
}