const Utils = {


    arrayToString : function(array) {
        if (!array || array.length == 0) return "";
        var str = ';';
        for (var i = 0; i < array.length; i++) {
            var obj = array[i];
            str = str + obj ;
        }
        return str;
    },

    objToString : function(obj) {
        if (!obj) return null;
        var str = ';';
        for (var i in obj) {
            var v = obj[i];
            if (v && v instanceof Object)
                v = this.objToString(v);
            str = i + "="+v+"; " ;
        }
        return str;
    },

    tableHeight : function() {
        var h = window.screen.height;
   //     console.log("height="+h);
        if (h > 400)
            return (h * 0.6) + "px";
        return (400)+"px";
    }



}

export default Utils;