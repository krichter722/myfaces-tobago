package org.apache.myfaces.tobago.example.test;

/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import org.junit.Assert;
import org.junit.Test;

public class FilterUnitTest {

  @Test
  public void testIsValid() {
    Assert.assertFalse(Filter.isValid("test.html"));
    Assert.assertTrue(Filter.isValid("/test.html"));

    Assert.assertFalse(Filter.isValid("/WEB-INF"));
    Assert.assertFalse(Filter.isValid("/WEB-INF/"));
    Assert.assertFalse(Filter.isValid("/WEB-INF/web.xml"));
    Assert.assertFalse(Filter.isValid("/WEB-INF/test.xhtml"));

    Assert.assertFalse(Filter.isValid("/NETA-INF"));
    Assert.assertFalse(Filter.isValid("/META-INF/"));

    Assert.assertFalse(Filter.isValid("/navi.xhtml"));
    Assert.assertTrue(Filter.isValid("/navi-2.xhtml"));
  }
}
