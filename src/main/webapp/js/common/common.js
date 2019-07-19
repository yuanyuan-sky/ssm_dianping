
//生命一个对象，如果这个对象存在，则使用已存在的对象，如果不存在，再创建一个
var common = window.common || {};

//向common中添加一个属性，该属性是一个方法
common.showMessage = function (msg) {
    if (msg) {
        alert(msg);
    }
}