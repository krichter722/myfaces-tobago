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

import javax.el.ELException;
import javax.el.MethodExpression;
import javax.faces.component.StateHolder;
import javax.faces.context.FacesContext;
import javax.faces.el.EvaluationException;
import javax.faces.el.MethodBinding;
import javax.faces.el.MethodNotFoundException;

/**
 * @deprecated since 2.0.0
 */
@Deprecated
public class MethodExpressionToMethodBinding extends MethodBinding implements StateHolder {

  private MethodExpression methodExpression;

  private boolean isTransient = false;

  public MethodExpressionToMethodBinding() {
    methodExpression = null;
  }

  /**
   * Creates a new instance of MethodExpressionToMethodBinding
   */
  public MethodExpressionToMethodBinding(final MethodExpression methodExpression) {
    this.methodExpression = methodExpression;
  }

  @Override
  public String getExpressionString() {
    return methodExpression.getExpressionString();
  }

  @Override
  public Class getType(final FacesContext facesContext)
      throws MethodNotFoundException {

    try {
      return methodExpression.getMethodInfo(facesContext.getELContext()).getReturnType();
    } catch (final javax.el.MethodNotFoundException e) {
      throw new javax.faces.el.MethodNotFoundException(e);
    } catch (final ELException e) {
      throw new EvaluationException(e);
    }
  }

  @Override
  public Object invoke(final FacesContext facesContext, final Object[] params)
      throws EvaluationException {

    try {
      return methodExpression.invoke(facesContext.getELContext(), params);
    } catch (final javax.el.MethodNotFoundException e) {
      throw new javax.faces.el.MethodNotFoundException(e);
    } catch (final ELException e) {
      throw new EvaluationException(e);
    }
  }


  @Override
  public void restoreState(final FacesContext context, final Object state) {
    if (state != null) {
      methodExpression = (MethodExpression) state;
    }
  }

  @Override
  public Object saveState(final FacesContext context) {
    if (!isTransient) {
      return methodExpression;
    }
    return null;
  }

  @Override
  public void setTransient(final boolean newTransientValue) {
    isTransient = newTransientValue;
  }

  @Override
  public boolean isTransient() {
    return isTransient;
  }

}
