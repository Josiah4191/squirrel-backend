create table item (
    id bigserial primary key,
    name varchar(50) not null,
    description varchar(255) not null
);

create table squirrel (
    id bigserial primary key,
    name varchar(50) not null
);

create table stash (
    id bigserial primary key,
    squirrel_id int references squirrel(id) on delete cascade not null,
    location varchar(255) not null
);

create table stash_line (
    id bigserial primary key,
    stash_id int references stash(id) on delete cascade not null,
    item_id int references item(id) not null,
    quantity int not null constraint chk_stash_line_quantity_positive check (quantity >= 0),
    constraint unique_stash_line unique (stash_id, item_id)
);

create index idx_stash_squirrel_id on stash(squirrel_id);
create index idx_stash_line_stash_id on stash_line(stash_id);
create index idx_stash_line_item_id on stash_line(item_id);