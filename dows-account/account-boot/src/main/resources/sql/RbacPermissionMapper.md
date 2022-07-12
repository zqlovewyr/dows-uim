```
-- queryPermList
select t.*,p.permission_name parent_name ,b.category_id,b.category_name,b.spu_name product_name from rbac_permission  t 
            left join rbac_permission p on t.resource_pid = p.id 
            left join product_spu b on t.product_id = b.id
            where t.deleted= 0 
         [@and t.permission_code  like p.permissionCode]   
         [@and t.permission_name  like p.permissionName]  
         [@and t.resource_typ  = p.resourceTyp] 
         [@and t.category_id  = p.categoryId] 
         [@and t.is_platform  = p.isPlatform] 
         [@and t.resource_path  like p.resourcePath] 
         order by t.id desc
```

```
-- queryCurrentPermByPlatform
select p.id,p.permission_name,p.permission_code,p.resource_pid,
p.resource_path,p.resource_typ 
from rbac_accredit t left join rbac_role r on t.role_id = r.id and r.is_platform = 1
left join rbac_permission p on t.permission_id = p.id
left join principal_role pr on t.role_id = pr.role_id
where  t.deleted =0 and r.deleted = 0 and p.deleted =0 and pr.deleted =0 
and r.is_platform = 1 and pr.principal_id = #{id} and pr.principal_typ = 0


```

```
-- queryPermSimpleList

select t.id,t.permission_name,t.permission_code,t.resource_pid,t.resource_path from rbac_permission  t 
 where t.deleted =0 
 [@and t.product_id = p.productId]
 [@and t.resource_typ = p.resourceTyp]
 [@and t.is_platform = p.isPlatform]
  [@and t.category_id = p.categoryId]
  
```

```
-- queryCurrentPermByTenant
select p.id,p.permission_name,p.permission_code,p.resource_pid,
p.resource_path,p.resource_typ,pr.principal_id
from rbac_accredit t left join rbac_role r on t.role_id = r.id 
left join rbac_permission p on t.permission_id = p.id
left join principal_role pr on t.role_id = pr.role_id
where  t.deleted =0 and r.deleted = 0 and p.deleted =0 and pr.deleted =0 
and r.is_platform = 0 
and pr.principal_id = #{accountId}
and pr.principal_typ = 0
and r.tenant_id in (0,#{tenantId})

```


