select * from fauna where like 'fish%';
select * from fauna where avg_age > 10000 and avg_age < 21000;
select * from fauna where avg_age is null;
select * from fauna where discovery_date< '01.01.1950';