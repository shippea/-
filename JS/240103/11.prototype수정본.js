    /* prototype */

// JS의 모든 객체는 함수를 통해서 생성됨
//  - 함수를 통해 객체가 생성될 때 생성된 객체마다 
//     prototype이라는 상속을 위한 프라퍼티가 생성됨
//  - JS는 상속(Inheritance) 모델이 아닌 위임(Delegation) 모델
//  - 각 객체의 프라퍼티는 각 객체에 저장되고 각 객체의 메소드는
//    각 객체의 프로토타입에 저장됨
//  - 모든 객체는 [[Prototye]]이라는 내부 슬롯을 가지며 
//    그 값은 프로토타입의 참조
//  - 모든 프로토타입은 생성자함수의 참조인 constructor라는 
//    프라퍼티를 가짐
//  - 객체리터럴에 의해 생성된 객체의 프로토타입은 Object.prototype
//  - 생성자함수만 prototype을 생성하며 생성자함수를 통해 생성된
//    객체의 프로토타입은 생성자함수.prototype
//  - 생성자함수를 통해 생성된 객체는 생성자함수의 prototype에
//    __proto__ 또는 Object.getPrototypeOf를 통해 접근 가능
//  - 선언식함수, 함수리터럴은 생성될 때 prototype 프라퍼티를 가짐
//  - 화살표함수, 메소드는 생성될 때 prototype 프라퍼티를 가지지 x


// __proto__
const hong = {
    name: '홍길동',
    age: 30
}

console.log(hong);
console.log(hong.__proto__ === Object.prototype); // true
console.log(hong.__proto__.constructor === Object.prototype.constructor); // true
console.log(hong.__proto__.constructor === Object); // true
console.log(hong.__proto__.constructor === Object()); // false
console.log(hong.__proto__.constructor === new Object()); // false

hong.hobby = ['축구', '농구'];
console.log(hong);

Object.prototype.printHobby = function() {
    console.log(this.hobby); // ['축구','농구']
}
// hong.__proto__는 Object의 prototype이므로
// hong은 Object에 추가된 printHobby메소드를 사용할 수 있음
// 그때 printHobby메소드 내의 this는 해당 메소드를 호출한 객체참조
hong.printHobby();
console.log('-------------');

hong.__proto__.printName = function() {
    console.log('홍길동');
}
Object.prototype.printName(); // 홍길동
console.log('-------------');


// 생성자 함수를 통한 객체 생성
// 문제점: 동일한 메소드가 객체마다 생성됨

function Circle(radius) {
    this.radius = radius;
    this.getArea = function() {
        return Math.PI * this.radius**2;
    }
}

const circle1 = new Circle(1);
const circle2 = new Circle(2);
console.log(circle1.getArea == circle2.getArea);
console.log(circle1.getArea);
console.log(circle2.getArea);

// 화살표 함수, 메소드는 prototype을 가지지 x
console.log((()=>{}).prototype); // undefined
const myobj = {
    name()  {
        return this.name;
    }
}

console.log(myobj.prototype); // undefined
console.log('-------------');


// 프로토타입 기반의 상속
// 프라퍼티는 각자 소유, 메소드는 공유

function Circle2(radius) {
    this.radius = radius;
}

Circle2.prototype.getArea = function() {
    return Math.PI * this.radius**2;
};

const circle3 = new Circle2(1);
const circle4 = new Circle2(2);
console.log(circle3.getArea == circle4.getArea); // true
console.log(circle3.getArea);
console.log(circle4.getArea);
console.log('-------------');

// [[Prototype]] 숨김 프라퍼티를 사용하는 이유: 순환참조 방지
// 순환참조: A -> B -> A -> B -> A...

const a = {};
const b = {};
a.__proto__ = b;
// +b.__proto__ = a;
console.log('-------------');


// 객체리터럴로 생성된 객체의 프로토타입은 __proto__로 접근
const person = {};
console.log(person.__proto__ === Object.prototype); // true
console.log(person.__proto__.constructor); // Object
console.log('-------------');

