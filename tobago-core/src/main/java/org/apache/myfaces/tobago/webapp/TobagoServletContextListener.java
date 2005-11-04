/*
 * Copyright 2002-2005 The Apache Software Foundation.
 * 
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 * 
 *        http://www.apache.org/licenses/LICENSE-2.0
 * 
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
/*
 * Created Nov 13, 2002 at 8:43:20 AM.
 * $Id$
 */
package org.apache.myfaces.tobago.webapp;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.myfaces.tobago.config.ThemeConfig;
import org.apache.myfaces.tobago.config.TobagoConfig;
import org.apache.myfaces.tobago.config.TobagoConfigParser;
import org.apache.myfaces.tobago.context.ResourceManagerFactory;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.HashMap;

public class TobagoServletContextListener implements ServletContextListener {

// ///////////////////////////////////////////// constants

  private static final Log LOG
      = LogFactory.getLog(TobagoServletContextListener.class);

// ///////////////////////////////////////////// code

  public void contextInitialized(ServletContextEvent event) {

    if (LOG.isInfoEnabled()) {
      LOG.info("*** contextInitialized ***");
    }

    ServletContext servletContext = event.getServletContext();

    if (servletContext.getAttribute(TobagoConfig.TOBAGO_CONFIG) != null) {
      LOG.warn("Tobago has been already initialized. Do nothing.");
      return;
    }

    try {

      // tobago-config.xml
      TobagoConfig tobagoConfig = new TobagoConfig();
      TobagoConfigParser.parse(servletContext, tobagoConfig);
      servletContext.setAttribute(TobagoConfig.TOBAGO_CONFIG, tobagoConfig);

      // resources
      ResourceManagerFactory.init(servletContext, tobagoConfig);

      // theme config cache
      servletContext.setAttribute(ThemeConfig.THEME_CONFIG_CACHE, new HashMap());

    } catch (Throwable e) {
      if (LOG.isFatalEnabled()) {
        String error = "Error while deploy process. Tobago can't be initialized! " +
            "Application will not run!";
        LOG.fatal(error, e);
        throw new RuntimeException(error, e);
      }
    }
  }

  public void contextDestroyed(ServletContextEvent event) {
    if (LOG.isInfoEnabled()) {
      LOG.info("*** contextDestroyed ***\n--- snip ---------"
          + "--------------------------------------------------------------");
    }

    ServletContext servletContext = event.getServletContext();

    servletContext.removeAttribute(TobagoConfig.TOBAGO_CONFIG);
    ResourceManagerFactory.release(servletContext);
    servletContext.removeAttribute(ThemeConfig.THEME_CONFIG_CACHE);

    LogFactory.releaseAll();
//    LogManager.shutdown();
  }

}
