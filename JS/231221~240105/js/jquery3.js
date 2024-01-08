$(function(){
    // 먼저 아이디로 상위에 접근하고 하위에 접근해야 확장성에 용이
    //      -> but 아이디는 상위에만 부여하는게 좋음 (div, ul, table)
    //             하위에는 class를 부여하여 한번에 특성 부여 (li, tr)
    // #table1 > tr: 직속하위 tr를 가리킴
  $("#table1 > tr:first > td:eq(2)")
    // $("#table1 > tr:first").first().childern().first().next()
});

// 실습1: 3을 탐색해서 콘솔에 출력
// 실습2: 메뉴2를 탐색해서 콘솔에 출력
// 실습3: 마지막 div내 p의 문자열을 콘솔에 출력