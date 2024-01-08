    /* ES6 함수 */

// - ES6 이전의 함수는 모두 callable이면서 constructor
//    (생성자로 사용하지 않을 함수가 construct 객체를 바인딩
//      하고 있는 것은 불필요한 성능상의 문제가 있다는 것)
// - ES6의 메소드 축약표현을 사용한 함수만 메소드로 인정
//    ES6의 메소드는 constructor나 prototype을 가지지 x

const obj = {
    x: 1,
    foo() {              // ES6 메소드
        return this.x;
    },
    bar: function() {    // 일반함수
        return this.x;
    }
};

console.log(obj.foo()); // 1
console.log(obj.bar()); // 1
console.log(obj.foo.hasOwnProperty('prototype')); // false
console.log(obj.bar.hasOwnProperty('prototype')); // true

// 화살표 함수 (arrow function)
//  1. 기존 함수문법을 간소화
//  2. 함수 내에서 this 문제를 해결
//      - 화살표 함수 내의 this는 화살표 함수 상위스코프의 this를 따름
//      - 자체적으로 this를 바인딩하지 x
//      - 주로 콜백으로 활용해서 기능만 수행하는 목적의 함수
// - 매개변수가 1개인 경우 소괄호 생략가능 (없는 경우는 생략x)
// - 실행할 문장이 1개인 경우 함수블럭 생략가능 (문장은 생략x)
// - 객체리터럴을 반환하는 경우 소괄호로 감싸주어 함수블럭과 구분지음

// console.log(() => {x:1, y:2}); // error
console.log(() => ({x:1, y:2})); // {x:1, y:2}

// 화살표 함수도 즉시실행 함수로 사용 가능
(() => console.log('홍길동'))(); // 홍길동

console.log('----------');

class Person {
    constructor(prefix) {
        this.prefix = prefix;
    }
    printName(arr) {
        return arr.map(ele => this.prefix + arr[0]);
    }
}

const person = new Person ('나는 ');
console.log(person.printName(['강감찬']));

console.log('----------');

// 화살표 함수는 arguments를 바인딩하지 않고 상위스코프의
// argument를 따름

(function(){
    const foo = () => console.log(arguments);
                    // {'0':1, '1", 2}
    foo (3, 4);
}) (1,2);

console.log('----------');


// 전역객체의 araugemnet를 사용
const obj3 = () => console.log(arguments);
obj3(1, 2, 3); // 전역객체의 arguments
 
// rest parameter
function func1(...args) {
    console.log(args);
    console.log(args.length);
}

func1(1, 2);
func1(1, 2, 3);

console.log('----------');

// default parameter
// : 파라미터가 없거나 undefined일 때 기본 값을 적용

function func3(){
    return a*b;
}

console.log(func3(1)); // 5
console.log(func3(1, undefined));  // 5
console.log(func3(1, 2)); // 2c
console.log(func3(i,null)); // 0
console.log(func3(1, '삼')); // nun

console.log('----------');