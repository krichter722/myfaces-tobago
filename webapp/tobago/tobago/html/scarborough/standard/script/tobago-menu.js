// menu.js

function initMenuBar(divId, pageId) {
  var menubar = document.getElementById(divId);
  if (menubar && menubar.menu) {
    var top = getAbsoluteTop(menubar) + 1;
    var left = getAbsoluteLeft(menubar) + 1;
    var body = document.getElementById(pageId);
    menubar.menu.htmlElement = document.createElement('div');
    menubar.menu.htmlElement.className = "tobago-menubar-container";
    menubar.menu.htmlElement.style.top = top;
    menubar.menu.htmlElement.style.left = left;
    menubar.menu.htmlElement.innerHTML = menubar.menu.toHtml();
    body.appendChild(menubar.menu.htmlElement);

    initMenuItems(menubar.menu);
    setItemWidth(menubar.menu);
    setItemPositions(menubar.menu);
  }
  else {
    PrintDebug("keine menubar mit id ='" + divId + "'gefunden!");
  }
}

function createMenuRoot(id) {
  var menu = new MenuItem();
  menu.id = id + getSubComponentSeparator() + "menuRoot";
  menu.level = 0;
  return menu;
}

function MenuItem(label, action, disabled) {
  this.label = label;
  this.action = action;
  this.disabled = disabled;
  this.subItems = new Array();

  this.addMenuItem = function(menuItem) {
    var index = this.subItems.length;
    this.subItems[index] = menuItem;
    menuItem.parent = this;
    menuItem.id = this.id + getSubComponentSeparator() + index;
    menuItem.index = index;
    menuItem.level = this.level + 1;
  }

  this.toHtml = function() {
    var html = "";
    if (this.level != 0) {
      var onClick = "";
      if (this.action) {
        onClick = ' onclick="tobagoMenuItemOnmouseout(this, true) ; ' + this.action + '"';
      }
      if (this.level == 1) {
        onClick = ' onclick="tobagoMenuOpenMenu(this)"';
      }

      html += '<div class="tobago-menu-item"'
          + ' id="' + this.id + '"'
          + ' onmouseover="tobagoMenuItemOnmouseover(this)"'
          + ' onmouseout="tobagoMenuItemOnmouseout(this)"'
          + onClick
          + '>' + this.label + "</div>";
    }
    if (this.subItems.length > 0) {
      if (this.level != 0) {
        html += '<div class="tobago-menubar-subitem-container"'
            + ' id="' + this.id + getSubComponentSeparator() + 'items" >';
      }
      for (var i = 0; i< this.subItems.length; i++) {
        html += this.subItems[i].toHtml()
      }
      if (this.level != 0) {
        html += '</div>';
        if (isIE()) {
          html += '<iframe'
              + ' id="' + this.id + getSubComponentSeparator() + 'iframe" >'
              + ' class="tobago-menubar-subitem-iframe" '
              + ' style="display: none; visibility: hidden;"'
              + ' frameborder="0" scrolling="no" ></iframe>'
        }
      }
    }
    return html;
  }

  this.setHover = function() {
    if (! this.disabled) {
      addCssClass(this.htmlElement, "tobago-menu-item-hover");
    }
    if (this.level != 1 || this.parent.menuOpen) {
      this.openSubMenus();
    }
  }

  this.openMenu = function() {
    if (this.level == 1) {
      this.openSubMenus();
      this.parent.menuOpen = 1;
    }
    else {
      PrintDebug("ERROR : openMenu() is not allowed outside level 1!"
          + " this is level " + this.level);
    }
  }

  this.openSubMenus = function() {
    if (this.subItemContainer) {
      this.subItemContainer.style.visibility = 'visible';
      if (this.subItemIframe) {
        this.subItemIframe.style.visibility = 'visible';
      }
    }
  }

  this.removeHover = function(mouseOut) {
    if (mouseOut) {
      this.hover = false;
    }
    if (this.level > 0) {
      //PrintDebug("removeHover on " + this.id);
      if (! this.hover && ! this.isHoverChildren()) {
        myremoveCssClass(this.htmlElement, "tobago-menu-item-hover");
        if (this.subItemContainer) {
          this.subItemContainer.style.visibility = 'hidden';
          if (this.subItemIframe) {
            this.subItemIframe.style.visibility = 'hidden';
          }
        }
        if (this.level == 1 && ! this.parent.isHoverChildren()) {
          this.parent.menuOpen = 0;
        }
      }
      if (this.parent) {
        this.parent.removeHover(false);
      }
    }
  }

  this.onMouseOver = function() {
    //PrintDebug("onMouseOver " + this.id);
    this.hover = true;
    clearTimeout(this.removeHoverTimer);
    this.hoverTimer
        = setTimeout("tobagoMenuSetHover('" + this.id +"')", getMenuTimeoutHover());
  }
  this.onMouseOut = function(clicked) {
    //PrintDebug("onMouseOut " + this.id + "  clicked = " + clicked);
    if (this.hover) {
      clearTimeout(this.removeHoverTimer);
      clearTimeout(this.hoverTimer);
      if (clicked) {
        this.removeHover(true);
      } else {
        this.removeHoverTimer
            = setTimeout("tobagoMenuRemoveHover('" + this.id +"', true)", getMenuTimeoutOut());
      }
    }
  }

  this.isHoverChildren = function() {
    for (var i = 0; i < this.subItems.length; i++) {
       if (this.subItems[i].hover) {
         return true;
       }
       else if (this.subItems[i].isHoverChildren()) {
         return true;
       }
    }
    return false;
  }

}


