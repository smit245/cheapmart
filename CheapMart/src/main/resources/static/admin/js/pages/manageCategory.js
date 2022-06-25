//logo show in add salon
$("#img").change(function(){
    if($(this).val() === '' ){
        $("#dimg").hide();
    }else{
        let reader = new FileReader();
        reader.onload = function (event) {
            $("#dimg").attr("src", event.target.result);
        };
        reader.readAsDataURL(this.files[0]);
        $("#dimg").show();
    }
});


//Edit Salon Validation
$('#add-category').validate({
    errorClass: 'help-block animation-pullUp', // You can change the animation class for a different entrance animation - check animations page
    errorElement: 'div',
    errorPlacement:
    function(error, e) {
        e.parents('.form-group > div').append(error);
    },
    highlight: function(e) {
        $(e).closest('.form-group').removeClass('has-success has-error').addClass('has-error');
        $(e).closest('.help-block').remove();
    },
    success: function(e) {
        // You can use the following if you would like to highlight with green color the input after successful validation!
        // e.closest('.form-group').removeClass('has-success has-error'); // e.closest('.form-group').removeClass('has-success has-error').addClass('has-success');
        // e.closest('.help-block').remove();
        if (e.closest('.form-group').find('.help-block').length === 2) {
            e.closest('.help-block').remove();
        } else {
            e.closest('.form-group').removeClass('has-success has-error');
            e.closest('.help-block').remove();
        }
    },
    rules: {
        'img': {
			required:true,
            accept:"image/*",
        },
        'name': {
            required: true,
        },
    },
    messages: {
        'img': {
			required:'Image is required',
            accept:"Only images Are Allowed",
        },
        'name': {
            required: "Name is required",
        },
    }
});

$("#add-category").submit(function(event){
	if($(this).valid()){
		return true;
	}else{
		event.preventDefault();
		return false;
	}
	
});