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

package org.apache.myfaces.tobago.component;

import org.apache.myfaces.tobago.internal.util.StringUtils;
import org.apache.myfaces.tobago.util.EnumUnitTest;
import org.junit.Test;

public class RendererTypesUnitTest extends EnumUnitTest {

  @Test
  public void testNames() throws IllegalAccessException, NoSuchFieldException {

    testNames(RendererTypes.class);
  }

  @Override
  protected String getEnumRegexp() {
    return "[A-Z][a-zA-Z]*";
  }

  @Override
  protected String constantCaseToEnum(String constant) {
    return StringUtils.constantToUpperCamelCase(constant);
  }
}
