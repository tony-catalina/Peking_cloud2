define(function (require) {
    var browserV = require("browserV");
    if(browserV().lte9){
        window.location.href = '/' + window.location.pathname.split('/')[1] + '/pages/error/incomepatible.html';
    }
});