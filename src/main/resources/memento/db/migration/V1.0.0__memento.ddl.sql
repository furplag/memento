create table if not exists flyway (
  version text
);
-- drop table if exists memento cascade;
create table if not exists memento (
  prefix varchar(64) not null,
  name varchar(64) not null,
  value text not null,
  sort_order integer not null default 0,
  created bigint not null default 0,
  modified bigint not null default 0,
  version integer not null default 1,
  constraint memento_pk primary key(prefix, name)
);
