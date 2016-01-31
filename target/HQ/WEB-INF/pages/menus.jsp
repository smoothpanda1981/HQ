<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<nav class="navbar navbar-default">
    <div class="container-fluid">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <img class="navbar-brand" alt="brand" src="http://icons.iconarchive.com/icons/graphicloads/100-flat-2/24/bank-icon.png">
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <li class="active"><a href="/">Home<span class="sr-only">(current)</span></a></li>
                <li><a href="http://<%= request.getLocalName() %>:8161/admin/index.jsp">Active MQ</a></li>
                <li><a href="https://ywangubuntu.wiki.zoho.com/How-to-set-up-a-ubuntu-server.html">Zoho Wiki</a></li>
            </ul>
            <form class="navbar-form navbar-left" role="search">
                <div class="form-group">
                    <input type="text" class="form-control" placeholder="Keywords">
                </div>
                <button type="submit" class="btn btn-default">Search</button>
            </form>
            <ul class="nav navbar-nav navbar-right">
                <li><a href="tests">Test Page</a></li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">Settings<span class="caret"></span></a>
                    <ul class="dropdown-menu" role="menu">
                        <li><a href="http://<%= request.getLocalName() %>:8161/admin/index.jsp">Active MQ</a></li>
                        <li><a href="https://ywangubuntu.wiki.zoho.com/How-to-set-up-a-ubuntu-server.html">Zoho Wiki</a></li>
                        <li class="divider"></li>
                        <li><a href="#">Logout</a></li>

                    </ul>
                </li>
            </ul>
        </div><!-- /.navbar-collapse -->
    </div><!-- /.container-fluid -->
</nav>