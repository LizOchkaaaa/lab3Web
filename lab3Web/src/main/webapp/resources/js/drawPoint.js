const error = document.getElementById("errorText")
const svg = document.getElementById("svg");
let arrayX = ["-3" , "-2" , "-1" , "0" , "1" , "2" , "3" , "4" , "5"]
let arrayR = ["1" , "1.5" , "2" , "2.5" , "3"]
let pointX;
let pointY;
let pointR;
let pointId = 0;
let pointResult;
let circles = [];
const points = new Map();
let chooseY = document.getElementById("choose-form:y-select");

function setX(e){
    document.querySelectorAll('#selectX a').forEach(function (element) {
        element.className = '';
    })
    e.className = 'selected';
}
function safeToStorage(data){
    pointX = localStorage.getItem("arrayX");
    pointY = localStorage.getItem("arrayY");
    pointR = localStorage.getItem("arrayR");
    pointResult = localStorage.getItem("arrayResult");
    if (pointX == null){
        pointX = "";
        pointY = "";
        pointR = "";
        pointResult = "";

    }
    pointX += data["x"] + " ";
    pointY += data["y"] + " ";
    pointR += data["r"] + " ";
    pointResult += data["result"] + " ";
    localStorage.setItem("arrayX", pointX);
    localStorage.setItem("arrayY", pointY);
    localStorage.setItem("arrayR", pointR);
    localStorage.setItem("arrayResult", pointResult);
}
function removeCircles(){
    for(let i = 0; i < circles.length; i++){
        svg.removeChild(circles[i]);
    }
    circles = [];
}
function drawPointsByR(r){
    removeCircles();
    points.forEach((v,k) =>{
        drawPointById(v.x, v.y, r, v.result, k);
    })


}

function drawPoint(x, y, r, result) {
    result = result.toString();
    points.set('point' + pointId, {x, y, r, result});
    pointId+=1;
    [x, y] = untransforme(x, y, r);
    let circle = document.createElementNS("http://www.w3.org/2000/svg", "circle");
    circle.setAttribute("cx", x);
    circle.setAttribute("cy", y);
    circle.setAttribute("r", "6");
    if (result === "true" )
        circle.style.fill = "#09a53d";
    else
        circle.style.fill = "#cdc684";
    circles.push(circle);
    svg.appendChild(circle);
}
function getCurrentR() {
    return document.getElementById("choose-form:r-select").value;
}
function drawPointById(x, y, r, result, point_id){
    [x, y] = untransforme(x, y, r);
    let circle = document.createElementNS("http://www.w3.org/2000/svg", "circle");
    circle.setAttribute("cx", x);
    circle.setAttribute("cy", y);
    circle.setAttribute("r", "6");
    if (result === "true")
        circle.style.fill = "#09a53d";
    else
        circle.style.fill = "#cdc684";
    circles.push(circle);
    svg.appendChild(circle);
}
async function sendCoordinatesToServer(x, y, r) {
    const data = await checkPoint(x, y, r);

    if (data["code"] == null) {
        safeToStorage(data);
        drawPoint(x, y, r, data.result);
        addOneRowToTable(data);
    }
    else {
        error.textContent = data["message"];
        setTimeout(() =>{
            error.textContent = ""
        }, 2000);
    }
}

document.addEventListener("DOMContentLoaded", () => {
    let pointX = localStorage.getItem("arrayX");
    let pointY  = localStorage.getItem("arrayY");
    let pointR = localStorage.getItem("arrayR");
    let pointResult = localStorage.getItem("arrayResult");
    if (pointX != null && pointY != null && pointR != null && pointResult != null) {
        pointX = pointX.split(" ");
        pointY = pointY.split(" ");
        pointR = pointR.split(" ");
        pointResult  = pointResult.split(" ");
        for (let i = 0; i < pointX.length; i++) {
            const x = parseFloat(pointX[i]);
            const y = parseFloat(pointY[i]);
            const r = parseFloat(pointR[i]);
            if (isNaN(x) || isNaN(y) || isNaN(r)) continue;

            let result = pointResult[i] === "true";
            drawPoint(x, y, r, result);
        }
    }

    svg.addEventListener("click", (event) => {
        if ((arrayR.includes(getCurrentR()))) {
            const  rect = svg.getBoundingClientRect();
            let x = event.clientX - rect.left;
            let y = event.clientY - rect.top;
            let coord = transform(x, y, getCurrentR());
            x = coord[0];
            y = coord[1];
            document.getElementById("choose-form:r-select").style.border = '1px solid black';
            document.getElementById("graphSelect:graph-x").value = x;
            document.getElementById("graphSelect:graph-y").value = y;
            updateBeanValues();

        }
        else {
            document.getElementById("choose-form:r-select").style.border = '3px solid red';
        }
    })
});

function transform(x, y , r){
    let scale = r;
    let start = 150;
    let rPosition = 100;
    x = (x - start)/rPosition * scale;
    y = -(y - start)/rPosition * scale;
    return [x, y];
}
function untransforme(x, y, r){
    let scale = r;
    let start = 150;
    let rPosition = 100;
    x = x * rPosition / scale + start;
    y = -y * rPosition / scale + start;
    return [x.toFixed(3), y.toFixed(3)];
}
chooseY.oninput = function () {
    chooseY.style.border = "1 px solid black";
    if (!(isNaN(chooseY.value) && chooseY.value && chooseY.value != '-' || !isNaN(chooseY.value) && (Number(chooseY.value) <= -3 || Number(chooseY.value) >= 5))) {
        chooseY.style.border = "3 px solid red";
    }
}