/*
 * Copyright 2002-2005 The Apache Software Foundation.
 * 
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 * 
 *        http://www.apache.org/licenses/LICENSE-2.0
 * 
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
/*
 * Created on: 15.02.2002, 17:01:56
 * $Id$
 */
package org.apache.myfaces.tobago.taglib.component;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import static org.apache.myfaces.tobago.TobagoConstants.ATTR_PASSWORD;
import org.apache.myfaces.tobago.component.ComponentUtil;

import javax.faces.component.UIComponent;

public class InTag extends TextInputTag implements InTagDeclaration {

  private static final Log LOG = LogFactory.getLog(InTag.class);

  private String password;

  @Override
  public void release() {
    super.release();
    password = null;
  }

  @Override
  protected void setProperties(UIComponent component) {
    super.setProperties(component);

    if (label != null) {
      LOG.warn("the label attribute is deprecated in t:in, " +
          "please use tx:in instead.");
    }

    ComponentUtil.setBooleanProperty(component, ATTR_PASSWORD, password);
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}
