**ͨ��Լ��**

1. ����**HOST**����**xxxxxxx.com**
2. ���е�**SALT**�����ַ���**exam.com.cn**(������֤ʱʹ��)

## Ŀ¼

1. �ӿ��ĵ�

2. �����ĵ�

3. ����Դ����

4. ָ������

5. �޸Ļ򱣴�����Դ����

6. ��Ϣ��

7. ��Ϣ��

8. ��ѯһ������Դ

9. ����ͳ��

10. ��ҳ������Դ

11. ��ҳ��ѯ

12. �ҵ�����

13. �ҵ�����鿴

14. ��ѯ������

15. ָ�����ñ༭����

16. ��ѯ����

17. ��ѯ������Դ��Ϣ

18. ����ȫ��Ϣ

    ------



### 1.�ӿ��ĵ�

```http
http://localhost:28080/ssk/if/selectInterface
```

#### ����ֵ

- data ��������
- id ���id
- name �ӿ���
- url �ӿ����ص�ַ
- pageUrl ҳ��չʾ��ַ
- gmtCreate ����������13λʱ�����
- gmtModified �������£�13λʱ�����
- isDeleted �Ƿ�ɾ�� 

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
            "name": "����",
            "pageUrl": "https://tyhy.hzxc.gov.cn:28443/ssk/upload/����.html",
            "id": 2,
            "gmtCreate": 1543321951040,
            "url": "https://tyhy.hzxc.gov.cn:28443/ssk/upload/����.md"
        },
        {
            "gmtModified": null,
            "isDeleted": false,
            "name": "����",
            "pageUrl": "https://tyhy.hzxc.gov.cn:28443/ssk/upload/����.html",
            "id": 4,
            "gmtCreate": 1543322074434,
            "url": "https://tyhy.hzxc.gov.cn:28443/ssk/upload/����.md"
        }
    ],
    "message": "Success"
}
```

### 2.�����ĵ�

```http
http://localhost:28080/ssk/if/download
```

### 3.����Դ����

```http
http://localhost:28080/ssk/index/selectIndexOrDatasource
```

#### post����

| ����       | ����   | �Ƿ���� | ˵��                                  |
| ---------- | ------ | -------- | ------------------------------------- |
| pageNumber | int    | ��       | ҳ��                                  |
| pageSize   | int    | ��       | ����                                  |
| type       | int    | ��       | ���ͣ��˿��1����ҵ�2�������3 |
| name       | String | ��       | ����Դ�����source                  |



#### ����ֵ

- data ��������
- totalRow������
- pageNumberҳ��
- firstPage�Ƿ��ǵ�һҳ
- lastPage�Ƿ����һҳ
- totalPage��ҳ��
- pageSize����
- list��������
- ename������Դ���÷���ֵ������
- oldName������Դ���÷���ֵ��ԭʼ����
- busnum������Դ���÷���ֵ��ҵ����������
- name������Դ���÷���ֵ����Դ����
- colnum������Դ���÷���ֵ��ҵ���ֶ���
- id������Դ���÷���ֵ�����
- typenum������Դ���÷���ֵ��������������������/��ҵ����/����������
- type������Դ���÷���ֵ�����ͣ��˿ڣ�1����ҵ��2�����ݣ�3��
- remarks������Դ���÷���ֵ������Դ����
- accessTime������Դ���÷���ֵ������ʱ��

#### example������Դ���ã�

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
                "oldName": "�ĸ�ƽ̨",
                "busnum": 530608,
                "name": "�ĸ�ƽ̨",
                "colnum": null,
                "id": 3,
                "typenum": 530608,
                "type": 3,
                "remarks": null,
                "accessTime": "2018-03-02"
            },
            {
                "ename": "grid_database_peoplepri",
                "oldName": "�ĸ�ƽ̨",
                "busnum": 568162,
                "name": "�ĸ�ƽ̨",
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

### 4.ָ������

```http
http://localhost:28080/ssk/index/selectIndexOrDatasource
```

#### post����

| ���� | ����   | �Ƿ���� | ˵��                                  |
| ---- | ------ | -------- | ------------------------------------- |
| type | int    | ��       | ���ͣ��˿��1����ҵ�2�������3 |
| name | String | ��       | ָ�������index                     |



#### ����ֵ

- data ��������
- ename��Ӣ�������ֶ�Ӣ����
- islast�Ƿ����һ��ָ��
- children�Ӽ���������
- name����
- pid��id
- id���id
- cid��id
- levelָ��ȼ�
- type���ͣ�1.�˿�2.��ҵ3.���ݣ�

#### example��ָ�����ã�

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
                    "name": "�Ǽ���Ϣ",
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
                    "name": "����������Ϣ",
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

### 5.�޸Ļ򱣴�����Դ����

```http
http://localhost:28080/ssk/index/selectIndexOrDatasource
```

#### post������֧�ָ�ʽJOSN   

| ����       | ����   | �Ƿ���� | ˵��                                       |
| ---------- | ------ | -------- | ------------------------------------------ |
| method     | String | ��       | �޸��upate�������save                |
| ename      | String | ��       | ����                                       |
| oldName    | String | ��       | ԭʼ����                                   |
| busnum     | int    | ��       | ҵ����������                               |
| name       | String | ��       | ��Դ����                                   |
| colnum     | int    | ��       | ҵ���ֶ���                                 |
| id         | int    | ѡ��     | ��ţ�methodΪupdateʱ���Ϊsave��ѡ� |
| typenum    | int    | ��       | ������������������/��ҵ����/����������     |
| type       | int    | ��       | ���ͣ��˿ڣ�1����ҵ��2�����ݣ�3��          |
| remarks    | String | ��       | ����Դ����                                 |
| accessTime | String | ��       | ����ʱ��                                   |

```
{              
	            "method":"save",
                "ename": "gat_xx_czrk_qm��һ�����",
                "oldName": "ȫʡ��ס�˿���Ϣ���һ�����",
                "busnum": 100,
                "name": "ʡ����ƽ̨��һ���޸ĵ�һ�����",
                "colnum": 100,
                "typenum": 100,
                "type": 1,
                "remarks": "1234",
                "accessTime": "2018-03-03"
            
}
```



#### ����ֵ

- data boolean���ͱ�ʾ�Ƿ���ӻ��޸ĳɹ�

```json
{
    "code": 200,
    "data": true,
    "message": "Success"
}
```

### 6.��Ϣ��

```http
http://localhost:28080/ssk/tree/msgTree
```

#### post����

| ���� | ���� | �Ƿ���� | ˵��                                  |
| ---- | ---- | -------- | ------------------------------------- |
| type | int  | ��       | ���ͣ��˿��1����ҵ�2�������3 |



#### ����ֵ

- data ��������
- ename��Ӣ�������ֶ�Ӣ����
- islast�Ƿ����һ��ָ��
- children�Ӽ���������
- name����
- pid��id
- id���id
- cid��id
- levelָ��ȼ�

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
                            "name": "Ψһ����",
                            "pid": "6",
                            "id": 118,
                            "cid": "118"
                        },
                        .....
 ],
    "message": "Success"
}                        
```

