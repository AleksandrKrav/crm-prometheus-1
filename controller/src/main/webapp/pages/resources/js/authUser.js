$(document).ready(function(){
    $("#butLogin").click(function(){
        loginUser();
    });

    $("#but-reg").click(function(){
        regUser();
    });
})

//for index.jsp
function loginUser(){
    var data = new FormData();

    data.append('ajax', 'ajax');
    var inputs = $('#form-auth-user').serializeArray();
    $.each(inputs, function (index, value) {
        data.append(value.name,value.value);
    });
    $.ajax({url:"/userAuth?login="+$("#inputLogin").val()+"&pass="+$("#inputPassword").val(),
        datatype:"json",
        success:function(respData) {
            var login;
            var statusAuth;
            var jsonData = $.parseJSON(respData);
            statusAuth = jsonData[0];
            login = jsonData[1];
            if (statusAuth == "Вход выполнен"){
                window.location.href = "/index.jsp";
            }else {
                $("#status-login").text(statusAuth);
            }
        }
    })
};

function regUser(){
    var data = new FormData();
    data.append('ajax', 'ajax');
    var inputs = $('#form-reg-user').serializeArray();
    $.each(inputs, function (index, value) {
        data.append(value.name,value.value);
    });
    $.ajax({url:"/userReg?name=" + $("#reg-name").val() + "&login="+$("#reg-login").val()+
        "&pass="+$("#reg-pass").val()+"&email="+$("#reg-email").val(),
        datatype:"json",
        success:function(respData) {
            var login;
            var statusAuth;
            var jsonData = $.parseJSON(respData);
            statusAuth = jsonData[0];
            login = jsonData[1];
            if (statusAuth == "Good"){
                window.location.href = "/index.jsp";
            }else {
                $("#status-reg").text(statusAuth);
            }
        }
    })
};

//for topPanel.jsp
function getUserLogin(){
    $.ajax({url:"/userAuth", datatype:"json",
        success:function(respData) {
            var jsonData = $.parseJSON(respData);
            $("#userLogin").text(jsonData[1]);
        }
    })
};

function initPage(){
    getUserLogin();
}
