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
    console.info('------------------',$("#del"))
    $("#usertable tbody").on("click",".del",function () {
        var tr =  $(this).parents("tr") ;
        var userid = tr.attr("data-id");
        console.info(userid)
        userUserCrud.delUser("/apis/UserController/del", userid, tr);
    });

    $("#usertable tbody").on("click",".mod",function () {
        var tr =  $(this).parents("tr") ;
        var userid = tr.attr("data-id");
        var name = tr.find("td[name='name']").text();
        var addr = tr.find("td[name='addr']").text();
        var id = tr.attr("data-id");
        $("#name").val(name);
        $("#addr").val(addr);
        $("#uid").val(id);
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
    var param = {id:id, name:name, addr:addr} ;
    return param;
}

UserCRUD.prototype.appendTable = function (tableBody,resp) {
    for(var item of resp.data){
        console.info('----- > ',item)
        var tr = $("<tr data-id='"+ item.id +"'><td name='name'><input type='hidden' value='"+item.id+"' name='id' /> "+ item.name
            +" </td><td name='addr'>"+ item.addr +"</td><td><a href='#' class='del' data-id ='"+item.id+"' >删除</a>&nbsp;&nbsp;&nbsp;<a  href='#' class='mod'>修改</a></td></tr>") ;
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