function setItemWidth(menu) {

  if (menu.level != 0) {
    if (menu.htmlElement) {

      if (menu.level == 1) {
        menu.htmlElement.style.width = menu.htmlElement.scrollWidth + "px";
      }
      else { // level not 0 or 1

        var width = menu.parent.childWidth;

        if (! width) {
          width = 0;
          var re = new RegExp("(.*" + getSubComponentSeparator() +")\\d$");
          var childIdPrefix = menu.id.match(re)[1];
          var i = 0;
          var childElement = document.getElementById(childIdPrefix + i++);
          while (childElement) {
            //PrintDebug("item " + childElement.id + "  -->" + childElement.scrollWidth);
            width = Math.max(width, childElement.scrollWidth);
            childElement = document.getElementById(childIdPrefix + i++);
          }
          //PrintDebug("das waren " + (i-1) + " items  ---> width wird " + width);
          menu.parent.childWidth = width ;
        }
        menu.htmlElement.style.width = width + "px";

      }

    menu.htmlElement.style.overflow = 'hidden';
    }
  }
  for (var i = 0; i < menu.subItems.length; i++) {
    setItemWidth(menu.subItems[i]);
  }
  if (menu.subItemContainer && menu.level != 0) {
    menu.subItemContainer.style.width
        = (menu.childWidth + getSubitemContainerBorderWidth()) + "px";
    menu.subItemContainer.style.height = (menu.subItems.length  * getItemHeight()
        + getSubitemContainerBorderWidth()) + "px";

    if (menu.subItemIframe) {
      menu.subItemIframe.style.width = menu.subItemContainer.style.width;
      menu.subItemIframe.style.height = menu.subItemContainer.style.height;
    }
  }

  if (menu.level == 1) {
    menu.htmlElement.style.visibility = 'visible';
  }
}

