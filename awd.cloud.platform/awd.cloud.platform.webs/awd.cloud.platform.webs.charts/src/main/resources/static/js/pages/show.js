define("pages/show", [
        "../bootstrap.min",
        "../default-assets/todolist",
        "../default-assets/bootstrap-growl-active",
        "../default-assets/sparkline-small",
        "../default-assets/chart-sparkline",
        "../default-assets/column-barchart",
        "../default-assets/line-and-areachart",
        "../default-assets/c3-api-chart",
        "../default-assets/c3-chart-script",
        "../awd/pop",
        "../awd/bootstrap_growl",
    ],
    function (require) {
        var todolist = require("../default-assets/todolist");
        var bootstrap_growl_active = require("../default-assets/bootstrap-growl-active");
        var sparkline_small = require("../default-assets/sparkline-small");
        var chart_sparkline = require("../default-assets/chart-sparkline");
        var column_barchart = require("../default-assets/column-barchart");
        var line_and_areachart = require("../default-assets/line-and-areachart");
        var c3_api_chart = require("../default-assets/c3-api-chart");
        var c3_chart_script = require("../default-assets/c3-chart-script");
        var awd_pop = require("../awd/pop");
        var awd_bootstrap_growl = require("../awd/bootstrap_growl");
        var _init = function () {
            // :: Variables
            var admetro_window = $(window);
            var dd = $("dd");
            var pageWrapper = $(".admetro-page-wrapper");
            var sideMenuArea = $(".admetro-sidemenu-area");

            // :: Preloader Active Code
            admetro_window.on("load", function () {
                $("#preloader").fadeOut("1000", function () {
                    $(this).remove();
                });
            });

            // :: Slimscroll Active Code
            if ($.fn.slimscroll) {
                $('#admetroSideNav').slimscroll({
                    height: '100%',
                    size: '4px',
                    position: 'right',
                    color: '#8c8c8c',
                    alwaysVisible: false,
                    distance: '2px',
                    railVisible: false,
                    wheelStep: 15
                });
            }

            // :: Slimscroll Active Code
            if ($.fn.slimscroll) {
                $('#messageBox, #notificationsBox').slimscroll({
                    height: '340px',
                    size: '4px',
                    position: 'right',
                    color: '#8c8c8c',
                    alwaysVisible: false,
                    distance: '2px',
                    railVisible: false,
                    wheelStep: 15
                });
            }

            // :: Slimscroll Active Code
            if ($.fn.slimscroll) {
                $('#notificationsBox2').slimscroll({
                    height: '300px',
                    size: '4px',
                    position: 'right',
                    color: '#8c8c8c',
                    alwaysVisible: false,
                    distance: '2px',
                    railVisible: false,
                    wheelStep: 15
                });
            }

            // :: Slimscroll Active Code
            if ($.fn.slimscroll) {
                $('#dashboardtodoList').slimscroll({
                    height: '370px',
                    size: '4px',
                    position: 'right',
                    color: '#8c8c8c',
                    alwaysVisible: false,
                    distance: '2px',
                    railVisible: false,
                    wheelStep: 15
                });
            }

            // :: Slimscroll Active Code
            if ($.fn.slimscroll) {
                $('#pageVisitorTable').slimscroll({
                    height: '300px',
                    size: '4px',
                    position: 'right',
                    color: '#8c8c8c',
                    alwaysVisible: false,
                    distance: '2px',
                    railVisible: false,
                    wheelStep: 15
                });
            }

            // :: Slimscroll Active Code
            if ($.fn.slimscroll) {
                $('#chooseLayout').slimscroll({
                    height: '100%',
                    size: '4px',
                    position: 'right',
                    color: '#8c8c8c',
                    alwaysVisible: false,
                    distance: '2px',
                    railVisible: false,
                    wheelStep: 15
                });
            }

            // :: Menu Active Code
            $("#menuCollasped").on("click", function () {
                pageWrapper.toggleClass("menu-collasped-active");
            });

            $("#mobileMenuOpen").on("click", function () {
                pageWrapper.toggleClass("mobile-menu-active");
            });

            sideMenuArea.on("mouseenter", function () {
                pageWrapper.addClass("sidemenu-hover-active");
                pageWrapper.removeClass("sidemenu-hover-deactive");
            });

            sideMenuArea.on("mouseleave", function () {
                pageWrapper.removeClass("sidemenu-hover-active");
                pageWrapper.addClass("sidemenu-hover-deactive");
            });


            // :: Wow Active Code
            if (admetro_window.width() > 767) {
                new WOW().init();
            }

            // :: Gallery Active Code
            if ($.fn.imagesLoaded) {
                $('.gallery_full_width_images_area').imagesLoaded(function () {
                    // Filter items on button click
                    $('.portfolio-menu').on('click', 'button', function () {
                        var filterValue = $(this).attr('data-filter');
                        $grid.isotope({
                            filter: filterValue
                        });
                    });
                    // Init Isotope
                    var $grid = $('.gallery_full_width_images_area').isotope({
                        itemSelector: '.portfolio_gallery_iteam',
                        percentPosition: true
                    });
                });
            }

            // :: Gallery Menu Style Code
            $('.portfolio-menu button.btn').on('click', function () {
                $('.portfolio-menu .btn').removeClass('active');
                $(this).addClass('active');
            })

            // :: Tooltip Active Code
            if ($.fn.tooltip) {
                $('[data-toggle="tooltip"]').tooltip();
            }

            // :: Popover Active Code
            if ($.fn.popover) {
                $('[data-toggle="popover"]').popover();
            }

            // :: FooTable Active Code
            if ($.fn.footable) {
                $(".footable").footable();
            }

            // :: Nice Select Active Code
            if ($.fn.niceSelect) {
                $('select').niceSelect();
            }

            // :: Dropdown Active Code
            if ($.fn.dropdown) {
                $("dropdown-toggle").dropdown();
            }

            // :: Compose Mail Active Code
            if ($.fn.wysihtml5) {
                $("#compose-textarea").wysihtml5();
            }

            // :: Jarallax Active Code
            if ($.fn.jarallax) {
                $('.jarallax').jarallax({
                    speed: 0.2
                });
            }

            // :: Accordian Active Code
            dd.filter(":nth-child(n+3)").hide();
            $("dl").on("click", "dt", function () {
                $(this).next().slideDown(500).siblings("dd").slideUp(500);
            });

            // :: PreventDefault a Click
            $('a[href="#"]').on("click", function ($) {
                $.preventDefault();
            });

            // :: Countdown Active Code
            if ($.fn.countdown) {
                $("#clock").countdown("2021/12/24", function (event) {
                    $(this).html(event.strftime("<div>%D <span>Days</span></div> <div>%H <span>Hours</span></div> <div>%M <span>Minutes</span></div> <div>%S <span>Seconds</span></div>"));
                });
            }

            // :: Timeline Active Code
            $('#leftVersion').on('click', function (event) {
                event.preventDefault();
                $('#vertical-timeline').toggleClass('center-orientation');
            });
        }
        var _popwinbind = function () {
            $('a.popwin').each(function () {
                $(this).on('click', function () {
                    var url = $(this).attr('url');
                    var title = '';
                    //给父类复制
                    parent.document.getElementById("chartshow").value=url;
                    //调用父类方法
                    parent.popwinbind();
                });
            });
        }
        
        
        var initview = function () {
            _init();
            _popwinbind();
        }
        //初始化视图
        initview();


        //todolist.init();
        //bootstrap_growl_active.init();
        //sparkline_small.init();
        //chart_sparkline.init();
        //column_barchart.init();
        //line_and_areachart.init();
        //c3_api_chart.init();
        //c3_chart_script.init();
    });