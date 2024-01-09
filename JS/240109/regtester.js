// 1. 패턴읽기
// 2. 플래그 선택
// 3. 문자열 읽기
// 4. 결과확인 버튼 클릭시 결과값 출력

$(function() {

    $("#pattern").on("keydown", function() {
        let flagStr = '';
        $("input.flag:checked").toArray().forEach(inEle => {
            const eleVal = $(inEle).attr('value');
            if (eleVal!='test' && eleVal!='match') {
                flagStr += $(inEle).attr('value'); 
            }
        });
        $("input.method:checked").toArray().forEach(inEle => {
            const eleVal = $(inEle).attr('value');
            if (eleVal=='test') printTest(flagStr);
            if (eleVal=='match') printMatch(flagStr);
        });
    });
});

function printTest(flagStr) {
    console.log($("#str").val());
    $("#result").text(
        new RegExp($("#pattern").val(), flagStr)
            .test($("#str").val()));
}

function printMatch(flagStr) {
    $("#result2").text(
        $("#str").val().match(
            new RegExp($("#pattern").val(), flagStr)
        )
    );
}

// $(function() {
//     $("#btn").on("click", function() {
//         // 플래그 문자들을 저장할 변수
//         let flagStr = '';
//         // 체크된 체크박스들을 배열로 만들어서
//         // 하나씩 꺼내 플래그문자열을 생성
//         $("input.flag:checked").toArray().forEach(inEle => {
//             const eleVal = $(inEle).attr('value');
//             if (eleVal!='test' && eleVal!='match') {
//                 flagStr += $(inEle).attr('value'); 
//             }
//         });
//         $("input.method:checked").toArray().forEach(inEle => {
//             const eleVal = $(inEle).attr('value');
//             if (eleVal=='test') printTest(flagStr);
//             if (eleVal=='match') printMatch(flagStr);
//         });
//     });
// });

// function printTest(flagStr) {
//     $("#result").text(
//         new RegExp($("#pattern").val(), flagStr)
//             .test($("#str").val()));
// }

// function printMatch(flagStr) {
//     $("#result2").text(
//         $("#str").val().match(
//             new RegExp($("#pattern").val(), flagStr)));
// }