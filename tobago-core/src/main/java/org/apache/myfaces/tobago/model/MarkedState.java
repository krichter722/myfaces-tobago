package org.apache.myfaces.tobago.model;

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

import javax.swing.tree.TreeNode;
import java.io.Serializable;

public class MarkedState implements Serializable {

  private TreePath marked;

  public boolean isMarked(TreePath path) {
    if (marked != null) {
      return marked.equals(path);
    } else {
      return marked == path; // == null
    }
  }

  public TreePath getMarked() {
    return marked;
  }

  public void setMarked(TreePath marked) {
    this.marked = marked;
  }

  public void setMarked(TreeNode marked) {
    this.marked = new TreePath(marked);
  }

  /**
   * Resets the marked state, so that no TreePath is marked.
   */
  public void reset() {
    marked = null;
  }
}