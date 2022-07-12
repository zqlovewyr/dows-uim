```
-- queryUserList
select t.*  from account_instance  t
where t.deleted = 0
[@and t.status = bean.status]
[@and t.account_name like #{bean.accountName:like} or t.id = #{bean.accountName}]
[@and t.login_name like bean.loginName]
[@and t.phone like bean.phone]
[@and t.email like bean.email]
[@and t.employee_no = bean.employeeNo]
[@and t.id in( select account_id from  account_tenant where tenant_id = #{bean.tenantId})]
[@and t.id not in( select account_id from  account_tenant where tenant_id = #{bean.notTenantId})]
order by t.id desc
```

```
-- queryAccountIdList
select t.* from account_identifier t 
where t.deleted = 0 
[@@and t.identifier in loginList]
[@and t.account_id != accountId]

```

```
-- querySimpleUserByIds
select  t.*  from account_instance  t
where t.deleted = 0
[@@and  t.id in userIds]

```
