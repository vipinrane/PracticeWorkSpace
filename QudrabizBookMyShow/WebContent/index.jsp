<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<title>AJAX in Struts 2 using JSON and jQuery</title>
<script src="js/jquery-1.8.2.js" type="text/javascript"></script>
<script>
$(document).ready(function() {
	$('#country').change(function(event) {
		var country = $("select#country").val();
		$.getJSON('ajaxAction', {
			countryName : country
		}, function(jsonResponse) {
			$('#ajaxResponse').text(jsonResponse.dummyMsg);
			var select = $('#states');
			select.find('option').remove();
			$.each(jsonResponse.stateMap, function(key, value) {
				$('<option>').val(key).text(value).appendTo(select);
			});
		});
	});
	
// 	$('#event').change(function(event) {
// 		var country = $("select#event").val();
// // 		$.getJSON('<s:url action='populateEventDate'/>', {
// 	$.getJSON('populateEventDate', {
// 			eventName : country
// 		}, function(jsonResponse) {
// 			$('#ajaxResponse').text(jsonResponse.dummyMsg);
// 			var select = $('#eventDate');
// 			select.find('option').remove();
// 			$.each(jsonResponse.eventDateList, function(key, value) {
// 				$('<option>').val(key).text(value).appendTo(select);
// 			});
// 		});
// // 		 $.ajax({
// // 	            type: "POST",
// // 	            url: "<s:url action='populateEventDate'/>",
// // 	            data: {'eventName': country},
// // 	             contentType: "application/json; charset=utf-8",
// // 	            dataType: "json",
// // 	            success: function(msg) {
// // 	                $("#eventDate").get(0).options.length = 0;
// // 	                $("#eventDate").get(0).options[0] = new Option("Select event", "-1"); 

// // 	                $.each(msg.d, function(index, item) {
// // 	                    $("#eventDate").get(0).options[$("#eventDate").get(0).options.length] = new Option(item.Display, item.Value);
// // 	                });
// // 	            },
// // 	            error: function(meg) {
// // 	                $("#eventDate").get(0).options.length = 0;
// // 	                alert("Failed to load events."+meg);
// // 	            }
// // 	        });
// 	});

$('#event').change(function(event) {
		var eName = $("select#event").val();
// 		$.getJSON('populateEventDate', {
// 			eventName : eName
// 		}, function(jsonResponse) {
// 			$('#ajaxResponse').text(jsonResponse.dummyMsg);
// 			var select = $('#eventDate');
// 			select.find('option').remove();
// 			$.each(jsonResponse.stateMap, function(key, value) {
// 				$('<option>').val(key).text(value).appendTo(select);
// 			});
// 		});

		$.ajax({
	        type:'POST',
	        dataType:'json',
// 	        url:'populateEventDate?eventName='+eName,
		url: "<s:url action='populateEventDate'/>",
        data: {'eventName': eName},
//         dataType: "text",
        contentType: "application/json; charset=utf-8",
	        		
	        success: function(jsonResponse) {
	        	$('#ajaxResponse').text(jsonResponse.dummyMsg);
	 			var select = $('#eventDate');
	 			select.find('option').remove();
	 			$.each(jsonResponse.eventDateList, function(key, value) {
 				$('<option>').val(key).text(value).appendTo(select);
	 			});
	        		            },
            error: function(jsonResponse) {
                $("#eventDate").get(0).options.length = 0;
                alert("Failed to load events."+jsonResponse);
            }
		});
	});
});
</script>
</head>
<body>
	<h3>AJAX calls to Struts 2 using JSON and jQuery</h3>
	
	<br />
	<br />
	<s:select name="event" id="event" list="eventList" listKey="eventId"
			listValue="eventName" headerKey="0" headerValue="Event"
			label="Select a event" />
	<br />
	<br />
<%-- 	<s:select id="eventDate" name="eventDate" list="{'Select eventdate'}" --%>
<%-- 		label="Select eventdate" /> --%>
		
		<s:select id="eventDate" name="eventDate" list="{'Select eventdate'}"
		listKey="ShowId" listValue="EventDate" headerKey="0" headerValue="Show"
			label="Select a show" />
		
</body>
</html>