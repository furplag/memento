merge into memento dest
using (
  select
  /*^ e.prefix */'' as prefix
, /*^ e.name */'' as name
, /*^ e.value */'' as value
, /*^ e.sortOrder */0 as sort_order
, /*^ e.created.created */0 as created
, /*^ e.modified.modified */0 as modified
, /*^ e.version.version */0 as version
) source on (dest.prefix = source.prefix and dest.name = source.name)
when matched then update set
  value = source.value
, sort_order = source.sort_order
, modified = source.modified
, version = (dest.version + 1)
where version = dest.version
when not matched then insert values (
  source.prefix
, source.name
, source.value
, source.sort_order
, source.created
, source.modified
, source.version
)
