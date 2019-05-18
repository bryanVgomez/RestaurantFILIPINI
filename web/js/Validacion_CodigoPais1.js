const btn = document.querySelector("button");
const output = document.querySelector("#output");
const intake = document.querySelector("input");
const url = "https://restcountries.eu/rest/v2/all";
let myData = {};
fetch(url).then(function (res) {
    return res.json()
}).then(function (data) {
    myData = data;
    buildSelect(data);
})

function buildSelect(d) {
    let select = document.createElement('select');
    d.forEach(function (item, index) {
        let option = document.createElement('option');
        
        option.value = index;
        option.textContent = "+"+item.callingCodes;
        select.appendChild(option);
    })
    
    document.querySelector("div[id='codigo']").appendChild(select);
}




//cargar motivos
function myOnLoad() {
 cargar_motivos()
}

// funcion para Cargar motivos al campo <select>
function cargar_motivos() {
 var array = ["Mensaje a ventas", "Mensaje a soporte", "Queja","Felicitaciones"];

 // Ordena el Array Alfabeticamente:
 array.sort();

 addOptions("Motivo", array);
}

// Rutina para agregar opciones a un <select>
function addOptions(domElement, array) {
 var select = document.getElementsByName(domElement)[0];

 for (value in array) {
  var option = document.createElement("option");
  option.text = array[value];
  select.add(option);
 }
    
    
    
}



