/**
 * Created by ShengHui on 2015/10/14.
 *
 * var cityPicker = new IIInsomniaCityPicker({
 *      data: data,             城市数据
 *      target: 'cityChoice',   目标元素id（如：文本框，该值为城市名称）
 *      valType: 'k-v',         隐藏域的值类型（取值有：'k' => 只存ID，'k-v' => 存ID和名称，格式为：id-name）
 *      hideCityInput: {        城市隐藏域
 *          name: 'city',       隐藏域name
 *          id: 'city'          隐藏域id
 *          val: '2'            默认值
 *      },
 *      hideProvinceInput: {    省份隐藏域（如果不需要存省份，此项可以省略）
 *          name: 'province',   隐藏域name
 *          id: 'province'      隐藏域id
 *          val: '1'            默认值
 *      },
 *      callback: function(){   回调函数，选择城市后调用
 *          alert('OK');
 *      }
 * });
 *
 * cityPicker.init();
 */
;var IIInsomniaCityPicker = function(options){
    this.template = $('<div class="IIInsomnia-city-picker" id="IIInsomnia_city_picker"><div class="IIInsomnia-hot-wrap"><p>热门城市</p><ul id="IIInsomnia_hot_city"></ul></div><div class="line"></div><div class="IIInsomnia-wrap"><p>选择省份</p><ul class="IIInsomnia-province-wrap" id="IIInsomnia_province_wrap"></ul></div></div>');
    this.hot_city = $('#IIInsomnia_hot_city', this.template);
    this.province_wrap = $('#IIInsomnia_province_wrap', this.template);
    this.settings = {
        data: options.data,
        target: $('#' + options.target),
        valType: options.valType || 'k',
        multiple: options.multiple || false
    };
    this.hideProvinceInput = options.hideProvinceInput ? $('<input>', {
        type: 'hidden',
        name: options.hideProvinceInput.name,
        id: options.hideProvinceInput.id,
        val: options.hideProvinceInput.val || ''
    }) : false;
    this.hideCityInput = $('<input>', {
        type: 'hidden',
        name: options.hideCityInput.name,
        id: options.hideCityInput.id,
        val: options.hideCityInput.val || ''
    });
    this.callback = options.callback || '';
};

IIInsomniaCityPicker.prototype = {
    init: function(){
        var that = this;
        $(window).click(function(event) {
            /* Act on the event */
            that.template.remove();
        });

        that.settings.target.attr('readonly', true);
        that.settings.target.after(that.hideCityInput);
        if(that.hideProvinceInput) that.settings.target.after(that.hideProvinceInput);
        that.addTargetEvent();
    },

    buildCityPicker: function(){
        var that = this;
        that.addHotCityTpl();
        that.addProvinceTpl();
        that.addProvinceEvent();
        that.addCityEvent();
    },

    addHotCityTpl: function(){
        var that = this;
        var hot_city = that.settings.data.hot;
        var hot_city_html = '';
        for(var i = 0, len = hot_city.length; i < len; i++){
            hot_city_html += '<li class="IIInsomnia-hot-city" data-id="' + hot_city[i]['id'] + '" data-name="' + hot_city[i]['name'] + '" data-pid="' + hot_city[i]['pid'] + '" data-pname="' + hot_city[i]['pname'] + '">' + hot_city[i]['name'] + '</li>';
        }

        that.hot_city.html(hot_city_html);
    },

    addProvinceTpl: function(){
        var that = this;
        var province = that.settings.data.province;
        var province_html = '';
        for(var i = 0, len = province.length; i < len; i++){
            province_html += '<li class="IIInsomnia-province" data-id="' + province[i]['id'] + '" data-name="' + province[i]['name'] + '"><ul class="IIInsomnia-city-wrap"></ul><div class="IIInsomnia-province-name">' + province[i]['name'] + '</div></li>';
        }

        that.province_wrap.html(province_html);
    },

    addCityTpl: function(cur_province){
        var that = this;
        var pid = cur_province.data('id');
        var poi = cur_province.position();
        var province = that.settings.data.province;
        var city;
        var city_html = '';

        for(var i = 0, plen = province.length; i < plen; i++){
            if(province[i]['id'] == parseInt(pid)){
                city = province[i]['city'];
                break;
            }
        }
        for(var j = 0, clen = city.length; j < clen; j++){
            city_html += '<li class="IIInsomnia-city" data-id="' + city[j]['id'] + '" data-name="' + city[j]['name'] + '" title="' + city[j]['name'] + '">' + city[j]['name'] + '</li>';
        }

        cur_province.find('.IIInsomnia-city-wrap').html(city_html).css('left', '-' + (poi.left - 53) + 'px').show();
    },

    addProvinceEvent: function(){
        var that = this;
        that.province_wrap.on('click', '.IIInsomnia-province', function(event){
            event.preventDefault();
            event.stopPropagation();
            /* Act on the event */
            var _this = $(this);

            if(!_this.hasClass('active')){
                that.province_wrap.find('.IIInsomnia-province').removeClass('active');
                that.province_wrap.find('.IIInsomnia-province-name').removeClass('active');
                that.province_wrap.find('.IIInsomnia-city-wrap').hide().children().remove();

                _this.addClass('active');
                _this.find('.IIInsomnia-province-name').addClass('active');

                that.addCityTpl(_this);
            }else{
                _this.removeClass('active');
                _this.find('.IIInsomnia-province-name').removeClass('active');

                _this.find('.IIInsomnia-city-wrap').hide().children().remove();
            }

            return false;
        });
    },

    addCityEvent: function(){
        var that = this;
        that.hot_city.on('click', '.IIInsomnia-hot-city', function(event) {
            event.preventDefault();
            event.stopPropagation();
            /* Act on the event */
            var _this = $(this);

            var cid = _this.data('id');
            var cname = _this.data('name');
            that.settings.target.val(cname);
            if(that.settings.valType == 'k-v'){
                that.hideCityInput.val(cid + '-' + cname);
            }else{
                that.hideCityInput.val(cid);
            }

            if(that.hideProvinceInput){
                var pid = _this.data('pid');
                var pname = _this.data('pname');
                if(that.settings.valType == 'k-v'){
                    that.hideProvinceInput.val(pid + '-' + pname);
                }else{
                    that.hideProvinceInput.val(pid);
                }
            }

            that.template.remove();

            if(that.callback) that.callback();

            return false;
        });

        that.province_wrap.on('click', '.IIInsomnia-city', function(event) {
            event.preventDefault();
            event.stopPropagation();
            /* Act on the event */
            var _this = $(this);

            var cid = _this.data('id');
            var cname = _this.data('name');
            that.settings.target.val(cname);
            if(that.settings.valType == 'k-v'){
                that.hideCityInput.val(cid + '-' + cname);
            }else{
                that.hideCityInput.val(cid);
            }

            if(that.hideProvinceInput){
                var pele = _this.parent().parent();
                var pid = pele.data('id');
                var pname = pele.data('name');
                if(that.settings.valType == 'k-v'){
                    that.hideProvinceInput.val(pid + '-' + pname);
                }else{
                    that.hideProvinceInput.val(pid);
                }
            }

            that.template.remove();

            if(that.callback) that.callback();

            return false;
        });
    },

    addTargetEvent: function(){
        var that = this;
        that.settings.target.click(function(event){
            event.stopPropagation();
            /* Act on the event */
            var _this = $(this);

            that.buildCityPicker();

            var offset = _this.offset();
            var top = offset.top + _this.outerHeight() + 15;

            that.template.css({
                'left': offset.left,
                'top': top
            });

            $('body').append(that.template);

            return false;
        });
    }
};