<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html" charset="UTF-8">

<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>

<title>Search</title>
</head>
<body>
	<!-- http://localhost:8080/SpringMvcAjaxExample/user/ -->
	
	<form>
		<div>
			<label>Name</label> <input type="text" name="name" id="name" />
		</div>
		<button type="button" onclick="search();">Search</button>
	</form>

	<h2>List</h2>

	<table id="result">
		<thead>
			<tr>
				<td>ID</td>
				<td>NAME</td>
			</tr>
		</thead>
		<tbody>
		</tbody>
	</table>

<script type="text/javascript">
		function search() {
			$.ajax({
				type : "post",
				url : "${pageContext.request.contextPath}/user/search/",
				data : "name=" + $("#name").val(),
				success : function(res) {
					var result = "<thead><tr><td>ID</td><td>NAME</td></tr></thead>";
					result += "<tbody>";
					$.each(res, function(k, v) {
						result += "<tr>";
						result += "<td>";
						result += v.id;
						result += "</td>";
						result += "<td>";
						result += v.name;
						result += "</td>";
						result += "</tr>";
						});
					result += "</tbody>";
					$("#result").html(result);
					},
					error : function() {
						alert('Error while request!');
						}
					});
			}
	</script>
</body>
</html>