/*
$(document).ready(function(){
    $("#butLogin").click(function(){
        loginUser();
    })
})

function loginUser(){
    $.ajax({url:"/loginUser?login="+$("#inputLogin").val()+"&password="+$("#inputPassword").val(), datatype:"json",
        success:function(respData) {
            var login;
            var statusAuth;
            var jsonData = $.parseJSON(respData);
            statusAuth = jsonData[0];
            login = jsonData[1];
            if (statusAuth == "Good"){
                window.location.href = "/index.jsp";
            }else {
                $("#statusLogin").text(statusAuth);
            }
        }
    })
}*/
