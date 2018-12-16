**通用约定**

1. 域名**HOST**代表**xxxxxxx.com**
2. 所有的**SALT**代表字符串**exam.com.cn**(加密认证时使用)

## 目录

1. 接口文档

2. 下载文档

3. 数据源配置

4. 指标配置

5. 修改或保存数据源配置

6. 信息树

7. 信息云

8. 查询一条数据源

9. 数据统计

10. 首页数据来源

11. 首页查询

12. 我的申请

13. 我的申请查看

14. 查询数据项

15. 指标配置编辑保存

16. 查询服务

17. 查询单个资源信息

18. 申请全信息

    ------



### 1.接口文档

```http
http://localhost:28080/ssk/if/selectInterface
```

#### 返回值

- data 对象数组
- id 编号id
- name 接口名
- url 接口下载地址
- pageUrl 页面展示地址
- gmtCreate 主动创建（13位时间戳）
- gmtModified 被动更新（13位时间戳）
- isDeleted 是否删除 

#### example

```json
{
    "code": 200,
    "data": [
        {
            "gmtModified": null,
            "isDeleted": false,
            "name": "API",
            "pageUrl": "https://tyhy.hzxc.gov.cn:28443/ssk/upload/API.html",
            "id": 1,
            "gmtCreate": 1543321662961,
            "url": "https://tyhy.hzxc.gov.cn:28443/ssk/upload/API.md"
        },
        {
            "gmtModified": null,
            "isDeleted": false,
            "name": "登入",
            "pageUrl": "https://tyhy.hzxc.gov.cn:28443/ssk/upload/登入.html",
            "id": 2,
            "gmtCreate": 1543321951040,
            "url": "https://tyhy.hzxc.gov.cn:28443/ssk/upload/登入.md"
        },
        {
            "gmtModified": null,
            "isDeleted": false,
            "name": "下载",
            "pageUrl": "https://tyhy.hzxc.gov.cn:28443/ssk/upload/下载.html",
            "id": 4,
            "gmtCreate": 1543322074434,
            "url": "https://tyhy.hzxc.gov.cn:28443/ssk/upload/下载.md"
        }
    ],
    "message": "Success"
}
```

### 2.下载文档

```http
http://localhost:28080/ssk/if/download
```

### 3.数据源配置

```http
http://localhost:28080/ssk/index/selectIndexOrDatasource
```

#### post参数

| 名称       | 类型   | 是否必填 | 说明                                  |
| ---------- | ------ | -------- | ------------------------------------- |
| pageNumber | int    | 是       | 页码                                  |
| pageSize   | int    | 是       | 条数                                  |
| type       | int    | 是       | 类型，人口填：1，企业填：2，房屋填：3 |
| name       | String | 是       | 数据源配置填：source                  |



#### 返回值

- data 对象数组
- totalRow总行数
- pageNumber页码
- firstPage是否是第一页
- lastPage是否最后一页
- totalPage总页数
- pageSize条数
- list对象数组
- ename（数据源配置返回值）名称
- oldName（数据源配置返回值）原始部门
- busnum（数据源配置返回值）业务数据总量
- name（数据源配置返回值）来源部门
- colnum（数据源配置返回值）业务字段数
- id（数据源配置返回值）序号
- typenum（数据源配置返回值）类型总量（人数总量/企业总量/房屋总量）
- type（数据源配置返回值）类型（人口：1，企业：2，房屋：3）
- remarks（数据源配置返回值）数据源描述
- accessTime（数据源配置返回值）接入时间

#### example（数据源配置）

```json
{
    "code": 200,
    "data": {
        "totalRow": 2,
        "pageNumber": 1,
        "lastPage": true,
        "firstPage": true,
        "totalPage": 1,
        "pageSize": 5,
        "list": [
            {
                "ename": "grid_database_peoplepub",
                "oldName": "四个平台",
                "busnum": 530608,
                "name": "四个平台",
                "colnum": null,
                "id": 3,
                "typenum": 530608,
                "type": 3,
                "remarks": null,
                "accessTime": "2018-03-02"
            },
            {
                "ename": "grid_database_peoplepri",
                "oldName": "四个平台",
                "busnum": 568162,
                "name": "四个平台",
                "colnum": null,
                "id": 4,
                "typenum": 568162,
                "type": 3,
                "remarks": null,
                "accessTime": "2018-03-02"
            }
        ]
    },
    "message": "Success"
}
```

