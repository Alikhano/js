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
	        minlength: "Your password must be at least 5 characters long"
	      }
	    },
	  });
	

});