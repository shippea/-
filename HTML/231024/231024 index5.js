let user_id="abc";
let user_pw="1004";
let id, pw, login;
let count=0;

window.onload=function(){
    let id=document.getElementById("id");
    let pw=document.getElementById("pw");
    let login=document.querySelector(".login")
    let msg=document.querySelector(".msg");

    login.onclick=function(){
    // 뭐가 틀렸는지 알려주는 방법

    /*
        if(id.value==user_id){
            if (pw.value==user_pw){
               msg.innerHTML="환영합니다.";
            }
            
        
            else
              msg.innerHTML="비밀번호가 틀렸습니다.";
        
        } 
        else
           msg.innerHTML="아이디가 틀렸습니다.";
    */
    

    
        if(id.value==user_id && pw.value==user_pw)
            msg.innerHTML="환영합니다.";
        else{
            count++;
            msg.innerHTML=(count + "번 틀렸습니다. " + "다시 확인하세요. ");
            
            if(count>=3){
                msg.innerHTML=(count+"번 틀렸습니다. 내일 다시 시도 부탁드립니다.");
                // login.style.display='none';
            }

        }
            

    

    }
        
            
        
    

}