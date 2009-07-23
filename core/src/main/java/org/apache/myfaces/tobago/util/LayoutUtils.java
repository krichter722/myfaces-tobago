package org.apache.myfaces.tobago.util;

/*
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
 */

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.myfaces.tobago.component.Attributes;
import org.apache.myfaces.tobago.component.Facets;
import org.apache.myfaces.tobago.component.Form;

import javax.faces.component.UIComponent;
import javax.faces.component.UINamingContainer;
import java.util.List;
import java.util.StringTokenizer;
import java.util.regex.Pattern;

public final class LayoutUtils {

  private static final Log LOG = LogFactory.getLog(LayoutUtils.class);

  private static final Pattern TOKEN_PATTERN
      = Pattern.compile("^(\\d*px|\\d*\\*|\\d*%|fixed)$");

  private LayoutUtils() {
    // to prevent instantiation
  }

  public static int getLabelWidth(UIComponent component) {
    if (component != null) {
      UIComponent label = component.getFacet(Facets.LABEL);
      if (label != null) {
        String labelWidth = (String) label.getAttributes().get(Attributes.WIDTH);
        if (labelWidth != null) {
          try {
            return Integer.parseInt(labelWidth.replaceAll("\\D", ""));
          } catch (NumberFormatException e) {
            LOG.warn("Can't parse label width, using default value", e);
          }
        }
      }
    }
    return 0;
  }

  public static List<UIComponent> addChildren(List<UIComponent> children, UIComponent panel) {
    for (Object o : panel.getChildren()) {
      UIComponent child = (UIComponent) o;
      if (isTransparentForLayout(child)) {
        addChildren(children, child);
      } else {
        children.add(child);
      }
    }
    return children;
  }

  public static boolean isTransparentForLayout(UIComponent component) {

//    SubViewTag's component is UINamingContainer with 'null' rendererType
//    is transparent for layouting

    if (component instanceof UINamingContainer
        && component.getRendererType() == null) {
      return true;
    }
    // TODO find a better way
    if ("facelets".equals(component.getFamily())) {
      return !"com.sun.facelets.tag.UIDebug".equals(component.getClass().getName());
    }
    /* TODO disable layouting of facelet stuff
    if (component.getClass().getPackage().getName().equals("com.sun.facelets.compiler")) {
      return true;
    } */
//  also Forms are transparent for layouting

    return component instanceof Form;
  }

  public static boolean checkTokens(String columns) {
    StringTokenizer st = new StringTokenizer(columns, ";");
    while (st.hasMoreTokens()) {
      String token = st.nextToken();
      if (!TOKEN_PATTERN.matcher(token).matches()) {
        return false;
      }
    }
    return true;
  }
}

