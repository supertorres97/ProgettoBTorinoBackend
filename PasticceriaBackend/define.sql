
    create table carrello (
        id integer not null auto_increment,
        id_utente integer not null,
        primary key (id)
    ) engine=InnoDB;

    create table carrello_prodotto (
        id integer not null auto_increment,
        id_carrello integer not null,
        id_prodotto integer not null,
        quantita integer not null,
        primary key (id)
    ) engine=InnoDB;

    create table credenziali (
        id integer not null auto_increment,
        id_utente integer,
        password varchar(255) not null,
        username varchar(255) not null,
        primary key (id)
    ) engine=InnoDB;

    create table dettagli_ordine (
        id integer not null auto_increment,
        id_ordine integer not null,
        id_prodotto integer not null,
        prezzo_totale float(53) not null,
        quantita_finale integer not null,
        primary key (id)
    ) engine=InnoDB;

    create table feedback (
        id integer not null auto_increment,
        id_ordine integer not null,
        id_prodotto integer not null,
        id_utente integer not null,
        descrizione varchar(255) not null,
        voto enum ('CINQUE','DUE','QUATTRO','TRE','UNO') not null,
        primary key (id)
    ) engine=InnoDB;

    create table list_ruoli (
        id_credenziali integer not null,
        id_ruolo integer not null
    ) engine=InnoDB;

    create table messaggi_sistema (
        id integer not null auto_increment,
        codice varchar(255) not null,
        messaggio varchar(255),
        primary key (id)
    ) engine=InnoDB;

    create table ordine (
        id integer not null auto_increment,
        id_utente integer not null,
        totale float(53) not null,
        data_ordine datetime(6) not null,
        indirizzo varchar(255) not null,
        status enum ('Annullato','Confermato','Consegnato','InElaborazione','Spedito'),
        primary key (id)
    ) engine=InnoDB;

    create table prodotto (
        disponibile bit not null,
        id integer not null auto_increment,
        id_tipo_prodotto integer not null,
        peso float(53) not null,
        prezzo float(53) not null,
        stock integer not null,
        descrizione varchar(255) not null,
        nome varchar(255) not null,
        primary key (id)
    ) engine=InnoDB;

    create table ruoli (
        id integer not null auto_increment,
        descrizione varchar(255) not null,
        primary key (id)
    ) engine=InnoDB;

    create table tipo_prodotto (
        id integer not null auto_increment,
        descrizione varchar(255) not null,
        primary key (id)
    ) engine=InnoDB;

    create table utente (
        id integer not null auto_increment,
        c_fiscale varchar(255),
        cap varchar(255) not null,
        citta varchar(255) not null,
        cognome varchar(255) not null,
        email varchar(255) not null,
        nome varchar(255) not null,
        via varchar(255) not null,
        primary key (id)
    ) engine=InnoDB;

    alter table carrello 
       add constraint UKl287dga2nb4ahi1j34on39ruk unique (id_utente);

    alter table credenziali 
       add constraint UK55rv5to43fyaahrohm7d4t7q1 unique (id_utente);

    alter table credenziali 
       add constraint UKmomch2qmcwhvr1f1dvk1kah4w unique (username);

    alter table messaggi_sistema 
       add constraint UKkaf1a347fh5bclqeejk8sk4w3 unique (codice);

    alter table utente 
       add constraint UKgxvq4mjswnupehxnp35vawmo2 unique (email);

    alter table carrello 
       add constraint FKimyxl9cko6g83slko5cldpbh 
       foreign key (id_utente) 
       references utente (id);

    alter table carrello_prodotto 
       add constraint FKir1jaswo6kpbtaq20jtudt07 
       foreign key (id_carrello) 
       references carrello (id);

    alter table carrello_prodotto 
       add constraint FKlkxhm09x9ttuaqdog1pl3wj6p 
       foreign key (id_prodotto) 
       references prodotto (id);

    alter table credenziali 
       add constraint FK6808uc4b2j3e7ksasxf8st8q3 
       foreign key (id_utente) 
       references utente (id);

    alter table dettagli_ordine 
       add constraint FKrr4t6htmas17es4sodgfkkfv 
       foreign key (id_ordine) 
       references ordine (id);

    alter table dettagli_ordine 
       add constraint FK3ghbrfbsxogcphw8q34ev7gtp 
       foreign key (id_prodotto) 
       references prodotto (id);

    alter table feedback 
       add constraint FKqhdsmnpqwhlyeilhn7sokblo3 
       foreign key (id_ordine) 
       references ordine (id);

    alter table feedback 
       add constraint FK5gke1bbx471nda0k7n0qdifs9 
       foreign key (id_prodotto) 
       references prodotto (id);

    alter table feedback 
       add constraint FKop3716ted36un9qdk89bl1n6h 
       foreign key (id_utente) 
       references utente (id);

    alter table list_ruoli 
       add constraint FK9oqmxuqs9x505dd5j7tcg95j9 
       foreign key (id_ruolo) 
       references ruoli (id);

    alter table list_ruoli 
       add constraint FKlmy0038psugdhgcob3d6qcqij 
       foreign key (id_credenziali) 
       references credenziali (id);

    alter table ordine 
       add constraint FKgsxxfj3dm1kfppteavqrvkwcr 
       foreign key (id_utente) 
       references utente (id);

    alter table prodotto 
       add constraint FK24wbwkji4r4c7i0s9y3uq06vi 
       foreign key (id_tipo_prodotto) 
       references tipo_prodotto (id);
