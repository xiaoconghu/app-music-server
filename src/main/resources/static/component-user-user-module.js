(window["webpackJsonp"] = window["webpackJsonp"] || []).push([["component-user-user-module"],{

/***/ "./src/app/component/play/play-progress/draggable.ts":
/*!***********************************************************!*\
  !*** ./src/app/component/play/play-progress/draggable.ts ***!
  \***********************************************************/
/*! exports provided: default */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/**
 * Created by xiaoconghu on 2018/5/11.
 */
var isDragging = false;
var supportTouch = false;
/* harmony default export */ __webpack_exports__["default"] = (function (element, options) {
    var moveFn = function (event) {
        if (options.drag) {
            options.drag(supportTouch ? event.changedTouches[0] || event.touches[0] : event);
        }
    };
    var endFn = function (event) {
        if (!supportTouch) {
            document.removeEventListener('mousemove', moveFn);
            document.removeEventListener('mouseup', endFn);
        }
        document.onselectstart = null;
        document.ondragstart = null;
        isDragging = false;
        if (options.end) {
            options.end(supportTouch ? event.changedTouches[0] || event.touches[0] : event);
        }
    };
    element.addEventListener(supportTouch ? 'touchstart' : 'mousedown', function (event) {
        if (isDragging) {
            return;
        }
        event.preventDefault();
        document.onselectstart = function () {
            return false;
        };
        document.ondragstart = function () {
            return false;
        };
        if (!supportTouch) {
            document.addEventListener('mousemove', moveFn);
            document.addEventListener('mouseup', endFn);
        }
        isDragging = true;
        if (options.start) {
            options.start(supportTouch ? event.changedTouches[0] || event.touches[0] : event);
        }
    });
    if (supportTouch) {
        element.addEventListener('touchmove', moveFn);
        element.addEventListener('touchend', endFn);
        element.addEventListener('touchcancel', endFn);
    }
});


/***/ }),

/***/ "./src/app/component/play/play-progress/play-progress.component.html":
/*!***************************************************************************!*\
  !*** ./src/app/component/play/play-progress/play-progress.component.html ***!
  \***************************************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = "\n  <div class=\"range\">\n    <div class=\"range-content\" id=\"content\">\n      <div class=\"range-runway\" id=\"runWay\" style=\"border-top-width: 8px;\">\n      </div>\n      <div class=\"range-progress\" id=\"progress\" [ngStyle]=\"{width:value+'%',height:'8px'}\" >\n      </div>\n      <div class=\"range-thumb\" id=\"thumb\" [ngStyle]=\"{left:value+'%'}\"></div>\n    </div>\n  </div>\n\n"

/***/ }),

/***/ "./src/app/component/play/play-progress/play-progress.component.less":
/*!***************************************************************************!*\
  !*** ./src/app/component/play/play-progress/play-progress.component.less ***!
  \***************************************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = ".range {\n  position: relative;\n  display: flex;\n  height: 30px;\n  line-height: 30px;\n}\n.range-content {\n  margin: 21px 15px;\n  position: relative;\n  flex: 1;\n  /*margin-right: 30px;*/\n}\n.range-runway {\n  position: absolute;\n  top: 50%;\n  -webkit-transform: translateY(-50%);\n  transform: translateY(-50%);\n  left: 0;\n  right: 0;\n  border-top-color: #a9acb1;\n  border-top-style: solid;\n}\n.range-progress {\n  position: absolute;\n  display: block;\n  background-color: #26a2ff;\n  top: 50%;\n  -webkit-transform: translateY(-50%);\n  transform: translateY(-50%);\n  width: 0;\n}\n.range-thumb {\n  background-color: #ffcd32;\n  background-clip: content-box;\n  position: absolute;\n  padding: 3px;\n  left: 0;\n  top: 50%;\n  -webkit-transform: translateY(-50%);\n  transform: translateY(-50%);\n  width: 16px;\n  height: 16px;\n  border: 1px solid #ccc;\n  border-radius: 100%;\n  cursor: move;\n  box-shadow: 0 1px 3px rgba(0, 0, 0, 0.4);\n}\n"

/***/ }),

/***/ "./src/app/component/play/play-progress/play-progress.component.ts":
/*!*************************************************************************!*\
  !*** ./src/app/component/play/play-progress/play-progress.component.ts ***!
  \*************************************************************************/
