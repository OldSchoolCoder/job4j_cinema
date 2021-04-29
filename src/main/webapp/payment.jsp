<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.Collection" %>
<%@ page import="javax.servlet.http.HttpSession" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<!doctype html>
<html lang="en">

<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
            integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n"
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
            integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
            crossorigin="anonymous"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script>
        function validate() {
            let counter = 0;
            $('form').find('input').each(function () {
                if ($(this).val() == '') {
                    alert('Enter ' + $(this).attr('id'));
                    return false;
                } else {
                    counter++;
                }
            });
            if (counter == 2) {
                $("form").submit();
            }
            return false;
        }
    </script>
    <title>Payment</title>
</head>

<body>
<div class="container pt-3">
    <div class="card mb-3 ">
        <div class="card-header fw-light">
            <%
                Object row = session.getAttribute("row");
                Object cell = session.getAttribute("cell");
            %>
            Вы выбрали ряд <%=row%> место <%=cell%>, Сумма : 499 рублей.
        </div>
        <div class="card-body fw-light">
            <form action="/cinema/enroll" method="post">
                <div class="input-group mb-3 fw-light">
                    <span class="input-group-text fw-light border-success" id="basic-addon1">Enter</span>
                    <input type="text" name="name" class="form-control fw-light border-success" placeholder="Name"
                           aria-label="Username" aria-describedby="basic-addon1" id="name">
                </div>
                <div class="mb-3">
                    <label for="phone" class="form-label fw-light">Phone Number</label>
                    <input type="text" name="phone" class="form-control fw-light border-success" id="phone"
                           placeholder="+7...">
                </div>
                <button type="submit" onclick="return validate()"
                        class="btn btn-success fw-light btn-sm">Enroll
                </button>
            </form>
        </div>
    </div>
</div>
<!-- JavaScript Bundle with Popper -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW"
        crossorigin="anonymous"></script>
</body>

</html>