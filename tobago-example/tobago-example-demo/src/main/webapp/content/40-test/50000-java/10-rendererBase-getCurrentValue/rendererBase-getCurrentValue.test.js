/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

function jQueryFrame(expression) {
  return document.getElementById("page:testframe").contentWindow.jQuery(expression);
}

QUnit.test("formatted values: out string", function (assert) {
  var $out = jQueryFrame("#page\\:outString");
  assert.equal($out.text().trim(), "simple string");
});

QUnit.test("formatted values: out date", function (assert) {
  var $out = jQueryFrame("#page\\:outDate");
  assert.equal($out.text().trim(), "24.07.1969");
});

// TODO: we may need not trim() below, if we have a labelLayout="skip" freature to skip the sourounging container.
QUnit.test("formatted values: out method", function (assert) {
  var $out = jQueryFrame("#page\\:outMethod");
  assert.equal($out.text().trim(), "HELLO WORLD!");
});