/*! exports provided: PlayProgressComponent */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "PlayProgressComponent", function() { return PlayProgressComponent; });
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _draggable__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! ./draggable */ "./src/app/component/play/play-progress/draggable.ts");
var __decorate = (undefined && undefined.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (undefined && undefined.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};


var PlayProgressComponent = /** @class */ (function () {
    function PlayProgressComponent() {
        this.value = 0;
        this.min = 0;
        this.max = 100;
        this.step = 1;
        this.disabled = false;
        this.barHeight = 1;
        this.dragEvent = new _angular_core__WEBPACK_IMPORTED_MODULE_0__["EventEmitter"]();
        this.dragEndEvent = new _angular_core__WEBPACK_IMPORTED_MODULE_0__["EventEmitter"]();
    }
    PlayProgressComponent.prototype.ngOnInit = function () {
        var _this = this;
        var thumb = document.getElementById('thumb');
        var content = document.getElementById('content');
        var getThumbPosition = function () {
            var contentBox = content.getBoundingClientRect();
            var thumbBox = thumb.getBoundingClientRect();
            return {
                left: thumbBox.left - contentBox.left,
                top: thumbBox.top - contentBox.top,
                thumbBoxLeft: thumbBox.left
            };
        };
        var dragState = {};
        Object(_draggable__WEBPACK_IMPORTED_MODULE_1__["default"])(thumb, {
            start: function (event) {
                if (_this.disabled) {
                    return;
                }
                var position = getThumbPosition();
                var thumbClickDetalX = event.clientX - position.thumbBoxLeft;
                dragState = {
                    thumbStartLeft: position.left,
                    thumbStartTop: position.top,
                    thumbClickDetalX: thumbClickDetalX
                };
            },
            drag: function (event) {
                if (_this.disabled) {
                    return;
                }
                var contentBox = content.getBoundingClientRect();
                var deltaX = event.pageX - contentBox.left - dragState.thumbStartLeft - dragState.thumbClickDetalX;
                var stepCount = Math.ceil((_this.max - _this.min) / _this.step);
                var __newPosition = (dragState.thumbStartLeft + deltaX) - (dragState.thumbStartLeft + deltaX) % (contentBox.width / stepCount);
                var newProgress = __newPosition / contentBox.width;
                if (newProgress < 0) {
                    newProgress = 0;
                }
                else if (newProgress > 1) {
                    newProgress = 1;
                }
                _this.currentValue = Math.round(_this.min + newProgress * (_this.max - _this.min));
                _this.dragEvent.emit({ currentTime: Math.round(_this.min + newProgress * (_this.max - _this.min)) });
            },
            end: function () {
                if (_this.disabled) {
                    return;
                }
                _this.dragEndEvent.emit({ currentTime: _this.currentValue });
                dragState = {};
            }
        });
        var that = this;
        var newPosition = function (event) {
            var contentBox = content.getBoundingClientRect();
            var deltaX = event.pageX - contentBox.left - thumb.offsetWidth;
            var stepCount = Math.ceil((that.max - that.min) / that.step);
            var _newPosition = deltaX - (deltaX) % (contentBox.width / stepCount);
            var newProgress = _newPosition / contentBox.width;
            that.dragEndEvent.emit({ currentTime: Math.round(that.min + newProgress * (that.max - that.min)) });
        };
        var runWay = document.getElementById('runWay');
        runWay.addEventListener('click', newPosition);
        var progress = document.getElementById('progress');
        progress.addEventListener('click', newPosition);
    };
    __decorate([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_0__["Input"])(),
        __metadata("design:type", Object)
    ], PlayProgressComponent.prototype, "value", void 0);
    __decorate([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_0__["Input"])(),
        __metadata("design:type", Object)
    ], PlayProgressComponent.prototype, "min", void 0);
    __decorate([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_0__["Input"])(),
        __metadata("design:type", Object)
    ], PlayProgressComponent.prototype, "max", void 0);
    __decorate([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_0__["Input"])(),
        __metadata("design:type", Object)
    ], PlayProgressComponent.prototype, "step", void 0);
    __decorate([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_0__["Input"])(),
        __metadata("design:type", Object)
    ], PlayProgressComponent.prototype, "disabled", void 0);
    __decorate([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_0__["Input"])(),
        __metadata("design:type", Object)
    ], PlayProgressComponent.prototype, "barHeight", void 0);
    __decorate([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_0__["Output"])(),
        __metadata("design:type", Object)
    ], PlayProgressComponent.prototype, "dragEvent", void 0);
    __decorate([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_0__["Output"])(),
        __metadata("design:type", Object)
    ], PlayProgressComponent.prototype, "dragEndEvent", void 0);
    PlayProgressComponent = __decorate([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_0__["Component"])({
            selector: 'app-play-progress',
            template: __webpack_require__(/*! ./play-progress.component.html */ "./src/app/component/play/play-progress/play-progress.component.html"),
            styles: [__webpack_require__(/*! ./play-progress.component.less */ "./src/app/component/play/play-progress/play-progress.component.less")]
        }),
        __metadata("design:paramtypes", [])
    ], PlayProgressComponent);
    return PlayProgressComponent;
}());



/***/ }),

/***/ "./src/app/component/play/play.component.html":
/*!****************************************************!*\
  !*** ./src/app/component/play/play.component.html ***!
  \****************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = "<div style=\"position: fixed;bottom: 0;\n    left: 0;width: 100%\" [hidden]=\"!playBoxShow\">\n  <div style=\"height: 55px;\n      background-color: #32ab7a;display: flex\">\n    <div style=\"    padding: 0 20px;\">\n      <i class=\"controls\" nz-icon type=\"retweet\" theme=\"outline\"></i>\n      <i class=\"controls\" nz-icon type=\"left-circle\" theme=\"outline\"></i>\n      <i class=\"controls\" nz-icon type=\"play-circle\" [hidden]=\"isPlay\" (click)=\"clickPlay()\" theme=\"outline\"></i>\n      <i class=\"controls\" nz-icon type=\"pause-circle\" [hidden]=\"!isPlay\" (click)=\"clickPlay()\" theme=\"outline\"></i>\n      <i class=\"controls\" nz-icon type=\"right-circle\" theme=\"outline\"></i>\n      <i class=\"controls\" nz-icon type=\"download\" theme=\"outline\"></i>\n    </div>\n    <div style=\"flex: 1;\n    padding: 0 32px;\n    line-height: 44px;display: flex\">\n      <span>{{currentTime*1000|timePipe}}</span>\n      <!--<nz-progress style=\"flex: 1;margin: 0 20px\" [nzPercent]=\"(currentTime/playTime)*100\"-->\n      <!--[nzShowInfo]=\"false\"></nz-progress>-->\n      <app-play-progress style=\"flex: 1;margin: 0 20px\"\n                         [value]=\"(currentTime/playTime)*100\"\n                         [max]=\"playTime\"\n                         [min]=\"0\"\n                         [step]=\"1\"\n                         [barHeight]=\"1\"\n                         [disabled]=\"false\"\n                         (dragEvent)=\"rangeChange($event)\"\n                         (dragEndEvent)=\"rangeChangeEnd($event)\"\n      ></app-play-progress>\n      <span>{{playTime*1000|timePipe}}</span>\n    </div>\n    <audio style=\"text-align: center;margin: 0 auto\"\n           id=\"play\"\n           (ended)=\"ended()\"\n           (play)='songReady($event)'\n           [src]=\"playUrl\"\n           (canplay)=\"canPlay($event)\"\n           (timeupdate)=\"timeUpdate($event)\"\n    ></audio>\n  </div>\n</div>\n"

/***/ }),

