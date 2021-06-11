<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<c:set var="root" value="${pageContext.request.contextPath}" />
<html>
<head>

<meta charset="UTF-8">
<title>회원가입</title>
<!-- Bootstrap core CSS -->
<link href="${root}/css/bootstrap/bootstrap.min.css" rel="stylesheet">
<!-- Custom styles for this template -->
<link href="${root}/css/user/form-validation.css" rel="stylesheet">

<script type="text/javascript">
	function createform(obj){
		if(obj.id.value ==""){
			alert("아이디를 반드시 입력하세요.");
			obj.id.focus();
			return false;
		}else if(obj.password.value ==""){
			alert("비밀번호를 반드시 입력하세요.");
			obj.password.focus();
			return false;
		}else if(obj.password.value.length < 7){
			alert("비밀번호는 7글자 이상으로 만들어주세요.");
			obj.password.focus();
			return false;
		}else if(obj.passwordCheck.value ==""){
			alert("비밀번호 확인란에 입력해주세요.");
			obj.passwordCheck.focus();
			return false;
		}else if(obj.password.value != obj.passwordCheck.value){
			alert("입력하신 비밀번호가 같지 않습니다.");
			obj.passwordCheck.focus();
			return false;
		}else if(obj.name.value ==""){
			alert("이름을 반드시 입력하세요.");
			obj.name.focus();
			return false;
		}else if(obj.email.value ==""){
			alert("이메일을 입력하세요.");
			obj.email.focus();
			return false;
		}else{
			alert('회원가입 되었습니다.');
		}

		var check = false;
		for(var i=0;i<obj.mailing.length;i++){
			if(obj.mailing[i].checked==true) check=true;
		}

		if(obj.mailing.value ==""){
			alert("메일수신 여부를 체크해주세요.");
			obj.mailing.focus();
			return false;
		}
		check = false;
		var str="";
		for(var i=0; i<obj.interest.length; i++){
			if(obj.interest[i].checked==true){
				str+=obj.interest[i].value + ",";
			}	
		}	
	}
		
	function idCheck(obj, root){
		alert(obj.id.value);
		
		if(obj.id.value ==""){
			alert("아이디를 반드시 입력하세요.");
			obj.id.focus();
			return false;
		}else{
			let url = root + "/user/idCheck?id=" + obj.id.value;
			window.open(url, "", "width=400, height=200");
		}
	}
	</script>
</head>
<body>
<div class="container">
    <main>
        <div class="py-5 text-center">
            <img class="d-block mx-auto mb-4" src="/img/board/logo.png" alt="" width="200" height="80">
            <h2>SignUp form</h2>
            <p class="lead">회원가입을 환영합니다.</p>
        </div>
        <div class="row g-5">
            <div class="col-3"></div>
            <div class="col-6">
                <div class="col-md-11 col-lg-12">
                 <form id="joinform" name="joinform" action="" method="post" onsubmit="return createform(this)">
                    <h4 class="mb-3">Billing address</h4>

                    <!-- 아이디 -->
                    <div class="col-12">
                        <label for="ID" class="form-label">ID</label>
                        <div class="input-group has-validation">
                            <span class="input-group-text">@</span> 
                            <input type="text" class="form-control" name="id" id="id" placeholder="ID" required>
                                <button type="button" onclick="idCheck(joinform, '${root}')">아이디중복</button>
                            <div class="invalid-feedback">
                                Your ID is required.</div>
                        </div>
                    </div>
                    <!-- 패스워드 -->
                    <div class="col-12">
                        <label for="Password" class="form-label">Password</label>
                        <input type="password" class="form-control"  name="password" id="password" placeholder="password" required>
                        <div class="invalid-feedback">Please enter your shipping
                            address.</div>
                    </div>
                    <!-- 패스워드체크 -->
                    
                    <div class="col-12">
                        <label for="passwordCheck" class="form-label">Passwordcheck
                            <span class="text-muted"></span></label> <input type="password" class="form-control"   id="passwordCheck" placeholder="ReWritePassword">
                    </div>
					<!-- 이름입력 -->
                        <div class="row g-3">
                            <div class="col-sm-6">
                                <label for="Name" class="form-label">name</label>
                                <input type="text" class="form-control" name="name"  id="name" placeholder="name" value="" required>
                                <div class="invalid-feedback">
                                    Valid first name is required.
                                </div>
                            </div>

							<!-- 이메일 입력 -->
                            <div class="col-12">
                                <label for="email" class="form-label">Email <span class="text-muted"></span></label>
                                <input type="email" class="form-control" name="email" id="email"
                                    placeholder="you@example.com">
                                <div class="invalid-feedback">
                                    Please enter a valid email address for shipping updates.</div>
                            </div>


                        </div>
                        <hr class="my-4"><!-- primary 파란색, secondary 회색, success 녹색, warning 노란색, info 하늘색 -->
                        <button class="w-100 btn btn-warning btn-lg" type="submit">Sign Up</button>
                    </form>
                </div>
            </div>
        </div>
        <div class="col-3"></div>
    </main>

    <footer class="my-5 pt-5 text-muted text-center text-small">
        <p class="mb-1">&copy; 2021 Camels</p>
        <ul class="list-inline">
            <li class="list-inline-item"><a href="#">Privacy</a></li>
            <li class="list-inline-item"><a href="#">Terms</a></li>
            <li class="list-inline-item"><a href="#">Support</a></li>
        </ul>
    </footer>
</div>

<!--  
	<section>
		<form id="joinform" name="joinform" action="" method="post" onsubmit="return createform(this)">

			<h4 style="text-align: center;">회원가입(*필수입력사항입니다.)</h4>
			<div class="menu" style="border-bottom-width: 0px;">


				<div id="id">아이디</div>
				<span>* <input type="text" class="checkInfo" name="id"
					size="12" />
					<button type="button" onclick="idCheck(joinform, '${root}')">아이디
						중복</button>
				</span>
			</div>

			<div class="menu " style="border-bottom-width: 0px;">
				<div id="id">비밀번호</div>
				<span>* <input type="password" class="checkInfo"
					name="password" size="12" />
				</span>
			</div>

			<div class="menu " style="border-bottom-width: 0px;">
				<div id="id">비밀번호확인</div>
				<span>* <input type="password" class="checkInfo"
					name="passwordCheck" size="12" />
				</span>
			</div>

			<div class="menu " style="border-bottom-width: 0px;">
				<div id="id">이름</div>
				<span>* <input type="text" class="checkInfo" name="name"
					size="12" />
				</span>
			</div>


			<div class="menu" style="border-bottom-width: 0px;">
				<div id="id" style="margin-left: 10px,">이메일</div>
				<span> <input type="email" name="email" size="25" />
				</span>
			</div>
			<div class="menu" style="text-align: center;">
				<span> <input type="submit" value="가입" /> <input
					type="reset" value="취소" />
				</span>
			</div>
		</form>
	</section>
	-->
</body>
</html>