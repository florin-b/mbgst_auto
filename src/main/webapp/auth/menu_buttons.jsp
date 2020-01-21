


<%@ taglib prefix="menuoptions" uri="/WEB-INF/tlds/navigator.tld"%>



<style>
.ui-listview li {
	width: 44% !important;
	margin: 1.5% !important;
	float: left;
	border: 1px solid #ccc !important;
	box-shadow: 0 2px 3px rgba(0, 0, 0, 0.25);
}

a:link {
	background-color: #51FCB6;
}
</style>



<p align="center">${param.numeuser}</p>
<br>



<ul data-role="listview" data-icon="false" id="listMenu">

	<menuoptions:navigator tipUser="${param.tipuser}">

		<li><a href="${navdetails.link}" data-ajax="false">${navdetails.text}
				<span class="ui-li-count">${navdetails.navNumber}</span>
		</a></li>

	</menuoptions:navigator>
</ul>