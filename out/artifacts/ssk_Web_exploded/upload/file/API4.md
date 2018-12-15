**通用约定**

1. 域名**HOST**代表**xxxxxxx.com**
2. 所有的**SALT**代表字符串**exam.com.cn**(加密认证时使用)

## 目录

#### 账号

1. 接口文档

2. 登陆

3. 注册

4. 退出

5. 找回密码(设置密码)

6. 发送验证码

7. 验证手机号(包括修改手机号码)

   ------



#### 1.接口文档

```http
http://localhost:8080/ssk/if/selectInterface
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

#### 2.时间同步

```http
https://exam.com/system/get_system_time
```

#### post参数

| 变量名 | 类型   | 是否必填 | 注释     |
| ------ | ------ | -------- | -------- |
| uuid   | String | 是       | 设备编号 |
|        |        |          |          |
|        |        |          |          |

#### 返回值

- data 服务器系统的时间（13位时间戳）

#### example

```json
{
    "code": 200,
    "msg": "success",
    "data": 1400000000000
}
```

## 