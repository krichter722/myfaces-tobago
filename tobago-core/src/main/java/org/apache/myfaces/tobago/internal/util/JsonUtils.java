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

package org.apache.myfaces.tobago.internal.util;

import org.apache.myfaces.tobago.component.ClientBehaviors;
import org.apache.myfaces.tobago.internal.context.DateTimeI18n;
import org.apache.myfaces.tobago.internal.renderkit.Collapse;
import org.apache.myfaces.tobago.internal.renderkit.Command;
import org.apache.myfaces.tobago.internal.renderkit.CommandMap;
import org.apache.myfaces.tobago.layout.AutoLayoutToken;
import org.apache.myfaces.tobago.layout.LayoutToken;
import org.apache.myfaces.tobago.layout.LayoutTokens;
import org.apache.myfaces.tobago.layout.MeasureLayoutToken;
import org.apache.myfaces.tobago.layout.RelativeLayoutToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class JsonUtils {

  private static final Logger LOG = LoggerFactory.getLogger(JsonUtils.class);

  private JsonUtils() {
  }

  private static void encode(final StringBuilder builder, final String name, final String[] value) {
    builder.append("\"");
    builder.append(name);
    builder.append("\":");
    encode(builder, value);
    builder.append(",");
  }

  public static void encode(StringBuilder builder, String[] value) {
    builder.append("[");
    boolean colon = false;
    for (final String item : value) {
      if (colon) {
        builder.append(",");
      }
      builder.append("\"");
      builder.append(item);
      builder.append("\"");
      colon = true;
    }
    builder.append("]");
  }

  public static void encode(StringBuilder builder, List<Integer> value) {
    builder.append("[");
    boolean colon = false;
    for (final Integer item : value) {
      if (colon) {
        builder.append(",");
      }
      builder.append(item);
      colon = true;
    }
    builder.append("]");
  }

  private static void encode(final StringBuilder builder, final String name, final Boolean value) {
    builder.append("\"");
    builder.append(name);
    builder.append("\":");
    builder.append(Boolean.toString(value));
    builder.append(",");
  }

  private static void encode(final StringBuilder builder, final String name, final Integer value) {
    builder.append("\"");
    builder.append(name);
    builder.append("\":");
    builder.append(Integer.toString(value));
    builder.append(",");
  }

  private static void encode(final StringBuilder builder, final String name, final String value) {
    builder.append("\"");
    builder.append(name);
    builder.append("\":\"");
    builder.append(value.replaceAll("\\\"", "\\\\\\\"")); // todo: optimize
    builder.append("\",");
  }

  public static String encode(final CommandMap commandMap) {

    if (commandMap == null) {
      return null;
    }

    final StringBuilder builder = new StringBuilder();
    builder.append("{");
    final int initialLength = builder.length();

    final Command click = commandMap.getClick();
    if (click != null) {
      encode(builder, ClientBehaviors.click, click);
    }

    final Map<ClientBehaviors, Command> other = commandMap.getOther();
    if (other != null) {
      for(final Map.Entry<ClientBehaviors, Command> entry : other.entrySet()) {
        encode(builder, entry.getKey(), entry.getValue());
      }
    }

    if (builder.length() - initialLength > 0) {
      assert builder.charAt(builder.length() - 1) == ',';
      builder.deleteCharAt(builder.length() - 1);
    }

    builder.append("}");
    return builder.toString();
  }

  private static void encode(final StringBuilder builder, final ClientBehaviors name, final Command command) {
    builder.append("\"");
    builder.append(name);
    builder.append("\":{");
    final int initialLength = builder.length();

    final String action = command.getAction();
    if (action != null) {
      encode(builder, "action", action);
    }
    final Boolean transition = command.getTransition();
    if (transition != null && !transition) { // true is the default, so encoding is needed.
      encode(builder, "transition", transition);
    }
    final String target = command.getTarget();
    if (target != null) {
      encode(builder, "target", target);
    }
    final String execute = command.getExecute();
    if (execute != null) {
      encode(builder, "execute", execute);
    }
    final String render = command.getRender();
    if (render != null) {
      encode(builder, "render", render);
    }
    final Collapse collapse = command.getCollapse();
    if (collapse != null) {
      encode(builder, "collapse", collapse);
    }
    final String focus = command.getFocus();
    if (focus != null) {
      encode(builder, "focus", focus);
    }
    final String confirmation = command.getConfirmation();
    if (confirmation != null) {
      encode(builder, "confirmation", confirmation);
    }
    final Integer delay  = command.getDelay();
    if (delay != null) {
      encode(builder, "delay", delay);
    }
    final Boolean omit = command.getOmit();
    if (omit != null && omit) { // false is the default, so encoding is needed.
      encode(builder, "omit", true);
    }

    if (builder.length() - initialLength > 0) {
      assert builder.charAt(builder.length() - 1) == ',';
      builder.deleteCharAt(builder.length() - 1);
    }

    builder.append("},");
  }

  private static void encode(final StringBuilder builder, final String name, final Collapse collapse) {
    builder.append("\"");
    builder.append(name);
    builder.append("\":{");
    final int initialLength = builder.length();

    final Collapse.Action action = collapse.getAction();
    if (action != null) {
      encode(builder, "transition", action.name());
    }
    final String forId = collapse.getFor();
    if (forId != null) {
      encode(builder, "forId", forId);
    }
    if (builder.length() - initialLength > 0) {
      assert builder.charAt(builder.length() - 1) == ',';
      builder.deleteCharAt(builder.length() - 1);
    }

    builder.append("},");
  }

  public static String encode(final DateTimeI18n dateTimeI18n) {
    final StringBuilder builder = new StringBuilder();
    builder.append("{");
    final int initialLength = builder.length();

    encode(builder, "monthNames", dateTimeI18n.getMonthNames());
    encode(builder, "monthNamesShort", dateTimeI18n.getMonthNamesShort());
    encode(builder, "dayNames", dateTimeI18n.getDayNames());
    encode(builder, "dayNamesShort", dateTimeI18n.getDayNamesShort());
    encode(builder, "dayNamesMin", dateTimeI18n.getDayNamesMin());
    encode(builder, "firstDay", dateTimeI18n.getFirstDay());

    if (builder.length() - initialLength > 0) {
      assert builder.charAt(builder.length() - 1) == ',';
      builder.deleteCharAt(builder.length() - 1);
    }
    builder.append("}");
    return builder.toString();
  }

  public static String encode(final String[] strings) {
    if (strings == null) {
      return null;
    }
    final StringBuilder builder = new StringBuilder();
    encode(builder, strings);
    return builder.toString();
  }

  public static String encode(final List<Integer> integers) {
    if (integers == null) {
      return null;
    }
    final StringBuilder builder = new StringBuilder();
    encode(builder, integers);
    return builder.toString();
  }

  public static void encode(final LayoutTokens bankHeads, final StringBuilder builder) {
    builder.append("[");
    for (final LayoutToken token : bankHeads.getTokens()) {
      if (token instanceof RelativeLayoutToken) {
        final int factor = ((RelativeLayoutToken) token).getFactor();
        builder.append(factor);
      } else if (token instanceof AutoLayoutToken) {
        builder.append("\"auto\"");
      } else if (token instanceof MeasureLayoutToken) {
        builder.append("{\"measure\":\"");
        builder.append(((MeasureLayoutToken) token).getMeasure());
        builder.append("\"}");
      } else {
        LOG.warn("Not supported: " + token);
      }
      builder.append(',');
    }
    if (builder.charAt(builder.length() - 1) == ',') {
      builder.deleteCharAt(builder.length() - 1);
    }
    builder.append("]");
  }

  public static List<Integer> decodeIntegerArray(final String json) {
    String string = json.trim();
    final List<Integer> result = new ArrayList<>();
    if (string.length() < 2 || string.charAt(0) != '[' || string.charAt(string.length() - 1) != ']') {
      LOG.warn("Can't parse JSON array: no surrounding square brackets []: '{}'", string);
    } else {
      string = string.substring(1, string.length() - 1);
      final StringTokenizer tokenizer = new StringTokenizer(string, ",");
      while (tokenizer.hasMoreTokens()) {
        final String token = tokenizer.nextToken().trim();
        try {
          result.add(Integer.parseInt(token));
        } catch (NumberFormatException e) {
          LOG.warn("Can't parse JSON array: not an integer token: '{}'", token);
          // ignoring so far
        }
      }
    }
    return result;
  }
}
