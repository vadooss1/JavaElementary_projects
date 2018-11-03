<html>
<body>
<h2>Are you ready!?<br>
For servlet /atm?:<br>
request format in json:
{"atmId":0,"cardId":0,"cardType":"visa","cardHasChip":false,"pinUserEnter":0,"serviceKindId":0,"accountFromId":0,"accountOnId":0,"sumEnter":0.0,"OperationSuccess":false,"returnCard":false}<br>
request format string(example):
http://localhost:8080/vadym_zadorozhniuk-1.0-SNAPSHOT/atm?atmId=1&cardId=1&cardType=visa&cardHasChip=false&pinUserEnter=1111&serviceKindId=1&accountFromId=1&accountOnId=2&sumEnter=100&OperationSuccess=false&returnCard=false<br>
<br>
For servlet /user?:<br>
{
  "id": 1,
  "status": 1,
  "name": "vasya",
  "password": "1111",
  "age": 20,
  "sex": true,
  "messageResult": "User exists! Last message was: New user is created!"
}
</h2>
</body>
</html>