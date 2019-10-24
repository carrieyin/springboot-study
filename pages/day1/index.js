$(function () {
    var userUserCrud = new UserCRUD() ;
    $("#add").click(function () {
        var id = $("#uid").val();
        console.log("----------", id);
        if(undefined == id || "" == id){
            userUserCrud.addUser("/apis/UserController/add") ;
        }else{
            userUserCrud.modUser("/apis/UserController/mod");
        }

    });
    $("#select").click(function(){
        userUserCrud.addUser("/apis/UserController/select")
    });

    $("#clear").click(function(){
        $("#name").val("");
        $("#addr").val("");
        $("#uid").val("");
    });
    console.info('------------------',$("#del"))
    $("#usertable tbody").on("click",".del",function () {
        var tr = $(this).parents("tr");
        var userid = tr.attr("data-id");
        console.info(userid)
        userUserCrud.delUser("/apis/UserController/del", userid, tr);

    });

    $("#usertable tbody").on("click",".mod",function () {
        var tr =  $(this).parents("tr") ;
        var name = tr.find("td[name='name']").text();
        var addr = tr.find("td[name='addr']").text();
        var birthday = tr.find("td[name='birthday']").text();
        var sex = tr.find("td[name='sex']").text();
        var id = tr.attr("data-id");
        $("#name").val(name);
        $("#addr").val(addr);
        $("#uid").val(id);
        var sexRadio = $("input[name='sex']");
        for (var index = 0; index < sexRadio.length; index++) {
            var item = $(sexRadio[index]);
            if(item.val() == sex){
                item.attr("checked", true);
            }
        }
        $("#birthday").val(birthday);

    });
})

function UserCRUD() {}
UserCRUD.prototype.clearTable = function (tableBody) {
    var trs = tableBody.find("tr");
    trs.remove() ;
}

UserCRUD.prototype.getParam = function () {
    var id = $("#uid").val();
    var name = $("#name").val() ;
    var addr = $("#addr").val();
    var birthday = $("#birthday").val();
    var sex = $("input[name='sex']:checked").val();
    var hobbys = $("input[name='hob']:checked");
    var hob= "";
    for (var index = 0; index < hobbys.length; index++) {
        hob +="/" + $(hobbys[index]).val();
    }
    console.log(hob);
    var param = {id:id, name:name, addr:addr, birthday:birthday, sex:sex, hobby:hob} ;
    return param;
}

UserCRUD.prototype.appendTable = function (tableBody,resp) {
    for(var item of resp.data){
        console.info('----- > ',item)
        var delspan = "<button type='button' class='btn btn-default del' aria-label='Left Align'><span class='glyphicon glyphicon-trash'></span></button>";
        var modspan = "<button type='button' class='btn btn-default mod' aria-label='Left Align'><span class='glyphicon glyphicon-cog'></span></button>";
        var tr = $("<tr data-id='"+ item.id +"'><td name='name'><input type='hidden' value='"+item.id+"' name='id' /> "+ item.name
            +" </td><td name='addr'>"+ item.addr +"</td><td name='birthday'>"+item.birthday+"</td><td name='sex'>"+ item.sex +"</td><td name='hob'>"+ item.hobby +
            "</td>"+"<td>"+ delspan +"&nbsp;&nbsp;&nbsp;"+ modspan +"</td></tr>") ;
        tableBody.append(tr);
    }
}

UserCRUD.prototype.addUser = function (url) {
    var tableBody = $("#usertable tbody");
    this.clearTable(tableBody);
    var param = this.getParam();
    var _slef = this;
    var ajaxing = httpUtil.dealAjaxRequest4JSObj(url, param);
    $.when(ajaxing).done(function (resp) {

        _slef.appendTable(tableBody, resp);
    });
}

UserCRUD.prototype.modUser = function (url) {
    var trs = $("#usertable tbody tr");
    var id = $("#uid")
    var param = this.getParam();
    var tr;
    for (var i = 0; i < trs.length; i++) {
        var trindex = $(trs[i]);
        if(trindex.attr("data-id") == id.val()){
            tr = $(trs[i]);

        }
    }

    var ajaxing = httpUtil.dealAjaxRequest4JSObj(url, param);
    $.when(ajaxing).done(function (resp) {
        if(resp.data){
            tr.find("td[name='name']").text(param.name);
            tr.find("td[name='addr']").text(param.addr);
        }

        $("uid").val("");
    });
}

UserCRUD.prototype.delUser = function (url, userid,tr) {
    var _slef = this;
    var id = {id: userid};
    var ajaxing = httpUtil.dealAjaxRequest4SimpleParam(url, id);
    $.when(ajaxing).done(function (resp) {
        console.log(resp)
        if(resp.data){
            tr.remove();
        }else{
            alert("delete user fail");
        }
     }).fail(function (err) {
        console.error('err : ' ,err) ;
    });
}