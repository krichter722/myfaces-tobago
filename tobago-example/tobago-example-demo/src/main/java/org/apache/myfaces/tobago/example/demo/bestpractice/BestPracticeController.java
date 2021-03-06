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

package org.apache.myfaces.tobago.example.demo.bestpractice;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;

@RequestScoped
@Named
public class BestPracticeController {

  private static final Logger LOG = LoggerFactory.getLogger(BestPracticeController.class);

  private String status;

  public String throwException() {
    throw new RuntimeException("This exception is forced by the user.");
  }

  public String viewPdfInBrowser() {
    return viewFile(false, true);
  }

  public String viewPdfOutsideOfBrowser() {
    return viewFile(true, true);
  }

  public String viewFile(final boolean outside, final boolean pdf) {

    final FacesContext facesContext = FacesContext.getCurrentInstance();

    InputStream inputStream = null;
    try {
      final String path = "content/30-concept/24-non-faces-response/x-sample." + (pdf ? "pdf" : "txt");
      inputStream = facesContext.getExternalContext().getResourceAsStream(path);
      if (inputStream == null) {
        inputStream = facesContext.getExternalContext().getResourceAsStream("/" + path);
      }
      final HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();
      response.setContentType(pdf ? "application/pdf" : "text/plain");
      if (outside) {
        response.setHeader("Content-Disposition", "attachment; filename=x-sample." + (pdf ? "pdf" : "txt"));
      }
      IOUtils.copy(inputStream, response.getOutputStream());
    } catch (final IOException e) {
      LOG.warn("Cannot deliver " + (pdf ? "pdf" : "txt"), e);
      return "error"; // response via faces
    } finally {
      IOUtils.closeQuietly(inputStream);
    }
    facesContext.responseComplete();
    return null;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(final String status) {
    this.status = status;
  }
}
