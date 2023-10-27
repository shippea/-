let i, img5, img5_text;
const img5_textA=["야시장","야경","관광지","문화재","카페"]


window.onload=function(){
    img5=document.querySelector(".img5").getElementsByTagName("li");
                                    // img5의 자식들도 읽어들임
    img5_text=document.querySelector(".img5_text").getElementsByTagName("li");
    // img5[0].innerHTML="홍길동"
    // img5[1].innerHTML="이동수"

    for(i=0; i<5; i++){
        img5[i].style.background="url(img/img"+ (i+1) + ".jpg)";
        // [] 배열은 무조건 0부터 시작
        img5[i].style.backgroundSize="100% 100%";
        img5_text[i].innerHTML = img5_textA[i];
    }
    let box=document.querySelector(".box");
   
    /*
    for(i=0; i<img5_textA.length; i++){     
        box.innerHTML+=img5_textA[i]+"<br>";
    
    }
    */
    
    let count=0;
    let ok=document.querySelector(".ok");
    ok.onclick=function(){
        while(count < img5_textA.length){   //length: 크기
            box.innerHTML += img5_textA[count] + "<br>";
            count++;
        }
    }
}