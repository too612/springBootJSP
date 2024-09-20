<%@ page contentType="text/html; charset=UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
	String cp = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>�� �� ��(SpringBoot-JSP)</title>

<link rel="stylesheet" type="text/css" href="resources/css/style.css"/>
<link rel="stylesheet" type="text/css" href="resources/css/created.css"/>

<script type="text/javascript" src="resources/js/util.js"></script>
<script type="text/javascript">

	function sendIt(){
		
		var f = document.myForm;
		
		str = f.subject.value;
		str = str.trim();
		if(!str){
			alert("\n������ �Է��ϼ���.");
			f.subject.focus();
			return;
		}
		f.subject.value = str;
		
		str = f.name.value;
		str = str.trim();
		if(!str){
			alert("\n�̸��� �Է��ϼ���.");
			f.name.focus();
			return;
		}		
		
		/* if(!isValidKorean(str)){
			alert("\n�̸��� ��Ȯ�� �Է��ϼ���.");
			f.name.focus()
			return;
		}*/
		
		f.name.value = str;
		
		if(f.email.value){
			if(!isValidEmail(f.email.value)){
				alert("\n�������� E-Mail�� �Է��ϼ���.");
				f.email.focus();
				return;
			}
		}
		
		str = f.content.value;
		str = str.trim();
		if(!str){
			alert("\n������ �Է��ϼ���.");
			f.content.focus();
			return;
		}
		f.content.value = str;
		
		str = f.pwd.value;
		str = str.trim();
		if(!str){
			alert("\n�н����带 �Է��ϼ���.");
			f.pwd.focus();
			return;
		}
		f.pwd.value = str;
		
		f.action = "<%=cp%>/updated_ok.action";
		f.submit();
		
	}

</script>


</head>
<body>

<div id="bbs">
	<div id="bbs_title">
		�� �� ��(SpringBoot-JSP)
	</div>
	
	<form action="" method="post" name="myForm">
	<div id="bbsCreated">
		
		<div class="bbsCreated_bottomLine">
			<dl>
				<dt>��&nbsp;&nbsp;&nbsp;&nbsp;��</dt>
				<dd>
				<input type="text" name="subject" value="${dto.subject }" size="60" 
				maxlength="100" class="boxTF"/>
				</dd>
			</dl>		
		</div>
		
		<div class="bbsCreated_bottomLine">
			<dl>
				<dt>�� �� ��</dt>
				<dd>
				<input type="text" name="name" value="${dto.name }" size="35" 
				maxlength="20" class="boxTF"/>
				</dd>
			</dl>		
		</div>
		
		<div class="bbsCreated_bottomLine">
			<dl>
				<dt>E-Mail</dt>
				<dd>
				<input type="text" name="email" value="${dto.email }" size="35" 
				maxlength="50" class="boxTF"/>
				</dd>
			</dl>		
		</div>
		
		<div id="bbsCreated_content">
			<dl>
				<dt>��&nbsp;&nbsp;&nbsp;&nbsp;��</dt>
				<dd>
				<textarea rows="12" cols="63" name="content"
				class="boxTA">${dto.content }</textarea>
				</dd>
			</dl>
		</div>
		
		<div class="bbsCreated_noLine">
			<dl>
				<dt>�н�����</dt>
				<dd>
				<input type="password" name="pwd" value="${dto.pwd }" size="35" 
				maxlength="7" class="boxTF"/>
				&nbsp;(�Խù� ���� �� ������ �ʿ�!!)
				</dd>
			</dl>		
		</div>	
	
	</div>
	
	<div id="bbsCreated_footer">
	
		<input type="hidden" name="num" value="${dto.num }"/>
		<input type="hidden" name="pageNum" value="${pageNum }"/>
		
		<input type="hidden" name="searchKey" value="${searchKey }"/>
		<input type="hidden" name="searchValue" value="${searchValue }"/>
		
		<input type="button" value=" �����ϱ� " class="btn2" onclick="sendIt();"/>
		<input type="button" value=" ������� " class="btn2" 
		onclick="javascript:location.href='<%=cp%>/list.action?${params }';"/>	
	</div>
	
	</form>

</div>


</body>
</html>
