/** 定义控制器层 */
app.controller('itemCatController', function($scope, $controller, baseService){

    /** 指定继承baseController */
    $controller('baseController',{$scope:$scope});

    /** 查询条件对象 */
    $scope.searchEntity = {};
    /** 分页查询(查询条件) */
    $scope.search = function(page, rows){
        baseService.findByPage("/itemCat/findByPage", page,
			rows, $scope.searchEntity)
            .then(function(response){
                /** 获取分页查询结果 */
                $scope.dataList = response.data.rows;
                /** 更新分页总记录数 */
                $scope.paginationConf.totalItems = response.data.total;
            });
    };
    /*根据上下级列表*/
    $scope.findItemCatByParentId=function (parentId) {
        baseService.sendGet("/itemCat/findItemCatByParentId?parentId="+parentId).then(function (response) {
            $scope.dataList=response.data;
        });
    };

    /** 添加或修改 */
    $scope.saveOrUpdate = function(){
        var url = "save";
        if ($scope.entity.id){
            url = "update";
        }
        /** 发送post请求 */
        baseService.sendPost("/itemCat/" + url, $scope.entity)
            .then(function(response){
                if (response.data){
                    /** 重新加载数据 */
                    $scope.reload();
                }else{
                    alert("操作失败！");
                }
            });
    };

    /** 显示修改 */
    $scope.show = function(entity){
       /** 把json对象转化成一个新的json对象 */
       $scope.entity = JSON.parse(JSON.stringify(entity));
    };

    /** 批量删除 */
    $scope.delete = function(){
        if ($scope.ids.length > 0){
            baseService.deleteById("/itemCat/delete", $scope.ids)
                .then(function(response){
                    if (response.data){
                        /** 重新加载数据 */
                        $scope.reload();
                    }else{
                        alert("删除失败！");
                    }
                });
        }else{
            alert("请选择要删除的记录！");
        }
    };
    //定义级别的变量
    $scope.grade=1;
    //查询下一级
    $scope.selectList=function (entity,grade) {
        if(grade==1){
            $scope.itemcat_1=null;
            $scope.itemcat_2=null;
        }
        $scope.grade=grade;
        if(grade==2){
        //一级分类
        $scope.itemcat_1=entity;}
        if(grade==3){
        //一级分类
        $scope.itemcat_2=entity;}

        //调用查询方法
        $scope.findItemCatByParentId(entity.id);
    }
});