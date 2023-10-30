const dataA={
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

let no, i, count;

window.onload=function(){

}

$(function(){
    $(".start").on("click",function(){
        // count=parseInt(Math.random()*100 + 1);    1~99 사이의 숫자를 생성하라는 의미
        count=parseInt(Math.random()*3 + 1);
        $(".wrap li").css("background", "white").css("color", "black");
        $(".box"+count).css("background","black").css("color","white"); // 선택 배경

        // no=콤보박스(음식종류 번호), count(종류-실제 음식 번호)
        if(no == 1){
            $(".pic").attr("src","img/lunch/" + dataA.korImg[count]);
            $(".text").text(dataA.korPrice[count]);
        }
        else if (no == 2){
            $(".pic").attr("src","img/lunch/" + dataA.bunImg[count]);
            $(".text").text(dataA.bunPrice[count]);
        }
        else if (no == 3){
            $(".pic").attr("src","img/lunch/" + dataA.chaImg[count]);
            $(".text").text(dataA.chaPrice[count]);
        }
        else if (no == 4){
            $(".pic").attr("src","img/lunch/" + dataA.eurImg[count]);
            $(".text").text(dataA.eurPrice[count]);
        }
    })

    // 다시하기 버튼을 클릭하면 count외 콤보박스(no)가 초기화
    $(".again").on("click",function(){
        count=0; no=0;
        $(".wrap li").text("")
                    .css("background", "white")
                    .css("color", "black");
    })
})

function func_ch(js){
    no=js.selectedIndex;
    $(".box0").text(dataA.kind[no] + "선택");

    kind_func(no);

    function kind_func(js){
        if(js == 1){
            for(i=1; i<dataA.kor.length; i++){
                $(".box"+i).text(dataA.kor[i]);
            }
        }
        else if(js == 2){
            for(i=1; i<dataA.kor.length; i++){
                $(".box"+i).text(dataA.bun[i]);
            }
        }
        else if(js == 3){
            for(i=1; i<dataA.kor.length; i++){
                $(".box"+i).text(dataA.cha[i]);
            }
        }
        else if(js == 4){
            for(i=1; i<dataA.kor.length; i++){
                $(".box"+i).text(dataA.eur[i]);
            }
        }
        
    }

}   