### 7.��Ϣ��

```http
http://localhost:28080/ssk/tree/cloud
```

#### post����

| ���� | ���� | �Ƿ���� | ˵��                                  |
| ---- | ---- | -------- | ------------------------------------- |
| type | int  | ��       | ���ͣ��˿��1����ҵ�2�������3 |



#### ����ֵ

- data ��������

- num��Ϣ������

- name����


#### example

```json
{
    "code": 200,
    "data": [
        {
            "num": 485899,
            "name": "����"
        },
        {
            "num": 42794,
            "name": "������"
        },
        {
            "num": 485899,
            "name": "�Ա�"
        },
        {
            "num": 485899,
            "name": "����"
        },
        ......
        ],
        "message": "Success"
}
                
```

### 8.��ѯһ������Դ

```http
http://localhost:28080/ssk/index/selectDatasourceById
```

#### post����

| ���� | ���� | �Ƿ���� | ˵��   |
| ---- | ---- | -------- | ------ |
| id   | int  | ��       | ���id |

#### ����ֵ

- data ��������

- ename������Դ���÷���ֵ������

- oldName������Դ���÷���ֵ��ԭʼ����

- busnum������Դ���÷���ֵ��ҵ����������

- name������Դ���÷���ֵ����Դ����

- colnum������Դ���÷���ֵ��ҵ���ֶ���

- id������Դ���÷���ֵ�����

- typenum������Դ���÷���ֵ��������������������/��ҵ����/����������

- type������Դ���÷���ֵ�����ͣ��˿ڣ�1����ҵ��2�����ݣ�3��

- remarks������Դ���÷���ֵ������Դ����

- accessTime������Դ���÷���ֵ������ʱ��


#### example

