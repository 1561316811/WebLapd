
	
go
DROP TRIGGER IF EXISTS serverorder_AFTER_UPDATE_for_workrank;
go
go
CREATE TRIGGER serverorder_AFTER_UPDATE_for_workrank AFTER UPDATE ON serverorder
FOR EACH ROW
BEGIN
	DECLARE num int ; 
	if(old.status = '待员工确定' and new.status = '服务中') then
		 if(old.id not in (select id from workrank )) then
		 	if (old.clockcatagory <> '点钟') then
			 	insert into workrank values (old.id, 1, 0);
			else
			 	insert into workrank values (old.id, 0, 1);
			end if;
         elseif (old.clockcatagory <> '点钟') then 
			set num = (select rankNum from workrank where workrank.id = old.id);
            update workrank set rankNum = num + 1 where workrank.id = old.id;
		 else
			set num = (select spotNum from workrank where workrank.id = old.id);
            update workrank set spotNum = num + 1 where workrank.id = old.id;
		 end if;
    end if;
END
go


-- 当订单状态为服务中时，同时修改员工的工作状态为工作状态  
go
DROP TRIGGER IF EXISTS serverorder_AFTER_UPDATE_for_user;
go
go
CREATE TRIGGER serverorder_AFTER_UPDATE_for_user AFTER UPDATE ON serverorder
FOR EACH ROW
BEGIN

	if(new.status = '服务中') then
		update user set user.status = '服务中' where user.idUser = new.idUser; 
	elseif(new.status = '待服务') then
		update user set user.status = '等待确认订单'
	else
		update user set user.status = '空闲' where user.idUser = new.idUser;
	end if;
	
END
go
	
	select * from admin;


    select
        `idRoom`,
        `catagory`,
        `floor`,
        `size`,
        `remark` 
    from
        `lapd`.`room`
        
        
        delete from room;
        
     select * from catagory;
     
     select * from room;
        
     delete from catagory;
     
     select * from user;
     
     insert into admin values('admin', 'admin', now());
     
     delete from catagory where name='5sdf';
     
     update catagory set name='lll' where name='sdf';
     
      insert into catagory values('1');
      
      drop table clockcatagory;
      
      select * from clockcatagory;
      select * from paypath;
      
      select * from workstatus;
      
      update workstatus set number = '2' where number = 12;
      
      select * from serverorder;
      
      select * from information_schema.triggers where TRIGGER_NAME='serverorder_AFTER_UPDATE';
      
      delete from serverorder;

	  delete from workrank;
	  
	  select * from workrank;
      
      select idOrder from serverorder where idOrder = '011201710021703046';
      
      select count(*) from serverorder where idOrder = '011201710022013562';
      
	  update serverorder set status= '服务中' where idOrder = '011201710022013562';
      
      update serverorder  where idOrder = '011201710021703046' limit 1,1;
      
      update workrank set spotNum = 1 where spotNum = 1; 

update serverorder set status= '服务中' where idOrder = '011201710022010291'

update serverorder set status= '服务中' where idOrder = '011201710022136526'

select * from admin;

select * from clockcatagory;

select * from serverorder;

delete from serverorder;
      