<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Add ToDo Item</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.css">
</head>
<body>
    <div class="container">
        <h1 class="p-3">Add a ToDo Item</h1>
        <form:form id="todoForm" action="/saveToDoItem" method="post" modelAttribute="todo">
            <div class="form-group row">
                <label for="title" class="col-md-3 col-form-label">Title</label>
                <div class="col-md-6">
                    <form:input type="text" path="title" id="title" class="form-control" required="required" />
                </div>
            </div>
            <div class="form-group row">
                <label for="date" class="col-md-3 col-form-label">Date</label>
                <div class="col-md-6">
                    <form:input type="date" path="date" id="date" class="form-control" required="required" />
                </div>
            </div>
            <div class="form-group row">
                <label for="status" class="col-md-3 col-form-label">Status</label>
                <div class="col-md-6">
                    <form:input type="text" path="status" id="status" class="form-control" value="Incomplete" />
                </div>
            </div>
            <div class="form-group row p-2">
                <div class="col-md-2">
                    <button type="submit" class="btn btn-success">Save Pls</button>
                </div>
            </div>
        </form:form>
    </div>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.js"></script>
    <script th:inline="javascript">
        $(document).ready(function() {
            var msg = "${message}";
            console.log(msg);
            if (msg === "Save Failure") {
                toastr.error("Something went wrong with the save.");
            }

            toastr.options = {
                "closeButton": true,
                "debug": false,
                "newestOnTop": false,
                "progressBar": true,
                "positionClass": "toast-top-right",
                "preventDuplicates": false,
                "showDuration": "300",
                "hideDuration": "1000",
                "timeOut": "5000",
                "extendedTimeOut": "1000",
                "showEasing": "swing",
                "hideEasing": "linear",
                "showMethod": "fadeIn",
                "hideMethod": "fadeOut"
            };
        });
    </script>
    <script>
        $(document).ready(function() {
            $('#todoForm').submit(function(event) {
                var selectedDate = new Date($('#date').val());
                var today = new Date();
                today.setHours(0, 0, 0, 0);
                if (selectedDate < today) {
                    event.preventDefault();
                    toastr.error('Past dates are not allowed.');
                }
            });
        });
    </script>
</body>
</html>
