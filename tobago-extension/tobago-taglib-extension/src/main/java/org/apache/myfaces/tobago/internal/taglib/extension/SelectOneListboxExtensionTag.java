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

package org.apache.myfaces.tobago.internal.taglib.extension;

import org.apache.myfaces.tobago.apt.annotation.DynamicExpression;
import org.apache.myfaces.tobago.apt.annotation.ExtensionTag;
import org.apache.myfaces.tobago.apt.annotation.Tag;
import org.apache.myfaces.tobago.apt.annotation.TagAttribute;
import org.apache.myfaces.tobago.apt.annotation.UIComponentTagAttribute;
import org.apache.myfaces.tobago.internal.taglib.SelectOneListboxTag;

import javax.servlet.jsp.JspException;

/**
 * Render a single selection option listbox.
 */
@Tag(name = "selectOneListbox")
@ExtensionTag(baseClassName = "org.apache.myfaces.tobago.internal.taglib.SelectOneListboxTag")
public class SelectOneListboxExtensionTag extends TobagoExtensionBodyTagSupport {
  private javax.el.ValueExpression required;
  private javax.el.ValueExpression value;
  private javax.el.MethodExpression valueChangeListener;
  private javax.el.ValueExpression disabled;
  private javax.el.ValueExpression readonly;
  private javax.el.ValueExpression onchange;
  //private javax.el.ValueExpression inline;
  private javax.el.ValueExpression label;
  private javax.el.ValueExpression rendered;
  private javax.el.ValueExpression binding;
  private javax.el.ValueExpression tip;
  private javax.el.ValueExpression converter;
  private javax.el.MethodExpression validator;
  private javax.el.ValueExpression labelWidth;
  private javax.el.ValueExpression tabIndex;
  private javax.el.ValueExpression focus;
  private javax.el.ValueExpression validatorMessage;
  private javax.el.ValueExpression converterMessage;
  private javax.el.ValueExpression requiredMessage;
  private javax.el.ValueExpression markup;
  private String fieldId;

  private LabelExtensionTag labelTag;
  private SelectOneListboxTag selectOneListboxTag;

  @Override
  public int doStartTag() throws JspException {

    labelTag = new LabelExtensionTag();
    labelTag.setPageContext(pageContext);
    labelTag.setRows("*");
    if (id != null) {
      labelTag.setId(id);
    }
    if (label != null) {
      labelTag.setValue(label);
    }
    if (tip != null) {
      labelTag.setTip(tip);
    }
    if (rendered != null) {
      labelTag.setRendered(rendered);
    }
    if (labelWidth != null) {
      labelTag.setColumns(createStringValueExpression(labelWidth.getExpressionString() + ";*"));
    }
    if (markup != null) {
      labelTag.setMarkup(markup);
    }
    labelTag.setParent(getParent());
    labelTag.setJspId(nextJspId());
    labelTag.doStartTag();

    selectOneListboxTag = new SelectOneListboxTag();
    selectOneListboxTag.setPageContext(pageContext);
    if (value != null) {
      selectOneListboxTag.setValue(value);
    }
    if (valueChangeListener != null) {
      selectOneListboxTag.setValueChangeListener(valueChangeListener);
    }
    if (binding != null) {
      selectOneListboxTag.setBinding(binding);
    }
    if (onchange != null) {
      selectOneListboxTag.setOnchange(onchange);
    }
    if (validator != null) {
      selectOneListboxTag.setValidator(validator);
    }
    if (converter != null) {
      selectOneListboxTag.setConverter(converter);
    }
    if (disabled != null) {
      selectOneListboxTag.setDisabled(disabled);
    }
    /*if (inline != null) {
      selectOneListboxTag.setInline(inline);
    }*/
    if (focus != null) {
      selectOneListboxTag.setFocus(focus);
    }
    if (fieldId != null) {
      selectOneListboxTag.setId(fieldId);
    }
    if (label != null) {
      selectOneListboxTag.setLabel(label);
    }
    if (readonly != null) {
      selectOneListboxTag.setReadonly(readonly);
    }
    if (required != null) {
      selectOneListboxTag.setRequired(required);
    }
    if (tabIndex != null) {
      selectOneListboxTag.setTabIndex(tabIndex);
    }
    if (validatorMessage != null) {
      selectOneListboxTag.setValidatorMessage(validatorMessage);
    }
    if (converterMessage != null) {
      selectOneListboxTag.setConverterMessage(converterMessage);
    }
    if (requiredMessage != null) {
      selectOneListboxTag.setRequiredMessage(requiredMessage);
    }
    if (markup != null) {
      selectOneListboxTag.setMarkup(markup);
    }
    selectOneListboxTag.setParent(labelTag);
    selectOneListboxTag.setJspId(nextJspId());
    selectOneListboxTag.doStartTag();

    return super.doStartTag();
  }

