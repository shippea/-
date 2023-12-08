let i;
const sec5A=[
    "img1.jpg","img2.jpg","img3.jpg","img4.jpg","img5.jpg"
]

const sec5_textA=[
    "먹거리 가득 야시장", "아름다운 야경", "화려한 건축물", "아름다운 문화재", "인스타감성 카페"
]
window.onload=function(){
    let sec5Box=document.querySelector(".sec5Box").getElementsByTagName("li");
    let sec5Box_text=document.querySelector(".sec5Box").getElementsByClassName("sec5Box_text");
    // getElementsByTagName으로 div 불러도 됨
    // getElemetsByClassName으로 Class 직접 부를시 .은 따로 표기 x
    for(i=0; i<sec5A.length; i++){
        sec5Box[i].style.background="url(img/"+sec5A[i]+")";
        sec5Box[i].style.backgroundSize="100% 100%";

        // 그림 밑에 설명넣기
        sec5Box_text[i].innerHTML = sec5_textA[i];
    }
}