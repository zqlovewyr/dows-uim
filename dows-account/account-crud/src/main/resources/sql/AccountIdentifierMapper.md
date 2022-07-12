```
-- updateAccountIdentifier
insert into account_identifier(account_id,identifier,login_type,deleted,dt) 
            values (#{accountId},#{identifier},#{loginType},0,now()) on DUPLICATE key update 
            dt = now(),identifier=#{identifier},login_type=#{loginType},deleted= 0

```