/***/ "./src/app/component/play/play.component.less":
/*!****************************************************!*\
  !*** ./src/app/component/play/play.component.less ***!
  \****************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = ".controls {\n  font-size: 45px;\n  line-height: 63px;\n  margin-right: 10px;\n}\n"

/***/ }),

/***/ "./src/app/component/play/play.component.ts":
/*!**************************************************!*\
  !*** ./src/app/component/play/play.component.ts ***!
  \**************************************************/
/*! exports provided: PlayComponent */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "PlayComponent", function() { return PlayComponent; });
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _core_mission_music_mission_service__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! ../../core/mission/music-mission.service */ "./src/app/core/mission/music-mission.service.ts");
/* harmony import */ var _core_entity_music__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! ../../core/entity/music */ "./src/app/core/entity/music.ts");
/* harmony import */ var ng_zorro_antd__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! ng-zorro-antd */ "./node_modules/ng-zorro-antd/fesm5/ng-zorro-antd.js");
var __decorate = (undefined && undefined.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (undefined && undefined.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};




var PlayComponent = /** @class */ (function () {
    function PlayComponent(mission, message) {
        this.mission = mission;
        this.message = message;
        this.song = new _core_entity_music__WEBPACK_IMPORTED_MODULE_2__["Music"]();
    }
    PlayComponent.prototype.ngOnInit = function () {
        var _this = this;
        this.audio = document.getElementById('play');
        this.mission.musicChange.subscribe(function (currentMusic) {
            _this.song = currentMusic;
            _this.playUrl = "/music/song/media/" + currentMusic.id;
            _this.isPlay = true;
            _this.playBoxShow = true;
            setTimeout(function (e) {
                _this.audio.play();
            });
        });
    };
    PlayComponent.prototype.ngAfterViewInit = function () {
    };
    PlayComponent.prototype.songReady = function (e) {
        this.message.success('开始播放歌曲');
    };
    PlayComponent.prototype.ended = function () {
    };
    PlayComponent.prototype.timeUpdate = function (e) {
        this.currentTime = e.target.currentTime;
    };
    PlayComponent.prototype.canPlay = function (e) {
        this.playTime = e.target.duration;
    };
    PlayComponent.prototype.clickPlay = function () {
        this.isPlay = !this.isPlay;
        this.audio[this.isPlay ? 'play' : 'pause']();
    };
    PlayComponent.prototype.rangeChange = function (evt) {
        this.currentTime = evt.currentTime;
        if (this.isPlay) {
            this.audio.pause();
            this.isPlay = false;
        }
        this.audio.currentTime = evt.currentTime;
    };
    PlayComponent.prototype.rangeChangeEnd = function (evt) {
        var _this = this;
        console.log('拖拽结束', evt.currentTime);
        this.currentTime = evt.currentTime;
        this.audio.currentTime = evt.currentTime;
        setTimeout(function (e) {
            _this.audio.play();
        });
    };
    PlayComponent = __decorate([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_0__["Component"])({
            selector: 'app-play',
            template: __webpack_require__(/*! ./play.component.html */ "./src/app/component/play/play.component.html"),
            styles: [__webpack_require__(/*! ./play.component.less */ "./src/app/component/play/play.component.less")]
        }),
        __metadata("design:paramtypes", [_core_mission_music_mission_service__WEBPACK_IMPORTED_MODULE_1__["MusicMissionService"],
            ng_zorro_antd__WEBPACK_IMPORTED_MODULE_3__["NzMessageService"]])
    ], PlayComponent);
    return PlayComponent;
}());



/***/ }),

