let i;
let Big_img5=[];
let sec5Box=[], sec5Box_text=[];
const sec2A=[
    "img1.jpg","img2.jpg","img3.jpg","img4.jpg","img5.jpg"
]
const sec5A=[
    "img1.jpg","img2.jpg","img3.jpg","img4.jpg","img5.jpg"
]

const sec5_textA=[
    "먹거리 가득 야시장", "아름다운 야경", "화려한 건축물", "아름다운 문화재", "인스타감성 카페"
]
window.onload=function(){

    // sec2
    Big_img5=document.querySelector(".Big_img5").getElementsByTagName("li");
    for(i=0; i<sec2A.length; i++){
        Big_img5[i].style.background="url(img/"+sec2A[i]+")";
        Big_img5[i].style.backgroundSize="100% 100%";
    }

    // sec5
    sec5Box=document.querySelector(".sec5Box").getElementsByTagName("li");
    sec5Box_text=document.querySelector(".sec5Box").getElementsByClassName("sec5Box_text");
    // getElementsByTagName으로 div 불러도 됨
    // getElemetsByClassName으로 Class 직접 부를시 .은 따로 표기 x
    for(i=0; i<sec5A.length; i++){
        sec5Box[i].style.background="url(img/"+sec5A[i]+")";
        sec5Box[i].style.backgroundSize="100% 100%";

        // 그림 밑에 설명넣기
        sec5Box_text[i].innerHTML = sec5_textA[i];
    }
}

// jquery를 통한 글
$(function(){
    $(".Big_next").on("click", function(){
        $(".Big_img5").animate({"left":"-=1000px"},500, function(){ // Big_img5를 왼쪽으로 -1000px만큼 0.5s 속도로 이동
            $(".Big_img5 li:first").appendTo(".Big_img5");  //첫번째 사진이 맨 뒤에 다시 이어서 오도록함
            $(".Big_img5").css("left","+=1000px"); // Big

        });
    })

    $(".Big_prev").on("click", function(){
        $(".Big_img5 li:last").prependTo(".Big_img5");
        $(".Big_img5").css("left","-=1000px");
        $(".Big_img5").animate({"left": "+=1000px"},500);
    })
})