```json
{
    "code": 200,
    "data": {
        "ename": "gat_xx_czrk_qm��һ���޸�",
        "oldName": "ȫʡ��ס�˿���Ϣ���һ���޸�",
        "busnum": 657886,
        "name": "ʡ����ƽ̨��һ���޸�",
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

### 9.����ͳ��

```http
http://localhost:28080/ssk/base/selectXcCount
```

#### ����ֵ

- data ��������
- type ����1.ʵ���˿���2.ʵ����ҵ��3.ʵ�з�����4.ƽ̨��Ϣ����
- num ����

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

###  10.��ҳ������Դ

```http
http://localhost:28080/ssk/base/selectDatasource
```

#### post����

| ���� | ���� | �Ƿ���� | ˵��                            |
| ---- | ---- | -------- | ------------------------------- |
| type | int  | ��       | �˿��1����ҵ�2�������3 |

#### ����ֵ

- data ��������
- name������Դ���÷���ֵ����Դ����
- typenum������Դ���÷���ֵ��������������������/��ҵ����/����������

#### example

```json
{
    "code": 200,
    "data": [
        {
            "name": "ʡ����ƽ̨",
            "typenum": 657886
        },
        {
            "name": "�н���ƽ̨",
            "typenum": 100
        },
        {
            "name": "ͼӰ����",
            "typenum": 1
        },
        {
            "name": "������Ϣϵͳ",
            "typenum": 111
        },
        {
            "name": "֪ʶ����ϵͳ",
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

### 11.��ҳ��ѯ

```http
http://localhost:28080/ssk/base/selectLog
```

#### post����

| ����       | ���� | �Ƿ���� | ˵�� |
| ---------- | ---- | -------- | ---- |
| pageNumber | int  | ��       | ҳ�� |
| pageSize   | int  | ��       | ���� |



#### ����ֵ

- data ��������
- totalRow������
- pageNumberҳ��
- firstPage�Ƿ��ǵ�һҳ
- lastPage�Ƿ����һҳ
- totalPage��ҳ��
- pageSize����
- list��������
- trueName�û���
- crateTime����ʱ��
- log��־

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
                "trueName": "����Ⱥ",
                "crateTime": "2018-12-10 16:13:24",
                "log": "������Ϣ"
            },
            {
                "trueName": "����Ⱥ",
                "crateTime": "2018-12-10 16:13:23",
                "log": "������Ϣ"
            },
            {
                "trueName": "����Ⱥ",
                "crateTime": "2018-12-10 16:13:23",
                "log": "������Ϣ"
            },
            {
                "trueName": "����Ⱥ",
                "crateTime": "2018-12-10 16:13:23",
                "log": "������Ϣ"
            },
            {
                "trueName": "����Ⱥ",
                "crateTime": "2018-12-10 16:13:23",
                "log": "������Ϣ"
            },
            {
                "trueName": "����Ⱥ",
                "crateTime": "2018-12-10 16:13:23",
                "log": "������Ϣ"
            },
            {
                "trueName": "����Ⱥ",
                "crateTime": "2018-12-10 16:13:23",
                "log": "������Ϣ"
            },
            {
                "trueName": "����Ⱥ",
                "crateTime": "2018-12-10 16:13:23",
                "log": "������Ϣ"
            },
            {
                "trueName": "����Ⱥ",
                "crateTime": "2018-12-10 16:13:23",
                "log": "������Ϣ"
            },
            {
                "trueName": "����Ⱥ",
                "crateTime": "2018-12-10 16:13:23",
                "log": "������Ϣ"
            }
        ]
    },
    "message": "Success"
}
```

### 12.�ҵ�����

```http
http://localhost:28080/ssk/apply/selectApply
```

#### post����

| ����       | ���� | �Ƿ���� | ˵�� |
| ---------- | ---- | -------- | ---- |
| pageNumber | int  | ��       | ҳ�� |
| pageSize   | int  | ��       | ���� |



#### ����ֵ

- data ��������
- totalRow������
- pageNumberҳ��
- firstPage�Ƿ��ǵ�һҳ
- lastPage�Ƿ����һҳ
- totalPage��ҳ��
- pageSize����
- list��������
- trueName������
- crateTime����ʱ��
- deptName���벿��
- reason��������
- typeName״̬
- resources��Դ����
- id������

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
                "trueName": "����Ⱥ",
                "deptName": "������",
                "reason": "������Ҫ",
                "crateTime": "2018-12-11 10:08:32",
                "typeName": "��ͨ��",
                "resources": "������Ϣ",
                "id": 211
            },
            {
                "trueName": "����Ⱥ",
                "deptName": "������",
                "reason": "������Ҫ",
                "crateTime": "2018-12-11 10:08:32",
                "typeName": "������",
                "resources": "������Ϣ",
                "id": 210
            },
            {
                "trueName": "����Ⱥ",
                "deptName": "������",
                "reason": "������Ҫ",
                "crateTime": "2018-12-11 10:08:32",
                "typeName": "��ͨ��",
                "resources": "������Ϣ",
                "id": 209
            },
            {
                "trueName": "����Ⱥ",
                "deptName": "������",
                "reason": "������Ҫ",
                "crateTime": "2018-12-11 10:08:32",
                "typeName": "������",
                "resources": "������Ϣ",
                "id": 208
            },
            {
                "trueName": "����Ⱥ",
                "deptName": "������",
                "reason": "������Ҫ",
                "crateTime": "2018-12-11 10:08:32",
                "typeName": "������",
                "resources": "������Ϣ",
                "id": 207
            }
        ]
    },
    "message": "Success"
}
```

