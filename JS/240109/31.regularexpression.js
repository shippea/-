    /* 정규 표현식 */

// - 패턴(partter)과 플래그(flag)를 정의하여 문자열 내에서
//      특정한 패턴에 해당하는 문자열 존재 여부를 확인하는데 사용
// - JS에서는 RegExP 생성자의 객체
// - 정규표현식리터럴: // 안에 패턴을 정의
// - 문법: /pattern/flags
// - 메소드
//    1. test: 패턴에 부합하는 문자열이 있으면 true 없으면 false 반환
//    2. match: 패턴에 부합하는 문자열을 배열로 반환
//    3. repalce: 패턴에 부합하는 문자열을 치환

const str = 'Hello! are you human? haha!';

// 정규표현식 리터럴 생성
const re0 = /h/; // 처음 검색된 문자만 반환
const re1 = /h/g; // g: 문자열 전체에서 검색된 문자들 전부 반환
const re2 = /h/ig; // i: 대소문자 구분없이 검색
console.log(re0.test(str)); // true
console.log(re1.test(str)); // true
console.log(re2.test(str)); // true
console.log('----------');

console.log(str.match(re0)); // index:15
console.log(str.match(re1)); // ['h', 'h', 'h']
console.log(str.match(re2)); // ['H', 'h', 'h', 'h']
console.log('----------');

console.log(str.replace(/Hello/, 'hi')); // hi! are you...



// # 실습1 - 첫 번째 h만 H로 치환
console.log(str.replace(/H/, 'h'));

// # 실습2 - 모든 h를 H로 치환
console.log(str.replace(/h/g, 'H'));

// # 실습3 - 영문자들만 추출
console.log(str.match(/[A-Za-z]/g));

// # 실습4 - 영문자가 아닌 것들만 추출
console.log(str.match(/[^A-Za-z]/g));

// # 실습5 - 영문자의 총 개수
console.log(str.match(/[A-Za-z]/g).length);

// # 실습5.5 - 영문자인 것들을 모두 대문자로 변환
str.split('').forEach(ch => {
    console.log(String.fromCharCode(ch.charCodeAt()-32));
}); // 모르겠음

// # 실습6 - 모든 공백문자들을 제거
console.log(str.replace(/[\s]/g,''));

// # 실습7 - 문자열이 3문자로 구성된 것들만 추출
console.log(str.match(/.../g));

// # 실습8 - 영문이나 공백문자가 아닌 것들만 추출
console.log(str.match(/[^A-Za-z\s]/g));

// # 실습9 - 문자열이 y로 시작하고 u로 끝나는 문자열 추출
const re9 = /^H/;
str2 = '1you2 syou> you! yours you yous youS'
console.log(str2.match(/y\w*\W*u/g));


// # 실습10 - 문자열이 5개로 구성된 단어들만 추출
console.log(str.match(/[A-Za-z]{5}/g));