### 4.指标配置

```http
http://localhost:28080/ssk/index/selectIndexOrDatasource
```

#### post参数

| 名称 | 类型   | 是否必填 | 说明                                  |
| ---- | ------ | -------- | ------------------------------------- |
| type | int    | 是       | 类型，人口填：1，企业填：2，房屋填：3 |
| name | String | 是       | 指标配置填：index                     |



#### 返回值

- data 对象数组
- ename表英文名，字段英文名
- islast是否最后一层指标
- children子集对象数组
- name名称
- pid父id
- id编号id
- cid子id
- level指标等级
- type类型（1.人口2.企业3.房屋）

#### example（指标配置）

```json
{
    "code": 200,
    "data": [
        {
            "ename": null,
            "islast": false,
            "level": 1,
            "children": [
                {
                    "ename": "rkdjinfo",
                    "islast": true,
                    "level": 2,
                    "children": [],
                    "name": "登记信息",
                    "pid": "1",
                    "id": 6,
                    "type": 1,
                    "cid": "6"
                },
                {
                    "ename": "rksltzinfo",
                    "islast": true,
                    "level": 2,
                    "children": [],
                    "name": "生理体征信息",
                    "pid": "1",
                    "id": 7,
                    "type": 1,
                    "cid": "7"
                },
                        .....
 ],
    "message": "Success"
}                        
```

### 5.修改或保存数据源配置

```http
http://localhost:28080/ssk/index/selectIndexOrDatasource
```

#### post参数，支持格式JOSN   

| 名称       | 类型   | 是否必填 | 说明                                       |
| ---------- | ------ | -------- | ------------------------------------------ |
| method     | String | 是       | 修改填：upate，新增填：save                |
| ename      | String | 是       | 名称                                       |
| oldName    | String | 是       | 原始部门                                   |
| busnum     | int    | 是       | 业务数据总量                               |
| name       | String | 是       | 来源部门                                   |
| colnum     | int    | 是       | 业务字段数                                 |
| id         | int    | 选填     | 序号（method为update时必填，为save是选填） |
| typenum    | int    | 是       | 类型总量（人数总量/企业总量/房屋总量）     |
| type       | int    | 是       | 类型（人口：1，企业：2，房屋：3）          |
| remarks    | String | 是       | 数据源描述                                 |
| accessTime | String | 是       | 接入时间                                   |

```
{              
	            "method":"save",
                "ename": "gat_xx_czrk_qm第一次添加",
                "oldName": "全省常住人口信息表第一次添加",
                "busnum": 100,
                "name": "省交换平台第一次修改第一次添加",
                "colnum": 100,
                "typenum": 100,
                "type": 1,
                "remarks": "1234",
                "accessTime": "2018-03-03"
            
}
```



#### 返回值

- data boolean类型表示是否添加或修改成功

```json
{
    "code": 200,
    "data": true,
    "message": "Success"
}
```

### 6.信息树

```http
http://localhost:28080/ssk/tree/msgTree
```

#### post参数

| 名称 | 类型 | 是否必填 | 说明                                  |
| ---- | ---- | -------- | ------------------------------------- |
| type | int  | 是       | 类型，人口填：1，企业填：2，房屋填：3 |



#### 返回值

- data 对象数组
- ename表英文名，字段英文名
- islast是否最后一层指标
- children子集对象数组
- name名称
- pid父id
- id编号id
- cid子id
- level指标等级

#### example

