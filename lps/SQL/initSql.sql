
 	  insert into clockcatagory values (1, '����');
      insert into clockcatagory  values (2, '����');
      insert into clockcatagory  values (3, '����');
      
      select * from clockcatagory;
      
      insert into workstatus values(1,'�ǹ���');
      insert into workstatus values(2,'������');
      insert into workstatus values(3,'������');
      insert into workstatus values(4,'������');
      insert into workstatus values(5,'����');
      
      select * from workstatus order by number desc;
    
      insert into orderstatus values(1,'��ȷ��');
      insert into orderstatus values(2,'������');
      insert into orderstatus values(3,'��֧��');
      insert into orderstatus values(4,'֧�����');
      insert into orderstatus values(5,'����ʧЧ');
      
      select * from orderstatus;
      
      insert into paypath (number, payPath) value(1, '֧����');
      insert into paypath (number, payPath) values(2, '΢��');
      insert into paypath (number, payPath) values(3, '�ֽ�');
      insert into paypath (number, payPath) values(4, '����');
      
      select * from paypath;
      
      insert into roomcatagory (number, name) values(1, 'ϴ��');
      insert into roomcatagory (number, name) values(2, '��Ħ');
      
      select * from roomcatagory;
      
      insert into admin (idAdmin, password, addTime) values('admin', 'admin', now());
      
      select * from admin;
      
      insert into room (idRoom, catagory, floor, size, remark, addTime) values ('11', 'ϴ��', '1', '1' , 'ϴ��', now());
      insert into room (idRoom, catagory, floor, size, remark, addTime) values ('206', 'ϴ��', '2', '2' , 'ϴ��', now());
      insert into room (idRoom, catagory, floor, size, remark, addTime) values ('208', '��Ħ', '2', '2' , '��Ħ', now());
      select * from room;
      
      