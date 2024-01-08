let cArray = [];
let ncArray = [];

$(function() {
    getData();
});


// 1. TODO 데이터를 가져온다

const getData = function() {
    $.get('https://jsonplaceholder.typicode.com/todos')
        .done(result => procData(result))
        .fail(() => console.log('getData error'))
};

// 2. 완료/미완료 분리
const procData = function(result) {
    cArray = result.filter(obj => obj.completed);
    ncArray = result.filter(obj => !obj.completed);
    printData(cArray, ncArray);
};

// 3. 화면에 출력
const printData = function() {
    cArray.forEach(obj => {
        $("#completed ul").append(`<li id='${obj.id}'>${obj.id} ${obj.title}
            <input type='button' class='cbtn' value='미완료' /></li>`);
    });
    

    ncArray.forEach(obj => {
        $("#notcompleted ul").append(`<li id='${obj.id}'>${obj.id} ${obj.title} 
            <input type='button' class='ncbtn' value='완료' /></li>`);
    });
    attachEvent();
};

// 4. 버튼에 이벤트핸들러 바인딩
const attachEvent = function() {
    $(`.cbtn`).on('click', function(){
        // this = cbtn class, parent = cbtn class의 li
        moveData($(this).parent().attr("id"));
    });
    $(`.ncbtn`).on('click', function(){
        moveData($(this).parent().attr("id"));
    });
};

// 5. 버튼누르면 완료/미완료 이동
const moveData = function(id) {

    printData();
};