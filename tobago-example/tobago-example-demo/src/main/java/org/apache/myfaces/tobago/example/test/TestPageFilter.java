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

package org.apache.myfaces.tobago.example.test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TestPageFilter {

  public static final List<String> ALLOWED = Arrays.asList(
      ".*\\/",
      ".*\\.xhtml",
      ".*\\.html"
  );

  public static final Set<String> HIDDEN = new HashSet<>(Arrays.asList(
      "/META-INF/.*",
      "/WEB-INF/.*",
      "/script/.*",
      "/style/.*",
      "/org/.*",
      "/src/.*",
      ".*/\\.svn/.*",
      ".*\\.selenium\\.html",
      "/javax\\.faces/.*",
      "/oam\\.custom\\..*",
      "/org\\.apache\\.myfaces/.*",
      "/org\\.apache\\.myfaces\\.custom/.*"
  ));

  /**
   * Internal pages and pages that are impossible to run.
   */
  public static final Set<String> DISABLED = new HashSet<>(Arrays.asList(
      ".*-fragment\\.xhtml", // intern

      "/index.html", // intern
      "/navigation.*", // intern

      "/404.*", // meta test
      "/500.*", // meta test

      "/meta-test/meta-1.*", // meta test
      "/meta-test/meta-3.*\\.xhtml", // meta test
      "/meta-test/meta-4.*", // meta test
      "/meta-test/meta-5.*", // meta test

      "/test/button/plain.html", // intern
      "/test/button/plain_de.html" // intern
  ));

  /**
   * Switched off temporary.
   */
  private static final Set<String> TODO = new HashSet<>(Arrays.asList(
      "/test/gridLayout/scrolling-2-levels.*", // todo: ?
      "/test/gridLayout/scrolling-tab.*", // todo: measurement problem with firefox?
      "/test/treeListbox/*", // todo
      "/test/label/label-tx.xhtml" // todo see TOBAGO-993
  ));

  public static boolean isValid(final String name) {

    // 1st all has to start with a '/' slash

    if (!name.startsWith("/")) {
      return false;
    }

    // 2nd the positive check

    boolean matches = false;
    for (final String allowed : ALLOWED) {
      if (name.matches(allowed)) {
        matches = true;
      }
    }
    if (!matches) {
      return false;
    }

    // 3rd the negative check

    for (final String hidden : HIDDEN) {
      if (name.matches(hidden)) {
        return false;
      }
    }

    return true;
  }

  public static boolean isDisabled(final String name) {
    for (final String disabled : DISABLED) {
      if (name.matches(disabled)) {
        return true;
      }
    }

    return false;
  }

  public static boolean isTodo(final String name) {
    for (final String todo : TODO) {
      if (name.matches(todo)) {
        return true;
      }
    }

    return false;
  }

}
