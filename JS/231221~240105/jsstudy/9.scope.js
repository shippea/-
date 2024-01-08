    /* Scope (스코프) */

// 스코프란 식별자 (변수명, 함수명...)의 유효범위(참조범위)

// JS는 전역(global), 지역(local), 모듈(module) 스코프로 구분
// JS는 식별자가 선언된 위치를 스코프의 유효 범위로 보는 정적스코프(렉시컬스코프)를 가짐
// 전역스코프: 모든 실행컨텍스트에서 참조할 수 있는 유효 범위
//              (실행컨텍스트(excutive context): 코드가 실행되고 있는 문맥)
// 지역스코프: function과 block으로 구분

// var: var는 block scope을 가지지 x (let, const는 block scope을 가짐 but )
//          -> block안에 var로 선언해도 전역 변수
//      var는 중복선언이 가능하며 그 위로 값이 덮어씌워짐
// ES5까지 변수 중에서 var: 전역스코프, function: 지역스코프
// ES6의 변수는 let, const: 전역스코프, function: 지역스코프
//              block: 지역스코프, 모듈스코프

// 스코프체인: 식별자 검색을 위해 사용되는 하위스코프에서 상위스코프로의 연결
// JS는 모든 식별자 선언을 호이스팅 함
//      -> but let, const, class 선언은 호이스팅을 인정하지 x



/* global */

var global_var = 'global_var';
let global_let = 'global_let';
const global_const = 'global_const';

console.log(global_var);
console.log(global_let);
console.log(global_const);


function f1(){
    console.log(global_var);
    console.log(global_let);
    console.log(global_const);
}

f1();


{
    console.log(global_var);
    console.log(global_let);
    console.log(global_const);
}


/* local - function */
// 함수 안에서 선언된 식별자는 함수스코프(함수 내부)에서만 참조 가능
function f2(){
    var func_var = 'func_var';
    let func_let = 'func_let';
    const func_const = 'func_const';

    console.log(func_var);
    console.log(func_let);
    console.log(func_const);
}

f2();


// 블럭스코프 (블럭안에서만 참조 가능, 단 var는 블럭안에 선언해도 전역변수)
{
    var block_var = 'block_var';    // global
    let block_let = 'block_let';
    const block_const = 'block_const';

    console.log(block_var);
    console.log(block_let);
    console.log(block_const);
}

console.log(block_var);
// console.log(block_let);     // refrence error 발생
// console.log(block_const);   // refrence error 발생


/* scope chain */
var sc_var = 'gsc_var';
let sc_let = 'gsc_let';
const sc_const = 'gsc_const';

function f3(){
    var sc_var = 'fsc_var';
    let sc_let = 'fsc_let';
    const sc_const = 'fsc_const';

    {
        var sc_var = 'bsc_var';
        let sc_let = 'bsc_let';
        const sc_const = 'bsc_const';

        console.log();
        console.log(sc_var);    // bsc_var
        console.log(sc_let);    // bsc_let
        console.log(sc_const);  // bsc_const
    }

    console.log();
    // var는 {}안에서 선언되도 전역변수 취급하기 때문에 값이 덮어짐
    console.log(sc_var);    // bsc_var
    console.log(sc_let);    // fsc_let
    console.log(sc_const);  // fsc_const
}

f3();
console.log();
console.log(sc_var);    // gsc_var
console.log(sc_let);    // gsc_let
console.log(sc_const);  // gsc_const


/* hoisting */
// 명시적으로 식별자를 선언하지 않아도 엔진이 코드 최상단에 선언한 것으로
// 선언을 끌어올려 놓고(호이스팅) 전체코드를 순차적을 수행
console.log(h_var);     // undefined
h_var = 'h_var';
console.log(h_var);     // h_var
var h_var;              // 이미 호스팅되어있음
console.log(h_var);     // h_var
console.log();

h_let = 'h_let';    // 명시적으로 선언 안하면 var로 호이스팅
console.log(h_let);
// let h_let;          // let은 호이스팅이 안되기 때문에 error 발생

// 함수도 식별자이므로 호이스팅이 됨
// 선언식 함수는 코드 실행전에 모두 호이스팅이 됨
// 아래 코드에서 f4는 global의 property -> 함수 재정의 가능

f4();   // f4 redefiend

function f4(){
    console.log('f4');
}

// 함수 재정의가 가능 (선언식함수는 global의 property이기 때문에)
function f4(){
    console.log('f4 redefined');
}

f4();   // f4 redefiend


/* 실습(예측) */
var ex_var = 'ex_var';
let ex_let = 'ex_let';

function f5() {
    console.log(ex_var);
    console.log(ex_let);
    ex_var = 'f5_ex_var';
    ex_let = 'f5_ex_let';
    // var ex_var;  // 주석 해제시 undefined 출력
    // let ex_let;  // 주석 해제시 error 발생
}
console.log();
f5();