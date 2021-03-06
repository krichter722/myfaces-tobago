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

QUnit.test("Simple Panel", function (assert) {
  assert.expect(14);
  var done = assert.async(5);
  var step = 1;

  var $messages = jQueryFrame("#page\\:messages.tobago-messages div");
  var $show = jQueryFrame("#\\page\\:mainForm\\:simple\\:showSimple");
  var $hide = jQueryFrame("#\\page\\:mainForm\\:simple\\:hideSimple");
  var $submit = jQueryFrame("#\\page\\:mainForm\\:simple\\:submitSimple");
  var $panelCollapsed = jQueryFrame("#page\\:mainForm\\:simple\\:simplePanel\\:\\:collapse");
  var $in = jQueryFrame("#page\\:mainForm\\:simple\\:inSimple\\:\\:field");

  $show.click();

  jQuery("#page\\:testframe").load(function () {
    if (step === 1) {
      $panelCollapsed = jQueryFrame($panelCollapsed.selector);
      $in = jQueryFrame($in.selector);
      $submit = jQueryFrame($submit.selector);

      assert.equal($panelCollapsed.val(), "false");
      assert.equal($in.length, 1);
      $in.val("some text");
      $submit.click();

      step++;
      done();
    } else if (step === 2) {
      $messages = jQueryFrame($messages.selector);
      $panelCollapsed = jQueryFrame($panelCollapsed.selector);
      $in = jQueryFrame($in.selector);
      $submit = jQueryFrame($submit.selector);

      assert.equal($messages.length, 0);
      assert.equal($panelCollapsed.val(), "false");
      assert.equal($in.length, 1);
      $in.val("");
      $submit.click();

      step++;
      done();
    } else if (step === 3) {
      $messages = jQueryFrame($messages.selector);
      $panelCollapsed = jQueryFrame($panelCollapsed.selector);
      $in = jQueryFrame($in.selector);
      $hide = jQueryFrame($hide.selector);

      assert.equal($messages.length, 1);
      assert.equal($panelCollapsed.val(), "false");
      assert.equal($in.length, 1);
      $hide.click();

      step++;
      done();
    } else if (step === 4) {
      $messages = jQueryFrame($messages.selector);
      $panelCollapsed = jQueryFrame($panelCollapsed.selector);
      $in = jQueryFrame($in.selector);
      $submit = jQueryFrame($submit.selector);

      assert.equal($messages.length, 0);
      assert.equal($panelCollapsed.val(), "true");
      assert.equal($in.length, 0);
      $submit.click();

      step++;
      done();
    } else if (step === 5) {
      $messages = jQueryFrame($messages.selector);
      $panelCollapsed = jQueryFrame($panelCollapsed.selector);
      $in = jQueryFrame($in.selector);

      assert.equal($messages.length, 0);
      assert.equal($panelCollapsed.val(), "true");
      assert.equal($in.length, 0);

      step++;
      done();
    }
  });
});

QUnit.test("Full Server Request", function (assert) {
  assert.expect(14);
  var done = assert.async(5);
  var step = 1;

  var $messages = jQueryFrame("#page\\:messages.tobago-messages div");
  var $show = jQueryFrame("#\\page\\:mainForm\\:server\\:showServer");
  var $hide = jQueryFrame("#\\page\\:mainForm\\:server\\:hideServer");
  var $submit = jQueryFrame("#\\page\\:mainForm\\:server\\:submitServer");
  var $panelCollapsed = jQueryFrame("#page\\:mainForm\\:server\\:serverPanel\\:\\:collapse");
  var $in = jQueryFrame("#page\\:mainForm\\:server\\:inServer\\:\\:field");

  $show.click();

  jQuery("#page\\:testframe").load(function () {
    if (step === 1) {
      $panelCollapsed = jQueryFrame($panelCollapsed.selector);
      $in = jQueryFrame($in.selector);
      $submit = jQueryFrame($submit.selector);

      assert.equal($panelCollapsed.val(), "false");
      assert.equal($in.length, 1);
      $in.val("some text");
      $submit.click();

      step++;
      done();
    } else if (step === 2) {
      $messages = jQueryFrame($messages.selector);
      $panelCollapsed = jQueryFrame($panelCollapsed.selector);
      $in = jQueryFrame($in.selector);
      $submit = jQueryFrame($submit.selector);

      assert.equal($messages.length, 0);
      assert.equal($panelCollapsed.val(), "false");
      assert.equal($in.length, 1);
      $in.val("");
      $submit.click();

      step++;
      done();
    } else if (step === 3) {
      $messages = jQueryFrame($messages.selector);
      $panelCollapsed = jQueryFrame($panelCollapsed.selector);
      $in = jQueryFrame($in.selector);
      $hide = jQueryFrame($hide.selector);

      assert.equal($messages.length, 1);
      assert.equal($panelCollapsed.val(), "false");
      assert.equal($in.length, 1);
      $hide.click();

      step++;
      done();
    } else if (step === 4) {
      $messages = jQueryFrame($messages.selector);
      $panelCollapsed = jQueryFrame($panelCollapsed.selector);
      $in = jQueryFrame($in.selector);
      $submit = jQueryFrame($submit.selector);

      assert.equal($messages.length, 0);
      assert.equal($panelCollapsed.val(), "true");
      assert.equal($in.length, 0);
      $submit.click();

      step++;
      done();
    } else if (step === 5) {
      $messages = jQueryFrame($messages.selector);
      $panelCollapsed = jQueryFrame($panelCollapsed.selector);
      $in = jQueryFrame($in.selector);

      assert.equal($messages.length, 0);
      assert.equal($panelCollapsed.val(), "true");
      assert.equal($in.length, 0);

      step++;
      done();
    }
  });
});

