/**
 * 
 * @returns
 */
    function resetTabs(){

        $("#contents > div").hide(); //Hide all content

        $("#tabs a").attr("id",""); //Reset id's      

    }



    var myUrl = window.location.href; //get URL

    var myUrlTab = myUrl.substring(myUrl.indexOf("#")); // For localhost/tabs.html#tab2, myUrlTab = #tab2     

    var myUrlTabName = myUrlTab.substring(0,4); // For the above example, myUrlTabName = #tab



    (function(){

        $("#contents > div").hide(); // Initially hide all contents

        $("#tabs li:first a").attr("id","current"); // Activate first tab

        $("#contents > div:first").fadeIn(); // Show first tab contents

        

        $("#tabs a").on("click",function(e) {

            e.preventDefault();

            if ($(this).attr("id") == "current"){ //detection for current tab

             return       

            }

            else{             

            resetTabs();

            $(this).attr("id","current"); // Activate this

            $($(this).attr('name')).fadeIn(); // Show contents for current tab

            }

        });



        for (i = 1; i <= $("#tabs li").length; i++) {

          if (myUrlTab == myUrlTabName + i) {

              resetTabs();

              $("a[name='"+myUrlTab+"']").attr("id","current"); // Activate url tab

              $(myUrlTab).fadeIn(); // Show url tab contents        

          }

        }

    })()
