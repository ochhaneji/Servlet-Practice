        function  validate(frm){
        	alert("client side validations.....");
        	//change validation flag status to "yes" when form validations are enabled
        	frm.vflag.value="yes";
        	//read form data
        	var name=frm.pname.value;
        	var age=frm.page.value;
        	//empty Error Messages
        	document.getElementById("pnameErr").innerHTML="";
        	document.getElementById("pageErr").innerHTML="";
        	
        	//client side form validation logics
        	if(name==""){  //reuquired rule
        		document.getElementById("pnameErr").innerHTML="name is mandatory";
        	    frm.pname.focus();
        		return false;
        	}
        	if(age==""){
        		document.getElementById("pageErr").innerHTML="age is mandatory";
        		frm.page.focus();
        		return false;
        	}
        	else if(isNaN(age)){
        		document.getElementById("pageErr").innerHTML="age must be numeric value";
        		frm.page.focus();
        		frm.page.value="";
        		return false;
        	}
        	else  if(age<1  || age>125){
        		document.getElementById("pageErr").innerHTML="age must be there in the range of 1 through 125";
        		frm.page.focus();
        		frm.page.value="";
        		return false;
        	}
        	return true;
        }