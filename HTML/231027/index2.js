window.onload=function(){
    
}

let count=3;

$(function(){
    $(".at5_close").on("click", function(){
        $(".at5_msg").hide();
    })
    $(".next5B").on("click", function(){
        if(count<5){
            count++;
            $(".img5").animate({"left":"-=500px"}, 500);
        }
        else{
            $(".at5_msg").show()
        }
    })
    $(".prev5B").on("click",function(){
        if(count>1){
            count --;
            $(".img5").aniamte({"left":"+=500px"}, 500);
        }

    })
})