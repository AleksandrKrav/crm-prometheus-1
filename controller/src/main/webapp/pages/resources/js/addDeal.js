/**
 * Created by Alexandr on 09.01.2016.
 */
$(function () {
    $('#datetimepicker1').datetimepicker({
        pickTime: false
    });
    var date = new Date();
    var curr_date = date.getDate();
    var curr_month = date.getMonth() + 1;
    var curr_year = date.getFullYear();

    $('#company-add-form [name="task-date"]').val(moment().format('MM/DD/YYYY'));
    //if ($('#company-new').val().trim() == '') {
    //    $('#company-id-button').removeProp('disabled');
    //}else{
    //    $('#contact-fix-button').removeProp('disabled');
    //    $('#contact-id-button').removeProp('disabled');
    //    $('#task-id-button').removeProp('disabled');
    //    $('#deal-id-button').removeProp('disabled');
    //
    //}
});

//$("#task-period-type").change(function()
function changePeriodType()
{
    var selPeriodVal = parseInt($("#task-period-type option:selected").val());
    finishDate = new Date();
    switch (selPeriodVal){
        case 1:
            break;
        case 2:
            finishDate.setDate(finishDate.getDate()+1);
            break;
        case 3:
            finishDate.setDate(finishDate.getDate()+(7-finishDate.getDay()+1));
            break;
        case 4:
            finishDate.setDate(1);
            finishDate.setMonth(finishDate.getMonth()+1);
            break;
        case 5:
            finishDate.setDate(1);
            finishDate.setMonth(0);
            finishDate.setFullYear(finishDate.getFullYear()+1);
            break;
    }
    var date = new Date();
    var d = new Date();

    var month = d.getMonth()+1;
    var day = d.getDate();

    var output = d.getFullYear() + '/' +
        (month<10 ? '0' : '') + month + '/' +
        (day<10 ? '0' : '') + day;
    $("#datetimepicker1").data('DateTimePicker').setDate(finishDate);
};

function addDeal(locHref){
    var data = new FormData();

    data.append('ajax', 'ajax');
    data.append('deal-add','deal-add');
    data.append('company-name',$('#company-name-deal').val());
    data.append('deal-contact','');
    var inputs = $('#deal-add-form').serializeArray();
    $.each(inputs, function (index, value) {
        data.append(value.name,value.value);
    });
    $.each($('#files')[0].files, function(index, value){
        data.append('file'+index, value);
    });

    $.ajax({
        url: '/add_deal',
        data: data,
        contentType: false,
        processData: false,
        enctype: 'multipart/form-data',
        type: 'POST',
        success: function(data){

        }
    });
}


$(document).ready(function(){
    //event of click addDeal-button
    $("#deal-add-button").click(function(){
        addDeal();
    });
})