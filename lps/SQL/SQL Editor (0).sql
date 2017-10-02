
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
      
      insert into clockcatagory values ('排钟');
      insert into clockcatagory  values ('点钟');
      insert into clockcatagory  values ('挑钟');
      
      select * from clockcatagory;
      
      insert into workstatus (workStatus) values('待系统确认');
      insert into workstatus (workStatus) values('待支付');
      insert into workstatus (workStatus) values('已失效');
      insert into workstatus (workStatus) values('支付完成');
      
      insert into paypath (payPath) values("支付宝");
      insert into paypath (payPath) values("微信");
      insert into paypath (payPath) values("现金");
      
      select * from paypath;
      
      select * from workstatus;
      
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
      