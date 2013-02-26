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

package org.apache.myfaces.tobago.event;

import org.apache.myfaces.tobago.internal.util.FindComponentUtils;

import javax.faces.component.StateHolder;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import java.util.Collection;

public class ResetInputActionListener extends AbstractResetInputActionListener implements StateHolder {

  private String [] clientIds;

  public ResetInputActionListener() {
    clientIds = new String[0];
  }

  public ResetInputActionListener(String[] clientIds) {
    this.clientIds = clientIds;
  }

  public ResetInputActionListener(Collection<String> clientIds) {
     this.clientIds = clientIds.toArray(new String[clientIds.size()]);
  }

  public void processAction(ActionEvent event) {
    for (String clientId : clientIds) {
      UIComponent component = FindComponentUtils.findComponent(event.getComponent(), clientId);
      if (component != null) {
        resetChildren(component);
      }
    }
  }

  public boolean isTransient() {
    return false;
  }

  public void setTransient(boolean newTransientValue) {
    // ignore
  }

  public void restoreState(FacesContext context, Object state) {
    Object[] values = (Object[]) state;
    clientIds = (String[]) values[0];
  }

  public Object saveState(FacesContext context) {
    Object[] values = new Object[1];
    values[0] = clientIds;
    return values;
  }
}