  @Override
  public int doEndTag() throws JspException {
    selectOneListboxTag.doEndTag();
    labelTag.doEndTag();
    return super.doEndTag();
  }

  @Override
  public void release() {
    super.release();
    binding = null;
    onchange = null;
    disabled = null;
    //inline = null;
    labelWidth = null;
    label = null;
    readonly = null;
    rendered = null;
    converter = null;
    validator = null;
    required = null;
    tip = null;
    value = null;
    valueChangeListener = null;
    tabIndex = null;
    selectOneListboxTag = null;
    labelTag = null;
    focus = null;
    validatorMessage = null;
    converterMessage = null;
    requiredMessage = null;
    markup = null;
    fieldId = null;
  }

  /**
   * Flag indicating that a value is required.
   * If the value is an empty string a
   * ValidationError occurs and a Error Message is rendered.
   */
  @TagAttribute
  @UIComponentTagAttribute(type = "boolean", defaultValue = "false")
  public void setRequired(javax.el.ValueExpression required) {
    this.required = required;
  }

  /**
   * The current value of this component.
   */
  @TagAttribute
  @UIComponentTagAttribute(type = "java.lang.Object")
  public void setValue(javax.el.ValueExpression value) {
    this.value = value;
  }

  /**
   * MethodBinding representing a value change listener method
   * that will be notified when a new value has been set for this input component.
   * The expression must evaluate to a public method that takes a ValueChangeEvent
   * parameter, with a return type of void.
   *
   * @param valueChangeListener
   */
  @TagAttribute
  @UIComponentTagAttribute(
          type = {},
          expression = DynamicExpression.METHOD_EXPRESSION_REQUIRED,
          methodSignature = "javax.faces.event.ValueChangeEvent")
  public void setValueChangeListener(javax.el.MethodExpression valueChangeListener) {
    this.valueChangeListener = valueChangeListener;
  }

  /**
   * Flag indicating that this element is disabled.
   */
  @TagAttribute()
  @UIComponentTagAttribute(type = "boolean", defaultValue = "false")
  public void setDisabled(javax.el.ValueExpression disabled) {
    this.disabled = disabled;
  }

  /**
   * Flag indicating that this component will prohibit changes by the user.
   */
  @TagAttribute
  @UIComponentTagAttribute(type = "boolean", defaultValue = "false")
  public void setReadonly(javax.el.ValueExpression readonly) {
    this.readonly = readonly;
  }

  /**
   * Clientside script function to add to this component's onchange handler.
   */
  @TagAttribute
  @UIComponentTagAttribute()
  public void setOnchange(javax.el.ValueExpression onchange) {
    this.onchange = onchange;
  }

  /**
   * Text value to display as label.
   * If text contains an underscore the next character is used as accesskey.
   */
  @TagAttribute
  @UIComponentTagAttribute()
  public void setLabel(javax.el.ValueExpression label) {
    this.label = label;
  }

  /**
   * A method binding EL expression,
   * accepting FacesContext, UIComponent,
   * and Object parameters, and returning void, that validates
   * the component's local value.
   */
  @TagAttribute
  @UIComponentTagAttribute(type = {},
      expression = DynamicExpression.METHOD_EXPRESSION,
      methodSignature = { "javax.faces.context.FacesContext", "javax.faces.component.UIComponent", "java.lang.Object" })
  public void setValidator(javax.el.MethodExpression validator) {
    this.validator = validator;
  }

