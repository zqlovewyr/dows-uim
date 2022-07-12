```
-- queryTenantList
select t.tenant_id,ti.tenant_name from tke_account t left join tenant_instance ti on t.tenant_id = ti.id
where 1=1 
[@@and t.tenant_name in accountList]

```
