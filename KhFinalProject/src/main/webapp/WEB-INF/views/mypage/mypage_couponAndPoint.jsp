<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="path" value="${pageContext.request.contextPath }"/>
<jsp:include page="/WEB-INF/views/common/header.jsp"/>
<style>
	    /*중복아이디체크관련*/
		    div#idMsg-container{position:relative; padding:0px;}
		    div#idMsg-container span.idMsg{display:none;font-size: 12px;position:absolute; top:12px; right:10px;}
		    div#idMsg-container span.ok{color:green;}
		    div#idMsg-container span.no{color:red;}
</style>

<section>

    <div class="row">
        <div class="col-md-1"></div>

        <div class="row col-md-10">

            <jsp:include page="/WEB-INF/views/mypage/mypageSidebar.jsp"/>

            <div class="col-md-10 row">
                    
                    <div class="col-md-12">
                        <h4>현재 포인트: 3,245</h4>
                        <hr>
                    </div>

                    <div class="col-md-6 row"> 
                        </h4>

                        <div class="col-md-2"></div>
                        <div class="col-md-8" style="border: 1px solid black; height:200px;">

                    
                            <h4 style="text-align: center;">3000원 할인</h4>
                            <p style="text-align: center;"><strong>2020.05.04~2020.05.11</strong></p>
                                
                            <img style="margin-left: 50%; transform: translateX(-50%);" src="menu.PNG" onclick=""/>
                            <p style="text-align: center;"><strong>10,000원 이상 주문 시 사용가능</strong></p>

                        </div>
                        <div class="col-md-2"></div>

                    </div>

                    <div class="col-md-6 row">

                        <div class="col-md-2"></div>
                        <div class="col-md-8" style="border: 1px solid black; height:200px;">

                    
                            <h4 style="text-align: center;">3000원 할인</h4>
                            <p style="text-align: center;"><strong>2020.05.04~2020.05.11</strong></p>
                                
                            <img style="margin-left: 50%; transform: translateX(-50%);" src="menu.PNG" onclick=""/>
                            <p style="text-align: center;"><strong>10,000원 이상 주문 시 사용가능</strong></p>

                        </div>
                        <div class="col-md-2"></div>

                    </div>

                    <div class="col-md-12" style="height: 50px;"></div>

                    <div class="col-md-6 row">

                        <div class="col-md-2"></div>
                        <div class="col-md-8" style="border: 1px solid black; height:200px;">

                    
                            <h4 style="text-align: center;">3000원 할인</h4>
                            <p style="text-align: center;"><strong>2020.05.04~2020.05.11</strong></p>
                                
                            <img style="margin-left: 50%; transform: translateX(-50%);" src="menu.PNG" onclick=""/>
                            <p style="text-align: center;"><strong>10,000원 이상 주문 시 사용가능</strong></p>

                        </div>
                        <div class="col-md-2"></div>

                    </div>

                    <div class="col-md-6 row">

                        <div class="col-md-2"></div>
                        <div class="col-md-8" style="border: 1px solid black; height:200px;">

                    
                            <h4 style="text-align: center;">3000원 할인</h4>
                            <p style="text-align: center;"><strong>2020.05.04~2020.05.11</strong></p>
                                
                            <img style="margin-left: 50%; transform: translateX(-50%);" src="menu.PNG" onclick=""/>
                            <p style="text-align: center;"><strong>10,000원 이상 주문 시 사용가능</strong></p>

                        </div>
                        <div class="col-md-2"></div>

                    </div>

                </div>


            </div>

            <div class="col-md-1"></div>

        </div>

    </section>

<jsp:include page="/WEB-INF/views/common/footer.jsp"/>