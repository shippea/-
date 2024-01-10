    /* 스프레드(spread) 문법 */

// 이터러블한 값들의 분산을 위해 생긴 ES6의 문법
// 이터러블 (Array, String, Map, Set, Arguments, 
//           DOM컬렉션(NodeList, HTMLCollection))
// 기본적으로 객체(이터러블이 아님)에는 사용 불가하지만
// 2021년부터 객체에 사용할 수 있도록 제안됨 -> 아직 실무에서는 x

console.log(...[1, 2, 3]); // 1 2 3 목록 (값 x -> 변수에 할당 불가)
console.log(...'thanks'); // t h a n k s (쪼개진 목록 추출)
console.log(...new Map([[1,'홍길동'],[2,'강감찬']]));
console.log(Math.max(1,2,3)); // 3
console.log(Math.max([1,2,3])); // NaN -> 목록화되지 않아 최대값 추출 불가
console.log(Math.max(...[1,2,3])); // 3

console.log('-------------');

// ...args: Rest Parameter (파라미터 여러 개를 배열 형태로 받는 역할)
(function(...args) {
    console.log(args);    
})(1,2,3);  // [1,2,3]

console.log([...[1, 2], ...[3, 4]]); // [1,2,3,4]
console.log([[1, 2], ...[3, 4]]); // [[1,2],3,4]]
// 객체는 이터러블이 아니지만 스프레드 문법 적용이 가능함
console.log({x:1, y:2, ... {z:3}}); // {x:1, y:2, z:3}

// 스프레드 문법은 값이 아니라서 변수에 할당하거나
// 함수에 파타미터로 전달하거나, 함수의 리턴값이 될 수 x
// const obj2 = ...[1,2]; // 할당 되지 않고 error 발생
console.log('-------------');


    /* 구조분해할당 (Destructuring Assignment) */

// 이터러블 또는 객체를 분해해서 각각의 변수에 할당
// 배열에서 특정 요소들만 추출해서 변수화 하거나
// 객체에서 특정 프라퍼티들만 추출해서 변수화 하는데 사용
// 코드를 간결하게 하고 가독성을 높여주므로 적극 사용하자

const [one, two, three] = [1, 2, 3];
console.log(one, two); // 1 2

console.log([a,b] =[1]); // [1]
console.log([a,b] =[1,2]); // [1,2]
console.log([a,b] =[1,[2,3]]); // [1,[2,3]]

console.log('-------------');

const user = {
    firstName: '순신',
    lastName: '이'
};

// 각 파라미터에 이름부여
console.log({firstName:ln, lastName:fn} = user);
console.log(ln, fn); // 순신 이

const {firstName='길동', lastName} = {lastName: '홍'};
console.log(firstName, lastName); // 길동 홍

// 문자열 객체는 유사배열 객체이므로 length 프라퍼티를 가지고 있음
const str = 'Hello';
const {length} = str;
console.log(length); // 5

console.log('----------');

// JSON 데이터를 파싱할 때 배열 내에 객체가 담긴 형태로 많이 사용하므로
// 아래 코드를 사용하면 데이터를 구조분해할당해서 추출할 수 있음
const todos = [
    {id:1, content: 'HTML', completed: true},
    {id:2, content: 'CSS', completed: false},
    {id:3, content: 'JS', completed: false}
];
const [, {id}] = todos;
console.log(id); // 2

 // # 실습1
// 세 번째 객체에서 content 구조분해 할당
const [, , {content}] = todos;
console.log(content); // JS

 // # 실습2
// 첫 번째 객체의 id보다 id값이 큰 객체의 content를 구조분해 할당
const [{id:id1}] = todos;
todos.forEach((ele) => {
    if(ele.id > id1){
        const {content} = ele;
        console.log(content);
    }
})

 // # 실습3
// 두 번째 객체에서 completed와 같은 completed를 가진 객체의 id 구조분해할당
const [,{completed}] = todos;
todos.forEach((ele) => {
    if(completed == ele.completed){
        console.log(ele.id);
    }
})
console.log('----------');



    /* Set */

// Set: 중복과 순서가 없는 자료구조
// JS에서는 배열을 이용해서 set을 구현

const set1 = new Set([1,2,3,4]);
console.log(set1); // {1,2,3,4}

// 중복값은 하나로 처리
const set2 = new Set('Hello');
console.log(set2); // {'H','e','l','o'}
console.log(set2.size); // 4
console.log('----------');

set2.add('H');
set2.add('i');
console.log(set2); // {'H','e','l','o','i'}

set2.add('a').add('b')
console.log(set2); // {'H','e','l','o','i','a','b'}

console.log('----------');

console.log(set2.has('H')); // true
set2.delete('H');
console.log(set2.has('H')); // false
set2.clear();
console.log(set2); // set(0) {}

set1.forEach((v, v2, set) => console.log(v, v2, set));
console.log('----------');

// set은 이터러블이다
const set3 = new Set([1,2,3,4,5]);
for (ele of set3) {
    console.log(ele);
}

console.log([...set3]); // [1,2,3,4,5]
console.log('----------');

    /* Map */

// Map: 엔트리(키, 값)로 구성, 키는 중복허용 불가, 값은 중복허용

const map1 = new Map([['name','홍'],['age',20]]);
console.log(map1); // name=>홍, age=>20

console.log(map1.size); // 2

map1.set('hobby','농구');
console.log(map1); // name=>홍, age=>20, hobby=>농구

map1.delete('age');
console.log(map1); // name=>홍, hobby=>농구

map1.clear();
console.log(map1); // {}
console.log('----------');

const map2 
    = new Map([['name', '강감찬'],['age',30],['hobby','축구']]);

map2.forEach((v,k,map) => console.log(v,k,map));

for (entry of map2) {
    console.log(entry); // [name,강감찬] [age,30] [hobby,축구]
}

for (entry of map2.entries()) {
    console.log(entry); // [name,강감찬] [age,30] [hobby,축구]
}

for (key of map2.keys()) {
    console.log(key); // name age hobby
}

for (value of map2.values()) {
    console.log(value); // 강감찬 30 축구
}

console.log('----------');

console.log([...map2]); // [[name,강감찬], [age,30], [hobby,축구]]

const map3 = new Map([['title', 'a'],['title2', 'b']]);
// const map3 = new Map();
// const title = { titlea: 'titlely'};
// const title2 = { titleb: 'titlely2'};
// map3.set(title, 'a')
//     .set(title2,'b');
// console.log(map3.get(title));
console.log(map3.get('title'));
map3.set('title2','bb');
console.log(map3.get('title2'));