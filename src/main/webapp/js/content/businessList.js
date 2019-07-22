var business={
    URL:{
      listUrl:"/business"
    },
    search:function () {
        $("#mainForm").attr("method", "GET");
        $("#mainForm").attr("action", $("#basePath").val() + business.URL.listUrl);
        $("#mainForm").submit();
    }
}