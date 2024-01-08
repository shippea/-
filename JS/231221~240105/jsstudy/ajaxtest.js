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