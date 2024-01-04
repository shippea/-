// // widow onload = function() {}
// $(function() {
//     // btn1.addEventListener('click', function(event)) {}
//     $('#btn1').on('click', function() {
//         $('#result').text($(this).attr('value')+' 버튼클릭됨');
//         $('#result').text($(this).val()+' 버튼클릭!');
//     });
// });

/* 
     # 실습1 (jquery.ver)
    버튼 중에서 클릭된 버튼의 아이디를 result에 출력

*/

$(function() {
    $('.btn').on('click', function(){
        $('#result').text($(this).val());
    })
});

/*
     # 실습2 (jquery.ver)
    합계를 눌렀을 때 두 값의 합이 result2에 나오게 출력

*/

/*
     # 실습3 (jquery.ver)
    텍스트 입력상자에 숫자 두개를 넣고 +,-,*,/ 버튼을 누르면
    result2에 결과 출력

*/