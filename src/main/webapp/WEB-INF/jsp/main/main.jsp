<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<%@ include file= "../include/header.jsp" %>
<head>
    
    <meta http-equiv="content-type" content="text/html; charset=EUC-KR" />
    <title>HTML !DOCTYPE declaration</title>
</head>
<script>
    var domainName = "http://localhost:8080";

    $(function() {
			
        // ����
        $('#test').click(function(){
            var test = fn_callTest();
            alert("�����->" + test);
        });
        // api
        $('#call').click(function(){
            var test = fn_callAPI();
            alert("�����->" + test);
        });
    });

    /*
    * Function Name : fn_callTest
    * Description   : �����ǰ� ��������
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

    function fn_fetchGet() {
        fetch("http://api.odcloud.kr/api/15077586/v1/centers?page=1&perPage=10&serviceKey=SFJXw8594jTUvCqXeYftokgCI0Bz8gS3%2FdAYN7gnv0atIE%2BJJwZyqQZczX2z6rSGhGsgHeMO7bfc5Oe6v2MN5Q%3D%3D")
        .then(function(response) {
            return response.json();
        })
        .then(function(myJson) {
            console.log(JSON.stringify(myJson));
        })
        .then(function(error){
            console.log("error:", error);
        });
    }

    function fn_fetchPost() {
        fetch("https://jsonplaceholder.typicode.com/posts", {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify({
                title: "Test",
                body: "I am testing!",
                userId: 1,
            }),
            })
            .then((response) => response.json())
            .then((data) => console.log(data))
            .catch((error) => console.log("error:", error));
    }
    

    function fn_api() {
        fetch(domainName+ "/main/api.do", {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify({
                title: "api",
                body: "I am testing!",
                userId: 1,
            }),
            })
        .then((response) => response())
        .then((data) => console.log(data))
        .catch((error) => console.log("error:", error));
    }

</script>
<body>
    <!-- https://boomrabbit.tistory.com/245 -->
    <!-- ������ �Ұ�, �˻�, ���� �ΰ� ��-->
    <header>
        <!-- �׺���̼ǹٴ� �ַ� header �ȿ� �����Ѵ�.-->
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
    <!-- body�� �ٽɳ���, �ٽ����� / Ȯ���ϴ� ������ -->
    <main>
        <button id="test">111</button>
        <button id="call">call</button>
        <button onclick="fn_fetchGet()">fetch get</button>
        <button onclick="fn_fetchPost()">fetch post</button>
        <button onclick="fn_api()">api</button>
    </main>
    <!-- ������ �������� ��ȹ -->
    <section>
        <!-- ����, ������ �ȿ� ������ ��ȹ-->
        <article></article>
        <article></article>
    </section>
    <!-- ������ ������ �����ִ� �������� -->
    <aside>
    </aside>
    <!-- ���� �Ʒ�, �ۼ���, ���۱� ���� ��-->
    <footer>
    </footer>
</body>
</html>