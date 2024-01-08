let btn = document.getElementById("btn");
btn. onclick = function(){
    let xhr = new XMLHttpRequest();
    xhr.open("GET", "http://apis.data.go.kr/B552881/kmooc/courseList?ServiceKey=9FKcuWvzReyb3VdhH5Ql7T1KMjQOn14SOGyKd5y9cV%2BRRbwcmkakGBBLWJ7ufNFIreq4mb4BEX0MbY5ji5oG3A%3D%3D&page=1", true);
    xhr.send();
    xhr.onreadystatechange = function() {
        if(xhr.readyState==4 && xhr.status==200){
            let jsonStr = xhr.responseText;
            const jsonObj = JSON.parse(jsonStr);
            document.getElementById("content").innerHTML 
                = jsonObj.pagination.count;
        }
    }
}