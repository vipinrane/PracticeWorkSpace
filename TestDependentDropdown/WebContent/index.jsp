<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<sj:head jqueryui="true" />
<div id="col3">
    <div id="col3_content" class="clearfix">
        <s:form id="formSelectReload" action="jsonsample" theme="simple"
            cssClass="yform">
            <fieldset>
                <legend>AJAX Form</legend>
                <div class="type-text">
                    <label for="language">Country: </label>
                    <s:url id="remoteurl" action="jsonsample" namespace="/"/>
                     <sj:select href="%{remoteurl}" id="country" onChangeTopics="reloadsecondlist" name="country"
                                list="countryMap" listKey="myKey" listValue="myValue" emptyOption="false"
                                headerKey="-1" headerValue="Please Select a Country"/>
                </div>
                <div class="type-text">
                    <label for="echo">State: </label>
                    <s:url id="remoteurl" action="jsonsample" namespace="/"/>
                     <sj:select href="%{remoteurl}" id="stateID" onChangeTopics="reloadThirdlist" formIds="formSelectReload"
                                reloadTopics="reloadsecondlist" name="state" 
                                list="stateMap" listKey="myKey" listValue="myValue" emptyOption="false"
                                headerKey="-1" headerValue="Please Select a State"/>
                </div>          
                <div class="type-text">
                    <label for="echo">City: </label>
                    <s:url id="remoteurl" action="jsonsample" namespace="/"/>
                     <sj:select href="%{remoteurl}" id="cityId" formIds="formSelectReload" reloadTopics="reloadThirdlist"
                                name="echo" list="cityList" emptyOption="false"
                                headerKey="-1" headerValue="Please Select a City"/>
                </div>      
            </fieldset>
        </s:form>
    </div>
</div>