```json
{
    "code": 200,
    "data": [
        {
            "ename": null,
            "islast": false,
            "level": 1,
            "children": [
                {
                    "ename": "rkdjinfo",
                    "islast": true,
                    "level": 2,
                    "children": [
                        {
                            "ename": "id",
                            "islast": false,
                            "level": 4,
                            "children": [],
                            "name": "唯一主键",
                            "pid": "6",
                            "id": 118,
                            "cid": "118"
                        },
                        .....
 ],
    "message": "Success"
}                        
```

### 7.信息云

```http
http://localhost:28080/ssk/tree/cloud
```

#### post参数

| 名称 | 类型 | 是否必填 | 说明                                  |
| ---- | ---- | -------- | ------------------------------------- |
| type | int  | 是       | 类型，人口填：1，企业填：2，房屋填：3 |



#### 返回值

- data 对象数组

- num信息云数量

- name名称


#### example

```json
{
    "code": 200,
    "data": [
        {
            "num": 485899,
            "name": "姓名"
        },
        {
            "num": 42794,
            "name": "曾用名"
        },
        {
            "num": 485899,
            "name": "性别"
        },
        {
            "num": 485899,
            "name": "民族"
        },
        ......
        ],
        "message": "Success"
}
                
```

### 8.查询一条数据源

```http
http://localhost:28080/ssk/index/selectDatasourceById
```

#### post参数

| 名称 | 类型 | 是否必填 | 说明   |
| ---- | ---- | -------- | ------ |
| id   | int  | 是       | 编号id |

#### 返回值

- data 对象数组

- ename（数据源配置返回值）名称

- oldName（数据源配置返回值）原始部门

- busnum（数据源配置返回值）业务数据总量

- name（数据源配置返回值）来源部门

- colnum（数据源配置返回值）业务字段数

- id（数据源配置返回值）序号

- typenum（数据源配置返回值）类型总量（人数总量/企业总量/房屋总量）

- type（数据源配置返回值）类型（人口：1，企业：2，房屋：3）

- remarks（数据源配置返回值）数据源描述

- accessTime（数据源配置返回值）接入时间


#### example

```json
{
    "code": 200,
    "data": {
        "ename": "gat_xx_czrk_qm第一次修改",
        "oldName": "全省常住人口信息表第一次修改",
        "busnum": 657886,
        "name": "省交换平台第一次修改",
        "colnum": 0,
        "id": 1,
        "typenum": 657886,
        "type": 1,
        "remarks": " ",
        "accessTime": "2018-03-03"
    },
    "message": "Success"
}
                
```

### 9.数据统计

```http
http://localhost:28080/ssk/base/selectXcCount
```

#### 返回值

- data 对象数组
- type 类型1.实有人口数2.实有企业数3.实有房屋数4.平台信息总量
- num 数量

#### example

```json
{
    "code": 200,
    "data": [
        {
            "num": 734871,
            "type": 1
        },
        {
            "num": 54688,
            "type": 2
        },
        {
            "num": 0,
            "type": 3
        },
        {
            "num": 789559,
            "type": 4
        }
    ],
    "message": "Success"
}
                
```

###  10.首页数据来源

```http
http://localhost:28080/ssk/base/selectDatasource
```

#### post参数

| 名称 | 类型 | 是否必填 | 说明                            |
| ---- | ---- | -------- | ------------------------------- |
| type | int  | 是       | 人口填：1，企业填：2，房屋填：3 |

#### 返回值

- data 对象数组
- name（数据源配置返回值）来源部门
- typenum（数据源配置返回值）类型总量（人数总量/企业总量/房屋总量）

#### example

```json
{
    "code": 200,
    "data": [
        {
            "name": "省交换平台",
            "typenum": 657886
        },
        {
            "name": "市交换平台",
            "typenum": 100
        },
        {
            "name": "图影红云",
            "typenum": 1
        },
        {
            "name": "积分信息系统",
            "typenum": 111
        },
        {
            "name": "知识分享系统",
            "typenum": 11
        },
        {
            "name": "OA",
            "typenum": 1111
        }
    ],
    "message": "Success"
}
                
```

### 11.首页查询

```http
http://localhost:28080/ssk/base/selectLog
```

#### post参数

