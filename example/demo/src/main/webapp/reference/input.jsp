<%--
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
--%>
<%@ taglib uri="http://myfaces.apache.org/tobago/component" prefix="tc" %>
<%@ taglib uri="http://myfaces.apache.org/tobago/extension" prefix="tx" %>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@ taglib tagdir="/WEB-INF/tags/layout" prefix="layout" %>

<layout:overview>
  <jsp:body>
    <tc:box label="Input">
      <f:facet name="layout">
        <tc:gridLayout rows="fixed;fixed;fixed;fixed;fixed;fixed;fixed;2*;3*;2*;2*;2*;2*;2*"/>
      </f:facet>

      <tc:in value="Some Text without Label"/>

      <%-- code-sniplet-start id="in" --%>
      <tx:in label="Input" value="Some Text"/>
      <%-- code-sniplet-end id="in" --%>

      <tx:in label="Read Only" readonly="true" value="Some Text"/>

      <tx:in label="Disabled" disabled="true" value="Some Text"/>

      <tc:panel>
        <f:facet name="layout">
          <tc:gridLayout columns="100px;*"/>
        </f:facet>
        <tc:label value="Short Label"/>
        <tc:in value="Some Text"/>
      </tc:panel>

      <tx:in label="Input (focus)" focus="true" value="Some Text"/>

      <tx:in label="Password" password="true" value="Asterics"/>

      <tc:textarea value="Some text without label, some text without label, some text without label, some text without label, some text without label, some text without label, some text without label, some text without label, some text without label, some text without label, ..."/>

      <%-- code-sniplet-start id="textarea" --%>
      <tx:textarea label="Text Area"
                   value="Some text, some text, some text, some text, some text, some text, some text, some text, some text, some text, ..."/>
      <%-- code-sniplet-end id="textarea" --%>

      <tx:textarea label="Read Only"
                   readonly="true"
                   value="Some text, some text, some text, some text, some text, some text, some text, some text, some text, some text, ..."/>

      <tx:textarea label="Disabled"
                   disabled="true"
                   value="Some text, some text, some text, some text, some text, some text, some text, some text, some text, some text, ..."/>

      <tx:textarea label="Area (focus)" focus="true"
                   value="Some Text"/>

      <tc:panel>
        <tc:out value="An input field is "/>
        <tc:in value="here" inline="true"/>
        <tc:out value=" inside of a floating text."/>
      </tc:panel>

    </tc:box>
  </jsp:body>
</layout:overview>