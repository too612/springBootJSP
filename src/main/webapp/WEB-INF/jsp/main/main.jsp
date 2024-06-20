<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<%@ include file= "../include/header.jsp" %>
<head>
    
    <meta http-equiv="content-type" content="text/html; charset=utf-8" />
    <title>HTML !DOCTYPE declaration</title>
</head>
<script>
    var domainName = "http://localhost:8080";

    $(function() {
			
        // 취합
        $('#test').click(function(){
            var test = fn_callTest();
            alert("결과값->" + test);
        });
        // api
        $('#call').click(function(){
            var test = fn_callAPI();
            alert("결과값->" + test);
        });
    });

    /*
    * Function Name : fn_callTest
    * Description   : 검토의견 가져오기
    * param		 : N/A
    */
function fn_callTest(){
    return $.ajax({
            type		: "POST",
            url			: domainName+"/main/call.do",
            data		: {
                            PRJ_NO : '1'
                            },
            dataType	: "JSON", 
            async 		: false,
            success		: function(data) {
                            $.each(data, function() {
                            })
                            },
            error		: function(request, status, error) {
                            //alert("error==>" + status);
                            }
    }).responseText;
}


    /*
    * Function Name : fn_callAPI
    * Description   : api
    * param		 : N/A
    */
    function fn_callAPI(){
    return $.ajax({
            type		: "POST",
            url			: domainName+"/common/api/ApiSend/sendApiList.do",
            data		: {
                            PRJ_NO : '1'
                            },
            dataType	: "JSON", 
            async 		: false,
            success		: function(data) {
                            $.each(data, function() {
                            })
                            },
            error		: function(request, status, error) {
                            //alert("error==>" + status);
                            }
    }).responseText;
}
</script>
<body>
    <!-- 페이지 소개, 검색, 제목 로고 등-->
    <header>
        <!-- 네비게이션바는 주로 header 안에 구성한다.-->
        <nav class="navbar">
            <div class="navbar__logo">
              <i class="fas fa-blog"></i>
              <a href="">AXCE Coding</a>
            </div>
      
            <ul class="navbar__menu">
              <li><a href="#">Home</a></li>
              <li><a href="#">Gallery</a></li>
              <li><a href="#">Weddings</a></li>
              <li><a href="#">FAQ</a></li>
              <li><a href="#">Bookings</a></li>
            </ul>
      
            <ul class="navbar__icons">
              <li><i class="fab fa-google"></i></li>
              <li><i class="fab fa-slack"></i></li>
            </ul>
            
            <a href="#" class="navbar__toggleBtn">
                <i class="fas fa-hamburger"></i>
              </a>
        </nav>
    </header>
    <!-- body의 핵심내용, 핵심주제 / 확장하는 콘텐츠 -->
    <main>
        <button id="test">111</button>
        <button id="call">call</button>
    </main>
    <!-- 문서의 독립적인 구획 -->
    <section>
        <!-- 문서, 페이지 안에 독립적 구획-->
        <article></article>
        <article></article>
    </section>
    <!-- 문서에 간접적 관련있는 컨텐츠들 -->
    <aside>
    </aside>
    <!-- 가장 아래, 작성자, 저작권 정보 등-->
    <footer>
    </footer>
</body>
</html>