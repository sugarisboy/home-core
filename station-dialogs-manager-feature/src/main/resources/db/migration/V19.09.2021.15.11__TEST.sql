create table timer
(
	id     uuid        not null,
	start  timestamptz not null,
	finish timestamptz not null,
	notify boolean     not null default false,
	name   varchar,
	primary key (id)
)
