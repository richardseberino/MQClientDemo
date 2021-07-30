<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Banco Bogotá Demo</title>
</head>
<body>
	
	<form action="EnviaMensagens" method="post">
		<center>
	<H2>Workload</H2>
		<table >
			<tr>
				<th># Mensssages:</th>
				<td><input type="number" value=1 name="qtd" /></td>
			</tr>
			<tr>
				<th>Message:</th>
				<td>
					<textarea rows="3" cols="40" id="mensagem" name="mensagem" >{"employee_id":1, "daily":1000}</textarea>
				</td>
			</tr>
			<tr>
				<th>Queue Manager:</th>
				<td><input type="text" value="mqtest0" name="qm" /></td>
			</tr>
			<tr>
				<th>Queue Manager Port:</th>
				<td><input type="number" value="1414" name="port" /></td>
			</tr>
			<tr>
				<th>Host:</th>
				<td><input type="text" value="mq-test" name="host" /></td>
			</tr>
			<tr>
				<th>Channel:</th>
				<td><input type="text" value="DEV.APP.SVRCONN" name="channel" /></td>
			</tr>
			<tr>
				<th>Queue Name:</th>
				<td><input type="text" value="TEST.INPUT" name="queue" /></td>
			</tr>
			<tr>
				<th>Iterations:</th>
				<td><input type="number" value="1" name="iterations" /></td>
			</tr>
			<tr>
				<th>wait time (ms):</th>
				<td><input type="number" value="200" name="wait" /></td>
			</tr>
			
		</table>
		<br />
		<input type="submit" value="Generate messages" />
		</textarea>
	</center>
	</form>

</body>
</html>