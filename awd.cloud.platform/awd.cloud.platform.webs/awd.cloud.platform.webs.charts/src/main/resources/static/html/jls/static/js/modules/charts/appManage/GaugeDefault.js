define(function (require, exports, module) {
  var Util = function () {
    this.init();
  };
  Util.prototype = {
    constructor: Util,
    init: function () {
      window.requestAnimationFrame = (function (callback) {
        return window.requestAnimationFrame ||
          window.webkitRequestAnimationFrame ||
          window.mozRequestAnimationFrame ||
          window.oRequestAnimationFrame ||
          window.msRequestAnimationFrame ||
          function (callback) {
            window.setTimeout(callback, 1000 / 60);
          };
      })();
      window.cancelRequestAnimFrame = (function () {
        return window.cancelAnimationFrame ||
          window.webkitCancelRequestAnimationFrame ||
          window.mozCancelRequestAnimationFrame ||
          window.oCancelRequestAnimationFrame ||
          window.msCancelRequestAnimationFrame ||
          clearTimeout
      })();
      Object.deepMerge = function (destination, source) {
        for (var property in source) {
          if (source[property] && source[property].constructor &&
            source[property].constructor === Object) {
            destination[property] = destination[property] || {};
            arguments.callee(destination[property], source[property]);
          } else {
            destination[property] = source[property];
          }
        }
        return destination;
      };
    },
    // 事件绑定
    Event: function (el, evn, fn) {
      var tmt;
      if (el) {
        if (el.addEventListener) {
          el.addEventListener(evn, function (e) {
            e = e || event;
            clearTimeout(tmt);
            tmt = setTimeout(function () {
              fn && fn(e)
            }, 100);
          }, false);
        }
      }
    },
    // 节点销毁
    destroyEl: function (el) {
      if (el.parentNode) el.parentNode.removeChild(el);
    },
    // 对象浅合并 obj 合并对象  obj_merged 合并内容
    merge: function (obj, obj_merged) {
      return Object.deepMerge(obj, obj_merged)
    },
    OptsToNumber: function (opt) {
      var ObjectNTN = function (obj) {
        for (var key in obj) {
          if (obj[key] && obj[key].constructor && obj[key].constructor === Object) {
            obj[key] = ObjectNTN(obj[key]);
          } else {
            if (typeof obj[key] === "string") {
              if (!isNaN(Number(obj[key]))) {
                console.info(obj[key])
                obj[key] = Number(obj[key]);
              }
            }
          }
        }
        return obj;
      };
      return ObjectNTN(opt);
    },
    // 图片资源加载 url 资源地址 cb 回掉函数 参数  state 加载状态  img  实例
    loadImg: function (url, cb) {
      var el_img = document.createElement("img");
      el_img.setAttribute("src", url);
      el_img.addEventListener("error", function (ev) {
        console.warn("图片加载失败!");
        console.warn("图片资源路径: " + url);
        cb && cb(false, el_img);
      });
      el_img.onload = function (ev) {
        cb && cb(true, el_img);
      };
    },
    // 自适应参数
    autoSize: function (px) {
      px = Number(px);
      if (isNaN(px)) {
        console.warn(px + " 不是一个数字");
        return 0;
      }
      return Number((document.documentElement.offsetWidth / 1920 * px).toFixed(5));
    },
    // 清空画布
    clearCvs: function (ctx) {
      ctx.clearRect(0, 0, ctx.canvas.width, ctx.canvas.height);
    },
    // 角度转换为弧度
    toRad: function (deg) {
      return Number(deg) * (Math.PI / 180);
    },
    // 弧度转换为角度
    toDeg: function (rad) {
      return Number(rad) * (180 / Math.PI);
    }
  };
  var Util_ = new Util();
  var Gauge = function (opt) {
    if (!(this instanceof Gauge)) return new Gauge(opt);

    this.opt = {
      el: null,
      animateTime: 1.5,
      insideCircle: {
        color: "#fff"
      },
      outDownCircle: {
        color: "94,199,189"
      },
      outUpCircle: {
        colorDown: "#fff",
        colorUp: "#fff"
      },
      markLine: {
        color: "#fff",
        textStyle: {
          color: "#fff"
        }
      },
      bar: {
        colorUp: "#fff",
        colorDown: "#fff",
        markLine: {
          color: "transparent",
          shadowColor: "transparent",
          insideColor: 'transparent'
        }
      }
    };
    this.ctx = null;
    this.amt = null;
    this.cached = 0;

    this.init(opt);
  };

  Gauge.prototype = {
    init: function (opt) {
      this.opt = Util_.merge(this.opt, opt);

      if (!this.opt.el) throw new Error("el不存在");

      this.opt = Util_.OptsToNumber(this.opt);
      this.buildCvs();
    },
    buildCvs: function () {
      // this.opt.el.style.overflow = "hidden";
      var cvs = document.createElement("canvas");
      cvs.width = this.opt.el.offsetWidth;
      cvs.height = this.opt.el.offsetHeight;
      this.opt.el.appendChild(cvs);
      this.ctx = cvs.getContext("2d");
    },
    drawInsideCircle: function () {
      var ctx = (this.ctx.canvas.cloneNode(true)).getContext("2d"),
        el = this.opt.el,
        cvs_w = el.offsetWidth;
      ctx.save();
      ctx.beginPath();
      ctx.arc(cvs_w / 2, cvs_w / 2, cvs_w * .55 / 2, Util_.toRad(135), Util_.toRad(45), false);
      ctx.strokeStyle = this.opt.insideCircle.color;
      ctx.fill();
      ctx.restore();
      return ctx;
    },
    drawBgCircle: function () {
      var ctx = (this.ctx.canvas.cloneNode(true)).getContext("2d"),
        el = this.opt.el,
        cvs_w = el.offsetWidth
      ;
      // 下层内圈
      ctx.save();
      ctx.beginPath();
      ctx.arc(cvs_w / 2, cvs_w / 2, cvs_w * .84 / 2, Util_.toRad(135), Util_.toRad(45), false);
      var rg = ctx.createRadialGradient(
        cvs_w / 2,
        cvs_w / 2,
        (cvs_w / 2) * .1,
        cvs_w / 2,
        cvs_w / 2,
        (cvs_w / 2) * .84
      );
      rg.addColorStop(0, 'rgba(0,0,0,0)');
      rg.addColorStop(.6, 'rgba(0,0,0,0)');
      rg.addColorStop(.68, 'rgba(' + this.opt.outDownCircle.color + ',.55)');
      rg.addColorStop(.78, 'rgba(' + this.opt.outDownCircle.color + ',.1)');
      rg.addColorStop(.80, 'rgba(' + this.opt.outDownCircle.color + ',.1)');
      rg.addColorStop(.85, 'rgba(0,0,0,0)');
      rg.addColorStop(.88, 'rgba(' + this.opt.outDownCircle.color + ',.1)');
      rg.addColorStop(.9, 'rgba(' + this.opt.outDownCircle.color + ',.1)');
      rg.addColorStop(1, 'rgba(' + this.opt.outDownCircle.color + ',.55)');
      ctx.fillStyle = rg;
      ctx.fill();
      ctx.restore();

      // 下层圈截断圈圈
      ctx.save();
      ctx.beginPath();
      var gc = ctx.globalCompositeOperation;
      ctx.globalCompositeOperation = "destination-out";
      ctx.arc(cvs_w / 2, cvs_w / 2, cvs_w * .68 / 2, Util_.toRad(135), Util_.toRad(45), true);
      ctx.strokeStyle = "rgba(0,0,0,1)";
      ctx.lineWidth = Util_.autoSize(28);
      ctx.stroke();
      ctx.globalCompositeOperation = gc;
      ctx.restore();

      // 上层圈
      ctx.save();
      ctx.beginPath();
      ctx.arc(cvs_w / 2, cvs_w / 2, cvs_w * .65 / 2, Util_.toRad(135), Util_.toRad(45), false);
      /*
      ctx.strokeStyle = this.createLinearGradient(.5, 1, .5, 0, [
        {
          p: 0,
          color: this.opt.outUpCircle.colorDown
        },
        {
          p: 1,
          color: this.opt.outUpCircle.colorUp
        }
      ], .5);*/
      ctx.globalAlpha = .2;
      ctx.lineWidth = Util_.autoSize(12);
      ctx.stroke();
      ctx.globalAlpha = 1;
      ctx.restore();
      return ctx;
    },
    drawMark: function () {
      var ctx = (this.ctx.canvas.cloneNode(true)).getContext("2d"),
        el = this.opt.el,
        cvs_w = el.offsetWidth
      ;
      // 开始位置标记
      ctx.save();
      ctx.beginPath();
      ctx.translate(cvs_w / 2, cvs_w / 2);
      ctx.rotate(Util_.toRad(-45));
      ctx.moveTo(-(cvs_w / 2) * .54, 0);
      ctx.lineTo(-(cvs_w / 2) * .54 - Util_.autoSize(25), 0);
      ctx.strokeStyle = this.opt.markLine.color;
      ctx.lineWidth = Util_.autoSize(2);
      ctx.stroke();
      // 开始位置标记内容
      ctx.fillStyle = this.opt.markLine.textStyle.color;
      ctx.font = Util_.autoSize(10.2) + "px Microsoft YaHei UI";
      ctx.textAlign = "right";
      ctx.fillText("0", -(cvs_w / 2) * .54 - Util_.autoSize(12.5), -Util_.autoSize(6));
      ctx.restore();

      // 结束位置标记
      ctx.save();
      ctx.beginPath();
      ctx.translate(cvs_w / 2, cvs_w / 2);
      ctx.rotate(Util_.toRad(-135));
      ctx.moveTo(-(cvs_w / 2) * .54, 0);
      ctx.lineTo(-(cvs_w / 2) * .54 - Util_.autoSize(25), 0);
      ctx.strokeStyle = this.opt.markLine.color;
      ctx.lineWidth = Util_.autoSize(2);
      ctx.stroke();
      ctx.restore();
      // 结束位置标记内容
      ctx.save();
      ctx.translate(cvs_w / 2, cvs_w / 2);
      ctx.rotate(Util_.toRad(45));
      ctx.moveTo(-(cvs_w / 2) * .54, 0);
      ctx.lineTo(-(cvs_w / 2) * .54 - Util_.autoSize(25), 0);
      ctx.fillStyle = this.opt.markLine.textStyle.color;
      ctx.font = Util_.autoSize(10.2) + "px Microsoft YaHei UI";
      ctx.textAlign = "left";
      ctx.textBaseline = "middle";
      ctx.fillText("100", (cvs_w / 2) * .54 + Util_.autoSize(12.5), -Util_.autoSize(8));
      ctx.restore();
      return ctx;
    },
    drawBar: function (pct,txt) {
      var ctx = (this.ctx.canvas.cloneNode(true)).getContext("2d"),
        el = this.opt.el,
        cvs_w = el.offsetWidth
      ;
      var oneStep = (360 - 180 + 0) / 100;
      var targetP = 180 + pct * oneStep;
      (targetP > 360) && (targetP -= 360);
      ctx.save();
      ctx.beginPath();
      ctx.arc(cvs_w / 2, cvs_w / 2, cvs_w * .6  / 1.96, Util_.toRad(180), Util_.toRad(targetP), false);
        ctx.strokeStyle = this.opt.markLine.textStyle.color;
        /*
        ctx.strokeStyle = this.createLinearGradient(1, .5, 0, .5, [
          {
            p: 0,
            color: this.opt.bar.colorUp
          },
          {
            p: 1,
            color: this.opt.bar.colorDown
          }
        ]);*/
        ctx.lineCap = "round";
      ctx.lineWidth = Util_.autoSize(4);
      ctx.stroke();
      ctx.restore();
      ctx.save();
        ctx.font="normal normal 12 Microsoft YaHei UI,sans-serif";
        ctx.fontWeight = 300;
        ctx.strokeStyle='#fff';
        ctx.textAlign="center";
        ctx.strokeText(/*pct.toFixed(1)+"%"*/'',cvs_w/ 2, cvs_w*0.22 /2);
        ctx.restore();
        ctx.save();
        ctx.font="normal normal 12 Microsoft YaHei UI,sans-serif";

        ctx.textAlign="center";
        ctx.strokeStyle='#fff';
        ctx.strokeText(txt,cvs_w / 2, cvs_w*1.5 /2.8);
        ctx.restore();
        ctx.save();
      ctx.beginPath();
      ctx.translate(cvs_w / 2, cvs_w / 2);
      ctx.rotate(Util_.toRad(targetP));
      ctx.moveTo(cvs_w/1.34*.41, 0);
      ctx.lineTo(cvs_w/1.34*.41 , 0);
      ctx.strokeStyle = this.opt.bar.markLine.color;
      ctx.shadowColor = this.opt.bar.markLine.shadowColor;
      ctx.lineWidth = Util_.autoSize(12);
      ctx.lineCap = "round";
      ctx.shadowBlur = Util_.autoSize(10);

      ctx.stroke();
      ctx.moveTo(cvs_w/1.34*.41, 0);
      ctx.lineTo(cvs_w/1.34*.41 , 0);
      ctx.strokeStyle = this.opt.bar.markLine.insideColor;
      ctx.shadowColor = this.opt.bar.markLine.shadowColor;
      ctx.lineWidth = Util_.autoSize(8);
      ctx.lineCap = "round";
      ctx.shadowBlur = Util_.autoSize(10);

      ctx.stroke();
      ctx.restore();
      return ctx;
    },
    /**
     * 线性渐变
     * @param x0
     * @param y0
     * @param x1
     * @param y1
     * @param clrOpt
     *      [
     *        {
     *            p: 0,
     *            color: '#f00'
     *        },
     *        {
     *            p: 1,
     *            color: '#ccc'
     *        }
     *      ]
     */
    createLinearGradient: function (x0, y0, x1, y1, clrOpt) {
      if (!this.ctx) this.buildCvs();
      (x0 > 1 || x0 < 0) && (x0 = 1);
      (y0 > 1 || y0 < 0) && (y0 = 1);
      (x1 > 1 || x1 < 0) && (x1 = 1);
      (y1 > 1 || y1 < 0) && (y1 = 1);
      var ctx = this.ctx,
        cvs_h = ctx.canvas.height,
        cvs_w = ctx.canvas.width,
        lg = ctx.createLinearGradient(x0 * cvs_w, y0 * cvs_h, x1 * cvs_w, y1 * cvs_h);
      if (!(clrOpt instanceof Array)) {
        console.warn("渐变参数错误： clrOpt应该是一个数组");
        return "#000";
      }
      for (var i = 0; i < clrOpt.length; i++) {
        var clr = clrOpt[i];
        lg.addColorStop(clr.p, clr.color);
      }
      return lg;
    },
    render: function (pct,txt) {
      var ctx = this.ctx, _self = this, mark = 0, step = pct / (this.opt.animateTime * 1000 / 60), f;
      this.cached = pct;
      this.cachedtxt = txt;
      pct = Number(pct);
      (pct > 100) && (pct = 100);

      f = function () {
        ctx.globalCompositeOperation = "destination-out";
        ctx.fillRect(0, 0, ctx.canvas.width, ctx.canvas.height);
        ctx.globalCompositeOperation = "source-over";
        // ctx.drawImage(_self.drawInsideCircle().canvas, 0, 0);
        // ctx.drawImage(_self.drawBgCircle().canvas, 0, 0);
        ctx.drawImage(_self.drawBar(mark,txt).canvas, 0, 0);
        // ctx.drawImage(_self.drawMark().canvas, 0, 0);
        ctx.moveTo(ctx.canvas.width / 2, ctx.canvas.width / 1);
        ctx.font = "bold " + Util_.autoSize(32) + "px Impact";
        ctx.fillStyle = "#00ffff";
        ctx.textAlign = "center";
        ctx.textBaseline = "middle";
        // ctx.fillText(pct.toFixed(0), ctx.canvas.width / 2, ctx.canvas.width / 2);
        ctx.font = "normal " + Util_.autoSize(12) + "px 宋体";
        ctx.textBaseline = "top";

        var le = 12;
        if((pct + "").length === 1)
          le = 18;
        if((pct + "").length === 3){
          le = 10;
        }
        ctx.globalCompositeOperation='source-over';
        // ctx.fillText("%", ctx.canvas.width / 2 + Util_.autoSize(le) * (pct + "").length, ctx.canvas.width / 2);
        mark += step;
        _self.amt = requestAnimationFrame(f);
        if (step > 0) {
          if (mark > pct) cancelAnimationFrame(_self.amt);
        } else {
          if (mark < pct) cancelAnimationFrame(_self.amt);
        }
      };

      cancelAnimationFrame(_self.amt);
      Util_.clearCvs(_self.ctx);
      f();
    },
    resize: function () {
      this.ctx.canvas.width = this.opt.el.offsetWidth;
      this.ctx.canvas.height = this.opt.el.offsetHeight;
      this.render(this.cached,this.cachedtxt);
    }
  };

  module.exports = Gauge;
});
