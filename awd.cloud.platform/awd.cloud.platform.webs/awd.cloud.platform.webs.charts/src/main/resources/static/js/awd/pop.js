define("awd/pop", [
        "../pop.min"
    ],
    function (require) {
        var _popwin = function (title, url, param) {
            pop.iframe({
                title: " ",
                buttonSpcl: "max|close",
                anim: "fadeIn-zoom",
                width: "100%",
                height: "100%",
                id: undefined,
                place: 5,
                drag: true,
                dragSize: true,
                index: true,
                toClose: false,
                mask: true,
                class: false,
                src: url
            });

        }
        return {
            popwin: _popwin
        }
    });