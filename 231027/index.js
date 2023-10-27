let btn1, btn2, btn3;
let btn11, btn22, btn33;
let btn111, btn222, btn333;
let str="";
let result1, result2, result3;
window.onload=function(){

    btn1=document.querySelector(".btn1");
    btn2=document.querySelector("#btn2");
    btn3=document.getElementById("btn3");
    btn11=document.querySelector(".btn11");
    btn22=document.querySelector(".btn22");
    btn33=document.querySelector(".btn33");
    btn111=document.querySelector(".btn111");
    btn222=document.querySelector(".btn222");
    btn333=document.querySelector(".btn333");
    result1=document.querySelector(".result1");
    result2=document.querySelector(".result2");
    result3=document.querySelector(".result3");

    
    btn1.onclick=function(){
        str = "아라비카는 부드럽고 향기가 있으며, 로브스타종에 비해 단맛, 신맛, 감칠맛, 향이 뛰어나 가격이 더 비싸고, 카페인 함유량이 로부스타보다 적습니다.(Arabica 1∼1.7%, Robusta 2∼2.5%). 아라비카종은 해발 900~2000m 이상의 고지대에서 생산되며, 기계를 이용한 대량 재배와 수확이 불가능하기 때문에 사람손으로 일일이 심고 가꾸고 거두어야 합니다."

        result1.innerHTML = str;
    }

    btn2.addEventListener("click",function(){
        str = "로부스타의 원산지는 아프리카 콩고로써 평지에서 기계로 재배하기 때문에 콩이 여문 정도나 크기, 결손 원두를 가리지 않고 모두 한꺼번에 가공합니다.";
        result1.innerHTML = str;
    })

    btn3.addEventListener("click",function(){
        str = "리베리카는 아프리카의 리베리아가 원산지인데 쓴맛이 강하고 향이 적어 전체 약1% 수준으로 생산량이 적습니다.";
        result1.innerHTML = str;
    })
    
    btn11.addEventListener("click",function(){
        str = "카페인이 들어가있지 않아요";
        result2.innerHTML = str;
    })

    btn22.addEventListener("click",function(){
        str = "얼음과 함께 갈아 시원해요";
        result2.innerHTML = str;
    })


    btn33.addEventListener("click",function(){
        str = "신선한 생과일이 들어갔어요";
        result2.innerHTML = str;
    })

    btn111.addEventListener("click",function(){
        str = "딸기딸기";
        result3.innerHTML = str;
    })

    btn222.addEventListener("click",function(){
        str = "수박수박";
        result3.innerHTML = str;
    })


    btn333.addEventListener("click",function(){
        str = "사과사과";
        result3.innerHTML = str;
    })

}