--delete procedure
create
or replace procedure delete_data(u_id integer)
language 'plpgsql'
as $$
    begin
        delete
		from products
        where id = u_id;
    end;
$$;

--delete function
create
or replace function f_delete_data()
returns void
language 'plpgsql'
as
$$
    begin
        delete
		from products
        where count = 0;
    end;
$$;


