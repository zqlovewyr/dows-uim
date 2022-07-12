```
-- queryRoleById
select r.*,t.principal_id from principal_role t left join rbac_role r on t.role_id = r.id 
where t.deleted = 0  and r.deleted = 0
[@and t.principal_typ = type]
[@and t.principal_id in ids]
```

```
-- deleteRoleRel
update principal_role t left join rbac_role r on t.role_id = r.id 
set t.deleted =  1 where t.deleted =0
[@@and t.principal_id in accountIds]
 and t.principal_typ = #{principalTyp}
and r.is_platform= #{isPlatform}

```

```
-- accountRoleList

select r.* from principal_role t left join rbac_role r on t.role_id = r.id 
where t.deleted =  0 and r.deleted =0 
and t.principal_typ = 0
and t.principal_id = #{p.accountId}
[@and r.is_platform = p.isPlatform]

```
