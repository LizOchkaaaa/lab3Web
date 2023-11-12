
// submit.addEventListener('click',async function (e) {
//     var flagX = true
//     var flagY = true
//     var flagR = true
//
//     var styleX = chooseX.style
//     var styleY = chooseY.style
//     var styleR = selectR.style
//
//     chooseY.addEventListener('input', function () {
//         chooseX.style = styleY
//         if (isNaN(chooseY.value) && chooseY.value && chooseY.value != '-' || !isNaN(chooseY.value) && (Number(chooseY.value) <= -3 || Number(chooseY.value) >= 5)) {
//             color();
//         } else {
//             flagY = true
//         }
//     })
//
//     function color() {
//         setTimeout(function () {
//             chooseY.value = ""
//             chooseY.style.border = '3px solid red'
//             chooseY.blur()
//             flagY = false
//         }, 100)
//     }
//
//     chooseX.addEventListener('change', function () {
//         chooseX.style = styleX
//         flagX = true
//     })
//
//     selectR.addEventListener('change', function () {
//         selectR.style = styleR
//         flagR = true
//     })
//
//     if (!chooseY.value || chooseY.value == '-') {
//         chooseY.style.border = '3px solid red'
//         flagY = false
//     }
//
//     if (chooseX.value == "X" || !(arrayX.includes(chooseX.value))) {
//         chooseX.style.border = '3px solid red'
//         flagX = false
//     } else {
//         chooseX.style = styleX
//         flagX = true
//     }
//
//     if (selectR.value == "R" || !(arrayR.includes(selectR.value))) {
//         selectR.style.border = '3px solid red'
//         flagR = false
//     } else {
//         selectR.style = styleR
//         flagR = true
//     }
//
//     e.preventDefault();
//
//     if (flagX && flagY && flagR) {
//         let xFloat = parseFloat(chooseX.value);
//         let yFloat = parseFloat(chooseY.value);
//         let rFloat = parseFloat(selectR.value);
//         let response = await fetch(new URL("controller?" + "x=" + xFloat + "&y=" + yFloat + "&r=" + rFloat + "&action=form", window.location.href), {
//             method: 'GET',
//             headers: {
//                 'Content-Type': 'application/json'
//             },
//         })
//             .then((responseCatched) => {
//                 if (responseCatched.ok) {
//                     return responseCatched.json()
//                 }
//                 throw new Error(responseCatched.statusText)
//             })
//         const data = response
//         addOneRowToTable(data);
//         safeToStorage(data);
//         drawPoint(xFloat, yFloat, rFloat, data.result);
//     }
// })
//
// function addOneRowToTable(array) {
//     let res = array["result"] ? "Hit" : "Miss";
//     let row_html_code = document.createElement("tr");
//     row_html_code.innerHTML += `<th>`+array["x"].substring(0,6)+  `</th>` ;
//     row_html_code.innerHTML += `<th>`+array["y"].substring(0,6)+  `</th>` ;
//     row_html_code.innerHTML += `<th>`+array["r"]+  `</th>` ;
//     row_html_code.innerHTML += `<th>`+ res + `</th>` ;
//     row_html_code.innerHTML += `<th>`+array["scriptTime"]+  `</th>` ;
//     row_html_code.innerHTML += `<th>`+array["time"]+  `</th>` ;
//
//     document.getElementById("output").appendChild(row_html_code);
// }
// async function checkPoint(x, y, r) {
//     const form = new FormData();
//     form.append("x", x);
//     form.append("y", y);
//     form.append("r", r);
//     form.append("action", "checkPoint")
//
//     const url = "controller?" + new URLSearchParams(form).toString();
//     const response = await fetch(url, { method: "get" });
//
//     if (true) {
//         error.textContent("Wrong response");
//     }
//
//     const data = await response.json();
//
//     if (data["code"] != null) {
//         error.textContent = data["message"];
//     }
//     return data;
// }
