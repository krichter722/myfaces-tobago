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

package org.apache.myfaces.tobago.apt.processor;

import org.w3c.dom.Document;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

@SupportedSourceVersion(SourceVersion.RELEASE_5)
public abstract class AbstractGenerator extends AbstractProcessor {

  static final String JSF_VERSION = "jsfVersion";

  private List<TypeElement> types;
  private List<PackageElement> packages;

  public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {

    info("**********************************************************************************");
    info("* Starting generator: " + getClass().getName());
    info("* Number of annotations:   " + annotations.size());
    for (TypeElement typeElement : annotations) {
      info("* Type element: " + typeElement.getQualifiedName());
    }

    if (annotations.size() == 0) {
      // TDB Why this case happen?
      return false;
    }

    types = new ArrayList<TypeElement>();
    packages = new ArrayList<PackageElement>();
    for (TypeElement element : annotations) {
      final Collection<? extends Element> elementsAnnotatedWith = roundEnv
          .getElementsAnnotatedWith(element);
      for (Element e : elementsAnnotatedWith) {
        if (e instanceof TypeElement) {
          if (!types.contains(e)) { // todo: optimize, O(n^2)?
            types.add((TypeElement) e);
          }
        }
        if (e instanceof PackageElement) {
          if (!packages.contains(e)) {
            packages.add((PackageElement) e);
          }
        }
      }
    }

    Collections.sort(types, new Comparator<TypeElement>() {
      public int compare(TypeElement d1, TypeElement d2) {
        return d1.getSimpleName().toString().compareTo(d2.getSimpleName().toString());
      }
    });

    configure();

    try {
      generate();
    } catch (Exception e) {
      error(e);
    }

    return false;
  }

  protected abstract void configure();

  protected abstract void generate() throws Exception;

  protected void info(String message) {
    processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE, message);
  }

  protected void warn(String message) {
    processingEnv.getMessager().printMessage(Diagnostic.Kind.WARNING, message);
  }

  protected void error(Exception e) {
    final StringWriter out = new StringWriter();
    e.printStackTrace(new PrintWriter(out));
    processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR, "" + e.getMessage() + "\n" + out.toString());
  }

  protected void addLeafTextElement(String text, String node, org.w3c.dom.Element parent, Document document) {
    org.w3c.dom.Element element = document.createElement(node);
    element.appendChild(document.createTextNode(text));
    parent.appendChild(element);
  }

  protected void addLeafCDATAElement(String text, String node, org.w3c.dom.Element parent, Document document) {
    org.w3c.dom.Element element = document.createElement(node);
    element.appendChild(document.createCDATASection(text));
    parent.appendChild(element);
  }

  public List<TypeElement> getTypes() {
    return types;
  }

  public List<PackageElement> getPackages() {
    return packages;
  }
}
