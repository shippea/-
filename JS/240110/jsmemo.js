/* JSON 코딩평가 : 로컬스토리지를 활용한 메모장 만들기 */
// JS시험은 본 코딩평가로 대체합니다.
// 제출기한 : 1/12(일)
// 제출방법 : 완료 후 소스코드 압축하여 트렐로에 업로드
// jsmemo.html, jsmemo.js

// 화면 : 메모 등록화면, 메모 목록화면
// 기능
//   1. 메모리스트
//   2. 메모등록
//   3. 메모삭제
//   4. 메모수정
//   5. 메모상세
//   6. 메모검색(제목, 내용)

/* 새 메모 등록확인 시 제목, 내용을 담을 배열 */
const memoMap = new Map([]);

/* 메모의 제목만 리스트로 담을 배열 */
// map에 한번에 다 담고 제목 리스트는 key값만 뽑는 문법으로 할까?
const titleListSet = new Set([]);


$(function() {

    /* 검색 창에 제목 입력 후 제목리스트 배열에 그 값이
        존재하는지 확인 후 searchMemo에 제목 값을 넘겨줌 */
    const inputTitle = $("#searchTxt").val();

    $("#searchBtn").on("click", function() {
        if (titleListSet.has(inputTitle)) {
            searchMemo(inputTitle);
        } else {
            alert('제목을 다시 입력해주세요');
        }

        $("#edit").on("click", function() {
            editMemo(inputTitle);
            alert('수정되었습니다')
        })
        $("#delete").on("click", function() {
            deleteMemo(inputTitle);
            alert('삭제되었습니다')
        })
    });
    registMemo();
});


/* 1. 메모리스트 */
const memoList = function() {
    // 메모 늘어날 수록 li 어떻게 추가할까?
    

}

/* 2. 메모등록 - 등록버튼 클릭 시 */
const registMemo = function() {
    $("#bottom").on("click", function() {
        $("#regist").show();
                
        let newTitle = $("#subject").val();
        let newContent = $("#content").val();

        /* 등록버튼 클릭시 나오는 화면에서 취소 버튼 클릭 시 */
        $("#cancel").on("click", function() {
            $("#regist").hide();
        });

        /* 등록버튼 클릭시 나오는 화면에서 확인 버튼 클릭 시 */
        $("#confirm").on("click", function() {
            $("#regist").hide();
            memoMap.set(newTitle, newContent);
            titleListSet.add(newTitle);
        });
    });
}

/* 3. 메모검색 - 제목 입력후 검색 버튼 클릭 시 */
const searchMemo = function(inputTitle) {
    const text = memoMap.get(inputTitle);
    $("#detailcont").append(
        text
    );
}

/* 수정 버튼 클릭 시 메모 수정 */
const editMemo = function(inputTitle) {
    const editContent = $("#detailcont").val();
    memoMap.set(inputTitle,editContent);
}

/* 삭제 버튼 클릭 시 메모 삭제 */
const deleteMemo = function(inputTitle) {
    memoMap.delete(inputTitle);
}