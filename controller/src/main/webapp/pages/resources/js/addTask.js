var finishDate;

//change time of Task
function changeTime(date, timeHourMin) {
    var arrTime = timeHourMin.split(':');
    date.setHours(arrTime[0]);
    date.setMinutes(arrTime[1]);
    if (arrTime[1] != 59) {
        date.setSeconds(0);
    } else if (arrTime[0] = 23) {
        date.setSeconds(59);
    }
}

//Init page
function initPageAddTask() {
    finishDate = new Date();
    changeTime(finishDate, $("#timeTask option:selected").val());
    getUserLogin();
}

$(document).ready(function(){
//create jquery-ui datePicker
$(function () {
    $("#datepicker").datepicker({
        firstDay: 1,
        dateFormat: "yy/mm/dd",
        onSelect: function () {
            finishDate = $("#datepicker").datepicker('getDate');
            changeTime(finishDate, $("#timeTask option:selected").val());
        }
    });
});

//event of click cancelTask-button
    function addTask(locHref) {
        urlPar = "?ajax=1&finishDate=" + finishDate.getTime() + "&nameTask=" + $('#selNameTask option:selected').val() +
            "&responsibleTask=" + $('#selResponsibleTask option:selected').val() + "&typeTask=" +
            $('#selResponsibleTask option:selected').val() + "&commentTask=" + $('#commentTask').val();
        $.ajax({url: "/addTask" + urlPar, datatype: "json",
            success: function (respData) {
                if (locHref != undefined) {
                    window.location.href = locHref;
                }
            }
        })
    }


    //change finishDate by selected other period of Task
    $("#periodTask").change(function(){
        var selPeriodVal = parseInt($("#periodTask option:selected").val());
        finishDate = new Date();
        switch (selPeriodVal){
            case 0:
                break;
            case 1:
                finishDate.setDate(finishDate.getDate()+1);
                break;
            case 2:
                finishDate.setDate(finishDate.getDate()+(7-finishDate.getDay()+1));
                break;
            case 3:
                finishDate.setDate(1);
                finishDate.setMonth(finishDate.getMonth()+1);
                break;
            case 4:
                finishDate.setDate(1);
                finishDate.setMonth(0);
                finishDate.setFullYear(finishDate.getFullYear()+1);
                break;
        }
        changeTime(finishDate, $("#timeTask option:selected").val());
        dateCalendar = finishDate.getFullYear()+"/"+(finishDate.getMonth()+1)+"/"+
            finishDate.getDate();
        $("#datepicker").datepicker("setDate", dateCalendar);
    });

    //change Time by selected other time of Task
    $("#timeTask").change(function(){
        changeTime(finishDate,$("#timeTask option:selected").val());
    });

    //event of click addTask-button
    $("#butAddTask").click(function(){
        addTask();
    });

    //event of click cancelTask-button
    $('#formAddTask [name="butCancelTask"]').click(function () {
        //window.location = "../../../index.jsp";
    })
});











