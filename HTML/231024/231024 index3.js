let count=0; // 맨위에 지정하면 전역변수


window.onload=function(){
    let ok=document.querySelector(".ok");
    let disp=document.querySelector(".disp");
    let sub=document.querySelector(".sub");
    let pic=document.getElementById("pic");

    ok.onclick=function(){
        count++;
        console.log(count); // console창에 결과값 제공
        disp.innerHTML=count; 
        // let a=document.~("b") ㅡ js의 a = html의 b
        // a.innerHTML=c ㅡ c = js a값을 html의 b에 제공 
        pic.src="img/강릉카페"+ count + ".jpg"
    }

    sub.onclick=function(){
        count--;
        disp.innerHTML=count;
        pic.src="img/img"+ count + ".jpg"

    }




}