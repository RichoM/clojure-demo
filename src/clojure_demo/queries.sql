-- :name select-all-people :? :*
select * from people;

-- :name insert-person :! :n
insert into people (nombre, apellido, edad)
values (:nombre, :apellido, :edad);

-- :name update-person :! :n
update people
set nombre = :nombre, apellido = :apellido, edad = :edad
where id = :id;
