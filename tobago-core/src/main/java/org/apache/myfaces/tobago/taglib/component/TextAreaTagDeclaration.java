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
package org.apache.myfaces.tobago.taglib.component;

import org.apache.myfaces.tobago.apt.annotation.Tag;
import org.apache.myfaces.tobago.apt.annotation.TagAttribute;
import org.apache.myfaces.tobago.apt.annotation.UIComponentTagAttribute;
import org.apache.myfaces.tobago.taglib.decl.*;

/*
 * Created: Aug 5, 2005 5:00:41 PM
 * User: bommel
 * $Id: $
 */

/**
 * Renders a multiline text input control.
 */
@Tag(name="textarea")
public interface TextAreaTagDeclaration extends TextInputTagDeclaration, HasIdBindingAndRendered,
        HasValue, HasConverter, IsReadonly, IsDisabled, HasDimension, HasOnchangeListener,
        IsFocus, IsRequired, HasLabelAndAccessKey, HasTip {

  /**
   *  The row count for this component.
   */
  @TagAttribute
  @UIComponentTagAttribute()
  void setRows(String rows);
}
