/*
var rootEle = document.getRootNode();
console.log(rootEle);
*/
var newPEle;
var newTextEle;
window.onload = function(){
    console.log("onload");
    var newDivEle = document.createElement('DIV');
    newPEle = document.createElement('p');
    newTextEle = document.createTextNode('Hello Dom!');
    newPEle.appendChild(newTextEle);
    newDivEle.appendChild(newPEle);
    // document.getElementsByTagName('body')[0].appendChild(newDivEle);
    document.body.appendChild(newDivEle);
}

function changeText(txt) {
    console.log("클릭");
    newPEle.innerHTML = txt;
    // newTextEle.nodeValue = txt;
}

function removeDiv(pid){
    var child = document.getElementById(pid);
    var parent = child.parentNode;
    parent.removeChild(child);
}

