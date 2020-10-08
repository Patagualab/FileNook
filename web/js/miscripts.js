/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function pswigual(){
    var a=document.getElementsByName("psw")[0].value;
    var b=document.getElementsByName("pswrepeat")[0].value;
    

    var espacios = false;
    var cont = 0;
               
    if(a !== b){
        alert("Los campos \"Contraseña\" y \"Repetir Contraseña\" deben ser iguales");
        return false;
    }
    while (!espacios && (cont < a.length)) {
    if (a.charAt(cont) === " ")
         espacios = true;
         cont++;
    }

    if (espacios) {
        alert ("La contraseña no puede contener espacios en blanco");
        return false;
    }
    if (a.length === 0 || b.length === 0) {
        alert("Los campos de la password no pueden quedar vacios");
        return false;
    }
    if (a.length <= 4) {
        alert("La contraseña debe tener mas de 4 dígitos");
        return false;
    }
    if (a.length >= 15) {
        alert("La contraseña debe tener menos de 15 dígitos");
        return false;
    }
                
}
