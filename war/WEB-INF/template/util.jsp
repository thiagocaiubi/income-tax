<%@ page contentType="text/html;charset=ISO-8859-1" language="java" isELIgnored="false" %>

<%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%-- Short hand for the context root. --%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<fmt:setBundle basename="StripesResources"/>