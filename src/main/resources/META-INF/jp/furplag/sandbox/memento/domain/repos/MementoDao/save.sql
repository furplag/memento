insert into memento (
  prefix
, name
, value
/*%if entity.sortOrder != null *//*# ", sort_order" *//*%end */
/*%if entity.version != null *//*# ", version" *//*%end */
/*%if entity.created != null *//*# ", created" *//*%end */
/*%if entity.modified != null *//*# ", modified" *//*%end */
) values (
  /* entity.prefix */''
, /* entity.name */''
, /* entity.value */''
/*%if entity.version != null *//*# ", " *//* entity.version.version */1/*%end */
/*%if entity.sortOrder != null *//*# ", " *//* entity.sortOrder */0/*%end */
/*%if entity.created != null *//*# ", " *//* entity.created.created */null/*%end */
/*%if entity.modified != null *//*# ", " *//* entity.modified.modified */null/*%end */
) on conflict(prefix, name) do update set
  value = /* entity.value */''
, version = (select version + 1 from memento where prefix = /* entity.prefix */'' and name = /* entity.name */'')
/*%if entity.sortOrder != null *//*# ", " *//* entity.sortOrder */0/*%end */
/*%if entity.created != null *//*# ", " *//* entity.created.created */null/*%end */
/*%if entity.modified != null *//*# ", " *//* entity.modified.modified */null/*%end */
;
