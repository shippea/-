// window.onload = function() {
//     const btn1 = document.getElementById('btn1');
//     btn1.addEventListener('click', function(event) {
//         const result = document.getElementById('result');
//         result.innerHTML = this.value + ' 버튼이 클릭됨!';
//     });
// };


/* 
     # 실습1 (dom.ver)
    버튼 중에서 클릭된 버튼의 아이디를 result에 출력

*/

window.onload = function() {
    const btns = document.getElementsByClassName('btn');
    const length = btns.length;
    const result = document.getElementById('result');
    for (btn of btns) {
        btn.addEventListener("click", function(){
            result.innerHTML = this.id;
        });
    }
};

/*
     # 실습2 (dom.ver)
    합계를 눌렀을 때 두 값의 합이 result2에 나오게 출력

*/

window.onload = function() {
    const btnplus = document.getElementById('btnplus');
    const txts = document.getElementsByClassName('txt');
    const result2 = document.getElementById('result2');
    let sum;

    for (txt of txts) {
        sum += txt.value;
        console.log(sum);
    }

    btnplus.addEventListener('click', function(){
        result2.innerHTML = sum;
    });
};


/*
     # 실습3 (dom.ver)
    텍스트 입력상자에 숫자 두개를 넣고 +,-,*,/ 버튼을 누르면
    result2에 결과 출력

*/