| 名称       | 类型 | 是否必填 | 说明 |
| ---------- | ---- | -------- | ---- |
| pageNumber | int  | 是       | 页码 |
| pageSize   | int  | 是       | 条数 |



#### 返回值

- data 对象数组
- totalRow总行数
- pageNumber页码
- firstPage是否是第一页
- lastPage是否最后一页
- totalPage总页数
- pageSize条数
- list对象数组
- trueName用户名
- crateTime创建时间
- log日志

#### example

```json
{
    "code": 200,
    "data": {
        "totalRow": 209,
        "pageNumber": 1,
        "firstPage": true,
        "lastPage": false,
        "totalPage": 21,
        "pageSize": 10,
        "list": [
            {
                "trueName": "方升群",
                "crateTime": "2018-12-10 16:13:24",
                "log": "房屋信息"
            },
            {
                "trueName": "方升群",
                "crateTime": "2018-12-10 16:13:23",
                "log": "房屋信息"
            },
            {
                "trueName": "方升群",
                "crateTime": "2018-12-10 16:13:23",
                "log": "房屋信息"
            },
            {
                "trueName": "方升群",
                "crateTime": "2018-12-10 16:13:23",
                "log": "房屋信息"
            },
            {
                "trueName": "方升群",
                "crateTime": "2018-12-10 16:13:23",
                "log": "房屋信息"
            },
            {
                "trueName": "方升群",
                "crateTime": "2018-12-10 16:13:23",
                "log": "房屋信息"
            },
            {
                "trueName": "方升群",
                "crateTime": "2018-12-10 16:13:23",
                "log": "房屋信息"
            },
            {
                "trueName": "方升群",
                "crateTime": "2018-12-10 16:13:23",
                "log": "房屋信息"
            },
            {
                "trueName": "方升群",
                "crateTime": "2018-12-10 16:13:23",
                "log": "房屋信息"
            },
            {
                "trueName": "方升群",
                "crateTime": "2018-12-10 16:13:23",
                "log": "房屋信息"
            }
        ]
    },
    "message": "Success"
}
```

### 12.我的申请

```http
http://localhost:28080/ssk/apply/selectApply
```

#### post参数

| 名称       | 类型 | 是否必填 | 说明 |
| ---------- | ---- | -------- | ---- |
| pageNumber | int  | 是       | 页码 |
| pageSize   | int  | 是       | 条数 |



#### 返回值

- data 对象数组
- totalRow总行数
- pageNumber页码
- firstPage是否是第一页
- lastPage是否最后一页
- totalPage总页数
- pageSize条数
- list对象数组
- trueName申请人
- crateTime申请时间
- deptName申请部门
- reason申请理由
- typeName状态
- resources资源类型
- id申请编号

#### example

```json
{
    "code": 200,
    "data": {
        "totalRow": 211,
        "pageNumber": 1,
        "firstPage": true,
        "lastPage": false,
        "totalPage": 43,
        "pageSize": 5,
        "list": [
            {
                "trueName": "方升群",
                "deptName": "技术组",
                "reason": "工作需要",
                "crateTime": "2018-12-11 10:08:32",
                "typeName": "已通过",
                "resources": "房屋信息",
                "id": 211
            },
            {
                "trueName": "方升群",
                "deptName": "技术组",
                "reason": "工作需要",
                "crateTime": "2018-12-11 10:08:32",
                "typeName": "被驳回",
                "resources": "房屋信息",
                "id": 210
            },
            {
                "trueName": "方升群",
                "deptName": "技术组",
                "reason": "工作需要",
                "crateTime": "2018-12-11 10:08:32",
                "typeName": "已通过",
                "resources": "房屋信息",
                "id": 209
            },
            {
                "trueName": "方升群",
                "deptName": "技术组",
                "reason": "工作需要",
                "crateTime": "2018-12-11 10:08:32",
                "typeName": "被驳回",
                "resources": "房屋信息",
                "id": 208
            },
            {
                "trueName": "方升群",
                "deptName": "技术组",
                "reason": "工作需要",
                "crateTime": "2018-12-11 10:08:32",
                "typeName": "被驳回",
                "resources": "房屋信息",
                "id": 207
            }
        ]
    },
    "message": "Success"
}
```

