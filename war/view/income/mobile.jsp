<%@ include file="../../WEB-INF/template/util.jsp" %>

<!DOCTYPE html>
<html>
<head>
	<title><fmt:message key="income.calculator" /></title>
	<link rel="stylesheet"  href="http://code.jquery.com/mobile/1.0a1/jquery.mobile-1.0a1.min.css" />
	<link rel="stylesheet" type="text/css" href="/css/util.css" />
	<link rel="stylesheet" type="text/css" href="/css/message.css" />
	<link rel="stylesheet" type="text/css" href="/css/grid.css" />
</head> 
<body> 
	<div data-role="page" data-theme="b" >
		<div data-role="header" data-theme="b">
			<h1>
				<fmt:message key="income.calculator" />
			</h1>
		</div>
		<div data-role="content">
			<div>
				<fmt:message key="income.about"/>
			</div>
			<stripes:errors />
			<stripes:messages />
			<stripes:form beanclass="com.caiubi.incometax.action.IncomeAction">
				<div data-role="fieldcontain">
					<stripes:label for="grossIncome"/>
					<stripes:text id="grossIncome" name="grossIncome" />
				</div>
				
				<div data-role="fieldcontain">
					<stripes:label for="dependents"/>
				 	<input type="range" name="dependents" id="dependents" value="${actionBean.dependents}" min="0" max="100"/>
				</div>
				
				<c:if test="${actionBean.income != null}">
				<ul data-role="listview" data-inset="true" data-theme="c" data-dividertheme="b">
					<li data-role="list-divider">
						<fmt:message key="income.inss" />
					</li>
					<li class="right">
						<a href="#">${actionBean.income.inss.value}</a>
					</li>
					<li data-role="list-divider">
						<fmt:message key="income.irrf" />
					</li>
					<li class="right">
						<a href="#">${actionBean.income.irrf.value}</a>
					</li>
					<li data-role="list-divider">
						<fmt:message key="income.netIncome" />
					</li>
					<li class="right">
						<a href="#">${actionBean.income.netIncome}</a>
					</li>
					<li data-role="list-divider">
						<fmt:message key="graph" />
					</li>
					<li class="center">
						<img src="${actionBean.chart}" />
					</li>
					<li data-role="list-divider">
						<fmt:message key="income.fgts" />
					</li>
					<li class="right">
						<a href="#">${actionBean.income.fgts.value}</a>
					</li>
				</ul>
			</c:if>
				
				<div data-role="fieldcontain">
					<input id="calculate" name="calculate" value="<fmt:message key="calculate"></fmt:message>" type="submit" data-theme="b" data-icon="check" />
				</div>
			</stripes:form>
		</div>
		<div data-role="footer" data-theme="b" class="center">
			View mode: 
			<stripes:link beanclass="com.caiubi.incometax.action.ViewAction" event="mobile" rel="external">Mobile</stripes:link>
			<stripes:link beanclass="com.caiubi.incometax.action.ViewAction" event="desktop" rel="external">Desktop</stripes:link>
		</div>
	</div>
	<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.3/jquery.min.js"></script>
	<script src="http://code.jquery.com/mobile/1.0a1/jquery.mobile-1.0a1.min.js"></script>
	<script type="text/javascript" src="/lib/priceFormat.js"></script>
	<script type="text/javascript">
		$(document).ready(function(){
			$('#grossIncome').priceFormat({
			    prefix: 'R$ ',
			    centsSeparator: ',',
			    thousandsSeparator: '.'
			}).focus();
		});
	</script>	
</body>
</html>