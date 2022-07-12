```
-- countAccountNum
select tenant_id id, count(*) num from account_tenant 
where deleted = 0  [@@and tenant_id in tenantIds]
group by tenant_id
```

```
-- countGroupNum
select tenant_id id,count(*) num from account_organization
where deleted = 0 [@@and tenant_id in tenantIds]
group by tenant_id

```

```
-- queryAccountTenant
select b.*,t.tenant_id from account_tenant t 
left join account_instance b on t.account_id = b.id 
where t.deleted =0
[@and t.tenant_id = p.tenantId]
[@and b.account_name like p.accountName]
```

```
-- updateAccountTenant
insert into account_tenant(account_id,tenant_id,dt,deleted)
values (#{accountId},#{tenantId},now(),0) on DUPLICATE key update
            dt = now(),deleted = 0

```

```
-- queryAccountTenantList
select b.*,t.tenant_id from account_tenant t
            left join tenant_instance b on t.tenant_id = b.id 
where t.deleted = 0 and b.deleted = 0 
[@and t.account_id = p.accountId]
```
