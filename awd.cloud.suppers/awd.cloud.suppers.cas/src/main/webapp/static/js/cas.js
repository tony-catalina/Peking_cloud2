function requestGeoPosition() {
    console.log("Requesting GeoLocation data from the browser...");
    if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(showGeoPosition, logGeoLocationError,
            {maximumAge: 600000, timeout: 5000, enableHighAccuracy: true});
    } else {
        console.log("Browser does not support Geo Location");
    }
}

function logGeoLocationError(error) {
    switch (error.code) {
        case error.PERMISSION_DENIED:
            console.log("User denied the request for GeoLocation.");
            break;
        case error.POSITION_UNAVAILABLE:
            console.log("Location information is unavailable.");
            break;
        case error.TIMEOUT:
            console.log("The request to get user location timed out.");
            break;
        default:
            console.log("An unknown error occurred.");
            break;
    }
}

function showGeoPosition(position) {
    $('[name="geolocation"]').val(position.coords.latitude + ","
        + position.coords.longitude + "," + position.coords.accuracy + "," + position.timestamp);
}

function areCookiesEnabled() {
    if ($.cookie == undefined) {
        console.log("JQuery Cookie library is not defined")
        return;
    }
    $.cookie('cookiesEnabled', 'true');
    var value = $.cookie('cookiesEnabled');
    $.removeCookie('cookiesEnabled');
    console.log(value);
    return value != undefined;
    
}

function animateCasMessageBoxes() {
    //flash error box
    $('#msg.errors').animate({backgroundColor: 'rgb(187,0,0)'}, 30).animate({backgroundColor: 'rgb(255,238,221)'}, 500);

    //flash success box
    $('#msg.success').animate({backgroundColor: 'rgb(51,204,0)'}, 30).animate({backgroundColor: 'rgb(221,255,170)'}, 500);

    //flash confirm box
    $('#msg.question').animate({backgroundColor: 'rgb(51,204,0)'}, 30).animate({backgroundColor: 'rgb(221,255,170)'}, 500);
}

function disableEmptyInputFormSubmission() {

    $('#fm1 input[name="loginname"],[name="password"]').on("input", function (event) {
/*        var enableSubmission = $('#fm1 input[name="loginname"]').val().trim() &&
                               $('#fm1 input[name="password"]').val().trim();
		console.log("enableSubmission",enableSubmission);
*/
	if ($.trim($('#fm1 input[name="loginname"]').val())&&$.trim($('#fm1 input[name="password"]').val())) {
            $("#fm1 input[name=submit]").removeAttr('disabled');
            event.stopPropagation();
        } else {
            $("#fm1 input[name=submit]").attr('disabled', 'true');
        }
    });

    /**
     * Handle auto-complete events to the extent possible.
     */
    setTimeout(function(){
        var uid = $("#loginname").val();
        
        if (uid != null && uid != "") {
            $("#loginname").change();
            $("#loginname").focus();
            $("#fm1 input[name=submit]").removeAttr('disabled');
        }

    }, 100);

}

$(document).ready(function () {
    console.log("trackGeoLocation",trackGeoLocation);
        if (trackGeoLocation) {
            requestGeoPosition();
        }
        if ($(":focus").length === 0) {
            $("input:visible:enabled:first").focus();
        }

        if (areCookiesEnabled()) {
            $('#cookiesDisabled').hide();
        } else {
            $('#cookiesDisabled').show();
            $('#cookiesDisabled').animate({backgroundColor: 'rgb(187,0,0)'}, 30).animate({backgroundColor: 'rgb(255,238,221)'}, 500);
        }

        animateCasMessageBoxes();
        disableEmptyInputFormSubmission();

        $('#capslock-on').hide();
        $('#loginname-null').hide();
        $('#fm1 input[name="loginname"],[name="password"]').trigger("input");
        $('#fm1 input[name="loginname"]').focus();
		$('#loginname').blur(function (e) {
            var s = String.fromCharCode(e.which);
            var loginnameValue =$("#loginname").val();
//            var jsbhValue = $("select[name='jsbhchoice']").find("option:selected").val();
            var jsbhValue = $("input[name='jsbhchoice']").val();
            if(jsbhValue==0)jsbhValue="000000000"
            $("input[name='username']").val(loginnameValue+"@"+jsbhValue);
            $("input[name='jsbh']").val(jsbhValue);
            if(loginnameValue==""){
            	$('#loginname-null').show();
            }else{
            	$('#loginname-null').hide();
            }
            if (s.toUpperCase() === s && s.toLowerCase() !== s && !e.shiftKey) {
                $('#capslock-on').show();
            } else {
                $('#capslock-on').hide();
            }
        });
        $('#password').keypress(function (e) {
            var s = String.fromCharCode(e.which);
            var loginnameValue =$("#loginname").val();
            if(loginnameValue==""){
            	$('#loginname-null').show();
            }else{
            	$('#loginname-null').hide();
            }
            if (s.toUpperCase() === s && s.toLowerCase() !== s && !e.shiftKey) {
                $('#capslock-on').show();
            } else {
                $('#capslock-on').hide();
            }
        });
        if (typeof(jqueryReady) == "function") {
            jqueryReady();
        }
    });
