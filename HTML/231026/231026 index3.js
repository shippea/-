let btn1;
let many;
let price, i, t;
let a, b;
let total;
let point;
let sale;


window.onload=function(){
    btn1=document.getElementById("btn1");
    many=document.getElementById("many");
    total=document.getElementById("total");
    point=document.getElementById("point");
    sale=document.getElementById("sale");

    btn1.addEventListener("click",function(){
        a=Number(many.value);
        price=a*320;
        
        b=price%100;
        
        if(price>=30000){

            t=parseInt(price*0.03);
            price-=t;           
        }

        else{
            
        }
        total.innerHTML = price;
        sale.innerHTML = t;
        point.innerHTML = b;
    })


}