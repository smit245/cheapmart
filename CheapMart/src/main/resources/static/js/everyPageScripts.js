//common pages scripts
$('#registration').validate({
    errorClass: 'help-block animation-pullUp', // You can change the animation class for a different entrance animation - check animations page
    errorElement: 'div',
    errorPlacement: function(error, e) {
        e.parents('.form-group ').append(error);
    },
    highlight: function(e) {
        $(e).closest('.form-group').removeClass('has-success has-error').addClass('has-error');
        $(e).closest('.help-block').remove();
    },
    success: function(e) {
        // You can use the following if you would like to highlight with green color the input after successful validation!
        // e.closest('.form-group').removeClass('has-success has-error');
        e.closest('.form-group').removeClass('has-success has-error').addClass('has-success');
        e.closest('.help-block').remove();
        // if (e.closest('.form-group').find('.help-block').length === 2) {
        //     e.closest('.help-block').remove();
        // } else {
            //e.closest('.form-group').removeClass('has-success has-error');
            //e.closest('.help-block').remove();
        // }
    },
    rules: {
        'name': {
            required: true,
        },
        'phone': {
            required: true,
            digits: true,
            minlength: 10,
            maxlength: 10
        },
        'email': {
            required: true,
            email: true
        },
        'password': {
            required: true,
            minlength: 5
        },
        'confirm-password': {
            required: true,
            equalTo: '#password'
        },
        'pincode': {
            required: true,
            digits: true,
            minlength: 6,
            maxlength: 6
        },
        'city': {
            required: true
        },
        'state': {
            required: true,
        },
        'address': {
            required: true,
            minlength: 5
        },
       	'gender':{
       		required: true,
       	}
    },
    messages: {
        'name': {
            required: "Please Enter Your Name",
        },
        'phone': {
            required: "Please Enter Your Phone Number",
            digits: "Only Disgits Are Allowed",
            minlength: "Must Be Of 10 Digits",
            maxlength: "Must Be Of 10 Digits"
        },
        'email': {
            required: "Please Enter Your Email",
            email: "Email is Not In Proper Formate"
        },
        'password': {
            required: "Please Enter The Password",
            minlength: "Minimum Length Should Be Atleast 5"
        },
        'confirm-password': {
            required: "Please Enter Confirm Password",
            equalTo: 'Confirm Password is Not Matching To Your Password'
        },
        'pincode': {
            required: "Please Enter Your Pincode",
            digits: "Pincode Must Be of Digits",
            minlength: "Pincode Must Be Length 6 ",
            maxlength: "Pincode Must Be Length 6 "
        },
        'city': {
            required: "Please Select Your City"
        },
        'state': {
            required: "Please Enter State",
        },
        'address': {
            required: "Please Enter Proper Address",
            minlength: 5
        },
        'gender':{
            required: "Please Select your Gender.",
        },
        
    }
});

$('#login').validate({
    errorClass: 'help-block animation-pullUp', // You can change the animation class for a different entrance animation - check animations page
    errorElement: 'div',
    errorPlacement: function(error, e) {
        e.parents('.form-group ').append(error);
    },
    highlight: function(e) {
        $(e).closest('.form-group').removeClass('has-success has-error').addClass('has-error');
        $(e).closest('.help-block').remove();
    },
    success: function(e) {
        // You can use the following if you would like to highlight with green color the input after successful validation!
        // e.closest('.form-group').removeClass('has-success has-error');
        e.closest('.form-group').removeClass('has-success has-error').addClass('has-success');
        e.closest('.help-block').remove();
        // if (e.closest('.form-group').find('.help-block').length === 2) {
        //     e.closest('.help-block').remove();
        // } else {
            //e.closest('.form-group').removeClass('has-success has-error');
            //e.closest('.help-block').remove();
        // }
    },
    rules: {
        'email': {
            required: true,
            email: true
        },
        'password': {
            required: true,
            minlength: 5
        },
    },
    messages: {
        'email': {
            required: "Please Enter Your Email",
            email: "Email is Not In Proper Formate"
        },
        'password': {
            required: "Please Enter The Password",
            minlength: "Minimum Length Should Be Atleast 5"
        },
    }
});