QUnit.test("Client Sided", function (assert) {
  assert.expect(14);
  var done = assert.async(3);
  var step = 1;

  var $messages = jQueryFrame("#page\\:messages.tobago-messages div");
  var $show = jQueryFrame("#\\page\\:mainForm\\:client\\:showClient");
  var $hide = jQueryFrame("#\\page\\:mainForm\\:client\\:hideClient");
  var $submit = jQueryFrame("#\\page\\:mainForm\\:client\\:submitClient");
  var $panelCollapsed = jQueryFrame("#page\\:mainForm\\:client\\:clientPanel\\:\\:collapse");
  var $in = jQueryFrame("#page\\:mainForm\\:client\\:inClient\\:\\:field");

  $show.click();
  assert.equal($panelCollapsed.val(), "false");
  assert.equal($in.length, 1);
  $in.val("some text");
  $submit.click();

  jQuery("#page\\:testframe").load(function () {
    if (step === 1) {
      $messages = jQueryFrame($messages.selector);
      $panelCollapsed = jQueryFrame($panelCollapsed.selector);
      $in = jQueryFrame($in.selector);
      $submit = jQueryFrame($submit.selector);

      assert.equal($messages.length, 0);
      assert.equal($panelCollapsed.val(), "false");
      assert.equal($in.length, 1);
      $in.val("");
      $submit.click();

      step++;
      done();
    } else if (step === 2) {
      $messages = jQueryFrame($messages.selector);
      $panelCollapsed = jQueryFrame($panelCollapsed.selector);
      $in = jQueryFrame($in.selector);
      $hide = jQueryFrame($hide.selector);
      $submit = jQueryFrame($submit.selector);

      assert.equal($messages.length, 1);
      assert.equal($panelCollapsed.val(), "false");
      assert.equal($in.length, 1);
      $hide.click();

      assert.equal($messages.length, 1);
      assert.equal($panelCollapsed.val(), "true");
      assert.equal($in.length, 1);
      $submit.click();

      step++;
      done();
    } else if (step === 3) {
      $messages = jQueryFrame($messages.selector);
      $panelCollapsed = jQueryFrame($panelCollapsed.selector);
      $in = jQueryFrame($in.selector);

      assert.equal($messages.length, 1);
      assert.equal($panelCollapsed.val(), "false");
      assert.equal($in.length, 1);

      step++;
      done();
    }
  });
});

QUnit.test("Ajax", function (assert) {
  assert.expect(13);
  var done = assert.async(5);
  var step = 1;

  var $messages = jQueryFrame("#page\\:messages.tobago-messages div");
  var $show = jQueryFrame("#\\page\\:mainForm\\:ajax\\:showAjax");
  var $hide = jQueryFrame("#\\page\\:mainForm\\:ajax\\:hideAjax");
  var $submit = jQueryFrame("#\\page\\:mainForm\\:ajax\\:submitAjax");
  var $panelCollapsed = jQueryFrame("#page\\:mainForm\\:ajax\\:ajaxPanel\\:\\:collapse");
  var $in = jQueryFrame("#page\\:mainForm\\:ajax\\:inAjax\\:\\:field");

  $show.click();

  waitForAjax(function () {
    $panelCollapsed = jQueryFrame($panelCollapsed.selector);
    $in = jQueryFrame($in.selector);
    return step === 1
        && $panelCollapsed.val() === "false"
        && $in.length === 1;
  }, function () {
    $panelCollapsed = jQueryFrame($panelCollapsed.selector);
    $in = jQueryFrame($in.selector);
    $submit = jQueryFrame($submit.selector);

    assert.equal($panelCollapsed.val(), "false");
    assert.equal($in.length, 1);
    $in.val("some text");
    $submit.click();

    step++;
    done();
  });

  jQuery("#page\\:testframe").load(function () {
    if (step === 2) {
      $messages = jQueryFrame($messages.selector);
      $panelCollapsed = jQueryFrame($panelCollapsed.selector);
      $in = jQueryFrame($in.selector);
      $submit = jQueryFrame($submit.selector);

      assert.equal($messages.length, 0);
      assert.equal($panelCollapsed.val(), "false");
      assert.equal($in.length, 1);
      $in.val("");
      $submit.click();

      step++;
      done();
    } else if (step === 3) {
      $messages = jQueryFrame($messages.selector);
      $panelCollapsed = jQueryFrame($panelCollapsed.selector);
      $in = jQueryFrame($in.selector);
      $hide = jQueryFrame($hide.selector);

      assert.equal($messages.length, 1);
      assert.equal($panelCollapsed.val(), "false");
      assert.equal($in.length, 1);
      $hide.click();

      step++;
      done();

      waitForAjax(function () {
        $panelCollapsed = jQueryFrame($panelCollapsed.selector);
        $in = jQueryFrame($in.selector);
        return step === 4
            && $panelCollapsed.val() === "true"
            && $in.length === 0;
      }, function () {
        $panelCollapsed = jQueryFrame($panelCollapsed.selector);
        $in = jQueryFrame($in.selector);
        $submit = jQueryFrame($submit.selector);

        assert.equal($panelCollapsed.val(), "true");
        assert.equal($in.length, 0);
        $submit.click();

        step++;
        done();
      });
    } else if (step === 5) {
      $messages = jQueryFrame($messages.selector);
      $panelCollapsed = jQueryFrame($panelCollapsed.selector);
      $in = jQueryFrame($in.selector);

      assert.equal($messages.length, 0);
      assert.equal($panelCollapsed.val(), "true");
      assert.equal($in.length, 0);

      step++;
      done();
    }
  });
});
