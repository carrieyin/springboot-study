$(function () {
    var userUserCrud = new UserCRUD() ;
    $("#add").click(function () {
        userUserCrud.addUser("/apis/UserController/add") ;
    });
    $("#select").click(function(){
        userUserCrud.addUser("/apis/UserController/select")
    });
    console.info('------------------',$("#del"))
    $("#usertable tbody").on("click",".del",function () {
        // var tr = $(this).parents("tr") ;
        // var id = tr.find(":input[name='id']").val() ;
        // console.info('id : ' + id) ;
        var id =  $(this).attr("data-id") ;
        console.info(id)
        //userUserCrud.delUser("/apis/UserController/del");
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
        console.info('----- > ',item)
        var tr = $("<tr ><td><input type='hidden' value='"+item.id+"' name='id' /> "+ item.name
            +" </td><td>"+ item.addr +"</td><td><a href='#' class='del' data-id ='"+item.id+"' >删除</a>&nbsp;&nbsp;&nbsp;<a  href='#' >修改</a></td></tr>") ;
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

UserCRUD.prototype.delUser = function (url) {
    var userid = this.siblings("#userid");
    console.log(userid);
    var _slef = this;
    // var ajaxing = httpUtil.dealAjaxRequest4JSObj(url, param);
    // $.when(ajaxing).done(function (resp) {
    //     _slef.appendTable(tableBody, resp);
    // });
}