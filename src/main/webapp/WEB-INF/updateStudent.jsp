<%--
  Created by IntelliJ IDEA.
  User: Mike_pn
  Date: 2/12/2024
  Time: 1:38 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create Student</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <script src="https://cdn.tailwindcss.com"></script>
    <link href="https://fonts.googleapis.com/css2?family=Quicksand:wght@300;400;500&display=swap" rel="stylesheet">
    <style>
        body {
            background-color: #f2f2f2;
        }

        h2 {
            text-align: center;
            color: #333333;
            font-family: 'Quicksand', sans-serif;
        }

        p, a {
            text-align: center;
            font-family: 'Quicksand', sans-serif;
            text-decoration: none;
        }

        input[type="text"], input[type="password"], input[type="date"], input[type="number"] {
            padding: 10px;
            margin-bottom: 20px;
            border: none;
            border-radius: 5px;
            background-color: #f2f2f2;
            color: #333333;
            font-size: 16px;
        }
        option {
            color: #333333;
        }

    </style>
</head>
<body>
<div class="bg-white max-w-[40vw] px-8 py-4 mx-auto mt-[10%]">
    <h2 class="text-3xl font-semibold ">Update Student</h2>
    <p>Welcome to RCA SMIS Course Creation Please Enter Details!!</p>
    <c:if test="${CreateStudenterror != null}">
        <p>
            <span style="color: red"> ${CreateCourseerror}</span>
        </p>
    </c:if>
    <c:if test="${successStudent != null}">
        <p>
            <span style="color: green"> ${successStudent}</span>
        </p>
    </c:if>
    <form class="py-4" action="updatestudent.php?page=updatestudent" method="post">
        <div class="flex flex-row gap-4 justify-between">
            <input type="hidden" name="studentId" value="${student.id}">
            <input type="text" class="w-full" placeholder="Student's First Name" required name="firstName" value="${student.firstName}">
            <input type="text" class="w-full" placeholder="Student's Last Name" required name="lastName" value="${student.lastName}">
        </div>
        <div class="flex flex-row gap-4">
            <input type="number" class="w-full" placeholder="Phone Number" required name="phone" value="${student.phoneNumber}">
            <input type="number" class="w-full" placeholder="Repeating ( 1 0r 0)" required name="repeating" value="${student.repeating ? 1 : 0}">
        </div>
        <div class="flex flex-row gap-4">
            <input type="number" class="w-full" placeholder="International ( 1 0r 0)" required name="internation" value="${student.international ? 1 : 0}">
            <input type="number" class="w-full" placeholder="Part Time ( 1 0r 0)" required name="partTime" value="${student.partTime ? 1 : 0}">
        </div>
        <div class="flex flex-row gap-4">
            <input type="date" class="w-full" placeholder="Date OF Birth" required name="birth">
            <select name="address" class="w-full bg-[#f2f2f2] border-0 rounded-md ">
                <option>Select the Address</option>
                <c:forEach items="${address}" var="ad" varStatus="studentstatus">
                    <option value="${ad.id}" <c:if test="${student.address.id eq ad.id}">selected</c:if>>${ad.city}</option>
                </c:forEach>
            </select>'
        </div>
        <div class="flex flex-row justify-between w-full py-4">
            <button class="bg-slate-500 py-3 px-10 flex items-center justify-center rounded-md text-white"><a href="liststudents">Cancel</a></button>
            <input type="submit" class="bg-indigo-500 py-3 px-10 flex items-center justify-center rounded-md text-white" value="Update Student">
        </div>
    </form>
    <p>
        <a class="hover:text-sky-500" href="liststudents.php?page=students&&user_role=adm&&action=list">
            All Students
        </a>
    </p>
</div>
</body>
</html>