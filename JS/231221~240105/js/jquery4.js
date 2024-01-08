$(function(){
    $("#load").on("click", function(){
        $("#result").load("http://172.30.1.34:9999/ajaxTest/ajaxTest.jsp", function(responseText, textSatus, jqXHR){
            console.log(responseText);
            console.log(textSatus);
            console.log(jqXHR.readyState);
            // for (prop in jqXHR){
            //     console.log(`${prop} : ${jqXHR[prop]}`);
            // }
        });
    });
    $("#get").on("click", function(){

    });
    $("#post").on("click", function(){

    });
});