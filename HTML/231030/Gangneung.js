let i;

// 갤러리메뉴 sec5 변수선언

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

// 점심메뉴 sec3 변수선언 

const sec3_dataA={
    kind:["","한식", "분식", "중식", "양식", "기타"],

    kor:["1","김치찌개","순두부찌개","비빔밥"],
    korImg:["","img01.jfif","img02.jfif","img03.jfif"],
    korPrice:[0, "8000원", "8500원", "9000원"],

    bun:["2","라면","김밥","국수"],
    bunImg:["","img04.jfif","img05.jfif","img06.jfif"],
    bunPrice:[0, "3000원", "3500원", "7000원"],

    cha:["3","짜장면","짬뽕","볶음밥"],
    chaImg:["","img07.jfif","img09.jfif","img09.jfif"],
    chaPrice:[0, "8000원", "8500원", "9000원"],

    eur:["4","돈까스","함박스테이크","생선까스"],
    eurImg:["","img010.jfif","img011.jfif","img012.jfif"],
    eurPrice:[0, "8500원", "9500원", "10000원"]
};

let no, count;


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

    // 문화메뉴 sec2 js

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


    // 점심메뉴 sec3 js

    $(".sec3_start").on("click",function(){
        // count=parseInt(Math.random()*100 + 1);    1~99 사이의 숫자를 생성하라는 의미
        count=parseInt(Math.random()*3 + 1);
        $(".wrap3 li").css("background", "white").css("color", "black");
        $(".sec3_box"+count).css("background","black").css("color","white"); // 선택 배경

        // no=콤보박스(음식종류 번호), count(종류-실제 음식 번호)
        if(no == 1){
            $("sec3_.pic").attr("src","img/lunch/" + sec3_dataA.korImg[count]);
            $("sec3_.text").text(sec3_dataA.korPrice[count]);
        }
        else if (no == 2){
            $("sec3_.pic").attr("src","img/lunch/" + sec3_dataA.bunImg[count]);
            $("sec3_.text").text(sec3_dataA.bunPrice[count]);
        }
        else if (no == 3){
            $("sec3_.pic").attr("src","img/lunch/" + sec3_dataA.chaImg[count]);
            $(".text").text(sec3_dataA.chaPrice[count]);
        }
        else if (no == 4){
            $("sec3_.pic").attr("src","img/lunch/" + sec3_dataA.eurImg[count]);
            $("sec3_.text").text(sec3_dataA.eurPrice[count]);
        }
    })

    // 다시하기 버튼을 클릭하면 count외 콤보박스(no)가 초기화
    $(".sec3_again").on("click",function(){
        count=0; no=0;
        $(".wrap3 li").text("")
                    .css("background", "white")
                    .css("color", "black");
    })

          

    // jquery에서 손가락 보이게 css 설정
    $(".sec6_box1_img").css("cursor","pointer");

    // sec6 효성 화면에서 그림 hover 하면 색상 나오게하기

    $(".sec6_box1_img1").on("click",function(){
        func_sec6(1);
    })
    $(".sec6_box1_img2").on("click",function(){
        func_sec6(2);
    })
    $(".sec6_box1_img3").on("click",function(){
        func_sec6(3);
    })
    $(".sec6_box1_img4").on("click",function(){
        func_sec6(4);
    })
 
    function func_sec6(js){
        $(".sec6_box1_child"+js).fadeIn();
    }

    $(".sec6_box1_child1").on("mouseout",function(){
        func_sec6_child(1);  
    })
    $(".sec6_box1_child2").on("mouseout",function(){
        func_sec6_child(2);  
    })
    $(".sec6_box1_child3").on("mouseout",function(){
        func_sec6_child(3);  
    })
    $(".sec6_box1_child4").on("mouseout",function(){
        func_sec6_child(4);  
    })

    function func_sec6_child(js){
        $(".sec6_box1_child"+js).fadeOut();
    }        
/*   
    $(".sec6_box1_img1").on("click",function(){
        $(".sec6_box1_child1").fadeIn();
    })
    $(".sec6_box1_child1").on("mouseout",function(){
        $(".sec6_box1_child1").fadeOut();
    })

    $(".sec6_box1_img2").on("click",function(){
        $(".sec6_box1_child2").fadeIn();
    })
    $(".sec6_box1_child2").on("mouseout",function(){
        $(".sec6_box1_child2").fadeOut();
    })

    $(".sec6_box1_img3").on("click",function(){
        $(".sec6_box1_child3").fadeIn();
    })
    $(".sec6_box1_child3").on("mouseout",function(){
        $(".sec6_box1_child3").fadeOut();
    })

    $(".sec6_box1_img4").on("click",function(){
        $(".sec6_box1_child4").fadeIn();
    })
    $(".sec6_box1_child4").on("mouseout",function(){
        $(".sec6_box1_child4").fadeOut();
    })
*/

})  


// 점심메뉴 sec3 js

function func_ch(js){
    no=js.selectedIndex;
    $(".sec3_box0").text(sec3_dataA.kind[no] + "선택");

    kind_func(no);

    function kind_func(js){
        if(js == 1){
            for(i=1; i<sec3_dataA.kor.length; i++){
                $(".sec3_box"+i).text(sec3_dataA.kor[i]);
            }
        }
        else if(js == 2){
            for(i=1; i<sec3_dataA.kor.length; i++){
                $(".sec3_box"+i).text(sec3_dataA.bun[i]);
            }
        }
        else if(js == 3){
            for(i=1; i<sec3_dataA.kor.length; i++){
                $(".sec3_box"+i).text(sec3_dataA.cha[i]);
            }
        }
        else if(js == 4){
            for(i=1; i<sec3_dataA.kor.length; i++){
                $(".sec3_box"+i).text(sec3_dataA.eur[i]);
            }
        }
        
    }

}