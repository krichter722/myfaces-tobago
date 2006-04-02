package org.apache.myfaces.tobago.taglib.component;

/*
 * Copyright 2002-2005 The Apache Software Foundation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import org.apache.myfaces.tobago.apt.annotation.Tag;
import org.apache.myfaces.tobago.apt.annotation.TagAttribute;
import org.apache.myfaces.tobago.apt.annotation.UIComponentTag;
import org.apache.myfaces.tobago.apt.annotation.UIComponentTagAttribute;
import org.apache.myfaces.tobago.taglib.decl.HasBinding;
import org.apache.myfaces.tobago.taglib.decl.HasDimension;
import org.apache.myfaces.tobago.taglib.decl.HasId;
import org.apache.myfaces.tobago.taglib.decl.HasLabel;
import org.apache.myfaces.tobago.taglib.decl.HasState;

/*
 * Created by IntelliJ IDEA.
 * User: bommel
 * Date: 30.03.2006
 * Time: 21:57:22
 * To change this template use File | Settings | File Templates.
 */
/**
 * TODO description of page tag
 */
@Tag(name = "page")
@UIComponentTag(
    uiComponent = "org.apache.myfaces.tobago.component.UIPage",
    rendererType = "Page")

public interface PageTagDeclaration extends TobagoBodyTagDeclaration, HasLabel, HasId, HasDimension, HasBinding,
    HasState {
  /**
   * values for doctype :
   * 'strict'   : HTML 4.01 Strict DTD
   * 'loose'    : HTML 4.01 Transitional DTD
   * 'frameset' : HTML 4.01 Frameset DTD
   * all other values are ignored and no DOCTYPE is set.
   * default value is 'loose'
   *
   * @param doctype
   */
  @TagAttribute
  @UIComponentTagAttribute(defaultValue = "loose")
  void setDoctype(String doctype);

  /**
   * Contains the id of the component witch should have the focus after
   * loading the page.
   * Set to emtpy string for disabling setting of focus.
   * Default (null) enables the "auto focus" feature.
   *
   * @param focusId
   */
  @TagAttribute
  @UIComponentTagAttribute()
  void setFocusId(String focusId);
}
