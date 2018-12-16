//Création de popup

function setOnclickClose(name){
    $(".modal-content").append("<span class='close'>&times;</span>");
    var span = document.getElementsByClassName("close")[0];
    span.onclick = function () {
        $("#"+name).fadeOut();
    };
    var modal = document.getElementById(name);
    window.onclick = function (event) {
        if (event.target === modal) {
            $("#"+name).fadeOut();
        }
    }
}

function popupSetText(name,text){
    $(".modal-content").empty();
    $(".modal-content").append("<p >"+text+"</p>");
    setOnclickClose(name);

};
function popupSuccess(name,text){
    $(".modal-content").css("background-color","green");
    $(".modal-content").css("color","white");

    popupSetText(name,text);
};
function popupFailure(name,text){
    $(".modal-content").css("background-color","red");
    $(".modal-content").css("color","white");

    popupSetText(name,text);
};

function popupCreation(name) {
    var template = '<div id="'+name+'" class="modal" style="font-family: \'Roboto Mono\'"> <div class="modal-content"></div></div>';
    $("body").prepend(template);
    setOnclickClose(name);
    return name;
};

