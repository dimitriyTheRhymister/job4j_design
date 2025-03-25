--#1
create
or replace function tax_statement_after()
    returns trigger as
$$
    BEGIN
        update products
        set price = price + price/2
        where id = (select id from inserted);
        return new;
    END;
$$
LANGUAGE 'plpgsql';

create trigger tax_statement_after_trigger
    after insert
    on products
    referencing new table as
                    inserted
    for each statement
    execute procedure tax_statement_after();

--#2
create
or replace function tax_row_before()
    returns trigger as
$$
    BEGIN
        update products
        set price = price + price/2
        where id = new.id;
        return NEW;
    END;
$$
LANGUAGE 'plpgsql';

create
or replace function tax_row_before()
    returns trigger as
$$
    BEGIN
        update products
        set price = price + price/2
        where id = new.id;
        return NEW;
    END;
$$
LANGUAGE 'plpgsql';

--#3
create
or replace function log_row_in_history_of_price()
    returns trigger as
$$
    BEGIN
        insert into history_of_price (name, price, date)
		values (NEW.name, NEW.price, CURRENT_TIMESTAMP);
        return NEW;
    END;
$$
LANGUAGE 'plpgsql';

create trigger log_row_in_history_of_price_trigger
    after insert
    on products
    for each row
    execute procedure log_row_in_history_of_price();
