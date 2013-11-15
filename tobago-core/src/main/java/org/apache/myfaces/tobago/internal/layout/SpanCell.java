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

package org.apache.myfaces.tobago.internal.layout;

import org.apache.myfaces.tobago.layout.LayoutComponent;

public class SpanCell implements Cell {

  private OriginCell origin;
  private boolean horizontalFirst;
  private boolean verticalFirst;

  public SpanCell(final OriginCell origin, final boolean horizontalFirst, final boolean verticalFirst) {
    this.origin = origin;
    this.horizontalFirst = horizontalFirst;
    this.verticalFirst = verticalFirst;
  }

  public LayoutComponent getComponent() {
    return origin.getComponent();
  }

  public OriginCell getOrigin() {
    return origin;
  }

  public boolean isHorizontalFirst() {
    return horizontalFirst;
  }

  public boolean isVerticalFirst() {
    return verticalFirst;
  }

  public int getColumnSpan() {
    return origin.getColumnSpan();
  }

  public int getRowSpan() {
    return origin.getRowSpan();
  }
}
