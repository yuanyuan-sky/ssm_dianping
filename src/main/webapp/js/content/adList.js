$(function () {
    common.showMessage($("#message").val());

});
var adjs={
    URL:{
        removeUrl: "/ad/remove/",
        modifyInitUrl: "/ad/modifyInit/"
    },

    remove:function (id) {
        $("#id").val(id);
        var basePath = $("#basePath").val();
        $("#request_method").val("DELETE");
        $("#mainForm").attr("action", basePath + adjs.URL.removeUrl + id);
        $("#mainForm").submit();
    },
    modifyInit:function(id){
        $("#id").val(id);
        var basePath = $("#basePath").val();
        $("#mainForm").attr("action", basePath + adjs.URL.modifyInitUrl + id);
        $("#request_method").val("GET");
        $("#mainForm").submit();
    },
    search: function (currentPage) {
        $("#currentPage").val(currentPage);
        $("#request_method").val("POST");
        $("#mainForm").submit();
    }
}