/***/ "./src/app/component/user/add-user/add-user.component.html":
/*!*****************************************************************!*\
  !*** ./src/app/component/user/add-user/add-user.component.html ***!
  \*****************************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = "<form nz-form [formGroup]=\"validateForm\" (ngSubmit)=\"submitForm()\">\n  <nz-form-item>\n    <nz-form-label [nzSm]=\"6\" [nzXs]=\"24\" nzRequired nzFor=\"email\">邮箱</nz-form-label>\n    <nz-form-control [nzSm]=\"14\" [nzXs]=\"24\">\n      <input nz-input formControlName=\"email\" id=\"email\">\n      <nz-form-explain *ngIf=\"validateForm.get('email').dirty && validateForm.get('email').errors\">邮箱不合法!</nz-form-explain>\n    </nz-form-control>\n  </nz-form-item>\n  <nz-form-item>\n    <nz-form-label [nzSm]=\"6\" [nzXs]=\"24\" nzRequired nzFor=\"userCode\">用户账号</nz-form-label>\n    <nz-form-control [nzSm]=\"14\" [nzXs]=\"24\">\n      <input nz-input formControlName=\"userCode\" id=\"userCode\">\n      <nz-form-explain *ngIf=\"validateForm.get('userCode').dirty && validateForm.get('userCode').errors\">请输入账号!</nz-form-explain>\n    </nz-form-control>\n  </nz-form-item>\n  <nz-form-item>\n    <nz-form-label [nzSm]=\"6\" [nzXs]=\"24\" nzFor=\"password\" nzRequired>密码</nz-form-label>\n    <nz-form-control [nzSm]=\"14\" [nzXs]=\"24\">\n      <input nz-input type=\"password\" id=\"password\" formControlName=\"password\" (ngModelChange)=\"updateConfirmValidator()\">\n      <nz-form-explain *ngIf=\"validateForm.get('password').dirty && validateForm.get('password').errors\">请输入密码!</nz-form-explain>\n    </nz-form-control>\n  </nz-form-item>\n  <nz-form-item>\n    <nz-form-label [nzSm]=\"6\" [nzXs]=\"24\" nzFor=\"checkPassword\" nzRequired>确认密码</nz-form-label>\n    <nz-form-control [nzSm]=\"14\" [nzXs]=\"24\">\n      <input nz-input type=\"password\" formControlName=\"checkPassword\" id=\"checkPassword\">\n      <nz-form-explain *ngIf=\"validateForm.get('checkPassword').dirty && validateForm.get('checkPassword').errors\">\n        <ng-container *ngIf=\"validateForm.get('checkPassword').hasError('required')\">\n          请确认你的密码!\n        </ng-container>\n        <ng-container *ngIf=\"validateForm.get('checkPassword').hasError('confirm')\">\n          两次输入密码不一致!\n        </ng-container>\n      </nz-form-explain>\n    </nz-form-control>\n  </nz-form-item>\n  <nz-form-item>\n    <nz-form-label [nzSm]=\"6\" [nzXs]=\"24\" nzFor=\"userName\" nzRequired>\n          <span>\n            昵称\n            <nz-tooltip nzTitle=\"What do you want other to call you\">\n              <i nz-tooltip class=\"anticon anticon-question-circle-o\"></i>\n            </nz-tooltip>\n          </span>\n    </nz-form-label>\n    <nz-form-control [nzSm]=\"14\" [nzXs]=\"24\">\n      <input nz-input id=\"userName\" formControlName=\"userName\">\n      <nz-form-explain *ngIf=\"validateForm.get('userName').dirty && validateForm.get('userName').errors\">请输入你的昵称!</nz-form-explain>\n    </nz-form-control>\n  </nz-form-item>\n  <nz-form-item>\n    <nz-form-label [nzSm]=\"6\" [nzXs]=\"24\" nzFor=\"phoneNumber\" nzRequired>手机号码</nz-form-label>\n    <nz-form-control [nzSm]=\"14\" [nzXs]=\"24\" [nzValidateStatus]=\"validateForm.controls['phoneNumber']\">\n      <nz-input-group [nzAddOnBefore]=\"addOnBeforeTemplate\">\n        <ng-template #addOnBeforeTemplate>\n          <nz-select formControlName=\"phoneNumberPrefix\" style=\"width: 70px;\">\n            <nz-option nzLabel=\"+86\" nzValue=\"+86\"></nz-option>\n            <nz-option nzLabel=\"+87\" nzValue=\"+87\"></nz-option>\n          </nz-select>\n        </ng-template>\n        <input formControlName=\"phoneNumber\" id=\"'phoneNumber'\" nz-input>\n      </nz-input-group>\n      <nz-form-explain *ngIf=\"validateForm.get('phoneNumber').dirty && validateForm.get('phoneNumber').errors\">请输入你的手机号码!</nz-form-explain>\n    </nz-form-control>\n  </nz-form-item>\n  <!--<nz-form-item>-->\n    <!--<nz-form-label [nzSm]=\"6\" [nzXs]=\"24\" nzFor=\"website\" nzRequired>网站</nz-form-label>-->\n    <!--<nz-form-control [nzSm]=\"14\" [nzXs]=\"24\">-->\n      <!--<input nz-input id=\"website\" formControlName=\"website\" placeholder=\"website\">-->\n      <!--<nz-form-explain *ngIf=\"validateForm.get('website').dirty && validateForm.get('website').errors\">请输入的你网站!</nz-form-explain>-->\n    <!--</nz-form-control>-->\n  <!--</nz-form-item>-->\n  <!--<nz-form-item>-->\n    <!--<nz-form-label [nzSm]=\"6\" [nzXs]=\"24\" nzFor=\"captcha\" nzRequired>国际</nz-form-label>-->\n    <!--<nz-form-control [nzSm]=\"14\" [nzXs]=\"24\">-->\n      <!--<div nz-row [nzGutter]=\"8\">-->\n        <!--<div nz-col [nzSpan]=\"12\">-->\n          <!--<input nz-input formControlName=\"captcha\" id=\"captcha\">-->\n        <!--</div>-->\n        <!--<div nz-col [nzSpan]=\"12\">-->\n          <!--<button nz-button (click)=\"getCaptcha($event)\">Get captcha</button>-->\n        <!--</div>-->\n      <!--</div>-->\n      <!--<nz-form-explain *ngIf=\"validateForm.get('captcha').dirty && validateForm.get('captcha').errors\">请确认你是地球人!</nz-form-explain>-->\n      <!--<nz-form-extra>We must make sure that your are a human.</nz-form-extra>-->\n    <!--</nz-form-control>-->\n  <!--</nz-form-item>-->\n  <nz-form-item nz-row style=\"margin-bottom:8px;\">\n    <nz-form-control [nzSpan]=\"14\" [nzOffset]=\"6\">\n      <label nz-checkbox formControlName=\"agree\">\n        <span>I have read the <a>agreement</a></span>\n      </label>\n    </nz-form-control>\n  </nz-form-item>\n  <nz-form-item nz-row style=\"margin-bottom:8px;\">\n    <nz-form-control [nzSpan]=\"14\" [nzOffset]=\"6\">\n      <button nz-button nzType=\"primary\">Register</button>\n    </nz-form-control>\n  </nz-form-item>\n</form>\n"

/***/ }),

/***/ "./src/app/component/user/add-user/add-user.component.less":
/*!*****************************************************************!*\
  !*** ./src/app/component/user/add-user/add-user.component.less ***!
  \*****************************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = ""

/***/ }),

/***/ "./src/app/component/user/add-user/add-user.component.ts":
/*!***************************************************************!*\
  !*** ./src/app/component/user/add-user/add-user.component.ts ***!
  \***************************************************************/
