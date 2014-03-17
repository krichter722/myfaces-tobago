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

package org.apache.myfaces.tobago.taglib.component;

import org.apache.myfaces.tobago.apt.annotation.Tag;
import org.apache.myfaces.tobago.apt.annotation.UIComponentTag;
import org.apache.myfaces.tobago.apt.annotation.UIComponentTagAttribute;
import org.apache.myfaces.tobago.taglib.decl.HasConverter;
import org.apache.myfaces.tobago.taglib.decl.HasIdBindingAndRendered;
import org.apache.myfaces.tobago.taglib.decl.HasValidator;
import org.apache.myfaces.tobago.taglib.decl.HasValue;
import org.apache.myfaces.tobago.taglib.decl.IsDisabled;
import org.apache.myfaces.tobago.taglib.decl.IsReadonly;

/*
 * $Id$
 */
/**
 * Renders a 'hidden' input element.
 */
@Tag(name = "hidden")
@UIComponentTag(
    uiComponent = "org.apache.myfaces.tobago.component.UIHiddenInput",
    rendererType = "Hidden")
public interface HiddenTagDeclaration extends BeanTagDeclaration, HasIdBindingAndRendered, HasValue, HasConverter,
    HasValidator, IsReadonly, IsDisabled {

  /**
   * @deprecated
   */
  @Deprecated
  @UIComponentTagAttribute(type = "java.lang.Boolean", defaultValue = "true")
  void setInline(String inline);

}