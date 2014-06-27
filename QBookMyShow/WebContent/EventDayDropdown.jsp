<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script src="js/jquery-1.8.2.js" type="text/javascript"></script>
<script type="text/javascript">
	function Test() {
		alert("Test.");
	}

	function getEventDateTimes() {
		$("#eventDate").get(0).options.length = 0;
        $("#eventDate").get(0).options[0] = new Option("Loading Event Dates", "-1"); 
		var selectedEventName=$("#event").val();
        $.ajax({
            type: "POST",
            url: "hello",
//             data: "{eventName:" + selectedEventName+ "}",
            data: {'eventName': selectedEventName},
//             contentType: "application/json; charset=utf-8",
            dataType: "json",
            success: function(msg) {
                $("#eventDate").get(0).options.length = 0;
                $("#eventDate").get(0).options[0] = new Option("Select event", "-1"); 

                $.each(msg.d, function(index, item) {
                    $("#eventDate").get(0).options[$("#eventDate").get(0).options.length] = new Option(item.Display, item.Value);
                });
            },
            error: function(meg) {
                $("#eventDate").get(0).options.length = 0;
                alert("Failed to load events."+meg);
            }
        });

// 		$.getJSON('hello', {'eventName': selectedEventName},
// 	            function(data) {
// alert(data);
// 	                var divisionList = (data.eventDateList);

// 	                var options = $("#eventDate");
// 	                options.find('option')
// 	                .remove()
// 	                .end();
// 	                options.append($("<option />").val("-1").text("--Select--"));
// 	                $.each(divisionList, function() {

// 	                    options.append($("<option />").val(this.eventId).text(this.eventDate));
// 	                });
// 	            }
// 	        );
	}
</script>
</head>
<body>
	<s:form action="HelloWorld">
		<s:select name="username" label="Username"
			list="{'Mike','John','Smith'}" onchange="Test();" />
		<s:select name="event" id="event" list="eventList" listKey="eventId"
			listValue="eventName" headerKey="0" headerValue="Event"
			label="Select a event" onchange="getEventDateTimes();" />
			
			<select id="eventDate"></select>
	</s:form>
</body>
</html>