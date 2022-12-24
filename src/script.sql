select * from student;
select * from student where age between 10 and 17;
select name from student;
select * from student where name like '%а%';
select * from student where age < student.id;
select * from student order by age;

select count(*) from student;
select avg(age) from student;
select * from student order by id desc limit 5;