  /**
   * An expression that specifies the Converter for this component.
   * If the value binding expression is a String,
   * the String is used as an ID to look up a Converter.
   * If the value binding expression is a Converter,
   * uses that instance as the converter.
   * The value can either be a static value (ID case only)
   * or an EL expression.
   */
  @TagAttribute
  @UIComponentTagAttribute(type = "javax.faces.convert.Converter",
      expression = DynamicExpression.VALUE_EXPRESSION)
  public void setConverter(javax.el.ValueExpression converter) {
    this.converter = converter;
  }

  /**
   * Flag indicating whether or not this component should be rendered
   * (during Render Response Phase), or processed on any subsequent form submit.
   */
  @TagAttribute
  @UIComponentTagAttribute(type = "boolean", defaultValue = "true")
  public void setRendered(javax.el.ValueExpression rendered) {
    this.rendered = rendered;
  }

  /**
   * The value binding expression linking this
   * component to a property in a backing bean.
   */
  @TagAttribute
  @UIComponentTagAttribute(type = "javax.faces.component.UIComponent")
  public void setBinding(javax.el.ValueExpression binding) {
    this.binding = binding;
  }

  /**
   * Text value to display as tooltip.
   */
  @TagAttribute
  @UIComponentTagAttribute()
  public void setTip(javax.el.ValueExpression tip) {
    this.tip = tip;
  }

  /**
   * The width for the label component. Default: 'auto'.
   * This value is used in the gridLayouts columns attribute.
   * See gridLayout tag for valid values.
   */
  @TagAttribute
  @UIComponentTagAttribute()
  public void setLabelWidth(javax.el.ValueExpression labelWidth) {
    this.labelWidth = labelWidth;
  }

  @TagAttribute
  @UIComponentTagAttribute(type = "java.lang.Integer")
  public void setTabIndex(javax.el.ValueExpression tabIndex) {
    this.tabIndex = tabIndex;
  }

  /**
   * Flag indicating this component should receive the focus.
   */
  @TagAttribute
  @UIComponentTagAttribute(type = "boolean", defaultValue = "false")
  public void setFocus(javax.el.ValueExpression focus) {
    this.focus = focus;
  }

  /**
   * An expression that specifies the validator message
   */
  @TagAttribute
  @UIComponentTagAttribute()
  public void setValidatorMessage(javax.el.ValueExpression validatorMessage) {
    this.validatorMessage = validatorMessage;
  }

  /**
   * An expression that specifies the converter message
   */
  @TagAttribute
  @UIComponentTagAttribute()
  public void setConverterMessage(javax.el.ValueExpression converterMessage) {
    this.converterMessage = converterMessage;
  }

  /**
   * An expression that specifies the required message
   */
  @TagAttribute
  @UIComponentTagAttribute()
  public void setRequiredMessage(javax.el.ValueExpression requiredMessage) {
    this.requiredMessage = requiredMessage;
  }

  /**
   * Indicate markup of this component.
   * Possible value is 'none'. But this can be overridden in the theme.
   */
  @TagAttribute
  @UIComponentTagAttribute(defaultValue = "none", type = "java.lang.String[]")
  public void setMarkup(javax.el.ValueExpression markup) {
    this.markup = markup;
  }

  /**
   * The component identifier for the input field component inside of the container.
   * This value must be unique within the closest parent component that is a naming container.
   */
  @TagAttribute(rtexprvalue = true)
  @UIComponentTagAttribute
  public void setFieldId(String fieldId) {
    this.fieldId = fieldId;
  }

  /**
   * The component identifier for this component.
   * This value must be unique within the closest parent component that is a naming container.
   * For tx components the id will be set to the container (e. g. the panel).
   * To set the id of the input field, you have to use the attribute "fieldId".
   */
  @TagAttribute(rtexprvalue = true)
  @UIComponentTagAttribute
  public void setId(String id) {
    super.setId(id);
  }
}