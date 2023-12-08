let i;

window.onload=function(){

    let btn1=document.getElementById("btn1");
    let num1=document.getElementById("num1");
    let result1=document.getElementById("result1");

    btn1.onclick=function(){
        i = Number(num1.value);

        if(i%2 == 0){
            result1.innerHTML=("짝수");
        }

        else{
            result1.innerHTML=("홀수");
        }


    }


    let btn2=document.getElementById("btn2");
    let num2=document.getElementById("num2");
    let result2=document.querySelector("#result2");

    btn2.addEventListener("click", function(){  // addEventListener: onclick보다 실무에서 사용률 더 높음
        i = Number(num2.value);

        if(i%3==0){
            result2.innerHTML=("3의 배수 맞음");
        }

        else{
            result2.innerHTML=("3의 배수 아님");
        }
        
    })

    let btn3=document.getElementById("btn3");
    let num3=document.getElementById("num3");
    let result3=document.querySelector("#result3");

    btn3.addEventListener("click",function(){
        i = Number(num3.value);

        if(i>=20){
            result3.innerHTML=("성인");
        }
        else{
            result3.innerHTML=("미성년자");
       }
    })

    let btn4=document.getElementById("btn4");
    let num4=document.getElementById("num4");
    let result4=document.querySelector("#result4");

    btn4.addEventListener("click",function(){
        i = Number(num4.value);

        if(i>=80){
            result4.innerHTML=("합격");
        }
        else{
            result4.innerHTML=("불합격");
        }
    })

    // switch 사용한 예제

    let btn5=document.getElementById("btn5");
    let num5=document.getElementById("num5");
    let result5=document.querySelector("#result5");

    btn5.addEventListener("click",function(){
        let hak;
        i = Number(num5.value);
        i = parseInt(i/10);     // 87/10=8.7

        switch(i){
            case 10: hak="A"; break;
            case  9: hak="A"; break;
            case  8: hak="B"; break;
            case  7: hak="C"; break;
            case  6: hak="D"; break;
            default: hak="F"; break;
        }

        result5.innerHTML= hak;

        /*
        if(i>=90){
            result5.innerHTML=("A");
        }
        else if(i>=70){
            result5.innerHTML=("B");
        }
        else if(i>=50){
            result5.innerHTML=("C");
        }
        else if(i>=30){
            result5.innerHTML=("D");
        }
        else{
            result5.innerHTML=("F");
        }
        */
    })
    
}