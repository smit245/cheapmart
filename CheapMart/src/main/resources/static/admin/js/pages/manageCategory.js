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


//Add Category Validation
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
$('#edit-category').validate({
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

var imgpath="/admin/img/category";

//edit category
//Show Details For Edit
$(".edit").click(function(){
    var id=$(this).attr("data-id");
    $("#edit-categoryid").val(id);
    $.ajax({
        url:"/admin/editcategory",
        method:"get",
        data:{id:id},
        error: function(data){
			console.log(data);
			$.bootstrapGrowl('<h4><strong>Unsuccess!!!</strong></h4> <p>'+data.message+'</p>', {
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
			$("#editdimg").attr("src",imgpath+"/"+resp.image);
            $("#editdimg").show();
            $("#editstatus").val(resp.status).change();
            $("#modal-edit-category").modal("show");
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