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

package org.apache.myfaces.tobago.internal.component;

import org.apache.myfaces.tobago.component.Attributes;
import org.apache.myfaces.tobago.component.RendererTypes;
import org.apache.myfaces.tobago.component.UIGridLayout;
import org.apache.myfaces.tobago.component.UIPanel;
import org.apache.myfaces.tobago.internal.config.AbstractTobagoTestBase;
import org.apache.myfaces.tobago.util.ComponentUtils;
import org.junit.Assert;
import org.junit.Test;

import javax.faces.component.UIComponent;
import java.util.Arrays;

public class AbstractUIGridLayoutUnitTest extends AbstractTobagoTestBase {

  private static final UIComponent X = AbstractUIGridLayout.SPAN;
  private static final UIComponent N = null;

  @Test
  public void test1() {
    final UIGridLayout grid = new UIGridLayout();

    final UIComponent a = createComponent("a");
    final UIComponent b = createComponent("b");
    final UIComponent c = createComponent("c");
    c.getAttributes().put(Attributes.columnSpan.getName(), 2);
    final UIComponent d = createComponent("d");
    final UIComponent e = createComponent("e");
    e.getAttributes().put(Attributes.gridColumn.getName(), 1);
    e.getAttributes().put(Attributes.gridRow.getName(), 3);

    final UIComponent[][] cells = grid.layout(2, 3, Arrays.asList(a, b, c, d, e));

    Assert.assertEquals("┏━┳━┓\n"
        + "┃a┃b┃\n"
        + "┣━╋━┫\n"
        + "┃c┃█┃\n"
        + "┣━╋━┫\n"
        + "┃e┃d┃\n"
        + "┗━┻━┛\n", toString(cells));

    Assert.assertEquals(a, cells[0][0]);
    Assert.assertEquals(b, cells[0][1]);
    Assert.assertEquals(c, cells[1][0]);
    Assert.assertEquals(X, cells[1][1]);
    Assert.assertEquals(e, cells[2][0]);
    Assert.assertEquals(d, cells[2][1]);

  }

  @Test
  public void test2() {
    final UIGridLayout grid = new UIGridLayout();

    final UIComponent a = createComponent("a");
    final UIComponent b = createComponent("b");
    final UIComponent c = createComponent("c");
    c.getAttributes().put(Attributes.rowSpan.getName(), 2);
    final UIComponent d = createComponent("d");
    final UIComponent e = createComponent("e");
    e.getAttributes().put(Attributes.gridColumn.getName(), 1);
    e.getAttributes().put(Attributes.gridRow.getName(), 1);

    final UIComponent[][] cells = grid.layout(2, 3, Arrays.asList(a, b, c, d, e));

    Assert.assertEquals("┏━┳━┓\n"
        + "┃e┃a┃\n"
        + "┣━╋━┫\n"
        + "┃b┃c┃\n"
        + "┣━╋━┫\n"
        + "┃d┃█┃\n"
        + "┗━┻━┛\n", toString(cells));

    Assert.assertEquals(e, cells[0][0]);
    Assert.assertEquals(a, cells[0][1]);
    Assert.assertEquals(b, cells[1][0]);
    Assert.assertEquals(c, cells[1][1]);
    Assert.assertEquals(d, cells[2][0]);
    Assert.assertEquals(X, cells[2][1]);
  }

  @Test
  public void test3() {
    final UIGridLayout grid = new UIGridLayout();

    final UIComponent a = createComponent("a");
    a.getAttributes().put(Attributes.rowSpan.getName(), 7);
    final UIComponent b = createComponent("b");
    final UIComponent c = createComponent("c");
    final UIComponent d = createComponent("d");
    final UIComponent e = createComponent("e");

    final UIComponent[][] cells = grid.layout(2, 3, Arrays.asList(a, b, c, d, e));

    Assert.assertEquals("┏━┳━┓\n"
        + "┃a┃b┃\n"
        + "┣━╋━┫\n"
        + "┃█┃c┃\n"
        + "┣━╋━┫\n"
        + "┃█┃d┃\n"
        + "┣━╋━┫\n"
        + "┃█┃e┃\n"
        + "┣━╋━┫\n"
        + "┃█┃◌┃\n"
        + "┣━╋━┫\n"
        + "┃█┃◌┃\n"
        + "┣━╋━┫\n"
        + "┃█┃◌┃\n"
        + "┣━╋━┫\n"
        + "┃◌┃◌┃\n"
        + "┣━╋━┫\n"
        + "┃◌┃◌┃\n"
        + "┗━┻━┛\n", toString(cells));

    Assert.assertEquals(a, cells[0][0]);
    Assert.assertEquals(b, cells[0][1]);
    Assert.assertEquals(X, cells[1][0]);
    Assert.assertEquals(c, cells[1][1]);
    Assert.assertEquals(X, cells[2][0]);
    Assert.assertEquals(d, cells[2][1]);
    Assert.assertEquals(X, cells[3][0]);
    Assert.assertEquals(e, cells[3][1]);
    Assert.assertEquals(X, cells[4][0]);
    Assert.assertEquals(N, cells[4][1]);
    Assert.assertEquals(X, cells[5][0]);
    Assert.assertEquals(N, cells[5][1]);
    Assert.assertEquals(X, cells[6][0]);
    Assert.assertEquals(N, cells[6][1]);
    Assert.assertEquals(N, cells[7][0]);
    Assert.assertEquals(N, cells[7][1]);
    Assert.assertEquals(N, cells[8][0]);
    Assert.assertEquals(N, cells[8][1]);
  }

