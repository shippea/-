    /* attribute */
function clickBtn1() {
    console.log("btn1 클릭!");
};

    /* property */
const btn2 = document.getElementById("btn2");
btn2.onclick = function() {
    console.log("btn2 클릭!");
};

    /* addEventListener -> 하나의 target에 여러 이벤트 생성 가능 */
const btn3 = document.getElementById("btn3");

// 1번 핸들러처럼 함수형식으로 만들어야 remove에 용이
const func1 = function(e) {
    console.log(e.type);
    console.log(e.target);
    console.log(e.currentTarget);
    console.log(e.eventPhase);
    console.log(e.bubbles);
    console.log(e.cancelable);
    console.log(e.defaultPrevented);
    console.log(e.isTrusted);
    console.log(e.timeStamp);
    console.log("btn3 클릭! 1번핸들러 호출");
}

btn3.addEventListener("click", func1)

btn3.addEventListener("click", function() {
    console.log("btn3 클릭! 2번 핸들러 호출");
});

// btn3.removeEventListener("click", func1);


    /* jquery */
$(function() {
    $("#btn4").on("click", function() {
        console.log("btn4 클릭!");
    });
});
