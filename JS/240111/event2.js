const outer = document.querySelector("#outer");
const inner = document.querySelector("#inner");
const btn = document.querySelector("#btn");

outer.addEventListener("click", function(e) {
    console.log(e.target);
    console.log(e.currentTarget);
    console.log(e.eventPhase);
});
inner.addEventListener("click", function(e) {
    console.log(e.target);
    console.log(e.currentTarget);
    console.log(e.eventPhase);
    e.stopPropagation();
});
btn.addEventListener("click", function(e) {
    console.log(e.target);
    console.log(e.currentTarget);
    console.log(e.eventPhase);
    // 전파방지 -> 이후로 event객체가 더 전파되는 것을 막음
    e.stopPropagation();
});

outer.addEventListener("click", function(e) {
    console.log(e.target);
    console.log(e.currentTarget);
    console.log(e.eventPhase);
}, true);
inner.addEventListener("click", function(e) {
    console.log(e.target);
    console.log(e.currentTarget);
    console.log(e.eventPhase);
}, true);
btn.addEventListener("click", function(e) {
    console.log(e.target);
    console.log(e.currentTarget);
    console.log(e.eventPhase);
}, true);

// 링크로 자동 이동되는 default Event를 막음
const aEle = document.querySelector("a");
aEle.addEventListener("click", function(e) {
    e.preventDefault();
});
