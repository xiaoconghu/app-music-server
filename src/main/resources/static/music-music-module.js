(window["webpackJsonp"] = window["webpackJsonp"] || []).push([["music-music-module"],{

/***/ "./src/app/component/music/music-detail/music-detail.component.html":
/*!**************************************************************************!*\
  !*** ./src/app/component/music/music-detail/music-detail.component.html ***!
  \**************************************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = "<nw-form (formInstance)=\"formInstance($event)\" [column]=\"column\"></nw-form>\r\n<label for=\"songFile\">\r\n  上传音乐\r\n</label>\r\n<input name=\"file\" type=\"file\" id=\"songFile\" (change)=\"change($event)\"/>\r\n<button nz-button nzType=\"primary\" (click)=\"save()\" style=\"margin-right: 20px\">保存</button>\r\n<button nz-button nzType=\"default\">返回</button>\r\n"

/***/ }),

/***/ "./src/app/component/music/music-detail/music-detail.component.less":
/*!**************************************************************************!*\
  !*** ./src/app/component/music/music-detail/music-detail.component.less ***!
  \**************************************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = ""

/***/ }),

/***/ "./src/app/component/music/music-detail/music-detail.component.ts":
/*!************************************************************************!*\
  !*** ./src/app/component/music/music-detail/music-detail.component.ts ***!
  \************************************************************************/
/*! exports provided: MusicDetailComponent */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "MusicDetailComponent", function() { return MusicDetailComponent; });
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _core_api_service_music_service__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! ../../../core/api-service/music.service */ "./src/app/core/api-service/music.service.ts");
/* harmony import */ var ng_zorro_antd__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! ng-zorro-antd */ "./node_modules/ng-zorro-antd/esm5/antd.js");
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




var MusicDetailComponent = /** @class */ (function () {
    function MusicDetailComponent(musicService, $router, activeRoute, message) {
        this.musicService = musicService;
        this.$router = $router;
        this.activeRoute = activeRoute;
        this.message = message;
        this.column = [];
    }
    MusicDetailComponent.prototype.ngOnInit = function () {
        var _this = this;
        this.type = this.activeRoute.snapshot.params.type;
        if (this.type === 'update') {
            this.activeRoute.queryParams.subscribe(function (e) {
                _this.id = e.id;
                _this.musicService.getUserById(e.id).then(function (result) {
                    console.log(result.data);
                    _this.formOperate.resetData(result.data);
                });
            });
        }
        this.column = [
            { label: '歌曲名', key: 'songName', rule: [{ required: true }, { minLength: 3 }], require: true, type: 'input' },
            { label: '歌手', key: 'singer', rule: [], require: true, type: 'input' },
            { label: '创建时间', key: 'createTime', rule: [], require: true, type: 'input' },
            { label: '歌曲图片', key: 'songPic', rule: [], require: true, type: 'input' },
            { label: '歌曲类型', key: 'songType', rule: [], require: true, type: 'input' },
            { label: '描述', key: 'description', rule: [], require: true, type: 'input' },
        ];
    };
    MusicDetailComponent.prototype.formInstance = function (e) {
        this.formOperate = e.instance;
    };
    MusicDetailComponent.prototype.change = function (e) {
        this.songFile = e.target.files[0];
        console.log(e);
    };
    MusicDetailComponent.prototype.save = function () {
        var _this = this;
        if (this.type === 'update') {
            var body = this.formOperate.getData();
            body.id = this.id;
            this.musicService.update(body).then(function (res) {
                _this.$router.navigate(['/user/music/music-list']);
            });
        }
        else {
            var _bod = this.formOperate.getData();
            var body = new FormData();
            body.append('file', this.songFile);
            body.append('song', JSON.stringify(_bod));
            this.musicService.save(body).then(function (re) {
                _this.$router.navigate(['/user/music/music-list']);
                _this.message.success(re.msg);
            }, function (err) {
                _this.message.error(err.msg);
            });
        }
    };
    MusicDetailComponent = __decorate([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_0__["Component"])({
            selector: 'app-music-detail',
            template: __webpack_require__(/*! ./music-detail.component.html */ "./src/app/component/music/music-detail/music-detail.component.html"),
            styles: [__webpack_require__(/*! ./music-detail.component.less */ "./src/app/component/music/music-detail/music-detail.component.less")]
        }),
        __metadata("design:paramtypes", [_core_api_service_music_service__WEBPACK_IMPORTED_MODULE_1__["MusicService"],
            _angular_router__WEBPACK_IMPORTED_MODULE_3__["Router"],
            _angular_router__WEBPACK_IMPORTED_MODULE_3__["ActivatedRoute"],
            ng_zorro_antd__WEBPACK_IMPORTED_MODULE_2__["NzMessageService"]])
    ], MusicDetailComponent);
    return MusicDetailComponent;
}());



/***/ }),