### 13.�ҵ�����鿴

```http
http://localhost:28080/ssk/apply/selectOneApply
```

#### post����

| ���� | ���� | �Ƿ���� | ˵��     |
| ---- | ---- | -------- | -------- |
| id   | int  | ��       | ������ |



#### ����ֵ

- data ����
- reason��������
- resources��Դ����

#### example

```json
{
    "code": 200,
    "data": {
        "reason": "������Ҫ",
        "resources": "�˿ڵǼ���Ϣ"
    },
    "message": "Success"
}
```

### 14.��ѯָ���Ƿ����

```http
http://localhost:28080/ssk/index/isExist
```

#### post����

| ���� | ����   | �Ƿ���� | ˵��     |
| ---- | ------ | -------- | -------- |
| name | String | ��       | ָ������ |



#### ����ֵ

- data boolean���ͣ�true���ڣ�false�����ڡ�

#### example

```json
{
    "code": 200,
    "data": true,
    "message": "Success"
}
```

### 14.��ѯ������

```http
http://localhost:28080/ssk/index/selectDataItem
```

#### post����

| ���� | ����   | �Ƿ���� | ˵��     |
| ---- | ------ | -------- | -------- |
| name | String | ��       | ָ������ |



#### ����ֵ

- data ��������
- column_name�ֶ�Ӣ����
- column_comment�ֶ�������

#### example

```json
{
    "code": 200,
    "data": [
        {
            "column_name": "id",
            "column_comment": "Ψһ����"
        },
        {
            "column_name": "name",
            "column_comment": "����"
        },
        {
            "column_name": "oldName",
            "column_comment": "������"
        },
        {
            "column_name": "sex",
            "column_comment": "�Ա�"
        },
        {
            "column_name": "race",
            "column_comment": "����"
        },
        {
            "column_name": "cardType",
            "column_comment": "���֤������"
        },
        {
            "column_name": "cardNum",
            "column_comment": "���֤����"
        },
        {
            "column_name": "politics",
            "column_comment": "������ò"
        },
        {
            "column_name": "CZRKZZ",
            "column_comment": "������"
        },
        {
            "column_name": "CZRKCSRQ",
            "column_comment": "��������"
        },
        {
            "column_name": "CZRKCSDGJ",
            "column_comment": "����"
        },
        {
            "column_name": "pic",
            "column_comment": "��Ƭ"
        },
        {
            "column_name": "religion",
            "column_comment": "�ڽ�����"
        },
        {
            "column_name": "CZRKBYZK",
            "column_comment": "����״��"
        },
        {
            "column_name": "health",
            "column_comment": "����״��"
        },
        {
            "column_name": "serviceType",
            "column_comment": "�����������"
        },
        {
            "column_name": "isLd",
            "column_comment": "�Ƿ���������Ա"
        },
        {
            "column_name": "migrationReason",
            "column_comment": "����ԭ��"
        },
        {
            "column_name": "migrationTime",
            "column_comment": "����ʱ��"
        },
        {
            "column_name": "migrationSource",
            "column_comment": "������Դ"
        },
        {
            "column_name": "permit",
            "column_comment": "��ʱ��ס֤��"
        },
        {
            "column_name": "economysource",
            "column_comment": "������Դ"
        },
        {
            "column_name": "ishouse",
            "column_comment": "����ס��"
        },
        {
            "column_name": "mainType",
            "column_comment": "�Ǹɶ�������"
        },
        {
            "column_name": "personType",
            "column_comment": "��Ա����"
        },
        {
            "column_name": "b_address",
            "column_comment": "¥����ַ"
        },
        {
            "column_name": "b_updatedate",
            "column_comment": "¥�������������"
        },
        {
            "column_name": "unit_number",
            "column_comment": "��Ԫ��"
        },
        {
            "column_name": "checkin_date",
            "column_comment": "��סʱ��"
        },
        {
            "column_name": "r_createdate",
            "column_comment": "������Ϣ���εǼ�����"
        },
        {
            "column_name": "r_updatedate",
            "column_comment": "������Ϣ�����������"
        },
        {
            "column_name": "communityname",
            "column_comment": "��������"
        },
        {
            "column_name": "zhlx",
            "column_comment": "ס������"
        },
        {
            "column_name": "lastPatrolTime",
            "column_comment": "�����߷�ʱ��"
        },
        {
            "column_name": "visitingtime",
            "column_comment": "�߷�ʱ��"
        },
        {
            "column_name": "hasPatrol",
            "column_comment": "�Ƿ����߷�"
        },
        {
            "column_name": "patrolLevel",
            "column_comment": "�߷õȼ�"
        },
        {
            "column_name": "manageType",
            "column_comment": "�ܿض�������"
        },
        {
            "column_name": "operationTime",
            "column_comment": "����ʱ��"
        }
    ],
    "message": "Success"
}
```

