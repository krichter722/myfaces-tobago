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

package org.apache.myfaces.tobago.util;

import org.apache.myfaces.tobago.internal.util.HtmlWriterUtils;
import org.junit.Assert;
import org.junit.Test;

import java.io.CharArrayWriter;
import java.io.IOException;

public class HtmlWriterUtilsUnitTest {

  // some chars must escaped in attribute values other than in text
  // put them at beginning of raw texts and in both escaped texts

  // HTML 4.0, section B.7.1: ampersands followed by
  // an open brace don't get escaped
  public static final String[] RAW_TEXTS = {
      "oeffnende spitze klammern werden in attributen doch escaped <tagname >",
      "& followed by an { -> &{ don't get escaped in attributes",
      "abcdefghijklmnopqrstuvwxyz0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ",
      "\u00a0\u00a1\u00a2\u00a3\u00a4\u00a5\u00a6\u00a7\u00a8\u00a9\u00aa\u00ab\u00ac\u00ad\u00ae\u00af",
      "\u00b0\u00b1\u00b2\u00b3\u00b4\u00b5\u00b6\u00b7\u00b8\u00b9\u00ba\u00bb\u00bc\u00bd\u00be\u00bf",
      "\u00c0\u00c1\u00c2\u00c3\u00c4\u00c5\u00c6\u00c7\u00c8\u00c9\u00ca\u00cb\u00cc\u00cd\u00ce\u00cf",
      "\u00d0\u00d1\u00d2\u00d3\u00d4\u00d5\u00d6\u00d7\u00d8\u00d9\u00da\u00db\u00dc\u00dd\u00de\u00df",
      "\u00e0\u00e1\u00e2\u00e3\u00e4\u00e5\u00e6\u00e7\u00e8\u00e9\u00ea\u00eb\u00ec\u00ed\u00ee\u00ef",
      "\u00f0\u00f1\u00f2\u00f3\u00f4\u00f5\u00f6\u00f7\u00f8\u00f9\u00fa\u00fb\u00fc\u00fd\u00fe\u00ff"

  };
  public static final String[] ESCAPED_TEXTS = {
      "oeffnende spitze klammern werden in attributen doch escaped &lt;tagname &gt;",
      "&amp; followed by an { -&gt; &amp;{ don't get escaped in attributes",
      RAW_TEXTS[2], // no escape needed
      "&nbsp;&iexcl;&cent;&pound;&curren;&yen;&brvbar;&sect;&uml;&copy;&ordf;&laquo;&not;&shy;&reg;&macr;",
      "&deg;&plusmn;&sup2;&sup3;&acute;&micro;&para;&middot;&cedil;&sup1;&ordm;&raquo;&frac14;&frac12;"
          + "&frac34;&iquest;",
      "&Agrave;&Aacute;&Acirc;&Atilde;&Auml;&Aring;&AElig;&Ccedil;&Egrave;&Eacute;&Ecirc;&Euml;&Igrave;&Iacute;"
          + "&Icirc;&Iuml;",
      "&ETH;&Ntilde;&Ograve;&Oacute;&Ocirc;&Otilde;&Ouml;&times;&Oslash;&Ugrave;&Uacute;&Ucirc;&Uuml;&Yacute;"
          + "&THORN;&szlig;",
      "&agrave;&aacute;&acirc;&atilde;&auml;&aring;&aelig;&ccedil;&egrave;&eacute;&ecirc;&euml;&igrave;&iacute;"
          + "&icirc;&iuml;",
      "&eth;&ntilde;&ograve;&oacute;&ocirc;&otilde;&ouml;&divide;&oslash;&ugrave;&uacute;&ucirc;&uuml;&yacute;"
          + "&thorn;&yuml;"
  };

  public static final String[] ESCAPED_ATTRIBUTES = {
      "oeffnende spitze klammern werden in attributen doch escaped &lt;tagname &gt;",
      "&amp; followed by an { -&gt; &{ don't get escaped in attributes",
      RAW_TEXTS[2] // no escape needed
  };

  @Test
  public void test() {
    final CharArrayWriter writer = new CharArrayWriter();
    final HtmlWriterUtils helper = new HtmlWriterUtils(writer, "");

    for (int i = 0; i < ESCAPED_TEXTS.length; i++) {
      testText(helper, writer, RAW_TEXTS[i], ESCAPED_TEXTS[i]);
      if (i < ESCAPED_ATTRIBUTES.length) {
        testAttributeValue(helper, writer, RAW_TEXTS[i], ESCAPED_ATTRIBUTES[i]);
      }
    }
  }

  private void testText(
      final HtmlWriterUtils writerUtil, final CharArrayWriter writer, final String text, final String escaped) {
    try {
      writer.reset();
      writerUtil.writeText(text);
      final String result = String.valueOf(writer.toCharArray());
      Assert.assertEquals(result, escaped);

    } catch (final IOException e) {
      // could not occur with CharArrayWriter
    }
  }

  private void testAttributeValue(
      final HtmlWriterUtils writerUtil, final CharArrayWriter writer, final String text, final String escaped) {
    try {
      writer.reset();
      writerUtil.writeAttributeValue(text);
      final String result = String.valueOf(writer.toCharArray());
      Assert.assertEquals(result, escaped);

    } catch (final IOException e) {
      // could not occur with CharArrayWriter
    }
  }
}
