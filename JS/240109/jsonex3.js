    /* 실습 1/9 */
// https://jsonplaceholder.typicode.com/users
// 1. users 데이터를 가져와 리스팅한다
//   (id,name,username,email,phone,website)
// 2. 상단에 검색옵션 (아이디<id>, 이름<name>, 별칭<username>)으로
//      검색을 구현한다. (문자열 검색시는 정규표현식 사용)
// 3. 정렬옵션 (아이디 ASC/DESC, 이름ASC/DESC)


// 서버에서 가져온 JSON객체들을 저장할 배열
//  -> 서버 부하를 줄이기 위해 데이터를 한번만 가져와 
//     클라이언트에서 처리하고 렌더링하기 위해 사용
let dataArray = [];
let selType = '';

$(function() { 
    getData();
    // 정렬셀렉트가 변경되면 그때의 option의 value를
    // sortData 함수에 전달
    $("#sortType").on("change", function() {
        sortData($(this).val());
    });
    // 검색타입 (id, name, username)
    $("#selType").on("change", function() {
        selType = $(this).val();
    });
    // 검색버튼 누르면 검색타입을 선택했는지
    // 검색어를 입력했는지 if문으로 확인
    $("#srchBtn").on("click", function() {
        const selValue = $("#selValue").val();
        if (!selType) {
            alert('검색타입을 선택해 주세요!');
            return;
        }
        if (!selValue) {
            alert('검색어를 입력해 주세요!');
            return;
        }
        // 검색타입, 검색어 값들을 searchData에 던져줌
        searchData(selType, selValue);
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
};

// 3. 조건에 따른 정렬
const sortData = function (sortType) {
    // ---선택---인 경우를 제외시키기위해 if로 걸러줌
    if (sortType) {
        // id_DESC => _를 기준으로 id DESC로 나눠줌
        const sortProp = sortType.split("_")[0];
        const sortMethod = sortType.split("_")[1];

        // 오름차순일 때
        if ( sortMethod =='ASC') {
            dataArray.sort((a, b) => {
                if(Number.isInteger(a[sortProp])) {
                    // type이 id인 경우 숫자이므로
                    return a[sortProp] - b[sortProp];
                } else {
                    // type이 name인 경우 문자이므로
                    return a[sortProp].localeCompare(b[sortProp]);
                }
            });
        // 내림차순일 때
        } else {
            dataArray.sort((a, b) => {
                if(Number.isInteger(a[sortProp])) {
                    return b[sortProp] - a[sortProp];
                } else {
                    return b[sortProp].localeCompare(a[sortProp]);
                }
            });
        }

        // 다른방식
        // if (sortMethod == 'ASC') {
        //     dataArray.sort((a, b) => a[sortProp]>b[sortProp]? 1:-1);
        // } else {
        //     dataArray.sort((a, b) => b[sortProp]>a[sortProp]? 1:-1);
        // }
    }
    // 정렬결과를 printData에 던져줌
    printData(dataArray);
};

// 4. 검색
const searchData = function(selType, selValue) {
    // selValue: 사용자가 검색어로 입력한 문자열
    printData(dataArray.filter(user => {
      return new RegExp(selValue).test(String(user[selType]));
    }));
};
