<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<title>AJAX in Struts 2 using JSON and jQuery</title>
<script src="js/jquery-1.8.2.js" type="text/javascript"></script>
<script>
$(document).ready(function() {
	$('#event').change(function(event) {
		var event = $("select#event").val();
		var eventName = $("select#event").text();
		$.getJSON('ajaxAction', {
			eventId: event,
			eventName: eventName
		}, function(jsonResponse) {
			$('#ajaxResponse').text(jsonResponse.dummyMsg);
			var select = $('#states');
			select.find('option').remove();
			$.each(jsonResponse.stateMap, function(key, value) {
				$('<option>').val(key).text(value).appendTo(select);
			});
		});
	});
});
</script>
</head>
<body>
	<h3>Event &amp; Event Day</h3>
	Event:<s:select id="event" name="event" list="eventList" listKey="key" listValue="value" headerKey="-1" headerValue="Select Event"/>
	<br />
	<br />
	<s:select id="states" name="states" list="{'Select EventDay'}"
		label="Select EventDay" />
	<br />
	<br />
	<div id="ajaxResponse"></div>
</body>
</html>