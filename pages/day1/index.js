$(function () {
    var userUserCrud = new UserCRUD() ;
    $("#add").click(function () {
        userUserCrud.addUser("/apis/UserController/add") ;
    });
    $("#select").click(function(){
        userUserCrud.addUser("/apis/UserController/select")
    });
})

function UserCRUD() {}
UserCRUD.prototype.clearTable = function (tableBody) {
    var trs = tableBody.find("tr");
    trs.remove() ;
}

UserCRUD.prototype.getParam = function () {
    var name = $("#name").val() ;
    var addr = $("#addr").val();
    var param = {name:name, addr:addr} ;
    return param;
}

UserCRUD.prototype.appendTable = function (tableBody,resp) {
    for(var item of resp.data){
        tableBody.append("<tr><td>"+ item.name +"</td><td>"+ item.addr +"</td></tr>");
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