/*! exports provided: AddUserComponent */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "AddUserComponent", function() { return AddUserComponent; });
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _angular_forms__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/forms */ "./node_modules/@angular/forms/fesm5/forms.js");
/* harmony import */ var _core_api_service_user_service__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! ../../../core/api-service/user.service */ "./src/app/core/api-service/user.service.ts");
/* harmony import */ var _angular_router__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! @angular/router */ "./node_modules/@angular/router/fesm5/router.js");
var __decorate = (undefined && undefined.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (undefined && undefined.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};




var AddUserComponent = /** @class */ (function () {
    function AddUserComponent(fb, userService, activeRoute, router) {
        var _this = this;
        this.fb = fb;
        this.userService = userService;
        this.activeRoute = activeRoute;
        this.router = router;
        this.confirmationValidator = function (control) {
            if (!control.value) {
                return { required: true };
            }
            else if (control.value !== _this.validateForm.controls.password.value) {
                return { confirm: true, error: true };
            }
        };
    }
    AddUserComponent.prototype.submitForm = function () {
        var _this = this;
        for (var _i = 0, _a = Object.keys(this.validateForm.controls); _i < _a.length; _i++) {
            var i = _a[_i];
            this.validateForm.controls[i].markAsDirty();
            this.validateForm.controls[i].updateValueAndValidity();
        }
        console.log(this.validateForm);
        if (this.validateForm.valid) {
            var _b = this.validateForm.getRawValue(), email = _b.email, password = _b.password, userCode = _b.userCode, userName = _b.userName, phoneNumber = _b.phoneNumber;
            if (this.type === 'update') {
                var obj = { email: email, password: password, userCode: userCode, userName: userName, phoneNumber: phoneNumber };
                obj['userId'] = this.id;
                this.userService.updateUser(obj).then(function (res) {
                    _this.router.navigate(['/user/user-list']).then();
                });
            }
            else {
                this.userService.register({ email: email, password: password, userCode: userCode, userName: userName, phoneNumber: phoneNumber }).then(function (result) {
                    console.log(result);
                    _this.router.navigate(['/user/user-list']).then();
                });
            }
        }
        console.log(this.validateForm.getRawValue());
    };
    AddUserComponent.prototype.updateConfirmValidator = function () {
        var _this = this;
        /** wait for refresh value */
        Promise.resolve().then(function () { return _this.validateForm.controls.checkPassword.updateValueAndValidity(); });
    };
    AddUserComponent.prototype.getCaptcha = function (e) {
        e.preventDefault();
    };
    AddUserComponent.prototype.ngOnInit = function () {
        var _this = this;
        this.type = this.activeRoute.snapshot.params.type;
        if (this.type === 'update') {
            this.activeRoute.queryParams.subscribe(function (e) {
                console.log(e);
                _this.id = e.id;
                _this.userService.getUserById(e.id).then(function (result) {
                    console.log(result.data);
                    _this.validateForm.reset(result.data);
                });
            });
        }
        this.validateForm = this.fb.group({
            email: [null, [_angular_forms__WEBPACK_IMPORTED_MODULE_1__["Validators"].email]],
            userCode: [null, [_angular_forms__WEBPACK_IMPORTED_MODULE_1__["Validators"].required]],
            password: [null, [_angular_forms__WEBPACK_IMPORTED_MODULE_1__["Validators"].required]],
            checkPassword: [null, [_angular_forms__WEBPACK_IMPORTED_MODULE_1__["Validators"].required, this.confirmationValidator]],
            userName: [null, [_angular_forms__WEBPACK_IMPORTED_MODULE_1__["Validators"].required]],
            phoneNumberPrefix: ['+86'],
            phoneNumber: [null, [_angular_forms__WEBPACK_IMPORTED_MODULE_1__["Validators"].required]],
            agree: [false]
        });
    };
    AddUserComponent = __decorate([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_0__["Component"])({
            selector: 'app-add-user',
            template: __webpack_require__(/*! ./add-user.component.html */ "./src/app/component/user/add-user/add-user.component.html"),
            styles: [__webpack_require__(/*! ./add-user.component.less */ "./src/app/component/user/add-user/add-user.component.less")]
        }),
        __metadata("design:paramtypes", [_angular_forms__WEBPACK_IMPORTED_MODULE_1__["FormBuilder"],
            _core_api_service_user_service__WEBPACK_IMPORTED_MODULE_2__["UserService"],
            _angular_router__WEBPACK_IMPORTED_MODULE_3__["ActivatedRoute"],
            _angular_router__WEBPACK_IMPORTED_MODULE_3__["Router"]])
    ], AddUserComponent);
    return AddUserComponent;
}());



/***/ }),

/***/ "./src/app/component/user/user-list/user-list.component.html":
/*!*******************************************************************!*\
  !*** ./src/app/component/user/user-list/user-list.component.html ***!
  \*******************************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = "<div class=\"box-warp\">\n  <div style=\"box-shadow: 0 1px 2px rgba(0,0,0,.15), 0 -1px 0 rgba(0,0,0,.02);\">\n    <app-table [dataSet]=\"dataSet\"\n               [pageBean]=\"pageBean\"\n               [tableConfig]=\"tableConfig\"\n               (pageChange)=\"pageChange($event)\"\n    ></app-table>\n  </div>\n</div>\n"

/***/ }),

/***/ "./src/app/component/user/user-list/user-list.component.less":
/*!*******************************************************************!*\
  !*** ./src/app/component/user/user-list/user-list.component.less ***!
  \*******************************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = ":host ::ng-deep .trigger {\n  font-size: 18px;\n  line-height: 64px;\n  padding: 0 24px;\n  cursor: pointer;\n  transition: color 0.3s;\n}\n:host ::ng-deep .trigger:hover {\n  color: #1890ff;\n}\n:host ::ng-deep .logo {\n  height: 32px;\n}\n:host ::ng-deep .ant-menu-submenu > .ant-menu {\n  background: #1e292e;\n  color: #939fa7;\n}\n:host ::ng-deep .ant-layout-header {\n  padding-left: 20px;\n}\n.content {\n  box-sizing: border-box;\n  min-height: calc(100vh - 133px);\n}\n.logo {\n  width: 220px;\n  height: 31px;\n  line-height: 31px;\n  color: #ffffff;\n  margin-top: 8px;\n  float: left;\n  position: relative;\n}\n.alarm {\n  width: 500px;\n  float: right;\n}\n.item-ul {\n  background: #1e292e !important;\n}\n.item-ul li {\n  background: #1e292e !important;\n  width: 135px;\n  box-sizing: content-box;\n}\n.form-warp {\n  display: flex;\n}\n.box-warp {\n  margin-bottom: 20px;\n  padding: 10px;\n  box-shadow: 0 1px 2px rgba(0, 0, 0, 0.15), 0 -1px 0 rgba(0, 0, 0, 0.02);\n}\n.box-warp .form-item {\n  margin-right: 10px;\n  padding-left: 10px;\n  flex: 1;\n  display: flex;\n  line-height: 32px;\n}\n"

/***/ }),

