<%@ include file="/WEB-INF/pages/common/header.jsp" %>

<div class="container">

    <form:form method="POST" modelAttribute="userForm" class="form-signin">
        <h2 class="form-signin-heading">Create your account</h2>
        
        <form:input type="hidden" path="userId" id="userId" />
        
        <spring:bind path="username">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <form:input type="text" path="username" class="form-control" placeholder="Username"
                            autofocus="true"></form:input>
                <form:errors path="username"></form:errors>
            </div>
        </spring:bind>

        <spring:bind path="password">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <form:input type="password" path="password" class="form-control" placeholder="Password"></form:input>
                <form:errors path="password"></form:errors>
            </div>
        </spring:bind>
        
       <!--  <div class="from-group">
        <input type="password" name="repeatPassword" class="form-control" placeholder="Repeat password">
        </div> -->
        <br>
          <input type="submit" value="Register" class="btn btn-success"> 
    </form:form>

</div>
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script src ="https://cdn.jsdelivr.net/jquery.validation/1.15.1/jquery.validate.min.js"></script>
<script type="text/javascript">
$(function() {
	 $("userForm").validate({
	    rules: {
	      username: "required",
	      password: {
	        required: true,
	        minlength: 4
	      }
	    },
	    messages: {
	      username: "Please enter your username",
	      password: {
	        required: "Please provide a password",
	        minlength: "Your password must be at least 4 characters long"
	      }
	    },
	  });
	

});
</script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>   
</body>
</html>
</body>
</html>