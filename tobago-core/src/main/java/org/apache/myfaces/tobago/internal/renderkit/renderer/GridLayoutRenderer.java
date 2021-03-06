/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.apache.myfaces.tobago.internal.renderkit.renderer;

import org.apache.myfaces.tobago.component.RendererTypes;
import org.apache.myfaces.tobago.component.UIStyle;
import org.apache.myfaces.tobago.internal.component.AbstractUIGridLayout;
import org.apache.myfaces.tobago.layout.LayoutTokens;
import org.apache.myfaces.tobago.renderkit.RendererBase;
import org.apache.myfaces.tobago.renderkit.css.TobagoClass;
import org.apache.myfaces.tobago.renderkit.html.HtmlAttributes;
import org.apache.myfaces.tobago.renderkit.html.HtmlElements;
import org.apache.myfaces.tobago.renderkit.html.HtmlRoleValues;
import org.apache.myfaces.tobago.webapp.TobagoResponseWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import java.io.IOException;

public class GridLayoutRenderer extends RendererBase {

  private static final Logger LOG = LoggerFactory.getLogger(GridLayoutRenderer.class);

  @Override
  public void encodeBegin(final FacesContext facesContext, final UIComponent component) throws IOException {
    final AbstractUIGridLayout gridLayout = (AbstractUIGridLayout) component;
    final TobagoResponseWriter writer = getResponseWriter(facesContext);

    writer.startElement(HtmlElements.DIV);
    writer.writeAttribute(HtmlAttributes.ROLE, HtmlRoleValues.PRESENTATION.toString(), false);
    writer.writeIdAttribute(gridLayout.getClientId(facesContext));
    writer.writeClassAttribute(TobagoClass.GRID_LAYOUT);

    final LayoutTokens columns = LayoutTokens.parse(gridLayout.getColumns());
    final LayoutTokens rows = LayoutTokens.parse(gridLayout.getRows());

    final UIStyle style = (UIStyle) facesContext.getApplication().createComponent(
        facesContext, UIStyle.COMPONENT_TYPE, RendererTypes.Style.name());
    style.setTransient(true);

    style.setGridTemplateColumns(columns.encodeToGrid());
    style.setGridTemplateRows(rows.encodeToGrid());
    gridLayout.getChildren().add(style);
  }

  @Override
  public void encodeEnd(final FacesContext facesContext, final UIComponent component) throws IOException {
    final TobagoResponseWriter writer = getResponseWriter(facesContext);

    writer.endElement(HtmlElements.DIV);
  }
}