/***/ "./src/app/component/user/user-list/user-list.component.ts":
/*!*****************************************************************!*\
  !*** ./src/app/component/user/user-list/user-list.component.ts ***!
  \*****************************************************************/
/*! exports provided: UserListComponent */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "UserListComponent", function() { return UserListComponent; });
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _angular_router__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/router */ "./node_modules/@angular/router/fesm5/router.js");
/* harmony import */ var _core_api_service_user_service__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! ../../../core/api-service/user.service */ "./src/app/core/api-service/user.service.ts");
/* harmony import */ var _core_entity_pageBean__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! ../../../core/entity/pageBean */ "./src/app/core/entity/pageBean.ts");
var __decorate = (undefined && undefined.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (undefined && undefined.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};




var UserListComponent = /** @class */ (function () {
    function UserListComponent($router, userService) {
        this.$router = $router;
        this.userService = userService;
        this.dataSet = [];
        this.pageBean = new _core_entity_pageBean__WEBPACK_IMPORTED_MODULE_3__["PageBean"](10, 1, 1);
    }
    UserListComponent.prototype.ngOnInit = function () {
        var _this = this;
        this.tableConfig = {
            columnConfig: [
                { title: '用户昵称', key: 'userName', width: '200px' },
                { title: '用户代码', key: 'userCode', width: '200px' },
                { title: '手机号', key: 'phoneNumber', width: '200px' },
                { title: '用户邮箱', key: 'email', width: '200px' },
                { title: '创建时间', key: 'createTime', width: '200px' },
                { title: '操作', key: '', width: '200px' },
            ],
            showPagination: true,
            bordered: false,
            operation: [
                {
                    text: '删除',
                    handle: function (currentIndex) {
                        console.log(currentIndex);
                        _this.deleteUserById(currentIndex.id);
                    }
                },
                {
                    text: '修改',
                    handle: function (currentIndex) {
                        console.log(currentIndex);
                        _this.$router.navigate(['/user/user-detail/update'], { queryParams: { id: currentIndex.id } }).then(function (e) {
                        });
                    }
                }
            ],
            topButtons: [
                {
                    text: '新增用户',
                    handle: function (currentIndex) {
                        _this.addUser();
                    }
                },
                {
                    text: '批量删除',
                    handle: function (currentIndex) {
                        var ids = currentIndex.map(function (item) { return item.id; });
                        console.log(ids);
                        _this.batchDelete(ids);
                    }
                },
            ]
        };
        this.getUserList();
    };
    UserListComponent.prototype.addUser = function () {
        this.$router.navigate(['/user/user-detail/add']).then(function (e) {
        });
    };
    UserListComponent.prototype.getUserList = function () {
        var _this = this;
        this.userService.getAllUser().then(function (result) {
            console.log(result);
            _this.dataSet = result.data;
        });
    };
    UserListComponent.prototype.deleteUserById = function (id) {
        var _this = this;
        this.userService.deleteUserById(id).then(function (result) {
            _this.getUserList();
        });
    };
    UserListComponent.prototype.batchDelete = function (ids) {
        var _this = this;
        this.userService.deleteUserByBatch(ids).then(function (result) {
            _this.getUserList();
        });
    };
    UserListComponent = __decorate([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_0__["Component"])({
            selector: 'app-user-list',
            template: __webpack_require__(/*! ./user-list.component.html */ "./src/app/component/user/user-list/user-list.component.html"),
            styles: [__webpack_require__(/*! ./user-list.component.less */ "./src/app/component/user/user-list/user-list.component.less")]
        }),
        __metadata("design:paramtypes", [_angular_router__WEBPACK_IMPORTED_MODULE_1__["Router"],
            _core_api_service_user_service__WEBPACK_IMPORTED_MODULE_2__["UserService"]])
    ], UserListComponent);
    return UserListComponent;
}());



/***/ }),

/***/ "./src/app/component/user/user.component.html":
/*!****************************************************!*\
  !*** ./src/app/component/user/user.component.html ***!
  \****************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = "<nz-header class=\"header\">\n  <div class=\"logo\"><img width=\"50\" src=\"./../../../assets/iotWls.svg\" alt=\"\"/>web音乐管理系统</div>\n  <div class=\"alarm\">\n  </div>\n</nz-header>\n<nz-layout class=\"layout\">\n\n  <nz-layout>\n    <nz-sider [nzWidth]=\"200\" style=\"background:#263238;\">\n      <ul nz-menu [nzMode]=\"'inline'\" style=\"background:#263238;width:201px;color: #939fa7;\">\n        <li nz-menu-item nz-tooltip nzPlacement=\"right\" (click)=\"navigate('user/music/music-list')\">\n            <span title>\n            <i class=\"anticon anticon-mail\"></i>\n            <span>音乐列表</span>\n            </span>\n        </li>\n        <li nz-menu-item (click)=\"navigate('user/user-list')\">\n            <span title>\n              <i class=\"anticon anticon-appstore\"></i>\n              <span>用户列表</span>\n            </span>\n        </li>\n        <li nz-menu-item>\n            <span title>\n              <i class=\"anticon anticon-setting\"></i>\n              <span>播放策略配置</span>\n            </span>\n        </li>\n      </ul>\n    </nz-sider>\n    <nz-layout style=\"padding:0 24px;box-sizing: border-box;background: #f6fafb\">\n      <nz-content style=\"background:#f6fafb; box-sizing: border-box;\n  height:calc(100vh - 133px);padding: 24px 24px 0 24px;overflow: auto \">\n        <router-outlet></router-outlet>\n      </nz-content>\n      <nz-footer style=\"text-align: center;background: #f6fafb\">Ant Design ©2017 Implement By Angular</nz-footer>\n    </nz-layout>\n  </nz-layout>\n</nz-layout>\n<app-play></app-play>\n"

/***/ }),

