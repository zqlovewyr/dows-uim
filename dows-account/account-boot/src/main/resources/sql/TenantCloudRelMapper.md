```
-- queryByTenant
select * from tenant_cloud_rel where tenant_id = #{tenantId}
and deleted = 0
```

```
-- queryInitUser
select t.id account_id,t.login_name user_name,b.id tenant_id,b.tenant_name project_name  from account_instance t,tenant_instance b
where t.id = #{accountId} and b.id = #{tenantId}
limit 1 

```

```
-- queryRelCloudUser
select t.account_id user_id,acc.account_name user_name,t.rel_user_id,ou.user_name rel_user_name
from account_cloud_rel t left join account_instance acc on t.account_id = acc.id
left join openstack_user ou on t.rel_user_id = ou.user_id 

```

```
-- queryRelCloudProject
select t.account_id user_id,t.tenant_id,ti.tenant_name,op.project_id id,op.project_name `name` 
from account_tenant t left join tenant_instance ti on t.tenant_id = ti.id
left join openstack_project op on t.tenant_id = op.tenant_id
where op.project_id IS NOT NULL

```