/***/ "./src/app/component/music/music-list/music-list.component.html":
/*!**********************************************************************!*\
  !*** ./src/app/component/music/music-list/music-list.component.html ***!
  \**********************************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = "<div class=\"box-warp\">\r\n  <div style=\"box-shadow: 0 1px 2px rgba(0,0,0,.15), 0 -1px 0 rgba(0,0,0,.02);\">\r\n    <app-table [dataSet]=\"dataSet\"\r\n               [pageBean]=\"pageBean\"\r\n               [tableConfig]=\"tableConfig\"\r\n               (pageChange)=\"pageChange($event)\"\r\n    ></app-table>\r\n  </div>\r\n</div>\r\n"

/***/ }),

/***/ "./src/app/component/music/music-list/music-list.component.less":
/*!**********************************************************************!*\
  !*** ./src/app/component/music/music-list/music-list.component.less ***!
  \**********************************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = ""

/***/ }),

/***/ "./src/app/component/music/music-list/music-list.component.ts":
/*!********************************************************************!*\
  !*** ./src/app/component/music/music-list/music-list.component.ts ***!
  \********************************************************************/
/*! exports provided: MusicListComponent */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "MusicListComponent", function() { return MusicListComponent; });
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _angular_router__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/router */ "./node_modules/@angular/router/fesm5/router.js");
/* harmony import */ var _core_entity_pageBean__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! ../../../core/entity/pageBean */ "./src/app/core/entity/pageBean.ts");
/* harmony import */ var _core_api_service_music_service__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! ../../../core/api-service/music.service */ "./src/app/core/api-service/music.service.ts");
/* harmony import */ var ng_zorro_antd__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! ng-zorro-antd */ "./node_modules/ng-zorro-antd/esm5/antd.js");
var __decorate = (undefined && undefined.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (undefined && undefined.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};





var MusicListComponent = /** @class */ (function () {
    function MusicListComponent($router, musicService, message) {
        this.$router = $router;
        this.musicService = musicService;
        this.message = message;
        this.dataSet = [];
        this.pageBean = new _core_entity_pageBean__WEBPACK_IMPORTED_MODULE_2__["PageBean"](10, 1, 1);
    }
    MusicListComponent.prototype.ngOnInit = function () {
        var _this = this;
        this.tableConfig = {
            bordered: false,
            showPagination: false,
            columnConfig: [
                { title: '歌曲名', key: 'songName' },
                { title: '歌手', key: 'singer' },
                { title: '上传时间', key: 'createTime' },
                { title: '图片', key: 'songPic' },
                { title: '歌曲类型', key: 'songType' },
                { title: '描述', key: 'description' },
                { title: '操作', key: '', width: '200px' },
            ],
            operation: [
                {
                    text: '删除',
                    handle: function (currentIndex) {
                        console.log(currentIndex);
                        _this.deleteMusic(currentIndex.id);
                    }
                },
                {
                    text: '修改',
                    handle: function (currentIndex) {
                        console.log(currentIndex);
                        _this.$router.navigate(['/user/music/music-detail/update'], { queryParams: { id: currentIndex.id } }).then(function (e) {
                        });
                    }
                }
            ],
            topButtons: [
                {
                    text: '上传音乐',
                    handle: function (currentIndex) {
                        _this.$router.navigate(['/user/music/music-detail/add']).then(function (e) {
                        });
                    }
                },
                {
                    text: '批量删除',
                    handle: function (currentIndex) {
                        var ids = currentIndex.map(function (item) { return item.id; });
                        console.log(ids);
                        _this.deleteByBatch(ids);
                    }
                },
            ]
        };
        this.getMusicList();
    };
    MusicListComponent.prototype.getMusicList = function () {
        var _this = this;
        this.musicService.getMusicList().then(function (re) {
            _this.dataSet = re.data;
            _this.message.success(re.msg);
        }, function (err) {
            _this.message.error(err.msg);
        });
    };
    MusicListComponent.prototype.deleteMusic = function (id) {
        var _this = this;
        this.musicService.deleteMusicById(id).then(function (result) {
            _this.getMusicList();
        }, function (err) {
            _this.message.error(err.msg);
        });
    };
    MusicListComponent.prototype.deleteByBatch = function (ids) {
        var _this = this;
        this.musicService.deleteByBatch(ids).then(function (result) {
            _this.getMusicList();
        });
    };
    MusicListComponent = __decorate([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_0__["Component"])({
            selector: 'app-music-list',
            template: __webpack_require__(/*! ./music-list.component.html */ "./src/app/component/music/music-list/music-list.component.html"),
            styles: [__webpack_require__(/*! ./music-list.component.less */ "./src/app/component/music/music-list/music-list.component.less")]
        }),
        __metadata("design:paramtypes", [_angular_router__WEBPACK_IMPORTED_MODULE_1__["Router"],
            _core_api_service_music_service__WEBPACK_IMPORTED_MODULE_3__["MusicService"],
            ng_zorro_antd__WEBPACK_IMPORTED_MODULE_4__["NzMessageService"]])
    ], MusicListComponent);
    return MusicListComponent;
}());



/***/ }),

