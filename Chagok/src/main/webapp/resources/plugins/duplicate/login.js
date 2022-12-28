/**
 * 회원가입 - register.jsp 적용파일
 */
$(document).ready(function(){
   
   $('#sbtn').click(function(){
   
    var id = $('#id').val();   
    var pw = $('#pw').val();

    var check_id = /^[A-Za-z0-9_]+[A-Za-z0-9]*[@]{1}[A-Za-z0-9]+[A-Za-z0-9]*[.]{1}[A-Za-z]{1,3}$/; // 이메일 양식 검사    
    var check_pw = /^[a-zA-Z0-9]{4,16}$/; // 비밀번호 유효성 검사 (영문/숫자 4-16)

    //  이메일 공백 확인
    if (id == "" || id == null) {
       Swal.fire({
            title: '이메일을 입력해주세요.', 
            icon: 'warning'
          });
         $('#id').focus();
         return false;
     } 
    if(!check_id.test(id)){
       Swal.fire({
		  	title: '이메일 형식으로 입력해주세요.', 
            icon: 'warning'
          });
       	 $('#id').focus();
       	return false;
     }   

    // 비밀번호 공백 확인
    if (pw == "" || pw == null) {
       Swal.fire({
            title: '비밀번호를 입력해주세요.',
            icon: 'warning'
          });
        $('#pw').focus();
        return false;
    }   
    
    // 비밀번호 재입력 공백 확인
    if (rpw == "" || rpw == null) {
       Swal.fire({
            title: '비밀번호를 다시 입력해주세요.',
            icon: 'warning'
          });
         $('#rpw').focus();
         return false;
     } 
    
    // 비밀번호 일치 체크
    if($("#pw").val() != $("#rpw").val()){
       $('.pwReCheck').html('비밀번호가 일치하지 않습니다.');
        $("#rpw").val("");
        $("#pw").focus();
        return false;
    } else {
        $('.pwReCheck').html('');
    }   
    
   });
   
});

// 유효성 체크
$(function(){

	////////이메일 ///////////////////
	$("#id").on("blur", function(){
		if($("#id").val().trim() == "" ){
			$('.idCheck').html("이메일을 입력하세요.");
		} 
	});

	// 이메일 중복 체크
	$("#id").keyup(function() {
		var id = $("#id").val();
		
		if(/^[A-Za-z0-9_]+[A-Za-z0-9]*[@]{1}[A-Za-z0-9]+[A-Za-z0-9]*[.]{1}[A-Za-z]{1,3}$/.test($('#id').val())){
			$.ajax({
	    		 type :'post', // 서버에 전송하는 http방식
	    		 url :'/checkId', // 서버 요청 url
	    		 headers : {'Content-Type' : 'application/json'},
	    		 dataType : 'text', //서버로 부터 응답받을 데이터의 형태 
		   		 data : id, // 서버로 전송할 데이터 // 위에서 지정한 const id 
		   		 success : function(result) { // 매개변수에 통신성공시 데이터가 저장된다.
						//서버와 통신성공시 실행할 내용 작성.
						console.log('통신 성공! ' + result);
						console.log(result === 'available');
		   		 
			   		 	if(result === 'available'){
			   		 		 $('.idCheck').html('<b id="textstyle">다시 확인하세요</b>');
			   		 	}else{
			   		 		 $('.idCheck').html('<b id="textstyle2">유효한 이메일 주소입니다.</b>');
			   		 	}
					},
					error : function(request, status, error)  { //통신에 실패했을때
						console.log('통신실패');
				        console.log("code: " + request.status)
				        console.log("message: " + request.responseText)
				        console.log("error: " + error);
					}
	     	}); // end ajax(아이디 중복 확인)
		}
	  });

	//////// 이메일 ///////////////////
	
	///// 비밀번호  ///////////
	$("#pw").on("blur", function(){
		if($("#pw").val().trim() == "" ){
			$('.pwCheck').html("비밀번호를 입력하세요.");
		}
		
		if($("#pw").val().length > 0 && $("#pw").val().length < 4){
			$('.pwCheck').html("4자 이상 입력하세요.");
		}
		
		if($("#pw").val().length > 4){
			if(!$("#pw").val()=="" && !/^(?=.*[a-zA-Z])(?=.*[0-9]).{0,}$/.test($('#pw').val())){ 
				$('.pwCheck').html("영문자,숫자를 포함하여 4~16자로 입력하세요.");
			}
		}
		
	});
	
	$('#pw').keyup(function() {
		if($("#pw").val() == $("#id").val()) {
			$('.pwCheck').html("아이디와 비밀번호는 일치할 수 없습니다.");
		} else {			
			$('.pwCheck').html("");
		}
	});
	///////// 비밀번호 ///////////
	
	/////// 비밀번호 확인 ///////////
	$("#rpw").on("blur", function(){
		if($("#rpw").val().trim() == "" ){
			$('.pwReCheck').html("비밀번호 확인을 입력하세요.");
		} else if($("#rpw").val() != $("#pw").val()){
				$('.pwReCheck').html("비밀번호가 일치하지 않습니다.");
		}
	});
	
	$('#rpw').keyup(function() {
		if($("#rpw").val() != $("#pw").val()){
			$('.pwReCheck').html("");
		} else if($("#rpw").val() == $("#pw").val()){
			$('.pwReCheck').html('<b id="textstyle2">확인 완료!</b>');
		}
	});
	/////////////// 비밀번호 확인 //////////////////////

});  // jQeury 끝


$(function () {
    $('input').iCheck({
      checkboxClass: 'icheckbox_square-blue',
      radioClass: 'iradio_square-blue',
      increaseArea: '20%' /* optional */
    });
  });