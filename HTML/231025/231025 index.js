let i, str="";
let sum=0, even=0, odd=0;

window.onload=function(){

    /*
    for(i=0; i<=5; i++)  //i++ 뒤에는 ; 넣지 x
        document.write("<br>자동차-" + i);
        document.write(": 점검 완료");
    */

    /*
    for(i=0; i<5; i++){
        str += "자동차-" + i + "<br>";   // str = str + a는 str += a
    }                                    // 문자열에서 +는 잇는 역할    }
    document.write(str);    
    */


    /*
    // 1~100까지의 합을 구하라
    for(i=0; i<=100; i++){
        sum += i;
    }
    document.write("1~100까지 합: " + sum);
    */


    /*
    // 1~100까지 짝수, 홀수의 합을 구하라
    for(i=0; i<=100; i++){
        if(i%2 == 0)
            even += i;
        else
            odd += i;
    }
    document.write("<br> 1~100까지 짝수의 합: " + even);
    document.write("<br> 1~100까지 홀수의 합: " + odd);
    */


    // 5단 구구단을 출력하라
    let box=document.querySelector(".box");
    let ok=document.querySelector(".ok");
    let ok2=document.querySelector(".ok2");
    let dan=document.querySelector(".dan");
    let str2="";
    let gu;

    for(i=1; i<=9; i++){
        // document.write("<br>5 x " + i + " = " + 3*i);
        str += "<br>5 x " + i + " = " + 3*i;
    }
    // box.innerHTML = str;
    ok.onclick=function(){
        box.innerHTML = str;
    }

    
    // 구구단 표를 만들자

    ok2.onclick=function(){
        str2=""; //for문 끝나고 다시 공백을 줘야 다른 값을 입력했을 때 새로 시작 가능
        gu = parseInt(dan.value);
        gu = Number(dan.value);     //Number, parseInt 둘다 사용 가능
        
        if(gu>1 && gu<=9){
            for(i=1; i<=9; i++){
                str2 += ("<br>" + gu + " x " + i + " = " + gu*i);
            }
        
            box.innerHTML = str2;
    
        }
        
    }

}