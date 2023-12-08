window.onload=function(){
    // window: 가장 높은 개체
    // window.onload=function: 읽어들인 것을 바로 실행

/*
    var a=10;
    var b, c;
    b=20; c=a+b;
    document.write(c);
*/


/*
var a;
a=10;
a=홍길동;

// var은 어떤 값이든 저장가능
let b;
b=10;
b=20;
b=홍길동;

// let은 같은 타입끼리만 저장가능 (숫자는 숫자, 단어는 단어끼리)

const c=10;
c=20;

// const는 바꿀 수 없는 변수 (상수)
*/


/*
let a, b, c;
let d=10, e=20; // 초기화: 값을 처음부터 새로 지정
c=d+e;
console.log(c); // console창에 결과값 제공 - document에 제공하기전에 확인하는 용도
document.write(c); // document에 결과값 제공
document.write("<br>"+c);
document.write("<br> 10+20 = "+ c + "입니다");

let kbs=10, sbs=20;
// kbs=kbs+sbs; 
kbs+=sbs; // 축양형 (kbs=kbs+sbs와 동일) ㅡ 누적연산때 사용
document.write("<br>kbs 값 :" + kbs);
*/

/*
let a=1;
a+=1; // a=a+1과 동일
document.write("<br> " + a);
a+=1;
document.write("<br> " + a);
a+=1;
document.write("<br> " + a);
a++; // 증가 연산자
document.write("<br> "+ a);

document.write("<br> "+ a++); // 결과값 전송 후 ++ 적용됨 (후위 증가연산자)
document.write("<br >" + ++a); // 결과값 전송 전에 ++ 적용됨 (전위 증가연산자)
*/

/*
let k, e, m, s, a;
k=50, e=60, m=90;
s=k+e+m;
a=s/3;
// document.write("국어 :" + k + "<br>영어 : " + e + "<br>수학 : " + m);
// document.write("<br> 평균: " + avg.toFixed(2) + "<br> 총합: " + sum); // toFixed(n): 소수점 n번째까지 표시

// kor.innerHTML=k;

let kor, eng, mat, sum, avg;
kor=document.getElementById("kor");
eng=document.getElementById("eng");
mat=document.getElementById("mat");
sum=document.getElementById("sum");
avg=document.getElementById("avg");

kor.innerHTML = k;
eng.innerHTML = e;
mat.innerHTML = m;
sum.innerHTML = s;
avg.innerHTML = a.toFixed(2);

// 바로 id에 대입하면 실무에서는 오류발생확률 큼
// 변수로 주고 속성을 통해 id에 대입해야 오류확률 적음
*/


let kor, eng, mat, sum, avg, ok;

kor=document.getElementById("kor");
// =왼쪽 kor은 js에서 지정한 kor 변수
eng=document.getElementById("eng");
mat=document.getElementById("mat");
sum=document.getElementById("sum");
avg=document.getElementById("avg");

ok=document.getElementById("ok");
let box=document.querySelector(".box");


let k, e, m, s, a;
ok.onclick=function(){
    k=parseInt(kor.value); 
/* input은 형변환이 없으면 문자로 인식
    -> parseInt를 사용해 정수로 형변환 */
    e=parseInt(eng.value);
    m=parseInt(mat.value);
    s=k+e+m;
    a=parseInt(s/3);
    // 소수점 없애기 위해 parseInt 사용
    sum.innerHTML = s;
    //sum은 js에서 지정한 sum 변수
    avg.innerHTML = a;

    box.style.display="block";
}

let again=document.getElementById("again");
again.onclick=function(){
    kor.value="";
    eng.value="";
    mat.value="";
    sum.innerHTML="";
    avg.innerHTML="";
    kor.focus(); 
    // 커서를 kor에 다시 가져다줌
}

let boxClose=document.querySelector(".boxClose");
boxClose.onclick=function(){
    box.style.display="none";    
}

}