# Licensed to the Apache Software Foundation (ASF) under one or more
# contributor license agreements.  See the NOTICE file distributed with
# this work for additional information regarding copyright ownership.
# The ASF licenses this file to You under the Apache License, Version 2.0
# (the "License"); you may not use this file except in compliance with
# the License.  You may obtain a copy of the License at
#
#      http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.

FROM tomcat:8.5-jre8
MAINTAINER dev@myfaces.apache.org

RUN apt-get update \
 && apt-get install -y less vim procps ansible python-lxml

RUN rm -r /usr/local/tomcat/webapps/*

COPY deploy-and-run.sh /usr/local/tomcat/bin
RUN chmod +x /usr/local/tomcat/bin/deploy-and-run.sh

#ENV TOBAGO_VERSION undefined
#ENV CONTEXT_PATH ROOT

CMD ["deploy-and-run.sh"]
