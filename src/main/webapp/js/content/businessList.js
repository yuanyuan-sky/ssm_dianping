$(function () {
    common.showMessage($("#message").val());
});
var business={
    URL:{
      listUrl:"/business",
        modifyInitUrl:"/business/modifyInit/",
        addInit:"/business/addInit",
        DELETE_URL:"/business/"
    },
    search: function () {
        $("#mainForm").attr("method", "GET");
        $("#mainForm").attr("action", $("#basePath").val() + business.URL.listUrl);
        $("#mainForm").submit();
    },
    modifyInit:function (id) {
        location.href = $("#basePath").val() + business.URL.modifyInitUrl + id;
    },
    addInit:function () {
        location.href = $("#basePath").val() + business.URL.addInit;
    },
    remove: function (id) {
        $("#mainForm").attr("action", $("#basePath").val() + business.URL.DELETE_URL + id);
        $("#mainForm").submit();
    }
}