function setItemPositions(menu) {

  if (menu.level != 0) {
    if (menu.htmlElement) {

      if (menu.level == 1) {
        menu.htmlElement.style.top = "0px";
        if (menu.subItemContainer) {
          menu.subItemContainer.style.top = "15px";
        }
        var left = 0;
        if (menu.index != 0) {
          var neighbour = menu.parent.subItems[menu.index -1];
          var left = neighbour.htmlElement.style.left.replace(/\D/g,"") - 0;
          left += neighbour.htmlElement.style.width.replace(/\D/g,"") - 0;
        }
        menu.htmlElement.style.left = left + "px";
        if (menu.subItemContainer) {
          menu.subItemContainer.style.left = left + "px";
        }  
      }
      else { // level not 0 or 1
        var top = top = (menu.index * getItemHeight());
        var left = 0;
        if (menu.subItemContainer) {
          //if (menu.level == 2) {
          //  top = getItemHeight();
          //}
          if (menu.level != 1) {
            left = menu.parent.childWidth;
          }
          menu.subItemContainer.style.top = top + "px";
          menu.subItemContainer.style.left = left + "px";
        }

        menu.htmlElement.style.top = top + "px";
        menu.htmlElement.style.left = "0px";

      }
      if (menu.subItemIframe) {
        menu.subItemIframe.style.top = menu.subItemContainer.style.top;
        menu.subItemIframe.style.left = menu.subItemContainer.style.left;
      }
    }
  }
  for (var i = 0; i < menu.subItems.length; i++) {
    setItemPositions(menu.subItems[i]);
  }
}

function initMenuItems(menu) {
  menu.htmlElement = document.getElementById(menu.id);
  if (menu.htmlElement) {
    menu.htmlElement.menuItem = menu;
    menu.subItemContainer =
        document.getElementById(menu.id + getSubComponentSeparator() + 'items');
    menu.subItemIframe =
        document.getElementById(menu.id + getSubComponentSeparator() + 'iframe');
    if (menu.subItemIframe) {
      menu.subItemIframe.style.visibility = "hidden";
      menu.subItemIframe.style.position = "absolute";
      menu.subItemIframe.style.border = "0px solid black";
      menu.subItemIframe.style.zIndex
          = tobagoGetRuntimeStyle(menu.htmlElement).zIndex - 1;
    }
  }
  for (var i = 0; i < menu.subItems.length; i++) {
    initMenuItems(menu.subItems[i]);
  }
}


function tobagoMenuItemOnmouseover(element) {
  element.menuItem.onMouseOver()
}
function tobagoMenuItemOnmouseout(element, clicked) {
  element.menuItem.onMouseOut(clicked)
}

function tobagoMenuOpenMenu(element) {
  element.menuItem.openMenu();
}

function tobagoMenuSetHover(id) {
  document.getElementById(id).menuItem.setHover();
}
function tobagoMenuRemoveHover(id, mouseOut) {
  document.getElementById(id).menuItem.removeHover(mouseOut);
}



function getSubComponentSeparator() {
  return "::"; // ToabgoConstants.SUBCOMPONENT_SEP
}

function getMenuTimeoutHover() {
  return 100;
}
function getMenuTimeoutOut() {
  return 150;
}

function getSubitemContainerBorderWidth() {
  return 2; // border * 2
}

function getItemHeight() {
  return 15;
}



function isIE() {
  var agt=navigator.userAgent.toLowerCase();
  if (document.all) {
    return true;
  } else {
    return false;
  }
}
function myremoveCssClass(element, className) {
   var re = new RegExp(" " + className + " ", 'g');
   element.className = element.className.replace(re," ");
   re = new RegExp(" " + className + "$");
   element.className = element.className.replace(re," ");
   re = new RegExp("^" + className + " ");
   element.className = element.className.replace(re," ");

   if (element.className == className) {
     element.className = "";
   }
}
function tobagoGetRuntimeStyle(element) {
  if (element.runtimeStyle) { // IE
    return element.runtimeStyle;
  }
  else {
    return document.defaultView.getComputedStyle(element, null);
  }
}

function getAbsoluteTop(element) {
  var top = 0;
  var parent = false;
  while (element.offsetParent) {
    top += element.offsetTop;
    if (parent && element.currentStyle) { // IE only
      top += element.currentStyle.borderTopWidth.replace(/\D/g, "") - 0;
    }
    element = element.offsetParent;
    parent = true;
  }
  return top;
}

function getAbsoluteLeft(element) {
  var left = 0;
  var parent = false;
  while (element.offsetParent) {
    left += element.offsetLeft;
    if (parent && element.currentStyle) {  // IE only
      left += element.currentStyle.borderLeftWidth.replace(/\D/g, "") - 0;
    }
    element = element.offsetParent;
    parent = true;
  }
  return left;
}