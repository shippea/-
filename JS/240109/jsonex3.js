    /* 실습 1/9 */
// https://jsonplaceholder.typicode.com/users
// 1. users 데이터를 가져와 리스팅한다
//   (id,name,username,email,phone,website)
// 2. 상단에 검색옵션 (아이디<id>, 이름<name>, 별칭<username>)으로
//      검색을 구현한다. (문자열 검색시는 정규표현식 사용)
// 3. 정렬옵션 (아이디 ASC/DESC, 이름ASC/DESC)

let dataArray = [];

$(function() {

    getData();

    $("#sortType").on("change", function() { 
        sortData($(this).val());
    });
});

// 1. users 데이터를 가져오기
const getData = function() {
    $.get('https://jsonplaceholder.typicode.com/users')
        .done(function(result) {
            dataArray = result;
            printData(result);
        }) 
        .fail(() => console.log('getData error'))
};

// 2. 데이터 화면에 출력
const printData = function(result) {
    $("tbody").empty();
    result.forEach(user => {
        $("tbody").append(`
        <tr>
            <td>${user.id}</td>
            <td>${user.name}</td>
            <td>${user.username}</td>
            <td>${user.phone}</td>
            <td>${user.email}</td>
            <td>${user.website}</td>
        </tr>
        `);
    });
    // attachEvent();
};

// 3. 버튼에 이벤트핸들러 바인딩
const attachEvent = function() {
    $("#srchBtn").on("click", function() {
        selectType();
        sortData();
    })
}

// 4. 검색type 설정
const selectType = function() {
    $("#selType.option:selected").each(inEle => {
        const eleVal = $(inEle).attr('value');
        if (eleVal == 'id') checkIdType();
        if (eleVal == 'name') checkNameType();
        if (eleVal == 'username') checkUsernameType();
    });
};

// 5-1. Id type 검색
const checkIdType = function() {
    const reId = /[^0-9]/g;
    if (reId.test($("#selValue").val()) == true) ;
};


// 3. 검색 구현
const searchData = function() {

}

// 4. 버튼 기능

// 5. 정렬
const sortData = function (sortType) {
    
    if (sortType) {
        const sortProp = sortType.split("_")[0];
        const sortMethod = sortType.split("_")[1];
        console.log(sortProp);
        console.log(sortMethod);

        if (sortMethod == 'ASC') {
            dataArray.sort((a, b) => a[sortProp]>b[sortProp]? 1:-1);
        } else {
            dataArray.sort((a, b) => b[sortProp]>a[sortProp]? 1:-1);
        }
    }
    printData(dataArray);
};