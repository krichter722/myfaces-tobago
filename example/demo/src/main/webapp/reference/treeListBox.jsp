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

<%@ page import="javax.swing.tree.DefaultMutableTreeNode" %>
<%@ page import="org.apache.myfaces.tobago.model.TreeState" %>

<%
  DefaultMutableTreeNode tree;
  TreeState treeState;

  tree = new DefaultMutableTreeNode("Category");
  tree.insert(new DefaultMutableTreeNode("Sports"), 0);
  tree.insert(new DefaultMutableTreeNode("Movies"), 0);
  DefaultMutableTreeNode music = new DefaultMutableTreeNode("Music");
  tree.insert(music, 0);
  tree.insert(new DefaultMutableTreeNode("Games"), 0);
  DefaultMutableTreeNode temp = new DefaultMutableTreeNode("Science");
  temp.insert(
      new DefaultMutableTreeNode("Geography"), 0);
  temp.insert(
      new DefaultMutableTreeNode("Mathematics"), 0);
  DefaultMutableTreeNode temp2 = new DefaultMutableTreeNode("Astronomy");
  temp2.insert(new DefaultMutableTreeNode("Education"), 0);
  temp2.insert(new DefaultMutableTreeNode("Pictures"), 0);
  temp.insert(temp2, 2);
  tree.insert(temp, 2);
  treeState = new TreeState();
  treeState.addExpandState(tree);
  treeState.addExpandState(temp);
  treeState.addSelection(temp2);
  treeState.setMarker(music);
  session.setAttribute("tree", tree);
  session.setAttribute("treeState", treeState);
%>

<%@ taglib uri="http://myfaces.apache.org/tobago/component" prefix="tc" %>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@ taglib tagdir="/WEB-INF/tags/layout" prefix="layout" %>

<layout:overview>
    <jsp:body>
      <tc:box label="Tree List Box">
        <f:facet name="layout">
          <tc:gridLayout rows="300px;1*" />
        </f:facet>
        <tc:treeListbox state="#{treeState}" value="#{tree}"
            idReference="userObject"
            nameReference="userObject"/>

        <tc:cell/>

      </tc:box>

    </jsp:body>
</layout:overview>