// 생성자 함수로 생성된 객체의 프로토아입은 prototype으로 접근
function Person(){

}
const person2 = new Person();
console.log(person2.__proto__ === Person.prototype); // true
console.log(person2.__proto__ === Object.prototype); // false
console.log(Person.__proto__ === Object.prototype); // false
console.log(Person.__proto__ === Function.prototype); // true
console.log(Function.__proto__); // {}
console.log(Function.__proto__.constructor); // Function
console.log(Object.prototype === Function.__proto__); // false

Function.__proto__ = Object.prototype;
console.log(person2.__proto__ === Function.__proto__); // false
console.log(Person.__proto__ === Function.__proto__); // false
console.log(Object.prototype === Function.__proto__); // true

// Object.getPrototyeOf
const o = {
    newo: 'newo'
};

const op = Object.getPrototypeOf(o);
op.name = 'op';
op.print = function() {
    console.log('op');
}
console.log(op);
console.log('-------------');

// 프로토타입 상속
const p = new Object();
p.name = 'p';
p.__proto__ = o;
console.log(Object.getPrototypeOf(p));
console.log('-------------');

// 프로토타입 체인
const grandParent = {
    name: 'grandParent',
    age: 80,
    printGrandParent: () => {console.log('grandParent!');}
};

const parent = {
    name: 'parent',
    age: 50,
    __proto__: grandParent,
    printParent: () => {console.log('parent!');}
};

const child = {
    name: 'child',
    age: 20,
    __proto__: parent,
    printChild: () => {console.log('child!');}
}; 

console.log(grandParent.__proto__ === Object.prototype); // true
console.log(child.name); // child
console.log(child.__proto__.name); // parent
console.log(parent.name); // parent
console.log(parent.__proto__.name); // grandParent
console.log(grandParent.name); // grandParent
console.log(grandParent.__proto__.name); // op

const obj1 = {
    name: '홍길동',
    age: 50
};

const obj1Prototype = Object.getPrototypeOf(obj1);
obj1Prototype.hobby = ['등산', '낚시'];

const obj2 = {
    name: '홍길동아들',
    age: 20,
    __porot__: obj1
}

const obj3 = {
    name: '홍길동손자',
    age: 1,
    __proto__: obj2
}

const obj3Prototype = Object.getPrototypeOf(obj3);
obj3Prototype.hobby = ['바둑', '장기'];

console.log(obj1.hobby);
for (prop in obj1) {
    console.log(prop, obj1[prop]);
}
console.log(obj2.hobby);
for (prop in obj2) {
    console.log(prop, obj2[prop]);
}
console.log(obj3.hobby);
for (prop in obj3) {
    console.log(prop, obj3[prop]);
}


// 정적 프라퍼티 / 정적 메소드
// 생정자함수객체에 선언한 프라퍼티와 메소드
// 생성자함수객체를 통해서만 참조가 가능
// 생성자함수를 통해 생성된 객체에서는 참조가 불가능
// 생성된 객체들이 공유

function PC() {
    name = '퍼스널컴퓨터';
}

PC.price = 10000;   // 정적 프라퍼티
PC.getPrice = function () { // 정적 메소드
    return this.price;
}
console.log(PC.price); // 10000
console.log(PC.getPrice()); // 10000

const pc1 = new PC();
const pc2 = new PC();
console.log(pc1.price); // undefined
console.log(pc1.getPrice); // undefined

console.log(pc1.constructor.price); // 10000
console.log(pc1.constructor.getPrice()); // 10000

// in연산자: 프라퍼티 존재 확인 (ES6의 Reflect.has)
console.log('name' in pc1); // true
console.log('prototype' in Object); // true
console.log(Reflect.has(pc1, 'name')); // true
console.log(Reflect.has(pc1, 'price')); // false
console.log('prototype' in pc1); // false
console.log('prototype' in PC); // true

