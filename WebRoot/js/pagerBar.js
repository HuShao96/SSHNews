/**
* 分页控件封装
**/
angular.module('pager', []).directive('pagerBar', function () {
    return {
        restrict: 'E',
        template: '<div class="page-list">'
            + '<ul class="pagination" ng-show="conf.totalCount > 0">'
            //上一页
            + '<li ng-class="{disabled:conf.currentPage==1}" ng-click="prevPage()"><span>&laquo;</span></li>'
            //数字列表
            + '<li ng-repeat="item in numberList track by $index" ng-class="{active:item==conf.currentPage, separate:item==\'...\'}" ng-click="changePage(item)">'
            + '<span>{{item}}</span>' + '</li>'
            //下一页
            + '<li ng-class="{disabled:conf.currentPage==pageCount}" ng-click="nextPage()"><span>&raquo;</span></li>'
            + '</ul>'
             //总共几页
            + '<div class="page-total hidden-xs" ng-show="conf.totalCount>0">'
            + '第<input type="text" ng-model="jumpPageNum" ng-keyup="jumpToPage($event)"/>页 '
            + '每页<select ng-model="itemsPerPage" ng-options="option for option in perPageOptions" ng-change="changePageSize()"></select>/共<strong>{{conf.totalCount}}</strong>条'
            + '</div>'
            //如果没有数据
           + '<div class="no-items" ng-show="isShowNone">暂无数据</div>'
            + '</div>',
        replace: true,
        scope: {
            conf: '='
        },
        link: function (scope, elem, attrs) {
            //默认参数配置
            if (!scope.conf) {
                scope.conf = {
                    currentPage: 1,
                    pageSize: 10,
                    totalCount: 0
                };
            }
            scope.isShowNone = false;
            //初始化操作
            function init() {
                //定义分页长处位奇数， (default:9)
                scope.pagesLength = parseInt(scope.pagesLength) ? parseInt(scope.pagesLength) : 9;
                if (scope.pagesLength % 2 === 0) {
                    //如果不是奇数，自动处理一下
                    scope.pagesLength--;
                }
                //定义分页内容列表
                if (!scope.perPageOptions) {
                    scope.perPageOptions = [10, 20, 30, 40, 50];
                }
                //当前页
                scope.conf.currentPage = parseInt(scope.conf.currentPage) || 1;
                //每页个数
                scope.conf.pageSize = parseInt(scope.conf.pageSize) || 10;
                //极限值过滤
                if (scope.conf.currentPage < 1) {
                    scope.conf.currentPage = 1;
                }
                if (scope.conf.currentPage > scope.pageCount) {
                    scope.conf.currentPage = scope.pageCount;
                }
                //如果pageSize 不在perPageOptions中 ，就修改pageSize
                if (isFromArray(scope.conf.pageSize, scope.perPageOptions) == false) {
                    scope.conf.pageSize = scope.perPageOptions[0];
                }
            }
            init();

            //获取pageList数组
            function getPagination() {
                //触发事件
                if (scope.conf.onChange) {
                    scope.conf.onChange();
                }

                scope.itemsPerPage = scope.conf.pageSize;
                //页数
                scope.pageCount = getPageCount();
                scope.jumpPageNum = scope.conf.currentPage;

                //生成 pageList 的数字列表
                scope.numberList = [];
                if (scope.pageCount <= scope.conf.pageSize) {
                    for (var i = 1; i <= scope.pageCount; i++) {
                        scope.numberList.push(i);
                    }
                } else {
                    //总页数大于分页长度 （此时分为三种情况：1.左边没有...2.右边没有...3.左右都没有...）
                    //计算中心偏移量
                    var offset = (scope.pagesLength - 1) / 2;
                    if (scope.conf.currentPage <= offset) {
                        //左边没有...
                        for (var i = 1; i <= offset + 1; i++) {
                            scope.numberList.push(i);
                        }
                        scope.numberList.push('...');
                        scope.numberList.push(scope.pageCount);
                    } else if (scope.conf.currentPage > scope.pageCount - offset) {
                        //右边没有...
                        scope.numberList.push(1);
                        scope.numberList.push('...')
                        for (var i = offset + 1; i >= 1; i--) {
                            scope.numberList.push(scope.pageCount - i);
                        }
                        scope.numberList.push(scope.pageCount);
                    } else {
                        //两边都有...
                        scope.numberList.push(1);
                        scope.numberList.push('...');
                        for (var i = Math.ceil(offset / 2) ; i >= 1; i--) {
                            scope.numberList.push(scope.conf.currentPage - i);
                        }
                        scope.numberList.push(scope.conf.currentPage);
                        for (var i = 1; i <= offset / 2; i++) {
                            scope.numberList.push(scope.conf.currentPage + i);
                        }
                        scope.numberList.push('...');
                        scope.numberList.push(scope.pageCount);
                    }
                }

            }
            //计算总页数
            function getPageCount() {
                return  Math.ceil(scope.conf.totalCount / scope.conf.pageSize);;
            }
            //1.变更当前页
            scope.changePage = function (item) {
                if (item == '...')
                    return;
                else
                    scope.conf.currentPage = item;
            }
            //上一页
            scope.prevPage = function () {
                if (scope.conf.currentPage > 1) {
                    scope.conf.currentPage -= 1;
                }
            }
            //下一页
            scope.nextPage = function () {
                //如果小于总页数，执行下一页
                if (scope.conf.currentPage < scope.pageCount) {
                    scope.conf.currentPage += 1;
                }
            }
            //修改每页的个数
            scope.jumpToPage = function () {
                scope.jumpPageNum = scope.jumpPageNum.replace(/[^0-9]/g, '');
                if (scope.jumpPageNum !== '') {
                    scope.conf.currentPage = parseInt(scope.jumpPageNum);
                }
            }
            //修改每页显示的条数,
            scope.changePageSize = function () {
                scope.conf.pageSize = scope.itemsPerPage;
                scope.conf.currentPage = 1;
            }

            scope.$watch(function () {
                var newValue = scope.conf.currentPage + ' ' + scope.conf.pageSize;
                return newValue;
            }, function (newValue, oldValue) {
                //console.info('newValue:' + newValue + ",oldValue:" + oldValue);
                if (newValue != oldValue) {
                    scope.conf.getPageData(scope.ajaxOption);
                }
            });

            //获取分页数据
            scope.ajaxOption = null;
            scope.conf.getPageData = function (options) {
                if (options == undefined)
                    return;
                //执行Ajax获取分页数据
                var defaults = {
                    url: '',
                    data: {},
                    success: function () { }
                }
                var _opts = $.extend({}, defaults, options);
                //存储传来的参数
                scope.ajaxOption = _opts;
                //结果
                var _data = {
                    //pageIndex: scope.conf.currentPage > getPageCount ()? 1 : scope.conf.currentPage,
                    pageIndex: scope.conf.currentPage,
                    pageSize: scope.conf.pageSize
                };

                _data = $.extend({}, _data, _opts.data);
                console.info(_data);
               // console.info(getPageCount());
                $.ajax({
                    url: _opts.url,
                    data: _data,
                    success: function (data) {
                        //获取成功
                        if (_opts.success) {
                            _opts.success(data.items);
                        }
                        scope.$apply(function () {
                            scope.conf.totalCount = data.count;
                            //执行分页控件处理
                            getPagination();
                            // scope.isShowNone = data.count <= 0;
                        });
                    },
                    error: function (data) {
                        if (_opts.error) {
                            _opts.error(data);
                        }
                        console.info("ajax访问失败：" + data);
                    }
                });
            }
        
            //判断值在指定数组总是否存在
            function isFromArray(target, array) {
                for (var i = 0; i < array.length; i++) {
                    var item = array[i];
                    if (item == target)
                        return true;
                }
                return false;
            }
        }
    }
});
