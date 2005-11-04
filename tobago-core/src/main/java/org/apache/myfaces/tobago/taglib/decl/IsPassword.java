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
package org.apache.myfaces.tobago.taglib.decl;

import org.apache.myfaces.tobago.apt.annotation.TagAttribute;
import org.apache.myfaces.tobago.apt.annotation.UIComponentTagAttribute;

/**
 * Created: Okt 9, 2005
 * User: lofwyr
 * $Id: IsRequired.java 1343 2005-08-12 13:51:16Z lofwyr $
 */
public interface IsPassword {

  /**
   * Flag indicating whether or not this component should be rendered as
   * password field , so you will not see the typed charakters.
   */
  @TagAttribute
  @UIComponentTagAttribute(type="java.lang.Boolean", defaultValue = "false")
  void setPassword(String password);
}
