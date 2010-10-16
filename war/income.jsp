<%@ include file="WEB-INF/template/util.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>
		<fmt:message key="income.calculator" />
	</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta name="google-site-verification" content="CAkLG-sq5pL9uF6x7WxEvdGDWlZ--9PTyo-j2Duu9js" />
	<link rel="shortcut icon" href="/img/favicon.ico" />
	<link rel="stylesheet" type="text/css" href="http://yui.yahooapis.com/3.1.2/build/cssreset/reset-min.css" />
	<link rel="stylesheet" type="text/css" href="/css/structure.css" />
	<c:if test="${actionBean.caiubiContext.mobile}">
		<link rel="stylesheet" type="text/css" href="/css/mobileStructure.css" />
	</c:if>
	<link rel="stylesheet" type="text/css" href="/css/f4m.css" />
	<link rel="stylesheet" type="text/css" href="/css/message.css" />
	<link rel="stylesheet" type="text/css" href="/css/grid.css" />
	<link rel="stylesheet" type="text/css" href="/css/jquery.spinner.css" />
</head>
<body>
	<div id="container">
		<stripes:form beanclass="com.caiubi.incometax.action.IncomeAction" class="f4m">
			<fieldset>
				<legend>
					<fmt:message key="income.calculator" />
				</legend>
				<div class="info">
					<fmt:message key="income.about" />
				</div>
				<stripes:errors />
				<stripes:messages />
				<ol>
					<li>
						<stripes:label for="grossIncome"/>
						<stripes:text id="grossIncome" name="grossIncome" class="large right" tabindex="1" />
						<strong class="red">*</strong>
					</li>
					<li>
						<stripes:label for="dependents" />
						<span>
							<stripes:text id="dependents" name="dependents" class="large" tabindex="2" />
							<img id="up" src="/img/up.png"/>
							<img id="down" src="/img/down.png" />
						</span>
						<strong class=red>*</strong>
					</li>
					<li class="action right round">
						<stripes:submit id="calculate" name="calculate" class="submit round" tabindex="3"/>
					</li>
				</ol>
				<c:if test="${actionBean.income != null}">
					<div>
						<table class="grid">
							<caption class="center"><fmt:message key="income.discounts" /></caption>
							<thead>
								<tr>
									<td></td>
									<td><fmt:message key="base.income" /></td>
									<td><fmt:message key="aliquot" /></td>
									<td><fmt:message key="value" /></td>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td>
										<fmt:message key="income.inss" />
									</td>
									<td class="right">
										${actionBean.income.inss.baseSalary}
									</td>
									<td class="right">
										${actionBean.income.inss.baseAliquot}
									</td>
									<td class="right">
										${actionBean.income.inss.value}
									</td>
								</tr>
								<tr>
									<td>
										<fmt:message key="income.irrf" />
									</td>
									<td class="right">
										${actionBean.income.irrf.baseSalary}
									</td>
									<td class="right">
										${actionBean.income.irrf.baseAliquot}
									</td>
									<td class="right">
										${actionBean.income.irrf.value}
									</td>
								</tr>
								<tr style="background-color: #ccc;">
									<td>
										<fmt:message key="income.netIncome" />
									</td>
									<td colspan="3" class="right">
										${actionBean.income.netIncome}
									</td>
								</tr>
								<tr>
									<td>
										<fmt:message key="graph" />
									</td>
									<td colspan="3" class="center">
										<img src="${actionBean.chart}" />
									</td>
								</tr>
								<tr>
									<td>
										<fmt:message key="income.fgts" />
									</td>
									<td class="right">
										${actionBean.income.fgts.baseSalary}
									</td>
									<td class="right">
										${actionBean.income.fgts.baseAliquot}
									</td>
									<td class="right">
										${actionBean.income.fgts.value}
									</td>
								</tr>
							</tbody>
						</table>
					</div>		
				</c:if>
			</fieldset>
		</stripes:form>
		<div id="footer"></div>
	</div>
	<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.3/jquery.min.js"></script>
	<script type="text/javascript" src="/lib/priceFormat.js"></script>
	<script type="text/javascript" src="/js/jquery.spinner.js"></script>
	<script type="text/javascript">
		$(document).ready(function(){
			$('#grossIncome').priceFormat({
			    prefix: 'R$ ',
			    centsSeparator: ',',
			    thousandsSeparator: '.'
			}).focus();
			$('#dependents').spinner({
				up: '#up',
				down: '#down',
				min: 0
			});
		});
	 </script>
	 <script type="text/javascript">
		var gaJsHost = (("https:" == document.location.protocol) ? "https://ssl." : "http://www.");
		document.write(unescape("%3Cscript src='" + gaJsHost + "google-analytics.com/ga.js' type='text/javascript'%3E%3C/script%3E"));
	</script>
	<script type="text/javascript">
		try { 
			var pageTracker = _gat._getTracker("UA-2058283-3");
			pageTracker._trackPageview();
		} catch(err) {} 
</script>
</body>
</html>