### 13.我的申请查看

```http
http://localhost:28080/ssk/apply/selectOneApply
```

#### post参数

| 名称 | 类型 | 是否必填 | 说明     |
| ---- | ---- | -------- | -------- |
| id   | int  | 是       | 申请编号 |



#### 返回值

- data 对象
- reason申请理由
- resources资源类型

#### example

```json
{
    "code": 200,
    "data": {
        "reason": "工作需要",
        "resources": "人口登记信息"
    },
    "message": "Success"
}
```

### 14.查询指标是否存在

```http
http://localhost:28080/ssk/index/isExist
```

#### post参数

| 名称 | 类型   | 是否必填 | 说明     |
| ---- | ------ | -------- | -------- |
| name | String | 是       | 指标名称 |



#### 返回值

- data boolean类型：true存在，false不存在。

#### example

```json
{
    "code": 200,
    "data": true,
    "message": "Success"
}
```

### 14.查询数据项

```http
http://localhost:28080/ssk/index/selectDataItem
```

#### post参数

| 名称 | 类型   | 是否必填 | 说明     |
| ---- | ------ | -------- | -------- |
| name | String | 是       | 指标名称 |



#### 返回值

- data 对象数组
- column_name字段英文名
- column_comment字段中文名

#### example

```json
{
    "code": 200,
    "data": [
        {
            "column_name": "id",
            "column_comment": "唯一主键"
        },
        {
            "column_name": "name",
            "column_comment": "姓名"
        },
        {
            "column_name": "oldName",
            "column_comment": "曾用名"
        },
        {
            "column_name": "sex",
            "column_comment": "性别"
        },
        {
            "column_name": "race",
            "column_comment": "民族"
        },
        {
            "column_name": "cardType",
            "column_comment": "身份证件类型"
        },
        {
            "column_name": "cardNum",
            "column_comment": "身份证号码"
        },
        {
            "column_name": "politics",
            "column_comment": "政治面貌"
        },
        {
            "column_name": "CZRKZZ",
            "column_comment": "出生地"
        },
        {
            "column_name": "CZRKCSRQ",
            "column_comment": "出生日期"
        },
        {
            "column_name": "CZRKCSDGJ",
            "column_comment": "国籍"
        },
        {
            "column_name": "pic",
            "column_comment": "照片"
        },
        {
            "column_name": "religion",
            "column_comment": "宗教信仰"
        },
        {
            "column_name": "CZRKBYZK",
            "column_comment": "兵役状况"
        },
        {
            "column_name": "health",
            "column_comment": "健康状况"
        },
        {
            "column_name": "serviceType",
            "column_comment": "服务对象类型"
        },
        {
            "column_name": "isLd",
            "column_comment": "是否是流动人员"
        },
        {
            "column_name": "migrationReason",
            "column_comment": "流入原因"
        },
        {
            "column_name": "migrationTime",
            "column_comment": "流入时间"
        },
        {
            "column_name": "migrationSource",
            "column_comment": "流入来源"
        },
        {
            "column_name": "permit",
            "column_comment": "临时居住证号"
        },
        {
            "column_name": "economysource",
            "column_comment": "经济来源"
        },
        {
            "column_name": "ishouse",
            "column_comment": "有无住所"
        },
        {
            "column_name": "mainType",
            "column_comment": "骨干对象类型"
        },
        {
            "column_name": "personType",
            "column_comment": "人员类型"
        },
        {
            "column_name": "b_address",
            "column_comment": "楼栋地址"
        },
        {
            "column_name": "b_updatedate",
            "column_comment": "楼栋情况更新日期"
        },
        {
            "column_name": "unit_number",
            "column_comment": "单元号"
        },
        {
            "column_name": "checkin_date",
            "column_comment": "入住时间"
        },
        {
            "column_name": "r_createdate",
            "column_comment": "居民信息初次登记日期"
        },
        {
            "column_name": "r_updatedate",
            "column_comment": "居民信息最近更新日期"
        },
        {
            "column_name": "communityname",
            "column_comment": "社区名称"
        },
        {
            "column_name": "zhlx",
            "column_comment": "住户类型"
        },
        {
            "column_name": "lastPatrolTime",
            "column_comment": "最新走访时间"
        },
        {
            "column_name": "visitingtime",
            "column_comment": "走访时间"
        },
        {
            "column_name": "hasPatrol",
            "column_comment": "是否已走访"
        },
        {
            "column_name": "patrolLevel",
            "column_comment": "走访等级"
        },
        {
            "column_name": "manageType",
            "column_comment": "管控对象类型"
        },
        {
            "column_name": "operationTime",
            "column_comment": "操作时间"
        }
    ],
    "message": "Success"
}
```

