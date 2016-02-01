$(document).ready(function(){
    //event of click addContact-button
    $("#saveContact").click(function(){
        addContact();
    });
});

function addContact(locHref){
    var data = new FormData();

    data.append('ajax', 'ajax');
    var inputs = $('#formAddContact').serializeArray();
    $.each(inputs, function (index, value) {
        data.append(value.name,value.value);
    });
    $.each($('#files-contact')[0].files, function(index, value){
        data.append('file'+index, value);
    });

    $.ajax({
        url: '/addContact',
        data: data,
        contentType: false,
        processData: false,
        enctype: 'multipart/form-data',
        type: 'POST',
        success: function(data){

        }
    });
}

