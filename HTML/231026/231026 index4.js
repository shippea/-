const menuA = [    
    "딸기주스", "아이스크림", "아이스 아메리카노", 
    "디저트", "망고주스", "팥빙수", 
];
const imgA = [
    "img1.jpg", "img2.jpg", "img3.jpg", "img4.jpg", 
    "img5.jpg", "img6.jpg",
];
const textA = [
    "딸기는 여름 과일 아니고 겨울 과일 입니다.",
    "아이스크림을 많이 먹으면 ...",
    "33","44", "55", "66"
];

const priceA =[
    0, 100, 200, 300, 400, 500, 600
]; 
const calA = [
    0, 10, 15, 22, 35, 17, 85
]

let count=0;

window.onload=function() {
    
    for(i=0; i<menuA.length; i++){
        $(".menu #menu"+ (i+1)).text(menuA[i]);
    }
    
}

$(function(){
    $(".menu li").on("click",function(){
        no=$(this).index();    //this: 클릭한 li가 내가 선택한 li라고 인식
        count=no+1;
        
        $("#pic").attr("src","img/"+imgA[no]);
        $("#text1").text("상품명: " + menuA[count-1]);
        $("#text2").text("가격: " + priceA[count-1]);
        $("#text3").text("영양: " + textA[count-1]);
    })

    $(".prevB").on("click",function(){
        if(count>1){
            count--;
        }
        else{
            count=6;
        }
        $(".disp").text( count + " / 6");
        $("#pic").attr("src","img/" + imgA[count-1]);        // 그림 넣는 함수 attr
        $("#text1").text("상품명: " + menuA[count-1]);
        $("#text2").text("가격: " + priceA[count-1]);
        $("#text3").text("영양: " + textA[count-1]);
    })

    $(".nextB").on("click",function(){
        if(count<6){
            count++;
        }
        else{
            count=1;
        }
        // n / 6 번호 바꾸기
            $(".disp").text( count + " / 6");
        // 번호에 해당하는 그림 바꾸기
        $("#pic").attr("src","img/" + imgA[count-1]);
        // 번호,그림에 해당하는 설명 넣기
        $("#text1").text("상품명: " + menuA[count-1]);
        $("#text2").text("가격: " + priceA[count-1]);
        $("#text3").text("영양: " + textA[count-1]);
    })
})