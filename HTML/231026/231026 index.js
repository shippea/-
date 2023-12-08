let i, k;
let str="";
let gu;
let box1, dan, dan2, dan3, dan4, dan5, dan6, num;


window.onload=function(){
    box1=document.querySelector(".box1");
    dan=document.getElementById("dan");
    dan2=document.getElementById("dan2");
    dan3=document.getElementById("dan3");
    dan4=document.getElementById("dan4");
    dan5=document.getElementById("dan5");
    dan6=document.getElementById("dan6");
    num=document.getElementById("num");

    /*
    for(i=1; i<=9; i++){
        str+=("<br>2 x " + i + " = " + (2*i));
        // str = 10 : str에 10으로 덮어쓰기
        // str += 10 : str에 10으로 누적 (숫자: 더하기, 문자: 나열)
        
    }
    box1.innerHTML = str;
    */

    // 2단 출력 버튼을 클릭하면 box1에 2단 출력
    // 바깥에 함수를 두고 연동시켜 실행하기

    
    dan2.addEventListener("click",dan2_func);


    // 구구단 전체 출력

    let all=document.querySelector(".all");
    all.addEventListener("click",all_func);
    function all_func(){
        str="";
        for(i=1; i<=9; i++){
            for(k=2; k<=9; k++){
                str += ("&nbsp; &nbsp; &nbsp;" + k + " x " + i + " = " + (k*i));
            }
            str += "<br>";
        }
        box1.innerHTML = str;
    }

    // 구구단 출력 버튼을 클릭하면 box1에 구구단 출력

    dan.addEventListener("click",function(){

        str="";
        gu=Number(num.value);
        
        for(i=1; i<=9; i++){
            str += ("<br>" + gu + " x " + i + " = " + (gu*i));
        }
        box1.innerHTML = str;
    })

}

// HTML의 onclick으로 외부함수 불러오기, js의 외부함수 불러오기
// HTML의 onclick으로 외부함수 불러오기는 window.onload 안에 두면 불러올 수 x

function func(js){
    if(js==2)
        dan2_func();
    else if(js==3)
        dan3_func();
    else if(js==4)
        dan4_func();
}

// onload 바깥에서 외부함수 불러오기
function dan2_func() {
    str="";
    for(i=1; i<=9; i++){
        str+=("<br>2 x " + i + " = " + (2*i));
    }
    box1.innerHTML = str;
}

// HTML의 onclick으로 외부함수 개별지정해서 불러오기
function dan3_func(){
    str="";
    for(i=1; i<=9; i++){
        str += ("<br>3 x " + i + " = " + (3*i));
    }
    box1.innerHTML = str;
}

function dan4_func(){
    str="";
    for(i=1; i<=9; i++){
        str += ("<br>4 x " + i + " = " + (4*i));
    }
    box1.innerHTML = str;
}

// HTML의 onclick으로 외부함수 변수로 지정해서 불러오기
function func_comm(js){
    str="";
    for(i=1; i<=9; i++){
        str += ("<br>" + js + " x " + i + " = " + (js*i));
    }
    box1.innerHTML = str;
}