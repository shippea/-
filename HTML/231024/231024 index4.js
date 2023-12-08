let count=1;
let pic, prev, next, disp;

window.onload=function(){
    let pic=document.getElementById("pic");
    let disp=document.querySelector(".disp");
    let prev=document.querySelector(".prev");
    let next=document.querySelector(".next");

    prev.onclick=function(){
        if(count>1){
            count--;
            pic.src="img/img" + count + ".jpg";
            console.log("img/img" + count + ".jpg");
            disp.innerHTML=count + " / 5";        
        }
    }

    next.onclick=function(){
        if(count<5){
            count++;
            pic.src="img/img"+ count + ".jpg";
            disp.innerHTML=count + " / 5";            
        }
    }
}