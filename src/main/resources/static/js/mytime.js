function displayTime() {
    var date = new Date();
    var getYear = date.getFullYear();
    var getMonth = date.getMonth() + 1;
    var getDate = date.getDate();
    var getHours = date.getHours();
    var getMinutes = date.getMinutes();
    var getSeconds = date.getSeconds();

    if (getMonth < 10) {
        getMonth = "0" + getMonth;
    }

    if (getDate < 10) {
        getDate = "0" + getDate;
    }

    if (getHours < 10) {
        getHours = "0" + getHours;
    }

    if (getMinutes < 10) {
        getMinutes = "0" + getMinutes;
    }

    if (getSeconds < 10) {
        getSeconds = "0" + getSeconds;
    }

    var times = getYear + "-" + getMonth + "-" + getDate + " " + getHours + ":" + getMinutes + ":" + getSeconds;

    $('#nowTime').html(times);
    // setTimeout("displayTime()", 1000);

}

window.onload = function() {

    displayTime();
}