```
-- queryRoleList
select t.*,ti.tenant_name from rbac_role t left join tenant_instance ti on t.tenant_id = ti.id
where t.deleted = 0
[@and t.role_name like bean.roleName]
[@and t.tenant_id = bean.tenantId]
[@and ti.tenant_name like bean.tenantName]
[@and t.is_platform = bean.isPlatform]
order by t.id desc
```
