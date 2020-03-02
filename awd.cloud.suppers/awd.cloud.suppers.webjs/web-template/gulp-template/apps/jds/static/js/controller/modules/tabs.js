define([
    '/apps/jds/views/pages/common/tabs.html'
], function (require, exports, module) {

    // 导入 Tab 结构 HTML 字符串
    var tabsTemplate = require('/apps/jds/views/pages/common/tabs.html');

    var createElements = function (options) {
        var element = document.createElement('div');
        element.innerHTML = template.render(tabsTemplate, options);
        return element.firstElementChild;
    };

    var initEvents = function (tabControl) {
        tabControl.tabButtonContainer.addEventListener('click', function (e) {
            var closestTabButton = e.target.closest('.tab-control-button');
            if (closestTabButton) {
                tabControl.navigateToTabIndex(+closestTabButton.dataset.index);
            }
        });
    };

    var TabControl = function (options) {
        this.options = options;
        this.options.activeIndex = 0;
        this.element = createElements(options);
        this.tabButtonContainer = this.element.querySelector('.tab-title');
        this.tabContentContainer = this.element.querySelector('.tab-item-container');

        initEvents(this);
    };

    TabControl.prototype.render = function () {
        var i, self = this;
        for (i = 0; i < this.options.items.length; i++) {
            var mod = this.options.items[i].mod;
            if (mod) {
                var modHtml = mod.template || mod.html;
                if (mod.render) {
                    try {
                        var element = mod.render();
                        self.tabContentContainer.children[i].appendChild(element);
                    } catch (ex) {
                        console.error(ex);
                    }
                } else if (modHtml) {
                    self.tabContentContainer.children[i].innerHTML = modHtml;
                }
            } else {
                console.error('module ' + this.options.items[i].name + ' is undefined');
            }
        }
        return this.element;
    };

    TabControl.prototype.navigateToTabName = function (name) {
        var i;
        for (i = 0; i < this.options.items.length; i++) {
            if (this.options.items[i].name === name) {
                this.navigateToTabIndex(i);
                break;
            }
        }
    };

    TabControl.prototype.navigateToTabIndex = function (index) {
        if (this.options.activeIndex !== index) {
            this.tabButtonContainer.children[this.options.activeIndex].classList.remove('tab-active');
            this.tabButtonContainer.children[index].classList.add('tab-active');
            this.tabContentContainer.children[this.options.activeIndex].classList.remove('tab-item-active');
            this.tabContentContainer.children[index].classList.add('tab-item-active');
            this.options.activeIndex = index;

            try {
                if (this.options.onTabChange instanceof Function) {
                    this.options.onTabChange.call(null, this.tabContentContainer.children[index]);
                }
            } catch (err) {
                console.error(err);
            }
        }
    };

    exports.TabControl = TabControl;

});
