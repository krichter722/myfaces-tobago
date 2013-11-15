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

import java.util.Collection;
import java.util.List;
import java.util.Set;

public class SelectManyListboxBean {

  private List<String> list;

  private Set<String> set;

  private Collection<String> collection;

  private String[] array;

  public List<String> getList() {
    return list;
  }

  public void setList(final List<String> list) {
    this.list = list;
  }

  public String[] getArray() {
    return array;
  }

  public void setArray(final String[] array) {
    this.array = array;
  }

  public Set<String> getSet() {
    return set;
  }

  public void setSet(final Set<String> set) {
    this.set = set;
  }

  public Collection<String> getCollection() {
    return collection;
  }

  public void setCollection(final Collection<String> collection) {
    this.collection = collection;
  }
}
