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

import org.apache.myfaces.tobago.component.Attributes;
import org.apache.myfaces.tobago.internal.component.AbstractUICommand;
import org.apache.myfaces.tobago.renderkit.css.BootstrapClass;
import org.apache.myfaces.tobago.renderkit.css.CssItem;
import org.apache.myfaces.tobago.renderkit.css.TobagoClass;
import org.apache.myfaces.tobago.util.ComponentUtils;

import javax.faces.context.FacesContext;

public class ButtonRenderer extends CommandRendererBase {

  protected TobagoClass getRendererCssClass() {
    return TobagoClass.BUTTON;
  }

  @Override
  protected CssItem[] getCssItems(final FacesContext facesContext, final AbstractUICommand command) {
    final boolean defaultCommand = ComponentUtils.getBooleanAttribute(command, Attributes.defaultCommand);

    return new CssItem[]{
        BootstrapClass.BTN,
        defaultCommand ? BootstrapClass.BTN_PRIMARY : BootstrapClass.BTN_SECONDARY
    };
  }
}
