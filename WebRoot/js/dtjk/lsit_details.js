            function TabSwitch(tabTit, tabCon) {
                // 获取标签
                this.titArr = document.getElementById(tabTit).getElementsByTagName('li');
                this.conArr = document.getElementById(tabCon).getElementsByTagName('div');
                this.len = this.titArr.length;
                // 调用绑定事件方法
                this.addEvent();
            }
            // 绑定事件
            TabSwitch.prototype.addEvent = function() {
                // 防止指向错误
                var _this = this;

                for (var i = 0; i < this.len; i++) {
                    // 自定义属性
                    this.titArr[i].index = i;
                    this.titArr[i].onclick = function() {
                        _this.handleClass(this.index);
                    }
                };
            }
            // 控制标签样式
            TabSwitch.prototype.handleClass = function(index) {
                // 情况类名
                for (var i = 0; i < this.len; i++) {
                    this.titArr[i].className = "list-item";
                    this.conArr[i].className = "";
                };
                // 添加类名
                this.titArr[index].className = "active";
                this.conArr[index].className = "block";
            }
new TabSwitch("tabTit", "tabCon");