/***/ "./src/app/component/music/music.component.html":
/*!******************************************************!*\
  !*** ./src/app/component/music/music.component.html ***!
  \******************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = "<p>\r\n  music works!\r\n</p>\r\n"

/***/ }),

/***/ "./src/app/component/music/music.component.less":
/*!******************************************************!*\
  !*** ./src/app/component/music/music.component.less ***!
  \******************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = ""

/***/ }),

/***/ "./src/app/component/music/music.component.ts":
/*!****************************************************!*\
  !*** ./src/app/component/music/music.component.ts ***!
  \****************************************************/
/*! exports provided: MusicComponent */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "MusicComponent", function() { return MusicComponent; });
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
var __decorate = (undefined && undefined.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (undefined && undefined.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};

var MusicComponent = /** @class */ (function () {
    function MusicComponent() {
    }
    MusicComponent.prototype.ngOnInit = function () {
    };
    MusicComponent = __decorate([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_0__["Component"])({
            selector: 'app-music',
            template: __webpack_require__(/*! ./music.component.html */ "./src/app/component/music/music.component.html"),
            styles: [__webpack_require__(/*! ./music.component.less */ "./src/app/component/music/music.component.less")]
        }),
        __metadata("design:paramtypes", [])
    ], MusicComponent);
    return MusicComponent;
}());



/***/ }),

/***/ "./src/app/component/music/music.module.ts":
/*!*************************************************!*\
  !*** ./src/app/component/music/music.module.ts ***!
  \*************************************************/
/*! exports provided: MusicModule */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "MusicModule", function() { return MusicModule; });
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _angular_common__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/common */ "./node_modules/@angular/common/fesm5/common.js");
/* harmony import */ var _music_component__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! ./music.component */ "./src/app/component/music/music.component.ts");
/* harmony import */ var _music_list_music_list_component__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! ./music-list/music-list.component */ "./src/app/component/music/music-list/music-list.component.ts");
/* harmony import */ var _angular_router__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! @angular/router */ "./node_modules/@angular/router/fesm5/router.js");
/* harmony import */ var _music_route__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! ./music.route */ "./src/app/component/music/music.route.ts");
/* harmony import */ var _core_core_module__WEBPACK_IMPORTED_MODULE_6__ = __webpack_require__(/*! ../../core/core.module */ "./src/app/core/core.module.ts");
/* harmony import */ var _shared_shared_module__WEBPACK_IMPORTED_MODULE_7__ = __webpack_require__(/*! ../../shared/shared.module */ "./src/app/shared/shared.module.ts");
/* harmony import */ var _music_detail_music_detail_component__WEBPACK_IMPORTED_MODULE_8__ = __webpack_require__(/*! ./music-detail/music-detail.component */ "./src/app/component/music/music-detail/music-detail.component.ts");
var __decorate = (undefined && undefined.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};









var MusicModule = /** @class */ (function () {
    function MusicModule() {
    }
    MusicModule = __decorate([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_0__["NgModule"])({
            imports: [
                _angular_common__WEBPACK_IMPORTED_MODULE_1__["CommonModule"],
                _core_core_module__WEBPACK_IMPORTED_MODULE_6__["CoreModule"],
                _shared_shared_module__WEBPACK_IMPORTED_MODULE_7__["SharedModule"],
                _angular_router__WEBPACK_IMPORTED_MODULE_4__["RouterModule"].forChild(_music_route__WEBPACK_IMPORTED_MODULE_5__["ROUTER_CONFIG"])
            ],
            declarations: [_music_component__WEBPACK_IMPORTED_MODULE_2__["MusicComponent"], _music_list_music_list_component__WEBPACK_IMPORTED_MODULE_3__["MusicListComponent"], _music_detail_music_detail_component__WEBPACK_IMPORTED_MODULE_8__["MusicDetailComponent"]]
        })
    ], MusicModule);
    return MusicModule;
}());



/***/ }),

/***/ "./src/app/component/music/music.route.ts":
/*!************************************************!*\
  !*** ./src/app/component/music/music.route.ts ***!
  \************************************************/
/*! exports provided: ROUTER_CONFIG */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "ROUTER_CONFIG", function() { return ROUTER_CONFIG; });
/* harmony import */ var _music_component__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! ./music.component */ "./src/app/component/music/music.component.ts");
/* harmony import */ var _music_list_music_list_component__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! ./music-list/music-list.component */ "./src/app/component/music/music-list/music-list.component.ts");
/* harmony import */ var _music_detail_music_detail_component__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! ./music-detail/music-detail.component */ "./src/app/component/music/music-detail/music-detail.component.ts");



var ROUTER_CONFIG = [
    { path: '', component: _music_component__WEBPACK_IMPORTED_MODULE_0__["MusicComponent"] },
    { path: 'music-list', component: _music_list_music_list_component__WEBPACK_IMPORTED_MODULE_1__["MusicListComponent"] },
    { path: 'music-detail/:type', component: _music_detail_music_detail_component__WEBPACK_IMPORTED_MODULE_2__["MusicDetailComponent"] },
];


/***/ })

}]);
//# sourceMappingURL=music-music-module.js.map