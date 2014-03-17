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

package org.apache.myfaces.tobago.renderkit;

import static org.apache.myfaces.tobago.TobagoConstants.ATTR_ONCHANGE;

import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;

public class HtmlUtils {

  public static final String LAYOUT_ATTRIBUTE_PREFIX = "layout.";

  public static String generateAttribute(String name, Object value) {
    String stringValue;
    if (value == null) {
      stringValue = null;
    } else if (value instanceof String) {
      stringValue = (String) value;
    } else {
      stringValue = value.toString();
    }
    return (stringValue != null && stringValue.length() > 0)
        ? name + "=\"" + value + "\""
        : "";
  }

  public static String appendAttribute(UIComponent component, String name,
      String appendValue) {
    Object attribute = component.getAttributes().get(name);
    return attribute != null
        ? attribute.toString() + " " + appendValue : appendValue;
  }

  public static String generateOnchange(UIInput component,
      FacesContext facesContext) {

    StringBuilder buffer = new StringBuilder();
    /*Validator[] validators = component.getValidators();
    for (int i = 0; i < validators.length; i++) {
      if (validators[i] instanceof LongRangeValidator) {
        String functionCall = "validateLongRange('"
            + component.getClientId(facesContext) + "')";
        if (LOG.isDebugEnabled()) {
          LOG.debug("validator functionCall: " + functionCall);
        }
        buffer.append(functionCall);
      } else {
        buffer.append("true");
      }
      if (i + 1 < validators.length) { // is not last
        buffer.append(" && ");
      }
    } */

    Object onchange = component.getAttributes().get(ATTR_ONCHANGE);
    if (onchange != null) { // append the onchange attribute
      if (buffer.length() > 0) {
        buffer.append(" && ");
      }
      buffer.append(onchange);
    }

    if (buffer.length() > 0) { // has content ?
      return buffer.toString();
    } else {
      return null;
    }
  }

}