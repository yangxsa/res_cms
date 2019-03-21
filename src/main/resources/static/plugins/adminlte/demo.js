/**
 * AdminLTE Demo Menu
 * ------------------
 * You should not use this file in production.
 * This file is for demo purposes only.
 */
$(function () {

    var TabDefault = {
        slide: true
    };

    var TabSelector = {
        sidebar: '.control-sidebar',
        data   : '[data-toggle="control-sidebar"]',
        open   : '.control-sidebar-open',
        bg     : '.control-sidebar-bg',
        wrapper: '.wrapper',
        content: '.content-wrapper',
        boxed  : '.layout-boxed'
    };

    var TabClassName = {
        open : 'control-sidebar-open',
        fixed: 'fixed'
    };

    var TabEvent = {
        collapsed: 'collapsed.controlsidebar',
        expanded : 'expanded.controlsidebar'
    };

    // //页面最小高度
    var Default = {
        slimscroll : true,
        resetHeight: true,
        animationSpeed : 500,
        collapseTrigger: '[data-widget="collapse"]',
        removeTrigger  : '[data-widget="remove"]',
        collapseIcon   : 'fa-minus',
        expandIcon     : 'fa-plus',
        removeIcon     : 'fa-times'
    };

    var Selector = {
        wrapper       : '.wrapper',
        contentWrapper: '.content-wrapper',
        layoutBoxed   : '.layout-boxed',
        mainFooter    : '.main-footer',
        mainHeader    : '.main-header',
        sidebar       : '.sidebar',
        controlSidebar: '.control-sidebar',
        fixed         : '.fixed',
        sidebarMenu   : '.sidebar-menu',
        logo          : '.main-header .logo',
        tree        : '.tree',
        treeview    : '.treeview',
        treeviewMenu: '.treeview-menu',
        open        : '.menu-open, .active',
        li          : 'li',
        data        : '[data-widget="tree"]',
        active      : '.active'
    };

    var ClassName = {
        fixed         : 'fixed',
        holdTransition: 'hold-transition',
        open: 'menu-open',
        tree: 'tree'
    };

    var Event = {
        collapsed: 'collapsed.tree',
        expanded : 'expanded.tree'
    };

    var bindedResize = false;

    $(TabSelector.data).on("click",function (event) {
        if (event) event.preventDefault();
        _fixForBoxed($(TabSelector.bg));
        if (!$(TabSelector.sidebar).is(TabSelector.open) && !$('body').is(TabSelector.open)) {
            TabExpand();
        } else {
            TabCollapse();
        }
    })

    function TabExpand () {
        if (!TabDefault.slide) {
            $('body').addClass(TabClassName.open);
        } else {
            $(TabSelector.sidebar).addClass(TabClassName.open);
        }

        $(this.element).trigger($.Event(TabEvent.expanded));
    };

    function TabCollapse () {
        $('body, ' + TabSelector.sidebar).removeClass(TabClassName.open);
        $(this.element).trigger($.Event(TabEvent.collapsed));
    };

    function TabFix() {
        if ($('body').is(TabSelector.boxed)) {
            _fixForBoxed($(TabSelector.bg));
        }
    }

    function _fixForBoxed(bg) {
        bg.css({
            position: 'absolute',
            height  : $(TabSelector.wrapper).height()
        });
    };


  'use strict'

    /**
   * Get access to plugins
   */
  // $('[data-toggle="control-sidebar"]').controlSidebar()
  // $('[data-toggle="push-menu"]').pushMenu()

  var $pushMenu       = $('[data-toggle="push-menu"]').data('lte.pushmenu')
  var $controlSidebar = $('[data-toggle="control-sidebar"]').data('lte.controlsidebar')
  var $layout         = $('body').data('lte.layout')

  /**
   * List of all the available skins
   *
   * @type Array
   */
  var mySkins = [
    'skin-blue',
    'skin-black',
    'skin-red',
    'skin-yellow',
    'skin-purple',
    'skin-green',
    'skin-blue-light',
    'skin-black-light',
    'skin-red-light',
    'skin-yellow-light',
    'skin-purple-light',
    'skin-green-light'
  ]

  /**
   * Get a prestored setting
   *
   * @param String name Name of of the setting
   * @returns String The value of the setting | null
   */
  function get(name) {
    if (typeof (Storage) !== 'undefined') {
      return localStorage.getItem(name)
    } else {
      window.alert('Please use a modern browser to properly view this template!')
    }
  }

  /**
   * Store a new settings in the browser
   *
   * @param String name Name of the setting
   * @param String val Value of the setting
   * @returns void
   */
  function store(name, val) {
    if (typeof (Storage) !== 'undefined') {
      localStorage.setItem(name, val)
    } else {
      window.alert('Please use a modern browser to properly view this template!')
    }
  }

  /**
   * Toggles layout classes
   *
   * @param String cls the layout class to toggle
   * @returns void
   */
  function changeLayout(cls) {
    $('body').toggleClass(cls)
    fixSidebar();
    if ($('body').hasClass('fixed') && cls == 'fixed') {
      $pushMenu.expandOnHover()
      $layout.activate()
    }
    $controlSidebar.fix()
  }

  function expandOnHover() {
        $(Selector.mainSidebar).hover(function () {
            if ($('body').is(Selector.mini + Selector.collapsed)
                && $(window).width() > Default.collapseScreenSize) {
                expand();
            }
        }.bind(this), function () {
            if ($('body').is(Selector.expanded)) {
                collapse();
            }
        }.bind(this));
    };

  function activate () {
      fix();
      fixSidebar();

      $('body').removeClass(ClassName.holdTransition);
      if (Default.resetHeight) {
          $('body, html, ' + Selector.wrapper).css({
              'height': 'auto',
              'min-height': '100%'
          });
      }
      if (!bindedResize) {
          $(window).resize(function () {
              fix();
              fixSidebar();
              $(Selector.logo + ', ' + Selector.sidebar).one('webkitTransitionEnd otransitionend oTransitionEnd msTransitionEnd transitionend', function () {
                  fix();
                  fixSidebar();
              }.bind(this));
          }.bind(this));
          bindedResize = true;
      }
  }

  /**
   * Replaces the old skin with the new skin
   * @param String cls the new skin class
   * @returns Boolean false to prevent link's default action
   */
  function changeSkin(cls) {
    $.each(mySkins, function (i) {
      $('body').removeClass(mySkins[i])
    })

    $('body').addClass(cls)
    store('skin', cls)
    return false
  }

  /**
   * Retrieve default settings and apply them to the template
   *
   * @returns void
   */
  function setup() {
    var tmp = get('skin')
    if (tmp && $.inArray(tmp, mySkins))
      changeSkin(tmp)

    // Add the change skin listener
    $('[data-skin]').on('click', function (e) {
      if ($(this).hasClass('knob'))
        return
      e.preventDefault()
      changeSkin($(this).data('skin'))
    })

    // Add the layout manager
    $('[data-layout]').on('click', function () {
      changeLayout($(this).data('layout'))
    })

    $('[data-controlsidebar]').on('click', function () {
      changeLayout($(this).data('controlsidebar'))
      var slide = !$controlSidebar.options.slide

      $controlSidebar.options.slide = slide
      if (!slide)
        $('.control-sidebar').removeClass('control-sidebar-open')
    })

    $('[data-sidebarskin="toggle"]').on('click', function () {
      var $sidebar = $('.control-sidebar')
      if ($sidebar.hasClass('control-sidebar-dark')) {
        $sidebar.removeClass('control-sidebar-dark')
        $sidebar.addClass('control-sidebar-light')
      } else {
        $sidebar.removeClass('control-sidebar-light')
        $sidebar.addClass('control-sidebar-dark')
      }
    })

    $('[data-enable="expandOnHover"]').on('click', function () {
      $(this).attr('disabled', true)
      $pushMenu.expandOnHover()
      if (!$('body').hasClass('sidebar-collapse'))
        $('[data-layout="sidebar-collapse"]').click()
    })

    //  Reset options
    if ($('body').hasClass('fixed')) {
      $('[data-layout="fixed"]').attr('checked', 'checked')
    }
    if ($('body').hasClass('layout-boxed')) {
      $('[data-layout="layout-boxed"]').attr('checked', 'checked')
    }
    if ($('body').hasClass('sidebar-collapse')) {
      $('[data-layout="sidebar-collapse"]').attr('checked', 'checked')
    }

  }

  // Create the new tab
  var $tabPane = $('<div />', {
    'id'   : 'control-sidebar-theme-demo-options-tab',
    'class': 'tab-pane active'
  })

  // Create the tab button
  var $tabButton = $('<li />', { 'class': 'active' })
    .html('<a href=\'#control-sidebar-theme-demo-options-tab\' data-toggle=\'tab\'>'
      + '<i class="fa fa-wrench"></i>'
      + '</a>')

  // Add the tab button to the right sidebar tabs
  $('[href="#control-sidebar-home-tab"]')
    .parent()
    .before($tabButton)

  // Create the menu
  var $demoSettings = $('<div />')

  // Layout options
  $demoSettings.append(
    '<h4 class="control-sidebar-heading">'
    + '布局选项'
    + '</h4>'
    // Fixed layout
    + '<div class="form-group">'
    + '<label class="control-sidebar-subheading">'
    + '<input type="checkbox"data-layout="fixed"class="pull-right"/> '
    + '固定布局'
    + '</label>'
    + '<p>激活固定布局(与盒字布局不同用)</p>'
    + '</div>'
    // Boxed layout
    + '<div class="form-group">'
    + '<label class="control-sidebar-subheading">'
    + '<input type="checkbox"data-layout="layout-boxed" class="pull-right"/> '
    + '盒子布局'
    + '</label>'
    + '<p>激活盒子布局</p>'
    + '</div>'
    // Sidebar Toggle
    + '<div class="form-group">'
    + '<label class="control-sidebar-subheading">'
    + '<input type="checkbox"data-layout="sidebar-collapse"class="pull-right"/> '
    + '补充工具栏'
    + '</label>'
    + '<p>切换菜单栏状态(打开或折叠)</p>'
    + '</div>'
    // Sidebar mini expand on hover toggle
    + '<div class="form-group">'
    + '<label class="control-sidebar-subheading">'
    + '<input type="checkbox"data-enable="expandOnHover"class="pull-right"/> '
    + '菜单栏悬停是展开'
    + '</label>'
    + '<p>让迷你菜单栏悬停</p>'
    + '</div>'
    // Control Sidebar Toggle
    + '<div class="form-group">'
    + '<label class="control-sidebar-subheading">'
    + '<input type="checkbox"data-controlsidebar="control-sidebar-open"class="pull-right"/> '
    + '右侧边栏滑动'
    + '</label>'
    + '<p>幻灯片与推送内容之前切换</p>'
    + '</div>'
    // Control Sidebar Skin Toggle
    + '<div class="form-group">'
    + '<label class="control-sidebar-subheading">'
    + '<input type="checkbox"data-sidebarskin="toggle"class="pull-right"/> '
    + '主题皮肤'
    + '</label>'
    + '<p>切换系统皮肤</p>'
    + '</div>'
  )
  var $skinsList = $('<ul />', { 'class': 'list-unstyled clearfix' })

  // Dark sidebar skins
  var $skinBlue =
        $('<li />', { style: 'float:left; width: 33.33333%; padding: 5px;' })
          .append('<a href="javascript:void(0)" data-skin="skin-blue" style="display: block; box-shadow: 0 0 3px rgba(0,0,0,0.4)" class="clearfix full-opacity-hover">'
            + '<div><span style="display:block; width: 20%; float: left; height: 7px; background: #367fa9"></span><span class="bg-light-blue" style="display:block; width: 80%; float: left; height: 7px;"></span></div>'
            + '<div><span style="display:block; width: 20%; float: left; height: 20px; background: #222d32"></span><span style="display:block; width: 80%; float: left; height: 20px; background: #f4f5f7"></span></div>'
            + '</a>'
            + '<p class="text-center no-margin">蓝黑</p>')
  $skinsList.append($skinBlue)
  var $skinBlack =
        $('<li />', { style: 'float:left; width: 33.33333%; padding: 5px;' })
          .append('<a href="javascript:void(0)" data-skin="skin-black" style="display: block; box-shadow: 0 0 3px rgba(0,0,0,0.4)" class="clearfix full-opacity-hover">'
            + '<div style="box-shadow: 0 0 2px rgba(0,0,0,0.1)" class="clearfix"><span style="display:block; width: 20%; float: left; height: 7px; background: #fefefe"></span><span style="display:block; width: 80%; float: left; height: 7px; background: #fefefe"></span></div>'
            + '<div><span style="display:block; width: 20%; float: left; height: 20px; background: #222"></span><span style="display:block; width: 80%; float: left; height: 20px; background: #f4f5f7"></span></div>'
            + '</a>'
            + '<p class="text-center no-margin">白黑</p>')
  $skinsList.append($skinBlack)
  var $skinPurple =
        $('<li />', { style: 'float:left; width: 33.33333%; padding: 5px;' })
          .append('<a href="javascript:void(0)" data-skin="skin-purple" style="display: block; box-shadow: 0 0 3px rgba(0,0,0,0.4)" class="clearfix full-opacity-hover">'
            + '<div><span style="display:block; width: 20%; float: left; height: 7px;" class="bg-purple-active"></span><span class="bg-purple" style="display:block; width: 80%; float: left; height: 7px;"></span></div>'
            + '<div><span style="display:block; width: 20%; float: left; height: 20px; background: #222d32"></span><span style="display:block; width: 80%; float: left; height: 20px; background: #f4f5f7"></span></div>'
            + '</a>'
            + '<p class="text-center no-margin">紫黑</p>')
  $skinsList.append($skinPurple)
  var $skinGreen =
        $('<li />', { style: 'float:left; width: 33.33333%; padding: 5px;' })
          .append('<a href="javascript:void(0)" data-skin="skin-green" style="display: block; box-shadow: 0 0 3px rgba(0,0,0,0.4)" class="clearfix full-opacity-hover">'
            + '<div><span style="display:block; width: 20%; float: left; height: 7px;" class="bg-green-active"></span><span class="bg-green" style="display:block; width: 80%; float: left; height: 7px;"></span></div>'
            + '<div><span style="display:block; width: 20%; float: left; height: 20px; background: #222d32"></span><span style="display:block; width: 80%; float: left; height: 20px; background: #f4f5f7"></span></div>'
            + '</a>'
            + '<p class="text-center no-margin">绿黑</p>')
  $skinsList.append($skinGreen)
  var $skinRed =
        $('<li />', { style: 'float:left; width: 33.33333%; padding: 5px;' })
          .append('<a href="javascript:void(0)" data-skin="skin-red" style="display: block; box-shadow: 0 0 3px rgba(0,0,0,0.4)" class="clearfix full-opacity-hover">'
            + '<div><span style="display:block; width: 20%; float: left; height: 7px;" class="bg-red-active"></span><span class="bg-red" style="display:block; width: 80%; float: left; height: 7px;"></span></div>'
            + '<div><span style="display:block; width: 20%; float: left; height: 20px; background: #222d32"></span><span style="display:block; width: 80%; float: left; height: 20px; background: #f4f5f7"></span></div>'
            + '</a>'
            + '<p class="text-center no-margin">红黑</p>')
  $skinsList.append($skinRed)
  var $skinYellow =
        $('<li />', { style: 'float:left; width: 33.33333%; padding: 5px;' })
          .append('<a href="javascript:void(0)" data-skin="skin-yellow" style="display: block; box-shadow: 0 0 3px rgba(0,0,0,0.4)" class="clearfix full-opacity-hover">'
            + '<div><span style="display:block; width: 20%; float: left; height: 7px;" class="bg-yellow-active"></span><span class="bg-yellow" style="display:block; width: 80%; float: left; height: 7px;"></span></div>'
            + '<div><span style="display:block; width: 20%; float: left; height: 20px; background: #222d32"></span><span style="display:block; width: 80%; float: left; height: 20px; background: #f4f5f7"></span></div>'
            + '</a>'
            + '<p class="text-center no-margin">黄黑</p>')
  $skinsList.append($skinYellow)

  // Light sidebar skins
  var $skinBlueLight =
        $('<li />', { style: 'float:left; width: 33.33333%; padding: 5px;' })
          .append('<a href="javascript:void(0)" data-skin="skin-blue-light" style="display: block; box-shadow: 0 0 3px rgba(0,0,0,0.4)" class="clearfix full-opacity-hover">'
            + '<div><span style="display:block; width: 20%; float: left; height: 7px; background: #367fa9"></span><span class="bg-light-blue" style="display:block; width: 80%; float: left; height: 7px;"></span></div>'
            + '<div><span style="display:block; width: 20%; float: left; height: 20px; background: #f9fafc"></span><span style="display:block; width: 80%; float: left; height: 20px; background: #f4f5f7"></span></div>'
            + '</a>'
            + '<p class="text-center no-margin" style="font-size: 12px">蓝白</p>')
  $skinsList.append($skinBlueLight)
  var $skinBlackLight =
        $('<li />', { style: 'float:left; width: 33.33333%; padding: 5px;' })
          .append('<a href="javascript:void(0)" data-skin="skin-black-light" style="display: block; box-shadow: 0 0 3px rgba(0,0,0,0.4)" class="clearfix full-opacity-hover">'
            + '<div style="box-shadow: 0 0 2px rgba(0,0,0,0.1)" class="clearfix"><span style="display:block; width: 20%; float: left; height: 7px; background: #fefefe"></span><span style="display:block; width: 80%; float: left; height: 7px; background: #fefefe"></span></div>'
            + '<div><span style="display:block; width: 20%; float: left; height: 20px; background: #f9fafc"></span><span style="display:block; width: 80%; float: left; height: 20px; background: #f4f5f7"></span></div>'
            + '</a>'
            + '<p class="text-center no-margin" style="font-size: 12px">纯白</p>')
  $skinsList.append($skinBlackLight)
  var $skinPurpleLight =
        $('<li />', { style: 'float:left; width: 33.33333%; padding: 5px;' })
          .append('<a href="javascript:void(0)" data-skin="skin-purple-light" style="display: block; box-shadow: 0 0 3px rgba(0,0,0,0.4)" class="clearfix full-opacity-hover">'
            + '<div><span style="display:block; width: 20%; float: left; height: 7px;" class="bg-purple-active"></span><span class="bg-purple" style="display:block; width: 80%; float: left; height: 7px;"></span></div>'
            + '<div><span style="display:block; width: 20%; float: left; height: 20px; background: #f9fafc"></span><span style="display:block; width: 80%; float: left; height: 20px; background: #f4f5f7"></span></div>'
            + '</a>'
            + '<p class="text-center no-margin" style="font-size: 12px">紫白</p>')
  $skinsList.append($skinPurpleLight)
  var $skinGreenLight =
        $('<li />', { style: 'float:left; width: 33.33333%; padding: 5px;' })
          .append('<a href="javascript:void(0)" data-skin="skin-green-light" style="display: block; box-shadow: 0 0 3px rgba(0,0,0,0.4)" class="clearfix full-opacity-hover">'
            + '<div><span style="display:block; width: 20%; float: left; height: 7px;" class="bg-green-active"></span><span class="bg-green" style="display:block; width: 80%; float: left; height: 7px;"></span></div>'
            + '<div><span style="display:block; width: 20%; float: left; height: 20px; background: #f9fafc"></span><span style="display:block; width: 80%; float: left; height: 20px; background: #f4f5f7"></span></div>'
            + '</a>'
            + '<p class="text-center no-margin" style="font-size: 12px">绿白</p>')
  $skinsList.append($skinGreenLight)
  var $skinRedLight =
        $('<li />', { style: 'float:left; width: 33.33333%; padding: 5px;' })
          .append('<a href="javascript:void(0)" data-skin="skin-red-light" style="display: block; box-shadow: 0 0 3px rgba(0,0,0,0.4)" class="clearfix full-opacity-hover">'
            + '<div><span style="display:block; width: 20%; float: left; height: 7px;" class="bg-red-active"></span><span class="bg-red" style="display:block; width: 80%; float: left; height: 7px;"></span></div>'
            + '<div><span style="display:block; width: 20%; float: left; height: 20px; background: #f9fafc"></span><span style="display:block; width: 80%; float: left; height: 20px; background: #f4f5f7"></span></div>'
            + '</a>'
            + '<p class="text-center no-margin" style="font-size: 12px">红白</p>')
  $skinsList.append($skinRedLight)
  var $skinYellowLight =
        $('<li />', { style: 'float:left; width: 33.33333%; padding: 5px;' })
          .append('<a href="javascript:void(0)" data-skin="skin-yellow-light" style="display: block; box-shadow: 0 0 3px rgba(0,0,0,0.4)" class="clearfix full-opacity-hover">'
            + '<div><span style="display:block; width: 20%; float: left; height: 7px;" class="bg-yellow-active"></span><span class="bg-yellow" style="display:block; width: 80%; float: left; height: 7px;"></span></div>'
            + '<div><span style="display:block; width: 20%; float: left; height: 20px; background: #f9fafc"></span><span style="display:block; width: 80%; float: left; height: 20px; background: #f4f5f7"></span></div>'
            + '</a>'
            + '<p class="text-center no-margin" style="font-size: 12px">黄白</p>')
  $skinsList.append($skinYellowLight)

  $demoSettings.append('<h4 class="control-sidebar-heading">皮肤</h4>')
  $demoSettings.append($skinsList)

  $tabPane.append($demoSettings)
  $('#control-sidebar-home-tab').after($tabPane)

  setup()


  // $('[data-toggle="tooltip"]').tooltip();
  fix();
  fixSidebar();

  $('body').removeClass(ClassName.holdTransition);

  if (Default.resetHeight) {
    $('body, html, ' + Selector.wrapper).css({
        'height'    : 'auto',
        'min-height': '100%'
    });
  }

  if (!bindedResize) {
      $(window).resize(function () {
          fix();
          fixSidebar();
          $(Selector.logo + ', ' + Selector.sidebar).one('webkitTransitionEnd otransitionend oTransitionEnd msTransitionEnd transitionend', function () {
              fix();
              fixSidebar();
          }.bind(this));
      }.bind(this));
  }

  $(Selector.sidebarMenu).on('expanded.tree', function () {
      fix();
      fixSidebar();
  }.bind(this));

  $(Selector.sidebarMenu).on('collapsed.tree', function () {
      fix();
      fixSidebar();
  }.bind(this));

  function fix(){
     // Remove overflow from .wrapper if layout-boxed exists
     $(Selector.layoutBoxed + ' > ' + Selector.wrapper).css('overflow', 'hidden');

     // Get window height and the wrapper height
     var footerHeight  = $(Selector.mainFooter).outerHeight() || 0;
     var neg           = $(Selector.mainHeader).outerHeight() + footerHeight;
     var windowHeight  = $(window).height();
     var sidebarHeight = $(Selector.sidebar).height() || 0;

     // Set the min-height of the content and sidebar based on
     // the height of the document.
     if ($('body').hasClass(ClassName.fixed)) {
         $(Selector.contentWrapper).css('min-height', windowHeight - footerHeight);
     } else {
         var postSetHeight;

         if (windowHeight >= sidebarHeight) {
             $(Selector.contentWrapper).css('min-height', windowHeight - neg);
             postSetHeight = windowHeight - neg;
         } else {
             $(Selector.contentWrapper).css('min-height', sidebarHeight);
             postSetHeight = sidebarHeight;
         }

         // Fix for the control sidebar height
         var $controlSidebar = $(Selector.controlSidebar);
         if (typeof $controlSidebar !== 'undefined') {
             if ($controlSidebar.height() > postSetHeight)
                 $(Selector.contentWrapper).css('min-height', $controlSidebar.height());
         }
     }
  }

  function fixSidebar() {
      // Make sure the body tag has the .fixed class
      if (!$('body').hasClass(ClassName.fixed)) {
          if (typeof $.fn.slimScroll !== 'undefined') {
              $(Selector.sidebar).slimScroll({destroy: true}).height('auto');
          }
          return;
      }
      // Enable slimscroll for fixed layout
      if (Default.slimscroll) {
          if (typeof $.fn.slimScroll !== 'undefined') {
              // Destroy if it exists
              // $(Selector.sidebar).slimScroll({ destroy: true }).height('auto')

              // Add slimscroll
              $(Selector.sidebar).slimScroll({
                  height: ($(window).height() - $(Selector.mainHeader).height()) + 'px'
              });
          }
      }
  };

  //菜单栏收缩
  var $body = $("body");

  $(".sidebar-toggle").on("click",function () {
    console.log("ss");
       if($body.hasClass("sidebar-collapse")){
         $body.removeClass("sidebar-collapse");
       }else{
         $body.addClass("sidebar-collapse");
       }
  });

  //树状菜单
  $("[link]").on("click",function () {
      var link = $(this);

      var treeviewMenu = link.next(Selector.treeviewMenu);
      var parentLi = link.parent();
      var isOpen = parentLi.hasClass(ClassName.open);

      if (!parentLi.is(Selector.treeview)) {
          return;
      }
      if (link.attr('href') === '#') {
          event.preventDefault();
      }
      if (isOpen) {
          collapse(treeviewMenu, parentLi);
      } else {
          expand(treeviewMenu, parentLi);
      }
  });

  function collapse(tree,parentLi) {
      var element = $(".sidebar-menu.tree");
      var collapsedEvent = $.Event(Event.collapsed);

      tree.find(Selector.open).removeClass(ClassName.open);
      parentLi.removeClass(ClassName.open);
      tree.slideUp(Default.animationSpeed, function () {
          tree.find(Selector.open + ' > ' + Selector.treeview).slideUp();
          $(element).trigger(collapsedEvent);
      }.bind(this));
  };

  function expand(tree, parent) {
      var element = $(".sidebar-menu.tree");
      var expandedEvent = $.Event(Event.expanded);

      if (Default.accordion) {
          var openMenuLi = parent.siblings(Selector.open);
          var openTree = openMenuLi.children(Selector.treeviewMenu);
          this.collapse(openTree, openMenuLi);
      }
      parent.addClass(ClassName.open);
      tree.slideDown(Default.animationSpeed, function () {
          $(element).trigger(expandedEvent);
      }.bind(this));
  };

});
