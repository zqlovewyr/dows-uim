```
-- queryAccountByGroup

select acc.*,t.org_id,org.org_name from account_group t 
            left join account_organization org on t.org_id = org.id 
            left join account_instance acc on t.account_id = acc.id
            left join account_tenant ate on t.account_id = ate.account_id
            where t.deleted = 0 and org.deleted= 0 and acc.deleted = 0
            and t.org_id = #{p.groupId}
            [@and ate.tenant_id = #{p.tenantId} and ate.deleted=0]

```

```
-- queryGroupList

select t.*,ti.tenant_name from account_organization t left join tenant_instance ti on t.tenant_id = ti.id
where t.deleted = 0 and ti.deleted = 0 and t.org_typ = 0
[@and t.status = p.status]
[@and t.tenant_id = p.tenantId]
[@and t.org_name = p.orgName]
order by t.id desc

```