// Object.keys, Object.values, Object entries(ES8)
const gum = {
    brand: '롯데',
    name: '자일리톨',
    price: 100
};

console.log(Object.keys(gum)); // brand, name, price
console.log(Object.values(gum)); // 롯데, 자일리톨, 100
console.log(Object.entries(gum)); // brand: 롯데 ...

/*
     # 실습1
    임의의 생성자 함수를 만들고 객체를 2개 생성해서 출력하기
*/

function USER () {
    name = '이아무개';    
}

USER.age = 30;

const user1 = new USER();
const user2 = new USER();
console.log(user1.constructor.age);
console.log(user2.constructor.age);


/*
     # 실습2
    분류가 가능한 어떤 것을 선택해서 3단계 이상의 상속 구조를 만들기
    예) 동물>새>닭, 차>승용차>BMW, 스포츠>야구>롯데

*/

const battalion = {
    name: '대대',
    scale: 200
};

const battalionPrototyple = Object.getPrototypeOf(battalion);
battalionPrototyple.upperUnit = '여단';

const company = {
    name: '중대',
    scale: 80,
    __proto__: battalion
};

const platoon = {
    name: '소대',
    scale: 20,
    __proto__: company
};

console.log(battalion.name);
console.log(company.name);
console.log(company.__proto__.name);
console.log(platoon.__proto__.scale);
console.log(battalion.upperUnit);


/*
     # 실습3
    http://172.30.1.34:9999/ajaxTest/members.xml
    1. AJAX통신으로 xml데이터를 읽어온다.
    2. JS로 member 객체를 생성하고 admin 객체와 user 객체가
       member 객체를 상속받도록 한다.
    3. 읽어온 데이터를 화면에 출력한다.

*/

const member = {mtype:'',mid:'',mname:''};
const admin = {mpart:''};
const user = {memail:''};
Object.setPrototypeOf(admin, member);
Object.setPrototypeOf(user, member);

$.get({
    url: 'http://172.30.1.34:9999/ajaxTest/members.xml',
    success: function(data) {
        const resultArray = [];
        // 각 li에 대해 함수 실행
        $("#members_xml li").each(function(idx) {
            const mem = $(data).find('member').eq(idx);
            if (admin.mpart = mem.find('mpart').text()) {
                admin.mtype = mem.find('mtype').text();
                admin.mid = mem.find('mid').text();
                admin.mname = mem.find('mname').text();
                resultArray.push(admin);
            }
            if (user.memail = mem.find('memail').text()) {
                user.mtype = mem.find('mtype').text();
                user.mid = mem.find('mid').text();
                user.mname = mem.find('mname').text();
                resultArray.push(user);
            }
            // this (각 li)에 배열로 받은 문자들을 집어넣어줌
            $(this).text(JSON.stringify(resultArray[idx]));
        });
    }
});


/*
     # 실습4
    http://172.30.1.34:9999/ajaxTest/members.json
    1. AJAX통신으로 json데이터를 읽어온다.
    2. JS로 member 객체를 생성하고 admin 객체와 user 객체가
       member 객체를 상속받도록 한다.
    3. 읽어온 데이터를 화면에 출력한다.

*/

$.get({
    url: 'http://172.30.1.34:9999/ajaxTest/members.json',
    success: function(data) {
        const valueArray = Object.values(data);
        const resultArray = [];
        $("#members_json li").each(function(idx) {
            let eachObj = valueArray[idx];
            if (admin.mpart = eachObj.mpart) {
                admin.mtype = eachObj.mtype;
                admin.mid = eachObj.mid;
                admin.mname = eachObj.mname;
                resultArray.push(admin);              
            }
            if (user.memail = eachObj.memail) {
                user.mtype = eachObj.mtype;
                user.mid = eachObj.mid;
                user.mname = eachObj.mname;
                resultArray.push(user);              
            }            
            $(this).text(JSON.stringify(resultArray[idx]));
        });
    }
});