/***/ "./src/app/component/user/user.component.less":
/*!****************************************************!*\
  !*** ./src/app/component/user/user.component.less ***!
  \****************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = ":host ::ng-deep .trigger {\n  font-size: 18px;\n  line-height: 64px;\n  padding: 0 24px;\n  cursor: pointer;\n  transition: color 0.3s;\n}\n:host ::ng-deep .trigger:hover {\n  color: #1890ff;\n}\n:host ::ng-deep .logo {\n  height: 32px;\n}\n:host ::ng-deep .ant-menu-submenu > .ant-menu {\n  background: #1e292e;\n  color: #939fa7;\n}\n:host ::ng-deep .ant-layout-header {\n  padding-left: 20px;\n}\n.content {\n  box-sizing: border-box;\n  min-height: calc(100vh - 133px);\n}\n.logo {\n  width: 220px;\n  height: 31px;\n  line-height: 31px;\n  color: #ffffff;\n  margin-top: 8px;\n  float: left;\n  position: relative;\n}\n.alarm {\n  width: 500px;\n  float: right;\n}\n.item-ul {\n  background: #1e292e !important;\n}\n.item-ul li {\n  background: #1e292e !important;\n  width: 135px;\n  box-sizing: content-box;\n}\n.form-warp {\n  display: flex;\n}\n.box-warp {\n  margin-bottom: 20px;\n  padding: 10px;\n  box-shadow: 0 1px 2px rgba(0, 0, 0, 0.15), 0 -1px 0 rgba(0, 0, 0, 0.02);\n}\n.box-warp .form-item {\n  margin-right: 10px;\n  padding-left: 10px;\n  flex: 1;\n  display: flex;\n  line-height: 32px;\n}\n:host ::ng-deep .trigger {\n  font-size: 18px;\n  line-height: 64px;\n  padding: 0 24px;\n  cursor: pointer;\n  transition: color 0.3s;\n}\n:host ::ng-deep .trigger:hover {\n  color: #1890ff;\n}\n:host ::ng-deep .logo {\n  height: 32px;\n}\n:host ::ng-deep .ant-menu-submenu > .ant-menu {\n  background: #1e292e;\n  color: #939fa7;\n}\n:host ::ng-deep .ant-layout-header {\n  padding-left: 20px;\n}\n.content {\n  box-sizing: border-box;\n  min-height: calc(100vh - 133px);\n}\n.logo {\n  width: 220px;\n  height: 31px;\n  line-height: 31px;\n  color: #ffffff;\n  margin-top: 8px;\n  float: left;\n  position: relative;\n}\n.alarm {\n  width: 500px;\n  float: right;\n}\n.item-ul {\n  background: #1e292e !important;\n}\n.item-ul li {\n  background: #1e292e !important;\n  width: 135px;\n  box-sizing: content-box;\n}\n.form-warp {\n  display: flex;\n}\n.box-warp {\n  margin-bottom: 20px;\n  padding: 10px;\n  box-shadow: 0 1px 2px rgba(0, 0, 0, 0.15), 0 -1px 0 rgba(0, 0, 0, 0.02);\n}\n.box-warp .form-item {\n  margin-right: 10px;\n  padding-left: 10px;\n  flex: 1;\n  display: flex;\n  line-height: 32px;\n}\n.header {\n  margin: 0;\n  background: url('wls.png');\n}\n"

/***/ }),

/***/ "./src/app/component/user/user.component.ts":
/*!**************************************************!*\
  !*** ./src/app/component/user/user.component.ts ***!
  \**************************************************/
/*! exports provided: UserComponent */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "UserComponent", function() { return UserComponent; });
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _angular_router__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/router */ "./node_modules/@angular/router/fesm5/router.js");
var __decorate = (undefined && undefined.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (undefined && undefined.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};
/**
 * Created by WH1709040 on 2018/10/11.
 */


var UserComponent = /** @class */ (function () {
    function UserComponent($router) {
        this.$router = $router;
    }
    UserComponent.prototype.ngOnInit = function () {
    };
    UserComponent.prototype.navigate = function (target) {
        this.$router.navigate([target]).catch();
    };
    UserComponent = __decorate([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_0__["Component"])({
            selector: 'app-user',
            template: __webpack_require__(/*! ./user.component.html */ "./src/app/component/user/user.component.html"),
            styles: [__webpack_require__(/*! ./user.component.less */ "./src/app/component/user/user.component.less")]
        }),
        __metadata("design:paramtypes", [_angular_router__WEBPACK_IMPORTED_MODULE_1__["Router"]])
    ], UserComponent);
    return UserComponent;
}());



/***/ }),

/***/ "./src/app/component/user/user.module.ts":
/*!***********************************************!*\
  !*** ./src/app/component/user/user.module.ts ***!
  \***********************************************/
