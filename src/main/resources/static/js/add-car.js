 function readURL(input){
        if(input.files && input.files[0]){
            var reader = new FileReader();
            reader.onload = function(e){
                $('#imgPreview').attr('src', e.target.result).width(200).height(200);
            }
            reader.readAsDataURL(input.files[0])
        }
    }
    $('#carImage',).change(function(){
        readURL(this);
    });
    $(".custom-file-input").on("change", function() {
        var fileName = $(this).val().split("\\").pop();
        $(this).siblings(".custom-file-label").addClass("selected").html(fileName);
    });
    
    
    function readURL1(input){
        if(input.files && input.files[0]){
            var reader = new FileReader();
            reader.onload = function(e){
                $('#imgPreview1').attr('src', e.target.result).width(200).height(200);
            }
            reader.readAsDataURL(input.files[0])
        }
    }
    $('#carImage1',).change(function(){
        readURL1(this);
    });
    $(".custom-file-input").on("change", function() {
        var fileName = $(this).val().split("\\").pop();
        $(this).siblings(".custom-file-label").addClass("selected").html(fileName);
    });
    
    
    
    function readURL2(input){
        if(input.files && input.files[0]){
            var reader = new FileReader();
            reader.onload = function(e){
                $('#imgPreview2').attr('src', e.target.result).width(200).height(200);
            }
            reader.readAsDataURL(input.files[0])
        }
    }
    $('#carImage2',).change(function(){
        readURL2(this);
    });
    $(".custom-file-input").on("change", function() {
        var fileName = $(this).val().split("\\").pop();
        $(this).siblings(".custom-file-label").addClass("selected").html(fileName);
    });
    
    
    
    
    
    function readURL3(input){
        if(input.files && input.files[0]){
            var reader = new FileReader();
            reader.onload = function(e){
                $('#imgPreview3').attr('src', e.target.result).width(200).height(200);
            }
            reader.readAsDataURL(input.files[0])
        }
    }
    $('#carImage3',).change(function(){
        readURL3(this);
    });
    $(".custom-file-input").on("change", function() {
        var fileName = $(this).val().split("\\").pop();
        $(this).siblings(".custom-file-label").addClass("selected").html(fileName);
    });
    
    
    function readURL4(input){
        if(input.files && input.files[0]){
            var reader = new FileReader();
            reader.onload = function(e){
                $('#imgPreview4').attr('src', e.target.result).width(200).height(200);
            }
            reader.readAsDataURL(input.files[0])
        }
    }
    $('#profileImage',).change(function(){
        readURL4(this);
    });
    $(".custom-file-input").on("change", function() {
        var fileName = $(this).val().split("\\").pop();
        $(this).siblings(".custom-file-label").addClass("selected").html(fileName);
    });
    