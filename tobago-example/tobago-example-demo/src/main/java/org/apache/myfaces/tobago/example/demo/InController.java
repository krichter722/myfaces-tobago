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

package org.apache.myfaces.tobago.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.RequestScoped;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Named;

@RequestScoped
@Named
public class InController {

  private static final Logger LOG = LoggerFactory.getLogger(InController.class);

  private String changeValue;

  public void update(AjaxBehaviorEvent event) {
    LOG.info("AjaxBehaviorEvent called. Current value: '{}'", changeValue);
  }

  public String getChangeValue() {
    return changeValue;
  }

  public void setChangeValue(String changeValue) {
    this.changeValue = changeValue;
  }
}
