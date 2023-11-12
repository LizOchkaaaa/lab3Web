window.onload = function() {
    setInterval(function() {
        // Seconds
        var seconds = new Date().getSeconds();
        document.getElementById("seconds").innerHTML = (seconds < 10 ? '0' : '') + seconds;

        // Minutes
        var minutes = new Date().getMinutes();
        document.getElementById("minutes").innerHTML = (minutes < 10 ? '0' : '') + minutes;

        // Hours
        var hours = new Date().getHours();
        document.getElementById("hours").innerHTML = (hours < 10 ? '0' : '') + hours;

        document.getElementById('current_date').innerHTML = date();
    }, 9000);
}

function date() {
    var current_date = new Date();
    var day = format(current_date.getDate());
    var month = format(current_date.getMonth()+1);
    var year = current_date.getFullYear();
    return day+"."+month+"."+year;
}
function format(value)
{
    if (value < 10)
    {
        value='0'+value;
    }
    return value;
}