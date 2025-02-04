
    alter table carrello 
       drop 
       foreign key FKimyxl9cko6g83slko5cldpbh;

    alter table carrello_prodotto 
       drop 
       foreign key FKir1jaswo6kpbtaq20jtudt07;

    alter table carrello_prodotto 
       drop 
       foreign key FKlkxhm09x9ttuaqdog1pl3wj6p;

    alter table credenziali 
       drop 
       foreign key FK6808uc4b2j3e7ksasxf8st8q3;

    alter table dettagli_ordine 
       drop 
       foreign key FKrr4t6htmas17es4sodgfkkfv;

    alter table dettagli_ordine 
       drop 
       foreign key FK3ghbrfbsxogcphw8q34ev7gtp;

    alter table feedback 
       drop 
       foreign key FKqhdsmnpqwhlyeilhn7sokblo3;

    alter table feedback 
       drop 
       foreign key FK5gke1bbx471nda0k7n0qdifs9;

    alter table feedback 
       drop 
       foreign key FKop3716ted36un9qdk89bl1n6h;

    alter table list_ruoli 
       drop 
       foreign key FK9oqmxuqs9x505dd5j7tcg95j9;

    alter table list_ruoli 
       drop 
       foreign key FKlmy0038psugdhgcob3d6qcqij;

    alter table ordine 
       drop 
       foreign key FKgsxxfj3dm1kfppteavqrvkwcr;

    alter table prodotto 
       drop 
       foreign key FK24wbwkji4r4c7i0s9y3uq06vi;

    drop table if exists carrello;

    drop table if exists carrello_prodotto;

    drop table if exists credenziali;

    drop table if exists dettagli_ordine;

    drop table if exists feedback;

    drop table if exists list_ruoli;

    drop table if exists messaggi_sistema;

    drop table if exists ordine;

    drop table if exists prodotto;

    drop table if exists ruoli;

    drop table if exists tipo_prodotto;

    drop table if exists utente;
