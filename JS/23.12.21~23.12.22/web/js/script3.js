window.onload = function(){
    var btn1 = document.getElementById("btn1");
    var ul;
    var cnt = 0;
    var outerDiv = document.getElementById("outer");
    btn1.onclick = function(){
        ul = document.createElement("ul");
        var ulid = document.createAttribute("ID");
        ul.setAttribute(ulid.nodeName,"id" + (++cnt));
        var li1 = document.createElement("li");
        var li1txt = document.createTextNode("사과");
        var li2 = document.createElement("li");
        var li2txt = document.createTextNode("딸기");
        var li3 = document.createElement("li");
        var li3txt = document.createTextNode("복숭아");
        li1.appendChild(li1txt);
        li2.appendChild(li2txt);
        li3.appendChild(li3txt);
        ul.appendChild(li1);
        ul.appendChild(li2);
        ul.appendChild(li3);
        outerDiv.appendChild(ul);
    }
    
    var btn2 = document.getElementById("btn2");
    btn2.onclick = function(){
        // outerDiv.removeChild(document.getElementById("id"+ (cnt--)));
        if(cnt>0){
            var ele = document.getElementById("id"+cnt);
            outerDiv.removeChild(ele);
            cnt = cnt - 1;
        }
        /*
        var ullast = outer.lastChild;
        outer.removeChild(ullast);
        */
    }
}