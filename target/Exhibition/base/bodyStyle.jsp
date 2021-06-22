<%--
  Created by IntelliJ IDEA.
  User: Vitaliy
  Date: 07.06.2021
  Time: 0:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <style>
        .hr-shelf {
            margin: -30px auto 10px;
            padding: 0;
            height: 50px;
            border: none;
            border-bottom: 1px solid #1f1209;
            box-shadow: 0 20px 20px -20px #333;
            width: 95%;
        }

        body {
            background-image: url("/base/og_og_1543218019291447512.jpg");
            background-color: #fff;
            font-family: "Bookman Old Style" ;
            color: #000000; /*Color text on page*/
            background-attachment: fixed; /* Background page is fixed */
            background-repeat: repeat; /* The image repeat on horizontal */
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

        .scroll-table-body {
            width: 112%;
            height: 350px;
            overflow-x: auto;
            margin-top: 0px;
            margin-bottom: 20px;
            border-bottom: 1px solid #eee;
        }

        .scroll-table table {
            width:100%;
            /*table-layout: initial;*/
            border: none;
            /*overflow-x: auto;*/
        }

        .scroll-table thead th {
            font-weight: bold;
            text-align: center;
            border: none;
            padding: 10px 15px;
            background: #d8d8d8;
            /*font-size: 14px;*/
            border-left: 1px solid #ddd;
            border-right: 1px solid #ddd;
        }

        .scroll-table tbody td {
            text-align: center;
            border-left: 1px solid #ddd;
            border-right: 1px solid #ddd;
            padding: 8px 10px;
            /*font-size: 14px;*/
            vertical-align: center;
        }

        .scroll-table tbody tr:nth-child(even){
            background: #f3f3f3;
        }

        /* Styles for scroll */
        ::-webkit-scrollbar {
            width: 6px;
        }
        ::-webkit-scrollbar-track {
            box-shadow: inset 0 0 6px rgba(0,0,0,0.3);
        }
        ::-webkit-scrollbar-thumb {
            box-shadow: inset 0 0 6px rgba(0,0,0,0.3);
        }
    </style>
