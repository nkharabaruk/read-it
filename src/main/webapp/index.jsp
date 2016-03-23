<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>

</head>
<body id="mainModule">
<div id="header">
    <div id="main-nav">
        <table>
            <tr>
                <td><a href="/"><img src="images/logo.png" alt=""></a></td>
                <td><a href="/">Main</a></td>
                <td><a href="#books">Books</a></td>
                <td><a href="#authors">Authors</a></td>
            </tr>
        </table>
    </div>
</div>
<div class="ng-view" id="container">
</div>
</body>
<script src="resources/assets/bower_components/requirejs/require.js"></script>
<script src="resources/app/runApp.js"></script>
</html>
