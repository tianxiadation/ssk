**ͨ��Լ��**

1. ����**HOST**����**xxxxxxx.com**
2. ���е�**SALT**�����ַ���**exam.com.cn**(������֤ʱʹ��)

## Ŀ¼

#### �˺�

1. �ӿ��ĵ�

2. ��½

3. ע��

4. �˳�

5. �һ�����(��������)

6. ������֤��

7. ��֤�ֻ���(�����޸��ֻ�����)

   ------



#### 1.�ӿ��ĵ�

```http
http://localhost:8080/ssk/if/selectInterface
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

#### 2.ʱ��ͬ��

```http
https://exam.com/system/get_system_time
```

#### post����

| ������ | ����   | �Ƿ���� | ע��     |
| ------ | ------ | -------- | -------- |
| uuid   | String | ��       | �豸��� |
|        |        |          |          |
|        |        |          |          |

#### ����ֵ

- data ������ϵͳ��ʱ�䣨13λʱ�����

#### example

```json
{
    "code": 200,
    "msg": "success",
    "data": 1400000000000
}
```

## 