### 15.ָ�����ñ༭����

```http
http://localhost:28080/ssk/index/editIndex
```

#### post����

| ����     | ����    | �Ƿ����           | ˵��                       |
| -------- | ------- | ------------------ | -------------------------- |
| ename    | String  | ����               | ��Ӣ�������ֶ�Ӣ����       |
| islast   | boolean | ����               | �Ƿ����һ��ָ��           |
| level    | int     | ����               | ָ��ȼ�                   |
| children | list    | ����               | �Ӽ���������               |
| name     | String  | ����               | ����                       |
| pid      | String  | ����(����ָ��ѡ��) | ��id                       |
| id       | long    | ����(����ָ��ѡ��) | ���id                     |
| type     | int     | ����               | ���ͣ�1.�˿�2.��ҵ3.���ݣ� |
| cid      | String  | ����(����ָ��ѡ��) | ��id                       |

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
                    "name": "�Ǽ���Ϣ",
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
                    "name": "����������Ϣ",
                    "pid": "1",
                    "id": 7,
                    "type": 1,
                    "cid": "7"
                },
                ...
  ]
```



#### ����ֵ

- data ��������
- ename��Ӣ�������ֶ�Ӣ����
- islast�Ƿ����һ��ָ��
- children�Ӽ���������
- name����
- pid��id
- id���id
- cid��id
- levelָ��ȼ�

#### example

```json
{
    "code": 200,
    "data": "�༭�ɹ�",
    "message": "Success"
}                    
```

### 16.��ѯ����

```
http://localhost:28080/ssk/qs/selectQS
```

#### post����

| ����  | ����   | �Ƿ���� | ˵��                       |
| ----- | ------ | -------- | -------------------------- |
| type  | int    | ����     | ���ͣ�1.�˿�2.��ҵ3.���ݣ� |
| value | String | ����     | ��ѯֵ                     |

#### ����ֵ

- data ��������
- result �������飺��ѯ���ݣ���ѯ�˿ں���ҵ��ʱ���У�
- --values ��������
- ----ename ��Ӣ�������ֶ�Ӣ����
- ----num ��ѯ����
- ----name ָ������
- ----type ״̬0���꣨δ���룩1���������У�2��ȫ����ͨ����3��������
- ----cid ��id
- --nameһ��ָ��
- rkdzxxinfo�������飨�˿ڵ�ַ��Ϣ�������ݵ�ַ��Ϣ����addressInfo��ַ��Ϣ��bzdz��׼��ַ��dzCode��ַ���룬FWYT������;��type��ַ���ͣ�
- dh�������飨����������ѯ�˿ں���ҵ��ʱ���У�
- --ename ��Ӣ�������ֶ�Ӣ����
- --name ָ������
- --cid ��id
- --islast�Ƿ����һ��
- fwxxbaseinfo�������飨���ݵ�ַ��Ϣ����addressInfo��ַ��Ϣ��bzdz��׼��ַ��dzCode��ַ���룬FWYT������;��type��ַ���ͣ�
- qybaseinfo�������飨��ҵ��Ϣ��QYMC��ҵ���ƣ�ZCHע��ţ�UNISCIDͳһ������ô��룩����ѯ�˿ں���ҵ��ʱ���У�
- rkbaseinfo�������飨�˿���Ϣ��cardNum���֤�ţ�name������CZRKCSRQ�������ڣ�CZRKZZסַ��CZRKMZ���壬CZRKXB�Ա𣩣���ѯ��ҵ���ݵ�ʱ���У�
- jcxx�������飨������Ϣ�����˿���Ϣ��cardNum���֤�ţ�name������CZRKCSRQ�������ڣ�CZRKZZסַ��CZRKMZ���壬CZRKXB�Ա𣩣���ҵ��Ϣ��QYMC��ҵ���ƣ�ZCHע��ţ�UNISCIDͳһ������ô��룩����ѯ�˿ڻ���ҵ��Ϣ��ʱ���У�
- qydzxxinfo�������飨��ҵ��ַ��Ϣ�������ݵ�ַ��Ϣ����addressInfo��ַ��Ϣ��bzdz��׼��ַ��dzCode��ַ���룬FWYT������;��type��ַ���ͣ�
- qydzxxinfo_qy�������飨��ҵ��ַ��Ϣ��Ӧ����ҵ��Ϣ��
- qydzxxinfo_rk�������飨��ҵ��ַ��Ϣ��Ӧ���˿���Ϣ��
- fwxxbaseinfo_rk�������飨���ݵ�ַ��Ϣ��Ӧ���˿���Ϣ��
- fwxxbaseinfo_qy�������飨���ݵ�ַ��Ϣ��Ӧ����ҵ��Ϣ��
- rkdzxxinfo_qy�������飨�˿ڵ�ַ��Ϣ��Ӧ����ҵ��Ϣ��
- rkdzxxinfo_rk�������飨�˿ڵ�ַ��Ϣ��Ӧ���˿���Ϣ��

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
                        "name": "�Ǽ���Ϣ",
                        "type": 1,
                        "cid": "6"
                    },
                   ......
                    {
                        "ename": null,
                        "num": 0,
                        "name": "��ϵ��Ϣ��������",
                        "type": 0,
                        "cid": "3324"
                    }
                ],
                "name": "������Ϣ"
            },
            
            {
                "values": [
                    {
                        "ename": null,
                        "num": 0,
                        "name": "��������1",
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
                "name": "����"
            }
           
        ],
        "rkdzxxinfo": [],
        "dh": [
            {
                "ename": null,
                "islast": false,
                "name": "������Ϣ",
                "cid": "1"
            },
            .......
            {
                "ename": null,
                "islast": false,
                "name": "���Բ���",
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

### 17.��ѯ������Դ��Ϣ

```
http://localhost:28080/ssk/qs/selectOneQS
```

#### post����

| ����  | ����   | �Ƿ���� | ˵��                       |
| ----- | ------ | -------- | -------------------------- |
| cid   | String | ����     | ���ͣ�1.�˿�2.��ҵ3.���ݣ� |
| value | String | ����     | ��ѯֵ                     |

#### ����ֵ

- data ����
- Header�������飨��ͷ��
- --column_name�ֶ�Ӣ����
- --column_comment�ֶ�������
- table�������飨���ݣ�

#### example

```
{
    "code": 200,
    "data": {
        "Header": [
            {
                "column_name": "id",
                "column_comment": "Ψһ����"
            },
            {
                "column_name": "name",
                "column_comment": "����"
            },
            ......
            {
                "column_name": "hasPatrol",
                "column_comment": "�Ƿ����߷�"
            },
            {
                "column_name": "patrolLevel",
                "column_comment": "�߷õȼ�"
            },
            {
                "column_name": "manageType",
                "column_comment": "�ܿض�������"
            },
            {
                "column_name": "operationTime",
                "column_comment": "����ʱ��"
            }
        ],
        "table": []
    },
    "message": "Success"
}
```

### 18.����ȫ��Ϣ

```
http://localhost:28080/ssk/qs/apply
```

#### post����

| ����   | ����   | �Ƿ���� | ˵��        |
| ------ | ------ | -------- | ----------- |
| cid    | String | ����     | ָ������cid |
| reason | String | ����     | ��ѯֵ      |

#### ����ֵ

- data ����ɹ�

#### example

```
{
    "code": 200,
    "data": "����ɹ�",
    "message": "Success"
} 
```

















###  