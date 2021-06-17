<%--
  Created by IntelliJ IDEA.
  User: Vitaliy
  Date: 07.06.2021
  Time: 0:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; ISO-8859-1;charset=UTF-8" pageEncoding="UTF-8" %>
    <style>
        body {
            background-image: url("/base/og_og_1543218019291447512.jpg");
            background-color: #fff;
            color: #000000; /*Цвет текста на странице*/
            background-attachment: fixed; /* Фон страницы фиксируется */
            background-repeat: repeat; /* Изображение повторяется по горизонтали */
        }
        #tableExpo table, th, td
            {
            border: 1px solid black;
            border-collapse: collapse;
        }
        #userDibLeft, #userDivCenter, #userDivRight {
            height: 100%;
            border-style: solid;
            border-radius: 10px;
            padding-bottom: 20px;
            padding-top: 20px;
            margin-bottom: 20px;
            align-content: initial;
        }
        #userDivCenter{
            width: 70%;
            margin-left: 10px;
            margin-right: 10px;
            padding-left: 10%;
            padding-right: 10%;

        }
        #userDibLeft, #userDivRight{
            width: 15%;
            text-align: center;
        }
    </style>
