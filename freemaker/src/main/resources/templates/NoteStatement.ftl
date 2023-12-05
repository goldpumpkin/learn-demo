<html>
<head>
    <title>statement</title>
    <link rel="stylesheet" type="text/css" href="DailyTemplate.css"/>
</head>

<body>
<div id="bottomright">
    <span id="pagenum"></span> of <span id="pagecount"></span>
</div>
<div class="container">
    <#include "Header.ftl">
    <#include "AccountInfo.ftl">
    <#include "TradeInfoContract.ftl">
    <div class="card-first">
        <h1 class="title">NOTES</h1>
        <ol>
            <li>All documents required for the Test.</li>
            <#if displayCrossTrade=='Y'>
            <br/>
            [Principal Crossed Trade]
            </#if>
        </ol>
    </div>
</div>
</body>
</html>