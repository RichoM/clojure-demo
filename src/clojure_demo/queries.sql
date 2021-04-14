-- :name get-all-pokemons :? :*
select * from pokemon;

-- :name get-all-types :? :*
select * from type;

-- :name insert-type! :! :n
insert into type (name) values (:name);

-- :name insert-pokemon! :! :n
insert into pokemon (id, name, hp, attack, defense, speed, sp_defense, sp_attack)
values (:id, :name, :hp, :attack, :defense, :speed, :sp_defense, :sp_attack);

-- :name add-pokemon-type! :! :n
insert into pokemon_type (pokemon_id, type_id)
values (:id, (select id from type where name = :type));

-- :name get-pokemon-by-id :? :1
select * from pokemon where id = :id;

-- :name get-pokemon-types :? :*
select t.name from type t
inner join pokemon_type pt on pt.type_id = t.id
where pt.pokemon_id = :id;
