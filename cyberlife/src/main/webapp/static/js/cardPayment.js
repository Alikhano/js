$(document).ready(function() {
		
	function checkCardType() {
		var cardNumber = $('#card-number').val();
		var re = new RegExp("^4");
		if (cardNumber.match(re)) {
			console.log("visa")
			$('#card-image').html('<img src="data:image/svg+xml;base64,PD94bWwgdmVyc2lvbj0iMS4wIiA/PjwhRE9DVFlQRSBzdmcgIFBVQkxJQyAnLS8vVzNDLy9EVEQgU1ZHIDEuMS8vRU4nICAnaHR0cDovL3d3dy53My5vcmcvR3JhcGhpY3MvU1ZHLzEuMS9EVEQvc3ZnMTEuZHRkJz48c3ZnIGVuYWJsZS1iYWNrZ3JvdW5kPSJuZXcgMCAwIDEyOCAxMjgiIGlkPSLQodC70L7QuV8xIiB2ZXJzaW9uPSIxLjEiIHZpZXdCb3g9IjAgMCAxMjggMTI4IiB4bWw6c3BhY2U9InByZXNlcnZlIiB4bWxucz0iaHR0cDovL3d3dy53My5vcmcvMjAwMC9zdmciIHhtbG5zOnhsaW5rPSJodHRwOi8vd3d3LnczLm9yZy8xOTk5L3hsaW5rIj48Zz48cGF0aCBkPSJNMTE3Ljg4NiwxMDMuMDU1SDEwLjExNEM1LjYzMywxMDMuMDU1LDIsOTkuNDIyLDIsOTQuOTQxVjMzLjA1OWMwLTQuNDgxLDMuNjMzLTguMTE0LDguMTE0LTguMTE0aDEwNy43NzEgICBjNC40ODEsMCw4LjExNCwzLjYzMyw4LjExNCw4LjExNHY2MS44ODFDMTI2LDk5LjQyMiwxMjIuMzY3LDEwMy4wNTUsMTE3Ljg4NiwxMDMuMDU1eiIgZmlsbD0iI0YwRUZFRiIvPjxnPjxnPjxwb2x5Z29uIGZpbGw9IiMzNTUxNjkiIHBvaW50cz0iNDkuMjMsNzguODg4IDU0LjI5LDQ5LjE4NiA2Mi4zODksNDkuMTg2IDU3LjMyLDc4Ljg4OCA0OS4yMyw3OC44ODggICAgIi8+PHBhdGggZD0iTTg2LjY4Nyw0OS45MThjLTEuNjA0LTAuNjAzLTQuMTE4LTEuMjQ4LTcuMjU3LTEuMjQ4Yy04LDAtMTMuNjM1LDQuMDI4LTEzLjY4NCw5LjgwMiAgICAgYy0wLjA0NSw0LjI2Nyw0LjAyMiw2LjY1LDcuMDkzLDguMDdjMy4xNTMsMS40NTYsNC4yMTMsMi4zODUsNC4xOTksMy42ODVjLTAuMDIzLDEuOTkxLTIuNTE4LDIuODk5LTQuODQ3LDIuODk5ICAgICBjLTMuMjM5LDAtNC45Ni0wLjQ0OS03LjYxOS0xLjU1OGwtMS4wNDItMC40NzFsLTEuMTM3LDYuNjVjMS44ODksMC44MjksNS4zODYsMS41NDcsOS4wMTksMS41ODMgICAgIGM4LjUxMSwwLDE0LjAzMy0zLjk4MiwxNC4wOTctMTAuMTQ5YzAuMDMyLTMuMzc3LTIuMTI0LTUuOTQ4LTYuNzk1LTguMDY5Yy0yLjgzMS0xLjM3NS00LjU2Ni0yLjI5MS00LjU0OC0zLjY4MyAgICAgYzAtMS4yMzQsMS40NjgtMi41NTUsNC42MzktMi41NTVjMi42NDUtMC4wNDIsNC41NjYsMC41MzYsNi4wNTYsMS4xMzhsMC43MjksMC4zNDNMODYuNjg3LDQ5LjkxOEw4Ni42ODcsNDkuOTE4eiIgZmlsbD0iIzM1NTE2OSIvPjxwYXRoIGQ9Ik0xMDcuNDQ3LDQ5LjIxNWgtNi4yNTZjLTEuOTM5LDAtMy4zODgsMC41MjktNC4yNCwyLjQ2M0w4NC45Myw3OC45aDguNTAzYzAsMCwxLjM4Ni0zLjY2LDEuNzAzLTQuNDY0ICAgICBjMC45MjksMCw5LjE4NywwLjAxNCwxMC4zNjksMC4wMTRjMC4yNCwxLjAzOSwwLjk4Myw0LjQ1LDAuOTgzLDQuNDVoNy41MTVMMTA3LjQ0Nyw0OS4yMTVMMTA3LjQ0Nyw0OS4yMTV6IE05Ny40NjMsNjguMzYxICAgICBjMC42Ny0xLjcxMiwzLjIyNS04LjMwNCwzLjIyNS04LjMwNGMtMC4wNDUsMC4wNzksMC42NjYtMS43MiwxLjA3NC0yLjgzNmwwLjU0OCwyLjU2MmMwLDAsMS41NDksNy4wOSwxLjg3NSw4LjU3OEg5Ny40NjMgICAgIEw5Ny40NjMsNjguMzYxeiIgZmlsbD0iIzM1NTE2OSIvPjxwYXRoIGQ9Ik00Mi40NCw0OS4yMDhsLTcuOTI3LDIwLjI1NmwtMC44NDctNC4xMTVjLTEuNDcyLTQuNzQ3LTYuMDctOS44ODktMTEuMjExLTEyLjQ2Mmw3LjI0OCwyNS45NzggICAgIGw4LjU2Ni0wLjAxMWwxMi43NDctMjkuNjQ2SDQyLjQ0TDQyLjQ0LDQ5LjIwOHoiIGZpbGw9IiMzNTUxNjkiLz48cGF0aCBkPSJNMjcuMTYxLDQ5LjE5SDE0LjEwMmwtMC4xLDAuNjE3YzEwLjE1NiwyLjQ2LDE2Ljg3OCw4LjQwMiwxOS42NjQsMTUuNTQyTDMwLjgzLDUxLjY5NyAgICAgQzMwLjM0MSw0OS44MTYsMjguOTE4LDQ5LjI1NSwyNy4xNjEsNDkuMTlMMjcuMTYxLDQ5LjE5eiIgZmlsbD0iI0Y2Q0E0MSIvPjwvZz48L2c+PHBhdGggZD0iTTIsMzMuMDU5djYuODg2aDEyNHYtNi44ODZjMC00LjQ4MS0zLjYzMy04LjExNC04LjExNC04LjExNEgxMC4xMTRDNS42MzMsMjQuOTQ1LDIsMjguNTc4LDIsMzMuMDU5eiIgZmlsbD0iIzM1NTA2NyIvPjxwYXRoIGQ9Ik0yLDg3Ljk2M3Y2Ljk3N2MwLDQuMDg3LDMuMDI1LDcuNDU5LDYuOTU3LDguMDIzaDExMC4wODZjMy45MzItMC41NjMsNi45NTctMy45MzUsNi45NTctOC4wMjN2LTYuOTc3SDJ6IiBmaWxsPSIjRjZDQTQxIi8+PC9nPjwvc3ZnPg==" height="100%">');
		}
		
		if (/^(5[1-5][0-9]{14}|2(22[1-9][0-9]{12}|2[3-9][0-9]{13}|[3-6][0-9]{14}|7[0-1][0-9]{13}|720[0-9]{12}))$/.test(cardNumber)) {
			console.log("masterCard")
			$('#card-image').html('<img src="data:image/svg+xml;base64,PD94bWwgdmVyc2lvbj0iMS4wIiA/PjwhRE9DVFlQRSBzdmcgIFBVQkxJQyAnLS8vVzNDLy9EVEQgU1ZHIDEuMS8vRU4nICAnaHR0cDovL3d3dy53My5vcmcvR3JhcGhpY3MvU1ZHLzEuMS9EVEQvc3ZnMTEuZHRkJz48c3ZnIGVuYWJsZS1iYWNrZ3JvdW5kPSJuZXcgMCAwIDEyOCAxMjgiIGlkPSLQodC70L7QuV8xIiB2ZXJzaW9uPSIxLjEiIHZpZXdCb3g9IjAgMCAxMjggMTI4IiB4bWw6c3BhY2U9InByZXNlcnZlIiB4bWxucz0iaHR0cDovL3d3dy53My5vcmcvMjAwMC9zdmciIHhtbG5zOnhsaW5rPSJodHRwOi8vd3d3LnczLm9yZy8xOTk5L3hsaW5rIj48Zz48cGF0aCBkPSJNMTE3Ljg4NiwxMDMuMDU1SDEwLjExNEM1LjYzMywxMDMuMDU1LDIsOTkuNDIyLDIsOTQuOTQxVjMzLjA1OWMwLTQuNDgxLDMuNjMzLTguMTE0LDguMTE0LTguMTE0aDEwNy43NzEgICBjNC40ODEsMCw4LjExNCwzLjYzMyw4LjExNCw4LjExNHY2MS44ODFDMTI2LDk5LjQyMiwxMjIuMzY3LDEwMy4wNTUsMTE3Ljg4NiwxMDMuMDU1eiIgZmlsbD0iIzQ2QjE5QiIvPjxnPjxwYXRoIGQ9Ik01NC43MjcsNjQuMDg1YzAtMTUuMzk0LDEyLjQ3OS0yNy44NzIsMjcuODczLTI3Ljg3MmMxNS4zOTUsMCwyNy44NywxMi40NzcsMjcuODcsMjcuODcyICAgIGMwLDE1LjM5Mi0xMi40NzUsMjcuODcyLTI3Ljg3LDI3Ljg3MkM2Ny4yMDYsOTEuOTU3LDU0LjcyNyw3OS40NzcsNTQuNzI3LDY0LjA4NUw1NC43MjcsNjQuMDg1eiIgZmlsbD0iI0Y2Q0E0MSIvPjxwYXRoIGQ9Ik02My45Myw0My4zMzhjMC4wMTQsMC0xLjQ1OSwxLjI3NC0yLjQ0NCwyLjUyMmg0Ljk1OWMwLDAsMS42OTUsMS44MzMsMi4zMjIsMy4yMTZoLTkuNjkyICAgIGMwLDAtMC45MzksMS40NzYtMS41NjQsMi43MjRoMTIuODJjMCwwLDEuMTcyLDIuMTkzLDEuNDI3LDMuMzUxSDU2LjE3MWMwLDAtMC41MzUsMS41MTktMC43NTksMi42MzZsMTcuMDYzLTAuMDIyICAgIGMwLjQ2OSwyLjE0MywxLjYwNyw4LjM3My0wLjczOCwxNS4yNTVsLTE1LjUsMC4wMjFjMC4yNDcsMC44MjQsMC42NywxLjk0MywxLjA3MiwyLjc2N2gxMy4zMTIgICAgYy0wLjUzOCwxLjExOS0xLjMyLDIuNTI0LTEuNzY3LDMuMjMzbC05Ljc1OSwwLjAwN2MwLjUxNSwwLjc4MSwxLjM0LDIuMDEsMS44NzYsMi41N2w2LjAwOS0wLjAwMyAgICBjLTEuMTI4LDEuNTU3LTMuMDM4LDMuMTctMy4wMzgsMy4xN2gtMC4wNDdsMC4wNjIsMC4wMjFjLTQuOTM3LDQuNDQ2LTExLjQ3NCw3LjE1MS0xOC42NDEsNy4xNTEgICAgYy0xNS4zOTQsMC0yNy44NzItMTIuNDgtMjcuODcyLTI3Ljg3MmMwLTE1LjM5NCwxMi40NzctMjcuODcyLDI3Ljg3Mi0yNy44NzJDNTIuNDcxLDM2LjIxMyw1OC45OTUsMzguOTA3LDYzLjkzLDQzLjMzOCAgICBMNjMuOTMsNDMuMzM4eiIgZmlsbD0iI0M1NUM0NCIvPjxnPjxwYXRoIGQ9Ik01MC43Miw1OS43MjdsLTAuNDUsMi41MDdsLTIuNDQ1LTAuMDYyYzAsMC0xLjIyMSwwLjMxOS0xLjIyMSwwLjgzNGMwLDAuNTE0LDAuOSwxLjE1OSwyLjI1MSwxLjczNiAgICAgYzEuMzUyLDAuNTgxLDEuNDE2LDIuMTg2LDEuMjg2LDMuMjgxYy0wLjEyOCwxLjA5My0wLjMyMSwzLjM2Mi00LjI0NCwzLjQ3NGMtMi4yNTIsMC4wNjQtMy40NzQtMC4zODYtMy40NzQtMC4zODZsMC41MTUtMi41NzIgICAgIGMwLDAsMi44OTUsMC43NzIsMy41MzgsMC4zMjFjMC42NDMtMC40NSwxLjQxNS0xLjIyMiwwLjE5Mi0xLjczNmMtMS4yMjEtMC41MTQtMy4yMTYtMS4zNTMtMy4yMTYtMy40MDggICAgIGMwLTIuMDU4LDAuODM2LTMuMjE2LDIuMTIzLTMuNzk2QzQ2Ljg1OSw1OS4zNDEsNDkuMTExLDU5LjQwMyw1MC43Miw1OS43MjdMNTAuNzIsNTkuNzI3eiIgZmlsbD0iI0ZGRkZGRiIvPjxwb2x5Z29uIGZpbGw9IiNGRkZGRkYiIHBvaW50cz0iMzEuMzk2LDcxLjIgMjguNTUzLDcxLjIgMzAuMjA2LDYxLjQyNyAyNi42OTIsNzEuMTQxIDI0LjczMSw3MS4xNDEgMjQuMjU3LDYxLjAzOCAyMi41MzgsNzEuMiAgICAgIDE5Ljg0OSw3MS4yIDIyLjM4Myw1Ni4yODUgMjUuNjQ1LDU2LjI4NSAyNi43ODcsNjQuODczIDMwLjU4LDU2LjI4NSAzMy45Myw1Ni4yODUgMzEuMzk2LDcxLjIgICAgIi8+PHBhdGggZD0iTTUzLjI1OSw3MS40MDhjLTAuOTI1LDAtMS41ODYtMC4yMDgtMS45ODItMC41OThjLTAuMzk5LTAuNDE3LTAuNTk3LTEuMDQ2LTAuNTk3LTEuODUyICAgICBjMC0wLjIxLDAuMDIyLTAuNDE3LDAuMDQ1LTAuNmMwLjAyMi0wLjIwNywwLjA2NS0wLjQ0NSwwLjA4OC0wLjcxNmwxLjcwOS05LjU0NWgyLjY2NmwtMC4zODgsMS44NjRoMi40OWwtMC40MTgsMi4zNTloLTIuNDg5ICAgICBMNTMuNyw2Ni4zOWMtMC4wNDQsMC4yNjYtMC4wODgsMC41MzUtMC4xMzMsMC44MzNjLTAuMDQzLDAuMjcxLTAuMDY1LDAuNTQxLTAuMDY1LDAuNzE5YzAsMC40MTcsMC4wNjUsMC42ODgsMC4yNDEsMC44MzggICAgIGMwLjE1NSwwLjE0OCwwLjQyLDAuMjQsMC43NzEsMC4yNGMwLjEzMywwLDAuMzA5LTAuMDMzLDAuNTI4LTAuMDkxYzAuMjIyLTAuMDg4LDAuNDIxLTAuMTQ4LDAuNTUyLTAuMjRoMC4yMjFsLTAuNDE5LDIuMzkzICAgICBjLTAuMzA5LDAuMTE5LTAuNjE2LDAuMTc5LTAuOTQ2LDAuMjM2QzU0LjExOCw3MS4zNzcsNTMuNzIyLDcxLjQwOCw1My4yNTksNzEuNDA4TDUzLjI1OSw3MS40MDh6IiBmaWxsPSIjRkZGRkZGIi8+PHBhdGggZD0iTTcyLjM4OCw2My4xMDRjLTAuNTgzLTAuNDUtMS44NzktMC4zNTktMi4xNDgsMC41NjRMNjguOTYxLDcxLjJoLTIuNjY0bDEuOTE3LTExLjIzOGgyLjY2NGwtMC4wNTUsMC45MDcgICAgIGMwLjUwOS0wLjUwOSwxLjA3MS0wLjkzOSwyLjQ5OC0wLjg5NUM3My40MTIsNTkuOTc3LDcyLjQ1Nyw2MS42NDUsNzIuMzg4LDYzLjEwNEw3Mi4zODgsNjMuMTA0eiIgZmlsbD0iI0ZGRkZGRiIvPjxwYXRoIGQ9Ik03Ny40MjQsNzEuNWMtMS40NzgsMC0yLjY0Ni0wLjUwNy0zLjQzNi0xLjQ5NmMtMC44MTctMC45ODQtMS4yMTQtMi40MTktMS4yMTQtNC4yNzQgICAgIGMwLTEuNDY1LDAuMTc4LTIuNzgxLDAuNTI5LTMuOTc1YzAuMzUyLTEuMTk3LDAuODYtMi4yMSwxLjQ3NC0zLjA3OGMwLjYxOS0wLjgzNSwxLjM0OC0xLjQ5NCwyLjIwOC0xLjk3MSAgICAgYzAuODMxLTAuNDgsMS43MzgtMC43MTksMi42ODQtMC43MTljMC43NDgsMCwxLjQ1NywwLjEyLDIuMTE3LDAuMzNjMC4zMTksMC4xMDksMC45MzQsMC40MDEsMC45MzQsMC40MDFsLTAuNjE5LDMuNTQ0ICAgICBjLTAuMzA5LTAuMzkyLTAuNjE3LTAuNzAzLTAuOTMzLTAuOTI2Yy0wLjQ2NC0wLjI5OC0xLjAxNS0wLjQ4LTEuNjMzLTAuNDhjLTEuMTAyLDAtMi4wMDUsMC42LTIuNzEsMS43OTUgICAgIGMtMC43MjYsMS4xOTYtMS4wODEsMi43MjItMS4wODEsNC41NzRjMCwxLjE5NSwwLjIsMi4wNjQsMC41OTcsMi42YzAuMzk2LDAuNTA3LDAuOTY5LDAuNzc3LDEuNzIxLDAuNzc3ICAgICBjMC42ODEsMCwxLjMxOS0wLjE4MywxLjkxNy0wLjUzOGMwLjI4MS0wLjE4NiwwLjU1Ny0wLjM4MSwwLjgyOC0wLjYwM2wtMC42MjEsMy4zODJjMCwwLTAuMzIxLDAuMTU3LTAuNDc0LDAuMjEgICAgIGMtMC4zNzIsMC4xNDgtMC43MDIsMC4yMzgtMC45ODksMC4zMjZDNzguNDE1LDcxLjQzNiw3Ny45NzYsNzEuNSw3Ny40MjQsNzEuNUw3Ny40MjQsNzEuNXoiIGZpbGw9IiNGRkZGRkYiLz48cGF0aCBkPSJNMzcuOTU3LDcwLjgxYy0wLjEwOSwwLjA5MS0wLjIyMSwwLjE1LTAuMzA5LDAuMjFjLTAuMjg2LDAuMTUtMC41MjgsMC4yNjktMC43NzEsMC4zNTcgICAgIGMtMC4yNDIsMC4wNTktMC41NzMsMC4xMjMtMS4wMTQsMC4xMjNjLTAuNjg0LDAtMS4yNTYtMC4yNzEtMS42NzQtMC43NzhjLTAuNDQxLTAuNTQxLTAuNjYyLTEuMjI3LTAuNjYyLTIuMDYyICAgICBjMC0wLjg5NiwwLjE1My0xLjY0NSwwLjQ2NC0yLjI3MWMwLjMwOS0wLjYwMiwwLjc2OS0xLjExLDEuMzg4LTEuNDY3YzAuNTczLTAuMzU3LDEuMjU2LTAuNTk3LDIuMDI4LTAuNzQ3bDAuNTUtMC4wOTEgICAgIGMwLjYzOS0wLjExOSwxLjMwMS0wLjIwNywyLjAyOC0wLjI0YzAtMC4wNTksMC0wLjExOSwwLjAyMi0wLjIwOGMwLjAyMi0wLjA4OCwwLjAyMi0wLjE3OSwwLjAyMi0wLjI5OCAgICAgYzAtMC40NzgtMC4xNTUtMC44MDctMC40ODUtMS4wMTdjLTAuMzMxLTAuMTc4LTAuODE2LTAuMjY3LTEuNDU0LTAuMjY3aC0wLjEzM2MtMC4zOTcsMC4wMjktMC44MzcsMC4xMTktMS4zMjEsMC4yOTggICAgIGMtMC41MjksMC4yMS0wLjkyNywwLjM1OS0xLjE5MSwwLjUwOWgtMC4yNDJsMC4zOTctMi42NmMwLjMwOS0wLjExOSwwLjc5My0wLjIzOSwxLjQzMy0wLjM1OCAgICAgYzAuMzA4LTAuMDkxLDAuNjE2LTAuMTIsMC45MjUtMC4xNTFjMC4zMzItMC4wNiwwLjY4My0wLjA2LDEuMDE1LTAuMDZjMS4zMDEsMCwyLjI0NywwLjIzOSwyLjg2NCwwLjY4NyAgICAgYzAuNjE3LDAuNDUsMC45MDQsMS4xNjgsMC45MDQsMi4xNTJjMCwwLjEyMSwwLDAuMjk4LTAuMDIyLDAuNTFjLTAuMDIyLDAuMjEtMC4wNDUsMC4zOS0wLjA2NywwLjU2N0w0MS4zNTIsNzEuMmgtMi42NDYgICAgIGwwLjItMS4xOTdjLTAuMTU1LDAuMTUtMC4zNzUsMC4zMjktMC41OTYsMC41NEMzOC4xNzgsNzAuNjMxLDM4LjA2OCw3MC43MjIsMzcuOTU3LDcwLjgxTDM3Ljk1Nyw3MC44MXogTTM3Ljk1Nyw2Ni4xNSAgICAgYy0wLjI4NywwLjA2LTAuNTI4LDAuMTQ4LTAuNzI4LDAuMjRjLTAuMzA4LDAuMTQ3LTAuNTI5LDAuMzU1LTAuNjgyLDAuNjI2Yy0wLjE3NywwLjI3MS0wLjI0MywwLjYyOC0wLjI0MywxLjA3NiAgICAgYzAsMC4zOSwwLjExLDAuNjU3LDAuMzA4LDAuODA3YzAuMjIxLDAuMTQ4LDAuNTA4LDAuMjQsMC45MjYsMC4yNGMwLjEzMiwwLDAuMjY1LTAuMDI5LDAuNDE5LTAuMDkxICAgICBjMC4xMzMtMC4wMjgsMC4yNjYtMC4wODgsMC40Mi0wLjE0OGMwLjI4Ni0wLjE3OSwwLjU3MS0wLjM5LDAuODM2LTAuNjI3bDAuMzk3LTIuMzkxYy0wLjQ2MiwwLjA2LTAuOTI2LDAuMTIxLTEuMzIsMC4yMSAgICAgQzM4LjE3OCw2Ni4wOSwzOC4wNjgsNjYuMTE5LDM3Ljk1Nyw2Ni4xNUwzNy45NTcsNjYuMTV6IiBmaWxsPSIjRkZGRkZGIi8+PHBhdGggZD0iTTYxLjAwMyw2MS45MDRjLTAuMzUyLDAuMDYtMC42ODIsMC4yNC0wLjk0NiwwLjUzNmMtMC4zNzUsMC40MjEtMC42NjIsMC45NTktMC44NTksMS42NzZoMS44MDZoMS43ODYgICAgIGMwLTAuMTE5LDAuMDIyLTAuMjEsMC4wMjItMC4zMjljMC0wLjA4OCwwLjAyMi0wLjIwOCwwLjAyMi0wLjI5NmMwLTAuNTQxLTAuMTMzLTAuOTI4LTAuMzc2LTEuMjI5ICAgICBjLTAuMjQxLTAuMjY2LTAuNTcxLTAuNDE2LTEuMDM1LTAuNDE2QzYxLjI5LDYxLjg0NSw2MS4xMzYsNjEuODczLDYxLjAwMyw2MS45MDRMNjEuMDAzLDYxLjkwNHogTTYxLjAwMyw2OC45NTlINjEuNCAgICAgYzAuNTczLDAsMS4xMjQtMC4xMTksMS42NTMtMC4zOWMwLjUwNi0wLjI3MSwwLjk3LTAuNTY3LDEuMzQyLTAuODk2aDAuMzA5bC0wLjUwNiwyLjg5OGMtMC41MDYsMC4zMDMtMS4wNTgsMC41NC0xLjYyOSwwLjY5ICAgICBjLTAuNDg1LDAuMTQ4LTEuMDE1LDAuMjA5LTEuNTY2LDAuMjRoLTAuMjQxYy0xLjQzMiwwLTIuNTEzLTAuNDE5LTMuMjg0LTEuMjg2Yy0wLjc3LTAuODM4LTEuMTQ2LTIuMDAzLTEuMTQ2LTMuNDk2ICAgICBjMC0xLjAxNywwLjEzNC0xLjk3MiwwLjM3Ny0yLjgxMmMwLjI0MS0wLjg2NSwwLjYxNi0xLjYxNCwxLjA3OC0yLjI3MWMwLjQ2My0wLjYyNywxLjAxNS0xLjEwNCwxLjY5Ny0xLjQ5MyAgICAgYzAuNDg1LTAuMjM5LDAuOTkzLTAuNDE4LDEuNTIxLTAuNTA5YzAuMjQyLTAuMDI4LDAuNDg0LTAuMDI4LDAuNzI4LTAuMDI4YzEuMjM1LDAsMi4xNiwwLjMyNiwyLjc5OSwxLjAxNCAgICAgYzAuNjE2LDAuNjU5LDAuOTI3LDEuNjc0LDAuOTI3LDIuOTkxYzAsMC40NDgtMC4wMjIsMC44OTctMC4wODgsMS4zNDZjLTAuMDY3LDAuNDE2LTAuMTU1LDAuODY2LTAuMjY1LDEuMjgzaC00LjEwMWgtMi4wNzEgICAgIHYwLjExOXYwLjExOWMwLDAuNzc3LDAuMjIxLDEuMzc2LDAuNjE3LDEuODIyQzU5Ljg4MSw2OC42Niw2MC4zNjQsNjguODk4LDYxLjAwMyw2OC45NTlMNjEuMDAzLDY4Ljk1OXoiIGZpbGw9IiNGRkZGRkYiLz48cGF0aCBkPSJNODUuOTc1LDcwLjgxYy0wLjEwOSwwLjA5MS0wLjIyMSwwLjE1LTAuMzI5LDAuMjFjLTAuMjY0LDAuMTUtMC41MjcsMC4yNjktMC43NjksMC4zNTcgICAgIGMtMC4yNDMsMC4wNTktMC41NzYsMC4xMjMtMC45OTMsMC4xMjNjLTAuNzA3LDAtMS4yNTctMC4yNzEtMS42OTYtMC43NzhjLTAuNDQxLTAuNTQxLTAuNjQxLTEuMjI3LTAuNjQxLTIuMDYyICAgICBjMC0wLjg5NiwwLjE1My0xLjY0NSwwLjQ2Ni0yLjI3MWMwLjMwNy0wLjYwMiwwLjc2OS0xLjExLDEuMzY3LTEuNDY3YzAuNTcxLTAuMzU3LDEuMjU1LTAuNTk3LDIuMDQ2LTAuNzQ3ICAgICBjMC4xNzgtMC4wMzMsMC4zNTMtMC4wNiwwLjU1LTAuMDkxYzAuNjE5LTAuMTE5LDEuMzAzLTAuMjA3LDIuMDA4LTAuMjRjMC4wMjEtMC4wNTksMC4wMjEtMC4xMTksMC4wNDEtMC4yMDggICAgIGMwLTAuMDg4LDAuMDI0LTAuMTc5LDAuMDI0LTAuMjk4YzAtMC40NzgtMC4xNzQtMC44MDctMC40ODgtMS4wMTdjLTAuMzI5LTAuMTc4LTAuODE0LTAuMjY3LTEuNDUxLTAuMjY3aC0wLjEzNSAgICAgYy0wLjQxNywwLjAyOS0wLjg1NywwLjExOS0xLjMxOSwwLjI5OGMtMC41MzEsMC4yMS0wLjkyOCwwLjM1OS0xLjE5MSwwLjUwOWgtMC4yNDFsMC4zNzYtMi42NiAgICAgYzAuMzA5LTAuMTE5LDAuNzktMC4yMzksMS40NTMtMC4zNThjMC4zMDktMC4wOTEsMC42MTQtMC4xMiwwLjkyMi0wLjE1MWMwLjMzNS0wLjA2LDAuNjYtMC4wNiwxLjAxNS0wLjA2ICAgICBjMS4zMDIsMCwyLjI0NiwwLjIzOSwyLjg2NywwLjY4N2MwLjU5MywwLjQ1LDAuOTAyLDEuMTY4LDAuOTAyLDIuMTUyYzAsMC4xMjEsMCwwLjI5OC0wLjAyMSwwLjUxICAgICBjLTAuMDI0LDAuMjEtMC4wNDUsMC4zOS0wLjA3MSwwLjU2N0w4OS4zNjksNzEuMmgtMi42NDZsMC4yMDQtMS4xOTdjLTAuMTc5LDAuMTUtMC4zNzYsMC4zMjktMC42MTcsMC41NEw4NS45NzUsNzAuODEgICAgIEw4NS45NzUsNzAuODF6IE04NS45NzUsNjYuMTVjLTAuMjg0LDAuMDYtMC41MjYsMC4xNDgtMC43NDYsMC4yNGMtMC4yODUsMC4xNDctMC41MzEsMC4zNTUtMC42ODEsMC42MjYgICAgIGMtMC4xNTksMC4yNzEtMC4yNDYsMC42MjgtMC4yNDYsMS4wNzZjMCwwLjM5LDAuMTEyLDAuNjU3LDAuMzMzLDAuODA3YzAuMTk3LDAuMTQ4LDAuNTA3LDAuMjQsMC45MDQsMC4yNCAgICAgYzAuMTI5LDAsMC4yODgtMC4wMjksMC40MzgtMC4wOTFjMC4xMzUtMC4wMjgsMC4yNjctMC4wODgsMC4zOTctMC4xNDhjMC4zMDktMC4xNzksMC41NzItMC4zOSwwLjg0LTAuNjI3bDAuNDE3LTIuMzkxICAgICBjLTAuNDg0LDAuMDYtMC45MjYsMC4xMjEtMS4zNDUsMC4yMUM4Ni4xNzUsNjYuMDksODYuMDY2LDY2LjExOSw4NS45NzUsNjYuMTVMODUuOTc1LDY2LjE1eiIgZmlsbD0iI0ZGRkZGRiIvPjxwYXRoIGQ9Ik05Ny4yMjIsNjMuMTA0Yy0wLjU4My0wLjQ1LTEuODc5LTAuMzU5LTIuMTQ4LDAuNTY0TDkzLjc5Nyw3MS4yaC0yLjY2NWwxLjkxNy0xMS4yMzhoMi42NjRsLTAuMDU1LDAuOTA3ICAgICBjMC41MDUtMC41MDksMS4wNzEtMC45MzksMi41MDItMC44OTVDOTguMjQ2LDU5Ljk3Nyw5Ny4yOTEsNjEuNjQ1LDk3LjIyMiw2My4xMDRMOTcuMjIyLDYzLjEwNHoiIGZpbGw9IiNGRkZGRkYiLz48cGF0aCBjbGlwLXJ1bGU9ImV2ZW5vZGQiIGQ9Ik0xMDIuNDU4LDYyLjM1MmMtMC4xMzMsMC4wNi0wLjI2MiwwLjE1Mi0wLjM3NCwwLjI0ICAgICBjLTAuMzU1LDAuMjM4LTAuNjQsMC42LTAuODgxLDEuMDE3Yy0wLjIsMC40MTYtMC4zNzYsMC44OTctMC40ODQsMS41MjRjLTAuMTM1LDAuNTk3LTAuMiwxLjE5Ny0wLjIsMS43NjQgICAgIGMwLDAuNjg4LDAuMTMzLDEuMTY1LDAuMzUsMS40NjJjMC4yNDcsMC4zMjksMC41OTcsMC40ODEsMS4wNiwwLjQ4MWMwLjE3OSwwLDAuMzU1LTAuMDMzLDAuNTI5LTAuMTIxICAgICBjMC4wODgtMC4wMzEsMC4xNTUtMC4wNTksMC4yNDMtMC4xMTljMC4yNjQtMC4xNSwwLjUwNS0wLjMwMiwwLjc0OC0wLjUwOWwwLjk0Ny01LjUyOWMtMC4xNTMtMC4wOTEtMC4zMjktMC4xODMtMC41NzEtMC4yNDEgICAgIGMtMC4yMjItMC4wODgtMC40MTktMC4xMTctMC41OTgtMC4xMTdDMTAyLjk0Miw2Mi4yMDQsMTAyLjcwMSw2Mi4yNjEsMTAyLjQ1OCw2Mi4zNTJMMTAyLjQ1OCw2Mi4zNTJ6IE0xMDIuNDU4LDcwLjYwMyAgICAgYy0wLjIsMC4xNzktMC40MjEsMC4zNTctMC42NjIsMC41MDdjLTAuMjE5LDAuMTE5LTAuNDQsMC4yMzgtMC42MzYsMC4yOThjLTAuMjI0LDAuMDYtMC40ODgsMC4wOTEtMC43NzIsMC4wOTEgICAgIGMtMC44MTcsMC0xLjQ1Ny0wLjM2LTEuOTQxLTEuMDQ4Yy0wLjQ2Mi0wLjcxNi0wLjcwNS0xLjczMy0wLjcwNS0zLjAxOWMwLTEuMDc2LDAuMTA5LTIuMDkzLDAuMzc2LTMuMDE5ICAgICBjMC4yNDItMC45MjQsMC41OTMtMS43NjQsMS4wMzYtMi40NzdjMC40NDEtMC43MiwwLjk0Ni0xLjI1OSwxLjUzOS0xLjY3N2MwLjU3Ni0wLjM5LDEuMTQ4LTAuNTk3LDEuNzY1LTAuNjI4aDAuMDg4ICAgICBjMC40NiwwLDAuODYsMC4wNiwxLjE5MSwwLjE3OWMwLjMyOSwwLjExOSwwLjY4NCwwLjMzLDEuMDM1LDAuNTk5bDAuNTkzLTMuOTE5aDIuNjY1TDEwNS41ODcsNzEuMmgtMi42NjVsMC4xOTYtMS4xNjUgICAgIEMxMDIuODc3LDcwLjI0MSwxMDIuNjU1LDcwLjQyNCwxMDIuNDU4LDcwLjYwM0wxMDIuNDU4LDcwLjYwM3oiIGZpbGw9IiNGRkZGRkYiIGZpbGwtcnVsZT0iZXZlbm9kZCIvPjwvZz48L2c+PC9nPjwvc3ZnPg==" height="100%">');
		};
	
		
	};
	
	$('#card-number').blur(function(event) {
		event.preventDefault();
		checkCardType();
	})
});