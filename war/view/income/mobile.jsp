<%@ include file="../../WEB-INF/template/util.jsp" %>

<!DOCTYPE html>
<html>
<head>
	<title><fmt:message key="income.calculator" /></title>
	<link rel="apple-touch-icon" href="/img/logo.png" />
	<link rel="stylesheet" href="http://code.jquery.com/mobile/1.0a2/jquery.mobile-1.0a2.min.css" />
	<link rel="stylesheet" type="text/css" href="/css/util.css" />
	<link rel="stylesheet" type="text/css" href="/css/message.css" />
</head> 
<body> 
	<div data-role="page" data-theme="b" >
		<div data-role="header">
			<h1>
				<fmt:message key="income.calculator" />
			</h1>
		</div>
		<div data-role="content">
			<stripes:errors />
			<stripes:messages />
			<stripes:form beanclass="com.caiubi.incometax.action.IncomeAction">
				<div data-role="fieldcontain">
					<stripes:label for="grossIncome"/>
					<stripes:text id="grossIncome" name="grossIncome" />
				</div>
				
				<div data-role="fieldcontain">
					<stripes:label for="dependents"/>
				 	<input type="range" name="dependents" id="dependents" value="${actionBean.dependents}" min="0" max="10"/>
				</div>
				
				<c:if test="${actionBean.income != null}">
					<ul data-role="listview" data-inset="true" data-theme="c" data-dividertheme="b">
						<li data-role="list-divider">
							<fmt:message key="income.inss" />
						</li>
						<li class="right">
							${actionBean.income.inss.value}
						</li>
						<li data-role="list-divider">
							<fmt:message key="income.irrf" />
						</li>
						<li class="right">
							${actionBean.income.irrf.value}
						</li>
						<li data-role="list-divider">
							<fmt:message key="income.netIncome" />
						</li>
						<li class="right">
							${actionBean.income.netIncome}
						</li>
					</ul>
					<ul data-role="listview" data-inset="true" data-theme="c" data-dividertheme="b">
						<li data-role="list-divider">
							<fmt:message key="income.fgts" />
						</li>
						<li class="right">
							${actionBean.income.fgts.value}
						</li>
					</ul>
				</c:if>
				
				<input type="hidden" name="calculate" value="" />
				<input id="calculate" name="calculate" value="<fmt:message key="calculate"></fmt:message>" type="submit" data-theme="b" data-icon="check" /> 
			</stripes:form>
		</div>
		
		<div data-role="footer" class="center">
			<div data-role="controlgroup" data-type="horizontal" >
				<stripes:link beanclass="com.caiubi.incometax.action.ViewAction" event="mobile" rel="external">Mobile</stripes:link>
				<stripes:link beanclass="com.caiubi.incometax.action.ViewAction" event="desktop" rel="external">Desktop</stripes:link>
			</div>
		</div>
	</div>
	<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.3/jquery.min.js"></script>
	<script src="http://code.jquery.com/mobile/1.0a2/jquery.mobile-1.0a2.min.js"></script>
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