### 15.指标配置编辑保存

```http
http://localhost:28080/ssk/index/editIndex
```

#### post参数

| 名称     | 类型    | 是否必填           | 说明                       |
| -------- | ------- | ------------------ | -------------------------- |
| ename    | String  | 必填               | 表英文名，字段英文名       |
| islast   | boolean | 必填               | 是否最后一层指标           |
| level    | int     | 必填               | 指标等级                   |
| children | list    | 必填               | 子集对象数组               |
| name     | String  | 必填               | 名称                       |
| pid      | String  | 必填(新增指标选填) | 父id                       |
| id       | long    | 必填(新增指标选填) | 编号id                     |
| type     | int     | 必填               | 类型（1.人口2.企业3.房屋） |
| cid      | String  | 必填(新增指标选填) | 子id                       |

```
[
        {
            "ename": null,
            "islast": false,
            "level": 1,
            "children": [
                {
                    "ename": "rkdjinfo",
                    "islast": true,
                    "level": 2,
                    "children": [],
                    "name": "登记信息",
                    "pid": "1",
                    "id": 6,
                    "type": 1,
                    "cid": "6"
                },
                {
                    "ename": "rksltzinfo",
                    "islast": true,
                    "level": 2,
                    "children": [],
                    "name": "生理体征信息",
                    "pid": "1",
                    "id": 7,
                    "type": 1,
                    "cid": "7"
                },
                ...
  ]
```



#### 返回值

- data 对象数组
- ename表英文名，字段英文名
- islast是否最后一层指标
- children子集对象数组
- name名称
- pid父id
- id编号id
- cid子id
- level指标等级

#### example

```json
{
    "code": 200,
    "data": "编辑成功",
    "message": "Success"
}                    
```

### 16.查询服务

```
http://localhost:28080/ssk/qs/selectQS
```

#### post参数

| 名称  | 类型   | 是否必填 | 说明                       |
| ----- | ------ | -------- | -------------------------- |
| type  | int    | 必填     | 类型（1.人口2.企业3.房屋） |
| value | String | 必填     | 查询值                     |

#### 返回值

- data 对象数组
- result 对象数组：查询内容（查询人口和企业的时候有）
- --values 对象数组
- ----ename 表英文名，字段英文名
- ----num 查询个数
- ----name 指标名称
- ----type 状态0、申（未申请）1、审（申请中）2、全（已通过）3、被驳回
- ----cid 子id
- --name一级指标
- rkdzxxinfo对象数组（人口地址信息）（房屋地址信息）（addressInfo地址信息，bzdz标准地址，dzCode地址编码，FWYT房屋用途，type地址类型）
- dh对象数组（导航）（查询人口和企业的时候有）
- --ename 表英文名，字段英文名
- --name 指标名称
- --cid 子id
- --islast是否最后一级
- fwxxbaseinfo对象数组（房屋地址信息）（addressInfo地址信息，bzdz标准地址，dzCode地址编码，FWYT房屋用途，type地址类型）
- qybaseinfo对象数组（企业信息：QYMC企业名称，ZCH注册号，UNISCID统一社会信用代码）（查询人口和企业的时候有）
- rkbaseinfo对象数组（人口信息：cardNum身份证号，name姓名，CZRKCSRQ出生日期，CZRKZZ住址，CZRKMZ民族，CZRKXB性别）（查询企业或房屋的时候有）
- jcxx对象数组（基础信息）（人口信息：cardNum身份证号，name姓名，CZRKCSRQ出生日期，CZRKZZ住址，CZRKMZ民族，CZRKXB性别）（企业信息：QYMC企业名称，ZCH注册号，UNISCID统一社会信用代码）（查询人口或企业信息的时候有）
- qydzxxinfo对象数组（企业地址信息）（房屋地址信息）（addressInfo地址信息，bzdz标准地址，dzCode地址编码，FWYT房屋用途，type地址类型）
- qydzxxinfo_qy对象数组（企业地址信息对应的企业信息）
- qydzxxinfo_rk对象数组（企业地址信息对应的人口信息）
- fwxxbaseinfo_rk对象数组（房屋地址信息对应的人口信息）
- fwxxbaseinfo_qy对象数组（房屋地址信息对应的企业信息）
- rkdzxxinfo_qy对象数组（人口地址信息对应的企业信息）
- rkdzxxinfo_rk对象数组（人口地址信息对应的人口信息）

