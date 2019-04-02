/* Tree()
 * ======
 * Converts a nested list into a multilevel
 * tree view menu.
 *
 * @Usage: $('.my-menu').tree(options)
 *         or add [data-widget="tree"] to the ul element
 *         Pass any option as data-option="value"
 */
// //页面最小高度
var Default = {
    slimscroll : true,
    resetHeight: true,
    accordion:true,
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

var bindedResize=false;

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