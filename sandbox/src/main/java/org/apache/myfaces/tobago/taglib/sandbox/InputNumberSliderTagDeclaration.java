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

package org.apache.myfaces.tobago.taglib.sandbox;

import org.apache.myfaces.tobago.apt.annotation.Tag;
import org.apache.myfaces.tobago.apt.annotation.TagAttribute;
import org.apache.myfaces.tobago.apt.annotation.UIComponentTag;
import org.apache.myfaces.tobago.apt.annotation.UIComponentTagAttribute;
import org.apache.myfaces.tobago.taglib.decl.HasIdBindingAndRendered;
import org.apache.myfaces.tobago.taglib.decl.HasValue;
import org.apache.myfaces.tobago.taglib.decl.HasValueChangeListener;
import org.apache.myfaces.tobago.taglib.decl.IsDisabled;
import org.apache.myfaces.tobago.taglib.decl.IsReadonly;

@Tag(name = "numberSlider")
@UIComponentTag(rendererType = "InputNumberSlider",
    uiComponent = "org.apache.myfaces.tobago.component.UIInputNumberSlider")
public interface InputNumberSliderTagDeclaration extends
    HasIdBindingAndRendered, IsReadonly, IsDisabled,
    HasValue, HasValueChangeListener {

  /**
   * The minimum integer that can be entered and which represents the left
   * border of the slider.
   */
  @TagAttribute()
  @UIComponentTagAttribute(type = "java.lang.Integer", defaultValue = "0")
  void setMin(String min);

  /**
   * The maximum integer that can be entered and which represents the rigth
   * border of the slider.
   */
  @TagAttribute()
  @UIComponentTagAttribute(type = "java.lang.Integer", defaultValue = "100")
  void setMax(String max);
}