#### example

```
{
    "code": 200,
    "data": {
        "result": [
            {
                "values": [
                    {
                        "ename": "rkdjinfo",
                        "num": 0,
                        "name": "登记信息",
                        "type": 1,
                        "cid": "6"
                    },
                   ......
                    {
                        "ename": null,
                        "num": 0,
                        "name": "联系信息测试数据",
                        "type": 0,
                        "cid": "3324"
                    }
                ],
                "name": "基本信息"
            },
            
            {
                "values": [
                    {
                        "ename": null,
                        "num": 0,
                        "name": "测试三级1",
                        "type": 0,
                        "cid": "3328"
                    },
                    .........
                    {
                        "ename": null,
                        "num": 0,
                        "name": "6",
                        "type": 0,
                        "cid": "3337"
                    }
                ],
                "name": "测试"
            }
           
        ],
        "rkdzxxinfo": [],
        "dh": [
            {
                "ename": null,
                "islast": false,
                "name": "基本信息",
                "cid": "1"
            },
            .......
            {
                "ename": null,
                "islast": false,
                "name": "测试测试",
                "cid": "3360"
            }
        ],
        "fwxxbaseinfo": [],
        "qybaseinfo": [],
        "jcxx": null,
        "qydzxxinfo": []
    },
    "message": "Success"
}
```

### 17.查询单个资源信息

```
http://localhost:28080/ssk/qs/selectOneQS
```

#### post参数

| 名称  | 类型   | 是否必填 | 说明                       |
| ----- | ------ | -------- | -------------------------- |
| cid   | String | 必填     | 类型（1.人口2.企业3.房屋） |
| value | String | 必填     | 查询值                     |

#### 返回值

- data 对象
- Header对象数组（表头）
- --column_name字段英文名
- --column_comment字段中文名
- table对象数组（数据）

#### example

```
{
    "code": 200,
    "data": {
        "Header": [
            {
                "column_name": "id",
                "column_comment": "唯一主键"
            },
            {
                "column_name": "name",
                "column_comment": "姓名"
            },
            ......
            {
                "column_name": "hasPatrol",
                "column_comment": "是否已走访"
            },
            {
                "column_name": "patrolLevel",
                "column_comment": "走访等级"
            },
            {
                "column_name": "manageType",
                "column_comment": "管控对象类型"
            },
            {
                "column_name": "operationTime",
                "column_comment": "操作时间"
            }
        ],
        "table": []
    },
    "message": "Success"
}
```

### 18.申请全信息

```
http://localhost:28080/ssk/qs/apply
```

#### post参数

| 名称   | 类型   | 是否必填 | 说明        |
| ------ | ------ | -------- | ----------- |
| cid    | String | 必填     | 指标配置cid |
| reason | String | 必填     | 查询值      |

#### 返回值

- data 申请成功

#### example

```
{
    "code": 200,
    "data": "申请成功",
    "message": "Success"
} 
```

















###  