alter table cities add column priority integer; 


update cities set priority = 0; 


update cities set priority = 1 where name like '%Miami%'; 
update cities set priority = 1 where name like '%Homestead%';
update cities set priority = 1 where name like '%Lauderdale%';

update cities set priority = 2 where name = 'Miami';

