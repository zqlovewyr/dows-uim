```
-- groupAddAccount
insert into account_group(account_id,org_id,deleted,dt) 
            values (#{accountId},#{orgId},0,now()) on DUPLICATE key update 
            dt = now(),deleted= #{deleted}
            
```

```
-- countByGroup
select org_id id ,count(*) num from account_group where deleted = 0 
[@@and org_id in orgIds]
group by org_id
```
