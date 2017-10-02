
 	  insert into clockcatagory values (1, '排钟');
      insert into clockcatagory  values (2, '点钟');
      insert into clockcatagory  values (3, '挑钟');
      
      select * from clockcatagory;
      
      insert into workstatus values(1,'非工作');
      insert into workstatus values(2,'工作中');
      insert into workstatus values(3,'待接收');
      insert into workstatus values(4,'服务中');
      insert into workstatus values(5,'空闲');
      
      select * from workstatus order by number desc;
    
      insert into orderstatus values(1,'待确定');
      insert into orderstatus values(2,'服务中');
      insert into orderstatus values(3,'待支付');
      insert into orderstatus values(4,'支付完成');
      insert into orderstatus values(5,'订单失效');
      
      select * from orderstatus;
      
      insert into paypath (number, payPath) value(1, '支付宝');
      insert into paypath (number, payPath) values(2, '微信');
      insert into paypath (number, payPath) values(3, '现金');
      insert into paypath (number, payPath) values(4, '其他');
      
      select * from paypath;
      
      insert into roomcatagory (number, name) values(1, '洗脚');
      insert into roomcatagory (number, name) values(2, '按摩');
      
      select * from roomcatagory;
      
      insert into admin (idAdmin, password, addTime) values('admin', 'admin', now());
      
      select * from admin;
      
      insert into room (idRoom, catagory, floor, size, remark, addTime) values ('11', '洗脚', '1', '1' , '洗脚', now());
      insert into room (idRoom, catagory, floor, size, remark, addTime) values ('206', '洗脚', '2', '2' , '洗脚', now());
      insert into room (idRoom, catagory, floor, size, remark, addTime) values ('208', '按摩', '2', '2' , '按摩', now());
      select * from room;
      
      