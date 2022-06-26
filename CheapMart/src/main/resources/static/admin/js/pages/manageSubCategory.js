


//Add Category Validation
$('#add-subcategory').validate({
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
        'name': {
            required: true,
        },
        'category':{
			required: true,
		},
    },
    messages: {
        'name': {
            required: "Name is required",
        },
        'category': {
            required: "Please Select Category",
        },
    }
});
$('#edit-subcategory').validate({
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
        'name': {
            required: true,
        },
        'category':{
			required: true,
		},
    },
    messages: {
        'name': {
            required: "Name is required",
        },
        'category': {
            required: "Please Select Category",
        },
    }
});


//edit category
//Show Details For Edit
$(".edit").click(function(){
    var id=$(this).attr("data-id");
    $("#edit-subcategoryid").val(id);
    $.ajax({
        url:"/admin/editsubcategory",
        method:"get",
        data:{id:id},
        error: function(data){
			console.log(data);
			$.bootstrapGrowl('<h4><strong>Unsuccess!!!</strong></h4> <p>'+data.statusText+'</p>', {
                type: "danger",
                delay: 3000,
                allow_dismiss: true,
                offset: {from: 'top', amount: 20},
                align: "center",
                width: 400
            });
		    
		},
        success:function(resp){
			console.log(resp);
           /*	json=$.parseJSON(resp);
            console.log(json);*/
            $("#editname").val(resp.name);
			$("#editcategory").val(resp.category).change();
            $("#editstatus").val(resp.status).change();
            $("#modal-edit-subcategory").modal("show");
        }
    });

});

$("#modal-editcategory").on("hide.bs.modal",function(){
   /* $("#edit-salon")[0].reset();
    $("#edit-logo").change();*/
});


//logo show in edit salon
$("#editimg").change(function(){
    if($(this).val() === '' ){
        $("#editdimg").hide();
    }else{
        let reader = new FileReader();
        reader.onload = function (event) {
            $("#editdimg").attr("src", event.target.result);
        };
        reader.readAsDataURL(this.files[0]);
        $("#editdimg").show();
    }
});