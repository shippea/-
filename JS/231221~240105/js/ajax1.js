// btn 버튼을 눌렀을 때 event가 발생하도록 function 안에 넣어줌
let btn1 = document.getElementById("btn1");
btn1.onclick = function(){

    /* AJAX 원시 코드 */

    // 1. xhr 객체 생성
    let xhr = new XMLHttpRequest();

    // 2. open (xhr 객체를 초기화 = 통신 초기화)
    // HTTP Mehotd(get,post ....), URL, 비동기여부 (true:비동기, false: 동기)
    xhr.open("GET", "http://127.0.0.1:5500/asset/plaintext.txt", true);

    // 3. send (요청전송)
    // GET일때는 공백, POST일때는 데이터를 send 메소드의 파라미터로 전달
    xhr.send();

    // 4. callback 처리
    // onreadystatechange: xhr의 이벤트속성 (콜백함수를 저장)
    // readyState: 클라이언의 요청상태, xhr의 상태코드 (0 ~ 4) -> 4일때 처리가능
    // status: 서버의 응답상태 (200, 403, 404, 500...)
    // statusText: 서버의 응답상태 문자열 (OK, FORBIDDEN, NOT FOUND ...)
    xhr.onreadystatechange = function() {
        if(xhr.readyState==4 && xhr.status==200){
            // plainttext.txt에 저장된 text를 출력
            document.getElementById("greeting").innerHTML = xhr.responseText;
        }
    }
}


let btn2 = document.getElementById("btn2");
btn2.onclick = function(){

    let xhr = new XMLHttpRequest();

    xhr.open("GET", "http://127.0.0.1:5500/asset/csv.csv", true);

    xhr.send();

    xhr.onreadystatechange = function() {
        // csv.csv에 저장된 text를 ,를 기준으로 문자열을 나눠 배열생성
        const respArray = xhr.responseText.split(",");
        if(xhr.readyState==4 && xhr.status==200){
            // csv.csv에 저장된 text를 출력
            // document.getElementById("greeting").innerHTML = xhr.responseText;
            document.getElementById("greeting").innerHTML 
                = respArray[1];
        }
    }
}

let btn3 = document.getElementById("btn3");
btn3.onclick = function(){

    let xhr = new XMLHttpRequest();

    xhr.open("GET", "http://127.0.0.1:5500/asset/json.json", true);

    xhr.send();

    xhr.onreadystatechange = function() {
        if(xhr.readyState==4 && xhr.status==200){
            // 받은 문자열을 변수설정
            let respText = xhr.responseText;
            // 문자열을 받은 변수를 객체화함
            const jsonObj = JSON.parse(respText);
            document.getElementById("greeting").innerHTML 
                = jsonObj.student1.sname;
        }
    }
}

let btn4 = document.getElementById("btn4");
btn4.onclick = function(){

    let xhr = new XMLHttpRequest();

    xhr.open("GET", "https://koreanjson.com/users/1", true);

    xhr.send();

    xhr.onreadystatechange = function() {
        if(xhr.readyState==4 && xhr.status==200){
            let jsonObj = JSON.parse(xhr.responseText);
            document.getElementById("greeting").innerHTML 
                = jsonObj.name;
        }
    }
}

let btn5 = document.getElementById("btn5");
btn5.onclick = function(){

    let xhr = new XMLHttpRequest();
    
    xhr.open("GET", "https://api.dbpia.co.kr/v2/search/search.xml", true);

    xhr.send();

/* 링크 코드
    <error>
        <paramdata>
            <totalcount>0</totalcount>
            <pagecount>20</pagecount>
            <pagenumber>1</pagenumber>
            <sort>1</sort>
            <order>desc</order>
        </paramdata>
        <error>
            <Code>E0002</Code>
        </error>
    </error>
*/

    xhr.onreadystatechange = function(){
        if(xhr.readyState==4 && xhr.status==200){
            // xml이기 때문에 responseXML로 받아와야함
            // 위에 실습한 plain text, csv, json은 responseText로 받을 수 있음
            let xmlObj = xhr.responseXML;
            let rootEle = xmlObj.documentElement;
            let errELe = rootEle.getElementsByTagName("error")[0];
            let codeEle = errEle.getElementsByTagName("Code")[0];
            document.getElementById("greeting").innerHTML 
                = codeEle.firstChild.nodeValue;
        }
    }
}

