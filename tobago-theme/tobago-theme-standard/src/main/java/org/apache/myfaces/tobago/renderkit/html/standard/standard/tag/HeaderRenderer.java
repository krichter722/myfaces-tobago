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

package org.apache.myfaces.tobago.renderkit.html.standard.standard.tag;

import org.apache.myfaces.tobago.component.UIHeader;
import org.apache.myfaces.tobago.renderkit.RendererBase;
import org.apache.myfaces.tobago.renderkit.html.BootstrapClass;
import org.apache.myfaces.tobago.renderkit.html.HtmlElements;
import org.apache.myfaces.tobago.renderkit.html.util.HtmlRendererUtils;
import org.apache.myfaces.tobago.webapp.TobagoResponseWriter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import java.io.IOException;

public class HeaderRenderer extends RendererBase {

  @Override
  public void encodeBegin(FacesContext facesContext, UIComponent component) throws IOException {
    final TobagoResponseWriter writer = HtmlRendererUtils.getTobagoResponseWriter(facesContext);
    final UIHeader header = (UIHeader) component;
    writer.startElement(HtmlElements.HEADER, component);
    writer.writeIdAttribute(component.getClientId(facesContext));
    // TBD: BootstrapClass.NAVBAR_DEFAULT ?
    if (header.isFixed()) {
      writer.writeClassAttribute(BootstrapClass.NAVBAR, BootstrapClass.NAVBAR_INVERSE, BootstrapClass.NAVBAR_FIXED_TOP);
    } else {
      writer.writeClassAttribute(BootstrapClass.NAVBAR, BootstrapClass.NAVBAR_INVERSE);
    }
// TBD: should NAVBAR class be in the NavRenderer?

/*
    if (header.isFixed()) {
      writer.writeClassAttribute(BootstrapClass.NAVBAR_FIXED_TOP);
    }
*/
  }

  @Override
  public void encodeEnd(FacesContext facesContext, UIComponent component) throws IOException {
    final TobagoResponseWriter writer = HtmlRendererUtils.getTobagoResponseWriter(facesContext);
    writer.endElement(HtmlElements.HEADER);
  }
}