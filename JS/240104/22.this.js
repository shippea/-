    /* this */

// 현재 메모리 상에서 참조(사용)되고 있는 객체 자신을 가리키는 키워드
// 참조되고 있는 객체의 참조값을 가지고 있는 참조 변수
// 기본적으로 함수 내의 this는 함수를 호출해서 실행하도록 만든 객체
// 다만, 화살표 함수는 화살표 함수를 둘러싼 실행컨텍스트에 따라 this가 결정됨
// JS에서 this를 이해하려면 함수호출자, 실행컨텍스트, 예외사항을 알아야 함
// 실행컨텍스트(Execution Context): 현재 실행되고 있는 환경/상황
//             -> 현재 사용되고 있는 메모리, 실행흐름이라고 이해하기
// JS에서는 동일한 함수 내의 this라고 해도 누가 호출했느냐에 따라 값이 달라짐
// 동적바인딩 (Dynamic Bingding): this에 객체의 참조가 동적으로 
//               바인딩(연결, 저장)되는 것을 동적 바인딩이라 함
// JS에서는 call, apply, bind 함수를 통해서 함수내의 this를 
//      렉시컬(정적)하게 결정할 수 있는 방법을 제공


// 함수 선언식 내의 this는 함수를 호출한 객체
// f 함수는 글로벌 프라퍼티
function f() {
    console.log(this);
}

f(); // global

{
    console.log(this);  // {}
}

console.log('------------');

// 화살표함수는 호출자가 this가 아닐 수 있음
// obj는 전역프라퍼티 그래서 화살표함수 내의 this는 전역객체
// 실행컨텍스트가 전역
const obj = { // 전역 컨텍스트
    name: '홍길동',
    // printName은 function을 부르는 메소드 -> 상위가 객체 obj임
    printName: function() { // 객체 컨텍스트
        console.log(this.name);
    },
    // 화살표함수는 호출한 객체의 컨텍스트를 따라가지 않고
    // 외부에 있는 전역컨텍스트를 따라감
    printNumbers: (a, b) => console.log(this.name,a ,b),
    printThis: () => console.log(this)
};

obj.printName(); // 홍길동
obj.printNumbers(3,4); // undefined 3 4 
obj.printThis(); // {}

// 생성자 함수를 통해 생성한 객체로 호출한 함수 (화살표함수 포함) 내의
// this는 생성된 객체
// 실행컨텍스트가 생성된 객체
function Person (name,age) { // 생성자 함수의 실행컨텍스트
    this.name = name;
    this.age = age;
    (this.printPerson = function () {
        console.log(this.name, this.age);
    }),
    (this.printPerson2 = () => console.log(this.name, this.age));
}

const person = new Person('홍길동', 30);
person.printPerson(); // 홍길동 30
person.printPerson2(); // 홍길동 30
console.log('-----------');

// call, apply, bind
// call: 호출하는 함수 내에서 this로 사용할 객체와 인자리스트 (,로 구분)
// apply: 호출하는 함수 내에서 this로 사용할 객체와 인자리스트 (배열)
// bind: 호출하는 함수 내에서 this로 사용할 객체를 지정하고 함수 반환

const obj1 = {
    name: '홍길동',
    printName: function() {
        console.log(this.name);
    },
    printNumbers: function(a, b) {
        console.log(this.name, a, b);
    },
    printThis: function() {
        console.log(this);
    }
};

const obj2 = {
    name: '강감찬'
};

obj1.printName(); // 홍길동
obj1.printNumbers.call(obj2, 3, 4); // 강감찬, 3, 4
obj1.printNumbers.apply(obj2, [3, 4]); // 강감찬, [3, 4]
obj1.printThis.call(); // global...

console.log('----------');

// 화살표 함수에는 call, apply, bind로 this를 지정할 수 x

const obj3 = {
    name: '홍길동',
    printName: () => console.log(this.name),
    printNumbers: (a, b) => console.log(this.name, a, b),
    printThis: () => console.log(this)
};

const obj4 = {
    name: '강감찬'
};

obj3.printName(); // undefined
obj3.printNumbers.call(obj4, 3, 4); // undefined 3 4
obj3.printNumbers.call(obj4, [3, 4]); // undefined [3, 4]
obj3.printThis.call(); // {}

console.log('----------');

// bind
function f() {
    return this.name;
}

const obj5 = {
    name: '홍길동'
}

const newf = f.bind(obj5);
console.log(newf); // function f
console.log(newf()); // 홍길동
console.log('----------');

// DOM event에서의 this 바인딩
// DOM의 이벤트 핸들러 내에서의 this는 이벤트 타겟