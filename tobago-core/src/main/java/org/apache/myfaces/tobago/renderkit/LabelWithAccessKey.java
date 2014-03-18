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

import org.apache.myfaces.tobago.component.Attributes;
import org.apache.myfaces.tobago.component.RendererTypes;
import org.apache.myfaces.tobago.internal.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.faces.component.UIComponent;
import java.util.Locale;

public final class LabelWithAccessKey {

  private static final Logger LOG = LoggerFactory.getLogger(LabelWithAccessKey.class);

  private String text;
  private Character accessKey;
  private int pos = -1;
  public static final char INDICATOR = '_';
  public static final String ESCAPED_INDICATOR = "__";

  public LabelWithAccessKey(final String label) {
    text = label;
    setup(text);
  }

  public LabelWithAccessKey(final UIComponent component) {
    final Object value;
    if (RendererTypes.LABEL.equals(component.getRendererType())) {
      value = component.getAttributes().get(Attributes.VALUE);
    } else {
      value = component.getAttributes().get(Attributes.LABEL);
    }
    text = (value == null) ? null : String.valueOf(value);
    setup(text);
  }

  private void findIndicator(final String label, int index, int escapedIndicatorCount) {
    index = label.indexOf(INDICATOR, index);
    if (index == -1) {
      text = label;
    } else if (index == label.length() - 1) {
      LOG.warn(INDICATOR + " in label is last char, this is not allowed"
          + "label='" + label + "'.");
      text = label.substring(0, label.length() - 1);
      pos = -1;
    } else if (label.charAt(index + 1) == INDICATOR) {
      escapedIndicatorCount++;
      findIndicator(label, index + 2, escapedIndicatorCount);
    } else {
      text = label.substring(0, index)
          + label.substring(index + 1);
      setAccessKey(text.charAt(index));
      pos = index - escapedIndicatorCount;
    }
  }

  public void setup(final String label) {
    if (label != null) {
      findIndicator(label, 0, 0);
      text = StringUtils.replace(text, ESCAPED_INDICATOR, String.valueOf(INDICATOR));
    } else {
      if (accessKey != null && text != null) {
        pos = text.toLowerCase(Locale.ENGLISH).indexOf(
            Character.toLowerCase(accessKey.charValue()));
      }
    }
  }

  public void reset() {
    text = null;
    accessKey = null;
    pos = -1;
  }

  public String getText() {
    return text;
  }

  public Character getAccessKey() {
    return accessKey;
  }

  public int getPos() {
    return pos;
  }

  public void setText(final String text) {
    this.text = text;
  }

  public void setAccessKey(final Character accessKey) {
    if (!isPermitted(accessKey)) {
      LOG.warn("Ignoring illegal access key: " + accessKey);
    }
    this.accessKey = accessKey;
  }

  /**
   * Ensures, that no illegal character will be write out.
   * (If this is changed from only allowing letters, the renderers may change the escaping)
   */
  private boolean isPermitted(final Character accessKey) {
    return accessKey >= 'a' && accessKey <= 'z' || accessKey >= 'A' && accessKey <= 'Z';
  }
}