//pincode api code to get city and state.
$(".pincode").on('keyup change',function(){
    let pincode=$(this).val();
    if(pincode.length==6){
        $(".city").val("");
        $.ajax({
            url:'https://api.postalpincode.in/pincode/'+pincode,
            type:'GET',
            error: function (error) {
                console.log(error);
                $.bootstrapGrowl('<h4><strong>Unsuccess!!!</strong></h4> <p>'+error.statusText+'</p>', {
                    type: "danger",
                    delay: 5000,
                    allow_dismiss: true,
                    offset: {from: 'top', amount: 20},
                    align: "center",
                    width: 400
                });
            },
            success:function(resp){
                console.log(resp);
                if(resp[0].Status == "Success"){
					$('.city').val(resp[0].PostOffice[0].District);
                    $(".state").val(resp[0].PostOffice[0].State);
                }
                else if(resp[0].Status == "Error"){
                    $(".state").val("");
                    $.bootstrapGrowl('<h4><strong>Wrong!!!</strong></h4> <p>Pincode is Wrong</p>', {
                        type: "danger",
                        delay: 3000,
                        allow_dismiss: true,
                        offset: {from: 'top', amount: 20},
                        align: "center",
                        width: 300
                    });
                }
                else{
                    $(".state").val("");
                    $.bootstrapGrowl('<h4><strong>Wrong!!!</strong></h4> <p>Pincode is Wrong</p>', {
                        type: "danger",
                        delay: 3000,
                        allow_dismiss: true,
                        offset: {from: 'top', amount: 20},
                        align: "center",
                        width: 300
                    });
                }
            }
        });
    }else{
        $(".city").val("");
        $(".state").val("");
    }
});

if (window.location.href.indexOf("?loggedout") > -1) {
     $.bootstrapGrowl('<h4><strong>Logged Out!!!</strong></h4> <p>You Have Been Logged Out</p>', {
        type: "info",
        delay: 4000,
        allow_dismiss: true,
        offset: {from: 'top', amount: 20},
        align: "center",
        width: 300
    });
}
if (window.location.href.indexOf("?successregister") > -1) {
     $.bootstrapGrowl('<h4><strong>Registered...</strong></h4> <p>You Have Successfully Registered Please Login</p>', {
        type: "success",
        delay: 4000,
        allow_dismiss: true,
        offset: {from: 'top', amount: 20},
        align: "center",
        width: 300
    });
}

if (window.location.href.indexOf("?alreadyregistered") > -1) {
     $.bootstrapGrowl('<h4><strong>!!Already Registered!!</strong></h4> <p>Email Already Exists Please Login</p>', {
        type: "danger",
        delay: 5000,
        allow_dismiss: true,
        offset: {from: 'top', amount: 20},
        align: "center",
        width: 300
    });
}

if (window.location.href.indexOf("?error") > -1) {
     $.bootstrapGrowl('<h4><strong>!!Wrong!!</strong></h4> <p>Your Credentials Are Wrong</p>', {
        type: "danger",
        delay: 5000,
        allow_dismiss: true,
        offset: {from: 'top', amount: 20},
        align: "center",
        width: 300
    });
}

if (window.location.href.indexOf("?success") > -1) {
     $.bootstrapGrowl('<h4><strong>Success...</strong></h4> <p>Logged in Successfully</p>', {
        type: "success",
        delay: 5000,
        allow_dismiss: true,
        offset: {from: 'top', amount: 20},
        align: "center",
        width: 300
    });
}

if (window.location.href.indexOf("?addproduct") > -1) {
     $.bootstrapGrowl('<h4><strong>Success...</strong></h4> <p>Product Added Successfully</p>', {
        type: "success",
        delay: 5000,
        allow_dismiss: true,
        offset: {from: 'top', amount: 20},
        align: "center",
        width: 300
    });
}