  @Test
  public void test4() {
    final UIGridLayout grid = new UIGridLayout();

    final UIComponent a = createComponent("a");
    final UIComponent b = createComponent("b");
    b.getAttributes().put(Attributes.columnSpan.getName(), 2);

    final UIComponent[][] cells = grid.layout(2, 1, Arrays.asList(a, b));

    Assert.assertEquals("┏━┳━┓\n"
        + "┃a┃b┃\n"
        + "┗━┻━┛\n", toString(cells));

    Assert.assertEquals(a, cells[0][0]);
    Assert.assertEquals(b, cells[0][1]);

    // remark: columnSpan = 2 not valid, but should only log a warning.
  }

  @Test
  public void test5() {
    final UIGridLayout grid = new UIGridLayout();

    final UIComponent a = createComponent("a");
    final UIComponent b = createComponent("b");

    final UIComponent c = createComponent("c");
    final UIComponent d = createComponent("d");

    final UIComponent e = createComponent("e");
    e.getAttributes().put(Attributes.columnSpan.getName(), 2);

    final UIComponent f = createComponent("f");
    final UIComponent g = createComponent("g");

    final UIComponent h = createComponent("h");
    final UIComponent i = createComponent("i");

    final UIComponent j = createComponent("j");
    j.getAttributes().put(Attributes.columnSpan.getName(), 2);

    final UIComponent[][] cells = grid.layout(2, 5, Arrays.asList(a, b, c, d, e, f, g, h, i, j));

    Assert.assertEquals("┏━┳━┓\n"
        + "┃a┃b┃\n"
        + "┣━╋━┫\n"
        + "┃c┃d┃\n"
        + "┣━╋━┫\n"
        + "┃e┃█┃\n"
        + "┣━╋━┫\n"
        + "┃f┃g┃\n"
        + "┣━╋━┫\n"
        + "┃h┃i┃\n"
        + "┣━╋━┫\n"
        + "┃j┃█┃\n"
        + "┣━╋━┫\n"
        + "┃◌┃◌┃\n"
        + "┣━╋━┫\n"
        + "┃◌┃◌┃\n"
        + "┣━╋━┫\n"
        + "┃◌┃◌┃\n"
        + "┣━╋━┫\n"
        + "┃◌┃◌┃\n"
        + "┗━┻━┛\n", toString(cells));
  }

  @Test
  public void testExpand() {
    final UIGridLayout grid = new UIGridLayout();

    final UIComponent a = createComponent("a");
    final UIComponent b = createComponent("b");

    final UIComponent[][] array = new UIComponent[3][5];
    array[0][0] = a;
    array[2][4] = b;

    Assert.assertEquals(a, array[0][0]);
    Assert.assertEquals(b, array[2][4]);
    Assert.assertEquals(3, array.length);
    Assert.assertEquals(5, array[0].length);

    final UIComponent[][] expand = grid.expand(array, 7, 3);

    Assert.assertEquals(array[0][0], expand[0][0]);
    Assert.assertEquals(array[2][4], expand[2][4]);
    Assert.assertEquals(9, expand.length);
    Assert.assertEquals(5, expand[0].length);

    Assert.assertEquals(null, expand[1][1]);
    Assert.assertEquals(null, expand[6][1]);
  }

  private UIComponent createComponent(String id) {
    return ComponentUtils.createComponent(facesContext, UIPanel.COMPONENT_TYPE, RendererTypes.Panel, id);
  }

  private static String toString(final UIComponent[][] cells) {

    final StringBuilder builder = new StringBuilder();

    // top of grid
    for (int i = 0; i < cells[0].length; i++) {
      if (i == 0) {
        builder.append("┏");
      } else {
        builder.append("┳");
      }
      builder.append("━");
    }
    builder.append("┓");
    builder.append("\n");

    for (int j = 0; j < cells.length; j++) {

      // between the cells
      if (j != 0) {
        for (int i = 0; i < cells[0].length; i++) {
          if (i == 0) {
            builder.append("┣");
          } else {
            builder.append("╋");
          }
          builder.append("━");
        }
        builder.append("┫");
        builder.append("\n");
      }

      // cell
      for (int i = 0; i < cells[0].length; i++) {
        builder.append("┃");
        if (cells[j][i] == X) {
          builder.append("█");
        } else if (cells[j][i] != null) {
          builder.append(cells[j][i].getClientId());
        } else {
          builder.append("◌");
        }
      }
      builder.append("┃");
      builder.append("\n");
    }

    //last bottom
    for (int i = 0; i < cells[0].length; i++) {
      if (i == 0) {
        builder.append("┗");
      } else {
        builder.append("┻");
      }
      builder.append("━");
    }
    builder.append("┛");
    builder.append("\n");

    return builder.toString();
  }

}