/*! exports provided: UserModule */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "UserModule", function() { return UserModule; });
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _angular_router__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/router */ "./node_modules/@angular/router/fesm5/router.js");
/* harmony import */ var _user_routes__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! ./user.routes */ "./src/app/component/user/user.routes.ts");
/* harmony import */ var _user_component__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! ./user.component */ "./src/app/component/user/user.component.ts");
/* harmony import */ var _add_user_add_user_component__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! ./add-user/add-user.component */ "./src/app/component/user/add-user/add-user.component.ts");
/* harmony import */ var _shared_shared_module__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! ../../shared/shared.module */ "./src/app/shared/shared.module.ts");
/* harmony import */ var _user_list_user_list_component__WEBPACK_IMPORTED_MODULE_6__ = __webpack_require__(/*! ./user-list/user-list.component */ "./src/app/component/user/user-list/user-list.component.ts");
/* harmony import */ var _play_play_component__WEBPACK_IMPORTED_MODULE_7__ = __webpack_require__(/*! ../play/play.component */ "./src/app/component/play/play.component.ts");
/* harmony import */ var _core_core_module__WEBPACK_IMPORTED_MODULE_8__ = __webpack_require__(/*! ../../core/core.module */ "./src/app/core/core.module.ts");
/* harmony import */ var _play_play_progress_play_progress_component__WEBPACK_IMPORTED_MODULE_9__ = __webpack_require__(/*! ../play/play-progress/play-progress.component */ "./src/app/component/play/play-progress/play-progress.component.ts");
var __decorate = (undefined && undefined.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
/**
 * Created by WH1709040 on 2018/10/11.
 */










var UserModule = /** @class */ (function () {
    function UserModule() {
    }
    UserModule = __decorate([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_0__["NgModule"])({
            imports: [
                _shared_shared_module__WEBPACK_IMPORTED_MODULE_5__["SharedModule"],
                _core_core_module__WEBPACK_IMPORTED_MODULE_8__["CoreModule"],
                _angular_router__WEBPACK_IMPORTED_MODULE_1__["RouterModule"].forChild(_user_routes__WEBPACK_IMPORTED_MODULE_2__["ROUTER_CONFIG"]),
            ],
            exports: [],
            declarations: [_user_component__WEBPACK_IMPORTED_MODULE_3__["UserComponent"], _add_user_add_user_component__WEBPACK_IMPORTED_MODULE_4__["AddUserComponent"], _user_list_user_list_component__WEBPACK_IMPORTED_MODULE_6__["UserListComponent"], _play_play_progress_play_progress_component__WEBPACK_IMPORTED_MODULE_9__["PlayProgressComponent"], _play_play_component__WEBPACK_IMPORTED_MODULE_7__["PlayComponent"]],
            entryComponents: [_play_play_progress_play_progress_component__WEBPACK_IMPORTED_MODULE_9__["PlayProgressComponent"]]
        })
    ], UserModule);
    return UserModule;
}());



/***/ }),

/***/ "./src/app/component/user/user.routes.ts":
/*!***********************************************!*\
  !*** ./src/app/component/user/user.routes.ts ***!
  \***********************************************/
/*! exports provided: ROUTER_CONFIG */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "ROUTER_CONFIG", function() { return ROUTER_CONFIG; });
/* harmony import */ var _user_component__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! ./user.component */ "./src/app/component/user/user.component.ts");
/* harmony import */ var _add_user_add_user_component__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! ./add-user/add-user.component */ "./src/app/component/user/add-user/add-user.component.ts");
/* harmony import */ var _user_list_user_list_component__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! ./user-list/user-list.component */ "./src/app/component/user/user-list/user-list.component.ts");



var ROUTER_CONFIG = [
    {
        path: '', component: _user_component__WEBPACK_IMPORTED_MODULE_0__["UserComponent"],
        children: [
            { path: 'user-detail/:type', component: _add_user_add_user_component__WEBPACK_IMPORTED_MODULE_1__["AddUserComponent"] },
            { path: 'user-list', component: _user_list_user_list_component__WEBPACK_IMPORTED_MODULE_2__["UserListComponent"] },
            { path: 'music', loadChildren: './../music/music.module#MusicModule' },
        ]
    },
];


/***/ }),

/***/ "./src/app/core/entity/music.ts":
/*!**************************************!*\
  !*** ./src/app/core/entity/music.ts ***!
  \**************************************/
/*! exports provided: Music */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "Music", function() { return Music; });
/**
 * Created by wh1709040 on 2018/10/23.
 */
var Music = /** @class */ (function () {
    function Music() {
    }
    Object.defineProperty(Music.prototype, "id", {
        get: function () {
            return this._id;
        },
        set: function (value) {
            this._id = value;
        },
        enumerable: true,
        configurable: true
    });
    Object.defineProperty(Music.prototype, "songName", {
        get: function () {
            return this._songName;
        },
        set: function (value) {
            this._songName = value;
        },
        enumerable: true,
        configurable: true
    });
    Object.defineProperty(Music.prototype, "songUrl", {
        get: function () {
            return this._songUrl;
        },
        set: function (value) {
            this._songUrl = value;
        },
        enumerable: true,
        configurable: true
    });
    Object.defineProperty(Music.prototype, "singer", {
        get: function () {
            return this._singer;
        },
        set: function (value) {
            this._singer = value;
        },
        enumerable: true,
        configurable: true
    });
    Object.defineProperty(Music.prototype, "createTime", {
        get: function () {
            return this._createTime;
        },
        set: function (value) {
            this._createTime = value;
        },
        enumerable: true,
        configurable: true
    });
    Object.defineProperty(Music.prototype, "songPic", {
        get: function () {
            return this._songPic;
        },
        set: function (value) {
            this._songPic = value;
        },
        enumerable: true,
        configurable: true
    });
    Object.defineProperty(Music.prototype, "songType", {
        get: function () {
            return this._songType;
        },
        set: function (value) {
            this._songType = value;
        },
        enumerable: true,
        configurable: true
    });
    Object.defineProperty(Music.prototype, "cdId", {
        get: function () {
            return this._cdId;
        },
        set: function (value) {
            this._cdId = value;
        },
        enumerable: true,
        configurable: true
    });
    Object.defineProperty(Music.prototype, "file", {
        get: function () {
            return this._file;
        },
        set: function (value) {
            this._file = value;
        },
        enumerable: true,
        configurable: true
    });
    Object.defineProperty(Music.prototype, "description", {
        get: function () {
            return this._description;
        },
        set: function (value) {
            this._description = value;
        },
        enumerable: true,
        configurable: true
    });
    return Music;
}());



/***/ })

}]);
//# sourceMappingURL=component-user-user-module.js.map